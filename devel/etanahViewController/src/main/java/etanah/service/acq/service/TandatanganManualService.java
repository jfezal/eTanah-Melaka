/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.service.acq.service;

import com.google.inject.Inject;
import com.wideplay.warp.persist.Transactional;
import etanah.dao.BorangPerHakmilikDAO;
import etanah.dao.BorangPerPBDAO;
import etanah.dao.BorangPerPermohonanDAO;
import etanah.dao.KodNotisDAO;
import etanah.dao.PenggunaDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanTandatanganDokumenDAO;
import etanah.model.Alamat;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodNotis;
import etanah.model.Pengguna;
import etanah.model.PenggunaPeranan;
import etanah.model.PermohonanTandatanganDokumen;
import etanah.model.ambil.BorangPerHakmilik;
import etanah.model.ambil.BorangPerPB;
import etanah.model.ambil.BorangPerPermohonan;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author mohd.faidzal
 */
public class TandatanganManualService {

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    KodNotisDAO kodNotisDAO;
    @Inject
    PenggunaDAO penggunaDAO;
    @Inject
    BorangPerPermohonanDAO borangPerPermohonanDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PermohonanTandatanganDokumenDAO permohonanTandatanganDokumenDAO;
    @Inject
    BorangPerHakmilikDAO borangPerHakmilikDAO;
    @Inject
    BorangPerPBDAO borangPerPBDAO;

    public List<PenggunaPeranan> listSenaraiTandatangan(String list[]) {
        String query = "select p from etanah.model.PenggunaPeranan p"
                + " where p.peranan.kumpBPEL in (:list)";
        Query q = sessionProvider.get().createQuery(query);
        q.setParameterList("list", list);
        return q.list();
    }

    public void simpanBykodNotis(String[] kodNotis, String tandatangan, String idPermohonan) {
        Session s = sessionProvider.get();
        Transaction txn = s.beginTransaction();
        Pengguna p = penggunaDAO.findById(tandatangan);
        List<HakmilikPermohonan> listHp = permohonanDAO.findById(idPermohonan).getSenaraiHakmilik();
        Alamat a = new Alamat();

        if (contains(kodNotis, "NAA") || contains(kodNotis, "NBB") || contains(kodNotis, "NBC") || contains(kodNotis, "NBD") || contains(kodNotis, "NBO")
                || contains(kodNotis, "NBM")) {
            BorangPerPermohonan bpp = findByKodNotis(kodNotis, idPermohonan);
            if (bpp == null) {
                bpp = new BorangPerPermohonan();
                KodNotis kod = kodNotisDAO.findById(kodNotis[0]);
                bpp.setKodNotis(kod);
                bpp.setPermohonan(permohonanDAO.findById(idPermohonan));
                InfoAudit infoAudit = new InfoAudit();
                infoAudit.setDimasukOleh(p);
                infoAudit.setTarikhMasuk(new Date());
                bpp.setInfoAudit(infoAudit);
            }
            bpp.setDitandatangan(p);
            saveBorangPerPermohonan(bpp);
        }
        if (contains(kodNotis, "NBE") || contains(kodNotis, "NBK") || contains(kodNotis, "NBJ") || contains(kodNotis, "NBG")) {
            List<BorangPerHakmilik> lBph = findBorangPerHakmilikByKodNotis(kodNotis, idPermohonan);
            if (lBph.size() > 0) {
                for (BorangPerHakmilik bph : lBph) {
                    bph.setDitandatangan(p.getIdPengguna());
                    saveBorangPerHakmilik(bph);
                }
            } else {

                if (listHp.size() > 0) {
                    for (HakmilikPermohonan hp : listHp) {
                        if (hp.getHakmilik() != null) {
                            BorangPerHakmilik bph = new BorangPerHakmilik();
                            bph.setHakmilikPermohonan(hp);
                            bph.setInfoAudit(setIA(p));
                            bph.setKodNotis(kodNotisDAO.findById(kodNotis[0]));
                            saveBorangPerHakmilik(bph);
                        }
                    }
                    List<BorangPerHakmilik> lBph1 = findBorangPerHakmilikByKodNotis(kodNotis, idPermohonan);
                    if (lBph1.size() > 0) {
                        for (BorangPerHakmilik bph : lBph1) {
                            bph.setDitandatangan(p.getIdPengguna());
                            saveBorangPerHakmilik(bph);
                        }
                    }
                }
            }

        }
        if (contains(kodNotis, "NBL")) {
            String[] kodNotisBaru = new String[1];
            kodNotisBaru[0] = "NBK";
            String[] kodNotisBaru2 = new String[1];
            kodNotisBaru2[0] = "NBH";
            List<BorangPerPB> lBpb = findBorangPerPBbyKodNotis(kodNotis, idPermohonan);
            if (lBpb.size() > 0) {
                for (BorangPerPB pb : lBpb) {
                    pb.setDitandatangan(p.getIdPengguna());
                    saveBorangPerPB(pb);
                }
            } else {
                List<BorangPerHakmilik> lBph = findBorangPerHakmilikByKodNotis(kodNotisBaru, idPermohonan);
                List<BorangPerPB> lBpb2 = findBorangPerPBbyKodNotis(kodNotisBaru2, idPermohonan);
                if (lBpb2.size() > 0) {
                    for (BorangPerPB pb : lBpb2) {
                        for (BorangPerHakmilik bph : lBph) {

                            BorangPerPB perPB = new BorangPerPB();
                            perPB.setBorangPerHakmilik(bph);
//                            perPB.setBorangPerPb(perPB);
                            perPB.setKodNotis(kodNotisDAO.findById(kodNotis[0]));
                            perPB.setInfoAudit(setIA(p));
                            saveBorangPerPB(perPB);

                        }
                    }
                }
                List<BorangPerPB> lBpb1 = findBorangPerPBbyKodNotis(kodNotis, idPermohonan);
                if (lBpb1.size() > 0) {
                    for (BorangPerPB pb : lBpb1) {
                        pb.setDitandatangan(p.getIdPengguna());
                        saveBorangPerPB(pb);
                    }
                }
            }
        } else if (contains(kodNotis, "NBF") || contains(kodNotis, "NBH")) {
            List<BorangPerPB> lBpb = findBorangPerPBbyKodNotis(kodNotis, idPermohonan);
            if (lBpb.size() > 0) {
                for (BorangPerPB pb : lBpb) {
                    pb.setDitandatangan(p.getIdPengguna());
                    saveBorangPerPB(pb);
                }
            } else {
//                if ()

                List<BorangPerHakmilik> lBph = findBorangPerHakmilikByKodNotis(kodNotis, idPermohonan);
                for (BorangPerHakmilik bph : lBph) {
                    BorangPerPB perPB = new BorangPerPB();
                    perPB.setBorangPerHakmilik(bph);
                    perPB.setKodNotis(kodNotisDAO.findById(kodNotis[0]));
                    perPB.setInfoAudit(setIA(p));
                    saveBorangPerPB(perPB);

                }
                List<BorangPerPB> lBpb1 = findBorangPerPBbyKodNotis(kodNotis, idPermohonan);
                if (lBpb1.size() > 0) {
                    for (BorangPerPB pb : lBpb1) {
                        pb.setDitandatangan(p.getIdPengguna());
                        saveBorangPerPB(pb);
                    }
                }
            }

        }

        txn.commit();
    }

    InfoAudit setIA(Pengguna pengguna
    ) {
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new Date());
        return ia;
    }

    public BorangPerPermohonan findByKodNotis(String[] kodNotis, String idPermohonan) {
        String query = "select p from etanah.model.ambil.BorangPerPermohonan p"
                + " where p.permohonan.idPermohonan =:idPermohonan and p.kodNotis.kod in (:kodNotis)";
        Query q = sessionProvider.get().createQuery(query);
        q.setParameterList("kodNotis", kodNotis);
        q.setString("idPermohonan", idPermohonan);
        return (BorangPerPermohonan) q.uniqueResult();
    }

    public PermohonanTandatanganDokumen findByttByIDMohon(String idPermohonan, String kodDokumen) {
        String query = "select p from etanah.model.PermohonanTandatanganDokumen p"
                + " where p.permohonan.idPermohonan = :idPermohonan and p.kodDokumen.kod = :kodDokumen ";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("kodDokumen", kodDokumen);
        return (PermohonanTandatanganDokumen) q.uniqueResult();
    }

    @Transactional
    private void saveBorangPerPermohonan(BorangPerPermohonan bpp) {
        bpp.setTrh_tt(new Date());
        borangPerPermohonanDAO.saveOrUpdate(bpp);
    }

    @Transactional
    public void saveMohonDoktt(PermohonanTandatanganDokumen permohonanTandatanganDokumen) {
        permohonanTandatanganDokumen.setTrhTt(new Date());
        permohonanTandatanganDokumenDAO.saveOrUpdate(permohonanTandatanganDokumen);
    }

    public List<BorangPerHakmilik> findBorangPerHakmilikByKodNotis(String[] kodNotis, String idPermohonan) {
        String query = "select p from etanah.model.ambil.BorangPerHakmilik p"
                + " where p.hakmilikPermohonan.permohonan.idPermohonan = :idPermohonan and p.kodNotis.kod in (:kodNotis) ";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setParameterList("kodNotis", kodNotis);
        return q.list();
    }

    @Transactional
    private void saveBorangPerHakmilik(BorangPerHakmilik bph) {
        borangPerHakmilikDAO.saveOrUpdate(bph);
    }

    public List<BorangPerPB> findBorangPerPBbyKodNotis(String[] kodNotis, String idPermohonan) {
        String query = "select p from etanah.model.ambil.BorangPerPB p"
                + " where p.borangPerHakmilik.hakmilikPermohonan.permohonan.idPermohonan = :idPermohonan and p.kodNotis.kod in (:kodNotis) ";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setParameterList("kodNotis", kodNotis);
        return q.list();
    }

    @Transactional
    private void saveBorangPerPB(BorangPerPB pb) {
        borangPerPBDAO.save(pb);
    }

    public static boolean contains(final String[] array, final String v) {

        boolean result = false;

        for (String i : array) {
            if (i.equals(v)) {
                result = true;
                break;
            }
        }

        return result;
    }
}
