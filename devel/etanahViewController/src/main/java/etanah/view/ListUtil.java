package etanah.view;

import java.util.List;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import etanah.model.KodBank;
import etanah.model.KodCaraBayaran;
import etanah.model.KodJabatan;
import etanah.model.KodUrusan;
import etanah.dao.*;

import able.stripes.AbleActionBean;
import etanah.model.KodAgensi;
import etanah.model.KodAkaun;
import etanah.model.KodBahanBatu;
import etanah.model.KodBandarPekanMukim;
import etanah.model.KodBangsa;
import etanah.model.KodCaraPenghantaran;
import etanah.model.KodCawangan;
import etanah.model.KodDaerah;
import etanah.model.KodJenisPengenalan;
import etanah.model.KodJenisPihakBerkepentingan;
import etanah.model.KodLot;
import etanah.model.KodNegeri;
import etanah.model.KodPenyerah;
import etanah.model.KodSeksyen;
import etanah.model.KodRizab;
import etanah.model.KodSyaratNyata;
import etanah.model.KodSekatanKepentingan;
import etanah.model.KodPenubuhanSyarikat;
import etanah.model.KodUOM;
import etanah.model.KodKategoriTanah;
import etanah.model.KodPBT;
import etanah.model.KodHakmilik;
import etanah.model.KodStatusHakmilik;
import etanah.model.KodPerintah;
import etanah.model.KodKecerunanTanah;
import etanah.model.KodKelapanganTanah;
import etanah.model.KodNotis;
import etanah.model.KodStatusTerima;
import etanah.model.KodStatusTuntutanCukai;
import etanah.model.KodStrukturTanah;
import etanah.model.KodTanah;
import etanah.model.KodKegunaanTanah;
import etanah.model.KodKategoriBangunan;
import org.hibernate.Query;
import org.hibernate.Session;
import etanah.model.KodSuku;
import etanah.model.KodBatalDokumenKewangan;
import etanah.model.KodWarganegara;
import etanah.dao.KodPerhubunganHakmilikDAO;
import etanah.model.KodPerhubunganHakmilik;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.model.JUBL;
import etanah.model.KodAgensiKutipan;
import etanah.model.KodBahanBakar;
import etanah.model.KodDokumen;
import etanah.model.KodHartaBersama;
import etanah.model.KodPeranan;
import etanah.model.KodKeputusan;
import etanah.model.KodPemilikan;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import etanah.model.KodJantina;
import etanah.model.KodJenisBadanKenderaan;
import etanah.model.KodKegunaanKenderaan;
import etanah.model.KodKategoriAkaun;
import etanah.model.KodPerserahan;
import etanah.model.KodKategoriAgensi;
import etanah.model.KodKegunaanPetak;
import etanah.model.KodKementerian;
import etanah.model.KodKlasifikasi;
import etanah.model.KodWarnaKP;
import etanah.model.Pengguna;
import etanah.model.PenghantarNotis;
import etanah.model.SytJuruLelong;
import etanah.model.strata.BadanPengurusan;
import etanah.model.KodKadarPremium;
import etanah.model.PermohonanManual;
import etanah.model.KodKeadaanTanah;
import etanah.dao.KodKadarPremiumDAO;
import etanah.dao.KodKeadaanTanahDAO;
import etanah.dao.KodKawasanParlimenDAO;
import etanah.dao.KodItemPermitDAO;
import etanah.dao.PermohonanManualDAO;
import etanah.dao.KodPenubuhanSyarikatDAO;
import etanah.dao.KodDUNDAO;
import etanah.model.KodDUN;
import etanah.model.KodItemPermit;
import etanah.model.KodJawatan;
import etanah.model.KodKawasanParlimen;
import etanah.dao.KodKegunaanPetakDAO;
import etanah.dao.KodSyorUlasanJabatanTeknikalDAO;
import etanah.model.KodNegara;
import etanah.model.KodPerananOperasi;
import etanah.model.KodPerananRujukanLuar;
import etanah.model.KodJenisPembangunan;
import etanah.model.KodStatusEnkuiri;
import etanah.model.KodStsPembida;
import etanah.dao.KodStsPembidaDAO;
import etanah.model.*;
import etanah.model.KodSyorUlasanJabatanTeknikal;
import etanah.model.KodJenisPindahmilik;
import etanah.model.KodMaklumatInduk;

@Singleton
public class ListUtil extends AbleActionBean {

    @Inject
    private SytJuruLelongDAO sytJuruLelongDAO;
    @Inject
    private KodKadarPremiumDAO kodKadarPremiumDAO;
    @Inject
    private KodKawasanParlimenDAO kodKawasanParlimenDAO;
    @Inject
    private HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    private KodKeadaanTanahDAO kodKeadaanTanahDAO;
    @Inject
    private KodJabatanDAO kodJabatanDAO;
    @Inject
    private KodBatalDokumenKewanganDAO kodBatalDokumenKewanganDAO;
    @Inject
    private KodUrusanDAO kodUrusanDAO;
    @Inject
    private KodPerserahanDAO kodPerserahanDAO;
    @Inject
    private KodCaraBayaranDAO kodCaraBayaranDAO;
    @Inject
    private KodBankDAO kodBankDAO;
    @Inject
    private KodNegeriDAO kodNegeriDAO;
    @Inject
    private KodJenisPihakBerkepentinganDAO kodJenisPihakBerkepentinganDAO;
    @Inject
    private KodJenisPengenalanDAO kodJenisPengenalanDAO;
    @Inject
    private KodCawanganDAO kodCawanganDAO;
    @Inject
    private KodBandarPekanMukimDAO kodBandarPekanMukimDAO;
    @Inject
    private KodSeksyenDAO kodSeksyenDAO;
    @Inject
    private KodLotDAO kodLotDAO;
    @Inject
    private KodDaerahDAO kodDaerahDAO;
    @Inject
    private KodBangsaDAO kodBangsaDAO;
    @Inject
    private KodRizabDAO kodRizabDAO;
    @Inject
    private KodSyaratNyataDAO kodSyaratNyataDAO;
    @Inject
    private KodSekatanKepentinganDAO kodSekatanKepentinganDAO;
    @Inject
    private KodUOMDAO kodUomDAO;
    @Inject
    private KodKategoriTanahDAO kodKategoriTanahDAO;
    @Inject
    private KodKategoriAkaunDAO kodKategoriAkaunDAO;
    @Inject
    private KodPBTDAO kodPBTDAO;
    @Inject
    private KodHakmilikDAO kodHakmilikDAO;
    @Inject
    private KodStrukturTanahDAO kodStrukturTanahDAO;
    @Inject
    private KodKecerunanTanahDAO kodKecerunanTanahDAO;
    @Inject
    private KodKelapanganTanahDAO kodKelapanganTanahDAO;
    @Inject
    private KodTanahDAO kodTanahDAO;
    @Inject
    private KodDUNDAO kodDUNDAO;
    @Inject
    private KodPerintahDAO kodPerintahDAO;
    @Inject
    private KodKegunaanTanahDAO kodKegunaanTanahDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    private KodAgensiDAO kodAgensiDAO;
    @Inject
    private KodAgensiKutipanDAO kodAgensiKutipanDAO;
    @Inject
    private KodSukuDAO kodSukuDAO;
    @Inject
    private PenghantarNotisDAO penghantarNotisDAO;
    @Inject
    private KodAkaunDAO kodAkaunDAO;
    @Inject
    private KodStatusTuntutanCukaiDAO kodStatusTuntutanCukaiDAO;
    @Inject
    private KodNotisDAO kodNotisDAO;
    @Inject
    private KodCaraPenghantaranDAO kodCaraPenghantaranDAO;
    @Inject
    private KodStatusTerimaDAO kodStatusTerimaDAO;
    @Inject
    private KodWarganegaraDAO kodWargaNegaraDAO;
    @Inject
    private KodPerhubunganHakmilikDAO kodPerhubunganHakmilikDAO;
    @Inject
    private KodPenyerahDAO kodPenyerahDAO;
    @Inject
    private KodPerananDAO kodPerananDAO;
    @Inject
    private KodPemilikanDAO kodPemilikanDAO;
    @Inject
    private KodKeputusanDAO kodKeputusanDAO;
    @Inject
    private JUBLDAO jublDAO;
    @Inject
    private KodBahanBatuDAO bahanBatuDAO;
    @Inject
    private KodJantinaDAO kodJantinaDAO;
    @Inject
    private KodStatusHakmilikDAO kodStatusHakmilikDAO;
    @Inject
    private KodBahanBakarDAO kodBahanBakarDAO;
    @Inject
    private KodJenisBadanKenderaanDAO kodJenisBadanKenderaanDAO;
    @Inject
    private KodKegunaanKenderaanDAO kodKegunaanKenderaanDAO;
    @Inject
    private KodKategoriAgensiDAO kodKategoriAgensiDAO;
    @Inject
    private KodDokumenDAO kodDokumenDAO;
    @Inject
    private BadanPengurusanDAO badanPengurusanDAO;
    @Inject
    private KodKementerianDAO kodKementerianDAO;
    @Inject
    private KodHartaBersamaDAO kodHartaBersamaDAO;
    @Inject
    private KodKegunaanPetakDAO kodKegunaanPetakDAO;
    @Inject
    private KodWarnaKPDAO kodWarnaKPDAO;
    @Inject
    private KodKlasifikasiDAO kodKlasDAO;
    @Inject
    private PermohonanManualDAO permohonanManualDAO;
    @Inject
    private KodItemPermitDAO kodItemPermitDAO;
    @Inject
    private KodJawatanDAO kodJawatanDAO;
    @Inject
    private KodPenubuhanSyarikatDAO kodPenubuhanSyarikatDAO;
    @Inject
    private KodPerananOperasiDAO kodPerananOperasiDAO;
    @Inject
    private KodPerananRujukanLuarDAO kodPerananRujukanLuarDAO;
    @Inject
    private KodJenisPembangunanDAO kodJenisPembangunanDAO;
    @Inject
    private KodStatusEnkuiriDAO kodStatusEnkuiriDAO;
    @Inject
    private KodStsPembidaDAO kodStsPembidaDAO;
    @Inject
    private KodMatawangDAO kodMatawangDAO;
    @Inject
    private KodSyorUlasanJabatanTeknikalDAO kodSyorUlasanJabatanTeknikalDAO;
    @Inject
    private KodAgensiKutipanCawanganDAO kodAgensiKutipanCawanganDAO;
    @Inject
    private KodUnitDAO kodUnitDAO;
    @Inject
    private ProjekDAO projekDAO;
    @Inject
    private KodGelaranDAO kodGelaranDAO;
    @Inject
    private KodKelasTanahDAO kodKelasTanahDAO;
    @Inject
    private KodJenisPindahmilikDAO KodJenisPindahmilikDAO;
    @Inject
    private KodMaklumatIndukDAO KodMaklumatIndukDAO;
    @Inject
    KodKeputusanMahkamahDAO keputusanMahkamahDAO;

    //(To all programmers, update first before you commit anything. You will make other worst if you not update first)
    public List<KodItemPermit> getSenaraiKodItemPermit() {
        return kodItemPermitDAO.findAll();
    }

    public List<KodWarnaKP> getSenaraiKodWarnaKp() {
        return kodWarnaKPDAO.findAll();
    }

    public List<KodKawasanParlimen> getSenaraiKodKawasanparlimen() {
        return kodKawasanParlimenDAO.findAll();
    }

    public List<KodKeadaanTanah> getSenaraiKodKeadaanTanah() {
        return kodKeadaanTanahDAO.findAll();
    }

    public List<KodKadarPremium> getSenaraiKodKadarPremium() {
        return kodKadarPremiumDAO.findAll();
    }

    public List<KodKategoriAgensi> getSenaraiKodKategoriAgensi() {
        return kodKategoriAgensiDAO.findAll();
    }

    public List<KodGelaran> getSenaraiKodGelaran() {
        return kodGelaranDAO.findAll();
    }

    public List<KodJantina> getSenaraiKodJantina() {

        return kodJantinaDAO.findAll();
    }

    public List<KodPerhubunganHakmilik> getSenaraiKodPerhubunganHakmilik() {
        return kodPerhubunganHakmilikDAO.findAll();
    }

    public List<KodKategoriAkaun> getSenaraiKodKategoriAkaun() {
        return kodKategoriAkaunDAO.findAll();
    }

    public List<KodStsPembida> getSenaraiKodStsPembida() {
        return kodStsPembidaDAO.findAll();
    }

//     public List<HakmilikPermohonan> getSenaraiKodPerhubunganHakmilikMenguasai(){
//    	String[] name = {"hubunganHakmilik.kod"};
//        Object[] value = {"K"};
//
//    	return hakmilikPermohonanDAO.findByEqualCriterias(name, value, null);
//    }
//     public List<HakmilikPermohonan> getSenaraiKodPerhubunganHakmilikMenanggung(){
//     try {
//            Session s = sessionProvider.get();
//            Query q = s.createQuery("select u from etanah.model.HakmilikPermohonan u where u.hubunganHakmilik.kod = null or u.hubunganHakmilik.kod =:kod");
//            q.setString("kod", "T");
//            return q.list();
//        } finally {
//            //session.close();
//        }
//    }
    public List<KodPeranan> getSenaraiKodPeranan() {
        Session s = sessionProvider.get();
        Query q = s.createQuery("from etanah.model.KodPeranan kp where kp.aktif = 'Y' order by kp.nama");
        return q.list();
    }

    public List<KodPeranan> getSenaraiKodPerananBPEL() {
        Session s = sessionProvider.get();
        Query q = s.createQuery("from etanah.model.KodPeranan kp where kp.aktif = 'Y' and kp.kumpBPEL is not null");
        return q.list();
    }

    public List<KodPeranan> getSenaraiKodPerananByJabatan(String kodJabatan) {
        String query = "select kp from etanah.model.KodPeranan kp where kp.aktif = 'Y' ";

        if (StringUtils.isNotBlank(kodJabatan)) {
            query += "and kp.kodJabatan.kod = :kodJabatan";
        }

        Query q = sessionProvider.get().createQuery(query);
        if (StringUtils.isNotBlank(kodJabatan)) {
            q.setString("kodJabatan", kodJabatan);
        }
        return q.list();
    }

    public List<KodKlasifikasi> getSenaraiKodKlasifikasi() {
        return kodKlasDAO.findAll();
    }

    public List<KodStatusHakmilik> getSenaraiKodStatusHakmilik() {
        return kodStatusHakmilikDAO.findAll();
    }

    public List<KodWarganegara> getSenaraiWarganegara() {
        return kodWargaNegaraDAO.findAll();
    }

    public List<KodKegunaanTanah> getSenaraiKegunaanTanah() {
//        return kodKegunaanTanahDAO.findAll();
        try {
            Session s = sessionProvider.get();
            Query q = s.createQuery("from etanah.model.KodKegunaanTanah u where u.aktif = 'Y'");
            return q.list();
        } finally {
            //session.close();
        }
    }

    public List<KodHakmilik> getSenaraiKodHakmilik() {
        return kodHakmilikDAO.findAll();
    }

    public List<KodPBT> getSenaraiKodPBT() {
        return kodPBTDAO.findAll();
    }

    public List<KodPBT> getSenaraiKodPBTByDaerah(String kodDaerah) {
        try {
            Session s = sessionProvider.get();
            Query q = s.createQuery("select u from etanah.model.KodPBT u where u.daerah.kod = :kodDaerah order by u.nama");
            q.setString("kodDaerah", kodDaerah);
            return q.list();
        } finally {
            //session.close();
        }

    }

    public List<KodHakmilik> getSenaraiKodHakmilikSementara() {
        try {
            String query = "Select u from etanah.model.KodHakmilik u where u.kod in('HSM','HSD') order by u.nama";
            Query q = sessionProvider.get().createQuery(query);
            return q.list();
        } finally {
        }
    }

    public List<KodHakmilik> getSenaraiKodHakmilikDIS() {
        try {
            String query = "Select u from etanah.model.KodHakmilik u where u.kod in('HSM','HSD','HMM','GM','PM','GRN','PN','GMM') order by u.nama";
            Query q = sessionProvider.get().createQuery(query);
            return q.list();
        } finally {
        }
    }

    public List<KodHakmilik> getSenaraiKodHakmilikTetap() {
        try {
            String query = "Select u from etanah.model.KodHakmilik u where u.kod in('GM','PM','GRN','PN','GMM') order by u.nama";
            Query q = sessionProvider.get().createQuery(query);
            return q.list();
        } finally {
        }
    }

    public List<KodKategoriTanah> getSenaraiKodKategoriTanah() {
        //return kodKategoriTanahDAO.findAll();
        try {
            Session s = sessionProvider.get();
            Query q = s.createQuery("from etanah.model.KodKategoriTanah u where u.aktif = 'Y'");
            return q.list();
        } finally {
            //session.close();
        }
    }

    public List<KodKegunaanBangunan> getSenaraiKodKegunaanBangunan() {
        try {
            Session s = sessionProvider.get();
            Query q = s.createQuery("from etanah.model.KodKegunaanBangunan u where u.aktif = 'Y'");
            return q.list();
        } finally {
        }
    }

    public List<KodJenisPermit> getSenaraiKodPermit() {
        try {
            Session s = sessionProvider.get();
            Query q = s.createQuery("from etanah.model.KodJenisPermit u where u.aktif = 'Y' order by u.nama");
            return q.list();
        } finally {
        }
    }

    public List<KodKegunaanPetakAksesori> getSenaraiKodKegunaanPetakAksesori() {
        try {
            Session s = sessionProvider.get();
            Query q = s.createQuery("from etanah.model.KodKegunaanPetakAksesori u");
            return q.list();
        } finally {
        }
    }

    public List<KodUOM> getSenaraiKodUOM() {
        return kodUomDAO.findAll();
    }

    public List<KodUOM> getSenaraiKodUOMByLuas() {
        try {
            Session s = sessionProvider.get();
            Query q = s.createQuery("from etanah.model.KodUOM u where u.kump = :kump order by u.nama");
            q.setString("kump", "Luas");
            return q.list();
        } finally {
            //session.close();
        }
    }

    /**
     * Get senarai UOM by Isipadu
     *
     * @return List<KodUOM> byIsipadu
     * @author hairudin
     * @version 1.0 13 May 2010
     */
    public List<KodUOM> getSenaraiKodUOMByIsipadu() {
        try {
            Session s = sessionProvider.get();
            Query q = s.createQuery("from etanah.model.KodUOM u where u.kump = :kump order by u.nama");
            q.setString("kump", "Isipadu");
            return q.list();
        } finally {
        }
    }

    public List<KodUOM> getKodUOMByJarak() {
        try {
            Session s = sessionProvider.get();
            Query q = s.createQuery("from etanah.model.KodUOM u where u.kump = :kump order by u.nama");
            q.setString("kump", "Jarak");
            return q.list();
        } finally {
        }
    }

    public List<KodBangsa> getSenaraiKodUOMByMetrikSistem() {
        try {
            String query = "Select u from etanah.model.KodUOM u where u.kod in('K','M') order by u.nama";
            Query q = sessionProvider.get().createQuery(query);
            return q.list();
        } finally {
        }
    }

    public List<KodUOM> getSenaraiKodUOMByJarak() {

        try {
//            Session s = sessionProvider.get();
//            Query q = s.createQuery("");
            String query = "from etanah.model.KodUOM";
            query += " where kod in ('H','M','K','E')";
            Query q = sessionProvider.get().createQuery(query);
            return q.list();
        } finally {
            //session.close();
        }
    }

    public List<KodUOM> getSenaraiKodUOMByJarak2() {

        try {
//            Session s = sessionProvider.get();
//            Query q = s.createQuery("");
            String query = "from etanah.model.KodUOM";
            query += " where kod in ('M','K')";
            Query q = sessionProvider.get().createQuery(query);
            return q.list();
        } finally {
            //session.close();
        }
    }

    // add by azri 3/7/2013: used only in page 'nota_daftar_recreate.jsp'
    public List<KodUOM> getSenaraiKodUOMByJarak3() {
        try {
            String query = "from etanah.model.KodUOM";
            query += " where kod in ('M','H')";
            Query q = sessionProvider.get().createQuery(query);
            return q.list();
        } finally {
        }
    }
    // add by azri 3/7/2013: END

    public List<KodUOM> getSenaraiKodUOMByLuasLupus() {

        try {
//            Session s = sessionProvider.get();
//            Query q = s.createQuery("");
            String query = "from etanah.model.KodUOM";
            query += " where kod in ('H','M')";
            Query q = sessionProvider.get().createQuery(query);
            return q.list();
        } finally {
            //session.close();
        }
    }

    public List<KodUOM> getSenaraiKodUOMForBahanBatu() {
        try {
            String query = "from etanah.model.KodUOM";
            query += " where kod in ('MP','KB','TM','')";
            Query q = sessionProvider.get().createQuery(query);
            return q.list();
        } finally {
        }
    }

    public List<KodUOM> getSenaraiKodUOMMeterPadu() {
        try {
            String query = "from etanah.model.KodUOM";
            query += " where kod in ('MP')";
            Query q = sessionProvider.get().createQuery(query);
            return q.list();
        } finally {
        }
    }

    public List<KodBahanBatu> getSenaraiKodBahanBatu() {
        return bahanBatuDAO.findAll();
    }

    public List<KodSekatanKepentingan> getSenaraiKodSekatan() {
        return kodSekatanKepentinganDAO.findAll();
    }

    public List<KodSyaratNyata> getSenaraiKodSyaratNyata() {
        return kodSyaratNyataDAO.findAll();
    }

    public List<KodRizab> getSenaraiKodRizab() {
        return kodRizabDAO.findAll();
    }

    public List<KodJabatan> getSenaraiKodJabatan() {
        // training N.Sembilan 14082014
        Session s = sessionProvider.get();
        Query q = s.createQuery("SELECT u FROM etanah.model.KodJabatan u WHERE u.aktif = 'Y'");
        return q.list();
//        return kodJabatanDAO.findAll();
    }

    public List<PenghantarNotis> getSenaraiPenghantarNotis() {
        return penghantarNotisDAO.findAll();
    }

    public List<PenghantarNotis> getSenaraiPenghantarNotisByCaw(String kodCaw) {
        try {
            Session s = sessionProvider.get();
            Query q = s.createQuery("select u from etanah.model.PenghantarNotis u where u.cawangan.kod = :kodCaw order by u.nama");
            q.setString("kodCaw", kodCaw);
            return q.list();
        } finally {
            //session.close();
        }
    }

    public List<KodUrusan> getSenaraiKodUrusan() {
        return kodUrusanDAO.findAll();
    }

    public List<KodKategoriBayaran> getSenaraiKodKategoriBayaranAktif() {
        Session s = sessionProvider.get();
        Query q = s.createQuery("select a from etanah.model.KodKategoriBayaran a where a.aktif = 'Y' order by a.kod");

        return q.list();
    }

    public List<KodTransaksi> getSenaraiKodTransaksiAktif() {
        Session s = sessionProvider.get();
        Query q = s.createQuery("select a from etanah.model.KodTransaksi a where a.aktif = 'Y' order by a.kod");

        return q.list();
    }

    public List<KodPerserahan> getSenaraiKodPerserahanAktif() {
        Session s = sessionProvider.get();
        Query q = s.createQuery("select a from etanah.model.KodPerserahan a where a.aktif = 'Y' order by a.kod");

        return q.list();
    }

    public List<KodUrusan> getSenaraiKodUrusanAktif() {
        Session s = sessionProvider.get();
        Query q = s.createQuery("select a from etanah.model.KodUrusan a where a.aktif = 'Y' order by a.kod");

        return q.list();
    }

    public List<KodPerserahan> getSenaraiKodPerserahan() {
        Session s = sessionProvider.get();
        Query q = s.createQuery("select a from etanah.model.KodPerserahan a where kod in ('B','CR','CS','MH','N','NB','SA','SB','SC','SW') order by a.kod");

        return q.list();
//            return kodPerserahanDAO.findAll();
    }

    public List<KodCaraBayaran> getSenaraiKodCaraBayaran() {
        try {
            String query = "SELECT u FROM etanah.model.KodCaraBayaran u WHERE u.aktif = 'Y' ORDER BY u.nama";
            Query q = sessionProvider.get().createQuery(query);
            return q.list();
        } finally {
        }
        //return kodCaraBayaranDAO.findAll();
    }

    public List<KodBank> getSenaraiKodBank() {
        try {
            String query = "SELECT u FROM etanah.model.KodBank u WHERE u.aktif = 'Y' ORDER BY u.nama";
            Query q = sessionProvider.get().createQuery(query);
            return q.list();
        } finally {
        }
    }

    public List<KodNegeri> getSenaraiKodNegeri() {
        return kodNegeriDAO.findAll();
    }

    public List<KodJenisPihakBerkepentingan> getSenaraiKodJenisPihakBerkepentingan() {
        return kodJenisPihakBerkepentinganDAO.findAll();
    }

    public List<KodJenisPihakBerkepentingan> getSenaraiKodJenisPihakBerkepentinganBySerah(String kodSerah) {
        try {
            String query = "Select u from etanah.model.KodJenisPihakBerkepentingan u";
            if (kodSerah.equals("MH")) {
                query += " where u.kod ='PM'";
            }
            Query q = sessionProvider.get().createQuery(query);
            return q.list();
        } finally {
            //session.close();
        }
    }

    public List<KodJenisPengenalan> getSenaraiKodJenisPengenalan() {
        //return kodJenisPengenalanDAO.findAll(); Hafifi 18/04/2013 - Hanya paparkan jenis pengenalan yang aktif
        String query = "SELECT k FROM etanah.model.KodJenisPengenalan k WHERE k.aktif = :aktif";
        Query q = sessionProvider.get().createQuery(query);
        q.setCharacter("aktif", 'Y');
        return q.list();
    }

    public List<KodJenisPengenalan> getSenaraiKodJenisPengenalan1() {
        //return kodJenisPengenalanDAO.findAll(); Hafifi 18/04/2013 - Hanya paparkan jenis pengenalan yang aktif
        String query = "SELECT k.nama FROM etanah.model.KodJenisPengenalan k WHERE k.aktif = :aktif";
        Query q = sessionProvider.get().createQuery(query);
        q.setCharacter("aktif", 'Y');
        return q.list();
    }

    public List<KodCawangan> getSenaraiKodCawangan() {
        return kodCawanganDAO.findAll();
    }

    public List<KodCawangan> getSenaraiKodCawanganByDaerah() {
        try {
            String query = "select kc from etanah.model.KodCawangan kc where kc.kod in ('01','02','03') order by kc.name";
            Session s = sessionProvider.get();
            Query q = s.createQuery(query);
            return q.list();

        } finally {
        }
    }

    public List<KodBandarPekanMukim> getSenaraiKodBandarMukim() {
        return kodBandarPekanMukimDAO.findAll();
    }

    public List<KodBandarPekanMukim> getSenaraiKodBPMByDaerah(String kodDaerah) {
        try {
            Session s = sessionProvider.get();
            Query q = s.createQuery("select u from etanah.model.KodBandarPekanMukim u where u.daerah.kod = :kodDaerah and u.bandarPekanMukim <> '00' order by u.bandarPekanMukim ASC");
            q.setString("kodDaerah", kodDaerah);
            return q.list();
        } finally {
            //session.close();
        }
    }

    //Method untuk cater dlm mukim tampin yang ada mukim kecil gemas
    public List<KodBandarPekanMukim> getSenaraiKodBPMByDaerahAndCawangan(String kodDaerah, String kodCawangan) {
        try {
            String query = "select u from etanah.model.KodBandarPekanMukim u where u.daerah.kod = :kodDaerah and u.bandarPekanMukim <> '00'";
            if (StringUtils.isNotBlank(kodCawangan)) {
                query += " and u.cawangan.kod = :kodCawangan order by u.bandarPekanMukim ASC";
            } else {
                query += " order by u.bandarPekanMukim ASC";
            }
            Query q = sessionProvider.get().createQuery(query);
            q.setString("kodDaerah", kodDaerah);
            if (StringUtils.isNotBlank(kodCawangan)) {
                q.setString("kodCawangan", kodCawangan);
            }
            return q.list();
        } finally {
            //session.close();
        }
    }

//add senarai BPM   
    public List<KodBandarPekanMukim> getSenaraiKodBPMptg() {
        try {
            String query = "select u from etanah.model.KodBandarPekanMukim u where u.daerah.kod = '00' order by u.nama";
            Session session = sessionProvider.get();
            Query q = session.createQuery(query);
            return q.list();
        } finally {
        }
    }

    public List<KodBandarPekanMukim> getSenaraiKodBPMmt() {
        try {
            String query = "select u from etanah.model.KodBandarPekanMukim u where u.daerah.kod = '01' order by u.nama";
            Session session = sessionProvider.get();
            Query q = session.createQuery(query);
            return q.list();
        } finally {
        }
    }

    public List<KodBandarPekanMukim> getSenaraiKodBPMjs() {
        try {
            String query = "select u from etanah.model.KodBandarPekanMukim u where u.daerah.kod = '02' order by u.nama";
            Session session = sessionProvider.get();
            Query q = session.createQuery(query);
            return q.list();
        } finally {
        }
    }

    public List<KodBandarPekanMukim> getSenaraiKodBPMag() {
        try {
            String query = "select u from etanah.model.KodBandarPekanMukim u where u.daerah.kod = '03' order by u.nama";
            Session session = sessionProvider.get();
            Query q = session.createQuery(query);
            return q.list();
        } finally {
        }
    }
//end add senarai BPM   

    public List<KodBandarPekanMukim> getSenaraiKodBPMByDaerahOrderByBPM(String kodDaerah) {
        try {
            Session s = sessionProvider.get();
            Query q = s.createQuery("select u from etanah.model.KodBandarPekanMukim u where u.daerah.kod = :kodDaerah order by u.bandarPekanMukim");
            q.setString("kodDaerah", kodDaerah);
            return q.list();
        } finally {
        }
    }

    public List<KodSeksyen> getSenaraiKodSeksyen() {
        return kodSeksyenDAO.findAll();
    }

    public List<KodLot> getSenaraiKodLot() {
        return kodLotDAO.findAll();
    }

    public List<KodLot> getSenaraiLotPelupusan() {
        try {
            String query = "Select u from etanah.model.KodLot u where u.kod in('1','2','3') order by u.nama";
            Query q = sessionProvider.get().createQuery(query);
            return q.list();
        } finally {
        }
    }

    public List<KodLot> getSenaraiLotPelupusanLotOnly() {
        try {
            String query = "Select u from etanah.model.KodLot u where u.kod in('1') order by u.nama";
            Query q = sessionProvider.get().createQuery(query);
            return q.list();
        } finally {
        }
    }

    public List<KodPemilikan> getSenaraiKodPemilikanDIS() {
        try {
            String query = "Select u from etanah.model.KodPemilikan u where u.kod in('K','R') order by u.nama";
            Query q = sessionProvider.get().createQuery(query);
            return q.list();
        } finally {
        }
    }

    public List<KodPemilikan> getSenaraiKodPemilikanPTBT() {
        try {
            String query = "Select u from etanah.model.KodPemilikan u where u.kod in('K') order by u.nama";
            Query q = sessionProvider.get().createQuery(query);
            return q.list();
        } finally {
        }
    }

    public List<KodPemilikan> getSenaraiKodPemilikanBMBT() {
        try {
            String query = "Select u from etanah.model.KodPemilikan u where u.kod in('H') order by u.nama";
            Query q = sessionProvider.get().createQuery(query);
            return q.list();
        } finally {
        }
    }

    public List<KodPemilikan> getSenaraiKodPemilikanLPSP() {
        try {
            String query = "Select u from etanah.model.KodPemilikan u where u.kod in('P','K','R') order by u.nama";
            Query q = sessionProvider.get().createQuery(query);
            return q.list();
        } finally {
        }
    }

    public List<KodDaerah> getSenaraiKodDaerah() {
        return kodDaerahDAO.findAll();
    }

    public List<KodDaerah> getSenaraiKodDaerahBaru() {
        try {
            String query = "Select d from etanah.model.KodDaerah d where d.infoAudit.dikemaskiniOleh is not null order by d.nama";
            Query q = sessionProvider.get().createQuery(query);
            return q.list();
        } finally {
        }
    }

    public List<KodBangsa> getSenaraiKodBangsa() {
        return kodBangsaDAO.findAll();
    }

    public List<KodBangsa> getSenaraiKodBangsaByJenisPengenalan(String jenisPengenalan) {
        try {
            String query = "Select u from etanah.model.KodBangsa";
            if (jenisPengenalan.equals("B") || jenisPengenalan.equals("L") || jenisPengenalan.equals("P") || jenisPengenalan.equals("T") || jenisPengenalan.equals("I") || jenisPengenalan.equals("F")) {
                query += " u where u.kod in('MEL','CIN','IND','SIM','ASL','LN') order by u.nama";
            } else {
                query += " u where u.kod not in('MEL','CIN','IND','SIM','ASL','LN') order by u.nama";
            }
            Query q = sessionProvider.get().createQuery(query);
            return q.list();
        } finally {
            //session.close();
        }
    }

    public List<KodBangsa> getSenaraiKodBangsaByJenisPengenalanNotIn(String jenisPengenalan) {
        try {
            String query = "Select u from etanah.model.KodBangsa";
            if (!(jenisPengenalan.equals("B") || jenisPengenalan.equals("L") || jenisPengenalan.equals("P") || jenisPengenalan.equals("T") || jenisPengenalan.equals("I") || jenisPengenalan.equals("F"))) {
                query += " u where u.kod not in('MEL','CIN','IND','SIM','ASL','LN') order by u.nama";
            }
            Query q = sessionProvider.get().createQuery(query);
            return q.list();
        } finally {
            //session.close();
        }
    }

    public List<KodBangsa> getSenaraiKodBangsaSyarikat() {
        try {
            String query = "Select u from etanah.model.KodBangsa";
            query += " u where u.kod not in('MEL','CIN','IND','SIM','ASL','LN') order by u.nama";
            Query q = sessionProvider.get().createQuery(query);
            return q.list();
        } finally {
        }
    }

    public List<KodBangsa> getSenaraiSyarikat() {
        try {
            String query = "Select u from etanah.model.KodBangsa u where u.kod in('BNK','KP','SK','KN','BKP','BKN','PBT','ST','SLN','BAI','BAC','BAH','BAK','BPL','POL','INS','KOP','DUT','PPS') order by u.nama";
            Query q = sessionProvider.get().createQuery(query);
            return q.list();
        } finally {
        }
    }

    public List<KodJenisPengenalan> getSenaraiJenisPengenalanIndividu() {
        try {
            String query = "Select u from etanah.model.KodJenisPengenalan u where u.kod in('B','L','P','T','I') order by u.nama";
            Query q = sessionProvider.get().createQuery(query);
            return q.list();
        } finally {
        }
    }

    public List<KodJenisPengenalan> getSenaraiJenisPengenalanPelupusan() {
        try {
            String query = "Select u from etanah.model.KodJenisPengenalan u where u.kod in('B','S','L','P','T','I','U','0') order by u.nama";
            Query q = sessionProvider.get().createQuery(query);
            return q.list();
        } finally {
        }
    }

    public List<KodJenisPengenalan> getSenaraiJenisPengenalanPelupusanGSA() {
        try {
            String query = "Select u from etanah.model.KodJenisPengenalan u where u.kod in('B','S') order by u.nama";
            Query q = sessionProvider.get().createQuery(query);
            return q.list();
        } finally {
        }
    }

    public List<KodJenisPengenalan> getSenaraiJenisPengenalanPelupusanLembagaPengarah() {
        try {
            String query = "Select u from etanah.model.KodJenisPengenalan u where u.kod in('B','L') order by u.nama";
            Query q = sessionProvider.get().createQuery(query);
            return q.list();
        } finally {
        }
    }

    public List<KodJenisPengenalan> getSenaraiJenisPengenalanPihakPengarah() {
        try {
            String query = "Select u from etanah.model.KodJenisPengenalan u where u.kod in('U','S') order by u.nama";
            Query q = sessionProvider.get().createQuery(query);
            return q.list();
        } finally {
        }
    }

    public List<KodBangsa> getSenaraiKodBangsaPerseorangan() {
        try {
            String query = "Select u from etanah.model.KodBangsa u where u.kod in('MEL','CIN','IND','SIM','ASL','LN') order by u.nama";
            Query q = sessionProvider.get().createQuery(query);
            return q.list();
        } finally {
        }
    }

    public List<KodJenisPihakBerkepentingan> getSenaraiKodPihakPenerima(String module, String namaUrusan) {
        boolean f = false;
        List<String> senaraiKodPihak = new ArrayList<String>();
        try {
            String query = "Select u from etanah.model.KodJenisPihakBerkepentingan u";
            if (StringUtils.isNotBlank(module)) {
                if ("Consent".equalsIgnoreCase(module)) {

                    senaraiKodPihak.add("PGA");
                    senaraiKodPihak.add("PPM");
                    senaraiKodPihak.add("PSA");
                    senaraiKodPihak.add("PCA");
                    senaraiKodPihak.add("PPA");
                    senaraiKodPihak.add("PA");
                    senaraiKodPihak.add("PWS");
                    senaraiKodPihak.add("PTM");
                    senaraiKodPihak.add("PUH");
                    senaraiKodPihak.add("PYT");
                    senaraiKodPihak.add("PKW");
                    senaraiKodPihak.add("PP");
                    senaraiKodPihak.add("WS");

                    f = true;
                } else if ("Pendaftaran".equalsIgnoreCase(module)) {
//                    if (namaUrusan.equalsIgnoreCase("Perintah Jual Pentadbir Sebab Gadaian")
//                            || namaUrusan.equalsIgnoreCase("Perintah Jual Pentadbir Sebab gadaian pajakan/Pajakan kecil") ) {
//                    } else
                    if (namaUrusan.equals("Turun Milik Akibat Kematian Borang G")) {
                        senaraiKodPihak.add("WKL");
                        senaraiKodPihak.add("WS");
                        f = true;
                    } else if (namaUrusan.equalsIgnoreCase("Pindahmilik Tanah") || namaUrusan.startsWith("Perintah Mahkamah") //                            || namaUrusan.startsWith("Perintah Jual") // keluarkan semua kod PB yg ada
                            || namaUrusan.startsWith("Turun Milik") || namaUrusan.equalsIgnoreCase("Pindahmilik Tanah melalui Mahkamah")
                            || namaUrusan.startsWith("Hakmilik Sementara")
                            || namaUrusan.startsWith("Hakmilik Kekal")
                            || namaUrusan.equalsIgnoreCase("Pemberimilikan Tanah Bawah Tanah (Stratum)")) {
                        senaraiKodPihak.add("CP");
                        senaraiKodPihak.add("WAR");
                        senaraiKodPihak.add("ASL");
                        senaraiKodPihak.add("JA");
                        senaraiKodPihak.add("JK");
                        senaraiKodPihak.add("KL");
                        senaraiKodPihak.add("PA");
                        senaraiKodPihak.add("PDP");
                        senaraiKodPihak.add("PK");
                        senaraiKodPihak.add("PLK");
                        senaraiKodPihak.add("PM");
                        senaraiKodPihak.add("PP");
                        senaraiKodPihak.add("RP");
                        senaraiKodPihak.add("WKL");
                        senaraiKodPihak.add("WPA");
                        senaraiKodPihak.add("WS");
                        senaraiKodPihak.add("CP");
                        f = true;
                    } else if (namaUrusan.startsWith("Gadaian") || namaUrusan.equalsIgnoreCase("Pindahmilik Gadaian")) {
                        senaraiKodPihak.add("PAG");
                        senaraiKodPihak.add("PG");
                        f = true;
                    } else if (namaUrusan.startsWith("Ismen")) {
                        senaraiKodPihak.add("PI");
                        f = true;
                    } else if (namaUrusan.startsWith("Kaveat Amanah")) {
                        senaraiKodPihak.add("PKA");
                        f = true;
                    } else if (namaUrusan.startsWith("Kaveat Pemegang Lien")) {
                        senaraiKodPihak.add("PKL");
                        f = true;
                    } else if (namaUrusan.startsWith("Kaveat Pendaftar")) {
                        senaraiKodPihak.add("PKD");
                        f = true;
                    } else if (namaUrusan.startsWith("Kaveat Persendirian")) {
                        senaraiKodPihak.add("PKS");
                        f = true;
                    } else if (namaUrusan.startsWith("Pemotongan Kaveat") || namaUrusan.startsWith("Tarikbalik Kaveat") || namaUrusan.startsWith("Cadangan Pemotongan") || namaUrusan.startsWith("Pembatalan Kaveat")) {
                        senaraiKodPihak.add("PPK");
                        f = true;
                    } else if (namaUrusan.startsWith("Mortgage")) {
                        senaraiKodPihak.add("PMG");
                        f = true;
                    } else if (namaUrusan.equalsIgnoreCase("Pajakan Kecil Sebahagian Tanah")) {
                        senaraiKodPihak.add("PJK");
                        f = true;
                    } else if (namaUrusan.startsWith("Pajakan") || namaUrusan.equalsIgnoreCase("Pindahmilik Pajakan") || namaUrusan.equalsIgnoreCase("Pindahmilik Pajakan Kecil") || namaUrusan.equalsIgnoreCase("Gadaian Pajakan") || namaUrusan.equalsIgnoreCase("Gadaian Pajakan Kecil")) {
                        senaraiKodPihak.add("PJ");
                        senaraiKodPihak.add("PJK");
                        f = true;
                    } else if (namaUrusan.startsWith("Pendaftaran Penghuni")) {
                        senaraiKodPihak.add("PUH");
                        senaraiKodPihak.add("PAS");
                        f = true;
                    } else if (namaUrusan.startsWith("With A Right")) {
                        senaraiKodPihak.add("ROS");
                        f = true;
                    } else if (namaUrusan.startsWith("Tenansi") || namaUrusan.startsWith("Sewaan")) {
                        senaraiKodPihak.add("PY");
                        f = true;
                    } else if (namaUrusan.startsWith("Pendaftaran Pemegang Amanah")) {
                        senaraiKodPihak.add("PA");
                        senaraiKodPihak.add("WAR");
                        f = true;
                    } else if (namaUrusan.equalsIgnoreCase("Pertukaran Pemegang Amanah Atas Perintah Mahkamah")) {
                        senaraiKodPihak.add("PA");
                        f = true;
                    } else if (namaUrusan.equalsIgnoreCase("Pembatalan Pendaftaran Pemegang Amanah")) {
                        senaraiKodPihak.add("PM");
                        f = true;
                    }

                }
                if (f) {
                    query += " where u.kod in (:list)";
                }
            }
            query += " order by u.nama";
//            query += " u where u.kod in('PCA','PPA','PTM','PPG','PGA','PPC','PTA','PPM','PSA') order by u.nama";

            Query q = sessionProvider.get().createQuery(query);
            if (f) {
                q.setParameterList("list", senaraiKodPihak);
            }
            return q.list();
        } finally {
            //session.close();
        }
    }

    public Map<String, String> getSenaraiCaraTerimaSyer() {
        Map<String, String> senaraiCaraTerima = new HashMap<String, String>();
        senaraiCaraTerima.put("Adik Beradik", "Adik Beradik");
        senaraiCaraTerima.put("Melepaskan Pemegang Amanah", "Melepaskan Pemegang Amanah");
        senaraiCaraTerima.put("Pindah Milik Bersilang", "Pindah Milik Bersilang");
        senaraiCaraTerima.put("Pembelian", "Pembelian");
        senaraiCaraTerima.put("Pemberian Kasih Sayang", "Pemberian Kasih Sayang");
        senaraiCaraTerima.put("Pemberian Milik oleh Kerajaan", "Pemberian Milik oleh Kerajaan");
        senaraiCaraTerima.put("Penyambut Lelong", "Penyambut Lelong");
        senaraiCaraTerima.put("Pesaka", "Pesaka");
        senaraiCaraTerima.put("Perintah Mahkamah", "Perintah Mahkamah");
        senaraiCaraTerima.put("Pertukaran Lot", "Pertukaran Lot");
        senaraiCaraTerima.put("Lain-lain", "Lain-lain");
        return senaraiCaraTerima;
    }

    public List<KodStrukturTanah> getSenaraiKodStrukturTanah() {
        return kodStrukturTanahDAO.findAll();
    }

    public List<KodKecerunanTanah> getSenaraiKodKecerunanTanah() {
        return kodKecerunanTanahDAO.findAll();
    }

    public List<KodKelapanganTanah> getSenaraiKodKelapanganTanah() {
        return kodKelapanganTanahDAO.findAll();
    }

    public List<KodTanah> getSenaraiKodTanah() {
        return kodTanahDAO.findAll();
    }

    public List<KodDUN> getSenaraiKodDUN() {
        return kodDUNDAO.findAll();
    }

    public List<KodPerintah> getSenaraiKodPerintah() {

        List<KodPerintah> senaraiPerintah = kodPerintahDAO.findAll();

        List<KodPerintah> senaraiKodPerintahAktif = new ArrayList<KodPerintah>();

        for (KodPerintah kodPerintah : senaraiPerintah) {
            if (kodPerintah.isAktif() == 'Y') {
                senaraiKodPerintahAktif.add(kodPerintah);
            }
        }

        return senaraiKodPerintahAktif;
    }

    public List<KodAgensi> getSenaraiKodAgensi() {
        return kodAgensiDAO.findAll();
    }

    public List<KodAgensiKutipan> getSenaraiKodAgensiKutipan() {
        return kodAgensiKutipanDAO.findAll();
    }

    public List<KodSuku> getSenaraiKodSuku() {
        return kodSukuDAO.findAll();
    }

    public List<KodStatusTuntutanCukai> getSenaraiKodStatusTuntutanCukai() {
        return kodStatusTuntutanCukaiDAO.findAll();
    }

    public List<KodNotis> getSenaraiKodNotis() {
        return kodNotisDAO.findAll();
    }

    public List<SytJuruLelong> getSenaraiSytJuruLelong() {
        return sytJuruLelongDAO.findAll();
    }

    public List<KodCaraPenghantaran> getSenaraiKodCaraPenghantaran() {
        return kodCaraPenghantaranDAO.findAll();
    }

    public List<KodStatusTerima> getSenaraiKodStatusTerima() {
        return kodStatusTerimaDAO.findAll();
    }

    public List<KodStatusTerima> getSenaraiKodStatusTerimaN9() {
        try {
            String query = "SELECT s FROM etanah.model.KodStatusTerima s WHERE s.kod in('XT','TM','TL') ORDER BY s.nama";
            Query q = sessionProvider.get().createQuery(query);
            return q.list();
        } finally {
        }
    }

    public List<KodBatalDokumenKewangan> getSenaraiKodBatalDokumenKewangan() {
        return kodBatalDokumenKewanganDAO.findAll();
    }

    public List<KodAkaun> getSenaraiKodAkaunJenisAmanah() {
        Session s = sessionProvider.get();
        Query q = s.createQuery("select a from etanah.model.KodAkaun a where kategoriAkaun = 'AA' order by a.kod");

        return q.list();
    }

    public List<KodAkaun> getSenaraiKodAkaun() {
        Session s = sessionProvider.get();
        Query q = s.createQuery("select a from etanah.model.KodAkaun a where aktif = 'Y' order by a.kod");

        return q.list();
    }

    public List<KodPenyerah> getSenaraiKodPenyerah() {
//        return kodPenyerahDAO.findAll();
        try {
            Session s = sessionProvider.get();
            Query q = s.createQuery("from etanah.model.KodPenyerah u where u.aktif = :aktif order by u.nama");
            q.setString("aktif", "Y");
            return q.list();
        } finally {
            //session.close();
        }
    }

    public List<KodPenyerah> getSenaraiKodPenyerahFOrUtilityPenyerahMelaka() {
        try {
            Session s = sessionProvider.get();
            Query q = s.createQuery("from etanah.model.KodPenyerah u where u.aktif = :aktif and u.kod not in('05','08') order by u.nama");
            q.setString("aktif", "Y");
            return q.list();
        } finally {
        }
    }

    public List<KodUrusan> getSenaraiUrusanPendaftaran() {
        Session s = sessionProvider.get();
        return s.createQuery("select ku from KodUrusan ku where ku.jabatan.id = '16' and ku.urusanKaunter = 'Y' and ku.aktif='Y' order by ku.kod asc").list();
    }

    public List<KodUrusan> getSenaraiUrusanPendaftaranBelakangKaunter() {
        Session s = sessionProvider.get();
        return s.createQuery("select ku from KodUrusan ku where ku.jabatan.id = '16' and ku.urusanBelakangKaunter ='Y' and ku.aktif='Y'  order by ku.kod asc").list();
    }

    public List<KodUrusan> getUrusanPendaftaranList() {
        Session s = sessionProvider.get();
        return s.createQuery("select ku from KodUrusan ku where ku.jabatan.id = '16' and ku.aktif='Y' order by ku.kod asc").list();
    }

    public List<KodUrusan> getSenaraiKodUrusanPermit() {
        Session s = sessionProvider.get();
        return s.createQuery("select ku from KodUrusan ku where ku.kod in ('PPBB','PBPTD','PBPTG','PBMMK') order by ku.kod asc").list();
    }

    public List<KodItemPermit> getListItemPermit() {
        Session s = sessionProvider.get();
        return s.createQuery("select ku from KodItemPermit ku where ku.aktif='Y' order by ku.kod asc").list();
    }

    public List<KodUrusan> getKodPemilikanList() {
        Session s = sessionProvider.get();
        return s.createQuery("select ku from KodPemilikan ku where ku.aktif='Y' order by ku.kod asc").list();
    }

    public List<KodPemilikan> getKodPemilikanForRAndKOnly() {
        Session s = sessionProvider.get();
        return s.createQuery("select ku from KodPemilikan ku where ku.aktif='Y' and ku.kod in ('R','K') order by ku.kod asc").list();
    }

    public List<KodPemilikan> getKodPemilikanForRAndKAndPOnly() {
        Session s = sessionProvider.get();
        return s.createQuery("select ku from KodPemilikan ku where ku.aktif='Y' and ku.kod in ('R','K','P','S') order by ku.kod asc").list();
    }

    public List<KodPemilikan> getSenaraiKodPemilikan() {
        return kodPemilikanDAO.findAll();
    }

    public List<KodKeputusan> getSenaraiKodKeputusan() {
        return kodKeputusanDAO.findAll();
    }

    public List<KodMatawang> getSenaraiKodMatawang() {
        return kodMatawangDAO.findAll();
    }

    public List<JUBL> getSenaraiJUBL() {
        try {
            return jublDAO.findAll();
        } catch (Exception ex) {
            return null;
        }

    }

    public List<KodJenisPengenalan> getSenaraiJenisPengenalan() {
        try {
            String query = "Select u from etanah.model.KodJenisPengenalan u where u.kod in('B','L','P','T','I','0','N','F') order by u.kod";
            Query q = sessionProvider.get().createQuery(query);
            return q.list();
        } finally {
        }
    }

    public List<KodUrusan> getSenaraiKodUrusanBantahanTanahAdat() {
        try {
            String query = "Select u from etanah.model.KodUrusan u where u.kod in('PMADT','TMADT','CGADT') order by u.nama";
            Query q = sessionProvider.get().createQuery(query);
            return q.list();
        } finally {
        }
    }

    public List<KodDokumen> getSenaraiKodDokumenMMKN() {
        try {
            String query = "Select u from etanah.model.KodDokumen u where u.kod in('MMKN','MMKT') order by u.nama";
            Query q = sessionProvider.get().createQuery(query);
            return q.list();
        } finally {
        }
    }
    
    public List<KodDokumen> getSenaraiKodDokumenJKKPT() {
        try {
            String query = "Select u from etanah.model.KodDokumen u where u.kod in('JKKPT','JKKKT') order by u.nama";
            Query q = sessionProvider.get().createQuery(query);
            return q.list();
        } finally {
        }
    }

    public List<KodUrusan> getSenaraiKodUrusanPembangunanLaporanStatusPermohonan() {
        try {
            String query = "Select u from etanah.model.KodUrusan u where u.kod in('PPCS','PPCB','PYTN','TSPSS','SBMS','TSKKT','TSPSN','TSBSN','TSKSN','PSMT','PSBT') order by u.nama";
            Query q = sessionProvider.get().createQuery(query);
            return q.list();
        } finally {
        }
    }

//    public List<KodUrusan> getSenaraiKodUrusanKodSerah(){
//        try {
//            String query = "Select u from etanah.model.KodUrusan u where u.kod_serah in('B','CR','CS','FH','MD','MH','N','NB','P','SA','SB','SC','SS','ST','SW','Y') order by u.nama";
//            Query q = sessionProvider.get().createQuery(query);
//            return q.list();
//        } finally {
//        }
//    }
    public List<KodSenarai> getSenaraiKodLuak() {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.KodSenarai m WHERE m.kodJabatan.kod = 22 AND m.kod LIKE '%L%'");
        return q.list();
    }

//     public List<KodHakmilik> getKodHakmilikSenaraiNama() {
//        try {
//            String query = "Select u.nama from etanah.model.KodHakmilik u";
//            Query query1 = sessionProvider.get().createQuery(query);
//            return query1.list();
//        } finally {
//            //session.close();
//        }
//    }
//
//     public List<KodSyaratNyata> getKodSyaratNyataSenaraiNama() {
//        try {
//            String query = "Select u.syarat from etanah.model.KodSyaratNyata u";
//            Query query1 = sessionProvider.get().createQuery(query);
//            return query1.list();
//        } finally {
//            //session.close();
//        }
//    }
//
//    public List<KodSekatanKepentingan> getKodSekatanKepentinganSenaraiNama() {
//        try {
//            String query = "Select u.sekatan from etanah.model.KodSekatanKepentingan u";
//            Query query1 = sessionProvider.get().createQuery(query);
//            return query1.list();
//        } finally {
//            //session.close();
//        }
//    }
//
//    public List<KodKegunaanTanah> getKodKegunaanTanahSenaraiNama() {
//        try {
//            String query = "Select u.nama from etanah.model.KodKegunaanTanah u";
//            Query query1 = sessionProvider.get().createQuery(query);
//            return query1.list();
//        } finally {
//            //session.close();
//        }
//    }
    //add by sitifariza.hanim
    public List<KodBahanBakar> getSenaraiKodBahanBakar() {
        return kodBahanBakarDAO.findAll();
    }

    public List<KodJenisBadanKenderaan> getSenaraiKodJenisBadanKenderaan() {
        return kodJenisBadanKenderaanDAO.findAll();
    }

    public List<KodKegunaanKenderaan> getSenaraiKodKegunaanKenderaan() {
        return kodKegunaanKenderaanDAO.findAll();
    }
    //

    public List<KodDokumen> getSenaraiKodDokumen() {
        return kodDokumenDAO.findAll();
    }

    public List<BadanPengurusan> getSenaraiBadanPengurusan() {
        return badanPengurusanDAO.findAll();
    }

    public List<KodKementerian> getSenaraiKodKementerian() {
        return kodKementerianDAO.findAll();
    }

    public List<KodHartaBersama> getSenaraiKodHartaBersama() {
        return kodHartaBersamaDAO.findAll();
    }

    public List<KodKegunaanPetak> getSenaraiKodKegunaanPetak() {
        return kodKegunaanPetakDAO.findAll();
    }

    public List<KodJenisPembangunan> getSenaraiKodJenisPembangunan() {
        return kodJenisPembangunanDAO.findAll();
    }

    public List<KodPeranan> getSenaraiPeranan() {
        try {
            String query = "Select u from etanah.model.KodPeranan u where u.kod in('223','225') order by u.nama";
            Query q = sessionProvider.get().createQuery(query);
            return q.list();
        } finally {
        }
    }

    public List<Pengguna> getSenaraiPendaftar() {
        try {
            String query = "Select u from etanah.model.Pengguna u where u.perananUtama = '4' order by u.nama";
            Session session = sessionProvider.get();
            Query q = session.createQuery(query);
            return q.list();

        } finally {
        }
    }

    public List<Pengguna> getSenaraiPenggunaPendaftarPTG() {
        try {
            String query = "Select u from etanah.model.Pengguna u where u.perananUtama ='4' and u.kodCawangan ='00' order by u.nama";
            Session session = sessionProvider.get();
            Query q = session.createQuery(query);
            return q.list();
        } finally {
        }
    }

    public List<Pengguna> getSenaraiPenggunaPendaftarMT() {
        try {
            String query = "Select u from etanah.model.Pengguna u where u.perananUtama ='4' and u.kodCawangan ='01' order by u.nama";
            Session session = sessionProvider.get();
            Query q = session.createQuery(query);
            return q.list();
        } finally {
        }
    }

    public List<Pengguna> getSenaraiPenggunaPendaftarJasin() {
        try {
            String query = "Select u from etanah.model.Pengguna u where u.perananUtama ='4' and u.kodCawangan ='02' order by u.nama";
            Session session = sessionProvider.get();
            Query q = session.createQuery(query);
            return q.list();

        } finally {
        }
    }

    public List<Pengguna> getSenaraiPenggunaPendaftarAG() {
        try {
            String query = "Select u from etanah.model.Pengguna u where u.perananUtama ='4' and u.kodCawangan ='03' order by u.nama";
            Session session = sessionProvider.get();
            Query q = session.createQuery(query);
            return q.list();

        } finally {
        }
    }

    public List<Pengguna> getSenaraiPenggunaPendaftar04() {
        try {
            String query = "Select u from etanah.model.Pengguna u where u.perananUtama ='4' and u.kodCawangan ='04' order by u.nama";
            Session session = sessionProvider.get();
            Query q = session.createQuery(query);
            return q.list();

        } finally {
        }
    }

    public List<Pengguna> getSenaraiPenggunaPendaftar05() {
        try {
            String query = "Select u from etanah.model.Pengguna u where u.perananUtama ='4' and u.kodCawangan ='05' order by u.nama";
            Session session = sessionProvider.get();
            Query q = session.createQuery(query);
            return q.list();

        } finally {
        }
    }

    public List<Pengguna> getSenaraiPengguna() {
        try {
            String query = "Select u from etanah.model.Pengguna u where u.perananUtama in('223','225') and u.kodCawangan.kod = '01' order by u.nama";
            Session session = sessionProvider.get();
            Query q = session.createQuery(query);
            return q.list();

        } finally {
        }
    }

    public List<Pengguna> getSenaraiP134() { //P134
        try {
            String query = "Select kd from etanah.model.KodDUN kd where kd.kodKawasanParlimen "
                    + "in (select kp.kod from etanah.model.KodKawasanParlimen kp where kp.kod = 'P134')";
            Session session = sessionProvider.get();
            Query q = session.createQuery(query);
            return q.list();
        } finally {
        }
    }

    public List<Pengguna> getSenaraiP135() { //P135
        try {
            String query = "Select kd from etanah.model.KodDUN kd where kd.kodKawasanParlimen "
                    + "in (select kp.kod from etanah.model.KodKawasanParlimen kp where kp.kod = 'P135')";
            Session session = sessionProvider.get();
            Query q = session.createQuery(query);
            return q.list();
        } finally {
        }
    }

    public List<Pengguna> getSenaraiP136() { //P136
        try {
            String query = "Select kd from etanah.model.KodDUN kd where kd.kodKawasanParlimen "
                    + "in (select kp.kod from etanah.model.KodKawasanParlimen kp where kp.kod = 'P136')";
            Session session = sessionProvider.get();
            Query q = session.createQuery(query);
            return q.list();
        } finally {
        }
    }

    public List<Pengguna> getSenaraiP137() { //P137
        try {
            String query = "Select kd from etanah.model.KodDUN kd where kd.kodKawasanParlimen "
                    + "in (select kp.kod from etanah.model.KodKawasanParlimen kp where kp.kod = 'P137')";
            Session session = sessionProvider.get();
            Query q = session.createQuery(query);
            return q.list();
        } finally {
        }
    }

    public List<Pengguna> getSenaraiP138() { //P138
        try {
            String query = "Select kd from etanah.model.KodDUN kd where kd.kodKawasanParlimen "
                    + "in (select kp.kod from etanah.model.KodKawasanParlimen kp where kp.kod = 'P138')";
            Session session = sessionProvider.get();
            Query q = session.createQuery(query);
            return q.list();
        } finally {
        }
    }

    public List<Pengguna> getSenaraiP139() { //P139
        try {
            String query = "Select kd from etanah.model.KodDUN kd where kd.kodKawasanParlimen "
                    + "in (select kp.kod from etanah.model.KodKawasanParlimen kp where kp.kod = 'P139')";
            Session session = sessionProvider.get();
            Query q = session.createQuery(query);
            return q.list();
        } finally {
        }
    }

    public List<Pengguna> getSenaraiPenggunaKerani() {
        try {
            String query = "Select u from etanah.model.Pengguna u where (u.jawatan LIKE 'PEMBANTU TADBIR PENDAFTARAN' OR u.jawatan LIKE 'Pembantu Tadbir Pendaftaran') AND u.kodCawangan = '00' AND u.status.kod <> 'X' order by u.nama";
            Session session = sessionProvider.get();
            Query q = session.createQuery(query);
            return q.list();

        } finally {
        }
    }

    public List<Pengguna> getSenaraiPenggunaPendaftar() {
        try {
            String query = "Select u from etanah.model.Pengguna u where (u.jawatan LIKE 'Pendaftar' OR u.jawatan LIKE 'PENDAFTAR') AND u.kodCawangan = '00'  AND u.status.kod <> 'X' order by u.nama";
            Session session = sessionProvider.get();
            Query q = session.createQuery(query);
            return q.list();

        } finally {
        }
    }

//-----acan
    public List<Pengguna> getSenaraiPenggunaKeraniPTD() {
        try {
            String query = "Select u from etanah.model.Pengguna u where (u.jawatan LIKE 'PEMBANTU TADBIR PENDAFTARAN' OR u.jawatan LIKE 'Pembantu Tadbir Pendaftaran') AND u.kodCawangan = '05' order by u.nama";
            Session session = sessionProvider.get();
            Query q = session.createQuery(query);
            return q.list();

        } finally {
        }
    }

    public List<Pengguna> getSenaraiPenggunaPendaftarPTD() {
        try {
            String query = "Select u from etanah.model.Pengguna u where (u.jawatan LIKE 'Pendaftar' OR u.jawatan LIKE 'PENDAFTAR') AND u.kodCawangan = '05' order by u.nama";
            Session session = sessionProvider.get();
            Query q = session.createQuery(query);
            return q.list();

        } finally {
        }
    }
//-----------acan---------

//added by faiz
    public List<KodUrusan> getSenaraiKodUrusanKodSerah() {
        try {
            String query = "Select u from etanah.model.KodUrusan u where u.kodPerserahan in('B') order by u.nama";
            Query q = sessionProvider.get().createQuery(query);
            return q.list();
        } finally {
        }
    }

    public List<KodUrusan> getSenaraiKodUrusanKodSerahSC() {
        try {
            String query = "Select u from etanah.model.KodUrusan u where u.kodPerserahan in('SC') order by u.nama";
            Query q = sessionProvider.get().createQuery(query);
            return q.list();
        } finally {
        }
    }

    //Added for Batuan - afham 
    public List<KodItemPermit> getSenaraiKodItemPermitBatuan() {
        try {
            String query = "Select u from etanah.model.KodItemPermit u where u.kodKategoriItemPermit.kod in('KBB') order by u.nama";
            Query q = sessionProvider.get().createQuery(query);
            return q.list();
        } finally {
        }
    }
    //Added for PLPS - Shazwan 

    public List<KodItemPermit> getSenaraiKodItemLPS() {
        try {
            String query = "Select u from etanah.model.KodItemPermit u where u.kodKategoriItemPermit.kod in('KLP') order by u.nama";
            Query q = sessionProvider.get().createQuery(query);
            return q.list();
        } finally {
        }
    }

    public List<KodItemPermit> getSenaraiKodItemPRMP() {
        try {
            String query = "Select u from etanah.model.KodItemPermit u where u.kodKategoriItemPermit.kod in('MPP') order by u.nama";
            Query q = sessionProvider.get().createQuery(query);
            return q.list();
        } finally {
        }
    }

    public List<KodPenubuhanSyarikat> getSenaraiKodPenubuhanSyarikat() {
        return kodPenubuhanSyarikatDAO.findAll();
    }

    public List<KodJawatan> getSenaraiKodJawatan() {
        try {
            Session s = sessionProvider.get();
            Query q = s.createQuery("from etanah.model.KodJawatan kj where kj.aktif = 'Y' order by kj.nama");
//            Query q = s.createQuery("Select kj from etanah.model.KodJawatan kj where kj.aktif = :aktif");
//            q.setString("aktif", "Y");
            return q.list();

        } finally {
        }
    }

    public List<KodCawangan> getSenaraiKodCawanganAktif() {
        try {
            Session s = sessionProvider.get();
            Query q = s.createQuery("from etanah.model.KodCawangan kc where kc.aktif = 'Y' order by kc.name");
            return q.list();

        } finally {
        }
    }

    public List<KodNegeri> getSenaraiKodNegeriAktif() {
        try {
            Session s = sessionProvider.get();
            Query q = s.createQuery("from etanah.model.KodNegeri kn where kn.aktif = 'Y' order by kn.nama");
            return q.list();

        } finally {
        }
    }

    public List<KodNegara> getSenaraiKodNegara() {
        try {
            Session s = sessionProvider.get();
            Query q = s.createQuery("from etanah.model.KodNegara kn order by kn.nama");
            return q.list();

        } finally {
        }
    }

    public List<Pengguna> getSenaraiPenggunaAktif() {
        try {
            Session s = sessionProvider.get();
            Query q = s.createQuery("from etanah.model.Pengguna p where p.status = 'A' "
                    + "and p.jawatan is not null order by p.nama asc");
            return q.list();

        } finally {
        }
    }

    public List<KodPerananOperasi> getSenaraiKodPerananOperasi() {
        try {
            Session s = sessionProvider.get();
            Query q = s.createQuery("from etanah.model.KodPerananOperasi a where a.kod != 'K'");
            return q.list();

        } finally {
        }
    }

    public List<KodAgensi> getSenaraiMahkamah() {
        try {
            Session s = sessionProvider.get();
            Query q = s.createQuery("from etanah.model.KodAgensi a where a.kategoriAgensi = 'MKH'");
            return q.list();

        } finally {
        }
    }

    public List<KodAgensi> getUlasan(String idValue) {
        try {
            Session s = sessionProvider.get();
            Query q = s.createQuery("from etanah.model.KodAgensi a where a.kategoriAgensi ='" + idValue + "' ");
            return q.list();

        } finally {
        }
    }

    public List<KodPerananRujukanLuar> getSenaraiKodPerananRujukanLuar() {
        return kodPerananRujukanLuarDAO.findAll();
    }

    public List<KodRizab> getSenaraiKodRizabPelupusan() {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.KodRizab m WHERE m.kod = '1' or m.kod = '3' or m.kod = '2' or m.kod = '4' or m.kod = '99'");
        return q.list();
    }

    public List<KodAgensi> getSenaraiAgensi() {
        try {
            Session s = sessionProvider.get();
            Query q = s.createQuery("from etanah.model.KodAgensi a where a.kod in('0209','0213','0302','0316','0909','0912','1236','1818','6010','3204','6009','210','6013','6014','6015','0')");
            return q.list();

        } finally {
        }
    }

    public List<KodAgensi> getSenaraiAgensiSemua() {
        try {
            Session s = sessionProvider.get();
            Query q = s.createQuery("from etanah.model.KodAgensi a");
            return q.list();

        } finally {
        }
    }

    public List<KodBangsa> getSenaraiKodStatusPenyampaian() {
        try {
            String query = "Select u from etanah.model.KodStatusTerima";
            query += " u where u.kod not in('DD','DT','BP','JM') order by u.nama";
            Query q = sessionProvider.get().createQuery(query);
            return q.list();
        } finally {
        }
    }

    public List<KodHakmilik> getSenaraiKodHakmilikTetapNS() {
        try {
            String query = "Select u from etanah.model.KodHakmilik u where u.kod in('GM','PM','HSM','GRN','PN','HSD') order by u.nama";
            Query q = sessionProvider.get().createQuery(query);
            return q.list();
        } finally {
        }
    }

    public List<KodHakmilik> getSenaraiKodHakmilikTetapMelaka() {
        try {
            String query = "Select u from etanah.model.KodHakmilik u where u.kod in('GM','PM','HSM','GRN','PN','HSD','GMM') order by u.nama";
            Query q = sessionProvider.get().createQuery(query);
            return q.list();
        } finally {
        }
    }

    public List<KodStatusEnkuiri> getSenaraiKodStatusEnkuiri() {
        return kodStatusEnkuiriDAO.findAll();
    }

    public List<KodKeputusanPendakwaan> getSenaraiStatusDakwa() {
        try {
            Session s = sessionProvider.get();
            Query q = s.createQuery("from etanah.model.KodKeputusanPendakwaan kn order by kn.nama");
            return q.list();

        } finally {
        }
    }

    public List<KodSyorUlasanJabatanTeknikal> getSenaraiKodSyorUlasanJabatanTeknikal() {
        return kodSyorUlasanJabatanTeknikalDAO.findAll();
    }

    public List<KodAgensiKutipanCawangan> getSenaraiKodAgensiKutipanCawangan() {
        return kodAgensiKutipanCawanganDAO.findAll();
    }

    public List<KodUnit> getSenaraiKodUnit() {
        try {
            String query = "Select u from etanah.model.KodUnit u where u.skrin = 'Y'";
            Query q = sessionProvider.get().createQuery(query);
            return q.list();
        } finally {
        }
    }

    public List<Projek> getSenaraiProjek() {
        return projekDAO.findAll();
    }

    public List<KodUrusan> getSenaraiUrusanPelupusan() {
        Session s = sessionProvider.get();
        return s.createQuery("select ku from KodUrusan ku where ku.jabatan.id = '2' and ku.urusanKaunter = 'Y' and ku.aktif='Y' order by ku.kod asc").list();
    }

    public List<KodUrusan> getSenaraiUrusanPelupusanBelakangKaunter() {
        Session s = sessionProvider.get();
        return s.createQuery("select ku from KodUrusan ku where ku.jabatan.id = '2' and ku.urusanBelakangKaunter ='Y' and ku.aktif='Y'  order by ku.kod asc").list();
    }

    public List<KodUrusan> getSenaraiKodDokumenByAktif() {
        Session s = sessionProvider.get();
        return s.createQuery("select ku from KodDokumen ku where ku.aktif='Y' order by ku.kod asc").list();
    }

    public List<KodUrusan> getSenaraiKodUrusanByAktif() {
        Session s = sessionProvider.get();
        List<KodUrusan> u = s.createQuery("select u from KodUrusan u "
                + "left join fetch u.jabatan j "
                + "where u.aktif = 'Y' "
                + "order by u.jabatan.nama asc, u.kod asc ") //.setCacheable(true)
                .list();
        return u;
    }

    public List<KodUOM> getKodUOMByJenis() {
        try {
            Session s = sessionProvider.get();
            Query q = s.createQuery("from etanah.model.KodUOM u where u.kump = :kump order by u.nama");
            q.setString("kump", "jnstangkap");
            return q.list();
        } finally {
        }
    }

    public List<KodKelasTanah> getSenaraiKodKelasTanah() {
        return kodKelasTanahDAO.findAll();
    }
    
    public List<KodJenisPindahmilik> getSenaraiJenisPindahmilik() {
        return KodJenisPindahmilikDAO.findAll();
    }
    
    public List<KodMaklumatInduk> getSenaraiKodMaklumatInduk() {
        return KodMaklumatIndukDAO.findAll();
    }
    public List<KodKeputusanMahkamah> getSenaraiKodKpsnMahkamah() {
        return keputusanMahkamahDAO.findAll();
    }
    
    
    
}
