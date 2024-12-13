/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.service.strata.cukaipetak;

import com.google.inject.Inject;
import com.wideplay.warp.persist.Transactional;
import etanah.dao.AkaunDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodCawanganDAO;
import etanah.dao.KodPerananDAO;
import etanah.dao.KodTransaksiDAO;
import etanah.dao.PenggunaDAO;
import etanah.dao.PihakDAO;
import etanah.dao.SkimStrataDAO;
import etanah.dao.TransaksiDAO;
import etanah.model.Akaun;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.InfoAudit;
import etanah.model.Pengguna;
import etanah.model.Pihak;
import etanah.model.SkimStrata;
import etanah.service.NotifikasiService;
import etanah.service.common.HakmilikPihakKepentinganService;
import etanah.view.stripes.nota.PihakBerkepentinganActionBean;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author john
 */
public class JanaCukaiPetak {

    private static final Logger LOG = Logger.getLogger(JanaCukaiPetak.class);
    @Inject
    CukaiPetakService cukaiPetakService;
    @Inject
    SkimStrataDAO skimStrataDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    AkaunDAO akaunDAO;
    @Inject
    PihakDAO pihakDAO;
    @Inject
    TransaksiDAO transaksiDAO;
    @Inject
    KodTransaksiDAO kodTransaksiDAO;
    @Inject
    NotifikasiService notifikasiService;
    @Inject
    KodCawanganDAO kodCawanganDAO;
    @Inject
    HakmilikPihakKepentinganService hakmilikPihakKepentinganService;
    @Inject
    PenggunaDAO penggunaDAO;
    @Inject
    KodPerananDAO kodPerananDAO;
    @Inject
    KalkulatorCukaiPetak kalkulatorCukaiPetak;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    String idHakmilikInduk;
    String idPengguna;
    long idSkimStrata;
    Pengguna pengguna = new Pengguna();
    InfoAudit ia;

    public void setJanaCukaiPetakS(String idHakmilikInduk, String idPengguna, long idSkimStrata) {
        this.idHakmilikInduk = idHakmilikInduk;
        this.idPengguna = idPengguna;
        this.idSkimStrata = idSkimStrata;
        this.pengguna = penggunaDAO.findById(idPengguna);
        this.ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new Date());
        ia.setTarikhKemaskini(new Date());
        ia.setDiKemaskiniOleh(pengguna);

    }

    public void jana() {

        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        SkimStrata skim = skimStrataDAO.findById(idSkimStrata);
        double kadar = 0;
        double kadarLanded = 0;
        String kadarS = cukaiPetakService.findKadar(skim.getKategoriBangunan().getKod(), skim.getKelasTanah().getKod());
        String kadarLandeds = cukaiPetakService.findKadarLanded(skim.getKategoriBangunan().getKod(), skim.getKelasTanah().getKod());
        String kosRendah = skim.getKosRendah();

        if (StringUtils.isBlank(kadarS)) {

        } else {
            kadar = Double.parseDouble(kadarS);
            kadarLanded = Double.parseDouble(kadarLandeds);
        }
        try {
            List<Hakmilik> listHakmilik = cukaiPetakService.findHakmilikStrataByIdInduk(skim.getHakmilikInduk().getIdHakmilik());
            for (int a = 0; a < listHakmilik.size(); a++) {
                Hakmilik ha = listHakmilik.get(a);
                BigDecimal cukai = BigDecimal.ZERO;
                if (checkLandedPetak(ha)) {
                    cukai = getKadarCukai(kadarLanded, ha.getIdHakmilik(), kosRendah);
                } else {
                    cukai = getKadarCukai(kadar, ha.getIdHakmilik(), kosRendah);
                }

                ha.setCukai(cukai);
                ha.setCukaiSebenar(cukai);
                updateHakmilik(ha);
                Akaun akaun = akaunDAO.findById(ha.getIdHakmilik());
                Pihak pemegang = setPihakPemilik(ha);
                akaun.setPemegang(pemegang);
                updateAkaun(akaun);
            }
            skim.setFlagJana("Y");
            skim.setPegJanaCukai(pengguna);
            skim.setTarikhJanaCukai(new Date());
            cukaiPetakService.saveSkimStrata(skim);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            Throwable t = e;
            // getting the root cause
            while (t.getCause() != null) {
                t = t.getCause();
            }
            t.printStackTrace();
            LOG.error(t);
            throw new RuntimeException(t);
        } finally {
//            Transaction txs = s.beginTransaction();
            //sendNotification and update flag
//            KodCawangan cawangan = kodCawanganDAO.findById("00");
//            KodPeranan peranan = kodPerananDAO.findById("23");
//            Notifikasi notifikasi = new Notifikasi();
//            notifikasi.setCawangan(cawangan);
//            notifikasi.setMesej("Keseluruah Cukai Petak bagi Hakmilik Induk "+skim.getHakmilikInduk().getIdHakmilik()+" telah berjaya dijana dan bersedia untuk disemak.");
////            notifikasi.setPengguna(pengguna);
//            notifikasi.setTajuk("Cukai Petak Telah Siap dijana");
//            notifikasi.setInfoAudit(ia);
//            notifikasiService.addRoleToNotifikasi(notifikasi, cawangan, peranan);
//            txs.commit();

        }
    }

    private BigDecimal getKadarCukai(double kadar, String idHakmilik, String kosRendah) {
        BigDecimal hak = cukaiPetakService.luasHakmilikStrata(idHakmilik);
        BigDecimal aks = cukaiPetakService.luasHakmilikAksByidStrata(idHakmilik);
        BigDecimal total = hak.add(aks != null ? aks : new BigDecimal(BigInteger.ZERO));
        BigDecimal a = new BigDecimal(BigInteger.ZERO);
        a = kalkulatorCukaiPetak.calc(total.doubleValue(), kadar);
        if (kosRendah.equals("Y")) {
            if (0 < a.compareTo(BigDecimal.valueOf(10))) {
                return a;
            } else {
                return new BigDecimal(10);
            }
        } else {
            if (0 < a.compareTo(BigDecimal.valueOf(15))) {
                return a;
            } else {
                return new BigDecimal(15);
            }
        }

    }

    @Transactional
    private void updateHakmilik(Hakmilik ha) {
        hakmilikDAO.save(ha);
    }

    private boolean checkLandedPetak(Hakmilik hakmilik) {
        boolean a = false;
        if (hakmilik.getKodKategoriBangunan().getKod().equals("L")) {
            a = true;
        } else if (hakmilik.getKodKategoriBangunan().getKod().equals("L")) {
            a = true;
        } else {
            a = false;
        }
        return a;
    }

    private Pihak setPihakPemilik(Hakmilik hakmilik) {
        List< HakmilikPihakBerkepentingan> listHpb;
        if (checkProvisional(hakmilik)) {
            listHpb = hakmilikPihakKepentinganService.findHakmilikPihakActiveByHakmilik(hakmilikDAO.findById(hakmilik.getIdHakmilikInduk()));
        } else {
            listHpb = hakmilikPihakKepentinganService.findHakmilikPihakActiveByHakmilik(hakmilik);

        }
        Pihak p = new Pihak();
        if (!listHpb.isEmpty()) {
            for (HakmilikPihakBerkepentingan hpb : listHpb) {
//                p = new Pihak();
                p.setNama(hpb.getNama());
                p.setAlamat1(hpb.getAlamat1());
                p.setAlamat2(hpb.getAlamat2());
                p.setAlamat3(hpb.getAlamat3());
                p.setAlamat4(hpb.getAlamat4());
                p.setNoPengenalan(hpb.getNoPengenalan());
                p.setJenisPengenalan(hpb.getJenisPengenalan());
                p.setPoskod(hpb.getPoskod());
                p.setNegeri(hpb.getNegeri());
                if (hpb.getAlamatSurat() != null) {
                    p.setSuratAlamat1(hpb.getAlamatSurat().getAlamatSurat1());
                    p.setSuratAlamat2(hpb.getAlamatSurat().getAlamatSurat2());
                    p.setSuratAlamat3(hpb.getAlamatSurat().getAlamatSurat3());
                    p.setSuratAlamat4(hpb.getAlamatSurat().getAlamatSurat4());
                    p.setSuratNegeri(hpb.getAlamatSurat().getNegeriSurat());
                    p.setSuratPoskod(hpb.getAlamatSurat().getPoskodSurat());
                }
                updatePihak(p);
                if (hpb.getJenis().getKod().equals("PM")) {
                    break;
                }
            }
        } else {
            listHpb = hakmilikPihakKepentinganService.findHakmilikPihakActiveByHakmilik(hakmilikDAO.findById(hakmilik.getIdHakmilikInduk()));

            for (HakmilikPihakBerkepentingan hpb : listHpb) {
//                p = new Pihak();
                p.setNama(hpb.getNama());
                p.setAlamat1(hpb.getAlamat1());
                p.setAlamat2(hpb.getAlamat2());
                p.setAlamat3(hpb.getAlamat3());
                p.setAlamat4(hpb.getAlamat4());
                p.setNoPengenalan(hpb.getNoPengenalan());
                p.setJenisPengenalan(hpb.getJenisPengenalan());
                p.setPoskod(hpb.getPoskod());
                p.setNegeri(hpb.getNegeri());
                if (hpb.getAlamatSurat() != null) {
                    p.setSuratAlamat1(hpb.getAlamatSurat().getAlamatSurat1());
                    p.setSuratAlamat2(hpb.getAlamatSurat().getAlamatSurat2());
                    p.setSuratAlamat3(hpb.getAlamatSurat().getAlamatSurat3());
                    p.setSuratAlamat4(hpb.getAlamatSurat().getAlamatSurat4());
                    p.setSuratNegeri(hpb.getAlamatSurat().getNegeriSurat());
                    p.setSuratPoskod(hpb.getAlamatSurat().getPoskodSurat());
                }
                updatePihak(p);
                if (hpb.getJenis().getKod().equals("PM")) {
                    break;
                }
            }

        }
        return p;
    }

    @Transactional
    private void updateAkaun(Akaun a) {
        akaunDAO.save(a);
    }

    @Transactional
    private void updatePihak(Pihak p) {
        pihakDAO.save(p);
    }

    private boolean checkProvisional(Hakmilik hakmilik) {
        boolean a = false;
        a = hakmilik.getKodKategoriBangunan().getKod().equals("P");
        return a;
    }

}
