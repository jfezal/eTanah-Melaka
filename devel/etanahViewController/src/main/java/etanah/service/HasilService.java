/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.service;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.wideplay.warp.persist.Transactional;
import etanah.dao.HakmilikDAO;
import etanah.model.Hakmilik;
import etanah.model.KodBandarPekanMukim;
import etanah.model.PortalTransaksi;
import etanah.model.view.HakmilikHasil;
import etanah.service.uam.UamService;
import etanah.view.etanahContextListener;
import etanah.view.portal.ws.DokumenCarianPersendirian;
import etanah.view.stripes.hasil.SenaraiHakmilikHasilForm;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author mohd.faidzal
 */
public class HasilService {

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    private etanah.Configuration conf;
    @Inject
    UamService uamservice;
    @Inject
    HakmilikDAO hakmilikDAO;

    public Long count(String kodCaw, String kodDaerah, String kodBPM, String kodKatTanah) {
        String query2;
        Session session = sessionProvider.get();
        query2 = "SELECT count(pt) FROM etanah.model.view.HakmilikHasil pt"
                + " where pt.kodCaw = :kodCaw ";
        if (StringUtils.isNotEmpty(kodDaerah)) {
            query2 += "and pt.kodDaerah = :kodDaerah ";
        }
        if (StringUtils.isNotEmpty(kodDaerah)) {
            query2 += "and pt.kodBPM = :kodBPM ";
        }
        if (StringUtils.isNotEmpty(kodDaerah)) {
            query2 += "and pt.kodKatgTanah = :kodKatTanah";
        }
        Query q2 = session.createQuery(query2);
        q2.setString(
                "kodCaw", kodCaw);
        if (StringUtils.isNotEmpty(kodDaerah)) {
            q2.setString(
                    "kodDaerah", kodDaerah);
        }
        if (StringUtils.isNotEmpty(kodDaerah)) {
            q2.setString(
                    "kodBPM", kodBPM);
        }
        if (StringUtils.isNotEmpty(kodDaerah)) {
            q2.setString(
                    "kodKatTanah", kodKatTanah);
        }
        Long a = (Long) q2.uniqueResult();
        return a;
    }

    public List<SenaraiHakmilikHasilForm> paparSenaraiView(String kodCaw, String kodDaerah, String kodBPM, String kodKatTanah) {
        List<SenaraiHakmilikHasilForm> list = new ArrayList<SenaraiHakmilikHasilForm>();
        String query;
        Session session = sessionProvider.get();

        query = "SELECT pt FROM etanah.model.view.HakmilikHasil pt"
                + " where pt.kodCaw = :kodCaw ";
        if (StringUtils.isNotEmpty(kodDaerah)) {
            query += "and pt.kodDaerah = :kodDaerah ";
        }
        if (StringUtils.isNotEmpty(kodDaerah)) {
            query += "and pt.kodBPM = :kodBPM ";
        }
        if (StringUtils.isNotEmpty(kodDaerah)) {
            query += "and pt.kodKatgTanah = :kodKatTanah";
        }
        Query q = session.createQuery(query);
        q.setMaxResults(30);
        q.setString(
                "kodCaw", kodCaw);
        if (StringUtils.isNotEmpty(kodDaerah)) {
            q.setString(
                    "kodDaerah", kodDaerah);
        }
        if (StringUtils.isNotEmpty(kodDaerah)) {
            q.setString(
                    "kodBPM", kodBPM);
        }
        if (StringUtils.isNotEmpty(kodDaerah)) {
            q.setString(
                    "kodKatTanah", kodKatTanah);
        }
        for (int i = 0;
                i < q.list()
                        .size(); i++) {
            SenaraiHakmilikHasilForm form = new SenaraiHakmilikHasilForm();
            HakmilikHasil pt = (HakmilikHasil) q.list().get(i);
            form.setBpmNama(pt.getBpmNama());
            form.setIdHakmilik(pt.getIdHakmilik());
            form.setKatgTanah(pt.getKatgTanah());
            form.setKodBPM(pt.getKodBPM());
            form.setKodKatgTanah(pt.getKodKatgTanah());
            form.setSenaraiPemilik(pt.getSenaraiPemilik());
            form.setJumPemilik(pt.getJumPemilik());
            form.setTrhDaftar(pt.getTrhDaftar());
            form.setKodCaw(pt.getKodCaw());
            form.setKodDaerah(pt.getKodDaerah());
            list.add(form);
        }
        return list;
    }

    public List<KodBandarPekanMukim> findBPMByDaerah(String kod) {

        Session s = sessionProvider.get();
        Query q = s.createQuery("select u from etanah.model.KodBandarPekanMukim u where u.daerah.kod = :kodDaerah and u.bandarPekanMukim <> '00' order by u.bandarPekanMukim ASC");
        q.setString("kodDaerah", kod);
        return q.list();
    }

    @Transactional
    public void simpanHakmilik(Hakmilik ha) {
        hakmilikDAO.saveOrUpdate(ha);
    }
}
