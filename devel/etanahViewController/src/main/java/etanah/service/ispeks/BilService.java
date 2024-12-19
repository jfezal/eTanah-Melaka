/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.service.ispeks;

import com.google.inject.Inject;
import com.wideplay.warp.persist.Transactional;
import etanah.dao.BilDAO;
import etanah.dao.BilDetailDAO;
import etanah.dao.KodTransaksiDAO;
import etanah.dao.TugasanIspeksDAO;
import etanah.model.ispek.Bil;
import etanah.model.ispek.BilDetail;
import etanah.model.ispek.TugasanIspeks;
import etanah.model.view.IddBilContent;
import etanah.model.view.IddBilHeader;
import etanah.model.view.IddPPContent;
import etanah.model.view.IddPPHeader;
import etanah.view.stripes.ispeks.BilForm;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author mohd.faidzal
 */
public class BilService {

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    BilDAO bilDAO;
    @Inject
    BilDetailDAO bilDetailDAO;
    @Inject
    KodTransaksiDAO kodTransaksiDAO;
    @Inject
    TugasanIspeksDAO tugasanIspeksDAO;

    public TugasanIspeks findByIdRef(Long id) {
        String query = "Select p FROM etanah.model.ispek.TugasanIspeks p where "
                + "p.noRef =:id and p.kodPeringkat.kat = 'BIL'";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("id", id);
        return (TugasanIspeks) q.uniqueResult();
    }

    @Transactional
    public Bil simpanBil(Bil bil) {
        return bilDAO.saveOrUpdate(bil);
    }

    @Transactional
    public void deleteTugasan(TugasanIspeks t) {
        tugasanIspeksDAO.delete(t);
    }

    @Transactional
    public void simpanTugasanIspeks(TugasanIspeks t) {
        tugasanIspeksDAO.save(t);
    }

    public List<BilForm> paparPenyata(Long id) {
        List<BilForm> l = new ArrayList<BilForm>();
        String query = "Select p FROM etanah.model.ispek.BilDetail p where "
                + "p.bil.id =:id";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("id", id);

        List<BilDetail> listBil = q.list();
        for (BilDetail b : listBil) {
            BilForm f = new BilForm();
            f.setAmaun(b.getAmaun() + "");
            f.setKodTrans(kodTransaksiDAO.findById(b.getKodTrans()));
            f.setNoBil(b.getBil().getNoBil());
            l.add(f);
        }
        return l;
    }

    public Long findSumBil(Long id) {
        Session s = sessionProvider.get();
        String query = "Select sum(p.amaun) FROM etanah.model.ispek.BilDetail p where "
                + "p.bil.id =:id";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("id", id);
        return (Long) q.uniqueResult();
    }

    IddBilHeader findHeader(Long id) {
        String query = "Select p FROM etanah.model.view.IddBilHeader p "
                + "where p.idBil = :id";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("id", id);
        return (IddBilHeader) q.uniqueResult();
    }

    List<IddBilContent> findContent(Long id) {
        String query = "Select p FROM etanah.model.view.IddBilContent p "
                + "where p.noBil = :id";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("id", id);
        return q.list();
    }

    public Bil findByNoPenyata(String noPenyataR) {
        String query = "Select p FROM etanah.model.ispeks.Bil p "
                + "where p.noBil = :noPenyataR";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("noPenyataR", noPenyataR);
        return (Bil) q.uniqueResult();
    }
}
