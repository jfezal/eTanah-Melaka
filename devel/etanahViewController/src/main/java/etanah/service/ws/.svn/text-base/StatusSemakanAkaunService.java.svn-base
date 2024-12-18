/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.service.ws;

import com.google.inject.Inject;
import com.wideplay.warp.persist.Transactional;
import etanah.dao.HakmilikDAO;
import etanah.dao.PenggunaDAO;
import etanah.dao.PortalLogDAO;
import etanah.model.Akaun;
import etanah.model.Hakmilik;
import etanah.model.InfoAudit;
import etanah.model.PortalLog;
import etanah.report.ReportUtil;
import etanah.service.RegService;
import etanah.view.dokumen.ws.DokumenInfo;
import etanah.view.portal.service.ws.StatusSemakanAkaun;
import etanah.view.uam.BilCukaiForm;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author mohd.faidzal
 */
public class StatusSemakanAkaunService {

    private static final Logger logger = Logger.getLogger(RegService.class);
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    ReportUtil reportUtil;
    @Inject
    PenggunaDAO penggunaDAO;
    @Inject
    PortalLogDAO portalLogDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    Akaun akaun;

    public StatusSemakanAkaun findStatusSemakanAkaun(String noAkaun) {
        StatusSemakanAkaun form = new StatusSemakanAkaun();

        Session s = sessionProvider.get();

        String query1 = "select a FROM etanah.model.Akaun a"
                + " where (a.noAkaun = :noAkaun or a.hakmilik.idHakmilik = :noAkaun)"
                + " and a.status.kod = 'A'";
        Query q1 = sessionProvider.get().createQuery(query1);
        q1.setString("noAkaun", noAkaun);
        q1.uniqueResult();
        akaun = (Akaun) q1.uniqueResult();

        if (akaun == null) {
            String query = "select a FROM etanah.model.Akaun a, etanah.model.AkaunStrata s, etanah.model.Hakmilik h"
                    + " where a.hakmilik.idHakmilik = h.idHakmilik"
                    + " and s.hakmilik.idHakmilik = h.idHakmilik"
                    + " and (a.noAkaun = :noAkaun or s.idAkaunStrata = :noAkaun)"
                    + " and a.status.kod = 'A'";
            Query q = sessionProvider.get().createQuery(query);

            q.setString("noAkaun", noAkaun);
            q.uniqueResult();
            akaun = (Akaun) q.uniqueResult();
        }

        if (akaun != null) {
            form.setStatus(akaun.getStatus().getNama());
            form.setNoAkaun(noAkaun);
            form.setBaki(akaun.getBaki());
            if (akaun.getHakmilik() != null) {
                form.setIdHakmilik(akaun.getHakmilik().getIdHakmilik());
            } else {
                form = null;
            }

        }

        return form;
    }

    public List<StatusSemakanAkaun> findStatusSemakanAkaunByNoKP(String noKp) {

        List<StatusSemakanAkaun> list = new ArrayList<StatusSemakanAkaun>();
        Session s = sessionProvider.get();
        String query = "SELECT a from etanah.model.Akaun a, etanah.model.Hakmilik h, etanah.model.HakmilikPihakBerkepentingan hp"
                + " where a.hakmilik.idHakmilik = h.idHakmilik"
                + " and h.idHakmilik = hp.hakmilik.idHakmilik"
                + " and hp.noPengenalan = :noKp"
                + " and a.status.kod = 'A'";
        Query q = s.createQuery(query);
        q.setString("noKp", noKp);
        List<Akaun> listPermohonan = q.list();
        for (Akaun akaun : listPermohonan) {
            StatusSemakanAkaun form = new StatusSemakanAkaun();
            form.setStatus(akaun.getStatus().getNama());
            form.setNoAkaun(akaun.getNoAkaun());
            form.setBaki(akaun.getBaki());
            form.setIdHakmilik(akaun.getHakmilik().getIdHakmilik());
            list.add(form);
        }
        return list;
    }

    public DokumenInfo muatTurunBilCukai(String idHakmilik) {
        DokumenInfo dok = new DokumenInfo();
        byte[] b = reportUtil.generateReport("HSLBilCukaiMLK_Frame",
                new String[]{"p_id_hakmilik"},
                new String[]{idHakmilik},
                null, null);
        dok.setBytes(b);
        dok.setFileName("BilCukai_" + idHakmilik + ".pdf");
        dok.setDocType("application/pdf");
        Hakmilik hakmilik = hakmilikDAO.findById(idHakmilik);
        if (hakmilik != null) {
            Session s = sessionProvider.get();
            Transaction tx = s.beginTransaction();
            PortalLog portalLog = new PortalLog();
            portalLog.setAkaun(hakmilik.getAkaunCukai());
            portalLog.setLogInfo("Muat turun Bil Cukai : " + idHakmilik);
            InfoAudit infoAudit = new InfoAudit();
            infoAudit.setDimasukOleh(penggunaDAO.findById("portal"));
            infoAudit.setTarikhMasuk(new Date());
            portalLog.setInfoAudit(infoAudit);
            portalLog.setStatus("Y");
            savePortalLog(portalLog);
            tx.commit();
        }
        return dok;
    }

    public StatusSemakanAkaun findStatusSemakanAkaunByIdHakmilik(String idHakmilik) {
        StatusSemakanAkaun form = new StatusSemakanAkaun();

        Session s = sessionProvider.get();

        Query q = s.createQuery("from etanah.model.Akaun a"
                + " where a.hakmilik.idHakmilik = :idHakmilik and a.status.kod = 'A'");
        q.setString("idHakmilik", idHakmilik);
        q.uniqueResult();
        Akaun akaun = (Akaun) q.uniqueResult();
        if (akaun != null) {
            form.setStatus(akaun.getStatus().getNama());
            form.setNoAkaun(akaun.getNoAkaun());
            form.setBaki(akaun.getBaki());
            if (akaun.getHakmilik() != null) {
                form.setIdHakmilik(akaun.getHakmilik().getIdHakmilik());
            } else {
                form = null;
            }

        }

        return form;
    }

    @Transactional
    private void savePortalLog(PortalLog portalLog) {
        portalLogDAO.saveOrUpdate(portalLog);
    }

    public List<BilCukaiForm> findSenarai(String caw, String tarikhDari, String tarikhHingga) {
        List<BilCukaiForm> list = new ArrayList<BilCukaiForm>();

        if (StringUtils.isNotEmpty(caw)) {
            if (caw.equals("01")) {
                BilCukaiForm mt = new BilCukaiForm();
                mt.setCawangan("Melaka Tengah");
                mt.setJumlah(countStatistik(caw, tarikhDari, tarikhHingga));
                list.add(mt);
            } else if (caw.equals("02")) {
                BilCukaiForm js = new BilCukaiForm();
                js.setCawangan("Jasin");
                js.setJumlah(countStatistik(caw, tarikhDari, tarikhHingga));
                list.add(js);
            } else if (caw.equals("03")) {
                BilCukaiForm ag = new BilCukaiForm();
                ag.setCawangan("Alor Gajah");
                ag.setJumlah(countStatistik(caw, tarikhDari, tarikhHingga));
                list.add(ag);
            } else if (caw.equals("00")) {
                BilCukaiForm ptg = new BilCukaiForm();
                ptg.setCawangan("PTG");
                ptg.setJumlah(countStatistik(caw, tarikhDari, tarikhHingga));
                list.add(ptg);
            }
        } else {
            BilCukaiForm ptg = new BilCukaiForm();
            ptg.setCawangan("PTG");
            ptg.setJumlah(countStatistik("00", tarikhDari, tarikhHingga));
            list.add(ptg);
            BilCukaiForm mt = new BilCukaiForm();
            mt.setCawangan("Melaka Tengah");
            mt.setJumlah(countStatistik("01", tarikhDari, tarikhHingga));
            list.add(mt);
            BilCukaiForm js = new BilCukaiForm();
            js.setCawangan("Jasin");
            js.setJumlah(countStatistik("02", tarikhDari, tarikhHingga));
            list.add(js);
            BilCukaiForm ag = new BilCukaiForm();
            ag.setCawangan("Alor Gajah");
            ag.setJumlah(countStatistik("03", tarikhDari, tarikhHingga));
            list.add(ag);
        }

        return list;
    }

    public Long countStatistik(String kod_caw, String tarikhDari, String tarikhHingga) {
        Session s = sessionProvider.get();
        String query = "SELECT count(p) from etanah.model.Akaun a, etanah.model.Hakmilik h, etanah.model.PortalLog p"
                + " where a.hakmilik.idHakmilik = h.idHakmilik and a.noAkaun = p.akaun.noAkaun ";
        query = query + " and h.cawangan.kod = :kod_caw ";
        if (StringUtils.isNotEmpty(tarikhDari) && StringUtils.isNotEmpty(tarikhHingga)) {
            query = query + " and p.infoAudit.tarikhMasuk  between to_date(:date1,'dd/MM/yyyy hh24:mi:ss') and to_date(:date2,'dd/MM/yyyy hh24:mi:ss')";
        }
        Query q = s.createQuery(query);
        q.setString("kod_caw", kod_caw);
        if (StringUtils.isNotEmpty(tarikhDari) && StringUtils.isNotEmpty(tarikhHingga)) {
            q.setString("date1", tarikhDari);
            q.setString("date2", tarikhHingga);
        }

        return (Long) q.iterate().next();
    }
}
