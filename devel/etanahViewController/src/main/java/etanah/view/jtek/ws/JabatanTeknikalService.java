/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.jtek.ws;

import com.google.inject.Injector;
import etanah.dao.KodStatusUlasanJabatanTeknikalDAO;
import etanah.dao.KodSyorUlasanJabatanTeknikalDAO;
import etanah.dao.PortalPenggunaDAO;
import etanah.dao.UlasanJabatanTeknikalDAO;
import etanah.model.Dokumen;
import etanah.model.HakmilikPermohonan;
import etanah.model.JteknikalDokumenTerima;
import etanah.model.KodStatusUlasanJabatanTeknikal;
import etanah.model.KodSyorUlasanJabatanTeknikal;
import etanah.model.LaporanTanahSempadan;
import etanah.model.Pemohon;
import etanah.model.PortalPengguna;
import etanah.model.UlasanJabatanTeknikal;
import etanah.view.etanahContextListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author faidzal
 */
public class JabatanTeknikalService implements IJabatanTeknikalService {

    private static Injector injector = etanahContextListener.getInjector();
    public JTEKServices service = injector.getInstance(JTEKServices.class);
    public UlasanJabatanTeknikalDAO ulasJTEKDAO = injector.getInstance(UlasanJabatanTeknikalDAO.class);
    // public List<SenaraiTugasanJTEK> senaraiTugasan = new ArrayList<SenaraiTugasanJTEK>();
    KodSyorUlasanJabatanTeknikalDAO kodSyorUlasanJabatanTeknikalDAO = injector.getInstance(KodSyorUlasanJabatanTeknikalDAO.class);
    KodStatusUlasanJabatanTeknikalDAO kodStatusUlasanJabatanTeknikalDAO = injector.getInstance(KodStatusUlasanJabatanTeknikalDAO.class);
    UlasanJabatanTeknikalDAO ulasanJabatanTeknikalDAO = injector.getInstance(UlasanJabatanTeknikalDAO.class);
    public List<InfoPemohon> listPemohon = new ArrayList<InfoPemohon>();
    public List<InfoDok> listDok = new ArrayList<InfoDok>();
    public InfoPenggunaPortal ipp = new InfoPenggunaPortal();
    public SempadanTanahInfo sempadanInfo = new SempadanTanahInfo();
    PortalPenggunaDAO portalPenggunaDAO = injector.getInstance(PortalPenggunaDAO.class);

    @Override
    public List<SenaraiTugasanJTEK> senaraiTugasanJTEK1(String idJTEK) {
        List<SenaraiTugasanJTEK> senaraiTugasan = new ArrayList<SenaraiTugasanJTEK>();
        //JTEKServices service = new JTEKServices();
        List<UlasanJabatanTeknikal> lujt = service.getTugasan(idJTEK);
        for (UlasanJabatanTeknikal ujt : lujt) {
            SenaraiTugasanJTEK sj = new SenaraiTugasanJTEK();
            sj.setIdPermohonan(ujt.getPermohonan().getIdPermohonan());
            sj.setIdjtekulas(String.valueOf(ujt.getIdJtekUlas()));
            sj.setKodUrusan(ujt.getPermohonan().getKodUrusan().getKod());
            sj.setNamaUrusan(ujt.getPermohonan().getKodUrusan().getNama());
            sj.setTrhjtekterima(ujt.getInfoAudit().getTarikhMasuk());
            sj.setTrhmohon(ujt.getPermohonan().getInfoAudit().getTarikhMasuk());
            sj.setDaerah(ujt.getPermohonan().getCawangan().getName());
            sj.setKodDaerah(ujt.getPermohonan().getCawangan().getKod());
            sj.setKodStatus(ujt.getKodStatusUlasanJabatanTeknikal().getKod());
            sj.setHariLewat(kiraHariLewat(ujt.getInfoAudit().getTarikhMasuk()));
            senaraiTugasan.add(sj);
        }

        return senaraiTugasan;
    }

    @Override
    public List<SenaraiTugasanJTEK> senaraiTugasanJTEK1(String idJTEK, String daerah, String tarikhMula, String tarikhTamat) {
        List<SenaraiTugasanJTEK> senaraiTugasan = new ArrayList<SenaraiTugasanJTEK>();
        //JTEKServices service = new JTEKServices();

        List<UlasanJabatanTeknikal> lujt = service.getTugasan(idJTEK, daerah, tarikhMula, tarikhTamat);
        for (UlasanJabatanTeknikal ujt : lujt) {
            SenaraiTugasanJTEK sj = new SenaraiTugasanJTEK();
            sj.setIdPermohonan(ujt.getPermohonan().getIdPermohonan());
            sj.setIdjtekulas(String.valueOf(ujt.getIdJtekUlas()));
            sj.setKodUrusan(ujt.getPermohonan().getKodUrusan().getKod());
            sj.setNamaUrusan(ujt.getPermohonan().getKodUrusan().getNama());
            sj.setTrhjtekterima(ujt.getInfoAudit().getTarikhMasuk());
            sj.setTrhmohon(ujt.getPermohonan().getInfoAudit().getTarikhMasuk());
            sj.setDaerah(ujt.getPermohonan().getCawangan().getName());
            sj.setKodDaerah(ujt.getPermohonan().getCawangan().getKod());
            sj.setKodStatus(ujt.getKodStatusUlasanJabatanTeknikal().getKod());
            sj.setHariLewat(kiraHariLewat(ujt.getInfoAudit().getTarikhMasuk()));
            senaraiTugasan.add(sj);
        }

        return senaraiTugasan; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<SenaraiTugasanJTEK> senaraiTugasanJTEK1(String idJTEK, String idPermohonan) {
        List<SenaraiTugasanJTEK> senaraiTugasan = new ArrayList<SenaraiTugasanJTEK>();
        //JTEKServices service = new JTEKServices();
        List<UlasanJabatanTeknikal> lujt = service.getTugasan(idJTEK, idPermohonan);
        for (UlasanJabatanTeknikal ujt : lujt) {
            SenaraiTugasanJTEK sj = new SenaraiTugasanJTEK();
            sj.setIdPermohonan(ujt.getPermohonan().getIdPermohonan());
            sj.setIdjtekulas(String.valueOf(ujt.getIdJtekUlas()));
            sj.setKodUrusan(ujt.getPermohonan().getKodUrusan().getKod());
            sj.setNamaUrusan(ujt.getPermohonan().getKodUrusan().getNama());
            sj.setTrhjtekterima(ujt.getInfoAudit().getTarikhMasuk());
            sj.setTrhmohon(ujt.getPermohonan().getInfoAudit().getTarikhMasuk());
            sj.setDaerah(ujt.getPermohonan().getCawangan().getName());
            sj.setKodDaerah(ujt.getPermohonan().getCawangan().getKod());
            sj.setKodStatus(ujt.getKodStatusUlasanJabatanTeknikal().getKod());
            sj.setHariLewat(kiraHariLewat(ujt.getInfoAudit().getTarikhMasuk()));
            senaraiTugasan.add(sj);
        }

        return senaraiTugasan; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<InfoPemohon> infoPemohon1(String idPermohonan) {

        listPemohon = new ArrayList<InfoPemohon>();
        listPemohon.clear();
        for (Pemohon pe : service.getPemohon(idPermohonan)) {
            InfoPemohon ip = new InfoPemohon();
            ip.setIdPermohonan(pe.getPermohonan().getIdPermohonan());
            ip.setNama(pe.getPihak().getNama());
            ip.setNoTel(pe.getPihak().getNoTelefon1());
            ip.setEmail(pe.getPihak().getEmail());
            ip.setAlamat(pe.getPihak().getAlamat1() + ", "
                    + "" + pe.getPihak().getAlamat2() + ", " + pe.getPihak().getAlamat3() + ", " + pe.getPihak().getAlamat3());
            // ip.setPihak(pe.getPihak());
            listPemohon.add(ip);
        }
        return listPemohon;
    }

    @Override
    public List<InfoTanah> infoTanah1(String idPermohonan) {

        List<InfoTanah> listTanah = new ArrayList<InfoTanah>();
        for (HakmilikPermohonan hp : service.getHakmilikPermohonan(idPermohonan)) {
            InfoTanah it = new InfoTanah();
            if (hp.getHakmilik() != null) {
                it.setBpm(hp.getHakmilik().getBandarPekanMukim() != null ? hp.getHakmilik().getBandarPekanMukim().getNama() : "Tiada Maklumat");
                it.setIdHakmilik(hp.getHakmilik().getIdHakmilik());
                it.setNoLot(hp.getHakmilik().getLot().getNama() + " " + hp.getHakmilik().getNoLot());
                it.setLuas(hp.getHakmilik().getLuas() != null ? String.valueOf(hp.getHakmilik().getLuas()) + " " + hp.getHakmilik().getKodUnitLuas().getNama() : "Tiada Maklumat");
                it.setPajakan(hp.getHakmilik().getPegangan() != null ? String.valueOf(hp.getHakmilik().getPegangan()) : "Tiada Maklumat");
                it.setSekatan(hp.getHakmilik().getSekatanKepentingan() != null ? hp.getHakmilik().getSekatanKepentingan().getSekatan() : "Tiada Maklumat");
                it.setSyaratNyata(hp.getHakmilik() != null ? hp.getHakmilik().getSyaratNyata().getSyarat() : "Tiada Maklumat");
                it.setUpi(hp.getHakmilik().getUPI());
                it.setLokasi(hp.getHakmilik().getLokasi());
                it.setJenisGeran(hp.getHakmilik().getKodHakmilik() != null ? hp.getHakmilik().getKodHakmilik().getNama() : "Tiada Maklumat");
                it.setDaerah(hp.getHakmilik().getDaerah() != null ? hp.getHakmilik().getDaerah().getNama() : "Tiada Maklumat");
            } else {
                it.setBpm(hp.getBandarPekanMukimBaru() != null ? hp.getBandarPekanMukimBaru().getNama() : "Tiada Maklumat");
                it.setIdHakmilik("Tiada Maklumat");
                it.setNoLot(hp.getLot() != null ? hp.getLot().getNama() + "" + hp.getNoLot() : "Tiada Maklumat");
                it.setLuas(hp.getLuasTerlibat() + " " + hp.getKodUnitLuas().getNama());
                it.setPajakan(hp.getTempohPajakan() != null ? String.valueOf(hp.getTempohPajakan()) : "Tiada Maklumat");
                it.setSekatan(hp.getSekatanKepentingan() != null ? hp.getSekatanKepentingan().getSekatan() : "Tiada Maklumat");
                it.setSyaratNyata(hp.getSyaratNyata() != null ? hp.getSyaratNyata().getSyarat() : "Tiada Maklumat");
                it.setUpi(null);
                it.setLokasi(hp.getLokasi());
                it.setJenisGeran(hp.getKodHakmilik() != null ? hp.getKodHakmilik().getNama() : "Tiada Maklumat");
                it.setDaerah(hp.getBandarPekanMukimBaru().getDaerah() != null ? hp.getBandarPekanMukimBaru().getDaerah().getNama() : "Tiada Maklumat");

            }
            listTanah.add(it);
        }
        return listTanah;
    }

    @Override
    public List<InfoDok> infoDokumenTerima1(String idjtekulas) {

        listDok = new ArrayList<InfoDok>();
        listDok.clear();
        for (Dokumen d : service.getDokumenList(idjtekulas)) {
            InfoDok id = new InfoDok();
            id.setFormat(d.getFormat());
            id.setIdDokumen(String.valueOf(d.getIdDokumen()));
            id.setKeterangan(d.getTajuk());
            id.setNamaFizikal(d.getNamaFizikal());
            listDok.add(id);
        }
        return listDok;
    }

    @Override
    public List<InfoDok> infoDokumenHantar1(String idjtekulas) {
        listDok = new ArrayList<InfoDok>();
        listDok.clear();
        UlasanJabatanTeknikal ujt = ulasJTEKDAO.findById(Long.valueOf(idjtekulas));
        for (JteknikalDokumenTerima jdt : ujt.getSenaraiDokumenTerima()) {
            InfoDok id = new InfoDok();
            id.setFormat(jdt.getDokumen().getFormat());
            id.setIdDokumen(String.valueOf(jdt.getDokumen().getIdDokumen()));
            id.setKeterangan(jdt.getDokumen().getTajuk());
            id.setNamaFizikal(jdt.getNamaFizikal());
            id.setIdDokJtekUlas(String.valueOf(jdt.getIdJtekDokTerima()));
            listDok.add(id);
        }
        return listDok;
    }

    @Override
    public InfoJtekUlas jtekInfo(String idjtekulas) {
        InfoJtekUlas ujt = new InfoJtekUlas();
        UlasanJabatanTeknikal ujt1 = service.getJtekInfo(idjtekulas);
        if (ujt1 != null) {
            ujt.setIdjtekulas(String.valueOf(ujt1.getIdJtekUlas()));
            ujt.setKodSts(ujt1.getKodStatusUlasanJabatanTeknikal() != null ? ujt1.getKodStatusUlasanJabatanTeknikal().getKod() : null);
            ujt.setKodSyor(ujt1.getKodSyorUlasanJabatanTeknikal() != null ? ujt1.getKodSyorUlasanJabatanTeknikal().getKod() : null);
            ujt.setNamaPenyelia(ujt1.getNamaPenyelia());
            ujt.setNoRujukan(ujt1.getNoRuj());
            ujt.setTarikhSedia(ujt1.getTrhSedia());
            ujt.setUlasan(ujt1.getUlasan());
            ujt.setIdPermohonan(ujt1.getPermohonan().getIdPermohonan());
            ujt.setStatusPermohonan(ujt1.getKodStatusUlasanJabatanTeknikal() != null ? ujt1.getKodStatusUlasanJabatanTeknikal().getKod() : "");
        }
        return ujt;
    }

    @Override
    public InfoJtekUlas hantarLaporan(String namaPenyelia, String kodSyor, String Ulasan, Date tarikhSedia, String noRujukan, String kodSts, String idjtekulas) {
        InfoJtekUlas ujt = new InfoJtekUlas();
        UlasanJabatanTeknikal ujt1 = ulasanJabatanTeknikalDAO.findById(Long.valueOf(idjtekulas));
        ujt1.setNamaPenyelia(namaPenyelia);
        KodSyorUlasanJabatanTeknikal ksuj = kodSyorUlasanJabatanTeknikalDAO.findById(kodSyor);
        KodStatusUlasanJabatanTeknikal ksujt = kodStatusUlasanJabatanTeknikalDAO.findById(kodSts);
        ujt1.setKodStatusUlasanJabatanTeknikal(ksujt);
        ujt1.setKodSyorUlasanJabatanTeknikal(ksuj);
        ujt1.setNoRuj(noRujukan);
        ujt1.setUlasan(Ulasan);
        if (tarikhSedia != null) {
            ujt1.setTrhSedia(tarikhSedia);
        } else {
            ujt1.setTrhSedia(new Date());
        }
        ujt1.setTrhSelesai(new Date());

        ujt1 = service.setUlasanJabatanTeknikal(ujt1);
        if (ksujt.getKod().equals("SEL")) {
            service.updateMohonRujLuar(ujt1);
        }
        return ujt;
    }

    @Override
    public SempadanTanahInfo sempadanTanahInfo1(String idPermohonan, String idHakmilik) {

        List<LaporanTanahSempadan> llts = service.getLaporanTanah(idPermohonan, idHakmilik);
        for (LaporanTanahSempadan lts : llts) {
            LaporTanahInfo lti = new LaporTanahInfo();
            if (lts.getJenisSempadan().equals("U")) {
                lti = new LaporTanahInfo();
                lti.setIdHakmilik(lts.getHakmilik() != null ? lts.getHakmilik().getIdHakmilik() : null);
                lti.setKeadaanTanah(lts.getKeadaanTanah());
                lti.setKegunaanTanah(lts.getGuna());
                lti.setMilikKerajaan(lts.getMilikKerajaan());
                lti.setSempadan(lts.getJenisSempadan());
                lti.setUpi(lts.getHakmilik() != null ? lts.getHakmilik().getUPI() : null);
                sempadanInfo.setTanahUtara(lti);
            }
            if (lts.getJenisSempadan().equals("T")) {
                lti = new LaporTanahInfo();
                lti.setIdHakmilik(lts.getHakmilik() != null ? lts.getHakmilik().getIdHakmilik() : null);
                lti.setKeadaanTanah(lts.getKeadaanTanah());
                lti.setKegunaanTanah(lts.getGuna());
                lti.setMilikKerajaan(lts.getMilikKerajaan());
                lti.setSempadan(lts.getJenisSempadan());
                lti.setUpi(lts.getHakmilik() != null ? lts.getHakmilik().getUPI() : null);
                sempadanInfo.setTanahTimur(lti);
            }
            if (lts.getJenisSempadan().equals("S")) {
                lti = new LaporTanahInfo();
                lti.setIdHakmilik(lts.getHakmilik() != null ? lts.getHakmilik().getIdHakmilik() : null);
                lti.setKeadaanTanah(lts.getKeadaanTanah());
                lti.setKegunaanTanah(lts.getGuna());
                lti.setMilikKerajaan(lts.getMilikKerajaan());
                lti.setSempadan(lts.getJenisSempadan());
                lti.setUpi(lts.getHakmilik() != null ? lts.getHakmilik().getUPI() : null);
                sempadanInfo.setTanahSelatan(lti);
            }
            if (lts.getJenisSempadan().equals("B")) {
                lti = new LaporTanahInfo();
                lti.setIdHakmilik(lts.getHakmilik() != null ? lts.getHakmilik().getIdHakmilik() : null);
                lti.setKeadaanTanah(lts.getKeadaanTanah());
                lti.setKegunaanTanah(lts.getGuna());
                lti.setMilikKerajaan(lts.getMilikKerajaan());
                lti.setSempadan(lts.getJenisSempadan());
                lti.setUpi(lts.getHakmilik() != null ? lts.getHakmilik().getUPI() : null);
                sempadanInfo.setTanahBarat(lti);
            }
        }
        return sempadanInfo;
    }

    @Override
    public InfoPenggunaPortal infoPenggunaPortal1(String idPengguna, String password) {
        ipp = new InfoPenggunaPortal();
        PortalPengguna pp = service.getPenggunPortal(idPengguna);
        if (pp.getKodAgensi() != null) {
            ipp.setAgensi(pp.getKodAgensi().getNama());
            ipp.setKodAgensi(pp.getKodAgensi().getKod());
        }

        if (pp.getKodAgensiKutipan() != null) {
            ipp.setKodAgensiKutipan(pp.getKodAgensiKutipan().getKod());
            ipp.setNamaKutipan(pp.getKodAgensiKutipan().getNama());
            ipp.setKutipan(true);
        }
        ipp.setEmail(pp.getEmail());
        ipp.setIdPguna(pp.getIdPguna());
        ipp.setNama(pp.getNama());
        ipp.setNoKp(pp.getNoKp());
        ipp.setNoTel(pp.getNoTel());
        ipp.setPasswd(pp.getPasswd());
        ipp.setTrhLogin(pp.getTrhLogin());
        if (StringUtils.isNotBlank(password) && password.equals(pp.getPasswd())) {
            ipp.setAuth(true);
        } else {
            ipp.setAuth(false);
        }
        return ipp;
    }

    @Override
    public InfoPenggunaPortal kemaskiniProfil(InfoPenggunaPortal ip) {
        PortalPengguna pp = portalPenggunaDAO.findById(ip.getIdPguna());
        pp.setEmail(ip.getEmail());
        pp.setNama(ip.getNama());
        pp.setNoKp(ip.getNoKp());
        pp.setPasswd(ip.getPasswd());
        pp.setNoTel(ip.getNoTel());
        pp = service.UpdatePenggunPortal(pp);
        ip.setAgensi(pp.getKodAgensi().getNama());
        ip.setKodAgensi(pp.getKodAgensi().getKod());
        ip.setEmail(pp.getEmail());
        ip.setIdPguna(pp.getIdPguna());
        ip.setNama(pp.getNama());
        ip.setNoKp(pp.getNoKp());
        ip.setNoTel(pp.getNoTel());
        ip.setPasswd(pp.getPasswd());
        ip.setTrhLogin(pp.getTrhLogin());
        return ip;

    }

    @Override
    public List<SenaraiTugasanJTEK> tugasanSelesai(String idJTEK) {
        List<SenaraiTugasanJTEK> senaraiTugasanJTEK = new ArrayList<SenaraiTugasanJTEK>();
        List<UlasanJabatanTeknikal> lujt = service.getTugasanSelesai(idJTEK);
        for (UlasanJabatanTeknikal ujt : lujt) {
            SenaraiTugasanJTEK sj = new SenaraiTugasanJTEK();
            sj.setIdPermohonan(ujt.getPermohonan().getIdPermohonan());
            sj.setIdjtekulas(String.valueOf(ujt.getIdJtekUlas()));
            sj.setKodUrusan(ujt.getPermohonan().getKodUrusan().getKod());
            sj.setNamaUrusan(ujt.getPermohonan().getKodUrusan().getNama());
            sj.setTrhjtekterima(ujt.getInfoAudit().getTarikhMasuk());
            sj.setTrhmohon(ujt.getPermohonan().getInfoAudit().getTarikhMasuk());
            sj.setTarikhSelesai(ujt.getTrhSelesai());
            sj.setKodStatus(ujt.getKodStatusUlasanJabatanTeknikal().getKod());
            sj.setDaerah(ujt.getPermohonan().getCawangan().getName());
            sj.setKodDaerah(ujt.getPermohonan().getCawangan().getKod());
            sj.setHariLewat(kiraHariLewat(ujt.getInfoAudit().getTarikhMasuk()));
            senaraiTugasanJTEK.add(sj);
        }
        return senaraiTugasanJTEK;
    }

    public static Injector getInjector() {
        return injector;
    }

    public static void setInjector(Injector injector) {
        JabatanTeknikalService.injector = injector;
    }

    public JTEKServices getService() {
        return service;
    }

    public void setService(JTEKServices service) {
        this.service = service;
    }

    public UlasanJabatanTeknikalDAO getUlasJTEKDAO() {
        return ulasJTEKDAO;
    }

    public void setUlasJTEKDAO(UlasanJabatanTeknikalDAO ulasJTEKDAO) {
        this.ulasJTEKDAO = ulasJTEKDAO;
    }

    public List<InfoPemohon> getListPemohon() {
        return listPemohon;
    }

    public void setListPemohon(List<InfoPemohon> listPemohon) {
        this.listPemohon = listPemohon;
    }

    public List<InfoDok> getListDok() {
        return listDok;
    }

    public void setListDok(List<InfoDok> listDok) {
        this.listDok = listDok;
    }

    public InfoPenggunaPortal getIpp() {
        return ipp;
    }

    public void setIpp(InfoPenggunaPortal ipp) {
        this.ipp = ipp;
    }

    public SempadanTanahInfo getSempadanInfo() {
        return sempadanInfo;
    }

    public void setSempadanInfo(SempadanTanahInfo sempadanInfo) {
        this.sempadanInfo = sempadanInfo;
    }

    @Override
    public List<KodSyor> listKodSyorUlasanJabatanTeknikal() {
        List<KodSyor> syor = new ArrayList<KodSyor>();
        List<KodSyorUlasanJabatanTeknikal> list = new ArrayList<KodSyorUlasanJabatanTeknikal>();
        list = kodSyorUlasanJabatanTeknikalDAO.findAll();
        for (KodSyorUlasanJabatanTeknikal kod : list) {
            KodSyor k = new KodSyor();
            k.setKod(kod.getKod());
            k.setNama(kod.getNama());
            syor.add(k);
        }

        return syor;
    }

    @Override
    public String daftarPengguna(InfoPenggunaPortal ipp) {
        String s = new String();
        s = service.daftarPengguna(ipp);
        return s;
    }

    @Override
    public List<SenaraiTugasanJTEK> tugasanSelesai(String idJTEK, String daerah, String tarikhMula, String tarikhTamat) {
        List<SenaraiTugasanJTEK> senaraiTugasan = new ArrayList<SenaraiTugasanJTEK>();
        //JTEKServices service = new JTEKServices();

        List<UlasanJabatanTeknikal> lujt = service.getTugasanSelesai(idJTEK, daerah, tarikhMula, tarikhTamat);
        for (UlasanJabatanTeknikal ujt : lujt) {
            SenaraiTugasanJTEK sj = new SenaraiTugasanJTEK();
            sj.setIdPermohonan(ujt.getPermohonan().getIdPermohonan());
            sj.setIdjtekulas(String.valueOf(ujt.getIdJtekUlas()));
            sj.setKodUrusan(ujt.getPermohonan().getKodUrusan().getKod());
            sj.setNamaUrusan(ujt.getPermohonan().getKodUrusan().getNama());
            sj.setTrhjtekterima(ujt.getInfoAudit().getTarikhMasuk());
            sj.setTrhmohon(ujt.getPermohonan().getInfoAudit().getTarikhMasuk());
            sj.setDaerah(ujt.getPermohonan().getCawangan().getName());
            sj.setKodDaerah(ujt.getPermohonan().getCawangan().getKod());
            sj.setKodStatus(ujt.getKodStatusUlasanJabatanTeknikal().getKod());
            sj.setHariLewat(kiraHariLewat(ujt.getInfoAudit().getTarikhMasuk()));
            senaraiTugasan.add(sj);
        }

        return senaraiTugasan; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<SenaraiTugasanJTEK> tugasanSelesai(String idJTEK, String idPermohonan) {
        List<SenaraiTugasanJTEK> senaraiTugasan = new ArrayList<SenaraiTugasanJTEK>();
        //JTEKServices service = new JTEKServices();
        List<UlasanJabatanTeknikal> lujt = service.getTugasanSelesai(idJTEK, idPermohonan);
        for (UlasanJabatanTeknikal ujt : lujt) {
            SenaraiTugasanJTEK sj = new SenaraiTugasanJTEK();
            sj.setIdPermohonan(ujt.getPermohonan().getIdPermohonan());
            sj.setIdjtekulas(String.valueOf(ujt.getIdJtekUlas()));
            sj.setKodUrusan(ujt.getPermohonan().getKodUrusan().getKod());
            sj.setNamaUrusan(ujt.getPermohonan().getKodUrusan().getNama());
            sj.setTrhjtekterima(ujt.getInfoAudit().getTarikhMasuk());
            sj.setTrhmohon(ujt.getPermohonan().getInfoAudit().getTarikhMasuk());
            sj.setDaerah(ujt.getPermohonan().getCawangan().getName());
            sj.setKodDaerah(ujt.getPermohonan().getCawangan().getKod());
            sj.setKodStatus(ujt.getKodStatusUlasanJabatanTeknikal().getKod());
            sj.setHariLewat(kiraHariLewat(ujt.getInfoAudit().getTarikhMasuk()));
            senaraiTugasan.add(sj);
        }

        return senaraiTugasan; //To change body of generated methods, choose Tools | Templates.
    }

    private String kiraHariLewat(Date tarikhMasuk) {
        String hari = new String();
        SimpleDateFormat fmt = new SimpleDateFormat("MM/dd/yyyy");
        long exp = (tarikhMasuk.getTime() - new Date().getTime()) / (24 * 60 * 60 * 1000);
        hari = String.valueOf(exp);
        return hari;
    }

}
