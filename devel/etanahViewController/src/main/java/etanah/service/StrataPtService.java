/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.service;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.wideplay.warp.persist.Transactional;
import etanah.dao.*;
import etanah.dao.gis.PelanGISDAO;
import etanah.model.*;
import etanah.model.gis.PelanGIS;
import etanah.model.strata.*;
import etanah.service.common.*;
import etanah.view.etanahActionBeanContext;
import etanah.view.etanahContextListener;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import etanah.report.ReportUtil;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import etanah.workflow.StageContext;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import oracle.bpel.services.workflow.task.model.Task;
import net.sourceforge.stripes.action.FileBean;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Restrictions;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class StrataPtService {

    private Injector injector = etanahContextListener.getInjector();
    @Inject
    PermohonanWaranItemDAO permohonanWaranItemDAO;
    @Inject
    PermohonanPihakDAO permohonanPihakDAO;
    @Inject
    PemohonDAO pemohonDAO;
    @Inject
    ProjekDAO projekDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    SkimStrataDAO skimStrataDAO;
    @Inject
    PihakDAO pihakDAO;
    @Inject
    PermohonanBangunanDAO permohonanBangunanDAO;
    @Inject
    BangunanTingkatDAO bangunanTingkatDAO;
    @Inject
    BangunanPetakDAO bangunanPetakDAO;
    @Inject
    BangunanPetaAksesoriDAO bangunanPetakAksesoriDAO;
    @Inject
    PermohonanStrataDAO permohonanStrataDAO;
    @Inject
    HakmilikUrusanDAO hakmilikUrusanDAO;
    @Inject
    PermohonanKertasDAO permohonanKertasDAO;
    @Inject
    SejarahHakmilikDAO sejarahHakmilikDAO;
    @Inject
    PermohonanKertasKandunganDAO permohonanKertasKandunganDAO;
    @Inject
    HakmilikTukarGantiStrataDAO hakmilikTukarGantiStrataDAO;
    @Inject
    KodStatusHakmilikDAO kodSTSDAO;
    @Inject
    SemakDokumenService semakDokumenService;
    @Inject
    ReportUtil reportUtil;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    PermohonanRujukanLuarDAO permohonanRujukanLuarDAO;
    @Inject
    PermohonanTuntutanKosDAO permohonanTuntutanKosDAO;
    @Inject
    BadanPengurusanDAO badanPengurusanDAO;
    @Inject
    KodCaraPermohonanDAO kodCaraPermohonanDAO;
    @Inject
    KodBandarPekanMukimDAO kodBandarPekanMukimDAO;
    @Inject
    PermohonanJUBLDAO mohonJUBLDAO;
    @Inject
    KodCawanganDAO kodCawanganDAO;
    @Inject
    SenaraiRujukanDAO senarairujukDAO;
    @Inject
    WakilKuasaDAO wakilKuasaDAO;
    @Inject
    ImejLaporanDAO imejLaporanDAO;
    @Inject
    TransaksiDAO transaksiDAO;
    @Inject
    LaporanTanahDAO laporanTanahDAO;
    @Inject
    PermohonanLaporanKandunganDAO laporanKandunganDAO;
    @Inject
    PermohonanPermitButirDAO permohonanPermitButirDAO;
    @Inject
    JUBLDAO jublDAO;
    @Inject
    PermitDAO permitDAO;
    @Inject
    KodAgensiDAO kodAgensiDAO;
    @Inject
    PermohonanSkimDAO mohonSkimDAO;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    DokumenDAO dokumenDAO;
    @Inject
    PelaksanaWaranDAO pelaksanaWaranDAO;
    @Inject
    FasaPermohonanDAO fasaPermohonanDAO;
    @Inject
    PermohonanSyarikatLelongDAO permohonanSyarikatLelongDAO;
    @Inject
    SytJuruLelongDAO sytJurulelongDAO;
    @Inject
    StorRampasanDAO storRampasanDAO;
    @Inject
    WaranDAO waranDAO;
    @Inject
    WaranPihakDAO waranPihakDAO;
    @Inject
    PermohonanTandatanganDokumenDAO permohonanTandatanganDokumenDAO;
    @Inject
    AduanStrataDAO aduanStrataDAO;
    @Inject
    LanjutanTempohDAO lanjutanTempohDAO;
    @Inject
    UrusanDokumenDAO urusanDokumenDAO;
    @Inject
    PermohonanSemakDokumenDAO permohonanSemakDokumenDAO;
    @Inject
    PegawaiPenyiasatDAO pegawaiPenyiasatDAO;
    @Inject
    HakmilikPetakAksesoriDAO hakmilikPetakAksesoriDAO;
    @Inject
    SiasatanAduanImejDAO siasatanAduanImejDAO;
    @Inject
    KandunganFolderDAO kandunganFolderDAO;
    @Inject
    SiasatanPerihalKemudahanDAO siasatanPerihalKemudahanDAO;
    @Inject
    SiasatanPerihalBangunanDAO siasatanPerihalBangunanDAO;
    @Inject
    HakmilikPihakBerkepentinganDAO hakmilikPihakBerkepentinganDAO;
    @Inject
    AkaunDAO akaunDAO;
    @Inject
    PermohonanSyarikatLelongDAO mohonSytLelongDAO;
    @Inject
    PermohonanHartaBersamaDAO permohonanHartaBersamaDAO;
    //added
    @Inject
    HakmilikSebelumDAO hakmilikSebelumDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    DokumenService dokumenService;
    @Inject
    KandunganFolderService kandunganFolderService;
    @Inject
    KodKlasifikasiDAO kodKlasifikasiDAO;
    @Inject
    PihakAlamatTambDAO pihakAlamatDAO;
    @Inject
    IntegrasiPermohonanDAO integrasiPermohonanDAO;
    @Inject
    IntegrasiPermohonanButirDAO integrasiPermohonanButirDAO;
    @Inject
    IntegrasiPermohonanDokumenDAO integrasiPermohonanDokumenDAO;
    @Inject
    PelanGISDAO planGISDAO;
    private ArrayList<HakmilikPermohonan> listMohonHakmilikBaru = new ArrayList<HakmilikPermohonan>();
    private ArrayList<Hakmilik> listHakmilikBaru = new ArrayList<Hakmilik>();
    private ArrayList<HakmilikPihakBerkepentingan> lhp = new ArrayList<HakmilikPihakBerkepentingan>();
    private ArrayList<HakmilikSebelum> hakmiliksebelumlist = new ArrayList<HakmilikSebelum>();
    private List<HakmilikSebelum> hmSblm = new ArrayList<HakmilikSebelum>();
    @Inject
    private HakmilikPihakKepentinganService hpkService;
    @Inject
    private RegService regService;
    @Inject
    private HakmilikSebelumPermohonanDAO hakmilikSebelumPermohonanDAO;
    private String kodNegeri;
    private UrusanDokumen urusanDokumen;
    @Inject
    private etanah.Configuration conf;

    @Transactional
    public void updateMohon(Permohonan p) {
        permohonanDAO.update(p);
    }

    @Transactional
    public void simpanBangunan(PermohonanBangunan bangunan) {
        permohonanBangunanDAO.saveOrUpdate(bangunan);
    }
    
    @Transactional
    public void simpanIdSkim(PermohonanBangunan permohonanSkim) {
        permohonanBangunanDAO.saveOrUpdate(permohonanSkim);
    }
    
    @Transactional
    public void simpanSiasatanPerihalBangunan(SiasatanPerihalBangunan siasatanbangunan) {
        siasatanPerihalBangunanDAO.saveOrUpdate(siasatanbangunan);
    }

    @Transactional
    public void simpanhakmilik(Hakmilik hakmilik) {
        hakmilikDAO.saveOrUpdate(hakmilik);
    }

    @Transactional
    public void simpanOrUpdatehakmilik(Hakmilik hakmilik) {
        hakmilikDAO.update(hakmilik);
    }

    @Transactional
    public void simpanhakmilikPetakAks(HakmilikPetakAksesori hakmilikPetakAksesori) {
        hakmilikPetakAksesoriDAO.saveOrUpdate(hakmilikPetakAksesori);
    }

    @Transactional
    public void deletehakmilikPetakAks(HakmilikPetakAksesori hakmilikPetakAksesori) {
        hakmilikPetakAksesoriDAO.delete(hakmilikPetakAksesori);
    }

    @Transactional
    public void simpanBdnPengurusan(BadanPengurusan badanPengurusan) {
        badanPengurusanDAO.saveOrUpdate(badanPengurusan);
    }

    @Transactional
    public void simpanSiasatanPerihalKemudahan(SiasatanPerihalKemudahan siasatankemudahan) {
        siasatanPerihalKemudahanDAO.saveOrUpdate(siasatankemudahan);
    }

    @Transactional
    public void updateBangunan(PermohonanBangunan bangunan) {
        permohonanBangunanDAO.update(bangunan);
    }

    @Transactional
    public void simpanBangunanTingkat(BangunanTingkat tingkat) {
        bangunanTingkatDAO.saveOrUpdate(tingkat);
    }

    @Transactional
    public void simpanHmTukarGantiStrata(HakmilikTukarGantiStrata hmStrataTTG) {
        hakmilikTukarGantiStrataDAO.saveOrUpdate(hmStrataTTG);
    }

    @Transactional
    public void saveKodAgensi(KodAgensi kodAgensi) {
        kodAgensiDAO.saveOrUpdate(kodAgensi);
    }

    @Transactional
    public void delKodAgensi(KodAgensi kodAgensi) {
        kodAgensiDAO.delete(kodAgensi);
    }

    @Transactional
    public void simpanBangunanTingkatnoUpdate(BangunanTingkat tingkat) {
        bangunanTingkatDAO.save(tingkat);
    }

    @Transactional
    public void updateTingkat(BangunanTingkat tingkat) {
        bangunanTingkatDAO.update(tingkat);
    }

    @Transactional
    public void simpanPetak(BangunanPetak petak) {
        bangunanPetakDAO.saveOrUpdate(petak);
    }

    @Transactional
    public void simpanPetaknoUpdate(BangunanPetak petak) {
        bangunanPetakDAO.save(petak);
    }

    @Transactional
    public void updatePetak(BangunanPetak petak) {
        bangunanPetakDAO.update(petak);
    }

    @Transactional
    public void simpanSemua(BangunanPetak petak) {
        bangunanPetakDAO.update(petak);
    }

    @Transactional
    public void simpanPemilik(PermohonanStrata pemilik) {
        permohonanStrataDAO.saveOrUpdate(pemilik);
    }

    @Transactional
    public void updatePemilik(PermohonanStrata pemilik) {
        permohonanStrataDAO.update(pemilik);
    }

    @Transactional
    public void simpanMaklumatBangunan(BadanPengurusan mc) {
        badanPengurusanDAO.saveOrUpdate(mc);
    }

    @Transactional
    public void simpanProjek(Projek pr) {
        projekDAO.saveOrUpdate(pr);
    }

    @Transactional
    public void delProjek(Projek pr) {
        projekDAO.delete(pr);
    }

    @Transactional
    public void updateMaklumatBangunan(BadanPengurusan mc) {
        badanPengurusanDAO.update(mc);
    }

    @Transactional
    public void simpanPetakAksesori(BangunanPetakAksesori petakAksesori) {
        bangunanPetakAksesoriDAO.save(petakAksesori);
    }

    @Transactional
    public void updatePetakAksesori(BangunanPetakAksesori petakAksesori) {
        bangunanPetakAksesoriDAO.update(petakAksesori);
    }

    @Transactional
    public void deleteBngn(PermohonanSkim mohonSkim) {
//        deleteBngn(bangunan);
        mohonSkimDAO.delete(mohonSkim);

    }

    @Transactional
    public void deleteBngn(PermohonanBangunan bangunan) {
//        if(!bangunan.getSenaraiTingkat().isEmpty()){
//        deletechild(bangunan);
//        }
        permohonanBangunanDAO.delete(bangunan);
    }

    @Transactional
    public void simpanPelanGIS(PelanGIS pelanGIS) {
        planGISDAO.save(pelanGIS);
    }

    @Transactional
    public void saveOrUpdatePelanGIS(PelanGIS pelanGIS) {
        planGISDAO.saveOrUpdate(pelanGIS);
    }

    @Transactional
    public void deletePelanGIS(PelanGIS pelanGIS) {
//        deleteBngn(bangunan);
        planGISDAO.delete(pelanGIS);

    }

    public void deletechild(PermohonanBangunan bangunan) {
        List<BangunanTingkat> listTingkat = findTingkat(bangunan);
        deletePtkAksrByIdBngn(bangunan.getIdBangunan());
        for (BangunanTingkat tingkat : listTingkat) {
            checkPetak(tingkat);
            deleteTgkt(tingkat);
        }
    }

    private List<BangunanTingkat> findTingkat(PermohonanBangunan bangunan) {
        String[] name = {"bangunan"};
        Object[] value = {bangunan};
        List tingkat = bangunanTingkatDAO.findByEqualCriterias(name, value, null);
        return tingkat;
    }

    public void checkPetak(BangunanTingkat tingkat) {
        List<BangunanPetak> listPetak = findPetak(tingkat);
        for (BangunanPetak petak : listPetak) {
            deletePetak(petak);
        }
    }

    private List<BangunanPetak> findPetak(BangunanTingkat tingkat) {
        String[] name = {"tingkat"};
        Object[] value = {tingkat};
        List petak = bangunanPetakDAO.findByEqualCriterias(name, value, null);
        return petak;
    }
    
    @Transactional
    public void deletePetak(BangunanPetak petak) {
        bangunanPetakDAO.delete(petak);
    }

    @Transactional
    public void deleteTgkt(BangunanTingkat tingkat) {
        bangunanTingkatDAO.delete(tingkat);
    }

    @Transactional
    public void deleteAksesori(BangunanPetakAksesori petakAksesori) {
        bangunanPetakAksesoriDAO.delete(petakAksesori);
    }

    @Transactional
    public void SimpanLaporanTanah(PermohonanStrata laportanah) {
        permohonanStrataDAO.saveOrUpdate(laportanah);
    }

    @Transactional
    public void updateLaporanTanah(PermohonanStrata laportanah) {
        permohonanStrataDAO.update(laportanah);
    }

    @Transactional
    public void SimpanSempadan(PermohonanStrata sempadan) {
        permohonanStrataDAO.saveOrUpdate(sempadan);
    }

    @Transactional
    public void simpanPermohonanKertas(PermohonanKertas permohonanKertas) {
        permohonanKertasDAO.saveOrUpdate(permohonanKertas);
    }

    @Transactional
    public PermohonanKertas simpanPermohonanKertasObject(PermohonanKertas permohonanKertas) {
        permohonanKertas = permohonanKertasDAO.saveOrUpdate(permohonanKertas);
        return permohonanKertas;
    }

    @Transactional
    public PermohonanKertasKandungan simpanPermohonanKertasKandungan(PermohonanKertasKandungan permohonanKertasKandungan) {
        return permohonanKertasKandunganDAO.saveOrUpdate(permohonanKertasKandungan);
    }

    @Transactional
    public void SimpanBngnKosRendah(PermohonanStrata kos_rendah) {
        permohonanStrataDAO.saveOrUpdate(kos_rendah);
    }

    @Transactional
    public void updateBngnKosRendah(PermohonanStrata kos_rendah) {
        permohonanStrataDAO.update(kos_rendah);
    }

    @Transactional
    public void SimpanMohonRujukLuar(PermohonanRujukanLuar mohon) {
        permohonanRujukanLuarDAO.saveOrUpdate(mohon);
    }

    @Transactional
    public void updateMohonRujukLuar(PermohonanRujukanLuar mohon) {
        permohonanRujukanLuarDAO.update(mohon);
    }

    @Transactional
    public void updatePihak(Pihak pihak) {
        pihakDAO.update(pihak);
    }

    @Transactional
    public void simpanPihak(Pihak pihak) {
        pihakDAO.saveOrUpdate(pihak);
    }

    @Transactional
    public void simpanSijil(BadanPengurusan sijil) {
        badanPengurusanDAO.saveOrUpdate(sijil);
    }

    @Transactional
    public void simpanBayaran(PermohonanTuntutanKos bayaran) {
        permohonanTuntutanKosDAO.saveOrUpdate(bayaran);
    }

    @Transactional
    public void deleteBayaran(PermohonanTuntutanKos bayaran) {
        permohonanTuntutanKosDAO.delete(bayaran);
    }

    @Transactional
    public void updateBayaran(PermohonanTuntutanKos bayaran) {
        permohonanTuntutanKosDAO.update(bayaran);
    }

    //murali 06-07-2011
    @Transactional
    public void simpanAduanStrata(AduanStrata aduanStrata) {
        aduanStrataDAO.saveOrUpdate(aduanStrata);
    }

    @Transactional
    public void deletePtkAksr(long idPetak) {
        String query = "DELETE FROM etanah.model.BangunanPetakAksesori b where b.petak.idPetak = :idPetak";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setParameter("idPetak", idPetak);
        q.executeUpdate();
    }

    @Transactional
    public void deletePtkAksrByIdBngn(long idBangunan) {
        String query = "DELETE FROM etanah.model.BangunanPetakAksesori b where b.bangunan.idBangunan = :idBangunan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setParameter("idBangunan", idBangunan);
        q.executeUpdate();
    }

    @Transactional
    public void deleteMohonStrata(long idStrata) {
        String query = "DELETE FROM etanah.model.PermohonanStrata p where p.idStrata = :idStrata";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setParameter("idStrata", idStrata);
        q.executeUpdate();
    }

    @Transactional
    public void deleteMohonBangunan(String idPermohonan) {
        String query = "DELETE FROM etanah.model.PermohonanBangunan p where p.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.executeUpdate();
    }

    /**
     * dah xle pakai
     */
    public List<PermohonanStrata> findListID(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PermohonanStrata b where b.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();

    }

    public PermohonanStrata findID(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PermohonanStrata b where b.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (PermohonanStrata) q.uniqueResult();

    }

    public PermohonanStrata findIDnoIDMH(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PermohonanStrata b where b.permohonan.idPermohonan = :idPermohonan AND b.hakmilikPermohonan.id IS NULL";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (PermohonanStrata) q.uniqueResult();

    }

    public PermohonanStrata findIDNamaSkim(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PermohonanStrata b where b.permohonan.idPermohonan = :idPermohonan AND b.namaSkim IS NOT NULL AND b.hakmilikPermohonan.id IS NULL";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (PermohonanStrata) q.uniqueResult();

    }

    public List<BadanPengurusan> getHighestNoSiri(String kod, String tahun) {
        Session s = sessionProvider.get();
        String query = "SELECT m FROM etanah.model.strata.BadanPengurusan m WHERE m.pengurusanNoSijil = "
                + "(SELECT MAX(m2.pengurusanNoSijil) from etanah.model.strata.BadanPengurusan m2 "
                + "where m2.cawangan.kod = :kod and YEAR(m2.pengurusanTarikhRujukan) = :tahun)";
        Query q = s.createQuery(query);
        q.setString("kod", kod);
        q.setString("tahun", tahun);
        return q.list();
    }

    public List<BadanPengurusan> getHighestNoSiriByTahun(String kod, Integer tahun) {
        Session s = sessionProvider.get();
        String query = "SELECT m FROM etanah.model.strata.BadanPengurusan m WHERE m.pengurusanNoSijil = (SELECT MAX(m2.pengurusanNoSijil) from etanah.model.strata.BadanPengurusan m2 where m2.cawangan.kod = :kod and YEAR(m2.infoAudit.tarikhMasuk) = :tahun)";
        Query q = s.createQuery(query);
        q.setString("kod", kod);
        q.setInteger("tahun", tahun);
        return q.list();
    }

//    public List<Hakmilik> findYearPermit() {
//
//        String query = "SELECT distinct year(h.tarikhDaftar)FROM etanah.model.Permohonan p, etanah.model.Hakmilik h, etanah.model.HakmilikPermohonan hp WHERE p.idPermohonan like '%STR%' AND p.keputusan.kod = 'SL' AND p.idPermohonan = hp.permohonan.idPermohonan AND hp.hakmilik.idHakmilik = h.idHakmilikInduk";
//        Session s = sessionProvider.get();
//        Query q = s.createQuery(query);
//        return q.list();
//    }
    public List<Hakmilik> findYear() {
        String query = "SELECT distinct year(b.tarikhDaftar)"
                + " FROM etanah.model.Hakmilik b"
                + " WHERE"
                + " b.idHakmilikInduk is not null and"
                + " b.tarikhDaftar is not null order by year(b.tarikhDaftar) asc";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        return q.list();
    }

    public List<PermohonanSkim> findNamaSkim() {
        String query = "SELECT distinct (b.namaProjek)"
                + " FROM etanah.model.PermohonanSkim b";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        return q.list();
    }

    public List<Pihak> findBdnUrus() {
        String query = "SELECT distinct (b.nama)"
                + " FROM etanah.model.Hakmilik a, etanah.model.Pihak b, etanah.model.strata.BadanPengurusan c"
                + " WHERE"
                + " a.idHakmilikInduk is not null and"
                + " a.badanPengurusan.idBadan = c.idBadan and"
                + " c.pihak.idPihak = b.idPihak";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        return q.list();
    }

    public PermohonanPihakPendeposit findByPendeposit(String idPermohonan) {
        String query = "Select p FROM etanah.model.PermohonanPihakPendeposit p WHERE p.permohonan.idPermohonan = :idPermohonan";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (PermohonanPihakPendeposit) q.uniqueResult();
    }

    public PermohonanStrata getHighestKodSijilRendah() {
        Session s = sessionProvider.get();
        String query = "SELECT m FROM etanah.model.PermohonanStrata m WHERE m.perakuanKosRendahNoSijil = (SELECT MAX(m2.perakuanKosRendahNoSijil) from etanah.model.PermohonanStrata m2)";
        Query q = s.createQuery(query);
        return (PermohonanStrata) q.uniqueResult();
    }

    public List<PermohonanStrata> findIDMS(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PermohonanStrata b where b.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public BadanPengurusan findBdn(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.strata.BadanPengurusan b where b.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (BadanPengurusan) q.uniqueResult();

    }

    public BadanPengurusan findBdnIdHm380(String idPermohonan, String idHakmilik) {
        String query = "SELECT b FROM etanah.model.strata.BadanPengurusan b where b.permohonan.idPermohonan = :idPermohonan "
                + " and b.pengurusanNoRujukan = :idHakmilik";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("idHakmilik", idHakmilik);
        return (BadanPengurusan) q.uniqueResult();
    }

    public BadanPengurusan findBdnbyIdPihak(Long idPihak) {
        String query = "SELECT b FROM etanah.model.strata.BadanPengurusan b where b.pihak.idPihak = :idPihak";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idPihak", idPihak);
        return (BadanPengurusan) q.uniqueResult();

    }
    
    //nad tambah 7/7/2020
    public BadanPengurusan findBdnByIdHmInduk(String idHakmilik) {
        String query = "SELECT distinct c "
                +" FROM etanah.model.Hakmilik a, etanah.model.Pihak b, etanah.model.strata.BadanPengurusan c "
                +" WHERE "
                +" a.idHakmilikInduk is not null and "
                +" a.badanPengurusan.idBadan = c.idBadan and "
                +" c.pihak.idPihak = b.idPihak and "
                +" a.idHakmilikInduk = :idHakmilik ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        return (BadanPengurusan) q.uniqueResult();
    }
    
    //nad tambah 9/7/2020
    public List<Hakmilik> findBlokByIDHakmilik(String idHakmilik) {
        String query = "SELECT distinct h.noBangunan FROM etanah.model.Hakmilik h where h.idHakmilikInduk = :idHakmilik AND h.noBangunan LIKE 'M%'";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        return q.list();
    }
    
    //nad tambah 9/7/2020
    public List<Hakmilik> findPetakByIDHakmilik(String idHakmilik) {
        String query = "SELECT h FROM etanah.model.Hakmilik h where h.idHakmilikInduk = :idHakmilik AND h.kodKategoriBangunan.kod in ('B','L')";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        return q.list();
    }
    
    //nad tambah 10/7/2020
    public List<Hakmilik> findBlokProvisionalByIDHakmilik(String idHakmilik) {
        String query = "SELECT h FROM etanah.model.Hakmilik h where h.idHakmilikInduk = :idHakmilik AND h.kodKategoriBangunan.kod in ('P')";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        return q.list();
    }
    
    //nad tambah 10/7/2020
    public List<Hakmilik> findPetakProvisionalByIDHakmilik(String idHakmilik) {
        String query = "SELECT h FROM etanah.model.Hakmilik h where h.idHakmilikInduk = :idHakmilik AND h.kodKategoriBangunan.kod in ('PL')";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        return q.list();
    }

    public PermohonanBangunan findALLID(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PermohonanBangunan b where b.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (PermohonanBangunan) q.uniqueResult();

    }

    public Pemohon findById(String idPermohonan) {
        String query = "SELECT p FROM etanah.model.Pemohon p where p.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (Pemohon) q.uniqueResult();
        /*if (q.list().size() > 1) {
            return (Pemohon) q.list().get(0);
        } else {
            return (Pemohon) q.uniqueResult();
        }*/

    }

    public List<PermohonanBangunan> checkMhnBangunanExist(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PermohonanBangunan b WHERE b.permohonan.idPermohonan = :idPermohonan order by b.idBangunan asc";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();

    }
    //yus tambah 16/05/2019
    public List<PermohonanBangunan> checkMhnBangunanPnPL(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PermohonanBangunan b WHERE b.permohonan.idPermohonan = :idPermohonan and (b.kodKategoriBangunan = 'P' or b.kodKategoriBangunan = 'PL') order by b.idBangunan asc";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();

    }

    public List<PermohonanBangunan> checkMhnBangunanExist2(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PermohonanBangunan b WHERE b.permohonan.idPermohonan = :idPermohonan and b.kodKategoriBangunan.kod = 'B' order by b.idBangunan asc";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();

    }

    public PermohonanBangunan findByNama(String nama, String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PermohonanBangunan b where b.nama = :nama and b.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("nama", nama);
        q.setString("idPermohonan", idPermohonan);
        return (PermohonanBangunan) q.uniqueResult();

    }

    public PermohonanBangunan findLanded(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PermohonanBangunan b WHERE b.permohonan.idPermohonan = :idPermohonan and b.kodKategoriBangunan.kod like 'L'";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (PermohonanBangunan) q.uniqueResult();

    }

    public Dokumen findDok(String idPermohonan, String kodDokumen) {
        String query = " select d from Permohonan p, "
                + "KandunganFolder k, Dokumen d "
                + "where p.idPermohonan = :idPermohonan and p.folderDokumen.id = k.folder.id "
                + "and k.dokumen.id = d.idDokumen and d.kodDokumen.id = :kodDokumen ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("kodDokumen", kodDokumen);
        return (Dokumen) q.uniqueResult();

    }

    public List<UrusanDokumen> getListDokByUrusan(String kodUrusan) {
        String query = "select b from UrusanDokumen b where b.kodUrusan = :kodUrusan and AKTIF = 'Y' and b.kodDokumen.kod NOT IN ('GB','PC4','RBU','SCR','PJL','XML','DHK') order by b.kodDokumen.kod";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("kodUrusan", kodUrusan);
        return q.list();
    }

    public List<PermohonanBangunan> findByIDPermohonanByNamaDesc(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PermohonanBangunan b where b.permohonan.idPermohonan = :idPermohonan order by b.nama";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<PermohonanBangunan> findByIDPermohonan1(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PermohonanBangunan b where b.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<PermohonanBangunan> findByIDPermohonan(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PermohonanBangunan b where b.permohonan.idPermohonan = :idPermohonan AND b.nama LIKE '%M%'";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    //added by ida 13-07-201
    public List<PermohonanBangunan> findByIDMohonBlok(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PermohonanBangunan b where b.permohonan.idPermohonan = :idPermohonan AND b.kodKategoriBangunan.kod LIKE '%B%'";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<PermohonanBangunan> findByIDMohonBlokWoProv(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PermohonanBangunan b where b.permohonan.idPermohonan = :idPermohonan AND b.kodKategoriBangunan.kod !='P'";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<PermohonanBangunan> findByIDMohonBlok2(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PermohonanBangunan b where b.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    //added by ida 13-07-2013
    public List<PermohonanBangunan> findByIDMohonByProvisional(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PermohonanBangunan b where b.permohonan.idPermohonan = :idPermohonan AND b.kodKategoriBangunan.kod LIKE '%P%'";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    //added by ida 13-07-2013
    public List<PermohonanBangunan> findByIDMohonByLanded(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PermohonanBangunan b where b.permohonan.idPermohonan = :idPermohonan AND b.kodKategoriBangunan.kod LIKE '%L%'";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    // added by ida 08/01/2013
    public long getBilPetak(String idPermohonan, String jenis) {
        Session s = sessionProvider.get();
        String query = "SELECT sum(b.bilanganPetak) as petak FROM etanah.model.PermohonanBangunan b where b.permohonan.idPermohonan = :idPermohonan AND b.kodKategoriBangunan.kod =:jenis ";
        Query q = s.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("jenis", jenis);
        return (Long) q.uniqueResult();
    }

    // added by ida 08/01/2013
    public long getBilPetakL(String idPermohonan) {
        Session s = sessionProvider.get();
        String query = "SELECT b.bilanganPetak as petak FROM etanah.model.PermohonanBangunan b where b.permohonan.idPermohonan = :idPermohonan ";
        Query q = s.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (Long) q.uniqueResult();
    }

    public List<PermohonanBangunan> findByIDPermohonanByLandparcel(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PermohonanBangunan b where b.permohonan.idPermohonan = :idPermohonan AND b.nama LIKE '%L%'";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<BangunanPetak> findByPetakByIDTingkat(Long idTingkat) {
        String query = "SELECT b FROM etanah.model.BangunanPetak b where b.tingkat.idTingkat = :idTingkat";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idTingkat", idTingkat);
        return q.list();
    }

    public List<PermohonanBangunan> findByIDPermohonanByBlock(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PermohonanBangunan b where b.permohonan.idPermohonan = :idPermohonan AND b.kodKategoriBangunan.kod = 'B' order by b.nama";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<PermohonanBangunan> findByIDPermohonanByBlockP(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PermohonanBangunan b where b.permohonan.idPermohonan = :idPermohonan AND b.kodKategoriBangunan.kod = 'P' order by b.nama";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public PermohonanTuntutanKos findByIDMohon(String idPermohonan, String kod) {
        String query = "SELECT b FROM etanah.model.PermohonanTuntutanKos b where b.permohonan.idPermohonan = :idPermohonan and b.kodTuntut.kod = :kod";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("kod", kod);
        return (PermohonanTuntutanKos) q.uniqueResult();
    }

    public List<BangunanPetakAksesori> findByIDBgnn(String idBangunan) {
        String query = "SELECT b FROM etanah.model.BangunanPetakAksesori b where b.bangunan.idBangunan = :idBangunan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idBangunan", idBangunan);
        return q.list();
    }

    public BangunanTingkat findByPetak(String idTingkat) {
        String query = "SELECT b FROM etanah.model.BangunanTingkat b where b.idTingkat = :idTingkat";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idTingkat", idTingkat);
        return (BangunanTingkat) q.uniqueResult();

    }
    
    public BangunanPetak findByPetakNew(String nama, Long idTingkat) {
        String query = "SELECT b FROM etanah.model.BangunanPetak b where b.nama = :nama and b.tingkat.idTingkat = :idTingkat";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setParameter("nama", nama);
        q.setParameter("idTingkat", idTingkat);
        return (BangunanPetak) q.uniqueResult();

    }

    public List<BangunanTingkat> findByIDBangunanList(Long idBangunan) {
        String query = "SELECT b FROM etanah.model.BangunanTingkat b where b.bangunan.idBangunan = :idBangunan ORDER BY LPAD(lower(b.nama),10,0) ASC";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setParameter("idBangunan", idBangunan);
        return q.list();

    }

    public BangunanPetak findByIDPetak(Long idTingkat, String nama) {
        String query = "SELECT b FROM etanah.model.BangunanPetak b where b.tingkat.idTingkat = :idTingkat and b.nama = :nama";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setParameter("idTingkat", idTingkat);
        q.setParameter("nama", nama);
        return (BangunanPetak) q.uniqueResult();

    }

    public List<BangunanPetak> findByIDPetak2(Long idTingkat) {
        String query = "SELECT b FROM etanah.model.BangunanPetak b where b.tingkat.idTingkat = :idTingkat ORDER BY LPAD(lower(b.nama),10,0) ASC";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setParameter("idTingkat", idTingkat);
        return q.list();

    }

    public List<BangunanPetak> findByIDPetak3(Long idTingkat, String nama) {
        String query = "SELECT b FROM etanah.model.BangunanPetak b where b.tingkat.idTingkat = :idTingkat ORDER BY LPAD(lower(b.nama),10,0) ASC";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setParameter("idTingkat", idTingkat);
        return q.list();

    }

    public BangunanPetakAksesori findByIDPetakAksr(Long idPetak, String nama) {
        String query = "SELECT b FROM etanah.model.BangunanPetakAksesori b where b.petak.idPetak = :idPetak and b.nama = :nama";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setParameter("idPetak", idPetak);
        q.setParameter("nama", nama);
        return (BangunanPetakAksesori) q.uniqueResult();

    }

    public List<BangunanPetakAksesori> findByIDPetakAksr2(Long idPetak) {
        String query = "SELECT b FROM etanah.model.BangunanPetakAksesori b where b.petak.idPetak = :idPetak";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setParameter("idPetak", idPetak);
        return q.list();
    }

    public List<BangunanTingkat> findByIDBangunan(String idBangunan) {
        String query = "SELECT b FROM etanah.model.BangunanTingkat b where b.bangunan.idBangunan = :idBangunan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idBangunan", idBangunan);
        return q.list();

    }

    public List<BangunanPetak> findByIDPetak(String idPetak) {
        String query = "SELECT b FROM etanah.model.BangunanPetak b where b.idPetak = :idPetak";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPetak", idPetak);
        return q.list();

    }

    public KodCawangan findByKodCaw(String kodCaw) {
        String query = "Select b FROM etanah.model.KodCawangan b Where b.kod = :kodCaw";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("kodCaw", kodCaw);
        return (KodCawangan) q.uniqueResult();
    }

    public int findMaxPetakByIdHakmilik1(String idHakmilik) {
        // int count = 0;
        //String query = "SELECT max(b.noPetak+0) FROM etanah.model.Hakmilik b where b.idHakmilikInduk=:idHakmilik and b.noBangunan=:jenis";
        String query = "SELECT max(b.noPetak+0) FROM etanah.model.Hakmilik b where b.idHakmilikInduk=:idHakmilik ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idHakmilik", idHakmilik);
//    q.setString("jenis", jenis);
        //count = q.list().size();
        return (Integer) q.uniqueResult();
    }

    public int findMaxPetakByIdHakmilik(String idHakmilik, String jenis) {
        // int count = 0;
        String query = "SELECT max(b.noPetak+0) FROM etanah.model.Hakmilik b where b.idHakmilikInduk=:idHakmilik and b.noBangunan=:jenis";
//        String query = "SELECT max(b.noPetak+0) FROM etanah.model.Hakmilik b where b.idHakmilikInduk=:idHakmilik ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        q.setString("jenis", jenis);
        //count = q.list().size();
        return (Integer) q.uniqueResult();
    }

    public int CountPetak(String idBangunan) {
        int count = 0;
        String query = "from etanah.model.BangunanPetakAksesori n where n.bangunan.idBangunan = :idBangunan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idBangunan", idBangunan);
        count = q.list().size();
        return count;

    }

//    public int CountBangunan(String idPermohonan) {
//        int count = 0;
//        String query = "from etanah.model.PermohonanBangunan n where n.permohonan.idPermohonan = :idPermohonan";
//        Session session = sessionProvider.get();
//        Query q = session.createQuery(query);
//        q.setString("idPermohonan", idPermohonan);
//        count = q.list().size();
//        return count;
//
//    }
    public int CountBangunan(String idPermohonan) {
        int count = 0;
        String query = "from etanah.model.PermohonanBangunan n where n.permohonan.idPermohonan = :idPermohonan and n.nama IS NOT NULL";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        count = q.list().size();
        return count;

    }

    public int CountPetakAksr(String idBangunan) {
        int count = 0;
        String query = "from etanah.model.BangunanPetakAksesori n where n.bangunan.idBangunan = :idBangunan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idBangunan", idBangunan);
        count = q.list().size();
        return count;
    }

    //added by murali 17-05-2012
    public List<BangunanTingkat> countTingkatPetakAksri(long idBangunan) {
        String query = "from etanah.model.BangunanTingkat n where n.bangunan.idBangunan = :idBangunan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idBangunan", idBangunan);
        return q.list();
    }

    public int findMaxPetakByIdHakmilikInduk(String idHakmilik) {
        String query = "SELECT max(b.noPetak+0) FROM etanah.model.Hakmilik b where b.idHakmilikInduk=:idHakmilik ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        return (Integer) q.uniqueResult();
    }

    public void generateHakmilikStrata(InfoAudit ia, Permohonan p, Pengguna pengguna) {
        Hakmilik hk = new Hakmilik();
        Hakmilik idStrata = new Hakmilik();
        int maxPetak = 0;
        if (p.getKodUrusan().getKod().equals("PHPC")) {
            idStrata = hakmilikDAO.findById(p.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik());
            maxPetak = findMaxPetakByIdHakmilikInduk(p.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilikInduk());
            System.out.println("-----maxPetak-----:" + maxPetak);
            hk = getMaxPetakNo(p.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilikInduk());
            System.out.println("-----Max idStrata-----:" + hk.getIdHakmilik());
        } else {
            idStrata = hakmilikDAO.findById(p.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik());
            maxPetak = findMaxPetakByIdHakmilikInduk(p.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilikInduk());
            System.out.println("-----maxPetak-----:" + maxPetak);
            hk = getMaxPetakNo(p.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilikInduk());
            System.out.println("-----Max idStrata-----:" + hk.getIdHakmilik());
        }
        String str1 = "";
        String str2 = "";
        if (idStrata.getIdHakmilik().contains("GRN") || idStrata.getIdHakmilik().contains("HSD")) {
            //str1 = hk.getIdHakmilik().substring(0, 23);
            //str2 = hk.getIdHakmilik().substring(23, hk.getIdHakmilik().length());
            str1 = idStrata.getIdHakmilik().substring(0, 23);
            str2 = String.valueOf(maxPetak);
        } else if (idStrata.getIdHakmilik().contains("GM") || idStrata.getIdHakmilik().contains("PN") || idStrata.getIdHakmilik().contains("PM")) {
            //str1 = hk.getIdHakmilik().substring(0, 22);
            //str2 = hk.getIdHakmilik().substring(22, hk.getIdHakmilik().length());
            str1 = idStrata.getIdHakmilik().substring(0, 22);
            str2 = String.valueOf(maxPetak);
        }
        System.out.println("-----str1-----:" + str1);
        System.out.println("-----str2-----:" + str2);
        int val = Integer.parseInt(str2);
        System.out.println("Converted int value:" + val);
        int a = 0;
        if (p.getKodUrusan().getKod().equals("PHPC")) {
            if (p.getBilanganPermohonan() != null) {
                a = p.getBilanganPermohonan();
            }
        } else if (p.getKodUrusan().getKod().equals("PHPP")) {
            a = 1;
        }
        System.out.println("-----a-----:" + a);
        for (int i = 1; i <= a; i++) {
            val = val + 1;
            System.out.println("-----val-----:" + val);
            NumberFormat formatterBangunan = new DecimalFormat("00000");
            String hakmilikBaruStr = str1 + formatterBangunan.format(val);
            //saving hakmilik baru in hakmilik table
            Hakmilik hakmilikBaru = new Hakmilik();
            hakmilikBaru.setIdHakmilik(hakmilikBaruStr);
            hakmilikBaru.setNoFail(idStrata.getNoFail());
            hakmilikBaru.setCawangan(idStrata.getCawangan());
            hakmilikBaru.setDaerah(idStrata.getDaerah());
            hakmilikBaru.setBandarPekanMukim(idStrata.getBandarPekanMukim());
            hakmilikBaru.setSeksyen(idStrata.getSeksyen());
            hakmilikBaru.setLokasi(idStrata.getLokasi());
            hakmilikBaru.setKodHakmilik(idStrata.getKodHakmilik());
            hakmilikBaru.setLot(idStrata.getLot());
            hakmilikBaru.setNoLot(idStrata.getNoLot());
            hakmilikBaru.setKategoriTanah(idStrata.getKategoriTanah());
            hakmilikBaru.setKegunaanTanah(idStrata.getKegunaanTanah());
            hakmilikBaru.setTarikhDaftar(new java.util.Date());
            KodStatusHakmilik ksh = new KodStatusHakmilik();
            ksh.setKod("T");
            hakmilikBaru.setKodStatusHakmilik(ksh);
            hakmilikBaru.setLotBumiputera(idStrata.getLotBumiputera());
            hakmilikBaru.setMilikPersekutuan(idStrata.getMilikPersekutuan());
            hakmilikBaru.setKodUnitLuas(idStrata.getKodUnitLuas());
            if (p.getKodUrusan().getKod().equals("PHPP")) {
                BigDecimal totalluas = BigDecimal.ZERO;
                List<HakmilikPermohonan> hkp = p.getSenaraiHakmilik();
                for (HakmilikPermohonan hakmilikPermohonan : hkp) {
                    totalluas = totalluas.add(hakmilikPermohonan.getHakmilik().getLuas());
                }
                hakmilikBaru.setLuas(totalluas);
            } else {
                BigDecimal lu = p.getSenaraiHakmilik().get(0).getHakmilik().getLuas().divide(new BigDecimal("2"));
                hakmilikBaru.setLuas(lu);
            }
            String noHakmilik = hakmilikBaruStr.substring(hakmilikBaruStr.length() - 19, (hakmilikBaruStr.length() - 19) + 8);
            hakmilikBaru.setNoHakmilik(noHakmilik);
            hakmilikBaru.setNoPelan("");
            hakmilikBaru.setNoPu(idStrata.getNoPu());
            hakmilikBaru.setNoLitho(idStrata.getNoLitho());
            hakmilikBaru.setSekatanKepentingan(idStrata.getSekatanKepentingan());
            hakmilikBaru.setSyaratNyata(idStrata.getSyaratNyata());
            hakmilikBaru.setPbt(idStrata.getPbt());
            hakmilikBaru.setCukai(BigDecimal.ZERO);
            hakmilikBaru.setPbtKawasan(idStrata.getPbtKawasan());
            hakmilikBaru.setPbtNoWarta(idStrata.getPbtNoWarta());
            hakmilikBaru.setPbtTarikhWarta(idStrata.getPbtTarikhWarta());
            hakmilikBaru.setGsaKawasan(idStrata.getGsaKawasan());
            hakmilikBaru.setGsaNoWarta(idStrata.getGsaNoWarta());
            hakmilikBaru.setGsaTarikhWarta(idStrata.getGsaTarikhWarta());
            hakmilikBaru.setTarikhDaftarAsal(idStrata.getTarikhDaftarAsal());
            hakmilikBaru.setNoVersiDhde(idStrata.getNoVersiDhde());
            hakmilikBaru.setNoVersiDhke(idStrata.getNoVersiDhke());
            BadanPengurusan bdn = findBdn(p.getIdPermohonan());
            if (bdn != null) {
                hakmilikBaru.setBadanPengurusan(bdn);
            }
            hakmilikBaru.setNoSkim(idStrata.getNoSkim());
            hakmilikBaru.setTarikhLuput(idStrata.getTarikhLuput());
            hakmilikBaru.setPegangan(idStrata.getPegangan());
            hakmilikBaru.setTempohPegangan(idStrata.getTempohPegangan());
            hakmilikBaru.setNoBangunan(idStrata.getNoBangunan());
            hakmilikBaru.setKodKegunaanBangunan(idStrata.getKodKegunaanBangunan());
            hakmilikBaru.setKodKategoriBangunan(idStrata.getKodKategoriBangunan());
            hakmilikBaru.setNoTingkat(idStrata.getNoTingkat());
            hakmilikBaru.setNoPetak(String.valueOf(val));
            if (p.getKodUrusan().getKod().equals("PHPP")) {
                BigDecimal totalunit = BigDecimal.ZERO;
                List<HakmilikPermohonan> hkp = p.getSenaraiHakmilik();
                for (HakmilikPermohonan hakmilikPermohonan : hkp) {
                    totalunit = totalunit.add(hakmilikPermohonan.getHakmilik().getUnitSyer());
                }
                hakmilikBaru.setUnitSyer(totalunit);
            } else {
                BigDecimal unitSyor = p.getSenaraiHakmilik().get(0).getHakmilik().getUnitSyer().divide(new BigDecimal("2"));
                hakmilikBaru.setUnitSyer(unitSyor);
            }
            hakmilikBaru.setJumlahUnitSyer(idStrata.getJumlahUnitSyer());
            hakmilikBaru.setIdHakmilikInduk(idStrata.getIdHakmilikInduk());
            hakmilikBaru.setInfoAudit(ia);
            simpanhakmilik(hakmilikBaru);

            /*INSERT INTO MOHON HAKMILIK*/
            HakmilikPermohonan mohonHakmilikBaru = new HakmilikPermohonan();
            mohonHakmilikBaru.setHakmilik(hakmilikBaru);
            mohonHakmilikBaru.setPermohonan(p);
            mohonHakmilikBaru.setInfoAudit(ia);
            saveHakmilikPermohonan(mohonHakmilikBaru);

            /*INSERT INTO HAKMILIK SEBELUM*/
            if (p.getKodUrusan().getKod().equals("PHPC")) {
                SejarahHakmilik hMasterTitle1 = sejarahHakmilikDAO.findById(p.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik());
                HakmilikSebelum hakmilikSebelumBaru = new HakmilikSebelum();
                hakmilikSebelumBaru.setHakmilik(hakmilikBaru);
                hakmilikSebelumBaru.setHakmilikSebelum(hMasterTitle1.getIdHakmilik());
                hakmilikSebelumBaru.setInfoAudit(ia);
                hakmiliksebelumlist.add(hakmilikSebelumBaru);
            } else if (p.getKodUrusan().getKod().equals("PHPP")) {
                List<Hakmilik> senaraihak = new ArrayList<Hakmilik>();
                List<HakmilikPermohonan> hk1 = p.getSenaraiHakmilik();
                for (HakmilikPermohonan hakmilikPermohonan : hk1) {
                    SejarahHakmilik hMasterTitle1 = sejarahHakmilikDAO.findById(hakmilikPermohonan.getHakmilik().getIdHakmilik());
                    HakmilikSebelum hakmilikSebelumBaru = new HakmilikSebelum();
                    hakmilikSebelumBaru.setHakmilik(hakmilikBaru);
                    hakmilikSebelumBaru.setHakmilikSebelum(hMasterTitle1.getIdHakmilik());
                    hakmilikSebelumBaru.setInfoAudit(ia);
                    hakmiliksebelumlist.add(hakmilikSebelumBaru);
                }
            }

            /*INSERT INTO HAKMILIK PIHAK*/
            List<HakmilikPihakBerkepentingan> senaraiHakmilikPihak = hpkService.findHakmilikAllPihakActiveByHakmilik(p.getSenaraiHakmilik().get(0).getHakmilik());
            for (HakmilikPihakBerkepentingan hpk : senaraiHakmilikPihak) {
                if (hpk == null || hpk.getAktif() == 'T') {
                    continue;
                } else {
                    HakmilikPihakBerkepentingan hpb = new HakmilikPihakBerkepentingan();
                    hpb.setHakmilik(hakmilikBaru);
                    hpb.setCawangan(hakmilikBaru.getCawangan());
                    hpb.setPihakCawangan(hpk.getPihakCawangan());
                    hpb.setJenis(hpk.getJenis());
                    if (p.getBilanganPermohonan() == 1) {
                        hpb.setSyerPembilang(1);
                        for (i = 1; i <= senaraiHakmilikPihak.size(); i++) {
                            hpb.setSyerPenyebut(i);
                        }
                    } else {
                        hpb.setSyerPembilang(1);
                        hpb.setSyerPenyebut(1);
                    }
                    hpb.setPerserahan(hpk.getPerserahan());
                    hpb.setPerserahanKaveat(hpk.getPerserahanKaveat());
                    hpb.setKaveatAktif(hpk.getKaveatAktif());
                    hpb.setAktif(hpk.getAktif());
                    hpb.setPihak(hpk.getPihak());
                    if (hpk.getPihak() != null) {
                        if (hpk.getPihak().getNama() != null) {
                            hpb.setNama(hpk.getPihak().getNama());
                        }
                        if (hpk.getPihak().getAlamat1() != null) {
                            hpb.setAlamat1(hpk.getPihak().getAlamat1());
                        }
                        if (hpk.getPihak().getAlamat2() != null) {
                            hpb.setAlamat2(hpk.getPihak().getAlamat2());
                        }
                        if (hpk.getPihak().getAlamat3() != null) {
                            hpb.setAlamat3(hpk.getPihak().getAlamat3());
                        }
                        if (hpk.getPihak().getAlamat4() != null) {
                            hpb.setAlamat4(hpk.getPihak().getAlamat4());
                        }
                        if (hpk.getPihak().getNoPengenalan() != null) {
                            hpb.setNoPengenalan(hpk.getPihak().getNoPengenalan());
                        }
                        if (hpk.getPihak().getPoskod() != null) {
                            hpb.setPoskod(hpk.getPihak().getPoskod());
                        }
                        if (hpk.getPihak().getNegeri() != null) {
                            hpb.setNegeri(hpk.getPihak().getNegeri());
                        }
                    }
                    hpb.setInfoAudit(ia);
                    lhp.add(hpb);
                }
            }

            if (!lhp.isEmpty()) {
                regService.simpanHakmilikPihak(lhp);
            }
            if (!hakmiliksebelumlist.isEmpty()) {
                regService.simpanHakmilikSebelum(hakmiliksebelumlist);
            }
        }
    }

    public List<HakmilikUrusan> findByID(String idHakmilik) {
        String query = "SELECT b FROM etanah.model.HakmilikUrusan b where b.hakmilik.idHakmilik = :idHakmilik and b.aktif = 'Y'";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        return q.list();

    }

    public LaporanTanah findByIDMohon(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.LaporanTanah b where b.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (LaporanTanah) q.uniqueResult();
    }

    public List<HakmilikPetakAksesori> findIDforPetak(String idHakmilik) {
        String query = "SELECT hpa FROM etanah.model.HakmilikPetakAksesori hpa where hpa.hakmilik.idHakmilik = :idHakmilik order by hpa.nama, hpa.noPelan ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        return q.list();
    }

    public List<HakmilikPetakAksesori> findIDforPetakAsc(String idHakmilik) {
        String query = "SELECT hpa FROM etanah.model.HakmilikPetakAksesori hpa where hpa.hakmilik.idHakmilik = :idHakmilik order by hpa.nama+0";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        return q.list();
    }

    public List<PermohonanKertasKandungan> findByIdLapor(long idKertas, int bil) {
        String query = "SELECT b FROM etanah.model.PermohonanKertasKandungan b where b.kertas.idKertas = :idKertas and b.bil = :bil order by b.subtajuk";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idKertas", idKertas);
        q.setInteger("bil", bil);
        return q.list();
    }

    public List<PermohonanLaporanKandungan> findByIdLaporKand(long idLapor, int bil) {
        String query = "SELECT b FROM etanah.model.PermohonanLaporanKandungan b where b.laporanTanah.idLaporan = :idLapor and b.bil = :bil order by b.subtajuk";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idLapor", idLapor);
        q.setInteger("bil", bil);
        return q.list();
    }

    public PermohonanRujukanLuar findPermohonan(String idPermohonan, String kod) {
        String query = "SELECT b FROM etanah.model.PermohonanRujukanLuar b where b.permohonan.idPermohonan = :idPermohonan and b.kodRujukan= :kod ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("kod", kod);
        return (PermohonanRujukanLuar) q.uniqueResult();

    }

    public PermohonanRujukanLuar findNPermohonan(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PermohonanRujukanLuar b where b.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (PermohonanRujukanLuar) q.uniqueResult();

    }
    //Ida update 31072013

    public PermohonanRujukanLuar findPermohonan2(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PermohonanRujukanLuar b where b.permohonan.idPermohonan = :idPermohonan ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (PermohonanRujukanLuar) q.uniqueResult();

    }

    public List<KodBandarPekanMukim> getSenaraiKodBPMByCawangan(String kodBandarPekanMukim) {
        String query = "SELECT b FROM etanah.model.KodBandarPekanMukim b where b.daerah.kod = :kodBandarPekanMukim";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("kodBandarPekanMukim", kodBandarPekanMukim);
        return q.list();

    }

    public List<PermohonanBangunan> findByKodKatBngn(String idPermohonan, String katg_bngn) {
        String query = "SELECT b FROM etanah.model.PermohonanBangunan b where b.katg_bngn = :katg_bngn and b.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("katg_bngn", katg_bngn);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<KodUrusan> getSenaraiUrusan() {
        String query = "SELECT h FROM etanah.model.KodUrusan h where h.kod in( 'P14A', 'P22A', 'P22B', 'P40A','P20A') order by h.nama";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        return q.list();
    }

    public List<KodCaraPermohonan> getSenaraiKodCaraPermohonan() {
        return kodCaraPermohonanDAO.findAll();
    }

    public List<KodBandarPekanMukim> getSenaraiKodBandarPekanMukim() {
        return kodBandarPekanMukimDAO.findAll();
    }

    @Transactional
    public void simpanMohonJUBL(PermohonanJUBL mohonJUBL) {
        mohonJUBLDAO.saveOrUpdate(mohonJUBL);
    }

    public KodCawangan getkodCawangan(String kod) {
        KodCawangan kodC = new KodCawangan() {
        };
        kodC = kodCawanganDAO.findById(kod);
        return kodC;
    }

    public InfoAudit getInfo(Pengguna peng) {
        InfoAudit ia = new InfoAudit() {
        };
        ia.setDimasukOleh(peng);
        ia.setTarikhMasuk(new Date());
        return ia;
    }

    public PermohonanStrataDAO getPermohonanStrataDAO() {
        return permohonanStrataDAO;
    }

    public void setPermohonanStrataDAO(PermohonanStrataDAO permohonanStrataDAO) {
        this.permohonanStrataDAO = permohonanStrataDAO;
    }

    public List<PermohonanStrata> listMohonStrata(String[] criteriaNames, Object[] criteriaValues) {
        List<PermohonanStrata> list;
        list = permohonanStrataDAO.findByEqualCriterias(criteriaNames, criteriaValues, null);
        return list;
    }

    @Transactional
    public void saveorupdate(ImejLaporan imejLaporan) {
        imejLaporanDAO.saveOrUpdate(imejLaporan);
    }

    // Added by Murali
    @Transactional
    public void simpanTransaksi(Transaksi transaksi) {
        transaksiDAO.saveOrUpdate(transaksi);
    }

    public Permohonan findByIDSblm(String idPermohonan) {
        String query = "SELECT h FROM etanah.model.Permohonan h where h.permohonanSebelum.id = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (Permohonan) q.uniqueResult();
    }

    public HakmilikUrusan findHakmilikUrusan(String idPermohonan) {
        String query = "SELECT h FROM etanah.model.HakmilikUrusan h where h.idPerserahan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (HakmilikUrusan) q.uniqueResult();
    }

    public HakmilikUrusan findHakmilikUrusanByidHakmilikAndIdMohon(String idPermohonan, String idHakmilik) {
        String query = "SELECT h FROM etanah.model.HakmilikUrusan h where h.idPerserahan = :idPermohonan and h.hakmilik.idHakmilik = :idHakmilik";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("idHakmilik", idHakmilik);
        return (HakmilikUrusan) q.uniqueResult();
    }

    public List<PermohonanTuntutanKos> findMohonTuntutKos(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PermohonanTuntutanKos b where b.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public PermohonanKertas findKertas(String idPermohonan) {
        String query = "SELECT h FROM etanah.model.PermohonanKertas h where h.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (PermohonanKertas) q.uniqueResult();

    }

    public PermohonanKertas findMaxIDKertas() {
        Session s = sessionProvider.get();
        String query = "SELECT m FROM etanah.model.PermohonanKertas m WHERE m.idKertas = (SELECT MAX(m3.idKertas) from etanah.model.PermohonanKertas m3)";
        Query q = s.createQuery(query);
        return (PermohonanKertas) q.uniqueResult();
    }

    public LaporanTanah findLaporanTanahByIdPermohonan(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.LaporanTanah b where b.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (LaporanTanah) q.uniqueResult();
    }

    public List<PermohonanLaporanKandungan> findLaporanTanahByLaporanTanah(long idLaporan) {
        String query = "SELECT b FROM etanah.model.PermohonanLaporanKandungan b where b.laporanTanah.idLaporan = :idLaporan ORDER BY subtajuk";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idLaporan", idLaporan);
        return q.list();
    }

    public List<UrusanDokumen> findUrusanDokbyID(String kod) {
        String query = "SELECT b FROM etanah.model.UrusanDokumen b where b.kodDokumen.kod = :kod ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("kod", kod);
        return q.list();
    }

    public UrusanDokumen findUrusanDokbyKOD(String kod, String kodUrusan) {
        String query = "SELECT b FROM etanah.model.UrusanDokumen b where b.kodDokumen.kod = :kod and b.kodUrusan.kod = :kodUrusan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("kod", kod);
        q.setString("kodUrusan", kodUrusan);
        return (UrusanDokumen) q.uniqueResult();
    }

    public List<Dokumen> findUrusanDokWajib(String idPermohonan, String kodUrusan) {
        String query = "SELECT d FROM etanah.model.Permohonan m, etanah.model.KandunganFolder fd, etanah.model.Dokumen d "
                + "where m.idPermohonan = :idPermohonan and m.folderDokumen.folderId = fd.folder.folderId "
                + "and fd.dokumen.idDokumen = d.idDokumen and d.kodDokumen.kod in "
                + "(select ud.kodDokumen.kod from etanah.model.UrusanDokumen ud where ud.kodUrusan.kod =:kodUrusan "
                + "and ud.wajib = 'Y' and ud.aktif = 'Y')";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("kodUrusan", kodUrusan);
        return q.list();
    }

    public List<PermohonanKertasKandungan> findMohonKertasByMohonKertas(long idKertas) {
        String query = "SELECT b FROM etanah.model.PermohonanKertasKandungan b where b.kertas.idKertas = :idKertas ORDER BY subtajuk";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idKertas", idKertas);
        return q.list();
    }

    @Transactional
    public void SimpanKand(LaporanTanah laporanTanah) {
        laporanTanahDAO.saveOrUpdate(laporanTanah);
    }

    @Transactional
    public PermohonanLaporanKandungan SimpanLaporanKand(PermohonanLaporanKandungan laporanKandungan) {
        return laporanKandunganDAO.saveOrUpdate(laporanKandungan);
    }

    @Transactional
    public void deleteLaporanKandungan(PermohonanLaporanKandungan laporanKandungan) {
        laporanKandunganDAO.delete(laporanKandungan);
    }

    @Transactional
    public void deleteKertasKandungan(PermohonanKertasKandungan kertasKandungan) {
        permohonanKertasKandunganDAO.delete(kertasKandungan);
    }

    public List<PermohonanPermitButir> getSumOfPermitButirByIdHakmilikPermohonan(long id) {
        String query = "SELECT b FROM etanah.model.PermohonanPermitButir b where b.hakmilikPermohonan.id = :id ORDER BY idMpermitBtr";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("id", id);
        return q.list();
    }

    public LaporanTanah getLaporanTanahByIdPermohonan(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.LaporanTanah b where b.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (LaporanTanah) q.uniqueResult();
    }

    @Transactional
    public LaporanTanah simpanLaporan(LaporanTanah laporanTanah) {
        return laporanTanahDAO.saveOrUpdate(laporanTanah);
    }

    @Transactional
    public void SimpanPermohonanPermitButir(PermohonanPermitButir permohonanPermitButir) {
        permohonanPermitButirDAO.saveOrUpdate(permohonanPermitButir);
    }

    public List<HakmilikPermohonan> getMaklumatTan(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.HakmilikPermohonan b where b.permohonan.idPermohonan = :idPermohonan";
        return sessionProvider.get().createQuery(query).setString("idPermohonan", idPermohonan).list();
    }

    public List<PermohonanKertasKandungan> findSenaraiKertas(int bil) {
        String query = "SELECT b FROM etanah.model.PermohonanKertasKandungan b where b.bil = :bil ORDER BY subtajuk";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
//        q.setLong("idKertas", idKertas);
        q.setInteger("bil", bil);
        return q.list();
    }

    public List<PermohonanTuntutanBayar> findMohonTuntutBayar(long idKos) {
        String query = "SELECT b FROM etanah.model.PermohonanTuntutanBayar b where b.permohonanTuntutanKos.idKos = :idKos";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idKos", idKos);
        return q.list();
    }

    @Transactional
    public void savePermohonan(Permohonan permohonan) {
        permohonanDAO.saveOrUpdate(permohonan);
    }

    public AduanLokasi getAduanLokasi(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.AduanLokasi b where b.permohonan.idPermohonan = :idPermohonan";
        return (AduanLokasi) sessionProvider.get().createQuery(query).setString("idPermohonan", idPermohonan).uniqueResult();
    }

    public List<Hakmilik> getNoPetakLatest(String idHakmilikInduk, String noBangunan, String noTingkat) {
        String query = "SELECT b FROM etanah.model.Hakmilik b where b.idHakmilikInduk = :idHakmilikInduk and b.noBangunan = :noBangunan and b.noTingkat = :noTingkat ORDER BY noPetak DESC";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idHakmilikInduk", idHakmilikInduk);
        q.setString("noBangunan", noBangunan);
        q.setString("noTingkat", noTingkat);
        return q.list();
    }

    public List<HakmilikPermohonan> findIdPBBSByIdHakmilik(String idHakmilik) {
        String query = "SELECT b FROM etanah.model.HakmilikPermohonan b where b.hakmilik.idHakmilik = :idHakmilik";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        return q.list();
    }

    public List<HakmilikAsal> findHakmilikAsal(String idHakmilik) {
        String query = "SELECT b FROM etanah.model.HakmilikAsal b where b.hakmilik.idHakmilik = :idHakmilik";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        return q.list();
    }

    @Transactional
    public PermohonanBangunan saveMohonBangunan(PermohonanBangunan mohonBngn) {
        mohonBngn = permohonanBangunanDAO.saveOrUpdate(mohonBngn);
        return mohonBngn;
    }

    @Transactional
    public BangunanTingkat saveBangunanTingkat(BangunanTingkat bangunanTingkat) {
        bangunanTingkat = bangunanTingkatDAO.saveOrUpdate(bangunanTingkat);
        return bangunanTingkat;
    }

    @Transactional
    public BangunanPetak saveBangunanPetak(BangunanPetak bangunanPetak) {
        bangunanPetak = bangunanPetakDAO.saveOrUpdate(bangunanPetak);
        return bangunanPetak;
    }

    public List<BangunanPetakAksesori> getListPetakAks(String idHakmilik) {
        String query = "select bpa from etanah.model.HakmilikPermohonan mh,"
                + " etanah.model.PermohonanBangunan mb,"
                + " etanah.model.BangunanTingkat bt,"
                + " etanah.model.BangunanPetak bp,"
                + " etanah.model.BangunanPetakAksesori bpa,"
                + " etanah.model.Hakmilik h"
                + " where"
                + " mb.idBangunan = bt.bangunan.idBangunan and"
                + " bt.idTingkat = bp.tingkat.idTingkat and"
                + " mh.permohonan.idPermohonan = mb.permohonan.idPermohonan and"
                + " h.idHakmilikInduk = mh.hakmilik.idHakmilik and"
                + " mb.nama = h.noBangunan and"
                + " bt.tingkat = h.noTingkat and"
                + " bp.nama = h.noPetak and"
                + " mb.idBangunan = bpa.bangunan.idBangunan and"
                + " bt.idTingkat = bpa.tingkat.idTingkat and"
                + " bp.idPetak =bpa.petak.idPetak and"
                + " mh.hakmilik.idHakmilik = :idHakmilik";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idHakmilik", idHakmilik);

        return q.list();
    }

    public BadanPengurusan findbyId(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.strata.BadanPengurusan b where b.permohonan.idPermohonan = :idPermohonan";
        return (BadanPengurusan) sessionProvider.get().createQuery(query).setString("idPermohonan", idPermohonan).uniqueResult();
    }

    public HakmilikPermohonan findMohonHakmilik(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.HakmilikPermohonan b where b.permohonan.idPermohonan = :idPermohonan";
        return (HakmilikPermohonan) (sessionProvider.get().createQuery(query).setString("idPermohonan", idPermohonan)).list().get(0);
    }

    //added by murali @PAT Melaka 07/09/2012
    public HakmilikPermohonan findMohonHakmilikByAsc(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.HakmilikPermohonan b where b.permohonan.idPermohonan = :idPermohonan order by b.hakmilik.idHakmilik asc";
        return (HakmilikPermohonan) (sessionProvider.get().createQuery(query).setString("idPermohonan", idPermohonan)).list().get(0);
    }

    public HakmilikPihakBerkepentingan findPihakByIdHakmilik(String idHakmilik) {
        String query = "SELECT b FROM etanah.model.HakmilikPihakBerkepentingan b where b.hakmilik.idHakmilik = :idHakmilik AND b.jenis.kod = :kodPB "
                + "and b.aktif = 'Y'";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        q.setString("kodPB", "PM");
        return (HakmilikPihakBerkepentingan) q.list().get(0);
    }

    public HakmilikPihakBerkepentingan findPihakByIdHakmilikNKod(String idHakmilik, String kodPB) {
        String query = "SELECT b FROM etanah.model.HakmilikPihakBerkepentingan b where b.hakmilik.idHakmilik = :idHakmilik AND b.jenis.kod = :kodPB "
                + "and b.aktif = 'Y'";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        q.setString("kodPB", kodPB);
        if (q.list().size() > 0) {
            return (HakmilikPihakBerkepentingan) q.list().get(0);
        } else {
            return null;
        }
    }

    public PermohonanJUBL findMohonJUBL(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PermohonanJUBL b where b.permohonan.idPermohonan = :idPermohonan";
        return (PermohonanJUBL) sessionProvider.get().createQuery(query).setString("idPermohonan", idPermohonan).uniqueResult();
    }

    @Transactional
    public JUBL savaJuruUkur(JUBL juruUkur) {
        return jublDAO.saveOrUpdate(juruUkur);
    }

    @Transactional
    public void deletePermit(PermohonanPermitButir mohonPermitB) {
        permohonanPermitButirDAO.delete(mohonPermitB);
    }

    public List<PermohonanPermitButir> findPermitButir(long id) {
        String query = "SELECT b FROM etanah.model.PermohonanPermitButir b where b.hakmilikPermohonan.id = :id";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("id", id);
        return q.list();
    }

    public PermohonanPermitButir findPermitButirByBlok(long idMH, String blok, String tingkat, String petak, String jenisStruk) {
        String query = "SELECT b FROM etanah.model.PermohonanPermitButir b where b.hakmilikPermohonan.id = :idMH AND b.noBlok = :blok"
                + " AND b.noTingkat = :tingkat AND b.noPetak = :petak AND b.jenisStrukTambah = :jenisStruk";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idMH", idMH);
        q.setString("blok", blok);
        q.setString("tingkat", tingkat);
        q.setString("petak", petak);
        q.setString("jenisStruk", jenisStruk);
        return (PermohonanPermitButir) q.uniqueResult();
    }

    @Transactional
    public void savePermit(Permit permit) {
        permitDAO.saveOrUpdate(permit);

    }

    public KodKadarBayaran findkodKadarBayar(String kod) {
        String query = "SELECT b FROM etanah.model.KodKadarBayaran b where b.kodUrusan.kod = :kod";
        return (KodKadarBayaran) sessionProvider.get().createQuery(query).setString("kod", kod).uniqueResult();
    }

    public PermohonanTuntutanButiran findTuntutButir(long idKos, long idTuntut) {
        String query = "SELECT b FROM etanah.model.PermohonanTuntutanButiran b where b.permohonanTuntutanKos.idKos = :idKos and b.permohonanTuntutan = :idTuntut";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idTuntut", idTuntut);
        q.setLong("idKos", idKos);
        return (PermohonanTuntutanButiran) q.uniqueResult();
    }

    public FasaPermohonan findMohonFasa(String idPermohonan, String stageId) {
        String query = "SELECT b FROM etanah.model.FasaPermohonan b where b.permohonan.idPermohonan = :idPermohonan and b.idAliran = :stageId"
                + " order by b.idFasa desc ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("stageId", stageId);

        if (q.list().size() > 1) {
            return (FasaPermohonan) q.list().get(0);
        } else {
            return (FasaPermohonan) q.uniqueResult();
        }
    }

    public FasaPermohonan findMohonFasa2(String idfasa) {
        String query = "SELECT b FROM etanah.model.FasaPermohonan b where b.permohonan.idPermohonan = :idPermohonan and b.idFasa = :idfasa"
                + " order by b.idFasa desc ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
//        q.setString("idPermohonan", idPermohonan);
        q.setString("idfasa", idfasa);

        if (q.list().size() > 1) {
            return (FasaPermohonan) q.list().get(0);
        } else {
            return (FasaPermohonan) q.uniqueResult();
        }
    }

    public String stageId(String taskId, Pengguna pengguna) {
        BPelService service = new BPelService();
        String stageId = null;
        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = service.getTaskFromBPel(taskId, pengguna);
            stageId = t.getSystemAttributes().getStage();
        }

        return stageId;
    }

    public KodKadarBayaran getKodByKat(String kod, String kategori) {
        String query = "SELECT b FROM etanah.model.KodKadarBayaran b where b.kodUrusan.kod = :kod and b.kategori = :kategori";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("kod", kod);
        q.setString("kategori", kategori);
        return (KodKadarBayaran) q.uniqueResult();
    }

    public String getFormat(String format) {
        if (format != null) {
            if (format.equals("application/pdf")) {
                format = ".pdf";
            }
            if (format.equals("image/jpeg")) {
                format = ".jpeg";
            }
            if (format.equals("text/plain")) {
                format = ".txt";
            }
            if (format.equals("text/xml")) {
                format = ".xml";
            }
            if (format.equals("application/octet-stream")) {
                format = ".jsp";
            }
            if (format.equals("application/msword")) {
                format = ".doc";
            }
            if (format.equals("image/tiff")) {
                format = ".tif";
            }
            if (format.equals("image/pjpeg")) {
                format = ".jpeg";
            }
        } else {
            format = null;
        }
        return format;
    }

    public Dokumen findDokByXML(String idPermohonan, String kodDokumen, String format) {
        String query = " select d from Permohonan p, "
                + "KandunganFolder k, Dokumen d "
                + "where p.idPermohonan = :idPermohonan and p.folderDokumen.id = k.folder.id "
                + "and k.dokumen.id = d.idDokumen and d.kodDokumen.id = :kodDokumen and d.format= :format";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("kodDokumen", kodDokumen);
        q.setString("format", format);
        return (Dokumen) q.uniqueResult();

    }

    public List<PermohonanSkim> getListSkim(String idHakmilik, String kod) {
        String query = "SELECT b FROM etanah.model.PermohonanSkim b where b.hakmilikPermohonan.hakmilik.idHakmilik = :idHakmilik"
                + " and b.hakmilikPermohonan.permohonan.keputusan.kod ='Y'"
                + " and b.hakmilikPermohonan.permohonan.kodUrusan.kod =:kod";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        q.setString("kod", kod);
        return q.list();
    }

    @Transactional
    public Pihak savePihak(Pihak pihak) {
        pihakDAO.saveOrUpdate(pihak);
        return pihak;
    }

    @Transactional
    public Pemohon savePemohon(Pemohon pemohon) {
        return pemohonDAO.saveOrUpdate(pemohon);
    }

    @Transactional
    public PermohonanSkim saveSkim(PermohonanSkim mohonSkim) {
        return mohonSkimDAO.saveOrUpdate(mohonSkim);

    }

    public List<HakmilikPermohonan> findPermohonanIdHak(String idHakmilik, String kod) {
        String query = "SELECT b FROM etanah.model.HakmilikPermohonan b where b.hakmilik.idHakmilik = :idHakmilik and b.permohonan.keputusan.kod = :kod";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        q.setString("kod", kod);
        return q.list();
    }

    public Permohonan getMhnSblmByKod(Permohonan permohonan, KodUrusan kod) {
        String query = "SELECT b FROM etanah.model.Permohonan b where b.permohonanSebelum.idPermohonan = :permohonan and b.kodUrusan.kod = :kod";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("permohonan", permohonan.getIdPermohonan());
        q.setString("kod", kod.getKod());
        return (Permohonan) q.uniqueResult();
    }

    public PermohonanTuntutanKos findMohontuntutkos(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PermohonanTuntutanKos b where b.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (PermohonanTuntutanKos) q.uniqueResult();
    }

    public List<Hakmilik> findHakmilibyParent(String idHakmilik) {
        String query = "SELECT b FROM etanah.model.Hakmilik b where b.idHakmilikInduk = :idHakmilik and b.kodStatusHakmilik.kod = 'D'"
                + " ORDER BY LPAD(lower(b.noPetak),10,0) ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        return q.list();
    }

    public List<Hakmilik> findHakmilik(String idHakmilik) {
        String query = "SELECT h FROM etanah.model.Hakmilik h where h.idHakmilik = :idHakmilik and h.idHakmilikInduk is not null"
                + " and h.kodStatusHakmilik.kod = 'D' ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        return q.list();
    }

    public List<Hakmilik> findHakmilibyParentProv(String idHakmilik) {
        String query = "SELECT b FROM etanah.model.Hakmilik b where b.idHakmilikInduk = :idHakmilik and b.kodKategoriBangunan.kod = 'P' and b.kodStatusHakmilik.kod = 'D'";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        return q.list();
    }

    public List<Hakmilik> findHakmilibyNotInProv(String idHakmilik) {
        String query = "SELECT b FROM etanah.model.Hakmilik b where b.idHakmilikInduk = :idHakmilik and b.kodKategoriBangunan.kod not in ('P') and b.kodStatusHakmilik.kod = 'D'"
                + " ORDER BY LPAD(lower(b.noPetak),10,0)";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        return q.list();
    }

    public List<String> findNoBangunanProv(String idHakmilik) {
        String query = "SELECT distinct b.noBangunan FROM etanah.model.Hakmilik b where b.idHakmilikInduk = :idHakmilik and b.kodKategoriBangunan.kod = 'P' "
                + "ORDER BY b.noBangunan ASC";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        return q.list();
    }

    public List<FileBean> findNoPelan(String idHakmilik) {
        String query = "SELECT distinct h.noPelan FROM etanah.model.Hakmilik h where h.idHakmilikInduk is not null "
                + "and h.idHakmilik like :idHakmilik and h.noPelan is not null order by h.noPelan asc "
                + "union SELECT distinct hpa.noPelan FROM etanah.model.HakmilikPetakAksesori hpa where hpa.hakmilik.idHakmilik like :idHakmilik "
                + "and hpa.noPelan is not null order by hpa.noPelan asc";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idHakmilik", idHakmilik + "%");
        return q.list();
    }

    public List<String> findNoPelanHmString(String idHakmilik) {
        String query = "SELECT distinct h.noPelan FROM etanah.model.Hakmilik h where h.idHakmilikInduk is not null "
                + "and h.idHakmilik like :idHakmilik and h.noPelan is not null order by h.noPelan asc";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idHakmilik", idHakmilik + "%");
        return q.list();
    }

    public List<String> findNoPelanMohonBngn(String idMohon) {
        String query = "SELECT distinct bp.pabPetak FROM etanah.model.PermohonanBangunan mb, BangunanTingkat bt, BangunanPetak bp "
                + " where mb.permohonan.idPermohonan = :idMohon "
                + " and mb.idBangunan = bt.bangunan.idBangunan and bt.idTingkat = bp.tingkat.idTingkat"
                + " and bp.pabPetak is not null  order by bp.pabPetak asc";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idMohon", idMohon);
        return q.list();
    }

    public List<String> findNoPelanBngnPetakAksr(String idMohon) {
        String query = "SELECT distinct bpa.pabAksesori FROM etanah.model.PermohonanBangunan mb, BangunanPetakAksesori bpa "
                + " where mb.permohonan.idPermohonan = :idMohon "
                + " and mb.idBangunan = bpa.bangunan.idBangunan "
                + " and bpa.pabAksesori is not null  order by bpa.pabAksesori asc";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idMohon", idMohon);
        return q.list();
    }

    public List<String> findNoPelanAksrString(String idHakmilik) {
        String query = "SELECT distinct hpa.noPelan FROM etanah.model.HakmilikPetakAksesori hpa where hpa.hakmilik.idHakmilik like :idHakmilik "
                + "and hpa.noPelan is not null order by hpa.noPelan asc";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idHakmilik", idHakmilik + "%");
        return q.list();
    }

//distinct replace(b.id_Hakmilik,substr(b.id_Hakmilik,-8,8),'')
    public List<Hakmilik> findHm(String idHakmilik, String noBangunan) {
        String query = "SELECT b FROM etanah.model.Hakmilik b where b.idHakmilikInduk = :idHakmilik and b.noBangunan = :noBangunan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        q.setString("noBangunan", noBangunan);
        return q.list();
    }

    public List<Hakmilik> findHmDaftar(String idHakmilik, String noBangunan) {
        String query = "SELECT b FROM etanah.model.Hakmilik b where b.idHakmilikInduk = :idHakmilik and b.noBangunan = :noBangunan and b.kodStatusHakmilik.kod = 'D'";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        q.setString("noBangunan", noBangunan);
        return q.list();
    }

    public List<Hakmilik> findHakmilik2(String daerah, String bandarPekanMukim, String kodHakmilik, String noHakmilik) {
        String query = "SELECT b FROM etanah.model.Hakmilik b where b.daerah.kod = :daerah and b.bandarPekanMukim.kod = :bandarPekanMukim and b.kodHakmilik.kod = :kodHakmilik and b.noHakmilik LIKE :noHakmilik and b.idHakmilikInduk is null";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("daerah", daerah);
        q.setString("bandarPekanMukim", bandarPekanMukim);
        q.setString("kodHakmilik", kodHakmilik);
        q.setString("noHakmilik", "%" + noHakmilik);
        return q.list();
    }

    public List<Hakmilik> findHakmilik(String daerah, String bandarPekanMukim, String kodHakmilik, String noHakmilik) {
        String query = "SELECT b FROM etanah.model.Hakmilik b where b.daerah.kod = :daerah and b.bandarPekanMukim.kod = :bandarPekanMukim and b.kodHakmilik.kod = :kodHakmilik and b.noHakmilik LIKE :noHakmilik";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("daerah", daerah);
        q.setString("bandarPekanMukim", bandarPekanMukim);
        q.setString("kodHakmilik", kodHakmilik);
        q.setString("noHakmilik", "%" + noHakmilik);
        return q.list();
    }

    public List<Pihak> getPihakPM(String idHakmilik) {
        String query = "SELECT p FROM etanah.model.Hakmilik h,etanah.model.HakmilikPihakBerkepentingan hpb, "
                + "etanah.model.Pihak p "
                + "where p.idPihak = hpb.pihak.idPihak "
                + "and hpb.jenis.kod = 'PM' "
                + "and hpb.hakmilik.idHakmilik = h.idHakmilik "
                + "and hpb.aktif = 'Y'"
                + "and h.idHakmilik = :idHakmilik";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        return q.list();
    }

    @Transactional
    public HakmilikPermohonan saveHakmilikPermohonan(HakmilikPermohonan mh) {
        return hakmilikPermohonanDAO.saveOrUpdate(mh);
    }

    @Transactional
    public PermohonanSyarikatLelong savePermohonanSyarikatLelong(PermohonanSyarikatLelong psl) {
        return permohonanSyarikatLelongDAO.saveOrUpdate(psl);
    }
//     public List<HakmilikPermohonan> getidMohonPBBS(String idHakmilik) {
//        String query = "SELECT b FROM etanah.model.HakmilikPermohonan b where b.idHakmilikInduk = :idHakmilikInduk and b.noBangunan = :noBangunan and b.noTingkat = :noTingkat ORDER BY noPetak DESC";
//         Session session = sessionProvider.get();
//        Query q = session.createQuery(query);
//        q.setString("idHakmilikInduk", idHakmilikInduk);
//        q.setString("noBangunan", noBangunan);
//        q.setString("noTingkat", noTingkat);
//        return q.list();
//    }

//added by murali for Deleting image in laporan tanah jsp
    public Dokumen findDokumen(Long id) {
        String query = "Select p FROM etanah.model.Dokumen p WHERE p.idDokumen = :id";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("id", id);
        //q.setString("idH", idH);
        return (Dokumen) q.uniqueResult();
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
    public void deletePelaksana(PelaksanaWaran pelaksanaWaran) {
        pelaksanaWaranDAO.delete(pelaksanaWaran);
    }

    @Transactional
    public void deleteMohonStrataImejByIdDokumen(Long dokumenId) {
        String query = "DELETE FROM etanah.model.ImejLaporanStrata p WHERE  p.dokumen.idDokumen = :dokumenId";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("dokumenId", dokumenId);
        int rowCount = q.executeUpdate();
    }

    @Transactional
    public void deleteAll2(Dokumen d) {
        dokumenDAO.delete(d);
    }

    public DokumenDAO getDokumenDAO() {
        return dokumenDAO;
    }

    public void setDokumenDAO(DokumenDAO dokumenDAO) {
        this.dokumenDAO = dokumenDAO;
    }

    public List<PermohonanSyarikatLelong> findSytLelong(String idPermohonan) {
        String query = "Select p FROM etanah.model.PermohonanSyarikatLelong p WHERE p.idPermohonan.idPermohonan = :idPermohonan";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<FasaPermohonan> getSenaraiFasaPermohonan(String idPermohonan) {
        String query = "Select p FROM etanah.model.FasaPermohonan p WHERE p.permohonan.idPermohonan = :idPermohonan";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    @Transactional
    public FasaPermohonan saveFasaMohon(FasaPermohonan fasaPermohonan) {
        return fasaPermohonanDAO.saveOrUpdate(fasaPermohonan);
    }

    public List<KodHartaBersama> findSenaraiKodHartaBersama() {
        String query = "Select p FROM etanah.model.KodHartaBersama p WHERE p.kod != 1 and p.kod != 11";
        Query q = sessionProvider.get().createQuery(query);
        return q.list();
    }

    public List<KodHartaBersama> findSenaraiKodHartaBersama2() {
        String query = "Select p FROM etanah.model.KodHartaBersama p WHERE p.kod != 1 and p.kod != 12";
        Query q = sessionProvider.get().createQuery(query);
        return q.list();
    }

    public List<SytJuruLelong> findSenaraiSytJuruLelongNama(String nama) {
        String query = "Select p FROM etanah.model.SytJuruLelong p WHERE p.nama like :nama";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("nama", "%" + nama + "%");
        return q.list();
    }

    public List<SytJuruLelong> findSenaraiSytJuruLelongId(String idSyt) {
        String query = "Select p FROM etanah.model.SytJuruLelong p WHERE p.noPengenalan like :noPengenalan";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("noPengenalan", "%" + idSyt + "%");
        return q.list();
    }

    public Date formatSDF(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        return sdf.parse(date);
    }

    public String formatSDF(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(date);
    }

    public StorRampasan findStor(String idPermohonan) {
        String query = "Select p FROM etanah.model.StorRampasan p WHERE p.permohonan.idPermohonan = :idPermohonan";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (StorRampasan) q.uniqueResult();
    }

    public List<PelaksanaWaran> findListPelaksana(String idPermohonan) {
        String query = "Select p FROM etanah.model.PelaksanaWaran p WHERE p.permohonan.idPermohonan = :idPermohonan";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    @Transactional
    public SytJuruLelong saveSytJurulelong(SytJuruLelong sytJurulelong) {
        return sytJurulelongDAO.saveOrUpdate(sytJurulelong);
    }

    @Transactional
    public StorRampasan saveStorRampasan(StorRampasan storRampasan) {
        return storRampasanDAO.saveOrUpdate(storRampasan);
    }

    @Transactional
    public Waran saveWaran(Waran waran) {
        return waranDAO.saveOrUpdate(waran);
    }

    @Transactional
    public WaranPihak saveWaranPihak(WaranPihak waranPihak) {
        return waranPihakDAO.saveOrUpdate(waranPihak);
//        return waranPihak;
    }
    // added by zadirul for BangunanKosRendahActionBean

    public KodKegunaanPetak findGunaPetakByNama(String nama) {
        String query = "Select p FROM etanah.model.KodKegunaanPetak p WHERE p.nama = :nama";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("nama", nama);
        return (KodKegunaanPetak) q.uniqueResult();
    }
    // added by murali for BangunanKosRendahActionBean

    public KodHartaBersama findKodHartaBersamaByNama(String nama) {
        //String query = "Select p FROM etanah.model.KodHartaBersama p WHERE p.nama = :nama";
        //Query q = sessionProvider.get().createQuery(query);
        //query.setString("nama", nama);
        Session session = sessionProvider.get();
        Criteria criteria = session.createCriteria(KodHartaBersama.class).add(Restrictions.eq("nama", nama).ignoreCase());
        return (KodHartaBersama) criteria.uniqueResult();
    }

//added by murali for kertasPertimbanganMMK
    public Pemohon findPemohonPihak(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.Pemohon b where b.permohonan.idPermohonan = :idPermohonan";
        return (Pemohon) sessionProvider.get().createQuery(query).setString("idPermohonan", idPermohonan).uniqueResult();
    }

    public List<PermohonanBangunan> getSenaraiMohonBangunan(String idPermohonan) {
        String query = "SELECT n FROM etanah.model.PermohonanBangunan n where n.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<BangunanTingkat> findByIDBangunan(long idBangunan) {
        String query = "SELECT b FROM etanah.model.BangunanTingkat b where b.bangunan.idBangunan = :idBangunan ORDER BY TO_NUMBER(b.tingkat) ASC";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setParameter("idBangunan", idBangunan);
        return q.list();

    }

    public BangunanTingkat findByIDBangunan2(long idBangunan) {
        String query = "SELECT b FROM etanah.model.BangunanTingkat b where b.bangunan.idBangunan = :idBangunan ORDER BY LPAD(lower(b.nama),10,0)";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setParameter("idBangunan", idBangunan);
        return (BangunanTingkat) q.uniqueResult();

    }
    
    public List<BangunanTingkat> findByIDBangunan3(long idBangunan) {
        String query = "SELECT b FROM etanah.model.BangunanTingkat b where b.bangunan.idBangunan = :idBangunan ORDER BY LPAD(lower(b.nama),10,0)";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setParameter("idBangunan", idBangunan);
        return q.list();

    }

    public List<BangunanTingkat> findByIDBangunanTingkat(long idBangunan) {
        String query = "SELECT b FROM etanah.model.BangunanTingkat b, etanah.model.PermohonanBangunan n where b.bangunan.idBangunan = n.idBangunan and n.kodKategoriBangunan.kod NOT LIKE '%L%' and b.bangunan.idBangunan = :idBangunan order by b.tingkat asc";
        //SELECT a.*  from bngn_tgkt a, mohon_bngn b where a.id_bngn = b.id_bngn and a.id_bngn =:id_bngn and b.katg_bngn!='L' order by a.TGKT asc
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setParameter("idBangunan", idBangunan);
        return q.list();
    }

    public List<BangunanTingkat> findByIDBangunanTingkatL(long idBangunan) {
        String query = "SELECT b FROM etanah.model.BangunanTingkat b, etanah.model.PermohonanBangunan n where b.bangunan.idBangunan = n.idBangunan and n.kodKategoriBangunan.kod LIKE '%L%' and b.bangunan.idBangunan = :idBangunan order by b.tingkat asc";
        //SELECT a.*  from bngn_tgkt a, mohon_bngn b where a.id_bngn = b.id_bngn and a.id_bngn =:id_bngn and b.katg_bngn!='L' order by a.TGKT asc
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setParameter("idBangunan", idBangunan);
        return q.list();
    }

    public PermohonanBangunan findBangunanNama(long idBangunan) {
        String query = "SELECT n FROM etanah.model.PermohonanBangunan n where n.idBangunan =:idBangunan";
//        String query = "SELECT b FROM etanah.model.BangunanTingkat b, etanah.model.PermohonanBangunan n where b.bangunan.idBangunan = n.idBangunan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setParameter("idBangunan", idBangunan);
        return (PermohonanBangunan) q.uniqueResult();
    }

    public PermohonanBangunan findBangunanByKatgBngn(String idPermohonan, String katgbngn) {
        String query = "SELECT n FROM etanah.model.PermohonanBangunan n where n.permohonan.idPermohonan = :idPermohonan and n.kodKategoriBangunan.kod =:katgbngn";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setParameter("idPermohonan", idPermohonan);
        q.setParameter("katgbngn", katgbngn);
        return (PermohonanBangunan) q.uniqueResult();
    }

    public List<BangunanPetak> getSenaraiPetak(long idTingkat) {
        String query = "SELECT b FROM etanah.model.BangunanPetak b where b.tingkat.idTingkat = :idTingkat order by b.nama asc";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setParameter("idTingkat", idTingkat);
        return q.list();

    }

    public BangunanPetak getSenaraiPetak2(long idPetak, String nama) {
        String query = "SELECT b FROM etanah.model.BangunanPetak b where b.idPetak = :idPetak AND b.nama = :nama ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setParameter("idPetak", idPetak);
        q.setParameter("nama", nama);
        return (BangunanPetak) q.uniqueResult();

    }

    public PermohonanKertas findKertasByKod(String idPermohonan, String kod) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select mk from etanah.model.PermohonanKertas mk where mk.permohonan.idPermohonan = :idPermohonan and mk.kodDokumen.kod = :kod");
        q.setString("kod", kod);
        q.setString("idPermohonan", idPermohonan);
        return (PermohonanKertas) q.uniqueResult();
    }

    public PermohonanKertasKandungan findkandunganByIdKandungan(Long idKandungan) {
        String query = "Select p FROM etanah.model.PermohonanKertasKandungan p WHERE p.idKandungan = :idKandungan";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idKandungan", idKandungan);
        return (PermohonanKertasKandungan) q.uniqueResult();
    }

    public PelaksanaWaran findPelaksanaWaran(Long idPelaksanaWaran) {
        String query = "Select p FROM etanah.model.PelaksanaWaran p WHERE p.idPelaksanaWaran = :idPelaksanaWaran";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idPelaksanaWaran", idPelaksanaWaran);
        return (PelaksanaWaran) q.uniqueResult();
    }

    @Transactional
    public PermohonanPihak saveMohonPihak(PermohonanPihak mohonPihak) {
        return permohonanPihakDAO.saveOrUpdate(mohonPihak);
    }

    @Transactional
    public Dokumen saveDokumen(Dokumen dokumen) {
        return dokumenDAO.saveOrUpdate(dokumen);
    }

    @Transactional
    public PermohonanRujukanLuar saveMohonRujukLuar(PermohonanRujukanLuar mohonRujukLuar) {
        return permohonanRujukanLuarDAO.saveOrUpdate(mohonRujukLuar);
    }

    @Transactional
    public void saveOrUpdateDokumen(Dokumen dokumen) {
        dokumenDAO.saveOrUpdate(dokumen);
    }

    @Transactional
    public void saveOrUpdatekandunganFolder(KandunganFolder kandunganFolder) {
        kandunganFolderDAO.saveOrUpdate(kandunganFolder);
    }

    @Transactional
    public void deleteWaranItem(PermohonanWaranItem waranItem) {
        permohonanWaranItemDAO.delete(waranItem);
    }

    public Permohonan findPermohonanByHakmilik(String idHakmilik, String kpsn) {
        String query = "Select p FROM etanah.model.Permohonan p, etanah.model.HakmilikPermohonan mh, etanah.model.KodUrusan ku WHERE mh.permohonan.idPermohonan = p.idPermohonan"
                //+ " and p.keputusan.kod is null"
                + " and p.kodUrusan.kod = ku.kod"
                + " and ku.jabatan.kod = 20"
                + " and ku.kod in(select kuu.kod from etanah.model.KodUrusan kuu where kuu.jabatan.kod =20 and kuu.kod = 'PBBS' or kuu.kod = 'PBBD' or kuu.kod = 'PSBS' or kuu.kod = 'PBBSS' or kuu.kod = 'PHPC' or kuu.kod = 'PHPP' or kuu.kod = 'PBS' or kuu.kod = 'PBTS' or kuu.kod = 'PBBL')"
                + " and mh.hakmilik.idHakmilik = :idHakmilik";
        if (kpsn != null) {
            query += " and p.keputusan.kod = :kpsn";
        }
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        if (kpsn != null) {
            q.setString("kpsn", kpsn);
        }

        if (q.list().isEmpty()) {
            return (Permohonan) q.uniqueResult();
        } else {
            return (Permohonan) q.list().get(0);
        }

    }

    public Permohonan findPermohonanByHakmilik2(String idHakmilik) {
        String query = "Select p FROM etanah.model.Permohonan p, etanah.model.HakmilikPermohonan mh, etanah.model.KodUrusan ku WHERE mh.permohonan.idPermohonan = p.idPermohonan"
                //+ " and p.keputusan.kod is null"
                + " and p.kodUrusan.kod = ku.kod"
                + " and ku.kod in('HTB')"
                + " and mh.hakmilik.idHakmilik = :idHakmilik";

        Query q = sessionProvider.get().createQuery(query);
        q.setString("idHakmilik", idHakmilik);

        if (q.list().isEmpty()) {
            return (Permohonan) q.uniqueResult();
        } else {
            return (Permohonan) q.list().get(0);
        }

    }

    public FasaPermohonan findFasaPermohonanByIdAliran(String idPermohonan, String idAliran) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select lt from etanah.model.FasaPermohonan lt where lt.permohonan.idPermohonan = :idPermohonan and lt.idAliran = :idAliran"
                + " order by lt.idFasa desc ");
        q.setString("idPermohonan", idPermohonan);
        q.setString("idAliran", idAliran);
//        return (FasaPermohonan) q.uniqueResult();
        if (q.list().size() > 1) {
            return (FasaPermohonan) q.list().get(0);
        } else {
            return (FasaPermohonan) q.uniqueResult();
        }
    }

    //-------ADDED BY Zadirul--------------
    public Pemohon findPenyerahPemohon(String idPermohonan) {
        String query = "Select pmhn FROM etanah.model.Permohonan p, etanah.model.Pihak phk, etanah.model.Pemohon pmhn WHERE p.idPermohonan = :idPermohonan "
                + "and pmhn.permohonan.idPermohonan = p.idPermohonan and phk.idPihak = pmhn.pihak.idPihak and p.penyerahNama = phk.nama";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (Pemohon) q.uniqueResult();
        /*if (q.list().size() > 1) {
            return (Pemohon) q.list().get(0);
        } else {
            return (Pemohon) q.uniqueResult();
        }*/
    }

    //-------ADDED BY Zadirul--------------
    @Transactional
    public void deletePihakByIdPihak(Long idPihak) {
        String query = "DELETE FROM etanah.model.Pihak p WHERE p.idPihak = :idPihak";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idPihak", idPihak);
        q.executeUpdate();
    }

    //-------ADDED BY Zadirul--------------
    @Transactional
    public void deletePemohonByIDMohon(String idPermohonan) {
        String query = "DELETE FROM etanah.model.Pemohon p WHERE p.permohonan.idPermohonan = :idPermohonan";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.executeUpdate();
    }

    //-------ADDED BY ida--------------
    @Transactional
    public void deleteMohonDepositIDMohon(String idPermohonan) {
        String query = "DELETE FROM etanah.model.PermohonanPihakPendeposit p WHERE p.permohonan.idPermohonan = :idPermohonan";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.executeUpdate();
    }

    @Transactional
    public PermohonanTandatanganDokumen saveDokumenTT(PermohonanTandatanganDokumen ptd) {
        return permohonanTandatanganDokumenDAO.saveOrUpdate(ptd);
    }

    //added by murali 06-07-2011
    public AduanStrata findAduanStrataIdMohon(String idPermohonan) {
        String query = "Select p FROM etanah.model.AduanStrata p WHERE p.permohonan.idPermohonan = :idPermohonan";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (AduanStrata) q.uniqueResult();
    }

    public Pihak findPemohonAlamatSuratEqualAlamat(String kodPengenalan, String noPengenalan) {
        Session s = sessionProvider.get();
        String query = "Select phk FROM etanah.model.Pihak phk,etanah.model.PihakAlamatTamb pat WHERE phk.jenisPengenalan.kod = :kodPengenalan and phk.noPengenalan = :noPengenalan and phk.idPihak = pat.pihak.idPihak "
                + "and ( (pat.alamatKetiga1 = phk.alamat1 or (phk.alamat1 is null and pat.alamatKetiga1 is null)) "
                + "and (pat.alamatKetiga2 = phk.alamat2 or (phk.alamat2 is null and pat.alamatKetiga2 is null)) "
                + "and (pat.alamatKetiga3 = phk.alamat3 or (phk.alamat3 is null and pat.alamatKetiga3 is null)) "
                + "and (pat.alamatKetiga4 = phk.alamat4 or (phk.alamat4 is null and pat.alamatKetiga4 is null)) "
                + "and (pat.alamatKetigaPoskod = phk.poskod or (phk.poskod is null and pat.alamatKetigaPoskod is null)) "
                + "and (pat.alamatKetigaNegeri = phk.negeri or (phk.negeri is null and pat.alamatKetigaNegeri is null)))";
        Query q = s.createQuery(query);
        q.setString("kodPengenalan", kodPengenalan);
        q.setString("noPengenalan", noPengenalan);
        if (q.list().isEmpty()) {
            return (Pihak) q.uniqueResult();
        } else {
            return (Pihak) q.list().get(0);
        }
    }

    public List<ImejLaporan> findImejlaporan(long idLaporan) {
        String query = "Select p FROM etanah.model.ImejLaporan p WHERE p.laporanTanah.idLaporan = :idLaporan ";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idLaporan", idLaporan);
        return q.list();
    }

    @Transactional
    public void deleteImejLaporanByIdDokumen(Long dokumenId) {
        String query = "DELETE FROM etanah.model.ImejLaporan p WHERE  p.dokumen.idDokumen = :dokumenId";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("dokumenId", dokumenId);
        int rowCount = q.executeUpdate();
    }

//    public PermohonanTandatanganDokumen findMohonDokTT(String idPermohonan, String idPengguna, String kod) {
//        System.out.println("----query executing-----");
//        String query = "Select p FROM etanah.model.PermohonanTandatanganDokumen p WHERE p.permohonan.idPermohonan = :idPermohonan ";
//        System.out.println("----executed PermohonanTandatanganDokumen -----");
//
//         System.out.println("----executing kod-----");
//        if (StringUtils.isNotBlank(kod)) {
//            query = "and p.kodDokumen.kod = :kod ";
//        }
//         System.out.println("----executed kod-----");
//
//         System.out.println("----executing idpengguna-----");
//        if (StringUtils.isNotBlank(idPengguna)) {
//            query = "and p.diTandatangan = :idPengguna";
//        }
//         System.out.println("----executed idpengguna-----");
//        Query q = sessionProvider.get().createQuery(query);
//        q.setString("idPermohonan", idPermohonan);
//        if (StringUtils.isNotBlank(idPengguna)) {
//            q.setString("idPengguna", idPengguna);
//        }
//        if (StringUtils.isNotBlank(kod)) {
//            q.setString("kod", kod);
//        }
//        return (PermohonanTandatanganDokumen) q.uniqueResult();
//    }
    public PermohonanTandatanganDokumen findMohonDokTT(String idPermohonan, String idPengguna, String kod) {
        System.out.println("starate service : " + idPermohonan + "  -- id pengguna : " + idPengguna + " -- kod :" + kod);
        String query = "Select p FROM etanah.model.PermohonanTandatanganDokumen p WHERE p.permohonan.idPermohonan = :idPermohonan ";
        if (StringUtils.isNotBlank(kod)) {
            query += "and p.kodDokumen.kod = :kod ";
        }
        if (StringUtils.isNotBlank(idPengguna)) {
            query += "and p.diTandatangan = :idPengguna";
        }
        System.out.println("query nya ialah : " + query);
        Query q = sessionProvider.get().createQuery(query);

        q.setString("idPermohonan", idPermohonan);
        if (StringUtils.isNotBlank(idPengguna)) {
            q.setString("idPengguna", idPengguna);
        }
        if (StringUtils.isNotBlank(kod)) {
            q.setString("kod", kod);
        }
        return (PermohonanTandatanganDokumen) q.uniqueResult();
    }

    public FasaPermohonan checkLulusTolakRBHS(String idMohon, String idAliran) {
        String query = "Select p FROM etanah.model.FasaPermohonan p WHERE p.permohonan.idPermohonan = :idMohon AND p.idAliran = :idAliran "
                + " order by p.idFasa desc";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idMohon", idMohon);
        q.setString("idAliran", idAliran);
        return (FasaPermohonan) q.uniqueResult();
    }

    public List<FasaPermohonan> checkIDAliran(String idMohon) {
        String query = "Select p FROM etanah.model.FasaPermohonan p WHERE p.permohonan.idPermohonan = :idMohon order by p.idFasa desc ";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idMohon", idMohon);
        //return (FasaPermohonan) q.uniqueResult();
        return q.list();
    }

    public LanjutanTempoh findMohonLanjutTempoh(Long idN) {
        String query = "Select p FROM etanah.model.LanjutanTempoh p WHERE p.hakmilikPermohonan.id = :idN";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idN", idN);
        return (LanjutanTempoh) q.uniqueResult();
    }

    @Transactional
    public LanjutanTempoh saveLanjutTempoh(LanjutanTempoh lanjutTempoh) {
        return lanjutanTempohDAO.saveOrUpdate(lanjutTempoh);
    }

    public Permohonan findPermohonanSblm(String idPermohonan) {
        String query = "Select p FROM etanah.model.Permohonan p WHERE p.idPermohonan = '" + idPermohonan + "'";
        Query q = sessionProvider.get().createQuery(query);
//        q.setLong("idN", idN);
        return (Permohonan) q.uniqueResult();
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

    //added By Murali 05/07/2012
    public List<PermohonanTuntutanKos> findMohonTuntutKosExcludeBayaran(String idPermohonan, String kodTuntut) {
        String query = "SELECT b FROM etanah.model.PermohonanTuntutanKos b where b.permohonan.idPermohonan = :idPermohonan "
                + "and b.kodTuntut.kod <> :kodTuntut";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("kodTuntut", kodTuntut);
        return q.list();
    }

    public List<PermohonanTuntutanKos> findMohonTuntutKosDepositlist(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PermohonanTuntutanKos b where b.permohonan.idPermohonan = :idPermohonan "
                + "and b.kodTuntut.kod IN ( 'DIS4C','DISWC','DISKD','DISCJ' ) ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    //added By Murali 05/07/2012
    public PermohonanTuntutanKos findMohonTuntutKosDeposit(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PermohonanTuntutanKos b where b.permohonan.idPermohonan = :idPermohonan "
                + "and b.kodTuntut.kod = 'J01' ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);

        return (PermohonanTuntutanKos) q.uniqueResult();
    }

    //added By Murali 05/07/2012
    public List<PermohonanTuntutanKos> findMohonTuntutKosIncludeBayaran(String idPermohonan, String kodTuntut) {
        String query = "SELECT b FROM etanah.model.PermohonanTuntutanKos b where b.permohonan.idPermohonan = :idPermohonan "
                + "and b.kodTuntut.kod = :kodTuntut";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("kodTuntut", kodTuntut);
        return q.list();
    }

    public PermohonanTandatanganDokumen findMohonDokTT(String idPermohonan, String kodDokumen) {
        String query = "Select p FROM etanah.model.PermohonanTandatanganDokumen p WHERE p.permohonan.idPermohonan = :idPermohonan "
                + "and p.kodDokumen.kod = :kodDokumen";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("kodDokumen", kodDokumen);
        return (PermohonanTandatanganDokumen) q.uniqueResult();
    }

    // added by murali 07-07-2011
    public List<UrusanDokumen> findUrusanDokumenBykodUrusan(String kodurusan) {
        String query = "SELECT b FROM etanah.model.UrusanDokumen b where b.kodUrusan.kod = :kodurusan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("kodurusan", kodurusan);
        return q.list();
    }

    public List<PermohonanSemakDokumen> findSenaraiPermohonanSemakDokumen(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PermohonanSemakDokumen b where b.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<HakmilikPihakBerkepentingan> findSizePemilik(String idHakmilik, String kod) {
        String query = "SELECT b FROM etanah.model.HakmilikPihakBerkepentingan b where b.hakmilik.idHakmilik = :idHakmilik AND b.jenis.kod = :kod AND b.aktif = 'Y'";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        q.setString("kod", kod);
        return q.list();
    }

    @Transactional
    public void simpanPermohonanSemakDokumen(PermohonanSemakDokumen permohonanSemakDokumen) {
        permohonanSemakDokumenDAO.saveOrUpdate(permohonanSemakDokumen);
    }

    // added by Shah
    public PegawaiPenyiasat findPegawaiSiasatByIdPermohonan(String idPermohonan) {
        String query = "SELECT p FROM etanah.model.PegawaiPenyiasat p where p.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (PegawaiPenyiasat) q.uniqueResult();

    }
    // added by Shah

    @Transactional
    public void simpanPegawaiPenyiasat(PegawaiPenyiasat pegawaiPenyiasat) {
        pegawaiPenyiasatDAO.saveOrUpdate(pegawaiPenyiasat);
    }

    //added by zadirul
    @Transactional
    public void simpanSiasatanAduanImej(SiasatanAduanImej siasatanAduanImej) {
        siasatanAduanImejDAO.saveOrUpdate(siasatanAduanImej);

    }
    //added by zadirul

    @Transactional
    public void deleteSiasatanAduanImejByIdDokumen(Long dokumenId) {
        String query = "DELETE FROM etanah.model.SiasatanAduanImej p WHERE p.Dokumen.idDokumen = :dokumenId";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("dokumenId", dokumenId);
        int rowCount = q.executeUpdate();
    }
    //added by zadirul

    public List<SiasatanAduanImej> findSiasatanImejByIdPermohonan(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.SiasatanAduanImej b where b.permohonan.idPermohonan = :idPermohonan ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();

    }

    public List<HakmilikPermohonan> findListHakmilikByIdMohon(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.HakmilikPermohonan b where b.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }
    //added by murali 12-07-2011

    public List<KandunganFolder> findkandunganFolderByfolderId(Long folderId) {
        String query = "SELECT b FROM etanah.model.KandunganFolder b where b.folder.folderId = :folderId";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("folderId", folderId);
        // q.setString("kodDoc", kodDoc);
        return q.list();
    }

    public PermohonanKertasKandungan findByIdLapor(long idKertas, int bil, String subtajuk) {
        String query = "SELECT b FROM etanah.model.PermohonanKertasKandungan b where b.kertas.idKertas = :idKertas and b.bil = :bil and b.kertas.subtajuk :subtajuk order by b.subtajuk";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idKertas", idKertas);
        q.setInteger("bil", bil);
        q.setString("subTajuk", subtajuk);
        return (PermohonanKertasKandungan) q.list();
    }

    public PermohonanKertasKandungan findByIdkertasBil(long idKertas, int bil) {
        String query = "SELECT b FROM etanah.model.PermohonanKertasKandungan b where b.kertas.idKertas = :idKertas and b.bil = :bil ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idKertas", idKertas);
        q.setInteger("bil", bil);
        return (PermohonanKertasKandungan) q.uniqueResult();
    }

    //added By murali 14-07-2011
    public SiasatanPerihalBangunan findSiasatanPerihalBangunanByIdPermohonan(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.SiasatanPerihalBangunan b where b.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (SiasatanPerihalBangunan) q.uniqueResult();
    }

    //added By murali 18-07-2011
    public List<SiasatanPerihalKemudahan> findSiasatanPerihalKemudahanListByIdPermohonan(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.SiasatanPerihalKemudahan b where b.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }
    //added By murali 18-07-2011

    public List<KodHartaBersama> findHartaBersamaByNama() {
        String query = "SELECT b FROM etanah.model.KodHartaBersama b WHERE b.kod != :kod";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("kod", "1");
        return q.list();
    }

    public KodHartaBersama getLainlainOnly() {
        String query = "SELECT b FROM etanah.model.KodHartaBersama b WHERE b.kod = :kod";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("kod", "13");
        return (KodHartaBersama) q.uniqueResult();
    }

    public List<KodHartaBersama> findHartaBersamaByNama2() {
        String query = "SELECT b FROM etanah.model.KodHartaBersama b WHERE b.kod != :kod AND b.kod != :kod2";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("kod", "1");
        q.setString("kod2", "12");
        return q.list();
    }

    //added By murali 18-07-2011
    public List<SiasatanPerihalKemudahan> findSiasatanKemudahanListByIdPermohonan(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.SiasatanPerihalKemudahan b where b.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();

    }

    //added By murali 18-07-2011
    @Transactional
    public void deleteSiasatanPerihalKemudahan(SiasatanPerihalKemudahan siasatanPerihalKemudahan) {
        siasatanPerihalKemudahanDAO.delete(siasatanPerihalKemudahan);
    }

    @Transactional
    public void simpanAkaun(Akaun akaun) {
        akaunDAO.saveOrUpdate(akaun);
    }

    //added by zadirul
    public List<SiasatanPerihalBangunan> findAduanSiasatBangunanIdMohon(String idPermohonan) {
        String query = "Select p FROM etanah.model.SiasatanPerihalBangunan p WHERE p.permohonan.idPermohonan = :idPermohonan";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    //added by zadirul
    @Transactional
    public void deletePegawaSiasatByIdMohon(String idPermohonan) {
        String query = "DELETE FROM etanah.model.PegawaiPenyiasat p WHERE p.permohonan.idPermohonan = :idPermohonan";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        int rowCount = q.executeUpdate();
    }

    //added by zadirul
    public List<Permohonan> findPermohonanPecahSempadan(String idPermohonan) {
        String query = "SELECT p FROM etanah.model.Permohonan p, etanah.model.HakmilikPermohonan hp "
                + "WHERE p.idPermohonan = hp.permohonan.idPermohonan AND "
                + "p.kodUrusan.kod = 'PPCS' AND hp.hakmilik.idHakmilik IN "
                + "(SELECT a.hakmilik.idHakmilik FROM etanah.model.Permohonan p, etanah.model.AduanStrata a "
                + "WHERE p.idPermohonan = a.permohonan.idPermohonan AND p.idPermohonan = :idPermohonan)";

        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    // added by murali 29-07-2011
    public List<KodDokumen> getKodDokumenNotRequired(ArrayList<String> senaraiKodUrusan) {
        System.out.println("-----senaraiKodUrusan-----" + senaraiKodUrusan);
        String hql = "select kd from KodDokumen kd where kd  in "
                + "(select d from UrusanDokumen ud "
                + "inner join ud.kodDokumen d "
                + "where ud.kodUrusan.id in (:senaraiKodUrusan)) "
                + "order by kd.nama";

        return sessionProvider.get().createQuery(hql).
                setParameterList("senaraiKodUrusan", senaraiKodUrusan).list();
    }

    //added by murali 01-08-2011
    @Transactional
    public void deletePermohonanSemakDok(PermohonanSemakDokumen psd) {
        permohonanSemakDokumenDAO.delete(psd);
    }

    @Transactional
    public void deleteMohonSyrtLelong(PermohonanSyarikatLelong mohonSytLelong) {
        mohonSytLelongDAO.delete(mohonSytLelong);
    }

    public List<Permohonan> findListPermohonan(String idPermohonan) {
        String query = "SELECT p FROM etanah.model.Permohonan p "
                + "WHERE p.permohonanSebelum.idPermohonan = :idPermohonan";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    //-------ADDED BY Zadirul--------------
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

    // Zadirul
    @Transactional
    public void deleteSiasatanPerihalBangunan(SiasatanPerihalBangunan siasatanPerihalBangunan) {
        siasatanPerihalBangunanDAO.delete(siasatanPerihalBangunan);
    }
    //murali 16-08-2011

    public List<KodJenisPembangunan> findkodJenisPembangunanByNama() {
        String query = "SELECT b FROM etanah.model.KodJenisPembangunan b order by b.kod";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        return q.list();
    }
    //added By murali 16-08-2011

    public List<SiasatanPerihalBangunan> findaduanSiasatanBangunanListByIdPermohonan(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.SiasatanPerihalBangunan b where b.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();

    }

    // added By Zadirul to find pelan GIS by id permohonan
    public PelanGIS findPelanByIdPermohonan(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("select u from etanah.model.gis.PelanGIS u where u.pelanGISPK.permohonan.idPermohonan = :idPermohonan");
        q.setString("idPermohonan", idPermohonan);

        List<PelanGIS> lp = q.list();
        PelanGIS p = new PelanGIS();
        if (lp.size() > 0) {
            p = lp.get(0);
        } else {
            p = null;
        }
        return p;

    }
    // added by zadirul

    public List<PelanGIS> findListPelanGISPKByIdPermohonan(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.gis.PelanGIS b where b.pelanGISPK.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<PelanGIS> findListPelanGISPKByNoSkim(String noPelanTif, String stage) {
        String query = "SELECT b FROM etanah.model.gis.PelanGIS b where b.pelanTif = :noPelanTif and b.jenisPelan = :stage";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("noPelanTif", noPelanTif);
        q.setString("stage", stage);
        return q.list();
    }

    // added by ida 23 Jun 2013
    public PermohonanSkim readXMLForMohonSkim(Dokumen d) {
        String docPath = conf.getProperty("document.path");
        //idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        Document doc = null;
        PermohonanSkim mohonSkim = null;
        BadanPengurusan mc;

        File f = new File(docPath + (docPath.endsWith(File.separator) ? "" : File.separator) + d.getNamaFizikal());

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = null;
        try {
            db = dbf.newDocumentBuilder();
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(StrataPtService.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            doc = db.parse(f);
        } catch (SAXException ex) {
            Logger.getLogger(StrataPtService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(StrataPtService.class.getName()).log(Level.SEVERE, null, ex);
        }

        // permohonan = permohonanDAO.findById(idPermohonan);
        NodeList n1 = doc.getElementsByTagName("Scheme");
        NodeList n2 = doc.getElementsByTagName("Block");
        NodeList n3 = doc.getElementsByTagName("Tingkat");
        NodeList n4 = doc.getElementsByTagName("Petak");
        NodeList n5 = doc.getElementsByTagName("Ruang");
        NodeList n6 = doc.getElementsByTagName("Aksesori");
        NodeList n7 = doc.getElementsByTagName("CommonArea");

        String diukur_oleh,
                pengukur_noic,
                ulasan_jupem,
                nama_pemaju,
                nama_perbadanan_pengurusan,
                alamat_perbadanan_pengurusan,
                nama_projek,
                no_ruj_jubl,
                no_ruj_ljt,
                no_fail_ukursemula,
                kodtujuanukur, no_fail_ukur,
                negeri,
                daerah,
                mukim,
                seksyen,
                no_lot,
                skim;

        int bilangan_petak;
        int bilangan_aksesori;
        Date tarikh_terima_sijil_akuan;
        Date tarikh_lulus_permohonan;
        Date tarikh_siap;
        BigDecimal bayaran_ukur;
        BigDecimal bayaran_pelan;

//                BigDecimal jumlah_unit_syer;
        //added
        BigDecimal jumlah_unit_syer = new BigDecimal(0);

        for (int i = 0; i < n1.getLength(); i++) {
            mohonSkim = new PermohonanSkim();
            KodTujuanUkur kodTujuanUkur = new KodTujuanUkur();
            diukur_oleh = (n1.item(i).getAttributes().getNamedItem("diukur_oleh").getNodeValue());
            pengukur_noic = (n1.item(i).getAttributes().getNamedItem("pengukur_noic").getNodeValue());
            ulasan_jupem = (n1.item(i).getAttributes().getNamedItem("ulasan_jupem").getNodeValue());
            nama_pemaju = (n1.item(i).getAttributes().getNamedItem("nama_pemaju").getNodeValue());
            nama_perbadanan_pengurusan = (n1.item(i).getAttributes().getNamedItem("nama_perbadanan_pengurusan").getNodeValue());
            alamat_perbadanan_pengurusan = (n1.item(i).getAttributes().getNamedItem("alamat_perbadanan_pengurusan").getNodeValue());
            nama_projek = (n1.item(i).getAttributes().getNamedItem("nama_projek").getNodeValue());
            no_ruj_jubl = (n1.item(i).getAttributes().getNamedItem("no_ruj_jubl").getNodeValue());
            no_ruj_ljt = (n1.item(i).getAttributes().getNamedItem("no_ruj_ljt").getNodeValue());
            no_fail_ukur = (n1.item(i).getAttributes().getNamedItem("no_fail_ukursemula").getNodeValue());
            no_fail_ukursemula = (n1.item(i).getAttributes().getNamedItem("no_fail_ukur").getNodeValue());
            kodtujuanukur = (n1.item(i).getAttributes().getNamedItem("kodtujuanukur").getNodeValue());
            skim = (n1.item(i).getAttributes().getNamedItem("skim").getNodeValue());
            bilangan_petak = Integer.valueOf(n1.item(i).getAttributes().getNamedItem("bilangan_petak").getNodeValue());
            bilangan_aksesori = Integer.valueOf(n1.item(i).getAttributes().getNamedItem("bilangan_aksesori").getNodeValue());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            if (!StringUtils.isEmpty(n1.item(i).getAttributes().getNamedItem("bayaran_ukur").getNodeValue())) {
                bayaran_ukur = BigDecimal.valueOf(Double.valueOf(n1.item(i).getAttributes().getNamedItem("bayaran_ukur").getNodeValue()));
            }

            if (!StringUtils.isEmpty(n1.item(i).getAttributes().getNamedItem("bayaran_pelan").getNodeValue())) {
                bayaran_pelan = BigDecimal.valueOf(Double.valueOf(n1.item(i).getAttributes().getNamedItem("bayaran_pelan").getNodeValue()));
            }

            if (!StringUtils.isEmpty(n1.item(i).getAttributes().getNamedItem("jumlah_unit_syer").getNodeValue())) {
                jumlah_unit_syer = BigDecimal.valueOf(Double.valueOf(n1.item(i).getAttributes().getNamedItem("jumlah_unit_syer").getNodeValue()));
            }
            if (!n1.item(i).getAttributes().getNamedItem("bayaran_ukur").getNodeValue().equals("")) {
                bayaran_ukur = BigDecimal.valueOf(Double.valueOf(n1.item(i).getAttributes().getNamedItem("bayaran_ukur").getNodeValue()));
            }
            if (!n1.item(i).getAttributes().getNamedItem("bayaran_pelan").getNodeValue().equals("")) {
                bayaran_pelan = BigDecimal.valueOf(Double.valueOf(n1.item(i).getAttributes().getNamedItem("bayaran_pelan").getNodeValue()));
            }
            if (!n1.item(i).getAttributes().getNamedItem("jumlah_unit_syer").getNodeValue().equals("")) {
                jumlah_unit_syer = BigDecimal.valueOf(Double.valueOf(n1.item(i).getAttributes().getNamedItem("jumlah_unit_syer").getNodeValue()));
            }

            // mohonSkim.setCawangan(peng.getKodCawangan());
            // mohonSkim.setInfoAudit(infoAudit);
            mohonSkim.setNamaPemaju(nama_pemaju);
            mohonSkim.setNamaProjek(nama_projek);
            mohonSkim.setDiukur(diukur_oleh);
            mohonSkim.setNoKpPengukur(pengukur_noic);
            mohonSkim.setNoFailUkur(no_ruj_ljt);
            mohonSkim.setNoSkim(getIntegerValue1(skim));
            mohonSkim.setNoRujJubl(no_ruj_jubl);
            mohonSkim.setNoFailUkurSemula(no_fail_ukursemula);
            mohonSkim.setNoFailPt(no_fail_ukur);
            //added 16-05-2012 by murali
            mohonSkim.setJumlahUnitSyer(jumlah_unit_syer.longValue());
            // kodTujuanUkur = kodTujuanUkurDAO.findById(kodtujuanukur);
            mohonSkim.setKodTujuanUkur(kodTujuanUkur);//
            if (!StringUtils.isEmpty(String.valueOf(bilangan_petak))) {
                mohonSkim.setBilPetak(Long.parseLong(String.valueOf(bilangan_petak)));
            }
            if (!StringUtils.isEmpty(String.valueOf(bilangan_aksesori))) {

                mohonSkim.setBilAksr(Long.parseLong(String.valueOf(bilangan_aksesori)));
            }

        }
        return mohonSkim;

    }

    public String getIntegerValue1(String s) {
        String remove = "(S)";
        int i;
        String result = "";
        i = s.indexOf(remove);
        if (i != -1) {
            result = s.substring(0, i);
            result = result + s.substring(i + remove.length());
            s = result;
        }
        return s;
    }

    public List<KandunganFolder> findFolderDokForSenaraiKertasSiasatan(Long folderId, String kodDoc) {
        String query = "SELECT b FROM etanah.model.KandunganFolder b,etanah.model.UrusanDokumen u"
                + "where u.kodDokumen.kod = :kodDoc AND "
                + "u.kodDokumen.kod = b.dokumen.kodDokumen.kod AND"
                + "b.folder.folderId = :folderId";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("folderzId", folderId);
        q.setString("kodDoc", kodDoc);
        return q.list();
    }
    // added by zadirul

    public PegawaiPenyiasat findPegawaiSiasatByJawatan(String idPermohonan) {
        Session s = sessionProvider.get();
        String query = "Select p FROM etanah.model.PegawaiPenyiasat p WHERE p.permohonan.idPermohonan = :idPermohonan";
        Query q = s.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
//        q.setString("kodJawatan", kodJawatan);
        return (PegawaiPenyiasat) q.uniqueResult();
    }

    //murali
    @Transactional
    public void simpanPermohonanHartaBersama(PermohonanHartaBersama permohonanHartaBersama) {
        permohonanHartaBersamaDAO.saveOrUpdate(permohonanHartaBersama);
    }

    //Murali
    @Transactional
    public void deleteStorRampasan(StorRampasan storRampasan) {
        storRampasanDAO.delete(storRampasan);
    }

    //murali 05-12-2011
     public List<Hakmilik> findIdHakmilikByIdHakmilikInduk(String idHakmilik) {
        String query = "SELECT b FROM etanah.model.Hakmilik b where b.idHakmilikInduk = :idHakmilik order by LPAD(lower(b.noPetak),10,0) asc ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        return q.list();
    }
    public List<Hakmilik> findIdHakmilikByIdHakmilikIndukP(String idHakmilik) {
        String query = "SELECT b FROM etanah.model.Hakmilik b where b.kodKategoriBangunan.kod in ('P','PL') and b.idHakmilikInduk = :idHakmilik order by LPAD(lower(b.noPetak),10,0) asc ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        return q.list();
    }
    
   
    

    public List<Hakmilik> findIdHakmilikByIdHakmilikIndukversi1(String idHakmilik) {
        String query = "SELECT b FROM etanah.model.Hakmilik b where b.idHakmilikInduk = :idHakmilik and b.noVersiDhde != '0' and b.kodKategoriBangunan.kod not in ('P') order by b.idHakmilik asc";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        return q.list();
    }

    public List<Hakmilik> findIdHakmilikByIdHakmilikversi1(String idHakmilik) {
        String query = "SELECT b FROM etanah.model.Hakmilik b where b.idHakmilik = :idHakmilik and b.noVersiDhde != '0' order by b.idHakmilik asc";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        return q.list();
    }

    public List<Hakmilik> findIdHakmilikByIdHakmilikIndukversi0(String idHakmilik) {
        String query = "SELECT b FROM etanah.model.Hakmilik b where b.idHakmilikInduk = :idHakmilik and b.noVersiDhde = '0' "
                + "and b.kodKategoriBangunan.kod not in ('P') and b.kodStatusHakmilik.kod = 'D' order by b.idHakmilik asc";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        return q.list();
    }

    public List<Dokumen> findGeranStrata(String idHakmilik) {
        String query = "SELECT b FROM etanah.model.Dokumen b WHERE b.infoAudit.tarikhMasuk IN (SELECT MAX(d.infoAudit.tarikhMasuk) as latest "
                + "FROM etanah.model.Dokumen d where d.tajuk LIKE :idHakmilik and d.kodDokumen.kod in ('4KDHK','4KDHD') group by d.tajuk) "
                + "and b.tajuk LIKE :idHakmilik and b.kodDokumen.kod in ('4KDHK','4KDHD','BSKKK','BSKDK','PSK') order by b.tajuk asc";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idHakmilik", "%" + idHakmilik + "%");
        return q.list();
    }

    public List<Dokumen> findGeranStrataDokumen(List<String> idHakmilik) {
        String query = "SELECT b FROM etanah.model.Dokumen b WHERE b.infoAudit.tarikhMasuk IN (SELECT MAX(d.infoAudit.tarikhMasuk) as latest "
                + "FROM etanah.model.Dokumen d where d.hakmilik in (:idHakmilik) and d.kodDokumen.kod in ('4KDHK','4KDHD') group by d.tajuk) "
                + "and b.hakmilik in (:idHakmilik) and b.kodDokumen.kod in ('4KDHD') order by b.kodDokumen.kod asc, b.hakmilik asc";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setParameterList("idHakmilik", idHakmilik);
        return q.list();
    }

    public List<Dokumen> findGeranStrataDokumenKK(List<String> idHakmilik) {
        String query = "SELECT b FROM etanah.model.Dokumen b WHERE b.infoAudit.tarikhMasuk IN (SELECT MAX(d.infoAudit.tarikhMasuk) as latest "
                + "FROM etanah.model.Dokumen d where d.hakmilik in (:idHakmilik) and d.kodDokumen.kod in ('4KDHK','4KDHD') group by d.tajuk) "
                + "and b.hakmilik in (:idHakmilik) and b.kodDokumen.kod in ('4KDHK') order by b.kodDokumen.kod asc, b.hakmilik asc";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setParameterList("idHakmilik", idHakmilik);
        return q.list();
    }

    public List<Dokumen> findGeranStrataDokumenPSK(List<String> idHakmilik) {
        String query = "SELECT b FROM etanah.model.Dokumen b WHERE b.infoAudit.tarikhMasuk IN (SELECT MAX(d.infoAudit.tarikhMasuk) as latest "
                + "FROM etanah.model.Dokumen d where d.hakmilik in (:idHakmilik) and d.kodDokumen.kod in ('4KDHK','PSK') group by d.tajuk) "
                + "and b.hakmilik in (:idHakmilik) and b.kodDokumen.kod in ('PSK') order by b.kodDokumen.kod asc, b.hakmilik asc";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setParameterList("idHakmilik", idHakmilik);
        return q.list();
    }

    public List<Dokumen> findGeranStrataDokumenBSK(List<String> idHakmilik) {
        String query = "SELECT b FROM etanah.model.Dokumen b WHERE b.infoAudit.tarikhMasuk IN (SELECT MAX(d.infoAudit.tarikhMasuk) as latest "
                + "FROM etanah.model.Dokumen d where d.hakmilik in (:idHakmilik) and d.kodDokumen.kod in ('4KDHK','BSKKK','BSKDK') group by d.tajuk) "
                + "and b.hakmilik in (:idHakmilik) and b.kodDokumen.kod in ('BSKDK') order by b.kodDokumen.kod asc, b.hakmilik asc";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setParameterList("idHakmilik", idHakmilik);
        return q.list();
    }

    public List<Dokumen> findGeranStrataDokumenBSKK(List<String> idHakmilik) {
        String query = "SELECT b FROM etanah.model.Dokumen b WHERE b.infoAudit.tarikhMasuk IN (SELECT MAX(d.infoAudit.tarikhMasuk) as latest "
                + "FROM etanah.model.Dokumen d where d.hakmilik in (:idHakmilik) and d.kodDokumen.kod in ('4KDHK','BSKKK','BSKDK') group by d.tajuk) "
                + "and b.hakmilik in (:idHakmilik) and b.kodDokumen.kod in ('BSKKK') order by b.kodDokumen.kod asc, b.hakmilik asc";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setParameterList("idHakmilik", idHakmilik);
        return q.list();
    }

    public List<Dokumen> findGeranStrataProv(String idHakmilik) {
        String query = "SELECT b FROM etanah.model.Dokumen b WHERE b.infoAudit.tarikhMasuk IN (SELECT MAX(d.infoAudit.tarikhMasuk) as latest "
                + "FROM etanah.model.Dokumen d where d.hakmilik = :idHakmilik and d.kodDokumen.kod in ('4AKKK','4AKDH')) "
                + "and b.hakmilik = :idHakmilik and b.kodDokumen.kod in ('4AKKK','4AKDH','BSKKK','BSKDK','PSK') order by b.tajuk asc";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        return q.list();
    }

    public List<Dokumen> findGeranStrata2k3k(String idHakmilik) {
        String query = "SELECT b FROM etanah.model.Dokumen b WHERE b.infoAudit.tarikhMasuk IN (SELECT MAX(d.infoAudit.tarikhMasuk) as latest "
                + "FROM etanah.model.Dokumen d where d.tajuk like :idHakmilik and d.kodDokumen.kod in ('2K','3K')) AND b.tajuk like :idHakmilik and b.kodDokumen.kod in ('2K','3K')";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idHakmilik", "%" + idHakmilik + "%");
        return q.list();
    }

    //murali 16-04-2012
    public Pihak findByIdPihak(Long idPihak) {
        String query = "SELECT b FROM etanah.model.Pihak b where b.idPihak = :idPihak";
        return (Pihak) sessionProvider.get().createQuery(query).setLong("idPihak", idPihak).uniqueResult();
    }

    public PihakAlamatTamb findAlamatTambByIdPihak(Long idPihak) {
        String query = "SELECT b FROM etanah.model.PihakAlamatTamb b where b.pihak.idPihak = :idPihak";
        return (PihakAlamatTamb) sessionProvider.get().createQuery(query).setLong("idPihak", idPihak).uniqueResult();
    }

    //murali 18-04-2012
    @Transactional
    public void deleteBadanUrus(BadanPengurusan badanUrus) {
        badanPengurusanDAO.delete(badanUrus);
    }

    @Transactional
    public void deletemohonStrataByIDMohon(String idPermohonan) {
        String query = "DELETE FROM etanah.model.PermohonanStrata p WHERE p.permohonan.idPermohonan = :idPermohonan";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.executeUpdate();
    }

    @Transactional
    public void deleteMaklumatBgnn(String idPermohonan) {
        String query = "DELETE FROM etanah.model.LaporanTanah p where p.permohonan.idPermohonan = :idPermohonan";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.executeUpdate();
    }

    public List<PermohonanSkim> findPermohonanSkimByIdMh(long idMh) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select b from etanah.model.PermohonanSkim b where b.hakmilikPermohonan.id = :idMh order by b.infoAudit.tarikhMasuk desc");
        q.setLong("idMh", idMh);
        return q.list();
    }

    @Transactional
    public void deletemohonskim(long idSkim) {
        String query = "DELETE FROM etanah.model.PermohonanSkim p WHERE p.idSkim = :idSkim";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idSkim", idSkim);
        q.executeUpdate();
    }

//    @Transactional
//    public void deletePihak(Pihak pihak) {
//        pihakDAO.delete(pihak);
//    }
    @Transactional
    public void deletePihak(long idPihak) {
        String query = "DELETE FROM etanah.model.Pihak p WHERE p.idPihak = :idPihak";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idPihak", idPihak);
        q.executeUpdate();
    }

    //murali 26-04-2012
    public List<BangunanPetak> findPetakByIdTingKat(long idTingkat) {
        String query = "SELECT b FROM etanah.model.BangunanPetak b where b.tingkat.idTingkat =:idTingkat order by LPAD(lower(b.nama),10,0) ASC";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idTingkat", idTingkat);
        return q.list();
    }

    //murali 22-05-2012
    public void deleteHartaBersamaByIdSkim(long idSkim) {
        String query = "DELETE FROM etanah.model.PermohonanHartaBersama b where b.idSkim.idSkim =:idSkim";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idSkim", idSkim);
        q.executeUpdate();
    }

    //murali 23-05-2012
    public List<PermohonanHartaBersama> findHartaBersamaByidSkim(long idSkim) {
        String query = "SELECT b FROM etanah.model.PermohonanHartaBersama b where b.idSkim.idSkim =:idSkim order by b.idHartaBsama";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idSkim", idSkim);
        return q.list();
    }

    public List<PermohonanHartaBersama> findHartaBersamaByidSkim2(long idSkim) {
        String query = "SELECT distinct(b.jenisHartaBersama.kod) FROM etanah.model.PermohonanHartaBersama b where b.idSkim.idSkim =:idSkim ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idSkim", idSkim);
        return q.list();
    }

    public HakmilikPermohonan findMohonHakmilikAsc(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.HakmilikPermohonan b where b.permohonan.idPermohonan = :idPermohonan order by b.id";
        return (HakmilikPermohonan) (sessionProvider.get().createQuery(query).setString("idPermohonan", idPermohonan)).list().get(0);
    }

    public PermohonanSkim findSkimByBdnUrusan(long idBadan) {
        String query = "SELECT b FROM etanah.model.PermohonanSkim b where b.badanPengurusan.idBadan =:idBadan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idBadan", idBadan);
        return (PermohonanSkim) q.uniqueResult();
    }

    public PermohonanSkim findSkimById(long idSkim) {
        String query = "SELECT b FROM etanah.model.PermohonanSkim b where b.idSkim =:idSkim";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idSkim", idSkim);
        return (PermohonanSkim) q.uniqueResult();
    }

    //murali 25-05-2012
    public BangunanTingkat findTinketByIdBngnNama(long idBangunan, String nama) {
        String query = "SELECT b FROM etanah.model.BangunanTingkat b where b.bangunan.idBangunan = :idBangunan and b.nama =:nama";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idBangunan", idBangunan);
        q.setString("nama", nama);
        return (BangunanTingkat) q.uniqueResult();
    }

    public BangunanTingkat findTinketByIdBngnNama2(long idBangunan) {
        String query = "SELECT b FROM etanah.model.BangunanTingkat b where b.bangunan.idBangunan = :idBangunan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idBangunan", idBangunan);
        return (BangunanTingkat) q.uniqueResult();
    }

    public BangunanPetak findBangunanPetak(String nama, long idTgkt) {
        String query = "SELECT b FROM etanah.model.BangunanPetak b where b.nama = :nama and b.tingkat.idTingkat = :idTgkt";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("nama", nama);
        q.setLong("idTgkt", idTgkt);
        return (BangunanPetak) q.uniqueResult();
    }

    public KodAkaun findKodAkaun(String kodAkaun) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select lt from etanah.model.KodAkaun lt where lt.kod = :kodAkaun");
        q.setString("kodAkaun", kodAkaun);
        return (KodAkaun) q.uniqueResult();
    }

    public Akaun findAkaunHM(String idHakmilik) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select lt from etanah.model.Akaun lt where lt.hakmilik.idHakmilik = :idHakmilik");
        q.setString("idHakmilik", idHakmilik);
        return (Akaun) q.uniqueResult();
    }

    public BangunanPetak findPetakByIdBngnIdTinketNama(long idTingkat, String nama) {
        String query = "SELECT b FROM etanah.model.BangunanPetak b where b.tingkat.idTingkat =:idTingkat and b.nama=:nama";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idTingkat", idTingkat);
        q.setString("nama", nama);
        return (BangunanPetak) q.uniqueResult();
    }

    public BangunanPetak findPetakByIdBngnIdTinketNama2(long idTingkat) {
        String query = "SELECT b FROM etanah.model.BangunanPetak b where b.tingkat.idTingkat =:idTingkat";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idTingkat", idTingkat);
        return (BangunanPetak) q.uniqueResult();
    }

    public PermohonanSkim getermohonanSkimList(long idMh) {
        String query = "Select b from etanah.model.PermohonanSkim b where b.hakmilikPermohonan.id = :idMh";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idMh", idMh);
        //q.setString("kod", kod);
//        return q.list();
        if (q.list().isEmpty()) {
            return (PermohonanSkim) q.uniqueResult();
        } else {
            return (PermohonanSkim) q.list().get(0);
        }
    }

    //added by murali 26-06-2012
    public List<HakmilikPermohonan> findIdHakmilikSort(String idPermohonan) {
        String query = "Select hp from etanah.model.HakmilikPermohonan hp where hp.permohonan.idPermohonan = :idPermohonan order by hp.hakmilik.idHakmilik";
        Query q = sessionProvider.get().createQuery(query).setString("idPermohonan", idPermohonan);
        return q.list();
    }
    public List<HakmilikPermohonan> findIdHakmilikSortNew(String idPermohonan) {
        String query = "Select hp from etanah.model.HakmilikPermohonan hp where hp.permohonan.idPermohonan = :idPermohonan and hp.hakmilik.kodStatusHakmilik.kod = 'T' order by hp.hakmilik.idHakmilik";
        Query q = sessionProvider.get().createQuery(query).setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public HakmilikSebelumPermohonan findIdMohonSblm(String idMH) {
        String query = "Select hp from etanah.model.HakmilikSebelumPermohonan hp where hp.hakmilikPermohonan.id = :idMH";
        Query q = sessionProvider.get().createQuery(query).setString("idMH", idMH);
        return (HakmilikSebelumPermohonan) q.uniqueResult();
    }

    public HakmilikUrusan findIdMohonHakmilikUrusan(String idHakmilik) {
        String query = "Select hu from etanah.model.HakmilikUrusan hu where hu.hakmilik.idHakmilik = :idHakmilik and hu.kodUrusan.kod='STMA'";
        Query q = sessionProvider.get().createQuery(query).setString("idHakmilik", idHakmilik);
        return (HakmilikUrusan) q.uniqueResult();
    }

    public List<HakmilikSebelumPermohonan> findIdHakmilikSblm(String idPermohonan) {
        String query = "Select hp from etanah.model.HakmilikPermohonan hp where hp.permohonan.idPermohonan = :idPermohonan order by hp.hakmilik.idHakmilik";
        Query q = sessionProvider.get().createQuery(query).setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<HakmilikUrusan> findListHakmilikUrusanPMTbyHakmilikInduk(String idHakmilikInduk) {
        String query = "SELECT hu FROM etanah.model.HakmilikUrusan hu "
                + "WHERE hu.kodUrusan.kod in ('HT') "
                + "AND hu.hakmilik.idHakmilikInduk = :idHakmilikInduk "
                + "ORDER BY hu.hakmilik.idHakmilik asc";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query).setString("idHakmilikInduk", idHakmilikInduk);
        return q.list();
    }

    public List<Hakmilik> findListHakmilikInduk(String trhMula, String trhTamat) {
        String query = "SELECT DISTINCT hm.idHakmilikInduk FROM etanah.model.Hakmilik hm, etanah.model.HakmilikUrusan hu "
                + "WHERE hu.kodUrusan.kod in ('HT') "
                + "AND hm.idHakmilik = hu.hakmilik.idHakmilik "
                + "AND hm.idHakmilikInduk is not null "
                + "AND (hu.tarikhDaftar BETWEEN to_date(:trhMula,'dd/MM/yyyy') "
                + "AND to_date(:trhTamat,'dd/MM/yyyy')) "
                + "ORDER BY hm.idHakmilikInduk asc";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query).setString("trhMula", trhMula).setString("trhTamat", trhTamat);
        return q.list();
    }

    public List<Hakmilik> findListHakmilikIndukByHakmilik(String idHakmilik) {
        String query = "SELECT DISTINCT hm.idHakmilikInduk FROM etanah.model.Hakmilik hm, etanah.model.HakmilikUrusan hu "
                + "WHERE hu.kodUrusan.kod in ('HT') "
                + "AND hm.idHakmilik = hu.hakmilik.idHakmilik "
                + "AND hm.idHakmilikInduk = :idHakmilik ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query).setString("idHakmilik", idHakmilik);
        return q.list();
    }

    public List<Hakmilik> findIDHakmilikInduk(String daerah, String bpm, String seksyen, String lot, String noLot) {
        String query = "SELECT DISTINCT hm.idHakmilikInduk FROM etanah.model.Hakmilik hm "
                + "WHERE hm.daerah.kod =:daerah "
                + "AND hm.bandarPekanMukim.kod =:bpm "
                + "AND hm.lot.kod =:lot "
                + "AND hm.noLot =:noLot "
                + "AND hm.idHakmilikInduk is not null";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("daerah", daerah);
        q.setString("bpm", bpm);
        q.setString("lot", lot);
        q.setString("noLot", noLot);
        return q.list();
    }

    public List<HakmilikPermohonan> findIdHakmilikSortAsc(String idPermohonan) {
        String query = "Select hp from etanah.model.HakmilikPermohonan hp where hp.permohonan.idPermohonan = :idPermohonan order by hp.hakmilik.idHakmilik asc";
        Query q = sessionProvider.get().createQuery(query).setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<HakmilikPermohonan> findIdHakmilikSortIDHMAsc(String idPermohonan) {
        String query = "Select hp from etanah.model.HakmilikPermohonan hp, etanah.model.Hakmilik h where h.idHakmilik = hp.hakmilik.idHakmilik and hp.permohonan.idPermohonan = :idPermohonan order by h.noPetak asc";
        Query q = sessionProvider.get().createQuery(query).setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<HakmilikPermohonan> findIdHakmilikSortIDHMAsc2(String idPermohonan) {
        String query = "Select hp from etanah.model.HakmilikPermohonan hp, etanah.model.Hakmilik h where h.idHakmilik = hp.hakmilik.idHakmilik and hp.permohonan.idPermohonan = :idPermohonan and hp.hakmilik.idHakmilikInduk is not null order by length(h.noPetak), h.noPetak asc";
        Query q = sessionProvider.get().createQuery(query).setString("idPermohonan", idPermohonan);
        return q.list();
    }

    //added by ida 28-08-2013
    public List<HakmilikPermohonan> findIdHakmilikStrataSortAsc(String idPermohonan) {
        String query = "Select hm from etanah.model.HakmilikPermohonan hp, etanah.model.Hakmilik hm where hp.hakmilik.idHakmilik = hm.hakmilik.idHakmilik and hp.permohonan.idPermohonan = :idPermohonan order by hp.hakmilik.idHakmilik asc";
        Query q = sessionProvider.get().createQuery(query).setString("idPermohonan", idPermohonan);
        return q.list();
    }

    //added by murali 06-07-2012
    public List<Pemohon> findByPermohonan(String idPermohonan) {
        String query = "SELECT p FROM etanah.model.Pemohon p where p.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    //added by murali 06-07-2012
    public List<BangunanPetakAksesori> findPtkAksrByIDPetak(Long idPetak) {
        String query = "SELECT b FROM etanah.model.BangunanPetakAksesori b where b.petak.idPetak = :idPetak order by to_number(b.nama) asc";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idPetak", idPetak);
        return q.list();
    }

    public List<BangunanPetakAksesori> findPtkAksrByIdTgkt(Long idTgkt) {
        String query = "SELECT b FROM etanah.model.BangunanPetakAksesori b where b.tingkat.idTingkat = :idTgkt";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idTgkt", idTgkt);
        return q.list();
    }

    public List<BangunanPetakAksesori> listPetakAksr(List<Long> idBngn) {
        String query = "SELECT b FROM etanah.model.BangunanPetakAksesori b where "
                + "b.bangunan.idBangunan in  (:idBngn) and nama is not null "
                + " and b.tingkat.idTingkat is not null and b.petak.idPetak is not null "
                + "order by to_number(nama) asc ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setParameterList("idBngn", idBngn);
        return q.list();
    }

    public List<String> sumLuasPetakAksr(Long idBngn) {
        String query = "SELECT sum(b.lain), b.petak.idPetak FROM etanah.model.BangunanPetakAksesori b where "
                + "b.bangunan.idBangunan in  (:idBngn) and nama is not null "
                + " and b.tingkat.idTingkat is not null and b.petak.idPetak is not null "
                + " group by b.petak.idPetak "
                + " order by b.petak.idPetak asc ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idBngn", idBngn);
        return q.list();
    }//select sum(lain), id_petak  from bngn_petak_aksr where id_bngn = '2369' group by id_petak;

    public List<BangunanPetakAksesori> listPelanTamb(List<Long> idBngn) {
        String query = "SELECT b FROM etanah.model.BangunanPetakAksesori b where "
                + "b.bangunan.idBangunan in  (:idBngn) and nama is null "
                + " and b.tingkat.idTingkat is not null  "
                + "order by b.pabAksesori asc ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setParameterList("idBngn", idBngn);
        return q.list();
    }

    public PelanGIS findPelanGIS(String idPermohonan, String jenisPelan) {
        String query = "SELECT b FROM etanah.model.gis.PelanGIS b where b.pelanGISPK.permohonan.idPermohonan = :idPermohonan AND b.jenisPelan = :jenisPelan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setParameter("idPermohonan", idPermohonan);
        q.setParameter("jenisPelan", jenisPelan);
        return (PelanGIS) q.uniqueResult();
    }

    //added by murali 30-07-2012
    @Transactional
    public void simpanHakmilik(Hakmilik hakmilik) {
        hakmilikDAO.saveOrUpdate(hakmilik);
    }

    @Transactional
    public void updateHakmilik(Hakmilik hakmilik) {
        hakmilikDAO.update(hakmilik);
    }

    //added by murali 22-08-2012
    public List<PermohonanBangunan> findByIDPermohonanByProvisional(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PermohonanBangunan b where b.permohonan.idPermohonan = :idPermohonan AND b.nama LIKE '%P%'";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<PermohonanRujukanLuar> findPermohonanList(String idPermohonan, String kod) {
        String query = "SELECT b FROM etanah.model.PermohonanRujukanLuar b where b.permohonan.idPermohonan = :idPermohonan and b.kodRujukan= :kod ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("kod", kod);
        return q.list();
    }

    public Hakmilik getUnitPetakByIdHakmilik(String idHM) {
        String query = "SELECT b FROM etanah.model.Hakmilik b where b.idHakmilik = :idHM";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idHM", idHM);
        return (Hakmilik) q.uniqueResult();
    }

    public Hakmilik getMaxPetakNo(String idHakmilik) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select lt from etanah.model.Hakmilik lt where lt.idHakmilik=(Select MAX(lt1.idHakmilik) from etanah.model.Hakmilik lt1 where lt1.idHakmilik LIKE :idHakmilik)");
        q.setString("idHakmilik", "%" + idHakmilik + "%");
        return (Hakmilik) q.uniqueResult();
    }

    public Hakmilik getMaxPetakNo1(String idHakmilik1, String idHakmilik2) {
        idHakmilik1 = idHakmilik1.substring(0, idHakmilik1.length() - 2);
        idHakmilik2 = idHakmilik2.substring(0, idHakmilik2.length() - 2);
        System.out.println("-----idHakmilik1 in service method-----" + idHakmilik1);
        System.out.println("-----idHakmilik2 in service method-----" + idHakmilik2);
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select lt from etanah.model.Hakmilik lt where lt.idHakmilik=(Select MAX(lt1.idHakmilik) from etanah.model.Hakmilik lt1 where lt1.idHakmilik LIKE :idHakmilik1 or lt1.idHakmilik LIKE :idHakmilik2)");
        q.setString("idHakmilik1", "%" + idHakmilik1 + "%");
        q.setString("idHakmilik2", "%" + idHakmilik2 + "%");
        return (Hakmilik) q.uniqueResult();
    }

    public List<HakmilikPihakBerkepentingan> findHakmilikPihakByIDHakmilik(String idHakmilik) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select lt from etanah.model.HakmilikPihakBerkepentingan lt where lt.hakmilik.idHakmilik = :idHakmilik");
        q.setString("idHakmilik", idHakmilik);
        return q.list();
    }

    public List<HakmilikPihakBerkepentingan> findPMAktif(String idHakmilik) {
        String query = "SELECT b FROM etanah.model.HakmilikPihakBerkepentingan b where b.hakmilik.idHakmilik = :idHakmilik "
                + "and b.jenis.kod in ('PM','WS','WPA','WKL','RP','PP','PLK','PK','PDP','PA','KL','JK','JA','ASL','PPC','PPH','PPM','PL','PAT','PAW','PAP','WKF','PML','CP','WP','PH','BP') and b.aktif = 'Y'";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        return q.list();
    }

    public List<HakmilikPihakBerkepentingan> findHakmilikPihakKodPBByIDHakmilik(String idHakmilik, String kodPB) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select lt from etanah.model.HakmilikPihakBerkepentingan lt where lt.hakmilik.idHakmilik = :idHakmilik AND lt.jenis.kod = :kodPB");
        q.setString("idHakmilik", idHakmilik);
        q.setString("kodPB", kodPB);
        return q.list();
    }

    public void generatePHPCHakmilikStrata(InfoAudit ia, Permohonan p, Pengguna pengguna) {
        System.out.println("-----STRPTSERVICE generating hakmiliks for PHPP and PHPC-----");
        kodNegeri = conf.getProperty("kodNegeri");
        int a = 0;
        Hakmilik hk = new Hakmilik();
        if (kodNegeri.equalsIgnoreCase("04")) {
            System.out.println("-----melaka-----1:");
            if (p.getKodUrusan().getKod().equals("PHPC")) {
                System.out.println("-----PHPC-----");
                System.out.println("-----current id hakmilik-----:" + p.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik());
                hk = getMaxPetakNo(p.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik());
                System.out.println("-----Max hakmilik-----:" + hk.getIdHakmilik());
            } else {
                System.out.println("-----PHPP-----");
                System.out.println("-----current id hakmilik 1-----:" + p.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik());
                System.out.println("-----current id hakmilik 2-----:" + p.getSenaraiHakmilik().get(1).getHakmilik().getIdHakmilik());
                hk = getMaxPetakNo1(p.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik(), p.getSenaraiHakmilik().get(1).getHakmilik().getIdHakmilik());
                System.out.println("-----Max hakmilik-----:" + hk.getIdHakmilik());
            }
        } else {
            System.out.println("-----seremban-----2:");
            if (p.getPermohonanSebelum().getKodUrusan().getKod().equals("PHPC")) {
                System.out.println("-----PHPC-----");
                System.out.println("-----current id hakmilik-----:" + p.getPermohonanSebelum().getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik());
                hk = getMaxPetakNo(p.getPermohonanSebelum().getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik());
                System.out.println("-----Max hakmilik-----:" + hk.getIdHakmilik());
            } else {
                System.out.println("-----PHPP-----");
                System.out.println("-----current id hakmilik1-----:" + p.getPermohonanSebelum().getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik());
                System.out.println("-----current id hakmilik2-----:" + p.getPermohonanSebelum().getSenaraiHakmilik().get(1).getHakmilik().getIdHakmilik());
                hk = getMaxPetakNo1(p.getPermohonanSebelum().getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik(), p.getPermohonanSebelum().getSenaraiHakmilik().get(1).getHakmilik().getIdHakmilik());
                System.out.println("-----Max hakmilik-----:" + hk.getIdHakmilik());
            }
        }
        String str1 = "";
        String str2 = "";
        System.out.println("-----hakmilik induk-----:" + hk.getIdHakmilikInduk());
        if (hk.getIdHakmilik().contains("GRN") || hk.getIdHakmilik().contains("HSD")) {
            System.out.println("-----Hakmilik contains GRN/HSD-----:");
            str1 = hk.getIdHakmilik().substring(0, 23);
            str2 = hk.getIdHakmilik().substring(23, hk.getIdHakmilik().length());
        } else if (hk.getIdHakmilik().contains("GM") || hk.getIdHakmilik().contains("PN")) {
            System.out.println("-----Hakmilik contains GM/PN-----:");
            str1 = hk.getIdHakmilik().substring(0, 22);
            str2 = hk.getIdHakmilik().substring(22, hk.getIdHakmilik().length());
        }
        System.out.println("-----str1-----:" + str1);
        System.out.println("-----str2-----:" + str2);
        int val = Integer.parseInt(str2);
        System.out.println("Converted int value:" + val);
        if (kodNegeri.equalsIgnoreCase("04")) {
            System.out.println("-----melaka----2:");
            if (p.getKodUrusan().getKod().equals("PHPC")) {
                a = 2;
            } else if (p.getKodUrusan().getKod().equals("PHPP")) {
                a = 1;
            }
        } else {
            System.out.println("-----seremban-----2");
            if (p.getPermohonanSebelum().getKodUrusan().getKod().equals("PHPC")) {
                a = 2;
            } else if (p.getPermohonanSebelum().getKodUrusan().getKod().equals("PHPP")) {
                a = 1;
            }
        }
        if (p.getKodUrusan().getKod().equals("HTSPS")) {
            System.out.println("---Hakmilik Baru for HTSPS---");
            System.out.println("---p.getPermohonanSebelum().getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik()----:" + p.getPermohonanSebelum().getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik());
            List<HakmilikPihakBerkepentingan> hmpihak = findHakmilikPihakKodPBByIDHakmilik(p.getPermohonanSebelum().getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik(), "PM");

            for (int i = 1; i <= 2; i++) {
                val = val + 1;
                NumberFormat formatterBangunan = new DecimalFormat("00000");
                String hakmilikBaruStr = str1 + formatterBangunan.format(val);
                System.out.println("--Hakmilik Baru--for HTSPS--:" + hakmilikBaruStr);
                Hakmilik hmilik = hakmilikDAO.findById(p.getPermohonanSebelum().getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik());
                if (hk.getNoVersiDhde() == null) {
                    hk.setNoVersiDhde(1);
                    hakmilikDAO.saveOrUpdate(hk);
                }
                if (hk.getNoVersiDhke() == null) {
                    hk.setNoVersiDhke(1);
                    hakmilikDAO.saveOrUpdate(hk);
                }
                if (hmilik.getNoVersiDhde() == null) {
                    hmilik.setNoVersiDhde(1);
                    hakmilikDAO.saveOrUpdate(hmilik);
                }
                if (hmilik.getNoVersiDhke() == null) {
                    hmilik.setNoVersiDhke(1);
                    hakmilikDAO.saveOrUpdate(hmilik);
                }
                Hakmilik hakmilikBaru = new Hakmilik();
                //saving hakmilik baru in hakmilik table
                hakmilikBaru.setIdHakmilik(hakmilikBaruStr);
                hakmilikBaru.setNoFail(hk.getNoFail());
                if (conf.getProperty("kodNegeri").equals("05")) {
                    hakmilikBaru.setCawangan(hmilik.getCawangan());
                }
                if (conf.getProperty("kodNegeri").equals("04")) {
                    hakmilikBaru.setCawangan(pengguna.getKodCawangan());
                }

                hakmilikBaru.setDaerah(hk.getDaerah());
                hakmilikBaru.setBandarPekanMukim(hk.getBandarPekanMukim());
                hakmilikBaru.setSeksyen(hk.getSeksyen());
                hakmilikBaru.setLokasi(hk.getLokasi());
                hakmilikBaru.setKodHakmilik(hk.getKodHakmilik());
                hakmilikBaru.setLot(hk.getLot());
                hakmilikBaru.setNoLot(hk.getNoLot());
                hakmilikBaru.setKategoriTanah(hk.getKategoriTanah());
                hakmilikBaru.setKegunaanTanah(hk.getKegunaanTanah());
                hakmilikBaru.setTarikhDaftar(new java.util.Date());
                KodStatusHakmilik ksh = new KodStatusHakmilik();
                ksh.setKod("D");
                hakmilikBaru.setKodStatusHakmilik(ksh);
                hakmilikBaru.setLotBumiputera(hk.getLotBumiputera());
                hakmilikBaru.setMilikPersekutuan(hk.getMilikPersekutuan());
                hakmilikBaru.setKodUnitLuas(hk.getKodUnitLuas());
                System.out.println("--seremban--3:");
                System.out.println("--Saving unit luas--:");
                System.out.println("--Luas From Given IdHakmilik--:" + p.getPermohonanSebelum().getSenaraiHakmilik().get(0).getHakmilik().getLuas());
                BigDecimal lu = p.getPermohonanSebelum().getSenaraiHakmilik().get(0).getHakmilik().getLuas().divide(new BigDecimal("2"));
                System.out.println("--luas divided by 2--:" + lu);
                hakmilikBaru.setLuas(lu);

                String noHakmilik = hakmilikBaruStr.substring(hakmilikBaruStr.length() - 19, (hakmilikBaruStr.length() - 19) + 8);
                System.out.println("--noHakmilik--:" + noHakmilik);
                hakmilikBaru.setNoHakmilik(noHakmilik);
                hakmilikBaru.setNoPelan("");
                hakmilikBaru.setNoPu(hk.getNoPu());
                hakmilikBaru.setNoLitho(hk.getNoLitho());
                hakmilikBaru.setSekatanKepentingan(hk.getSekatanKepentingan());
                hakmilikBaru.setSyaratNyata(hk.getSyaratNyata());
                hakmilikBaru.setPbt(hk.getPbt());
                hakmilikBaru.setCukai(BigDecimal.ZERO);
                hakmilikBaru.setPbtKawasan(hk.getPbtKawasan());
                hakmilikBaru.setPbtNoWarta(hk.getPbtNoWarta());
                hakmilikBaru.setPbtTarikhWarta(hk.getPbtTarikhWarta());
                hakmilikBaru.setGsaKawasan(hk.getGsaKawasan());
                hakmilikBaru.setGsaNoWarta(hk.getGsaNoWarta());
                hakmilikBaru.setGsaTarikhWarta(hk.getGsaTarikhWarta());
                hakmilikBaru.setTarikhDaftarAsal(hk.getTarikhDaftarAsal());
                System.out.println("------novVersiDhde-------::" + hakmilikBaru.getNoVersiDhde());
                System.out.println("------novVersiDhde-------::" + hakmilikBaru.getNoVersiDhke());
                if (hakmilikBaru.getNoVersiDhde() != null) {
                    hakmilikBaru.setNoVersiDhde(hk.getNoVersiDhde());
                } else {
                    hakmilikBaru.setNoVersiDhde(1);
                }
                if (hakmilikBaru.getNoVersiDhke() != null) {
                    hakmilikBaru.setNoVersiDhke(hk.getNoVersiDhke());
                } else {
                    hakmilikBaru.setNoVersiDhke(1);
                }
                //hakmilikBaru.setBadanPengurusan(hk.getBadanPengurusan());
                System.out.println("--Seremban ID Mohon Sebelum--:" + p.getPermohonanSebelum().getIdPermohonan());
                BadanPengurusan bdn = findBdn(p.getPermohonanSebelum().getIdPermohonan());
                if (bdn != null) {
                    System.out.println("--BadanPengurusan--:" + bdn);
                    hakmilikBaru.setBadanPengurusan(bdn);
                }
                hakmilikBaru.setNoSkim(hk.getNoSkim());
                hakmilikBaru.setTarikhLuput(hk.getTarikhLuput());
                hakmilikBaru.setPegangan(hk.getPegangan());
                hakmilikBaru.setTempohPegangan(hk.getTempohPegangan());
                hakmilikBaru.setNoBangunan(hk.getNoBangunan());
                hakmilikBaru.setKodKegunaanBangunan(hk.getKodKegunaanBangunan());
                hakmilikBaru.setKodKategoriBangunan(hk.getKodKategoriBangunan());
                hakmilikBaru.setNoTingkat(hk.getNoTingkat());
                hakmilikBaru.setNoPetak(String.valueOf(val));
                System.out.println("--seremban--6:");
                System.out.println("--UnitySyor From Given IdHakmilik--:" + p.getPermohonanSebelum().getSenaraiHakmilik().get(0).getHakmilik().getUnitSyer());
                BigDecimal unitSyor = p.getPermohonanSebelum().getSenaraiHakmilik().get(0).getHakmilik().getUnitSyer().divide(new BigDecimal("2"));
                System.out.println("--luas divided by 2--:" + unitSyor);
                hakmilikBaru.setUnitSyer(unitSyor);
                hakmilikBaru.setJumlahUnitSyer(hk.getJumlahUnitSyer());
                hakmilikBaru.setIdHakmilikInduk(hk.getIdHakmilikInduk());
                hakmilikBaru.setInfoAudit(ia);
                simpanhakmilik(hakmilikBaru);

                /*INSERT INTO MOHON HAKMILIK*/
                HakmilikPermohonan mohonHakmilikBaru = new HakmilikPermohonan();
                mohonHakmilikBaru.setHakmilik(hakmilikBaru);
                mohonHakmilikBaru.setPermohonan(p);
                mohonHakmilikBaru.setInfoAudit(ia);
                saveHakmilikPermohonan(mohonHakmilikBaru);

                /*INSERT INTO HAKMILIK SEBULAM*/
                System.out.println("--Seremban--:");
                if (p.getPermohonanSebelum().getKodUrusan().getKod().equals("PHPC")) {
                    System.out.println("--PHPC--:");
                    //hmSblm = getHighestIDSblm();
                    //System.out.println("-------hmSblm-------"+hmSblm);
                    //System.out.println("-------hmSblm.get(0).getIdSebelum()-------"+hmSblm.get(0).getIdSebelum());
                    // int idSblm = (int) hmSblm.get(0).getIdSebelum();
                    //idSblm = idSblm + 1;
                    //System.out.println("---------idSBlm------------::" + idSblm);
                    System.out.println("---p.getPermohonanSebelum().getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik()---:" + p.getPermohonanSebelum().getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik());
                    SejarahHakmilik hMasterTitle1 = sejarahHakmilikDAO.findById(p.getPermohonanSebelum().getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik());
                    HakmilikSebelum hakmilikSebelumBaru = new HakmilikSebelum();
                    // hakmilikSebelumBaru.setIdSebelum(idSblm);
                    hakmilikSebelumBaru.setHakmilik(hakmilikBaru);
                    hakmilikSebelumBaru.setHakmilikSebelum(hMasterTitle1.getIdHakmilik());
                    hakmilikSebelumBaru.setInfoAudit(ia);
                    hakmiliksebelumlist.add(hakmilikSebelumBaru);
                    System.out.println("--UPDATING STATUS HAKMILIK--");
                    Hakmilik hmID = hakmilikDAO.findById(hMasterTitle1.getIdHakmilik());
                    KodStatusHakmilik kodSTS = kodSTSDAO.findById("B");
                    hmID.setKodStatusHakmilik(kodSTS);
                    hakmilikDAO.saveOrUpdate(hmID);
                }

                /*INSERT INTO MOHON HAKMILIK SEBELUM*/
                System.out.println("--Saving in Mohon Hakmilik Sebelum--:");
                if (kodNegeri.equalsIgnoreCase("05")) {
                    updateHakmilikSblm(hk, hakmilikBaru, ia, mohonHakmilikBaru);
                }
                System.out.println("---p.getPermohonanSebelum().getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik()----:" + p.getPermohonanSebelum().getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik());
                for (int z = 0; z < hmpihak.size(); z++) {
                    HakmilikPihakBerkepentingan hmp = new HakmilikPihakBerkepentingan();
                    hmp.setHakmilik(hakmilikBaru);
                    hmp.setCawangan(hakmilikBaru.getCawangan());
                    hmp.setPihakCawangan(hmpihak.get(z).getPihakCawangan());
                    hmp.setJenis(hmpihak.get(z).getJenis());
                    hmp.setSyerPembilang(1);
                    hmp.setSyerPenyebut(hmpihak.size());
                    hmp.setPerserahan(hmpihak.get(z).getPerserahan());
                    hmp.setPerserahanKaveat(hmpihak.get(z).getPerserahanKaveat());
                    hmp.setKaveatAktif(hmpihak.get(z).getKaveatAktif());
                    hmp.setAktif(hmpihak.get(z).getAktif());
                    hmp.setPihak(hmpihak.get(z).getPihak());
                    hmp.setInfoAudit(ia);
                    lhp.add(hmp);
                    System.out.println("---added Hakmilik Pihak list to lhp---" + lhp.size());
                }

                if (!lhp.isEmpty()) {
                    regService.simpanHakmilikPihak(lhp);
                    System.out.println("---hakmilik baru saved in hakmilik pihak---");
                }
                if (!hakmiliksebelumlist.isEmpty()) {
                    System.out.println("---hakmiliksebelumlist---before saving--:" + hakmiliksebelumlist.size());
                    regService.simpanHakmilikSebelum(hakmiliksebelumlist);
                    System.out.println("---hakmilik baru saved in hakmilik Sebelum---");
                }
            }
        } else if (p.getKodUrusan().getKod().equals("HTSPB")) {
            System.out.println("---Hakmilik Baru for HTSPB---");
            System.out.println("---p.getPermohonanSebelum().getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik()----:" + p.getPermohonanSebelum().getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik());
            List<HakmilikPihakBerkepentingan> hmpihak = findHakmilikPihakKodPBByIDHakmilik(p.getPermohonanSebelum().getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik(), "PM");

            for (int i = 0; i < hmpihak.size(); i++) {
                val = val + 1;
                NumberFormat formatterBangunan = new DecimalFormat("00000");
                String hakmilikBaruStr = str1 + formatterBangunan.format(val);
                System.out.println("--Hakmilik Baru--for HTSPB--:" + hakmilikBaruStr);
                Hakmilik hmilik = hakmilikDAO.findById(p.getPermohonanSebelum().getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik());
                if (hk.getNoVersiDhde() == null) {
                    hk.setNoVersiDhde(1);
                    hakmilikDAO.saveOrUpdate(hk);
                }
                if (hk.getNoVersiDhke() == null) {
                    hk.setNoVersiDhke(1);
                    hakmilikDAO.saveOrUpdate(hk);
                }
                if (hmilik.getNoVersiDhde() == null) {
                    hmilik.setNoVersiDhde(1);
                    hakmilikDAO.saveOrUpdate(hmilik);
                }
                if (hmilik.getNoVersiDhke() == null) {
                    hmilik.setNoVersiDhke(1);
                    hakmilikDAO.saveOrUpdate(hmilik);
                }
                Hakmilik hakmilikBaru = new Hakmilik();
                //saving hakmilik baru in hakmilik table
                hakmilikBaru.setIdHakmilik(hakmilikBaruStr);
                hakmilikBaru.setNoFail(hk.getNoFail());
                if (conf.getProperty("kodNegeri").equals("05")) {
                    hakmilikBaru.setCawangan(hk.getCawangan());
                }
                if (conf.getProperty("kodNegeri").equals("04")) {
                    hakmilikBaru.setCawangan(pengguna.getKodCawangan());
                }

                hakmilikBaru.setDaerah(hk.getDaerah());
                hakmilikBaru.setBandarPekanMukim(hk.getBandarPekanMukim());
                hakmilikBaru.setSeksyen(hk.getSeksyen());
                hakmilikBaru.setLokasi(hk.getLokasi());
                hakmilikBaru.setKodHakmilik(hk.getKodHakmilik());
                hakmilikBaru.setLot(hk.getLot());
                hakmilikBaru.setNoLot(hk.getNoLot());
                hakmilikBaru.setKategoriTanah(hk.getKategoriTanah());
                hakmilikBaru.setKegunaanTanah(hk.getKegunaanTanah());
                hakmilikBaru.setTarikhDaftar(new java.util.Date());
                KodStatusHakmilik ksh = new KodStatusHakmilik();
                if (kodNegeri.equalsIgnoreCase("05")) {
                    ksh.setKod("D");
                } else {
                    ksh.setKod("T");
                }
                hakmilikBaru.setKodStatusHakmilik(ksh);
                hakmilikBaru.setLotBumiputera(hk.getLotBumiputera());
                hakmilikBaru.setMilikPersekutuan(hk.getMilikPersekutuan());
                hakmilikBaru.setKodUnitLuas(hk.getKodUnitLuas());
                System.out.println("--seremban--3:");
                System.out.println("--Saving unit luas--:");
                System.out.println("--Luas From Given IdHakmilik--:" + p.getPermohonanSebelum().getSenaraiHakmilik().get(0).getHakmilik().getLuas());
                BigDecimal lu = p.getPermohonanSebelum().getSenaraiHakmilik().get(0).getHakmilik().getLuas().divide(new BigDecimal("2"));
                System.out.println("--luas divided by 2--:" + lu);
                hakmilikBaru.setLuas(lu);

                String noHakmilik = hakmilikBaruStr.substring(hakmilikBaruStr.length() - 19, (hakmilikBaruStr.length() - 19) + 8);
                System.out.println("--noHakmilik--:" + noHakmilik);
                hakmilikBaru.setNoHakmilik(noHakmilik);
                hakmilikBaru.setNoPelan("");
                hakmilikBaru.setNoPu(hk.getNoPu());
                hakmilikBaru.setNoLitho(hk.getNoLitho());
                hakmilikBaru.setSekatanKepentingan(hk.getSekatanKepentingan());
                hakmilikBaru.setSyaratNyata(hk.getSyaratNyata());
                hakmilikBaru.setPbt(hk.getPbt());
                hakmilikBaru.setCukai(BigDecimal.ZERO);
                hakmilikBaru.setPbtKawasan(hk.getPbtKawasan());
                hakmilikBaru.setPbtNoWarta(hk.getPbtNoWarta());
                hakmilikBaru.setPbtTarikhWarta(hk.getPbtTarikhWarta());
                hakmilikBaru.setGsaKawasan(hk.getGsaKawasan());
                hakmilikBaru.setGsaNoWarta(hk.getGsaNoWarta());
                hakmilikBaru.setGsaTarikhWarta(hk.getGsaTarikhWarta());
                hakmilikBaru.setTarikhDaftarAsal(hk.getTarikhDaftarAsal());
                System.out.println("------novVersiDhde-------::" + hakmilikBaru.getNoVersiDhde());
                System.out.println("------novVersiDhde-------::" + hakmilikBaru.getNoVersiDhke());
                if (hakmilikBaru.getNoVersiDhde() != null) {
                    hakmilikBaru.setNoVersiDhde(hk.getNoVersiDhde());
                } else {
                    hakmilikBaru.setNoVersiDhde(1);
                }
                if (hakmilikBaru.getNoVersiDhke() != null) {
                    hakmilikBaru.setNoVersiDhke(hk.getNoVersiDhke());
                } else {
                    hakmilikBaru.setNoVersiDhke(1);
                }
                //hakmilikBaru.setBadanPengurusan(hk.getBadanPengurusan());
                System.out.println("--Seremban ID Mohon Sebelum--:" + p.getPermohonanSebelum().getIdPermohonan());
                BadanPengurusan bdn = findBdn(p.getPermohonanSebelum().getIdPermohonan());
                if (bdn != null) {
                    System.out.println("--BadanPengurusan--:" + bdn);
                    hakmilikBaru.setBadanPengurusan(bdn);
                }
                hakmilikBaru.setNoSkim(hk.getNoSkim());
                hakmilikBaru.setTarikhLuput(hk.getTarikhLuput());
                hakmilikBaru.setPegangan(hk.getPegangan());
                hakmilikBaru.setTempohPegangan(hk.getTempohPegangan());
                hakmilikBaru.setNoBangunan(hk.getNoBangunan());
                hakmilikBaru.setKodKegunaanBangunan(hk.getKodKegunaanBangunan());
                hakmilikBaru.setKodKategoriBangunan(hk.getKodKategoriBangunan());
                hakmilikBaru.setNoTingkat(hk.getNoTingkat());
                hakmilikBaru.setNoPetak(String.valueOf(val));
                System.out.println("--seremban--6:");
                System.out.println("--UnitySyor From Given IdHakmilik--:" + p.getPermohonanSebelum().getSenaraiHakmilik().get(0).getHakmilik().getUnitSyer());
                BigDecimal unitSyor = p.getPermohonanSebelum().getSenaraiHakmilik().get(0).getHakmilik().getUnitSyer().divide(new BigDecimal("2"));
                System.out.println("--luas divided by 2--:" + unitSyor);
                hakmilikBaru.setUnitSyer(unitSyor);
                hakmilikBaru.setJumlahUnitSyer(hk.getJumlahUnitSyer());
                hakmilikBaru.setIdHakmilikInduk(hk.getIdHakmilikInduk());
                hakmilikBaru.setInfoAudit(ia);
                simpanhakmilik(hakmilikBaru);

                /*INSERT INTO MOHON HAKMILIK*/
                HakmilikPermohonan mohonHakmilikBaru = new HakmilikPermohonan();
                mohonHakmilikBaru.setHakmilik(hakmilikBaru);
                mohonHakmilikBaru.setPermohonan(p);
                mohonHakmilikBaru.setInfoAudit(ia);
                saveHakmilikPermohonan(mohonHakmilikBaru);

                /*INSERT INTO HAKMILIK SEBELUM*/
                System.out.println("--Seremban--:");
                if (p.getPermohonanSebelum().getKodUrusan().getKod().equals("PHPC")) {
                    System.out.println("--PHPC--:");
                    //hmSblm = getHighestIDSblm();
                    //System.out.println("-------hmSblm-------"+hmSblm);
                    //System.out.println("-------hmSblm.get(0).getIdSebelum()-------"+hmSblm.get(0).getIdSebelum());
                    // int idSblm = (int) hmSblm.get(0).getIdSebelum();
                    //idSblm = idSblm + 1;
                    //System.out.println("---------idSBlm------------::" + idSblm);
                    System.out.println("---p.getPermohonanSebelum().getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik()---:" + p.getPermohonanSebelum().getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik());
                    SejarahHakmilik hMasterTitle1 = sejarahHakmilikDAO.findById(p.getPermohonanSebelum().getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik());
                    HakmilikSebelum hakmilikSebelumBaru = new HakmilikSebelum();
                    // hakmilikSebelumBaru.setIdSebelum(idSblm);
                    hakmilikSebelumBaru.setHakmilik(hakmilikBaru);
                    hakmilikSebelumBaru.setHakmilikSebelum(hMasterTitle1.getIdHakmilik());
                    hakmilikSebelumBaru.setInfoAudit(ia);
                    hakmiliksebelumlist.add(hakmilikSebelumBaru);
                    System.out.println("--UPDATING STATUS HAKMILIK--");
                    Hakmilik hmID = hakmilikDAO.findById(hMasterTitle1.getIdHakmilik());
                    KodStatusHakmilik kodSTS = kodSTSDAO.findById("B");
                    hmID.setKodStatusHakmilik(kodSTS);
                    hakmilikDAO.saveOrUpdate(hmID);
                }

                /*INSERT INTO MOHON HAKMILIK SEBELUM*/
                System.out.println("--Saving in Mohon Hakmilik Sebelum--:");
                if (kodNegeri.equalsIgnoreCase("05")) {
                    updateHakmilikSblm(hk, hakmilikBaru, ia, mohonHakmilikBaru);
                }
                System.out.println("---p.getPermohonanSebelum().getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik()----:" + p.getPermohonanSebelum().getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik());
                for (int z = 0; z < 1; z++) {
                    HakmilikPihakBerkepentingan hmp = new HakmilikPihakBerkepentingan();
                    hmp.setHakmilik(hakmilikBaru);
                    hmp.setCawangan(hakmilikBaru.getCawangan());
                    hmp.setPihakCawangan(hmpihak.get(i).getPihakCawangan());
                    hmp.setJenis(hmpihak.get(i).getJenis());
                    hmp.setSyerPembilang(1);
                    hmp.setSyerPenyebut(1);
                    hmp.setPerserahan(hmpihak.get(i).getPerserahan());
                    hmp.setPerserahanKaveat(hmpihak.get(i).getPerserahanKaveat());
                    hmp.setKaveatAktif(hmpihak.get(i).getKaveatAktif());
                    hmp.setAktif(hmpihak.get(i).getAktif());
                    hmp.setPihak(hmpihak.get(i).getPihak());
                    hmp.setInfoAudit(ia);
                    lhp.add(hmp);
                    System.out.println("---added Hakmilik Pihak list to lhp---" + lhp.size());
                }

                if (!lhp.isEmpty()) {
                    regService.simpanHakmilikPihak(lhp);
                    System.out.println("---hakmilik baru saved in hakmilik pihak---");
                }
                if (!hakmiliksebelumlist.isEmpty()) {
                    System.out.println("---hakmiliksebelumlist---before saving--:" + hakmiliksebelumlist.size());
                    regService.simpanHakmilikSebelum(hakmiliksebelumlist);
                    System.out.println("---hakmilik baru saved in hakmilik Sebelum---");
                }
            }
        } else {
            for (int i = 1; i <= a; i++) {
                val = val + 1;
                NumberFormat formatterBangunan = new DecimalFormat("00000");
                String hakmilikBaruStr = str1 + formatterBangunan.format(val);
                System.out.println("--Hakmilik Baru--for PHPC/PHPP--:" + hakmilikBaruStr);
                Hakmilik hmilik = hakmilikDAO.findById(p.getPermohonanSebelum().getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik());
                if (hmilik.getNoVersiDhde() == null) {
                    hmilik.setNoVersiDhde(1);
                    hakmilikDAO.saveOrUpdate(hmilik);
                }
                if (hmilik.getNoVersiDhke() == null) {
                    hmilik.setNoVersiDhke(1);
                    hakmilikDAO.saveOrUpdate(hmilik);
                }
                //saving hakmilik baru in hakmilik table
                Hakmilik hakmilikBaru = new Hakmilik();
                hakmilikBaru.setIdHakmilik(hakmilikBaruStr);
                hakmilikBaru.setNoFail(hk.getNoFail());
                if (conf.getProperty("kodNegeri").equals("05")) {
                    hakmilikBaru.setCawangan(hk.getCawangan());
                }
                if (conf.getProperty("kodNegeri").equals("04")) {
                    hakmilikBaru.setCawangan(pengguna.getKodCawangan());
                }

                hakmilikBaru.setDaerah(hk.getDaerah());
                hakmilikBaru.setBandarPekanMukim(hk.getBandarPekanMukim());
                hakmilikBaru.setSeksyen(hk.getSeksyen());
                hakmilikBaru.setLokasi(hk.getLokasi());
                hakmilikBaru.setKodHakmilik(hk.getKodHakmilik());
                hakmilikBaru.setLot(hk.getLot());
                hakmilikBaru.setNoLot(hk.getNoLot());
                hakmilikBaru.setKategoriTanah(hk.getKategoriTanah());
                hakmilikBaru.setKegunaanTanah(hk.getKegunaanTanah());
                hakmilikBaru.setTarikhDaftar(new java.util.Date());
                KodStatusHakmilik ksh = new KodStatusHakmilik();
                if (kodNegeri.equalsIgnoreCase("05")) {
                    ksh.setKod("D");
                } else {
                    ksh.setKod("T");
                }
                hakmilikBaru.setKodStatusHakmilik(ksh);
                hakmilikBaru.setLotBumiputera(hk.getLotBumiputera());
                hakmilikBaru.setMilikPersekutuan(hk.getMilikPersekutuan());
                hakmilikBaru.setKodUnitLuas(hk.getKodUnitLuas());
                if (kodNegeri.equalsIgnoreCase("04")) {
                    System.out.println("--melaka--3:");
                    System.out.println("--Saving unit luas--:");
                    if (p.getKodUrusan().getKod().equals("PHPP")) {
                        BigDecimal totalluas = BigDecimal.ZERO;
                        List<HakmilikPermohonan> hkp = p.getSenaraiHakmilik();
                        for (HakmilikPermohonan hakmilikPermohonan : hkp) {
                            System.out.println("--hkp size--" + hkp.size());
                            totalluas = totalluas.add(hakmilikPermohonan.getHakmilik().getLuas());
                        }
                        System.out.println("--totalluas--:" + totalluas);
                        hakmilikBaru.setLuas(totalluas);
                    } else {
                        System.out.println("--melaka--4:");
                        System.out.println("--Luas From Given IdHakmilik--:" + p.getSenaraiHakmilik().get(0).getHakmilik().getLuas());
                        BigDecimal lu = p.getSenaraiHakmilik().get(0).getHakmilik().getLuas().divide(new BigDecimal("2"));
                        System.out.println("--luas divided by 2--:" + lu);
                        hakmilikBaru.setLuas(lu);
                    }
                } else {
                    System.out.println("--seremban--3:");
                    System.out.println("--Saving unit luas--:");
                    if (p.getPermohonanSebelum().getKodUrusan().getKod().equals("PHPP")) {
                        List<HakmilikPermohonan> hkp = p.getPermohonanSebelum().getSenaraiHakmilik();
                        BigDecimal totalluas = BigDecimal.ZERO;
                        for (HakmilikPermohonan hakmilikPermohonan : hkp) {
                            System.out.println("--hkp size--" + hkp.size());
                            totalluas = totalluas.add(hakmilikPermohonan.getHakmilik().getLuas());
                        }
                        System.out.println("--totalluas--:" + totalluas);
                        hakmilikBaru.setLuas(totalluas);
                    } else if (p.getPermohonanSebelum().getKodUrusan().getKod().equals("PHPC")) {
                        System.out.println("--seremban--4:");
                        System.out.println("--Luas From Given IdHakmilik--:" + p.getPermohonanSebelum().getSenaraiHakmilik().get(0).getHakmilik().getLuas());
                        BigDecimal lu = p.getPermohonanSebelum().getSenaraiHakmilik().get(0).getHakmilik().getLuas().divide(new BigDecimal("2"));
                        System.out.println("--luas divided by 2--:" + lu);
                        hakmilikBaru.setLuas(lu);
                    }
                }

                String noHakmilik = hakmilikBaruStr.substring(hakmilikBaruStr.length() - 19, (hakmilikBaruStr.length() - 19) + 8);
                System.out.println("--noHakmilik--:" + noHakmilik);
                hakmilikBaru.setNoHakmilik(noHakmilik);
                hakmilikBaru.setNoPelan("");
                hakmilikBaru.setNoPu(hk.getNoPu());
                hakmilikBaru.setNoLitho(hk.getNoLitho());
                hakmilikBaru.setSekatanKepentingan(hk.getSekatanKepentingan());
                hakmilikBaru.setSyaratNyata(hk.getSyaratNyata());
                hakmilikBaru.setPbt(hk.getPbt());
                hakmilikBaru.setCukai(BigDecimal.ZERO);
                hakmilikBaru.setPbtKawasan(hk.getPbtKawasan());
                hakmilikBaru.setPbtNoWarta(hk.getPbtNoWarta());
                hakmilikBaru.setPbtTarikhWarta(hk.getPbtTarikhWarta());
                hakmilikBaru.setGsaKawasan(hk.getGsaKawasan());
                hakmilikBaru.setGsaNoWarta(hk.getGsaNoWarta());
                hakmilikBaru.setGsaTarikhWarta(hk.getGsaTarikhWarta());
                hakmilikBaru.setTarikhDaftarAsal(hk.getTarikhDaftarAsal());
                hakmilikBaru.setNoVersiDhde(hk.getNoVersiDhde());
                hakmilikBaru.setNoVersiDhke(hk.getNoVersiDhke());
                //hakmilikBaru.setBadanPengurusan(hk.getBadanPengurusan());
                if (kodNegeri.equalsIgnoreCase("04")) {
                    System.out.println("--Melaka ID Mohon--:" + p.getIdPermohonan());
                    BadanPengurusan bdn = findBdn(p.getIdPermohonan());
                    if (bdn != null) {
                        System.out.println("--BadanPengurusan--:" + bdn);
                        hakmilikBaru.setBadanPengurusan(bdn);
                    }
                } else {
                    System.out.println("--Seremban ID Mohon Sebelum--:" + p.getPermohonanSebelum().getIdPermohonan());
                    BadanPengurusan bdn = findBdn(p.getPermohonanSebelum().getIdPermohonan());
                    if (bdn != null) {
                        System.out.println("--BadanPengurusan--:" + bdn);
                        hakmilikBaru.setBadanPengurusan(bdn);
                    }
                }
                hakmilikBaru.setNoSkim(hk.getNoSkim());
                hakmilikBaru.setTarikhLuput(hk.getTarikhLuput());
                hakmilikBaru.setPegangan(hk.getPegangan());
                hakmilikBaru.setTempohPegangan(hk.getTempohPegangan());
                hakmilikBaru.setNoBangunan(hk.getNoBangunan());
                hakmilikBaru.setKodKegunaanBangunan(hk.getKodKegunaanBangunan());
                hakmilikBaru.setKodKategoriBangunan(hk.getKodKategoriBangunan());
                hakmilikBaru.setNoTingkat(hk.getNoTingkat());
                hakmilikBaru.setNoPetak(String.valueOf(val));
                if (kodNegeri.equalsIgnoreCase("04")) {
                    System.out.println("--melaka--5:");
                    System.out.println("--Saving unit Syor--:");
                    if (p.getKodUrusan().getKod().equals("PHPP")) {
                        BigDecimal totalunit = BigDecimal.ZERO;
                        List<HakmilikPermohonan> hkp = p.getSenaraiHakmilik();
                        for (HakmilikPermohonan hakmilikPermohonan : hkp) {
                            System.out.println("--hkp size--" + hkp.size());
                            totalunit = totalunit.add(hakmilikPermohonan.getHakmilik().getUnitSyer());
                        }
                        System.out.println("--totalunit--:" + totalunit);
                        hakmilikBaru.setUnitSyer(totalunit);
                    } else {
                        System.out.println("--melaka--6:");
                        System.out.println("--UnitySyor From Given IdHakmilik--:" + p.getSenaraiHakmilik().get(0).getHakmilik().getUnitSyer());
                        BigDecimal unitSyor = p.getSenaraiHakmilik().get(0).getHakmilik().getUnitSyer().divide(new BigDecimal("2"));
                        System.out.println("--luas divided by 2--:" + unitSyor);
                        hakmilikBaru.setUnitSyer(unitSyor);
                    }
                } else {
                    System.out.println("--seremban--5:");
                    System.out.println("--Saving unit syor--:");
                    if (p.getPermohonanSebelum().getKodUrusan().getKod().equals("PHPP")) {
                        BigDecimal totalunit = BigDecimal.ZERO;
                        List<HakmilikPermohonan> hkp = p.getPermohonanSebelum().getSenaraiHakmilik();
                        for (HakmilikPermohonan hakmilikPermohonan : hkp) {
                            System.out.println("--hkp size--" + hkp.size());
                            totalunit = totalunit.add(hakmilikPermohonan.getHakmilik().getUnitSyer());
                        }
                        System.out.println("--totalunit--:" + totalunit);
                        hakmilikBaru.setUnitSyer(totalunit);
                    } else {
                        System.out.println("--seremban--6:");
                        System.out.println("--UnitySyor From Given IdHakmilik--:" + p.getPermohonanSebelum().getSenaraiHakmilik().get(0).getHakmilik().getUnitSyer());
                        BigDecimal unitSyor = p.getPermohonanSebelum().getSenaraiHakmilik().get(0).getHakmilik().getUnitSyer().divide(new BigDecimal("2"));
                        System.out.println("--luas divided by 2--:" + unitSyor);
                        hakmilikBaru.setUnitSyer(unitSyor);
                    }
                }
                hakmilikBaru.setJumlahUnitSyer(hk.getJumlahUnitSyer());
                hakmilikBaru.setIdHakmilikInduk(hk.getIdHakmilikInduk());
                hakmilikBaru.setInfoAudit(ia);
                simpanhakmilik(hakmilikBaru);

                /*INSERT INTO MOHON HAKMILIK*/
                HakmilikPermohonan mohonHakmilikBaru = new HakmilikPermohonan();
                mohonHakmilikBaru.setHakmilik(hakmilikBaru);
                mohonHakmilikBaru.setPermohonan(p);
                mohonHakmilikBaru.setInfoAudit(ia);
                saveHakmilikPermohonan(mohonHakmilikBaru);

                /*INSERT INTO HAKMILIK SEBULAM*/
                if (kodNegeri.equalsIgnoreCase("04")) {
                    System.out.println("--Melaka--:");
                    if (p.getKodUrusan().getKod().equals("PHPC")) {
                        System.out.println("--PHPC--:");
                        SejarahHakmilik hMasterTitle1 = sejarahHakmilikDAO.findById(p.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik());
                        HakmilikSebelum hakmilikSebelumBaru = new HakmilikSebelum();
                        hakmilikSebelumBaru.setHakmilik(hakmilikBaru);
                        hakmilikSebelumBaru.setHakmilikSebelum(hMasterTitle1.getIdHakmilik());
                        hakmilikSebelumBaru.setInfoAudit(ia);
                        hakmiliksebelumlist.add(hakmilikSebelumBaru);
                    } else {
                        System.out.println("--PHPP--:");
                        List<Hakmilik> senaraihak = new ArrayList<Hakmilik>();
                        List<HakmilikPermohonan> hk1 = p.getSenaraiHakmilik();
                        for (HakmilikPermohonan hakmilikPermohonan : hk1) {
                            System.out.println("--hk1 size--:" + hk1.size());
                            System.out.println("--hakmilikPermohonan--:" + hakmilikPermohonan.getHakmilik().getIdHakmilik());
                            SejarahHakmilik hMasterTitle1 = sejarahHakmilikDAO.findById(hakmilikPermohonan.getHakmilik().getIdHakmilik());
                            System.out.println("--hMasterTitle1--:" + hMasterTitle1);
                            HakmilikSebelum hakmilikSebelumBaru = new HakmilikSebelum();
                            hakmilikSebelumBaru.setHakmilik(hakmilikBaru);
                            hakmilikSebelumBaru.setHakmilikSebelum(hMasterTitle1.getIdHakmilik());
                            hakmilikSebelumBaru.setInfoAudit(ia);
                            hakmiliksebelumlist.add(hakmilikSebelumBaru);
                        }
                    }
                } else {
                    System.out.println("--Seremban--:");
                    if (p.getPermohonanSebelum().getKodUrusan().getKod().equals("PHPC")) {
                        System.out.println("--PHPC--:");
                        //hmSblm = getHighestIDSblm();
                        //System.out.println("-------hmSblm-------"+hmSblm);
                        //System.out.println("-------hmSblm.get(0).getIdSebelum()-------"+hmSblm.get(0).getIdSebelum());
                        // int idSblm = (int) hmSblm.get(0).getIdSebelum();
                        //idSblm = idSblm + 1;
                        //System.out.println("---------idSBlm------------::" + idSblm);
                        System.out.println("---p.getPermohonanSebelum().getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik()---:" + p.getPermohonanSebelum().getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik());
                        SejarahHakmilik hMasterTitle1 = sejarahHakmilikDAO.findById(p.getPermohonanSebelum().getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik());
                        HakmilikSebelum hakmilikSebelumBaru = new HakmilikSebelum();
                        // hakmilikSebelumBaru.setIdSebelum(idSblm);
                        hakmilikSebelumBaru.setHakmilik(hakmilikBaru);
                        hakmilikSebelumBaru.setHakmilikSebelum(hMasterTitle1.getIdHakmilik());
                        hakmilikSebelumBaru.setInfoAudit(ia);
                        hakmiliksebelumlist.add(hakmilikSebelumBaru);
                    } else {
                        System.out.println("--PHPP--:");
                        List<Hakmilik> senaraihak = new ArrayList<Hakmilik>();
                        List<HakmilikPermohonan> hk1 = p.getPermohonanSebelum().getSenaraiHakmilik();
                        for (HakmilikPermohonan hakmilikPermohonan : hk1) {
                            System.out.println("--hk1 size--:" + hk1.size());
                            System.out.println("--hakmilikPermohonan--:" + hakmilikPermohonan.getHakmilik().getIdHakmilik());
                            SejarahHakmilik hMasterTitle1 = sejarahHakmilikDAO.findById(hakmilikPermohonan.getHakmilik().getIdHakmilik());
                            System.out.println("--hMasterTitle1--:" + hMasterTitle1);
                            HakmilikSebelum hakmilikSebelumBaru = new HakmilikSebelum();
                            hakmilikSebelumBaru.setHakmilik(hakmilikBaru);
                            System.out.println("--hakmilik adding to hakmiliksebelumlist--:" + hMasterTitle1.getIdHakmilik());
                            hakmilikSebelumBaru.setHakmilikSebelum(hMasterTitle1.getIdHakmilik());
                            hakmilikSebelumBaru.setInfoAudit(ia);
                            hakmiliksebelumlist.add(hakmilikSebelumBaru);
                            System.out.println("--hakmiliksebelumlist size in side loop--:" + hakmiliksebelumlist.size());
                            System.out.println("--UPDATING STATUS HAKMILIK--");
                            Hakmilik hmID = hakmilikDAO.findById(hMasterTitle1.getIdHakmilik());
                            KodStatusHakmilik kodSTS = kodSTSDAO.findById("B");
                            hmID.setKodStatusHakmilik(kodSTS);
                            hakmilikDAO.saveOrUpdate(hmID);
                        }
                        System.out.println("--hakmiliksebelumlist size out side loop--:" + hakmiliksebelumlist.size());
                    }
                }


                /*INSERT INTO MOHON HAKMILIK SEBELUM*/
                System.out.println("--Saving in Mohon Hakmilik Sebelum--:");
                if (kodNegeri.equalsIgnoreCase("05")) {
                    updateHakmilikSblm(hk, hakmilikBaru, ia, mohonHakmilikBaru);
                }
                System.out.println("---p.getPermohonanSebelum().getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik()----:" + p.getPermohonanSebelum().getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik());
                List<HakmilikPihakBerkepentingan> hmpihak = findHakmilikPihakByIDHakmilik(p.getPermohonanSebelum().getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik());
                for (int z = 0; z < hakmiliksebelumlist.size(); z++) {
                    HakmilikSebelum hmSblm = hakmiliksebelumlist.get(z);
                    HakmilikPihakBerkepentingan hmp = new HakmilikPihakBerkepentingan();
                    hmp.setHakmilik(hakmilikBaru);
                    hmp.setCawangan(hakmilikBaru.getCawangan());
                    hmp.setPihakCawangan(hmpihak.get(0).getPihakCawangan());
                    hmp.setJenis(hmpihak.get(0).getJenis());
                    hmp.setSyerPembilang(hmpihak.get(0).getSyerPembilang());
                    hmp.setSyerPenyebut(hmpihak.get(0).getSyerPenyebut());
                    hmp.setPerserahan(hmpihak.get(0).getPerserahan());
                    hmp.setPerserahanKaveat(hmpihak.get(0).getPerserahanKaveat());
                    hmp.setKaveatAktif(hmpihak.get(0).getKaveatAktif());
                    hmp.setAktif(hmpihak.get(0).getAktif());
                    hmp.setPihak(hmpihak.get(0).getPihak());
                    hmp.setInfoAudit(ia);
                    lhp.add(hmp);
                    System.out.println("---added Hakmilik Pihak list to lhp---" + lhp.size());
                }

                /*INSERT INTO HAKMILIK PIHAK*/
                List<HakmilikPihakBerkepentingan> senaraiHakmilikPihak = hpkService.findHakmilikAllPihakActiveByHakmilik(hk);
                System.out.println("---Hakmilik Pihak---" + senaraiHakmilikPihak.size());
                for (HakmilikPihakBerkepentingan hpk : senaraiHakmilikPihak) {
                    if (hpk == null || hpk.getAktif() == 'T') {
                        continue;
                    } else {
                        System.out.println("---Saving in Hakmilik Pihak");
                        System.out.println("---creating new Hakmilik Pihak object---");
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
                        //hpkService.save(hpb);
                        lhp.add(hpb);
                        System.out.println("---added Hakmilik Pihak list to lhp---" + lhp.size());
                    }
                }

                /*
                 if (!listHakmilikBaru.isEmpty()) {
                 regService.simpanHakmilikList(listHakmilikBaru);
                 System.out.println("---hakmilik baru saved---:");
                 }
                 if (!listMohonHakmilikBaru.isEmpty()) {
                 regService.simpanMohonHakmilikList(listMohonHakmilikBaru);
                 System.out.println("---hakmilik baru saved in mohon hakmilik---:");
                 }*/
                if (!lhp.isEmpty()) {
                    regService.simpanHakmilikPihak(lhp);
                    System.out.println("---hakmilik baru saved in hakmilik pihak---");
                }
                if (!hakmiliksebelumlist.isEmpty()) {
                    System.out.println("---hakmiliksebelumlist---before saving--:" + hakmiliksebelumlist.size());
                    regService.simpanHakmilikSebelum(hakmiliksebelumlist);
                    System.out.println("---hakmilik baru saved in hakmilik Sebelum---");
                }
            }
        }
    }

    public void generatePHPCHakmilikStrataN9(InfoAudit ia, Permohonan p, Pengguna pengguna) {
        System.out.println("-----STRPTSERVICE generating hakmiliks for PHPP and PHPC-----");
        kodNegeri = conf.getProperty("kodNegeri");
        int a = 0;
        Hakmilik hk = new Hakmilik();
        if (kodNegeri.equalsIgnoreCase("04")) {
            System.out.println("-----melaka-----1:");
            {
                System.out.println("-----seremban-----2:");
                if (p.getPermohonanSebelum().getKodUrusan().getKod().equals("PHPC")) {
                    System.out.println("-----PHPC-----");
                    System.out.println("-----current id hakmilik-----:" + p.getPermohonanSebelum().getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik());
                    hk = getMaxPetakNo(p.getPermohonanSebelum().getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik());
                    System.out.println("-----Max hakmilik-----:" + hk.getIdHakmilik());
                } else {
                    System.out.println("-----PHPP-----");
                    System.out.println("-----current id hakmilik1-----:" + p.getPermohonanSebelum().getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik());
                    System.out.println("-----current id hakmilik2-----:" + p.getPermohonanSebelum().getSenaraiHakmilik().get(1).getHakmilik().getIdHakmilik());
                    hk = getMaxPetakNo1(p.getPermohonanSebelum().getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik(), p.getPermohonanSebelum().getSenaraiHakmilik().get(1).getHakmilik().getIdHakmilik());
                    System.out.println("-----Max hakmilik-----:" + hk.getIdHakmilik());
                }
            }
            String str1 = "";
            String str2 = "";
            System.out.println("-----hakmilik induk-----:" + hk.getIdHakmilikInduk());
            if (hk.getIdHakmilik().contains("GRN") || hk.getIdHakmilik().contains("HSD")) {
                System.out.println("-----Hakmilik contains GRN/HSD-----:");
                str1 = hk.getIdHakmilik().substring(0, 23);
                str2 = hk.getIdHakmilik().substring(23, hk.getIdHakmilik().length());
            } else if (hk.getIdHakmilik().contains("GM") || hk.getIdHakmilik().contains("PN")) {
                System.out.println("-----Hakmilik contains GM/PN-----:");
                str1 = hk.getIdHakmilik().substring(0, 22);
                str2 = hk.getIdHakmilik().substring(22, hk.getIdHakmilik().length());
            }
            System.out.println("-----str1-----:" + str1);
            System.out.println("-----str2-----:" + str2);
            int val = Integer.parseInt(str2);
            System.out.println("Converted int value:" + val);
            if (kodNegeri.equalsIgnoreCase("04")) {
                System.out.println("-----melaka----2:");
                if (p.getKodUrusan().getKod().equals("PHPC")) {
                    a = 2;
                } else if (p.getKodUrusan().getKod().equals("PHPP")) {
                    a = 1;
                }
            } else {
                System.out.println("-----seremban-----2");
                if (p.getPermohonanSebelum().getKodUrusan().getKod().equals("PHPC")) {
                    a = 2;
                } else if (p.getPermohonanSebelum().getKodUrusan().getKod().equals("PHPP")) {
                    a = 1;
                }
            }
            if (p.getKodUrusan().getKod().equals("HTSPS")) {
                System.out.println("---Hakmilik Baru for HTSPS---");
                System.out.println("---p.getPermohonanSebelum().getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik()----:" + p.getPermohonanSebelum().getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik());
                List<HakmilikPihakBerkepentingan> hmpihak = findHakmilikPihakKodPBByIDHakmilik(p.getPermohonanSebelum().getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik(), "PM");

                for (int i = 1; i <= 2; i++) {
                    val = val + 1;
                    NumberFormat formatterBangunan = new DecimalFormat("00000");
                    String hakmilikBaruStr = str1 + formatterBangunan.format(val);
                    System.out.println("--Hakmilik Baru--for HTSPS--:" + hakmilikBaruStr);
                    Hakmilik hmilik = hakmilikDAO.findById(p.getPermohonanSebelum().getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik());
                    if (hk.getNoVersiDhde() == null) {
                        hk.setNoVersiDhde(1);
                        hakmilikDAO.saveOrUpdate(hk);
                    }
                    if (hk.getNoVersiDhke() == null) {
                        hk.setNoVersiDhke(1);
                        hakmilikDAO.saveOrUpdate(hk);
                    }
                    if (hmilik.getNoVersiDhde() == null) {
                        hmilik.setNoVersiDhde(1);
                        hakmilikDAO.saveOrUpdate(hmilik);
                    }
                    if (hmilik.getNoVersiDhke() == null) {
                        hmilik.setNoVersiDhke(1);
                        hakmilikDAO.saveOrUpdate(hmilik);
                    }
                    Hakmilik hakmilikBaru = new Hakmilik();
                    //saving hakmilik baru in hakmilik table
                    hakmilikBaru.setIdHakmilik(hakmilikBaruStr);
                    hakmilikBaru.setNoFail(hk.getNoFail());
                    if (conf.getProperty("kodNegeri").equals("05")) {
                        hakmilikBaru.setCawangan(hmilik.getCawangan());
                    }
                    if (conf.getProperty("kodNegeri").equals("04")) {
                        hakmilikBaru.setCawangan(pengguna.getKodCawangan());
                    }

                    hakmilikBaru.setDaerah(hk.getDaerah());
                    hakmilikBaru.setBandarPekanMukim(hk.getBandarPekanMukim());
                    hakmilikBaru.setSeksyen(hk.getSeksyen());
                    hakmilikBaru.setLokasi(hk.getLokasi());
                    hakmilikBaru.setKodHakmilik(hk.getKodHakmilik());
                    hakmilikBaru.setLot(hk.getLot());
                    hakmilikBaru.setNoLot(hk.getNoLot());
                    hakmilikBaru.setKategoriTanah(hk.getKategoriTanah());
                    hakmilikBaru.setKegunaanTanah(hk.getKegunaanTanah());
                    hakmilikBaru.setTarikhDaftar(new java.util.Date());
                    KodStatusHakmilik ksh = new KodStatusHakmilik();
                    ksh.setKod("D");
                    hakmilikBaru.setKodStatusHakmilik(ksh);
                    hakmilikBaru.setLotBumiputera(hk.getLotBumiputera());
                    hakmilikBaru.setMilikPersekutuan(hk.getMilikPersekutuan());
                    hakmilikBaru.setKodUnitLuas(hk.getKodUnitLuas());
                    System.out.println("--seremban--3:");
                    System.out.println("--Saving unit luas--:");
                    System.out.println("--Luas From Given IdHakmilik--:" + p.getPermohonanSebelum().getSenaraiHakmilik().get(0).getHakmilik().getLuas());
                    BigDecimal lu = p.getPermohonanSebelum().getSenaraiHakmilik().get(0).getHakmilik().getLuas().divide(new BigDecimal("2"));
                    System.out.println("--luas divided by 2--:" + lu);
                    hakmilikBaru.setLuas(lu);

                    String noHakmilik = hakmilikBaruStr.substring(hakmilikBaruStr.length() - 19, (hakmilikBaruStr.length() - 19) + 8);
                    System.out.println("--noHakmilik--:" + noHakmilik);
                    hakmilikBaru.setNoHakmilik(noHakmilik);
                    hakmilikBaru.setNoPelan("");
                    hakmilikBaru.setNoPu(hk.getNoPu());
                    hakmilikBaru.setNoLitho(hk.getNoLitho());
                    hakmilikBaru.setSekatanKepentingan(hk.getSekatanKepentingan());
                    hakmilikBaru.setSyaratNyata(hk.getSyaratNyata());
                    hakmilikBaru.setPbt(hk.getPbt());
                    hakmilikBaru.setCukai(BigDecimal.ZERO);
                    hakmilikBaru.setPbtKawasan(hk.getPbtKawasan());
                    hakmilikBaru.setPbtNoWarta(hk.getPbtNoWarta());
                    hakmilikBaru.setPbtTarikhWarta(hk.getPbtTarikhWarta());
                    hakmilikBaru.setGsaKawasan(hk.getGsaKawasan());
                    hakmilikBaru.setGsaNoWarta(hk.getGsaNoWarta());
                    hakmilikBaru.setGsaTarikhWarta(hk.getGsaTarikhWarta());
                    hakmilikBaru.setTarikhDaftarAsal(hk.getTarikhDaftarAsal());
                    System.out.println("------novVersiDhde-------::" + hakmilikBaru.getNoVersiDhde());
                    System.out.println("------novVersiDhde-------::" + hakmilikBaru.getNoVersiDhke());
                    if (hakmilikBaru.getNoVersiDhde() != null) {
                        hakmilikBaru.setNoVersiDhde(hk.getNoVersiDhde());
                    } else {
                        hakmilikBaru.setNoVersiDhde(1);
                    }
                    if (hakmilikBaru.getNoVersiDhke() != null) {
                        hakmilikBaru.setNoVersiDhke(hk.getNoVersiDhke());
                    } else {
                        hakmilikBaru.setNoVersiDhke(1);
                    }
                    //hakmilikBaru.setBadanPengurusan(hk.getBadanPengurusan());
                    System.out.println("--Seremban ID Mohon Sebelum--:" + p.getPermohonanSebelum().getIdPermohonan());
                    BadanPengurusan bdn = findBdn(p.getPermohonanSebelum().getIdPermohonan());
                    if (bdn != null) {
                        System.out.println("--BadanPengurusan--:" + bdn);
                        hakmilikBaru.setBadanPengurusan(bdn);
                    }
                    hakmilikBaru.setNoSkim(hk.getNoSkim());
                    hakmilikBaru.setTarikhLuput(hk.getTarikhLuput());
                    hakmilikBaru.setPegangan(hk.getPegangan());
                    hakmilikBaru.setTempohPegangan(hk.getTempohPegangan());
                    hakmilikBaru.setNoBangunan(hk.getNoBangunan());
                    hakmilikBaru.setKodKegunaanBangunan(hk.getKodKegunaanBangunan());
                    hakmilikBaru.setKodKategoriBangunan(hk.getKodKategoriBangunan());
                    hakmilikBaru.setNoTingkat(hk.getNoTingkat());
                    hakmilikBaru.setNoPetak(String.valueOf(val));
                    System.out.println("--seremban--6:");
                    System.out.println("--UnitySyor From Given IdHakmilik--:" + p.getPermohonanSebelum().getSenaraiHakmilik().get(0).getHakmilik().getUnitSyer());
                    BigDecimal unitSyor = p.getPermohonanSebelum().getSenaraiHakmilik().get(0).getHakmilik().getUnitSyer().divide(new BigDecimal("2"));
                    System.out.println("--luas divided by 2--:" + unitSyor);
                    hakmilikBaru.setUnitSyer(unitSyor);
                    hakmilikBaru.setJumlahUnitSyer(hk.getJumlahUnitSyer());
                    hakmilikBaru.setIdHakmilikInduk(hk.getIdHakmilikInduk());
                    hakmilikBaru.setInfoAudit(ia);
                    simpanhakmilik(hakmilikBaru);

                    /*INSERT INTO MOHON HAKMILIK*/
                    HakmilikPermohonan mohonHakmilikBaru = new HakmilikPermohonan();
                    mohonHakmilikBaru.setHakmilik(hakmilikBaru);
                    mohonHakmilikBaru.setPermohonan(p);
                    mohonHakmilikBaru.setInfoAudit(ia);
                    saveHakmilikPermohonan(mohonHakmilikBaru);

                    /*INSERT INTO HAKMILIK SEBULAM*/
                    System.out.println("--Seremban--:");
                    if (p.getPermohonanSebelum().getKodUrusan().getKod().equals("PHPC")) {
                        System.out.println("--PHPC--:");
                        //hmSblm = getHighestIDSblm();
                        //System.out.println("-------hmSblm-------"+hmSblm);
                        //System.out.println("-------hmSblm.get(0).getIdSebelum()-------"+hmSblm.get(0).getIdSebelum());
                        // int idSblm = (int) hmSblm.get(0).getIdSebelum();
                        //idSblm = idSblm + 1;
                        //System.out.println("---------idSBlm------------::" + idSblm);
                        System.out.println("---p.getPermohonanSebelum().getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik()---:" + p.getPermohonanSebelum().getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik());
                        SejarahHakmilik hMasterTitle1 = sejarahHakmilikDAO.findById(p.getPermohonanSebelum().getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik());
                        HakmilikSebelum hakmilikSebelumBaru = new HakmilikSebelum();
                        // hakmilikSebelumBaru.setIdSebelum(idSblm);
                        hakmilikSebelumBaru.setHakmilik(hakmilikBaru);
                        hakmilikSebelumBaru.setHakmilikSebelum(hMasterTitle1.getIdHakmilik());
                        hakmilikSebelumBaru.setInfoAudit(ia);
                        hakmiliksebelumlist.add(hakmilikSebelumBaru);
                        System.out.println("--UPDATING STATUS HAKMILIK--");
                        Hakmilik hmID = hakmilikDAO.findById(hMasterTitle1.getIdHakmilik());
                        KodStatusHakmilik kodSTS = kodSTSDAO.findById("B");
                        hmID.setKodStatusHakmilik(kodSTS);
                        hakmilikDAO.saveOrUpdate(hmID);
                    }

                    /*INSERT INTO MOHON HAKMILIK SEBELUM*/
                    System.out.println("--Saving in Mohon Hakmilik Sebelum--:");
                    if (kodNegeri.equalsIgnoreCase("05")) {
                        updateHakmilikSblm(hk, hakmilikBaru, ia, mohonHakmilikBaru);
                    }
                    System.out.println("---p.getPermohonanSebelum().getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik()----:" + p.getPermohonanSebelum().getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik());
                    for (int z = 0; z < hmpihak.size(); z++) {
                        HakmilikPihakBerkepentingan hmp = new HakmilikPihakBerkepentingan();
                        hmp.setHakmilik(hakmilikBaru);
                        hmp.setCawangan(hakmilikBaru.getCawangan());
                        hmp.setPihakCawangan(hmpihak.get(z).getPihakCawangan());
                        hmp.setJenis(hmpihak.get(z).getJenis());
                        hmp.setSyerPembilang(1);
                        hmp.setSyerPenyebut(hmpihak.size());
                        hmp.setPerserahan(hmpihak.get(z).getPerserahan());
                        hmp.setPerserahanKaveat(hmpihak.get(z).getPerserahanKaveat());
                        hmp.setKaveatAktif(hmpihak.get(z).getKaveatAktif());
                        hmp.setAktif(hmpihak.get(z).getAktif());
                        hmp.setPihak(hmpihak.get(z).getPihak());
                        hmp.setInfoAudit(ia);
                        lhp.add(hmp);
                        System.out.println("---added Hakmilik Pihak list to lhp---" + lhp.size());
                    }

                    if (!lhp.isEmpty()) {
                        regService.simpanHakmilikPihak(lhp);
                        System.out.println("---hakmilik baru saved in hakmilik pihak---");
                    }
                    if (!hakmiliksebelumlist.isEmpty()) {
                        System.out.println("---hakmiliksebelumlist---before saving--:" + hakmiliksebelumlist.size());
                        regService.simpanHakmilikSebelum(hakmiliksebelumlist);
                        System.out.println("---hakmilik baru saved in hakmilik Sebelum---");
                    }
                }
            } else if (p.getKodUrusan().getKod().equals("HTSPB")) {
                System.out.println("---Hakmilik Baru for HTSPB---");
                System.out.println("---p.getPermohonanSebelum().getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik()----:" + p.getPermohonanSebelum().getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik());
                List<HakmilikPihakBerkepentingan> hmpihak = findHakmilikPihakKodPBByIDHakmilik(p.getPermohonanSebelum().getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik(), "PM");

                for (int i = 0; i < hmpihak.size(); i++) {
                    val = val + 1;
                    NumberFormat formatterBangunan = new DecimalFormat("00000");
                    String hakmilikBaruStr = str1 + formatterBangunan.format(val);
                    System.out.println("--Hakmilik Baru--for HTSPB--:" + hakmilikBaruStr);
                    Hakmilik hmilik = hakmilikDAO.findById(p.getPermohonanSebelum().getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik());
                    if (hk.getNoVersiDhde() == null) {
                        hk.setNoVersiDhde(1);
                        hakmilikDAO.saveOrUpdate(hk);
                    }
                    if (hk.getNoVersiDhke() == null) {
                        hk.setNoVersiDhke(1);
                        hakmilikDAO.saveOrUpdate(hk);
                    }
                    if (hmilik.getNoVersiDhde() == null) {
                        hmilik.setNoVersiDhde(1);
                        hakmilikDAO.saveOrUpdate(hmilik);
                    }
                    if (hmilik.getNoVersiDhke() == null) {
                        hmilik.setNoVersiDhke(1);
                        hakmilikDAO.saveOrUpdate(hmilik);
                    }
                    Hakmilik hakmilikBaru = new Hakmilik();
                    //saving hakmilik baru in hakmilik table
                    hakmilikBaru.setIdHakmilik(hakmilikBaruStr);
                    hakmilikBaru.setNoFail(hk.getNoFail());
                    if (conf.getProperty("kodNegeri").equals("05")) {
                        hakmilikBaru.setCawangan(hk.getCawangan());
                    }
                    if (conf.getProperty("kodNegeri").equals("04")) {
                        hakmilikBaru.setCawangan(pengguna.getKodCawangan());
                    }

                    hakmilikBaru.setDaerah(hk.getDaerah());
                    hakmilikBaru.setBandarPekanMukim(hk.getBandarPekanMukim());
                    hakmilikBaru.setSeksyen(hk.getSeksyen());
                    hakmilikBaru.setLokasi(hk.getLokasi());
                    hakmilikBaru.setKodHakmilik(hk.getKodHakmilik());
                    hakmilikBaru.setLot(hk.getLot());
                    hakmilikBaru.setNoLot(hk.getNoLot());
                    hakmilikBaru.setKategoriTanah(hk.getKategoriTanah());
                    hakmilikBaru.setKegunaanTanah(hk.getKegunaanTanah());
                    hakmilikBaru.setTarikhDaftar(new java.util.Date());
                    KodStatusHakmilik ksh = new KodStatusHakmilik();
                    if (kodNegeri.equalsIgnoreCase("05")) {
                        ksh.setKod("D");
                    } else {
                        ksh.setKod("T");
                    }
                    hakmilikBaru.setKodStatusHakmilik(ksh);
                    hakmilikBaru.setLotBumiputera(hk.getLotBumiputera());
                    hakmilikBaru.setMilikPersekutuan(hk.getMilikPersekutuan());
                    hakmilikBaru.setKodUnitLuas(hk.getKodUnitLuas());
                    System.out.println("--seremban--3:");
                    System.out.println("--Saving unit luas--:");
                    System.out.println("--Luas From Given IdHakmilik--:" + p.getPermohonanSebelum().getSenaraiHakmilik().get(0).getHakmilik().getLuas());
                    BigDecimal lu = p.getPermohonanSebelum().getSenaraiHakmilik().get(0).getHakmilik().getLuas().divide(new BigDecimal("2"));
                    System.out.println("--luas divided by 2--:" + lu);
                    hakmilikBaru.setLuas(lu);

                    String noHakmilik = hakmilikBaruStr.substring(hakmilikBaruStr.length() - 19, (hakmilikBaruStr.length() - 19) + 8);
                    System.out.println("--noHakmilik--:" + noHakmilik);
                    hakmilikBaru.setNoHakmilik(noHakmilik);
                    hakmilikBaru.setNoPelan("");
                    hakmilikBaru.setNoPu(hk.getNoPu());
                    hakmilikBaru.setNoLitho(hk.getNoLitho());
                    hakmilikBaru.setSekatanKepentingan(hk.getSekatanKepentingan());
                    hakmilikBaru.setSyaratNyata(hk.getSyaratNyata());
                    hakmilikBaru.setPbt(hk.getPbt());
                    hakmilikBaru.setCukai(BigDecimal.ZERO);
                    hakmilikBaru.setPbtKawasan(hk.getPbtKawasan());
                    hakmilikBaru.setPbtNoWarta(hk.getPbtNoWarta());
                    hakmilikBaru.setPbtTarikhWarta(hk.getPbtTarikhWarta());
                    hakmilikBaru.setGsaKawasan(hk.getGsaKawasan());
                    hakmilikBaru.setGsaNoWarta(hk.getGsaNoWarta());
                    hakmilikBaru.setGsaTarikhWarta(hk.getGsaTarikhWarta());
                    hakmilikBaru.setTarikhDaftarAsal(hk.getTarikhDaftarAsal());
                    System.out.println("------novVersiDhde-------::" + hakmilikBaru.getNoVersiDhde());
                    System.out.println("------novVersiDhde-------::" + hakmilikBaru.getNoVersiDhke());
                    if (hakmilikBaru.getNoVersiDhde() != null) {
                        hakmilikBaru.setNoVersiDhde(hk.getNoVersiDhde());
                    } else {
                        hakmilikBaru.setNoVersiDhde(1);
                    }
                    if (hakmilikBaru.getNoVersiDhke() != null) {
                        hakmilikBaru.setNoVersiDhke(hk.getNoVersiDhke());
                    } else {
                        hakmilikBaru.setNoVersiDhke(1);
                    }
                    //hakmilikBaru.setBadanPengurusan(hk.getBadanPengurusan());
                    System.out.println("--Seremban ID Mohon Sebelum--:" + p.getPermohonanSebelum().getIdPermohonan());
                    BadanPengurusan bdn = findBdn(p.getPermohonanSebelum().getIdPermohonan());
                    if (bdn != null) {
                        System.out.println("--BadanPengurusan--:" + bdn);
                        hakmilikBaru.setBadanPengurusan(bdn);
                    }
                    hakmilikBaru.setNoSkim(hk.getNoSkim());
                    hakmilikBaru.setTarikhLuput(hk.getTarikhLuput());
                    hakmilikBaru.setPegangan(hk.getPegangan());
                    hakmilikBaru.setTempohPegangan(hk.getTempohPegangan());
                    hakmilikBaru.setNoBangunan(hk.getNoBangunan());
                    hakmilikBaru.setKodKegunaanBangunan(hk.getKodKegunaanBangunan());
                    hakmilikBaru.setKodKategoriBangunan(hk.getKodKategoriBangunan());
                    hakmilikBaru.setNoTingkat(hk.getNoTingkat());
                    hakmilikBaru.setNoPetak(String.valueOf(val));
                    System.out.println("--seremban--6:");
                    System.out.println("--UnitySyor From Given IdHakmilik--:" + p.getPermohonanSebelum().getSenaraiHakmilik().get(0).getHakmilik().getUnitSyer());
                    BigDecimal unitSyor = p.getPermohonanSebelum().getSenaraiHakmilik().get(0).getHakmilik().getUnitSyer().divide(new BigDecimal("2"));
                    System.out.println("--luas divided by 2--:" + unitSyor);
                    hakmilikBaru.setUnitSyer(unitSyor);
                    hakmilikBaru.setJumlahUnitSyer(hk.getJumlahUnitSyer());
                    hakmilikBaru.setIdHakmilikInduk(hk.getIdHakmilikInduk());
                    hakmilikBaru.setInfoAudit(ia);
                    simpanhakmilik(hakmilikBaru);

                    /*INSERT INTO MOHON HAKMILIK*/
                    HakmilikPermohonan mohonHakmilikBaru = new HakmilikPermohonan();
                    mohonHakmilikBaru.setHakmilik(hakmilikBaru);
                    mohonHakmilikBaru.setPermohonan(p);
                    mohonHakmilikBaru.setInfoAudit(ia);
                    saveHakmilikPermohonan(mohonHakmilikBaru);

                    /*INSERT INTO HAKMILIK SEBELUM*/
                    System.out.println("--Seremban--:");
                    if (p.getPermohonanSebelum().getKodUrusan().getKod().equals("PHPC")) {
                        System.out.println("--PHPC--:");
                        //hmSblm = getHighestIDSblm();
                        //System.out.println("-------hmSblm-------"+hmSblm);
                        //System.out.println("-------hmSblm.get(0).getIdSebelum()-------"+hmSblm.get(0).getIdSebelum());
                        // int idSblm = (int) hmSblm.get(0).getIdSebelum();
                        //idSblm = idSblm + 1;
                        //System.out.println("---------idSBlm------------::" + idSblm);
                        System.out.println("---p.getPermohonanSebelum().getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik()---:" + p.getPermohonanSebelum().getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik());
                        SejarahHakmilik hMasterTitle1 = sejarahHakmilikDAO.findById(p.getPermohonanSebelum().getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik());
                        HakmilikSebelum hakmilikSebelumBaru = new HakmilikSebelum();
                        // hakmilikSebelumBaru.setIdSebelum(idSblm);
                        hakmilikSebelumBaru.setHakmilik(hakmilikBaru);
                        hakmilikSebelumBaru.setHakmilikSebelum(hMasterTitle1.getIdHakmilik());
                        hakmilikSebelumBaru.setInfoAudit(ia);
                        hakmiliksebelumlist.add(hakmilikSebelumBaru);
                        System.out.println("--UPDATING STATUS HAKMILIK--");
                        Hakmilik hmID = hakmilikDAO.findById(hMasterTitle1.getIdHakmilik());
                        KodStatusHakmilik kodSTS = kodSTSDAO.findById("B");
                        hmID.setKodStatusHakmilik(kodSTS);
                        hakmilikDAO.saveOrUpdate(hmID);
                    }

                    /*INSERT INTO MOHON HAKMILIK SEBELUM*/
                    System.out.println("--Saving in Mohon Hakmilik Sebelum--:");
                    if (kodNegeri.equalsIgnoreCase("05")) {
                        updateHakmilikSblm(hk, hakmilikBaru, ia, mohonHakmilikBaru);
                    }
                    System.out.println("---p.getPermohonanSebelum().getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik()----:" + p.getPermohonanSebelum().getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik());
                    for (int z = 0; z < 1; z++) {
                        HakmilikPihakBerkepentingan hmp = new HakmilikPihakBerkepentingan();
                        hmp.setHakmilik(hakmilikBaru);
                        hmp.setCawangan(hakmilikBaru.getCawangan());
                        hmp.setPihakCawangan(hmpihak.get(i).getPihakCawangan());
                        hmp.setJenis(hmpihak.get(i).getJenis());
                        hmp.setSyerPembilang(1);
                        hmp.setSyerPenyebut(1);
                        hmp.setPerserahan(hmpihak.get(i).getPerserahan());
                        hmp.setPerserahanKaveat(hmpihak.get(i).getPerserahanKaveat());
                        hmp.setKaveatAktif(hmpihak.get(i).getKaveatAktif());
                        hmp.setAktif(hmpihak.get(i).getAktif());
                        hmp.setPihak(hmpihak.get(i).getPihak());
                        hmp.setInfoAudit(ia);
                        lhp.add(hmp);
                        System.out.println("---added Hakmilik Pihak list to lhp---" + lhp.size());
                    }

                    if (!lhp.isEmpty()) {
                        regService.simpanHakmilikPihak(lhp);
                        System.out.println("---hakmilik baru saved in hakmilik pihak---");
                    }
                    if (!hakmiliksebelumlist.isEmpty()) {
                        System.out.println("---hakmiliksebelumlist---before saving--:" + hakmiliksebelumlist.size());
                        regService.simpanHakmilikSebelum(hakmiliksebelumlist);
                        System.out.println("---hakmilik baru saved in hakmilik Sebelum---");
                    }
                }
            } else {
                for (int i = 1; i <= a; i++) {
                    val = val + 1;
                    NumberFormat formatterBangunan = new DecimalFormat("00000");
                    String hakmilikBaruStr = str1 + formatterBangunan.format(val);
                    System.out.println("--Hakmilik Baru--for PHPC/PHPP--:" + hakmilikBaruStr);
                    Hakmilik hmilik = hakmilikDAO.findById(p.getPermohonanSebelum().getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik());
                    if (hmilik.getNoVersiDhde() == null) {
                        hmilik.setNoVersiDhde(1);
                        hakmilikDAO.saveOrUpdate(hmilik);
                    }
                    if (hmilik.getNoVersiDhke() == null) {
                        hmilik.setNoVersiDhke(1);
                        hakmilikDAO.saveOrUpdate(hmilik);
                    }
                    //saving hakmilik baru in hakmilik table
                    Hakmilik hakmilikBaru = new Hakmilik();
                    hakmilikBaru.setIdHakmilik(hakmilikBaruStr);
                    hakmilikBaru.setNoFail(hk.getNoFail());
                    if (conf.getProperty("kodNegeri").equals("05")) {
                        hakmilikBaru.setCawangan(hk.getCawangan());
                    }
                    if (conf.getProperty("kodNegeri").equals("04")) {
                        hakmilikBaru.setCawangan(pengguna.getKodCawangan());
                    }

                    hakmilikBaru.setDaerah(hk.getDaerah());
                    hakmilikBaru.setBandarPekanMukim(hk.getBandarPekanMukim());
                    hakmilikBaru.setSeksyen(hk.getSeksyen());
                    hakmilikBaru.setLokasi(hk.getLokasi());
                    hakmilikBaru.setKodHakmilik(hk.getKodHakmilik());
                    hakmilikBaru.setLot(hk.getLot());
                    hakmilikBaru.setNoLot(hk.getNoLot());
                    hakmilikBaru.setKategoriTanah(hk.getKategoriTanah());
                    hakmilikBaru.setKegunaanTanah(hk.getKegunaanTanah());
                    hakmilikBaru.setTarikhDaftar(new java.util.Date());
                    KodStatusHakmilik ksh = new KodStatusHakmilik();
                    if (kodNegeri.equalsIgnoreCase("05")) {
                        ksh.setKod("D");
                    } else {
                        ksh.setKod("T");
                    }
                    hakmilikBaru.setKodStatusHakmilik(ksh);
                    hakmilikBaru.setLotBumiputera(hk.getLotBumiputera());
                    hakmilikBaru.setMilikPersekutuan(hk.getMilikPersekutuan());
                    hakmilikBaru.setKodUnitLuas(hk.getKodUnitLuas());
                    if (kodNegeri.equalsIgnoreCase("04")) {
                        System.out.println("--melaka--3:");
                        System.out.println("--Saving unit luas--:");
                        if (p.getKodUrusan().getKod().equals("PHPP")) {
                            BigDecimal totalluas = BigDecimal.ZERO;
                            List<HakmilikPermohonan> hkp = p.getSenaraiHakmilik();
                            for (HakmilikPermohonan hakmilikPermohonan : hkp) {
                                System.out.println("--hkp size--" + hkp.size());
                                totalluas = totalluas.add(hakmilikPermohonan.getHakmilik().getLuas());
                            }
                            System.out.println("--totalluas--:" + totalluas);
                            hakmilikBaru.setLuas(totalluas);
                        } else {
                            System.out.println("--melaka--4:");
                            System.out.println("--Luas From Given IdHakmilik--:" + p.getSenaraiHakmilik().get(0).getHakmilik().getLuas());
                            BigDecimal lu = p.getSenaraiHakmilik().get(0).getHakmilik().getLuas().divide(new BigDecimal("2"));
                            System.out.println("--luas divided by 2--:" + lu);
                            hakmilikBaru.setLuas(lu);
                        }
                    } else {
                        System.out.println("--seremban--3:");
                        System.out.println("--Saving unit luas--:");
                        if (p.getPermohonanSebelum().getKodUrusan().getKod().equals("PHPP")) {
                            List<HakmilikPermohonan> hkp = p.getPermohonanSebelum().getSenaraiHakmilik();
                            BigDecimal totalluas = BigDecimal.ZERO;
                            for (HakmilikPermohonan hakmilikPermohonan : hkp) {
                                System.out.println("--hkp size--" + hkp.size());
                                totalluas = totalluas.add(hakmilikPermohonan.getHakmilik().getLuas());
                            }
                            System.out.println("--totalluas--:" + totalluas);
                            hakmilikBaru.setLuas(totalluas);
                        } else if (p.getPermohonanSebelum().getKodUrusan().getKod().equals("PHPC")) {
                            System.out.println("--seremban--4:");
                            System.out.println("--Luas From Given IdHakmilik--:" + p.getPermohonanSebelum().getSenaraiHakmilik().get(0).getHakmilik().getLuas());
                            BigDecimal lu = p.getPermohonanSebelum().getSenaraiHakmilik().get(0).getHakmilik().getLuas().divide(new BigDecimal("2"));
                            System.out.println("--luas divided by 2--:" + lu);
                            hakmilikBaru.setLuas(lu);
                        }
                    }

                    String noHakmilik = hakmilikBaruStr.substring(hakmilikBaruStr.length() - 19, (hakmilikBaruStr.length() - 19) + 8);
                    System.out.println("--noHakmilik--:" + noHakmilik);
                    hakmilikBaru.setNoHakmilik(noHakmilik);
                    hakmilikBaru.setNoPelan("");
                    hakmilikBaru.setNoPu(hk.getNoPu());
                    hakmilikBaru.setNoLitho(hk.getNoLitho());
                    hakmilikBaru.setSekatanKepentingan(hk.getSekatanKepentingan());
                    hakmilikBaru.setSyaratNyata(hk.getSyaratNyata());
                    hakmilikBaru.setPbt(hk.getPbt());
                    hakmilikBaru.setCukai(BigDecimal.ZERO);
                    hakmilikBaru.setPbtKawasan(hk.getPbtKawasan());
                    hakmilikBaru.setPbtNoWarta(hk.getPbtNoWarta());
                    hakmilikBaru.setPbtTarikhWarta(hk.getPbtTarikhWarta());
                    hakmilikBaru.setGsaKawasan(hk.getGsaKawasan());
                    hakmilikBaru.setGsaNoWarta(hk.getGsaNoWarta());
                    hakmilikBaru.setGsaTarikhWarta(hk.getGsaTarikhWarta());
                    hakmilikBaru.setTarikhDaftarAsal(hk.getTarikhDaftarAsal());
                    hakmilikBaru.setNoVersiDhde(hk.getNoVersiDhde());
                    hakmilikBaru.setNoVersiDhke(hk.getNoVersiDhke());
                    //hakmilikBaru.setBadanPengurusan(hk.getBadanPengurusan());
                    if (kodNegeri.equalsIgnoreCase("04")) {
                        System.out.println("--Melaka ID Mohon--:" + p.getIdPermohonan());
                        BadanPengurusan bdn = findBdn(p.getIdPermohonan());
                        if (bdn != null) {
                            System.out.println("--BadanPengurusan--:" + bdn);
                            hakmilikBaru.setBadanPengurusan(bdn);
                        }
                    } else {
                        System.out.println("--Seremban ID Mohon Sebelum--:" + p.getPermohonanSebelum().getIdPermohonan());
                        BadanPengurusan bdn = findBdn(p.getPermohonanSebelum().getIdPermohonan());
                        if (bdn != null) {
                            System.out.println("--BadanPengurusan--:" + bdn);
                            hakmilikBaru.setBadanPengurusan(bdn);
                        }
                    }
                    hakmilikBaru.setNoSkim(hk.getNoSkim());
                    hakmilikBaru.setTarikhLuput(hk.getTarikhLuput());
                    hakmilikBaru.setPegangan(hk.getPegangan());
                    hakmilikBaru.setTempohPegangan(hk.getTempohPegangan());
                    hakmilikBaru.setNoBangunan(hk.getNoBangunan());
                    hakmilikBaru.setKodKegunaanBangunan(hk.getKodKegunaanBangunan());
                    hakmilikBaru.setKodKategoriBangunan(hk.getKodKategoriBangunan());
                    hakmilikBaru.setNoTingkat(hk.getNoTingkat());
                    hakmilikBaru.setNoPetak(String.valueOf(val));
                    if (kodNegeri.equalsIgnoreCase("04")) {
                        System.out.println("--melaka--5:");
                        System.out.println("--Saving unit Syor--:");
                        if (p.getKodUrusan().getKod().equals("PHPP")) {
                            BigDecimal totalunit = BigDecimal.ZERO;
                            List<HakmilikPermohonan> hkp = p.getSenaraiHakmilik();
                            for (HakmilikPermohonan hakmilikPermohonan : hkp) {
                                System.out.println("--hkp size--" + hkp.size());
                                totalunit = totalunit.add(hakmilikPermohonan.getHakmilik().getUnitSyer());
                            }
                            System.out.println("--totalunit--:" + totalunit);
                            hakmilikBaru.setUnitSyer(totalunit);
                        } else {
                            System.out.println("--melaka--6:");
                            System.out.println("--UnitySyor From Given IdHakmilik--:" + p.getSenaraiHakmilik().get(0).getHakmilik().getUnitSyer());
                            BigDecimal unitSyor = p.getSenaraiHakmilik().get(0).getHakmilik().getUnitSyer().divide(new BigDecimal("2"));
                            System.out.println("--luas divided by 2--:" + unitSyor);
                            hakmilikBaru.setUnitSyer(unitSyor);
                        }
                    } else {
                        System.out.println("--seremban--5:");
                        System.out.println("--Saving unit syor--:");
                        if (p.getPermohonanSebelum().getKodUrusan().getKod().equals("PHPP")) {
                            BigDecimal totalunit = BigDecimal.ZERO;
                            List<HakmilikPermohonan> hkp = p.getPermohonanSebelum().getSenaraiHakmilik();
                            for (HakmilikPermohonan hakmilikPermohonan : hkp) {
                                System.out.println("--hkp size--" + hkp.size());
                                totalunit = totalunit.add(hakmilikPermohonan.getHakmilik().getUnitSyer());
                            }
                            System.out.println("--totalunit--:" + totalunit);
                            hakmilikBaru.setUnitSyer(totalunit);
                        } else {
                            System.out.println("--seremban--6:");
                            System.out.println("--UnitySyor From Given IdHakmilik--:" + p.getPermohonanSebelum().getSenaraiHakmilik().get(0).getHakmilik().getUnitSyer());
                            BigDecimal unitSyor = p.getPermohonanSebelum().getSenaraiHakmilik().get(0).getHakmilik().getUnitSyer().divide(new BigDecimal("2"));
                            System.out.println("--luas divided by 2--:" + unitSyor);
                            hakmilikBaru.setUnitSyer(unitSyor);
                        }
                    }
                    hakmilikBaru.setJumlahUnitSyer(hk.getJumlahUnitSyer());
                    hakmilikBaru.setIdHakmilikInduk(hk.getIdHakmilikInduk());
                    hakmilikBaru.setInfoAudit(ia);
                    simpanhakmilik(hakmilikBaru);

                    /*INSERT INTO MOHON HAKMILIK*/
                    HakmilikPermohonan mohonHakmilikBaru = new HakmilikPermohonan();
                    mohonHakmilikBaru.setHakmilik(hakmilikBaru);
                    mohonHakmilikBaru.setPermohonan(p);
                    mohonHakmilikBaru.setInfoAudit(ia);
                    saveHakmilikPermohonan(mohonHakmilikBaru);

                    /*INSERT INTO HAKMILIK SEBULAM*/
                    if (kodNegeri.equalsIgnoreCase("04")) {
                        System.out.println("--Melaka--:");
                        if (p.getKodUrusan().getKod().equals("PHPC")) {
                            System.out.println("--PHPC--:");
                            SejarahHakmilik hMasterTitle1 = sejarahHakmilikDAO.findById(p.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik());
                            HakmilikSebelum hakmilikSebelumBaru = new HakmilikSebelum();
                            hakmilikSebelumBaru.setHakmilik(hakmilikBaru);
                            hakmilikSebelumBaru.setHakmilikSebelum(hMasterTitle1.getIdHakmilik());
                            hakmilikSebelumBaru.setInfoAudit(ia);
                            hakmiliksebelumlist.add(hakmilikSebelumBaru);
                        } else {
                            System.out.println("--PHPP--:");
                            List<Hakmilik> senaraihak = new ArrayList<Hakmilik>();
                            List<HakmilikPermohonan> hk1 = p.getSenaraiHakmilik();
                            for (HakmilikPermohonan hakmilikPermohonan : hk1) {
                                System.out.println("--hk1 size--:" + hk1.size());
                                System.out.println("--hakmilikPermohonan--:" + hakmilikPermohonan.getHakmilik().getIdHakmilik());
                                SejarahHakmilik hMasterTitle1 = sejarahHakmilikDAO.findById(hakmilikPermohonan.getHakmilik().getIdHakmilik());
                                System.out.println("--hMasterTitle1--:" + hMasterTitle1);
                                HakmilikSebelum hakmilikSebelumBaru = new HakmilikSebelum();
                                hakmilikSebelumBaru.setHakmilik(hakmilikBaru);
                                hakmilikSebelumBaru.setHakmilikSebelum(hMasterTitle1.getIdHakmilik());
                                hakmilikSebelumBaru.setInfoAudit(ia);
                                hakmiliksebelumlist.add(hakmilikSebelumBaru);
                            }
                        }
                    } else {
                        System.out.println("--Seremban--:");
                        if (p.getPermohonanSebelum().getKodUrusan().getKod().equals("PHPC")) {
                            System.out.println("--PHPC--:");
                            //hmSblm = getHighestIDSblm();
                            //System.out.println("-------hmSblm-------"+hmSblm);
                            //System.out.println("-------hmSblm.get(0).getIdSebelum()-------"+hmSblm.get(0).getIdSebelum());
                            // int idSblm = (int) hmSblm.get(0).getIdSebelum();
                            //idSblm = idSblm + 1;
                            //System.out.println("---------idSBlm------------::" + idSblm);
                            System.out.println("---p.getPermohonanSebelum().getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik()---:" + p.getPermohonanSebelum().getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik());
                            SejarahHakmilik hMasterTitle1 = sejarahHakmilikDAO.findById(p.getPermohonanSebelum().getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik());
                            HakmilikSebelum hakmilikSebelumBaru = new HakmilikSebelum();
                            // hakmilikSebelumBaru.setIdSebelum(idSblm);
                            hakmilikSebelumBaru.setHakmilik(hakmilikBaru);
                            hakmilikSebelumBaru.setHakmilikSebelum(hMasterTitle1.getIdHakmilik());
                            hakmilikSebelumBaru.setInfoAudit(ia);
                            hakmiliksebelumlist.add(hakmilikSebelumBaru);
                        } else {
                            System.out.println("--PHPP--:");
                            List<Hakmilik> senaraihak = new ArrayList<Hakmilik>();
                            List<HakmilikPermohonan> hk1 = p.getPermohonanSebelum().getSenaraiHakmilik();
                            for (HakmilikPermohonan hakmilikPermohonan : hk1) {
                                System.out.println("--hk1 size--:" + hk1.size());
                                System.out.println("--hakmilikPermohonan--:" + hakmilikPermohonan.getHakmilik().getIdHakmilik());
                                SejarahHakmilik hMasterTitle1 = sejarahHakmilikDAO.findById(hakmilikPermohonan.getHakmilik().getIdHakmilik());
                                System.out.println("--hMasterTitle1--:" + hMasterTitle1);
                                HakmilikSebelum hakmilikSebelumBaru = new HakmilikSebelum();
                                hakmilikSebelumBaru.setHakmilik(hakmilikBaru);
                                System.out.println("--hakmilik adding to hakmiliksebelumlist--:" + hMasterTitle1.getIdHakmilik());
                                hakmilikSebelumBaru.setHakmilikSebelum(hMasterTitle1.getIdHakmilik());
                                hakmilikSebelumBaru.setInfoAudit(ia);
                                hakmiliksebelumlist.add(hakmilikSebelumBaru);
                                System.out.println("--hakmiliksebelumlist size in side loop--:" + hakmiliksebelumlist.size());
                                System.out.println("--UPDATING STATUS HAKMILIK--");
                                Hakmilik hmID = hakmilikDAO.findById(hMasterTitle1.getIdHakmilik());
                                KodStatusHakmilik kodSTS = kodSTSDAO.findById("B");
                                hmID.setKodStatusHakmilik(kodSTS);
                                hakmilikDAO.saveOrUpdate(hmID);
                            }
                            System.out.println("--hakmiliksebelumlist size out side loop--:" + hakmiliksebelumlist.size());
                        }
                    }


                    /*INSERT INTO MOHON HAKMILIK SEBELUM*/
                    System.out.println("--Saving in Mohon Hakmilik Sebelum--:");
                    if (kodNegeri.equalsIgnoreCase("05")) {
                        updateHakmilikSblm(hk, hakmilikBaru, ia, mohonHakmilikBaru);
                    }
                    System.out.println("---p.getPermohonanSebelum().getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik()----:" + p.getPermohonanSebelum().getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik());
                    List<HakmilikPihakBerkepentingan> hmpihak = findHakmilikPihakByIDHakmilik(p.getPermohonanSebelum().getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik());
                    for (int z = 0; z < hakmiliksebelumlist.size(); z++) {
                        HakmilikSebelum hmSblm = hakmiliksebelumlist.get(z);
                        HakmilikPihakBerkepentingan hmp = new HakmilikPihakBerkepentingan();
                        hmp.setHakmilik(hakmilikBaru);
                        hmp.setCawangan(hakmilikBaru.getCawangan());
                        hmp.setPihakCawangan(hmpihak.get(0).getPihakCawangan());
                        hmp.setJenis(hmpihak.get(0).getJenis());
                        hmp.setSyerPembilang(hmpihak.get(0).getSyerPembilang());
                        hmp.setSyerPenyebut(hmpihak.get(0).getSyerPenyebut());
                        hmp.setPerserahan(hmpihak.get(0).getPerserahan());
                        hmp.setPerserahanKaveat(hmpihak.get(0).getPerserahanKaveat());
                        hmp.setKaveatAktif(hmpihak.get(0).getKaveatAktif());
                        hmp.setAktif(hmpihak.get(0).getAktif());
                        hmp.setPihak(hmpihak.get(0).getPihak());
                        hmp.setInfoAudit(ia);
                        lhp.add(hmp);
                        System.out.println("---added Hakmilik Pihak list to lhp---" + lhp.size());
                    }

                    /*INSERT INTO HAKMILIK PIHAK*/
                    List<HakmilikPihakBerkepentingan> senaraiHakmilikPihak = hpkService.findHakmilikAllPihakActiveByHakmilik(hk);
                    System.out.println("---Hakmilik Pihak---" + senaraiHakmilikPihak.size());
                    for (HakmilikPihakBerkepentingan hpk : senaraiHakmilikPihak) {
                        if (hpk == null || hpk.getAktif() == 'T') {
                            continue;
                        } else {
                            System.out.println("---Saving in Hakmilik Pihak");
                            System.out.println("---creating new Hakmilik Pihak object---");
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
                            //hpkService.save(hpb);
                            lhp.add(hpb);
                            System.out.println("---added Hakmilik Pihak list to lhp---" + lhp.size());
                        }
                    }

                    /*
                     if (!listHakmilikBaru.isEmpty()) {
                     regService.simpanHakmilikList(listHakmilikBaru);
                     System.out.println("---hakmilik baru saved---:");
                     }
                     if (!listMohonHakmilikBaru.isEmpty()) {
                     regService.simpanMohonHakmilikList(listMohonHakmilikBaru);
                     System.out.println("---hakmilik baru saved in mohon hakmilik---:");
                     }*/
                    if (!lhp.isEmpty()) {
                        regService.simpanHakmilikPihak(lhp);
                        System.out.println("---hakmilik baru saved in hakmilik pihak---");
                    }
                    if (!hakmiliksebelumlist.isEmpty()) {
                        System.out.println("---hakmiliksebelumlist---before saving--:" + hakmiliksebelumlist.size());
                        regService.simpanHakmilikSebelum(hakmiliksebelumlist);
                        System.out.println("---hakmilik baru saved in hakmilik Sebelum---");
                    }
                }
            }
        }
    }

    public HakmilikPermohonan findByIdHakmilikIdPermohonan(String idPermohonan, String idHakmilik) {
        String query = "Select p FROM etanah.model.HakmilikPermohonan p WHERE p.permohonan.idPermohonan = :idPermohonan" + " and p.hakmilik.idHakmilik = :idHakmilik";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("idHakmilik", idHakmilik);
        return (HakmilikPermohonan) q.uniqueResult();
    }

    public LaporanTanah findLaporanTanahByIdMH(String idPermohonan, long idMH) {
        String query = "SELECT p FROM etanah.model.LaporanTanah p where p.hakmilikPermohonan.id = :idMH" + " and p.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setLong("idMH", idMH);
        return (LaporanTanah) q.uniqueResult();
    }

    public PermohonanStrata findIDByidMH(String idPermohonan, long idMH) {
        String query = "SELECT b FROM etanah.model.PermohonanStrata b where b.hakmilikPermohonan.id = :idMH" + " and b.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setLong("idMH", idMH);
        return (PermohonanStrata) q.uniqueResult();
    }

    public HakmilikPermohonan findHKByIdMh(String idPermohonan, long idMH) {
        String query = "Select p FROM etanah.model.HakmilikPermohonan p WHERE p.permohonan.idPermohonan = :idPermohonan" + " and p.id = :idMH";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setLong("idMH", idMH);
        return (HakmilikPermohonan) q.uniqueResult();
    }

    public Hakmilik hakmilikBaru(String MasterTitle, Hakmilik hakmiliklama, String s1, BigDecimal luasPetak, String noPetak, BigDecimal unitSyer, InfoAudit ia) {
        Hakmilik hakmilikBaru = new Hakmilik();
        Hakmilik hMasterTitle = hakmilikDAO.findById(MasterTitle);
        hakmilikBaru.setIdHakmilik(s1);
        hakmilikBaru.setNoFail(hMasterTitle.getNoFail());
        hakmilikBaru.setCawangan(hakmiliklama.getCawangan());
        hakmilikBaru.setDaerah(hMasterTitle.getDaerah());
        hakmilikBaru.setBandarPekanMukim(hakmiliklama.getBandarPekanMukim());
        hakmilikBaru.setSeksyen(hMasterTitle.getSeksyen());
        hakmilikBaru.setKodKegunaanBangunan(hakmiliklama.getKodKegunaanBangunan());
        hakmilikBaru.setKodKategoriBangunan(hakmiliklama.getKodKategoriBangunan());
        hakmilikBaru.setLokasi(hMasterTitle.getLokasi());
        hakmilikBaru.setKodHakmilik(hMasterTitle.getKodHakmilik());
        hakmilikBaru.setLot(hMasterTitle.getLot());
        hakmilikBaru.setNoLot(hMasterTitle.getNoLot());
        hakmilikBaru.setKategoriTanah(hakmiliklama.getKategoriTanah());
        hakmilikBaru.setKegunaanTanah(hakmiliklama.getKegunaanTanah());
        hakmilikBaru.setTarikhDaftar(new java.util.Date());
        KodStatusHakmilik ksh = new KodStatusHakmilik();
        ksh.setKod("T");
        hakmilikBaru.setKodStatusHakmilik(ksh);
        hakmilikBaru.setLotBumiputera(hakmiliklama.getLotBumiputera());
        hakmilikBaru.setMilikPersekutuan(hMasterTitle.getMilikPersekutuan());
        hakmilikBaru.setKodUnitLuas(hakmiliklama.getKodUnitLuas());
        //hakmilikBaru.setLuas(hMasterTitle.getLuas());
        hakmilikBaru.setLuas(luasPetak);

        String noHakmilik = s1.substring(s1.length() - 19, (s1.length() - 19) + 8);

        hakmilikBaru.setNoHakmilik(noHakmilik);

        hakmilikBaru.setNoPelan("");
        hakmilikBaru.setNoPu(hMasterTitle.getNoPu());
        hakmilikBaru.setNoLitho(hMasterTitle.getNoLitho());
        hakmilikBaru.setSekatanKepentingan(hMasterTitle.getSekatanKepentingan());
        hakmilikBaru.setSyaratNyata(hMasterTitle.getSyaratNyata());
        hakmilikBaru.setPbt(hMasterTitle.getPbt());
        hakmilikBaru.setCukai(BigDecimal.ZERO); //added by murali @MLK PAT 26-09-2012
        hakmilikBaru.setPbtKawasan(hMasterTitle.getPbtKawasan());
        hakmilikBaru.setPbtNoWarta(hMasterTitle.getPbtNoWarta());
        hakmilikBaru.setPbtTarikhWarta(hMasterTitle.getPbtTarikhWarta());
        hakmilikBaru.setGsaKawasan(hMasterTitle.getGsaKawasan());
        hakmilikBaru.setGsaNoWarta(hMasterTitle.getGsaNoWarta());
        hakmilikBaru.setGsaTarikhWarta(hMasterTitle.getGsaTarikhWarta());
        hakmilikBaru.setTarikhDaftarAsal(hMasterTitle.getTarikhDaftarAsal());

        hakmilikBaru.setBadanPengurusan(hakmiliklama.getBadanPengurusan());

        hakmilikBaru.setTarikhLuput(hMasterTitle.getTarikhLuput());
        hakmilikBaru.setPegangan(hMasterTitle.getPegangan());
        hakmilikBaru.setTempohPegangan(hMasterTitle.getTempohPegangan());
        hakmilikBaru.setNoBangunan(hakmiliklama.getNoBangunan());
        // added by zadirul to set kodKategoriBangunan from table Mohon_Bngn
        hakmilikBaru.setKodKategoriBangunan(hakmiliklama.getKodKategoriBangunan());
        hakmilikBaru.setNoTingkat(hakmiliklama.getNoTingkat());
        hakmilikBaru.setNoPetak(noPetak);
        hakmilikBaru.setUnitSyer(unitSyer);
        hakmilikBaru.setJumlahUnitSyer(hakmiliklama.getJumlahUnitSyer());

        hakmilikBaru.setKodKegunaanBangunan(hakmiliklama.getKodKegunaanBangunan());

        hakmilikBaru.setIdHakmilikInduk(hMasterTitle.getIdHakmilik());
        hakmilikBaru.setInfoAudit(ia);
        return hakmilikBaru;
    }

    public Hakmilik getMaxbyBngnAndInduk(String idHakmilikInduk, String noBngn) {
        String query = "select ha from etanah.model.Hakmilik ha where ha.idHakmilikInduk = :idHakmilikInduk and ha.noBangunan = :noBngn order by LPAD(lower(ha.noPetak),10,0) desc";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idHakmilikInduk", idHakmilikInduk);
        q.setString("noBngn", noBngn);

        return (Hakmilik) q.list().get(0);
    }
    
    public Hakmilik getMaxPetakbyKatgBngnAndInduk(String idHakmilikInduk, String katgBngn) {
        String query = "select max(hm.noPetak+0) from etanah.model.Hakmilik hm where hm.idHakmilikInduk = :idHakmilikInduk and hm.kodKategoriBangunan.kod = :katgBngn order by LPAD(lower(hm.noPetak),10,0) desc";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idHakmilikInduk", idHakmilikInduk);
        q.setString("katgBngn", katgBngn);

        return (Hakmilik) q.list().get(0);
    }

    @Transactional
    public void updateHakmilikSblm(Hakmilik lama, Hakmilik baru, InfoAudit ia, HakmilikPermohonan hp) {
        HakmilikSebelumPermohonan hsb = new HakmilikSebelumPermohonan();
        SejarahHakmilik sh = sejarahHakmilikDAO.findById(lama.getIdHakmilik());
        hsb.setHakmilik(baru);
        hsb.setHakmilikSejarah(sh.getIdHakmilik());
        hsb.setCawangan(baru.getCawangan());
        hsb.setInfoAudit(ia);
        hsb.setPermohonan(hp.getPermohonan());
        hsb.setHakmilikPermohonan(hp);
        hakmilikSebelumPermohonanDAO.saveOrUpdate(hsb);
    }

    public BadanPengurusan findBdnByIdBdn(long idBadan) {
        String query = "SELECT b FROM etanah.model.strata.BadanPengurusan b where b.idBadan.idBadan = :idBadan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idBadan", idBadan);
        return (BadanPengurusan) q.uniqueResult();
    }

    @Transactional
    public void simpanPihakAlamatTamb(PihakAlamatTamb pihakATB) {
        pihakAlamatDAO.saveOrUpdate(pihakATB);
    }

    @Transactional
    public void deletePihakATBByIdPihak(Long idPihak) {
        String query = "DELETE FROM etanah.model.PihakAlamatTamb p WHERE p.pihak.idPihak = :idPihak";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idPihak", idPihak);
        q.executeUpdate();
    }

    @Transactional
    public void deletedrafsijil(String idPermohonan) {
        String query = "DELETE FROM etanah.model.strata.BadanPengurusan p WHERE p.permohonan.idPermohonan = :idPermohonan";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.executeUpdate();
    }

    @Transactional
    public void deletedrafsijilpihak(Long idPihak) {
        String query = "DELETE FROM etanah.model.Pihak p WHERE p.idPihak = :idPihak";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idPihak", idPihak);
        q.executeUpdate();
    }

    public Permohonan findIDSblmByKodUrusan(String idPermohonan, String kod) {
        String query = "SELECT p FROM etanah.model.Permohonan p where p.permohonanSebelum.idPermohonan = :idPermohonan and p.kodUrusan.kod = :kod";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("kod", kod);
//        return (Permohonan) q.uniqueResult();
        if (q.list().size() > 1) {
            return (Permohonan) q.list().get(0);
        } else {
            return (Permohonan) q.uniqueResult();
        }
    }

    public PermohonanSkim findIDSkimByIDMH(long idMH) {
        String query = "SELECT m FROM etanah.model.PermohonanSkim m WHERE m.hakmilikPermohonan.id = :idMH";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idMH", idMH);
        return (PermohonanSkim) q.uniqueResult();
    }

    public PermohonanKertas findIDKertasByIDMohon(String idPermohonan) {
        String query = "SELECT m FROM etanah.model.PermohonanKertas m WHERE m.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setParameter("idPermohonan", idPermohonan);
        return (PermohonanKertas) q.uniqueResult();
    }

    public PermohonanKertas findIDKertasByIDMohonKod(String idPermohonan, String kod) {
        String query = "SELECT m FROM etanah.model.PermohonanKertas m WHERE m.permohonan.idPermohonan = :idPermohonan "
                + "and m.kodDokumen.kod = :kod ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setParameter("idPermohonan", idPermohonan);
        q.setParameter("kod", kod);
        return (PermohonanKertas) q.uniqueResult();
    }

    public List<PermohonanKertasKandungan> findKandunganByIDKertas(long idKertas) {
        String query = "SELECT m FROM etanah.model.PermohonanKertasKandungan m WHERE m.kertas.idKertas = :idKertas ORDER BY m.bil DESC";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idKertas", idKertas);
        return q.list();
    }

    public List<Hakmilik> getIdBdnUrus(String idHakmilikInduk) {
        String query = "SELECT m FROM etanah.model.Hakmilik m WHERE m.idHakmilikInduk = :idHakmilikInduk";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setParameter("idHakmilikInduk", idHakmilikInduk);
        return q.list();
    }

    public BadanPengurusan getidPihakByIdBdn(long idBdn) {
        String query = "SELECT m FROM etanah.model.strata.BadanPengurusan m WHERE m.idBadan = :idBdn";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idBdn", idBdn);
        return (BadanPengurusan) q.uniqueResult();
    }

    public Permohonan getidMohonHTBByIDSblm(String idPermohonan, String kod) {
        String query = "SELECT m FROM etanah.model.Permohonan m WHERE m.permohonanSebelum.idPermohonan = :idPermohonan AND m.kodUrusan.kod = :kod"
                + " and NVL(m.keputusan.kod,'0') = 'D' order by m.idPermohonan desc ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setParameter("idPermohonan", idPermohonan);
        q.setParameter("kod", kod);

        if (q.list().size() > 1) {
            return (Permohonan) q.list().get(0);
        } else {
            return (Permohonan) q.uniqueResult();
        }
    }

    public KodStatusPermohonan getkodKeputusan(String status) {
        String query = "SELECT m FROM etanah.model.KodStatusPermohonan m WHERE m.kod = :status";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setParameter("status", status);
        return (KodStatusPermohonan) q.uniqueResult();
    }

    public Pihak getMaklumatByIDPihak(long idPihak) {
        String query = "SELECT m FROM etanah.model.Pihak m WHERE m.idPihak = :idPihak";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idPihak", idPihak);
        return (Pihak) q.uniqueResult();
    }

    public List<KodHartaBersama> findDistinctHartaBersamaByidSkim(long idSkim) {
        String query = "SELECT DISTINCT b.jenisHartaBersama FROM etanah.model.PermohonanHartaBersama b where b.idSkim.idSkim =:idSkim";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idSkim", idSkim);
        return q.list();
    }

    public Integrasi getInteg(String kod, String idAliranHantar) {

        String query = "SELECT b FROM etanah.model.Integrasi b where b.kodUrusan.kod = :kod and b.idAliranHantar = :idAliranHantar";
        Session session = injector.getProvider(Session.class).get();
        Query q = session.createQuery(query);
        q.setString("kod", kod);
        q.setString("idAliranHantar", idAliranHantar);
        return (Integrasi) q.uniqueResult();

    }

    public IntegrasiPermohonan findIDMohon(String idPermohonan) {
        String query = "SELECT m FROM etanah.model.IntegrasiPermohonan m WHERE m.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setParameter("idPermohonan", idPermohonan);
        return (IntegrasiPermohonan) q.uniqueResult();
    }

    public Akaun findIDMohonDeposit(String idPermohonan) {
        String query = "SELECT m FROM etanah.model.Akaun m WHERE m.noAkaun = :idPermohonan and  m.kodAkaun.kod = 'AD' ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setParameter("idPermohonan", idPermohonan);
        return (Akaun) q.uniqueResult();
    }

    public IntegrasiPermohonanButir findByIDIntegIntegrasiPermohonanButir(Long idInteg) {
        String query = "SELECT m FROM etanah.model.IntegrasiPermohonanButir m WHERE m.idButir = :idInteg";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idInteg", idInteg);
        return (IntegrasiPermohonanButir) q.uniqueResult();
    }

    public Permit getHighestNopermit() {
        Session s = sessionProvider.get();
        String query = "SELECT m FROM etanah.model.Permit m WHERE m.noPermitStrata = (SELECT MAX(m2.noPermitStrata) from etanah.model.Permit m2)";
        Query q = s.createQuery(query);
        return (Permit) q.uniqueResult();
    }

    public List<HakmilikPermohonan> findIdHakmilikByIdMH(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.HakmilikPermohonan b WHERE b.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public Hakmilik findInfoByIdHakmilik(String idHM) {
        String query = "SELECT b FROM etanah.model.Hakmilik b WHERE b.idHakmilik = :idHM";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idHM", idHM);
        return (Hakmilik) q.uniqueResult();
    }

    public Hakmilik findInfoByIdHakmilikInduk(String idHM) {
        String query = "SELECT b FROM etanah.model.Hakmilik b WHERE b.idHakmilik = :idHM and b.idHakmilikInduk is null";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idHM", idHM);
        return (Hakmilik) q.uniqueResult();
    }

    public HakmilikPihakBerkepentingan findInfoByIdHakmilikProv(String idHM) {
        String query = "SELECT b FROM etanah.model.HakmilikPihakBerkepentingan b WHERE b.aktif = 'Y' "
                + " and b.idHakmilik = (SELECT c.idHakmilik FROM etanah.model.Hakmilik c WHERE c.idHakmilikInduk = :idHM "
                + " and c.kodKategoriBangunan.kod ='P' rownum=1)";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idHM", idHM);
        return (HakmilikPihakBerkepentingan) q.uniqueResult();
    }

    public Date findDateByIdHakmilik(String idHakmilik) {
        String query = "SELECT DISTINCT(b.tarikhDaftar) FROM etanah.model.Hakmilik b WHERE b.idHakmilikInduk = :idHakmilik";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        return (Date) q.uniqueResult();
    }

    public String findNoBukuByIdHakmilik(String idHakmilik) {
        String query = "SELECT max(b.noBukuDaftarStrata) FROM etanah.model.Hakmilik b WHERE b.idHakmilikInduk = :idHakmilik ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        return (String) q.uniqueResult();
    }

    public HakmilikPermohonan findIdMH(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.HakmilikPermohonan b, "
                + "etanah.model.Permit p, "
                + "etanah.model.PermohonanPermitButir mpb "
                + "where "
                + "b.id = mpb.hakmilikPermohonan.id and "
                + "mpb.idMpermitBtr = p.permohonanPermitButir.idMpermitBtr and "
                + "b.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (HakmilikPermohonan) q.uniqueResult();
    }

    public HakmilikPermohonan findidMhcore(String idPermohonan, String idInduk) {
        String query = "SELECT b FROM etanah.model.HakmilikPermohonan b "
                + "where "
                + "b.permohonan.idPermohonan = :idPermohonan and b.hakmilik.idHakmilik = :idInduk";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("idInduk", idInduk);
        return (HakmilikPermohonan) q.uniqueResult();
    }

    public HakmilikPermohonan findIdbyIDMohon(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.HakmilikPermohonan b WHERE b.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (HakmilikPermohonan) q.uniqueResult();
    }

    public List<Permit> findPermitByIDMH(long IdMH) {
        String query = "SELECT p FROM etanah.model.Permit p WHERE p.permohonanPermitButir.idMpermitBtr = :IdMH";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("IdMH", IdMH);
        return q.list();
    }

    public List<Permit> findmohonpermitbtrByIDMH(long idMH3) {
        String query = "SELECT p FROM etanah.model.Permit p, etanah.model.PermohonanPermitButir mpb WHERE mpb.idMpermitBtr = p.permohonanPermitButir.idMpermitBtr and mpb.hakmilikPermohonan.id = :idMH3";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idMH3", idMH3);
        return q.list();
    }

    public List<PermohonanPermitButir> findPermitbutirByIDMH(long IdMH) {
        String query = "SELECT p FROM etanah.model.PermohonanPermitButir p WHERE p.hakmilikPermohonan.id = :IdMH";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("IdMH", IdMH);
        return q.list();
    }

    public IntegrasiPermohonanDokumen findByIDButirIntegrasiPermohonanDokumen(Long idButir) {
        String query = "SELECT m FROM etanah.model.IntegrasiPermohonanDokumen m WHERE m.integrasiPermohonanButir.idButir = :idButir";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idButir", idButir);
        return (IntegrasiPermohonanDokumen) q.uniqueResult();
    }

    @Transactional
    public void deleteAduanStrata(String idPermohonan) {
        String query = "DELETE FROM etanah.model.AduanStrata p WHERE p.permohonan.idPermohonan = :idPermohonan";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.executeUpdate();
    }

    @Transactional
    public void UpdatedIdBdn(Long idBdn, String idHakmilik) {
        String query = "UPDATE etanah.model.Hakmilik p SET p.badanPengurusan.idBadan = :idBdn WHERE p.idHakmilikInduk =:idHakmilik";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idBdn", idBdn);
        q.setString("idHakmilik", idHakmilik);
        q.executeUpdate();
    }

    @Transactional
    public void daftarStrata(String idHakmilikInduk) {
        String query = "UPDATE etanah.model.Hakmilik p SET p.kodStatusHakmilik.kod = 'D' WHERE p.idHakmilikInduk =:idHakmilikInduk and p.kodStatusHakmilik <> 'B'";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idHakmilikInduk", idHakmilikInduk);
        q.executeUpdate();
    }

    public List<Hakmilik> getFilterDaerahMukim(String kodDaerah, int kodMukim) {
        Session s = sessionProvider.get();
        String query = "SELECT m FROM etanah.model.Hakmilik m WHERE m.daerah.kod = :kodDaerah AND m.bandarPekanMukim.kod = :kodMukim  AND m.syaratNyata.kod IN (SELECT s.kod FROM etanah.model.KodSyaratNyata s) AND m.sekatanKepentingan.kod IN (SELECT k.kod FROM etanah.model.KodSekatanKepentingan k) AND m.kegunaanTanah.kod IN (SELECT t.kod FROM etanah.model.KodKegunaanTanah t)";
        Query q = s.createQuery(query);
        q.setParameter("kodDaerah", kodDaerah);
        q.setInteger("kodMukim", kodMukim);
        return q.list();
    }

    @Transactional
    public IntegrasiPermohonan simpanMohonInteg(IntegrasiPermohonan integrasiPermohonan) {
        return integrasiPermohonanDAO.saveOrUpdate(integrasiPermohonan);
    }

    @Transactional
    public IntegrasiPermohonanButir simpanMohonIntegButir(IntegrasiPermohonanButir integrasiPermohonanButir) {
        return integrasiPermohonanButirDAO.saveOrUpdate(integrasiPermohonanButir);
    }

    @Transactional
    public IntegrasiPermohonanDokumen simpanMohonIntegDokumen(IntegrasiPermohonanDokumen integrasiPermohonanDokumen) {
        return integrasiPermohonanDokumenDAO.saveOrUpdate(integrasiPermohonanDokumen);
    }

    /**
     * @return the hmSblm
     */
    public List<HakmilikSebelum> getHmSblm() {
        return hmSblm;
    }

    /**
     * @param hmSblm the hmSblm to set
     */
    public void setHmSblm(List<HakmilikSebelum> hmSblm) {
        this.hmSblm = hmSblm;
    }

    public List<KodDokumen> getKodDokumenRequiredFiloA(ArrayList<String> senaraiKodUrusan) {
        System.out.println("-----senaraiKodUrusan-----" + senaraiKodUrusan);
        String hql = "select kd from KodDokumen kd where kd  in "
                + "(select d from UrusanDokumen ud "
                + "inner join ud.kodDokumen d "
                + "where ud.kodUrusan.id in (:senaraiKodUrusan)) "
                + "AND kd.kod in ('PSDPS','LP')"
                + "order by kd.nama";

        return sessionProvider.get().createQuery(hql).
                setParameterList("senaraiKodUrusan", senaraiKodUrusan).list();
    }

    public List<KodDokumen> getKodDokumenRequiredFiloB(ArrayList<String> senaraiKodUrusan) {
        System.out.println("-----senaraiKodUrusan-----" + senaraiKodUrusan);
        String hql = "select kd from KodDokumen kd where kd  in "
                + "(select d from UrusanDokumen ud "
                + "inner join ud.kodDokumen d "
                + "where ud.kodUrusan.id in (:senaraiKodUrusan)) "
                + "AND kd.kod in ('PDOKT')"
                + "order by kd.nama";

        return sessionProvider.get().createQuery(hql).
                setParameterList("senaraiKodUrusan", senaraiKodUrusan).list();
    }

    public List<KodDokumen> getKodDokumenRequiredFiloC(ArrayList<String> senaraiKodUrusan) {
        System.out.println("-----senaraiKodUrusan-----" + senaraiKodUrusan);
        String hql = "select kd from KodDokumen kd where kd  in "
                + "(select d from UrusanDokumen ud "
                + "inner join ud.kodDokumen d "
                + "where ud.kodUrusan.id in (:senaraiKodUrusan)) "
                + "AND kd.kod in ('DIAIO')"
                + "order by kd.nama";

        return sessionProvider.get().createQuery(hql).
                setParameterList("senaraiKodUrusan", senaraiKodUrusan).list();
    }

    public List<KodDokumen> getKodDokumenRequiredFiloD(ArrayList<String> senaraiKodUrusan) {
        System.out.println("-----senaraiKodUrusan-----" + senaraiKodUrusan);
        String hql = "select kd from KodDokumen kd where kd  in "
                + "(select d from UrusanDokumen ud "
                + "inner join ud.kodDokumen d "
                + "where ud.kodUrusan.id in (:senaraiKodUrusan)) "
                + "AND kd.kod in ('SLPPS', 'SAD', 'LTPS', 'LRPS', 'PC3', 'CRH', 'SDH', 'CF', 'PA', 'MSSM' ,'LKRTP', 'GTK', 'MMO')"
                //                + "AND kd.kod in ('SLPPS','SAD','LRPS','LTPS','PC3','CRH','CF','PABS','CSS1','PLKTA','PA','MMO','ST6','BO','NFM','LLL')"
                + "order by kd.nama";

        return sessionProvider.get().createQuery(hql).
                setParameterList("senaraiKodUrusan", senaraiKodUrusan).list();
    }

    public List<KandunganFolder> findkandunganFolderByfolderIdDesc(Long folderId) {
        //String query = "SELECT b FROM etanah.model.KandunganFolder b where b.folder.folderId = :folderId order by b.infoAudit.tarikhMasuk asc";
        String query = "SELECT b FROM etanah.model.KandunganFolder b where b.folder.folderId = :folderId order by b.dokumen.idDokumen asc";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("folderId", folderId);
        return q.list();
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanListByIdHakmilik(String idHakmilik) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.HakmilikPermohonan m WHERE m.permohonan.kodUrusan.kod = 'PPPP' AND m.hakmilik.idHakmilik = :idHakmilik");
        q.setString("idHakmilik", idHakmilik);
        return q.list();
    }

    public List<HakmilikPermohonan> getHakmilikUrusanAkByIdHakmilik(String idHakmilik) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.HakmilikPermohonan m, etanah.model.HakmilikUrusan hu "
                + "WHERE m.hakmilik.idHakmilik = :idHakmilik and m.hakmilik.idHakmilik = hu.hakmilik.idHakmilik "
                + "and m.permohonan.idPermohonan = hu.idPerserahan "
                + "and hu.aktif = 'Y' order by m.id ");
        q.setString("idHakmilik", idHakmilik);
        return q.list();
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanAKByIdHakmilik(String idHakmilik) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.HakmilikPermohonan m "
                + "WHERE m.hakmilik.idHakmilik = :idHakmilik and m.permohonan.status != 'SL' order by m.id ");
        q.setString("idHakmilik", idHakmilik);
        return q.list();
    }

    public List<Permohonan> findIDMohonByKodUrusan(String idPermohonan, String kod) {
        String query = "SELECT p FROM etanah.model.Permohonan p where p.permohonanSebelum.idPermohonan = :idPermohonan and p.kodUrusan.kod = :kod";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("kod", kod);
        return q.list();
    }

    //added By Murali 05/11/2013
    public PermohonanTuntutanKos findMohonTuntutKosPBBSDeposit(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PermohonanTuntutanKos b where b.permohonan.idPermohonan = :idPermohonan "
                + "and b.kodTuntut.kod = 'BDST' ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (PermohonanTuntutanKos) q.uniqueResult();
    }

    public List<PermohonanTuntutanBayar> findMohonTuntutBayar1(long idKos) {
        String query = "SELECT b FROM etanah.model.PermohonanTuntutanBayar b where b.permohonanTuntutanKos.idKos = :idKos";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idKos", idKos);
        return q.list();
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public LaporanTanah findByIDMohonLong(Long idPermohonan) {
        String query = "SELECT b FROM etanah.model.LaporanTanah b where b.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idPermohonan", idPermohonan);
        return (LaporanTanah) q.uniqueResult();
    }

    public List<Hakmilik> findNoBangunan(String idHakmilik) {
        String query = "SELECT distinct b.noBangunan FROM etanah.model.Hakmilik b where b.idHakmilikInduk = :idHakmilik ORDER BY LPAD(lower(b.noBangunan),10,0) ASC";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        return q.list();
    }

    public List<Hakmilik> findKatgBangunan(String idHakmilik) {
        String query = "SELECT distinct b.kodKategoriBangunan.kod FROM etanah.model.Hakmilik b where b.idHakmilikInduk = :idHakmilik";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        return q.list();
    }

    public List<Hakmilik> findByNoSkim(String noSkim) {
        String query = "SELECT b FROM etanah.model.Hakmilik b where b.noSkim = :noSkim ORDER BY LPAD(lower(b.noPetak),10,0) ASC";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("noSkim", noSkim);
        return q.list();
    }

    public List<BangunanTingkat> findByIdTgkt(Long idBngn) {
        String query = "SELECT bt FROM etanah.model.PermohonanBangunan mb, etanah.model.BangunanTingkat bt where mb.idBangunan = :idBngn and mb.idBangunan = bt.bangunan.idBangunan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idBngn", idBngn);
        return q.list();
    }

    @Transactional
    public void save(PermohonanBangunan permohonanBangunan) {
        permohonanBangunanDAO.save(permohonanBangunan);
    }

    @Transactional
    public void save(HakmilikPihakBerkepentingan hakmilikPihakBerkepentingan) {
        hakmilikPihakBerkepentinganDAO.save(hakmilikPihakBerkepentingan);
    }

    @Transactional
    public void save(BangunanTingkat bangunanTingkat) {
        bangunanTingkatDAO.save(bangunanTingkat);
    }

    @Transactional
    public void save(BangunanPetak bangunanPetak) {
        bangunanPetakDAO.save(bangunanPetak);
    }

    @Transactional
    public void save(BangunanPetakAksesori bangunanPetakAksesori) {
        bangunanPetakAksesoriDAO.save(bangunanPetakAksesori);
    }

//        public PermohonanKertas findMohonKertasMax() {
//        String query = "SELECT b FROM etanah.model.PermohonanKertas b where b.permohonan.idPermohonan like '%STR%' and b.nomborRujukan is not null" +
//                "SELECT m FROM etanah.model.PermohonanKertas m WHERE m.idKertas = (SELECT MAX(m3.idKertas) from etanah.model.PermohonanKertas m3)";
//        Session session = sessionProvider.get();
//        Query q = session.createQuery(query);
//        return (PermohonanKertas) q.uniqueResult();
//    }
    public Integer findMohonKertasMax() {
        Session s = sessionProvider.get();
        String query = "SELECT MAX(CAST(m2.nomborRujukan AS int)) FROM etanah.model.PermohonanKertas m2 WHERE m2.permohonan.idPermohonan LIKE '%STR%'";
        Query q = s.createQuery(query);
        return (Integer) q.uniqueResult();

    }

    public Long findMohonBngnMax() {
        Session s = sessionProvider.get();
        String query = "SELECT MAX(mb.idBangunan) FROM etanah.model.PermohonanBangunan mb";
        Query q = s.createQuery(query);
        return (Long) q.uniqueResult();

    }

    public Long findBngnTgktMax() {
        Session s = sessionProvider.get();
        String query = "SELECT MAX(bt.idTingkat) FROM etanah.model.BangunanTingkat bt";
        Query q = s.createQuery(query);
        return (Long) q.uniqueResult();

    }

    public Long findBngnPetakMax() {
        Session s = sessionProvider.get();
        String query = "SELECT MAX(bp.idPetak) FROM etanah.model.BangunanPetak bp";
        Query q = s.createQuery(query);
        return (Long) q.uniqueResult();

    }

    public Long findPetakAksMax() {
        Session s = sessionProvider.get();
        String query = "SELECT MAX(pa.idAksesori) FROM etanah.model.BangunanPetakAksesori pa";
        Query q = s.createQuery(query);
        return (Long) q.uniqueResult();

    }

    public PermohonanSkim findbyIdMh(String idHakmilikInduk) {
        Session s = sessionProvider.get();
        String query = "SELECT ms FROM etanah.model.PermohonanSkim ms, etanah.model.HakmilikPermohonan mh WHERE mh.id = ms.hakmilikPermohonan.id and mh.permohonan.idPermohonan LIKE '%STR%' and mh.hakmilik.idHakmilik = :idHakmilikInduk";
        Query q = s.createQuery(query);
        q.setString("idHakmilikInduk", idHakmilikInduk);
        return (PermohonanSkim) q.uniqueResult();
    }

    public BangunanTingkat findbyBngnTgkt(Long idBangunan) {
        Session s = sessionProvider.get();
        String query = "SELECT bt FROM etanah.model.BangunanTingkat bt, etanah.model.PermohonanBangunan mb where mb.idBangunan = :idBangunan and mb.idBangunan = bt.bangunan.idBangunan";
        Query q = s.createQuery(query);
        q.setLong("idBangunan", idBangunan);
        return (BangunanTingkat) q.uniqueResult();
    }

    public List<HakmilikPetakAksesori> findBngnAks(String idHakmilik) {
        Session s = sessionProvider.get();
        String query = "SELECT hpa FROM etanah.model.HakmilikPetakAksesori hpa where hpa.hakmilik.idHakmilik = :idHakmilik";
        Query q = s.createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        return q.list();
    }

    public List<HakmilikPetakAksesori> findHmPetakAksr(String idHakmilik) {
        Session s = sessionProvider.get();
        String query = "SELECT hpa FROM etanah.model.HakmilikPetakAksesori hpa where hpa.hakmilik.idHakmilik like :idHakmilik "
                + " and hpa.nama is not null order by to_char(hpa.nama) asc ";
        Query q = s.createQuery(query);
        q.setString("idHakmilik", idHakmilik + '%');
        return q.list();
    }

    public Hakmilik findLuasByIdHakmilikIndukNPetak(String idHakmilik, String petak) {
        Session s = sessionProvider.get();
        String query = "SELECT h FROM etanah.model.Hakmilik h where h.idHakmilikInduk = :idHakmilik and h.noPetak = :petak";
        Query q = s.createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        q.setString("petak", petak);
        return (Hakmilik) q.uniqueResult();
    }

    public Hakmilik findNoPetak(String noSkim, String noBangunan, String noTingkat, String noPetak) {
        Session s = sessionProvider.get();
        String query = "SELECT h FROM etanah.model.Hakmilik h where h.noSkim = :noSkim and h.noBangunan = :noBangunan and h.noTingkat =:noTingkat and h.noPetak = :noPetak";
        Query q = s.createQuery(query);
        q.setString("noSkim", noSkim);
        q.setString("noBangunan", noBangunan);
        q.setString("noTingkat", noTingkat);
        q.setString("noPetak", noPetak);
        return (Hakmilik) q.uniqueResult();
    }

    public Integer findTgktMax(String noBangunan, String noSkim) {
        Session s = sessionProvider.get();
        String query = "SELECT MAX(LTRIM(h.noTingkat,'NB&')) FROM etanah.model.Hakmilik h WHERE h.noBangunan = :noBangunan and h.noSkim = :noSkim";
        Query q = s.createQuery(query);
        q.setString("noBangunan", noBangunan);
        q.setString("noSkim", noSkim);
        return (Integer) q.uniqueResult();
    }

    public Long findCountPetak(String noBangunan, String noSkim) {
        Session s = sessionProvider.get();
        String query = "SELECT COUNT(h.noPetak) FROM etanah.model.Hakmilik h WHERE h.noBangunan = :noBangunan and h.noSkim = :noSkim";
        Query q = s.createQuery(query);
        q.setString("noBangunan", noBangunan);
        q.setString("noSkim", noSkim);
        return (Long) q.uniqueResult();
    }

    public Long findCountPetakAks(Long idBangunan) {
        Session s = sessionProvider.get();
        String query = "SELECT COUNT(h.bilanganPetakAksesori) FROM etanah.model.BangunanTingkat h WHERE h.bangunan.idBangunan = :idBangunan";
        Query q = s.createQuery(query);
        q.setLong("idBangunan", idBangunan);
        return (Long) q.uniqueResult();
    }

    public Long findSumPetakAks(Long idBangunan) {
        Session s = sessionProvider.get();
        String query = "SELECT sum(h.bilanganPetakAksesori) FROM etanah.model.BangunanTingkat h WHERE h.bangunan.idBangunan = :idBangunan";
        Query q = s.createQuery(query);
        q.setLong("idBangunan", idBangunan);
        return (Long) q.uniqueResult();
    }

    public String findTgktMaxbyidTgkt(String idTgkt) {
        Session s = sessionProvider.get();
        String query = "SELECT MAX(LTRIM(h.nama,'NB&')) FROM etanah.model.BangunanPetak h WHERE h.tingkat.idTingkat = :idTgkt";
        Query q = s.createQuery(query);
        q.setString("idTgkt", idTgkt);
        return (String) q.uniqueResult();
    }

    public Integer findPetakMaxBngnPetak(String idPermohonan) {
        Session s = sessionProvider.get();
        String query = "SELECT MAX(LTRIM(bp.nama,'NB&')) FROM etanah.model.BangunanPetak bp, etanah.model.BangunanTingkat bt, etanah.model.PermohonanBangunan mb "
                + "WHERE mb.permohonan.idPermohonan = :idPermohonan and mb.idBangunan = bt.bangunan.idBangunan and bt.idTingkat = bp.tingkat.idTingkat";
        Query q = s.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (Integer) q.uniqueResult();
    }

    public Long findCountPetakbyTingkat(String noSkim, String noTingkat, String noBangunan) {
        Session s = sessionProvider.get();
        String query = "SELECT COUNT(h.noPetak) FROM etanah.model.Hakmilik h WHERE h.noSkim = :noSkim and h.noTingkat = :noTingkat and h.noBangunan = :noBangunan";
        Query q = s.createQuery(query);
        q.setString("noSkim", noSkim);
        q.setString("noTingkat", noTingkat);
        q.setString("noBangunan", noBangunan);
        return (Long) q.uniqueResult();
    }

    public BigDecimal findSumSyerTingkat(String noBangunan, String noSkim) {
        Session s = sessionProvider.get();
        String query = "SELECT SUM(h.unitSyer) FROM etanah.model.Hakmilik h WHERE h.noBangunan = :noBangunan and h.noSkim = :noSkim";
        Query q = s.createQuery(query);
        q.setString("noBangunan", noBangunan);
        q.setString("noSkim", noSkim);
        return (BigDecimal) q.uniqueResult();
    }

    public List<PermohonanBangunan> findALLIDBngn(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PermohonanBangunan b where b.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();

    }

    public KodUrusan getUrusan(String kodUrusan) {
        String query = "SELECT h FROM etanah.model.KodUrusan h where h.kod = :kodUrusan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("kodUrusan", kodUrusan);
        return (KodUrusan) q.uniqueResult();
    }

    public Hakmilik findIdHakmilikequalIdInduk(String idInduk) {
        String query = "SELECT h FROM etanah.model.Hakmilik h where h.idHakmilik = :idInduk";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idInduk", idInduk);
        return (Hakmilik) q.uniqueResult();
    }

    public Hakmilik findIdHakmilikTemp(String idHakmilik) {
        String query = "SELECT h FROM etanah.model.Hakmilik h where h.idHakmilik = :idHakmilik";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        return (Hakmilik) q.uniqueResult();
    }

    public PermohonanLaporanKawasan findLprnKws(String idPermohonan) {
        String query = "SELECT h FROM etanah.model.PermohonanLaporanKawasan h where h.permohonan.idPermohonan = :idPermohonan and h.aktif IS NULL";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (PermohonanLaporanKawasan) q.uniqueResult();
    }

    public HakmilikPermohonan findIdHakmilik(String idHakmilik) {
        String query = "SELECT b FROM etanah.model.HakmilikPermohonan b WHERE b.hakmilik.idHakmilik = :idHakmilik and b.permohonan.idPermohonan LIKE '%STR%'";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        return (HakmilikPermohonan) q.uniqueResult();
//        return (HakmilikPermohonan) (sessionProvider.get().createQuery(query).setString("idHakmilik", idHakmilik)).list().get(0);
    }

    public HakmilikPermohonan findIdHakmilikHTB(String idHakmilik) {
        String query = "SELECT b FROM etanah.model.HakmilikPermohonan b WHERE b.hakmilik.idHakmilik = :idHakmilik and b.permohonan.idPermohonan IN (SELECT m.idPermohonan FROM etanah.model.Permohonan m WHERE m.kodUrusan.kod = 'HTB' and m.status = 'SL')";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        return (HakmilikPermohonan) q.uniqueResult();
    }

    public HakmilikPihakBerkepentingan findPihakPengurusan(String idHakmilik) {
        String query = "SELECT b FROM etanah.model.HakmilikPihakBerkepentingan b where b.hakmilik.idHakmilik = :idHakmilik AND b.jenis.kod = 'PM' and b.aktif = 'Y'";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        return (HakmilikPihakBerkepentingan) q.uniqueResult();
    }

    public PermohonanPihak findPihakHTB(String idMohonHTB) {
        String query = "SELECT b FROM etanah.model.PermohonanPihak b where b.permohonan.idPermohonan = :idMohonHTB";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idMohonHTB", idMohonHTB);
        return (PermohonanPihak) q.uniqueResult();
    }

    public HakmilikTukarGantiStrata findHmStrTG(String idHakmilik) {
        String query = "SELECT b FROM etanah.model.strata.HakmilikTukarGantiStrata b where b.hakmilikStrata = :idHakmilik";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        return (HakmilikTukarGantiStrata) q.uniqueResult();
    }

    public long countUnitSyer(String idmohon) {
        Session s = sessionProvider.get();
        String query = "SELECT sum(bp.syer) FROM etanah.model.BangunanPetak bp, etanah.model.BangunanTingkat bt, etanah.model.PermohonanBangunan mb"
                + " where mb.permohonan.idPermohonan = :idmohon and mb.idBangunan = bt.bangunan.idBangunan and bt.idTingkat = bp.tingkat.idTingkat";
        Query q = s.createQuery(query);
        q.setString("idmohon", idmohon);
        return (Long) q.uniqueResult();
    }

    public long countUnitSyerMhnBngn(String idmohon) {
        Session s = sessionProvider.get();
        String query = "SELECT sum(mb.syerBlok) FROM etanah.model.PermohonanBangunan mb where mb.permohonan.idPermohonan = :idmohon and mb.kodKategoriBangunan.kod not in ('P','PL')";
        Query q = s.createQuery(query);
        q.setString("idmohon", idmohon);
        return (Long) q.uniqueResult();
    }

    public Dokumen findIdDokumen(String idDokumen) {
        String query = "SELECT b FROM etanah.model.Dokumen b where b.idDokumen = :idDokumen";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idDokumen", idDokumen);
        return (Dokumen) q.uniqueResult();
    }

    public List<HakmilikTukarGantiStrata> findHmStrTGbyInduk(String idHakmilik) {
        String query = "SELECT h FROM etanah.model.strata.HakmilikTukarGantiStrata h where h.hakmilikInduk.idHakmilik = :idHakmilik order by h.hakmilikStrata asc";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        return q.list();
    }

    public List<Permohonan> findPermohonanSTR(String mula, String akhir) {
        String query = "SELECT h FROM etanah.model.Permohonan h where h.infoAudit.tarikhMasuk"
                + " between nvl(to_date(:mula,'dd/mm/yyyy'),h.infoAudit.tarikhMasuk) "
                + "and nvl(to_date(:akhir,'dd/mm/yyyy')+1,h.infoAudit.tarikhMasuk+1) and h.idPermohonan like '%STR%'";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("mula", mula);
        q.setString("akhir", akhir);
        return q.list();
    }

    @Transactional
    public void saveHmStrTG(String idHakmilik) {
        String query = "UPDATE etanah.model.Hakmilik p SET p.kodStatusHakmilik.kod = 'D' WHERE p.idHakmilikInduk =:idHakmilik";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        q.executeUpdate();
    }

    @Transactional
    public void deleteMhnBngn(String idPermohonan) {
        String query = "DELETE FROM etanah.model.PermohonanBangunan p WHERE p.permohonan.idPermohonan = :idPermohonan";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.executeUpdate();
    }

    @Transactional
    public void deleteHakmilikTemp(String idHakmilikTemp) {
        String query = "DELETE FROM etanah.model.Hakmilik p WHERE p.idHakmilik = :idHakmilikTemp";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idHakmilikTemp", idHakmilikTemp);
        q.executeUpdate();
    }

    @Transactional
    public void deleteHakmilikAksr(String idHakmilik) {
        String query = "DELETE FROM etanah.model.HakmilikPetakAksesori p WHERE p.hakmilik.idHakmilik = :idHakmilik";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        q.executeUpdate();
    }

    @Transactional
    public void deleteHakmilikAksr(String idHakmilik, String idAksesori) {
        String query = "DELETE FROM etanah.model.HakmilikPetakAksesori p WHERE p.hakmilik.idHakmilik = :idHakmilik and p.idAksesori = :idAksesori";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        q.setString("idAksesori", idAksesori);
        q.executeUpdate();
    }

    @Transactional
    public void deleteBngnTgkt(Long idBangunan) {
        String query = "DELETE FROM etanah.model.BangunanTingkat p WHERE p.bangunan.idBangunan = :idBangunan";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idBangunan", idBangunan);
        q.executeUpdate();
    }

    @Transactional
    public void deleteBngnTgktNama(Long idBangunan, String nama) {
        String query = "DELETE FROM etanah.model.BangunanTingkat p WHERE p.bangunan.idBangunan = :idBangunan and p.nama = :nama";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idBangunan", idBangunan);
        q.setString("nama", nama);
        q.executeUpdate();
    }

    @Transactional
    public void deleteBngnPetak(Long idTgkt) {
        String query = "DELETE FROM etanah.model.BangunanPetak p WHERE p.tingkat.idTingkat = :idTgkt";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idTgkt", idTgkt);
        q.executeUpdate();
    }

    @Transactional
    public void deleteBngnPetakbyidPetak(Long idPetak) {
        String query = "DELETE FROM etanah.model.BangunanPetak p WHERE p.idPetak = :idPetak";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idPetak", idPetak);
        q.executeUpdate();
    }

    @Transactional
    public void deleteBngnPetakAks(Long idBangunan) {
        String query = "DELETE FROM etanah.model.BangunanPetakAksesori p WHERE p.bangunan.idBangunan = :idBangunan";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idBangunan", idBangunan);
        q.executeUpdate();
    }

    @Transactional
    public void deleteBngnPetakAksbyTingkatidBngn(Long idBangunan, Long idTingkat) {
        String query = "DELETE FROM etanah.model.BangunanPetakAksesori p WHERE p.bangunan.idBangunan = :idBangunan and p.tingkat.idTingkat = :idTingkat";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idBangunan", idBangunan);
        q.setLong("idTingkat", idTingkat);
        q.executeUpdate();
    }

    public List<Hakmilik> findHakmilikStrata(String daerah, String bandarPekanMukim, String kodHakmilik, String noHakmilik) {
        String query = "SELECT b FROM etanah.model.Hakmilik b where b.idHakmilikInduk is not null and b.daerah.kod = :daerah and b.bandarPekanMukim.kod = :bandarPekanMukim and b.kodHakmilik.kod = :kodHakmilik "
                + "and b.noHakmilik LIKE :noHakmilik and b.kodStatusHakmilik <> 'B' order by b.idHakmilik asc";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("daerah", daerah);
        q.setString("bandarPekanMukim", bandarPekanMukim);
        q.setString("kodHakmilik", kodHakmilik);
        q.setString("noHakmilik", "%" + noHakmilik);
        return q.list();
    }

//    public List<Hakmilik> findHakmilikStrataTemp(String daerah, String bandarPekanMukim, String kodHakmilik, String noHakmilik) {
//        String query = "SELECT b FROM etanah.model.Hakmilik b where b.idHakmilikInduk is not null and b.daerah.kod = :daerah and b.bandarPekanMukim.kod = :bandarPekanMukim and b.kodHakmilik.kod = :kodHakmilik " +
//                "and b.noHakmilik LIKE :noHakmilik and b.noPetak is not null order by b.idHakmilik asc";
//        Session session = sessionProvider.get();
//        Query q = session.createQuery(query);
//        q.setString("daerah", daerah);
//        q.setString("bandarPekanMukim", bandarPekanMukim);
//        q.setString("kodHakmilik", kodHakmilik);
//        q.setString("noHakmilik", "%" + noHakmilik);
//        return q.list();
//    }
    public List<HakmilikPetakAksesori> findHakmilikPetakAksesori(String idHakmilik) {
        String query = "SELECT b FROM etanah.model.HakmilikPetakAksesori b where b.hakmilik.idHakmilik LIKE :idHakmilik ORDER BY LPAD(lower(b.nama),10,0) ASC";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idHakmilik", idHakmilik + "%");
        return q.list();
    }

    public List<HakmilikPetakAksesori> findHakmilikPelanTambah(String idHakmilik) {
        String query = "SELECT b FROM etanah.model.HakmilikPetakAksesori b where b.hakmilik.idHakmilik LIKE :idHakmilik and b.nama is null "
                + "order by b.hakmilik.idHakmilik ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idHakmilik", idHakmilik + "%");
        return q.list();
    }

    public List<Hakmilik> findHakmilikIndukNull(String idHakmilik) {
        String query = "SELECT h FROM etanah.model.Hakmilik h where h.idHakmilik = :idHakmilik and h.idHakmilikInduk is null";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        return q.list();
    }

    public List<Hakmilik> findHakmilikStrataByHakmilikIndukKATGBangunan(String idHakmilikInduk) {
        String query = "SELECT h FROM etanah.model.Hakmilik h where h.idHakmilikInduk = :idHakmilikInduk and h.kodKategoriBangunan.kod in ('P','PL')  order by h.idHakmilik asc";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idHakmilikInduk", idHakmilikInduk);
        return q.list();
    }
    
      public List<Hakmilik> findHakmilikStrataByHakmilikInduk(String idHakmilikInduk) {
        String query = "SELECT h FROM etanah.model.Hakmilik h where h.idHakmilikInduk = :idHakmilikInduk order by h.idHakmilik asc";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idHakmilikInduk", idHakmilikInduk);
        return q.list();
    }

    public List<Hakmilik> findHakmilikStrataTByHakmilikInduk(String idHakmilikInduk) {
        String query = "SELECT h FROM etanah.model.Hakmilik h where h.idHakmilikInduk = :idHakmilikInduk and h.kodStatusHakmilik.kod = 'T' order by h.idHakmilik asc";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idHakmilikInduk", idHakmilikInduk);
        return q.list();
    }

    public List<HakmilikTukarGantiStrata> findHakmilikTkrganti(String idHakmilik) {
        String query = "SELECT h FROM etanah.model.strata.HakmilikTukarGantiStrata h where h.hakmilikInduk.idHakmilik = :idHakmilik";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        return q.list();
    }

    public List<HakmilikTukarGantiStrata> findHakmilikTkrgantiStrata(String idHakmilik) {
        String query = "SELECT h FROM etanah.model.strata.HakmilikTukarGantiStrata h where h.hakmilikStrata = :idHakmilik";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        return q.list();
    }

    public List<DokumenCapaian> findDokumenCapaian(Long idDokumen) {
        String query = "SELECT dc FROM etanah.model.DokumenCapaian dc where dc.dokumen.idDokumen = :idDokumen";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idDokumen", idDokumen);
        return q.list();
    }

    public HakmilikTukarGantiStrata hakmilikTkrgantiStrata(String idHakmilik) {
        String query = "SELECT h FROM etanah.model.strata.HakmilikTukarGantiStrata h where h.hakmilikStrata = :idHakmilik";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        return (HakmilikTukarGantiStrata) q.uniqueResult();
    }

    public void reportGen(Permohonan p, String repName, String repCode, Pengguna pengguna) {
        String dokumenPath = conf.getProperty("document.path");
        String[] params = new String[]{"p_id_mohon"};
        String[] values = new String[]{p.getIdPermohonan()};
        Dokumen d = null;
        KodDokumen kd = null;

        String gen = repName;
        String code = repCode;
        kd = kodDokumenDAO.findById(code);
        d = saveOrUpdateDokumen(p.getFolderDokumen(), kd, p.getIdPermohonan(), pengguna);
        String path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
        reportUtil.generateReport(gen, params, values, dokumenPath + path, pengguna);
        updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
    }

    public Dokumen saveOrUpdateDokumen(FolderDokumen fd, KodDokumen kd, String id, Pengguna pengguna) {
        InfoAudit ia = new InfoAudit();
        Dokumen doc = null;
        doc = semakDokumen(kd, fd, id);
        if (doc == null) {
            doc = new Dokumen();
            ia.setDimasukOleh(pengguna);
            ia.setTarikhMasuk(new java.util.Date());
            doc.setBaru('Y');
            doc.setNoVersi("1.0");
        } else {
            ia = doc.getInfoAudit();
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(new java.util.Date());
            Double noVersi = Double.parseDouble(doc.getNoVersi());
            doc.setNoVersi(String.valueOf(noVersi + 1));
        }
        doc.setFormat("application/pdf");
        doc.setInfoAudit(ia);
        KodKlasifikasi klasifikasi_AM = kodKlasifikasiDAO.findById(1);
        doc.setKlasifikasi(klasifikasi_AM);
        doc.setTajuk(kd.getNama());
        doc.setKodDokumen(kd);
        doc.setDalamanNilai1(id);
        doc = dokumenService.saveOrUpdate(doc);
        KandunganFolder kf = kandunganFolderService.findByDokumen(doc, fd);
        if (kf == null) {
            kf = new KandunganFolder();
        } else {
            ia = kf.getInfoAudit();
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(new java.util.Date());
        }
        kf.setInfoAudit(ia);
        kf.setFolder(fd);
        kf.setDokumen(doc);
        dokumenService.saveKandunganWithDokumen(kf);

        return doc;
    }

    public void updatePathDokumen(String namaFizikal, Long idDokumen, String format) {
        Dokumen d = dokumenService.findById(idDokumen);
        d.setFormat(format);
        d.setNamaFizikal(namaFizikal);
        dokumenService.saveOrUpdate(d);
    }

    public Dokumen semakDokumen(KodDokumen kd, FolderDokumen fd, String id) {
        Dokumen d = null;
        List<KandunganFolder> l = kandunganFolderService.findByIdFolder(fd);
        for (KandunganFolder kf : l) {
            d = findByIDKodDokumen(kf.getDokumen().getIdDokumen(), kd, id);
            if (d != null) {
                return d;
            }
        }
        return null;
    }

    public Dokumen findByIDKodDokumen(Long idDokumen, KodDokumen kd, String id) {
        Session s = sessionProvider.get();
        Query q = s.createQuery(
                "select d from etanah.model.Dokumen d where d.idDokumen = :idDokumen and d.kodDokumen.kod = :kodDokumen");
        q.setLong("idDokumen", idDokumen);
        q.setParameter("kodDokumen", kd.getKod());

        Dokumen d = (Dokumen) q.uniqueResult();
        if (d == null) {
            return null;
        }
        return d;
    }

    public Hakmilik findIdBdn(String idHakmilik) {
        String query = "SELECT b FROM etanah.model.Hakmilik b where b.idHakmilikInduk = :idHakmilik ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idHakmilik", idHakmilik);

        if (q.list().size() > 1) {
            return (Hakmilik) q.list().get(0);
        } else {
            return (Hakmilik) q.uniqueResult();
        }
    }

    public List<Projek> findSifus(String idHakmilik, String noRujukan, String status) {
        StringBuilder sb = new StringBuilder("SELECT b FROM etanah.model.Projek b where 1=1 ");

        if (idHakmilik != null) {
            sb.append(" and b.idHakmilik = :idHakmilik ");
        }
        if (noRujukan != null) {
            sb.append(" and b.noRujFail = :noRujukan ");
        }
        if (StringUtils.isNotBlank(status)) {
            sb.append(" and b.aktif = :status ");
        }
        sb.append(" order by b.maksimumUnit");

        Query q = sessionProvider.get().createQuery(sb.toString());
        if (idHakmilik != null) {
            q.setString("idHakmilik", idHakmilik);
        }
        if (noRujukan != null) {
            q.setString("noRujukan", noRujukan);
        }
        if (StringUtils.isNotBlank(status)) {
            q.setString("status", status);
        }

        return q.list();
    }

    public Permohonan findPermohonanByidMohonKodUrusan(String idPermohonan, String kod) {
        String query = "Select p FROM etanah.model.Permohonan p WHERE p.idPermohonan = '"
                + idPermohonan + "' and p.kodUrusan.kod = '" + kod + "'";
        Query q = sessionProvider.get().createQuery(query);
        return (Permohonan) q.uniqueResult();
    }

    public Projek findSifus(String idProjek, String status) {
        StringBuilder sb = new StringBuilder("SELECT b FROM etanah.model.Projek b where b.idProjek = :idProjek ");

        if (!StringUtils.isBlank(status)) {
            sb.append(" and b.aktif = :status ");
        }

        Query q = sessionProvider.get().createQuery(sb.toString());
        q.setLong("idProjek", Long.parseLong(idProjek));

        if (StringUtils.isNotBlank(status)) {
            q.setString("status", status);
        }

        return (Projek) q.uniqueResult();
    }

    public Projek findSifusbyIdPermohonan(String idPermohonan) {
        StringBuilder sb = new StringBuilder("SELECT b FROM etanah.model.Projek b where b.idPermohonan = :idPermohonan ");

        Query q = sessionProvider.get().createQuery(sb.toString());
        q.setString("idPermohonan", idPermohonan);

        return (Projek) q.uniqueResult();
    }

    public List<Permohonan> getPermohonan(String idHakmilik, String kodUrusan) {
        String query = "SELECT b FROM etanah.model.Permohonan b, etanah.model.HakmilikPermohonan hp "
                + "where b.kodUrusan.kod = :kodUrusan and b.idPermohonan = hp.permohonan.idPermohonan and hp.hakmilik.idHakmilik = :idHakmilik ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        q.setString("kodUrusan", kodUrusan);
        return q.list();
    }

    public List<KodAgensi> getKodAgensi(String kod, String status) {
        StringBuilder sb = new StringBuilder("SELECT b FROM etanah.model.KodAgensi b where  b.kategoriAgensi.kod = :kod ");
        if (status != null) {
            sb.append(" and b.aktif = :status ");
        }
        sb.append(" order by nama");
        Session session = sessionProvider.get();
        Query q = session.createQuery(sb.toString());
        q.setString("kod", kod);
        if (status != null) {
            q.setString("status", status);
        }
        return q.list();
    }

    public List<KodAgensi> getKodAgensibyKod(String kod) {
        String query = "SELECT b FROM etanah.model.KodAgensi b "
                + "where  b.kod = :kod order by kod";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("kod", kod);
        return q.list();
    }

    public KodKategoriAgensi getKodKategoriAgensi(String kod) {
        String query = "SELECT b FROM etanah.model.KodKategoriAgensi b "
                + "where  b.kod = :kod and b.aktif = 'Y' order by nama";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("kod", kod);
        return (KodKategoriAgensi) q.uniqueResult();
    }

    public List<Dokumen> findDokumen(String idPermohonan, String kod) {
        StringBuilder sb = new StringBuilder("SELECT dok FROM etanah.model.Permohonan mhn, etanah.model.KandunganFolder fol ")
                .append(",  etanah.model.Dokumen dok ")
                .append(" where  mhn.idPermohonan = :idPermohonan ")
                .append(" and  mhn.folderDokumen.folderId = fol.folder.folderId ")
                .append(" and  fol.dokumen.idDokumen = dok.idDokumen ")
                .append(" and  dok.kodDokumen.kod = :kod ")
                .append(" order by dok.tajuk asc");

        Query q = sessionProvider.get().createQuery(sb.toString());
        q.setString("idPermohonan", idPermohonan);
        q.setString("kod", kod);
        return q.list();
    }

    public Integer maxNoBuku(String kodMilik) {
        String query = "select max(CAST(nvl(b.noBukuDaftarStrata,0) AS int)) "
                + " FROM etanah.model.Hakmilik b where b.kodHakmilik.milikDaerah =:kodMilik "
                + " and b.idHakmilikInduk is not null ";
//                + " order by to_number(b.noBukuDaftarStrata) desc ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("kodMilik", kodMilik);
        return (Integer) q.list().get(0);
    }

    public PermohonanRujukanLuar permohonanRujukanLuar(String kodMilik, String noBuku) {
        String query = "select b "
                + " FROM etanah.model.PermohonanRujukanLuar b where b.hakmilik.kodHakmilik.milikDaerah =:kodMilik "
                + " and b.noRujukan = :noBuku and b.permohonan.kodUrusan.kod = 'HTB' ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("noBuku", noBuku);
        q.setString("kodMilik", kodMilik);
        return (PermohonanRujukanLuar) q.uniqueResult();
    }

    public List<HakmilikPihakBerkepentingan> findHPBeforeHTB(String idHakmilik) {
        StringBuilder sb = new StringBuilder("SELECT hp FROM etanah.model.HakmilikPihakBerkepentingan hp ")
                .append(" where  (hp.infoAudit.dikemaskiniOleh,hp.infoAudit.tarikhKemaskini) in  ")
                .append(" (select hp.infoAudit.dikemaskiniOleh,hp.infoAudit.tarikhKemaskini from etanah.model.HakmilikPihakBerkepentingan hp ")
                .append(" where hp.infoAudit.dikemaskiniOleh is not null and hp.hakmilik.idHakmilik = :idHakmilik and hp.aktif = 'Y') ")
                .append(" and hp.hakmilik.idHakmilik = :idHakmilik and hp.aktif = 'T' ")
                .append(" and hp.jenis.kod in ('PM','WS','WPA','WKL','RP','PP','PLK','PK','PDP','PA','KL','JK','JA','ASL','PPC','PPH', ")
                .append(" 'PPM','PL','PAT','PAW','PAP','WKF','PML','CP','PH','ROS','BP') ");

        Query q = sessionProvider.get().createQuery(sb.toString());
        q.setString("idHakmilik", idHakmilik);
        return q.list();
    }

    public List<HakmilikPihakBerkepentingan> findHPAfterHTB(String idHakmilik) {
        StringBuilder sb = new StringBuilder("SELECT hp FROM etanah.model.HakmilikPihakBerkepentingan hp ")
                .append(" where  (hp.infoAudit.tarikhMasuk) in  ")
                .append(" (select max(hp.infoAudit.tarikhMasuk) from etanah.model.HakmilikPihakBerkepentingan hp ")
                .append(" where hp.hakmilik.idHakmilik = :idHakmilik and hp.aktif = 'T' ")
                .append(" and hp.jenis.kod in ('PM','WS','WPA','WKL','RP','PP','PLK','PK','PDP','PA','KL','JK','JA','ASL','PPC','PPH', ")
                .append(" 'PPM','PL','PAT','PAW','PAP','WKF','PML','CP','PH','ROS','BP') ")
                .append(" GROUP BY hp.infoAudit.dimasukOleh) ")
                .append(" and hp.hakmilik.idHakmilik = :idHakmilik and hp.aktif = 'T' ")
                .append(" and hp.jenis.kod in ('PM','WS','WPA','WKL','RP','PP','PLK','PK','PDP','PA','KL','JK','JA','ASL','PPC','PPH', ")
                .append(" 'PPM','PL','PAT','PAW','PAP','WKF','PML','CP','PH','ROS','BP') ");

        Query q = sessionProvider.get().createQuery(sb.toString());
        q.setString("idHakmilik", idHakmilik);
        return q.list();
    }

    public Integer maxkodSeksyen() {
        String query = "select max(cast(b.kod AS int)) "
                + " FROM etanah.model.KodSeksyen b ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        return (Integer) q.list().get(0);
    }

    public Integer maxkodBpm() {
        String query = "select max(cast(b.kod AS int)) "
                + " FROM etanah.model.KodBandarPekanMukim b ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        return (Integer) q.list().get(0);
    }

    public Integer maxKodKategoriTanah() {
        String query = "select max(cast(b.kod AS int)) "
                + " FROM etanah.model.KodKategoriTanah b ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        return (Integer) q.list().get(0);
    }

    public Integer maxKodSyktTubuh() {
        String query = "select max(cast(b.kod AS int)) "
                + " FROM etanah.model.KodPenubuhanSyarikat b ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        return (Integer) q.list().get(0);
    }

    public Integer maxKodKadarBayaran() {
        String query = "select max(cast(b.kod AS int)) "
                + " FROM etanah.model.KodKadarBayaran b ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        return (Integer) q.list().get(0);
    }
    
    public Integer maxKodKadarPremium() {
        String query = "select max(cast(b.idKodKadarPremium AS int)) "
                + " FROM etanah.model.KodKadarPremium b ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        return (Integer) q.list().get(0);
    }
    
    public Integer maxUrusanDokumen() {
        String query = "select max(cast(b.idUrusanDokumen AS int)) "
                + " FROM etanah.model.UrusanDokumen b ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        return (Integer) q.list().get(0);
    }

    public KodDaerah findKodDaerah(String kod) {
        String query = "select b FROM etanah.model.KodDaerah b where b.kod =:kod ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("kod", kod);
        return (KodDaerah) q.uniqueResult();
    }

    public KodCawangan findKodCawangan(String kod) {
        String query = "select b FROM etanah.model.KodCawangan b where b.kod =:kod ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("kod", kod);
        return (KodCawangan) q.uniqueResult();
    }

    public KodBandarPekanMukim findKodBandarPekanMukim(String kod) {
        String query = "select b FROM etanah.model.KodBandarPekanMukim b where b.kod =:kod ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("kod", kod);
        return (KodBandarPekanMukim) q.uniqueResult();
    }

    public KodSeksyen findKodSeksyen(String kod) {
        String query = "select b FROM etanah.model.KodSeksyen b where b.kod =:kod ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("kod", kod);
        return (KodSeksyen) q.uniqueResult();
    }

    public HakmilikPetakAksesori findPetakAksr(String idHakmilik, String nama) {
        Session s = sessionProvider.get();
        String query = "SELECT hpa FROM etanah.model.HakmilikPetakAksesori hpa where hpa.hakmilik.idHakmilik = :idHakmilik "
                + " and hpa.nama = :nama and hpa.nama is not null  ";
        Query q = s.createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        q.setString("nama", nama);
        return (HakmilikPetakAksesori) q.uniqueResult();
    }

    public FasaPermohonan findMohonFasaKpsn(String idPermohonan, String stageId) {
        String query = "SELECT b FROM etanah.model.FasaPermohonan b where b.permohonan.idPermohonan = :idPermohonan "
                + "and b.idAliran = :stageId and b.keputusan.kod is not null order by b.idFasa desc ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("stageId", stageId);

        if (q.list().size() > 1) {
            return (FasaPermohonan) q.list().get(0);
        } else {
            return (FasaPermohonan) q.uniqueResult();
        }
    }

    public SkimStrata findSkimStrata(String idHakmilik) {
        String query = "SELECT b FROM etanah.model.SkimStrata b where b.hakmilikInduk.idHakmilik = :idHakmilik ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idHakmilik", idHakmilik);

        if (q.list().size() > 1) {
            return (SkimStrata) q.list().get(0);
        } else {
            return (SkimStrata) q.uniqueResult();
        }
    }

    @Transactional
    public void saveSkimStrata(SkimStrata skim) {
        skimStrataDAO.save(skim);
    }

    @Transactional
    public void saveUpdateSkimStrata(SkimStrata skim) {
        skimStrataDAO.saveOrUpdate(skim);
    }

    public CukaiPetak findCukaiPetak(String katgBngn, String kelas) {
        Session s = sessionProvider.get();
        String query = "SELECT cp FROM etanah.model.CukaiPetak cp where cp.kategoriBangunan.kod = :katgBngn "
                + " and cp.kelasTanah.kod = :kelas  ";
        Query q = s.createQuery(query);
        q.setString("katgBngn", katgBngn);
        q.setString("kelas", kelas);
        return (CukaiPetak) q.uniqueResult();
    }
    
    public Hakmilik findCarianHakmilikStrataByHakmilikIndukKATGBangunan(String idHakmilikInduk, String noBangunan) {
        String query = "SELECT h FROM etanah.model.Hakmilik h where h.idHakmilikInduk = :idHakmilikInduk "
                + "and h.noBangunan = :noBangunan and h.kodKategoriBangunan.kod in ('P','PL') "
                + "and h.kodStatusHakmilik.kod = 'D' order by h.idHakmilik asc";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idHakmilikInduk", idHakmilikInduk);
        q.setString("noBangunan", noBangunan);
        return (Hakmilik)q.uniqueResult();
    }

    public List<Hakmilik> findTotalLuasByHM(String idHakmilik) {
        String sql = "SELECT hm.id_Hakmilik, hm.luas, nvl(sum(hpa.luas),0)"
                + " FROM Hakmilik hm, Hakmilik_Petak_Aksr hpa  "
                +" where  hm.id_Hakmilik_Induk = :idHakmilik  "
                +" and hm.id_Hakmilik = hpa.id_Hakmilik(+) "
                +" group by hm.id_Hakmilik, hm.luas order by hm.id_Hakmilik ";

        SQLQuery query = sessionProvider.get().createSQLQuery(sql);
//        query.addEntity(HakmilikPetakAksesori.class);
        query.setParameter("idHakmilik", idHakmilik);
        return query.list();
    }
    
    public List<Hakmilik> findTotalLuasByHMstatusHmT(String idHakmilik) {
        String sql = "SELECT hm.id_Hakmilik, hm.luas, nvl(sum(hpa.luas),0), hm.kod_guna_bngn, hm.kod_kelas_tanah, hm.kos_rendah, hm.kadar, hm.kod_sekatan"
                + " FROM Hakmilik hm, Hakmilik_Petak_Aksr hpa  "
                +" where  hm.id_Hakmilik_Induk = :idHakmilik  "
                +" and hm.id_Hakmilik = hpa.id_Hakmilik(+) "
                +" and hm.kod_sts_hakmilik = 'T' "
                +" group by hm.id_Hakmilik, hm.luas, hm.kod_guna_bngn, hm.kod_kelas_tanah, hm.kos_rendah, hm.kadar, hm.kod_sekatan order by hm.id_Hakmilik ";

        SQLQuery query = sessionProvider.get().createSQLQuery(sql);
//        query.addEntity(HakmilikPetakAksesori.class);
        query.setParameter("idHakmilik", idHakmilik);
        return query.list();
    }
    
    public List<HakmilikPetakAksesori> findHmPetakAksrstatusHmT(String idHakmilik) {
        Session s = sessionProvider.get();
        String query = "SELECT hpa FROM etanah.model.HakmilikPetakAksesori hpa where hpa.hakmilik.idHakmilik like :idHakmilik "
                + " and hpa.hakmilik.kodStatusHakmilik.kod = 'T' and hpa.nama is not null order by to_char(hpa.nama) asc ";
        Query q = s.createQuery(query);
        q.setString("idHakmilik", idHakmilik + '%');
        return q.list();
    }
}
