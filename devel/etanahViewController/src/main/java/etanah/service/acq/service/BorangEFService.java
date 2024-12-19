/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.service.acq.service;

import com.google.inject.Inject;
import com.wideplay.warp.persist.Transactional;
import etanah.dao.KodNotisDAO;
import etanah.dao.NotaSiasatanDokumenDAO;
import etanah.dao.NotaSiasatanLengkapDAO;
import etanah.model.Alamat;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.InfoAudit;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.ambil.BorangPerHakmilik;
import etanah.model.ambil.BorangPerPB;
import etanah.model.ambil.HakmilikPerbicaraan;
import etanah.model.ambil.HantarBorangPB;
import etanah.model.ambil.NotaSiasatanDokumen;
import etanah.model.ambil.NotaSiasatanLengkap;
import etanah.model.ambil.TampalBorangHakmilik;
import etanah.model.ambil.TuntutanPerPB;
import etanah.view.stripes.pengambilan.share.form.BorangEForm;
import etanah.view.stripes.pengambilan.share.form.BorangFForm;
import etanah.view.stripes.pengambilan.share.form.BorangFTuntutanForm;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author mohd.faidzal
 */
public class BorangEFService {

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    BorangACQService borangACQService;
    @Inject
    MaklumatHakmilikService maklumatHakmilikService;
    @Inject
    NotaSiasatanLengkapDAO notaSiasatanLengkapDAO;
    @Inject
    KodNotisDAO kodNotisDAO;
    @Inject
    NotaSiasatanDokumenDAO notaSiasatanDokumenDAO;

    public void generateBorangEF(Permohonan permohonan, Pengguna pengguna) {
        for (HakmilikPermohonan mh : maklumatHakmilikService.findHakmilikPermohonanNotTDK(permohonan.getIdPermohonan())) {
            BorangPerHakmilik bph = new BorangPerHakmilik();
            bph.setHakmilikPermohonan(mh);
            bph.setInfoAudit(setIA(pengguna));
            bph.setKodNotis(kodNotisDAO.findById("NBE"));
            borangACQService.saveBorangPerhakmilik(bph);
            for (HakmilikPihakBerkepentingan hp : maklumatHakmilikService.findListPihakKepentinganBerdaftar(mh.getHakmilik().getIdHakmilik())) {
                BorangPerPB bpp = new BorangPerPB();
                bpp.setBorangPerHakmilik(bph);
                bpp.setKodNotis(kodNotisDAO.findById("NBF"));
                bpp.setInfoAudit(setIA(pengguna));
                Alamat a = new Alamat();
                a.setAlamat1(hp.getAlamat1());
                a.setAlamat2(hp.getAlamat2());
                a.setAlamat3(hp.getAlamat3());
                a.setAlamat4(hp.getAlamat4());
                a.setNegeri(hp.getNegeri());
                a.setPoskod(hp.getPoskod());
                bpp.setAlamat(a);
                bpp.setJenisPengenalan(hp.getJenisPengenalan());
                bpp.setJenis_kepentingan(hp.getJenis().getNama());
                bpp.setNama(hp.getNama());
                bpp.setNoPengenalan(hp.getNoPengenalan());
                bpp.setSyerPembilang(hp.getSyerPembilang());
                bpp.setSyerPenyebut(hp.getSyerPenyebut());
                borangACQService.saveBorangPerPB(bpp);
            }
        }
    }

    InfoAudit setIA(Pengguna pengguna) {
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new Date());
        return ia;
    }

    public List<BorangEForm> findHakmilikAktifNotTDK(String idPermohonan) {
        List<BorangEForm> b = new ArrayList<BorangEForm>();
        for (BorangPerHakmilik mh : listBorangPerHakmilikE(idPermohonan)) {
            BorangEForm e = new BorangEForm();
            e.setId(mh.getId());
            e.setHm(mh.getHakmilikPermohonan());
            e.setDok(mh.getDokumen());
            e.setTandatangan(mh.getDitandatangan());
            e.setJumlahPihak(findBorangPB(idPermohonan, mh.getHakmilikPermohonan().getId(), "NBF"));
            e.setStatus(findStatusBorangPB(idPermohonan, mh.getHakmilikPermohonan().getId(), "NBF") == 0 ? "Selesai" : "Dalam Proses");
            e.setHakmilikPerbicaraan(findHakmilikPerbicaraanByIdMH(mh.getHakmilikPermohonan().getId()));
            b.add(e);
        }
        return b;
    }

    public List<BorangEForm> findHakmilikAktifNotTDKNew(String idPermohonan) {
        List<BorangEForm> b = new ArrayList<BorangEForm>();
        for (BorangPerHakmilik mh : listBorangPerHakmilikH(idPermohonan)) {
            BorangEForm e = new BorangEForm();
            e.setId(mh.getId());
            e.setHm(mh.getHakmilikPermohonan());
            e.setDok(mh.getDokumen());
            e.setTandatangan(mh.getDitandatangan());
            e.setJumlahPihak(findBorangPB(idPermohonan, mh.getHakmilikPermohonan().getId(), "NBH"));
            e.setStatus(findStatusBorangPB(idPermohonan, mh.getHakmilikPermohonan().getId(), "NBH") == 0 ? "Selesai" : "Dalam Proses");
            b.add(e);
        }
        return b;
    }

    public List<BorangEForm> findHakmilikAktifNotTDKG(String idPermohonan) {
        List<BorangEForm> b = new ArrayList<BorangEForm>();
        for (BorangPerHakmilik mh : listBorangPerHakmilikG(idPermohonan)) {
            BorangEForm e = new BorangEForm();
            e.setId(mh.getId());
            e.setHm(mh.getHakmilikPermohonan());
            e.setDok(mh.getDokumen());
            e.setTandatangan(mh.getDitandatangan());
            e.setJumlahPihak(findBorangPB(idPermohonan, mh.getHakmilikPermohonan().getId(), "NBF"));
            e.setStatus(findStatusBorangPB(idPermohonan, mh.getHakmilikPermohonan().getId(), "NBF") == 0 ? "Selesai" : "Dalam Proses");
            b.add(e);
        }
        return b;
    }

    public List<BorangPerHakmilik> listBorangPerHakmilikE(String idPermohonan) {
        String query = "select bph from  etanah.model.ambil.BorangPerHakmilik bph,"
                + " etanah.model.HakmilikPermohonan mh "
                + " where bph.hakmilikPermohonan.id = mh.id and mh.permohonan.idPermohonan = :idPermohonan and bph.kodNotis.kod = 'NBE'";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<BorangPerHakmilik> listBorangPerHakmilikH(String idPermohonan) {
        String query = "select bph from  etanah.model.ambil.BorangPerHakmilik bph,"
                + " etanah.model.HakmilikPermohonan mh "
                + " where bph.hakmilikPermohonan.id = mh.id and mh.permohonan.idPermohonan = :idPermohonan and bph.kodNotis.kod = 'NBH'";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<BorangPerHakmilik> listBorangPerHakmilikG(String idPermohonan) {
        String query = "select bph from  etanah.model.ambil.BorangPerHakmilik bph,"
                + " etanah.model.HakmilikPermohonan mh "
                + " where bph.hakmilikPermohonan.id = mh.id and mh.permohonan.idPermohonan = :idPermohonan and bph.kodNotis.kod = 'NBG'";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    private Long findStatusBorangPB(String idPermohonan, long id, String kod) {
        String query = "select count(p) from etanah.model.ambil.BorangPerPB p, "
                + "etanah.model.ambil.BorangPerHakmilik bph, etanah.model.ambil.NotaSiasatanLengkap nsl, "
                + " etanah.model.HakmilikPermohonan mh "
                + " where p.borangPerHakmilik.id = bph.id and bph.hakmilikPermohonan.id = mh.id "
                + " and mh.permohonan.idPermohonan = :idPermohonan and mh.id = :id and p.kodNotis.kod =:kod and nsl.borangPerPB.id = p.id"
                + " and nsl.flagBicara is null";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setLong("id", id);
        q.setString("kod", kod);
        return (Long) q.uniqueResult();
    }
    

    private Long findBorangPB(String idPermohonan, long id, String kod) {
        String query = "select count(p) from etanah.model.ambil.BorangPerPB p, "
                + "etanah.model.ambil.BorangPerHakmilik bph,"
                + " etanah.model.HakmilikPermohonan mh "
                + " where p.borangPerHakmilik.id = bph.id and bph.hakmilikPermohonan.id = mh.id "
                + "and mh.permohonan.idPermohonan = :idPermohonan and mh.id = :id and p.kodNotis.kod =:kod";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setLong("id", id);
        q.setString("kod", kod);
        return (Long) q.uniqueResult();
    }

    public List<BorangFForm> listBorangH(String idPermohonan, String idHakmilik, String Jenis) {
        List<BorangFForm> lf = new ArrayList<BorangFForm>();
        if (Jenis.equals("BORANGH")) {
            for (BorangPerPB pb : findBorangFIdMohonIdHakmilik(idPermohonan, idHakmilik, "NBH")) {
                BorangFForm f = new BorangFForm();
                f.setBppb(pb);
                f.setBpH(findBorangPerPB(pb, "NBH"));
                NotaSiasatanLengkap nsl = findNotaSiasatanByPerPB(pb);
                f.setNama(pb.getNama());
//                f.setAlamat(pb.getAlamat());
                f.setNoPengenalan(pb.getNoPengenalan());
                if (nsl != null) {

                    f.setMasaBicara(nsl.getMasaBicara());
                    f.setTempatBicara(nsl.getTempatBicara());
                    f.setNoBicara(nsl.getNoBicara());
                    f.setTarikhBicara(nsl.getTarikhBicara());
                    f.setStatusBicara(nsl.getFlagBicara());
                }
                if (f.getBpH() != null) {
                    if (findHantarPerPB(f.getBpH()).isEmpty()) {
                        f.setHantarH(Boolean.FALSE);
                    } else {
                        f.setHantarH(Boolean.TRUE);
                    }
                }
                if (findHantarPerPB(pb).isEmpty()) {
                    f.setHantar(Boolean.FALSE);
                } else {
                    f.setHantar(Boolean.TRUE);
                }
                BorangFTuntutanForm ff = new BorangFTuntutanForm();
                ff = borangACQService.countAmount(ff, pb);
                f.setJumlahTuntutanBaru(ff.getAmaun());
                lf.add(f);
            }
        }

        if (Jenis.equals("BORANGF")) {
            for (BorangPerPB pb : findBorangFIdMohonIdHakmilik(idPermohonan, idHakmilik, "NBF")) {
                for (BorangFForm bFFm : listBorangH(idPermohonan, idHakmilik, Jenis)) {
                    BorangFTuntutanForm ff = new BorangFTuntutanForm();
//                ff = borangACQService.countAmount(ff, pb);
                    bFFm.setJumlahTuntutan(ff.getAmaun());
                    lf.add(bFFm);
                }
            }

        }

        return lf;
    }

    public List<BorangFForm> listBorangF(String idPermohonan, String idHakmilik) {
        List<BorangFForm> lf = new ArrayList<BorangFForm>();
        for (BorangPerPB pb : findBorangFIdMohonIdHakmilik(idPermohonan, idHakmilik, "NBF")) {
            BorangFForm f = new BorangFForm();
            f.setBppb(pb);
            f.setBpH(findBorangPerPB(pb, "NBH"));
            NotaSiasatanLengkap nsl = findNotaSiasatanByPerPB(pb);
            if (nsl != null) {
                f.setMasaBicara(nsl.getMasaBicara());
                f.setTempatBicara(nsl.getTempatBicara());
                f.setNoBicara(nsl.getNoBicara());
                f.setTarikhBicara(nsl.getTarikhBicara());
                f.setStatusBicara(nsl.getFlagBicara());
            }
            if (f.getBpH() != null) {
                if (findHantarPerPB(f.getBpH()).isEmpty()) {
                    f.setHantarH(Boolean.FALSE);
                } else {
                    f.setHantarH(Boolean.TRUE);
                }
            }
            if (findHantarPerPB(pb).isEmpty()) {
                f.setHantar(Boolean.FALSE);
            } else {
                f.setHantar(Boolean.TRUE);
            }
            BorangFTuntutanForm ff = new BorangFTuntutanForm();
            ff = borangACQService.countAmount(ff, pb);
            f.setJumlahTuntutan(ff.getAmaun());
            lf.add(f);
        }
        return lf;
    }

    public List<BorangPerPB> findBorangFIdMohonIdHakmilik(String idPermohonan, String idHakmilik, String kod) {
        String query = "select p from etanah.model.ambil.BorangPerPB p, "
                + "etanah.model.ambil.BorangPerHakmilik bph,"
                + " etanah.model.HakmilikPermohonan mh "
                + " where p.borangPerHakmilik.id = bph.id and bph.hakmilikPermohonan.id = mh.id "
                + "and mh.permohonan.idPermohonan = :idPermohonan and mh.hakmilik.idHakmilik = :idHakmilik and p.kodNotis.kod =:kod";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("idHakmilik", idHakmilik);
        q.setString("kod", kod);
        return q.list();
    }

    public NotaSiasatanLengkap findNotaSiasatanByPerPB(BorangPerPB pb) {
        String query = "select nsl from etanah.model.ambil.BorangPerPB p, etanah.model.ambil.NotaSiasatanLengkap nsl"
                + " where nsl.borangPerPB.id = p.id "
                + " and p.id = :id";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("id", pb.getId() + "");
        return (NotaSiasatanLengkap) q.uniqueResult();
    }

    private List<HantarBorangPB> findHantarPerPB(BorangPerPB pb) {
        String query = "select p from etanah.model.ambil.HantarBorangPB p "
                + " where p.borangPerPB.id = :id ";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("id", pb.getId() + "");
        return q.list();
    }
    
    public HantarBorangPB findHantarPerPB1(BorangPerPB pb) {
        String query = "select p from etanah.model.ambil.HantarBorangPB p "
                + " where p.borangPerPB.id = :id ";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("id", pb.getId());
        return (HantarBorangPB) q.uniqueResult();
    }

    public List<TampalBorangHakmilik> findTampalBorangHakmilik(BorangPerHakmilik ph) {
        String query = "select p from etanah.model.ambil.TampalBorangHakmilik p "
                + " where p.borangPerHakmilik.id = :id ";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("id", ph.getId() + "");
        return q.list();
    }

    public List<TuntutanPerPB> listTuntutanPerPB(BorangPerPB pb) {

        String query = "select p from etanah.model.ambil.TuntutanPerPB p"
                + " where p.borangPerPB.id = :id ";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("id", pb.getId());
        return q.list();
    }

    @Transactional
    public void saveNotaSiasatanLengkap(NotaSiasatanLengkap nsl) {
        notaSiasatanLengkapDAO.save(nsl);
    }

    @Transactional
    public void saveNotaSiasatanDokumen(NotaSiasatanDokumen nsd) {
        notaSiasatanDokumenDAO.save(nsd);
    }

    public BorangPerPB findBorangPerPB(BorangPerPB pb, String nbh) {
        String query = "select p from etanah.model.ambil.BorangPerPB p "
                + " where p.borangPerPb.id = :id and p.kodNotis.kod =:nbh";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("id", pb.getId() + "");
        q.setString("nbh", nbh);
        return (BorangPerPB) q.uniqueResult();
    }

    public BigDecimal findJumlahTuntutan(Long idMh) {
        String query = "Select sum(a.amaun) FROM etanah.model.ambil.TuntutanPerPB a,etanah.model.ambil.BorangPerPB p, etanah.model.HakmilikPermohonan hp,"
                + "etanah.model.ambil.BorangPerHakmilik ph WHERE "
                + "a.borangPerPB.id = p.id and"
                + " p.borangPerHakmilik.id = ph.id and  ph.hakmilikPermohonan.id = hp.id and p.borangPerHakmilik.hakmilikPermohonan.id = hp.id"
                + " and hp.id = :idMh ";
        Session session = sessionProvider.get();
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idMh", idMh + "");
        return (BigDecimal) q.uniqueResult();
    }
    
    
     public HakmilikPerbicaraan findHakmilikPerbicaraanByIdMH(Long idMH) {
        String query = "select hp from etanah.model.ambil.HakmilikPerbicaraan hp"
                + " where hp.hakmilikPermohonan.id = :id ";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("id", idMH);
        return (HakmilikPerbicaraan) q.uniqueResult();
    }
}
