/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.portal.ws;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.wideplay.warp.persist.Transactional;
import etanah.dao.KodStatusPenggunaDAO;
import etanah.dao.KodTransaksiDAO;
import etanah.dao.PenggunaDAO;
import etanah.dao.PortalPenggunaDAO;
import etanah.model.InfoAudit;
import etanah.model.KandunganFolder;
import etanah.model.KodBandarPekanMukim;
import etanah.model.KodDaerah;
import etanah.model.KodHakmilik;
import etanah.model.KodLot;
import etanah.model.KodSeksyen;
import etanah.model.KonfigurasiSistem;
import etanah.model.PortalPengguna;
import etanah.model.PortalTransaksi;
import etanah.model.Transaksi;
import etanah.view.etanahContextListener;
import etanah.view.uam.MailService;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author faidzal
 */
public class UAMPortal {

    private static Injector injector = etanahContextListener.getInjector();
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    PortalPenggunaDAO portalPenggunaDAO;
    @Inject
    PenggunaDAO pgunaDAO;
    @Inject
    KodStatusPenggunaDAO kodStatusPenggunaDAO;
    @Inject
    MailService mail;
    @Inject
    KodTransaksiDAO kodTransaksiDAO;
    public static final int password_Length = 8;
    private String passwordGenerate = "";
    protected static java.util.Random random = new java.util.Random();

    protected static char[] goodChar = {'a', 'b', 'c', 'd', 'e', 'f', 'g',
        'h', 'i', 'j', 'k', 'l', 'm', 'n', 'p', 'q', 'r', 's', 't', 'u', 'v',
        'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
        'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y',
        'Z', '1', '2', '3', '4', '5', '6', '7', '8', '9', '_', '-', '@'};

    PortalPengguna login(String idPengguna, String katalaluan) {
        String query = "SELECT pp FROM etanah.model.PortalPengguna pp"
                + " where pp.passwd = :katalaluan "
                + "and pp.idPguna = :idPengguna ";
        Session session = injector.getProvider(Session.class).get();
        Query q = session.createQuery(query);
        q.setString("katalaluan", katalaluan);
        q.setString("idPengguna", idPengguna);
        return (PortalPengguna) q.uniqueResult();

    }

    public void daftarPengguna(FormDaftarPengguna fd) throws IOException {
        PortalPengguna pp = portalPenggunaDAO.findById(fd.getIdPguna());
        if (pp == null) {
            pp = new PortalPengguna();
            pp.setIdPguna(fd.getIdPguna());
            String d[] = {fd.getEmail()};
            mail.sendEmail(d, "Pendaftaran Pengguna Baru", "Tahniah anda berjaya mendaftar", "05");

        }
        pp.setNama(fd.getNama());
        pp.setEmail(fd.getEmail());
        pp.setNoKp(fd.getNoKp());
        pp.setNoTel(fd.getNoTel());
        pp.setPasswd(fd.getPasswd());
        pp.setKodSts(kodStatusPenggunaDAO.findById("A").getKod());
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pgunaDAO.findById("portal"));
        pp.setTrhLogin(new Date());
        ia.setTarikhMasuk(new Date());
        pp.setInfoAudit(ia);
        savePortalPengguna(pp);

    }

    @Transactional
    public PortalPengguna savePortalPengguna(PortalPengguna pp) {
        return portalPenggunaDAO.saveOrUpdate(pp);
    }

    public List<SejarahTransaksiPortal> sejarahTransaksi(String idPengguna, String tarikhmula, String tarikhtamat) {
        List<SejarahTransaksiPortal> listSejarah = new ArrayList<SejarahTransaksiPortal>();
        String query = "SELECT pt FROM etanah.model.PortalTransaksi pt where"
                + " pt.idPengguna = :idPengguna ";
        Session session = injector.getProvider(Session.class).get();
        Query q = session.createQuery(query);
        q.setString("idPengguna", idPengguna);

        for (int i = 0; i < q.list().size(); i++) {
            SejarahTransaksiPortal sejarah = new SejarahTransaksiPortal();
            PortalTransaksi portal = new PortalTransaksi();
            portal = (PortalTransaksi) q.list().get(i);
            sejarah.setAmaun(portal.getAmaun());
            sejarah.setIdHakmilik(portal.getHakmilik().getIdHakmilik());
            sejarah.setIdkewdok(portal.getIdKewDok());
            sejarah.setJenistrans(portal.getJenisTrans().equals("CU") ? "Cukai Tanah" : "Carian Persendirian");
            sejarah.setTarikhResit(portal.getTrhResit() != null ? formatDate(portal.getTrhResit()) : "");
            sejarah.setTarikhTrasaksi(formatDate(portal.getInfoAudit().getTarikhMasuk()));
            sejarah.setNofpx(portal.getIdTransAsal());
            sejarah.setStatus(StringUtils.isNotBlank(portal.getNoFpx()) ? "Berjaya" : "Tidak Berjaya");
            listSejarah.add(sejarah);
        }

        return listSejarah;
    }

    public String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat();
        return sdf.format(date);
    }

    public List<UtilKod> listKod(String jenisKod, String condition) {
        List<UtilKod> list = new ArrayList<UtilKod>();
        String query = new String();
        Session session = injector.getProvider(Session.class).get();
        Query q = null;
        if (jenisKod.equals("daerah")) {
            query = "SELECT kd FROM etanah.model.KodDaerah kd"
                    + " where kd.aktif = 'Y' ";
            q = session.createQuery(query);
            for (int i = 0; i < q.list().size(); i++) {
                KodDaerah kh = (KodDaerah) q.list().get(i);
                UtilKod kod = new UtilKod();
                kod.setKod(kh.getKod());
                kod.setNama(kh.getNama());
                list.add(kod);

            }
        }
        if (jenisKod.equals("mukim")) {
            query = "SELECT kd FROM etanah.model.KodBandarPekanMukim kd"
                    + " where kd.aktif = 'Y' and kd.daerah.kod = :koddaerah ";
            q = session.createQuery(query);
            q.setString("koddaerah", condition);
            for (int i = 0; i < q.list().size(); i++) {
                KodBandarPekanMukim kh = (KodBandarPekanMukim) q.list().get(i);
                UtilKod kod = new UtilKod();
                kod.setKod(String.valueOf(kh.getKod()));
                kod.setNama(kh.getNama());
                list.add(kod);

            }
        }
        if (jenisKod.equals("hakmilik")) {
            query = "SELECT kd FROM etanah.model.KodHakmilik kd"
                    + " where kd.aktif = 'Y' ";
            q = session.createQuery(query);
            for (int i = 0; i < q.list().size(); i++) {
                KodHakmilik kh = (KodHakmilik) q.list().get(i);
                UtilKod kod = new UtilKod();
                kod.setKod(kh.getKod());
                kod.setNama(kh.getNama());
                list.add(kod);

            }
        }
        if (jenisKod.equals("lot")) {
            query = "SELECT kd FROM etanah.model.KodLot kd"
                    + " where kd.aktif = 'Y' ";
            q = session.createQuery(query);
            for (int i = 0; i < q.list().size(); i++) {
                KodLot kh = (KodLot) q.list().get(i);
                UtilKod kod = new UtilKod();
                kod.setKod(kh.getKod());
                kod.setNama(kh.getNama());
                list.add(kod);

            }
        }
        if (jenisKod.equals("seksyen")) {
            query = "SELECT kd FROM etanah.model.KodSeksyen kd"
                    + " where kd.aktif = 'Y' and kd.kodBandarPekanMukim.kod = :bpm";
            q = session.createQuery(query);
            q.setString("bpm", condition);
            for (int i = 0; i < q.list().size(); i++) {
                KodSeksyen kh = (KodSeksyen) q.list().get(i);
                UtilKod kod = new UtilKod();
                kod.setKod(String.valueOf(kh.getKod()));
                kod.setNama(kh.getNama());
                list.add(kod);

            }
        }

        return list;
    }

    public FormDaftarPengguna profilPengguna(String idPengguna) {
        PortalPengguna pp = portalPenggunaDAO.findById(idPengguna);
        FormDaftarPengguna fd = new FormDaftarPengguna();
        if (pp != null) {
            fd.setIdPguna(pp.getIdPguna());
            fd.setEmail(pp.getEmail());
            fd.setNama(pp.getNama());
            fd.setNoKp(pp.getNoKp());
            fd.setNoTel(pp.getNoTel());
            fd.setPasswd(pp.getPasswd());
            fd.setTrhLogin(formatDate(pp.getTrhLogin()));
        }
        return fd;
    }

    public Konfigurasi setConfig(String key) {
        Konfigurasi c = new Konfigurasi();
        String query = "SELECT ks FROM etanah.model.KonfigurasiSistem ks"
                + " where ks.kumpulan = :key and ks.aktif = 'Y'";
        Session session = injector.getProvider(Session.class).get();
        Query q = session.createQuery(query);
        q.setString("key", key);
        List<KonfigurasiSistem> listKonfig = new ArrayList<KonfigurasiSistem>();
        listKonfig = q.list();
        c = new Konfigurasi();
        for (KonfigurasiSistem kss : listKonfig) {

            if (kss.getNama().equals("max_amaunt_carian")) {
                c.setMaxAmout(new BigDecimal(kss.getNilai()));
            } else if (kss.getNama().equals("merchant_code_carian")) {
                c.setMerchantCode(kss.getNilai());
            } else if (kss.getNama().equals("encryption_key_caria")) {
                c.setEncryptionKey(kss.getNilai());
            } else if (kss.getNama().equals("api_key_carian")) {
                c.setApi_key(kss.getNilai());
            } else if (kss.getNama().equals("max_amaunt_carian")) {
                c.setMaxAmout(new BigDecimal(kss.getNilai()));
            } else if (kss.getNama().equals("merchant_code_caria1")) {
                c.setMerchantCode(kss.getNilai());
            } else if (kss.getNama().equals("encryption_key_cari1")) {
                c.setEncryptionKey(kss.getNilai());
            } else if (kss.getNama().equals("api_key_carian1")) {
                c.setApi_key(kss.getNilai());
            } else if (kss.getNama().equals("max_amaunt_carian1")) {
                c.setMaxAmout(new BigDecimal(kss.getNilai()));
            } else if (kss.getNama().equals("merchant_code_cukai")) {
                c.setMerchantCode(kss.getNilai());
            } else if (kss.getNama().equals("encryption_key_cukai")) {
                c.setEncryptionKey(kss.getNilai());
            } else if (kss.getNama().equals("api_key_cukai1")) {
                c.setApi_key(kss.getNilai());
            } else if (kss.getNama().equals("max_amaunt_carian1")) {
                c.setMaxAmout(new BigDecimal(kss.getNilai()));
            } else if (kss.getNama().equals("merchant_code_cuka1")) {
                c.setMerchantCode(kss.getNilai());
            } else if (kss.getNama().equals("encryption_key_cuka1")) {
                c.setEncryptionKey(kss.getNilai());
            } else if (kss.getNama().equals("api_key_cukai")) {
                c.setApi_key(kss.getNilai());
            } else if (kss.getKumpulan().equals("EPS")) {
                c.setValue(kss.getNilai());
            }

        }
        return c;
    }

    public boolean lupakatalaluan(String idPengguna) throws IOException {
        PortalPengguna pp = portalPenggunaDAO.findById(idPengguna);
        if (pp != null) {
            String[] mail1 = {pp.getEmail()};
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < password_Length; i++) {
                sb.append(goodChar[random.nextInt(goodChar.length)]);
            }
            passwordGenerate = sb.toString();
            pp.setPasswd(passwordGenerate);
            pp.setTrhKKiniKatalaluan(new Date());
            savePortalPengguna(pp);
            return mail.sendEmail(mail1, "Lupa katalaluan", "Kata laluan anda telah dikemaskini kepada " + passwordGenerate + ". Sila kemaskini semula katalaluan anda. Terima kasih.", "05");

        } else {
            return false;
        }
    }

    List<KandunganFolder> getSenaraiKandunganFolder(long folderId) {
        List<KandunganFolder> list = new ArrayList<KandunganFolder>();
        String query = "SELECT pt FROM etanah.model.KandunganFolder pt where"
                + " pt.folder.folderId = :folderId ";
        Session session = injector.getProvider(Session.class).get();
        Query q = session.createQuery(query);
        q.setLong("folderId", folderId);
        return q.list();
    }

    Transaksi getTransaksiByNoResit(String idDokumenKewangan) {

        String query = "SELECT pt FROM etanah.model.Transaksi pt where"
                + " pt.dokumenKewangan.idDokumenKewangan = :idDokumenKewangan ";
        Session session = injector.getProvider(Session.class).get();
        Query q = session.createQuery(query);
        q.setString("idDokumenKewangan", idDokumenKewangan);

        return (Transaksi) q.uniqueResult();
    }

}
