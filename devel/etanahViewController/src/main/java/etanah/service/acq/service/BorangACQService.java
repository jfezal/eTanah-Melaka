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
import etanah.dao.HantarBorangPBDAO;
import etanah.dao.HantarBorangPermohonanDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.TampalBorangHakmilikDAO;
import etanah.dao.TampalBorangPermohonanDAO;
import etanah.dao.TuntutanPerPBDAO;
import etanah.model.InfoMmkn;
import etanah.model.Pemohon;
import etanah.model.Permohonan;
import etanah.model.ambil.BorangPerHakmilik;
import etanah.model.ambil.BorangPerPB;
import etanah.model.ambil.BorangPerPermohonan;
import etanah.model.ambil.HantarBorangPB;
import etanah.model.ambil.HantarBorangPermohonan;
import etanah.model.ambil.InfoWarta;
import etanah.model.ambil.PermohonanPengambilan;
import etanah.model.ambil.TampalBorangHakmilik;
import etanah.model.ambil.TampalBorangPermohonan;
import etanah.model.ambil.TuntutanPerPB;
import etanah.service.ambil.InfoWartaService;
import etanah.service.ambil.PengambilanAPTService;
import etanah.service.common.InfoMmknService;
import etanah.view.stripes.pengambilan.share.form.BorangABForm;
import etanah.view.stripes.pengambilan.share.form.BorangAForm;
import etanah.view.stripes.pengambilan.share.form.BorangBForm;
import etanah.view.stripes.pengambilan.share.form.BorangEForm;
import etanah.view.stripes.pengambilan.share.form.BorangFTuntutanForm;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author mohd.faidzal
 */
public class BorangACQService {

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    TampalBorangPermohonanDAO tampalBorangPermohonanDAO;
    @Inject
    BorangPerPermohonanDAO borangPerPermohonanDAO;
    @Inject
    HantarBorangPermohonanDAO hantarBorangPermohonanDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    HantarBorangPBDAO hantarBorangPBDAO;
    @Inject
    TampalBorangHakmilikDAO tampalBorangHakmilikDAO;
    @Inject
    BorangPerPBDAO borangPerPBDAO;
    @Inject
    BorangPerHakmilikDAO borangPerHakmilikDAO;
    @Inject
    InfoWartaService infoWartaService;
    @Inject
    PengambilanAPTService pengambilanAPTService;
    @Inject
    InfoMmknService infoMmknService;

    public List<BorangAForm> findInfoBorangA(Long id) throws ParseException {
        List<BorangAForm> listBorangA = new ArrayList<BorangAForm>();
        for (TampalBorangPermohonan tp : findTampalBorangPermohonan(id)) {
            BorangAForm form = new BorangAForm();
//        form.setIdMohon(tp);
            form.setId(tp.getId());
            form.setIdBorangPerPermohonan(tp.getBorangPerPermohonan().getId());
            form.setNamaPenampal(tp.getPenghantarNotis());
            form.setTarikhTampal(formatSDF(tp.getTarikhTampal()));
            form.setTempatTampal(tp.getTempatTampal());
            listBorangA.add(form);
        }
        return listBorangA;
    }

    public String formatSDF(Date date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(date);
    }

    public List<BorangBForm> findInfoBorangB(Long id) throws ParseException {

        List<BorangBForm> listBorangB = new ArrayList<BorangBForm>();
        for (HantarBorangPermohonan tp : findHantarBorangPermohonan(id)) {
            BorangBForm form = new BorangBForm();
//        form.setIdMohon(tp);
            form.setId(tp.getId());

            form.setIdBorangPerPermohonan(tp.getBorangPerPermohonan().getId());
//        form.setAlamatHantar(tp.);
            form.setNamaPenghantar(tp.getPenghantarNotis());
            form.setTarikhHantar(formatSDF(tp.getTarikhHantar()));
            listBorangB.add(form);
        }
        return listBorangB;
    }

    public BorangABForm findInfoBorangAB(String idPermohonan) {
        BorangABForm form = new BorangABForm();
        BorangPerPermohonan borangA = findBorangA(idPermohonan, "NAA");
        BorangPerPermohonan borangB = findBorangB(idPermohonan, "NBB");
        String kodNotis = "A";
        Permohonan mohon = permohonanDAO.findById(idPermohonan);
        PermohonanPengambilan pengambilan = pengambilanAPTService.findPermohonanPengambilanByIdMH(idPermohonan);
        InfoWarta infoWarta = infoWartaService.findByIdMohonAndKodNotis1(idPermohonan,kodNotis);
        InfoMmkn infoMmkn = infoMmknService.findByIdMohonAndKodPeringkatRKMMK(kodNotis);
        form.setIdPermohonan(idPermohonan);
        form.setUrusan(mohon.getKodUrusan().getNama());
        form.setTujuanPermohonan(pengambilan.getTujuanPermohonan());
        form.setJumlahLuas(0);
        form.setJumlahHakmilik(0);
        form.setLuas("");

        form.setBorangA(borangA.getDokumen());
        form.setIdBorangPerPermohonanA(borangA.getId());
        form.setTarikhBorangA(borangA.getTrh_tt() != null ? borangA.getTrh_tt().toString() : "");
        form.setTandatanganA(borangA.getDitandatangan() != null ? borangA.getDitandatangan().getNama() : "");
        if(infoMmkn != null){
            form.setTarikhKelulusanMMKN(String.valueOf(infoMmkn.getTrhSah()));
        }
        
        form.setTarikhWarta(String.valueOf(infoWarta.getTrhWarta()));
        form.setJumTempatTampal(setJumTempatTampal(borangA.getId()) != null ? (int) (long) setJumTempatTampal(borangA.getId()) : 0);

        form.setBorangB(borangB.getDokumen());
        form.setIdBorangPerPermohonanB(borangB.getId());
        form.setTarikhBorangB(borangB.getTrh_tt() != null ? borangB.getTrh_tt().toString() : "");
        form.setTandatanganB(borangB.getDitandatangan() != null ? borangB.getDitandatangan().getNama() : "");
        return form;
    }

    public BorangPerPermohonan findBorangA(String idPermohonan, String kod) {
        String query = "select p from etanah.model.ambil.BorangPerPermohonan p"
                + " where p.permohonan.idPermohonan = :idPermohonan and p.kodNotis.kod = :kod";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("kod", kod);
        return (BorangPerPermohonan) q.uniqueResult();
    }

    private BorangPerPermohonan findBorangB(String idPermohonan, String kod) {
        String query = "select p from etanah.model.ambil.BorangPerPermohonan p"
                + " where p.permohonan.idPermohonan = :idPermohonan and p.kodNotis.kod = :kod";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("kod", kod);
        return (BorangPerPermohonan) q.uniqueResult();
    }

    @Transactional
    public void simpanTampalBorangPermohonan(TampalBorangPermohonan bm) {
        tampalBorangPermohonanDAO.save(bm);
    }

    private List<TampalBorangPermohonan> findTampalBorangPermohonan(Long id) {
        String query = "select p from etanah.model.ambil.TampalBorangPermohonan p"
                + " where p.borangPerPermohonan.id = :id ";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("id", id);
        return q.list();
    }

    public List<Pemohon> findPemohonByIDPermohonan(Permohonan mohon) {
        String query = "select p from etanah.model.pemohon p"
                + " where p.permohonan.idPermohonan = :idPermohonan ";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", mohon.getIdPermohonan());
        return q.list();
    }

    public List<BorangPerPB> findBorangE(String kod) {
        String query = "select p from etanah.model.ambil.BorangPerPB pb"
                + " where p.kodNotis.kod = :kod";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("kod", kod);
        return q.list();
    }

    private Iterable<HantarBorangPermohonan> findHantarBorangPermohonan(Long id) {
        String query = "select p from etanah.model.ambil.HantarBorangPermohonan p"
                + " where p.borangPerPermohonan.id = :id ";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("id", id);
        return q.list();
    }

    @Transactional
    public void saveBorangPermohonan(BorangPerPermohonan bp) {
        borangPerPermohonanDAO.saveOrUpdate(bp);
    }

    @Transactional
    public void simpanHantarBorangPermohonan(HantarBorangPermohonan bm) {
        hantarBorangPermohonanDAO.saveOrUpdate(bm);
    }

    private Long setJumTempatTampal(long id) {
        String query = "Select count(p) FROM etanah.model.ambil.TampalBorangPermohonan p "
                + " where p.borangPerPermohonan.id = :id ";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("id", id);
        return (Long) q.iterate().next();
    }

    @Transactional
    public void hapusTampalBorangPermohonan(String id) {
        TampalBorangPermohonan tp = tampalBorangPermohonanDAO.findById(Long.parseLong(id));
        tampalBorangPermohonanDAO.delete(tp);
    }

    @Transactional
    public void hapusHantarBorangPermohonan(String id) {
        HantarBorangPermohonan hp = hantarBorangPermohonanDAO.findById(Long.parseLong(id));
        hantarBorangPermohonanDAO.delete(hp);
    }

    @Transactional
    public void saveBorangPerPB(BorangPerPB perPB) {
        borangPerPBDAO.saveOrUpdate(perPB);
    }

    @Transactional
    public void saveBorangPerhakmilik(BorangPerHakmilik bph) {
        borangPerHakmilikDAO.save(bph);
    }

    public BorangEForm findBorangEByIdMohonAndIdHakmilik(String idPermohonan, String idHakmilik) {
        BorangEForm e = new BorangEForm();
        String query = "select p from etanah.model.ambil.BorangPerHakmilik p"
                + " where p.hakmilikPermohonan.permohonan.idPermohonan = :idPermohonan and p.hakmilikPermohonan.hakmilik.idHakmilik = :idHakmilik and p.kodNotis.kod = 'NBE'";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("idHakmilik", idHakmilik);
        BorangPerHakmilik a = (BorangPerHakmilik) q.uniqueResult();
        List<BorangFTuntutanForm> list = new ArrayList<BorangFTuntutanForm>();
        if (a != null) {
            e.setDok(a.getDokumen());
            e.setTandatangan(a.getDitandatangan());

            for (BorangPerPB pb : a.getSenaraiBorangPerPB()) {
                BorangFTuntutanForm f = new BorangFTuntutanForm();
                f.setPb(pb);
                f = countAmount(f, pb);
                list.add(f);
            }
        }

        e.setListBorangPerPB(list);

        return e;
    }

    public BorangEForm findBorangEByIdMohonAndIdHakmilikWithKodNotis(String idPermohonan, String idHakmilik, String kodNotis) {
        BorangEForm e = new BorangEForm();
        String query = "select p from etanah.model.ambil.BorangPerHakmilik p"
                + " where p.hakmilikPermohonan.permohonan.idPermohonan = :idPermohonan "
                + "and p.hakmilikPermohonan.hakmilik.idHakmilik = :idHakmilik "
                + "and p.kodNotis.kod = :kodNotis";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("idHakmilik", idHakmilik);
        q.setString("kodNotis", kodNotis);
        BorangPerHakmilik a = (BorangPerHakmilik) q.uniqueResult();
        List<BorangFTuntutanForm> list = new ArrayList<BorangFTuntutanForm>();
        if (a != null) {
            e.setDok(a.getDokumen());
            e.setTandatangan(a.getDitandatangan());

            for (BorangPerPB pb : a.getSenaraiBorangPerPB()) {
                BorangFTuntutanForm f = new BorangFTuntutanForm();
                f.setPb(pb);
                f = countAmount(f, pb);
                list.add(f);
            }
        }

        e.setListBorangPerPB(list);

        return e;
    }

    BorangFTuntutanForm countAmount(BorangFTuntutanForm d, BorangPerPB pb) {
        Integer count = 0;
        BigDecimal a = new BigDecimal(BigInteger.ZERO);
        for (TuntutanPerPB t : listTuntutanPerPB(pb)) {
            count++;
            a = a.add(t.getAmaun());
        }
        d.setAmaun(a);
        d.setTotalItem(count);
        return d;
    }

    public List<TuntutanPerPB> listTuntutanPerPB(BorangPerPB pb) {

        String query = "select p from etanah.model.ambil.TuntutanPerPB p"
                + " where p.borangPerPB.id = :id ";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("id", pb.getId());
        return q.list();
    }
    @Inject
    TuntutanPerPBDAO tuntutanPerPBDAO;

    @Transactional
    public void saveTuntutan(TuntutanPerPB tu) {
        tuntutanPerPBDAO.save(tu);
    }

    @Transactional
    public void saveHantarBorangPB(HantarBorangPB bm) {
        hantarBorangPBDAO.save(bm);
    }

    @Transactional
    public void saveTampalBorangHakmilik(TampalBorangHakmilik bm) {
        tampalBorangHakmilikDAO.save(bm);
    }

    public HantarBorangPB findHantarBorangPB(BorangPerPB pb) {

        String query = "select p from etanah.model.ambil.HantarBorangPB p"
                + " where p.borangPerPB.id = :id ";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("id", pb.getId());
        return (HantarBorangPB) q.uniqueResult();
    }

}
