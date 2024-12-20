package etanah.view.stripes.hasil;

import java.util.*;
import etanah.dao.*;
import etanah.model.*;
import org.hibernate.*;
import java.math.BigDecimal;
import org.apache.log4j.Logger;
import com.google.inject.Inject;
import able.stripes.AbleActionBean;
import etanah.model.view.TransaksiSejarah;
import etanah.view.portal.ws.service.SejarahHasilService;
import net.sourceforge.stripes.action.*;

/*
 * @author haqqiem 28 April 2015
 */
@Wizard(startEvents = {"Step1"})
@UrlBinding("/hasil/history")
public class HistoryTransactionActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(HistoryTransactionActionBean.class);
    private static final String DEFAULT_VIEW = "/WEB-INF/jsp/hasil/history.jsp";
    private static String kodNegeri;

    private Akaun akaun = new Akaun();
    private Hakmilik hakmilik = new Hakmilik();
    private boolean flag = false;
    private List<Transaksi> senaraiTransaksi = new ArrayList<Transaksi>();

    @Inject
    private AkaunDAO akaunDAO;
    @Inject
    private TransaksiDAO transaksiDAO;
    @Inject
    private SejarahTransaksiDAO sejarahTransaksiDAO;

    @Inject
    SejarahHasilService sejarahHasilService;

    @Inject
    private HakmilikDAO hakmilikDAO;

    @Inject
    private SejarahDokumenKewanganDAO sejarahDokumenKewanganDAO;

    @Inject
    private etanah.Configuration conf;

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;

    @Inject
    private KutipanHasilManager manager;

    @HandlesEvent("Step1")
    @DefaultHandler
    public Resolution showForm() {
        if ("04".equals(conf.getProperty("kodNegeri"))) {
            kodNegeri = "melaka";
        }
        flag = false;
        return new ForwardResolution(DEFAULT_VIEW);
    }

    @HandlesEvent("Step2")
    public Resolution carianHakmilik() {
        if (hakmilik.getIdHakmilik() != null) {
            Session s = sessionProvider.get();
            String sql = "SELECT a FROM etanah.model.Akaun a WHERE a.hakmilik.idHakmilik = :id AND a.kodAkaun.kod='AC' AND a.status.kod='A'";
            Query q = s.createQuery(sql);
            q.setString("id", hakmilik.getIdHakmilik());
            akaun = (Akaun) q.uniqueResult();
        } else {
            akaun = akaunDAO.findById(akaun.getNoAkaun());
        }
        LOG.info("akaun.getNoAkaun : " + akaun.getNoAkaun());

        if (akaun != null) {
            senaraiTransaksi = akaun.getSemuaTransaksi();
        }
        LOG.info("senaraiTransaksi.size() : " + senaraiTransaksi.size());
        flag = true;

        return new ForwardResolution(DEFAULT_VIEW);
    }

    @HandlesEvent("Step3")
    public Resolution pushData() {
        carianHakmilik();

        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        tx = s.beginTransaction();
        Date now = new Date();
        int tahun = now.getYear() + 1900;
        LOG.info("tahun : " + tahun);

        List<DokumenKewangan> senaraiResit = new ArrayList<DokumenKewangan>();
        List<Transaksi> senaraiData = new ArrayList<Transaksi>();

        for (Transaksi t : senaraiTransaksi) {
            if (t.getUntukTahun() != tahun) {
                if (t.getDokumenKewangan() != null) {
                    senaraiResit.add(t.getDokumenKewangan());
//                    for (DokumenKewangan resit : senaraiResit){
//                        manager.getTransDt(resit.getUntukTahun, resit.get)
//                    }
                }
                senaraiData.add(t);
            }
        }
//        if (senaraiResit.size() > 0) {
//            pushResit(senaraiResit);
//        }
//        if (senaraiData.size() > 0) {
//            pushSejarah(senaraiData, tahun);
//        }
//        if (senaraiTransaksi.size() > 0) {
//            updateAkaun(senaraiTransaksi, akaun, tahun);
//        }

        try {
            pushResit(senaraiResit);

            updateAkaun(senaraiTransaksi, akaun, tahun);
            pushSejarah(senaraiData, tahun);
            addSimpleMessage("Process telah berjaya. Sila ke menu Pertanyaan Hakmilik untuk semakan transaksi.");
            tx.commit();
        } catch (Exception ex) {
            LOG.error(ex.getMessage(), ex);
            addSimpleError("Process tidak berjaya. Sila hubungi pentadbir sistem untuk maklumat lanjut.");
            tx.rollback();
        }

//        delTrans(senaraiData, tahun);
        return new ForwardResolution(DEFAULT_VIEW);
    }

    public void pushResit(List<DokumenKewangan> list) {

        for (DokumenKewangan dk : list) {

            List<SejarahDokumenKewangan> kewDokSej = manager.searchNoResitSejarah(dk.getIdDokumenKewangan());
            if (kewDokSej.size() <= 0) {
                SejarahDokumenKewangan sej = new SejarahDokumenKewangan();
                sej.setIdDokumenKewangan(dk.getIdDokumenKewangan());
                sej.setAkaun(dk.getAkaun());
                sej.setAmaunBayaran(dk.getAmaunBayaran());
                sej.setAmaunTunai(dk.getAmaunTunai());
                sej.setBilanganCetak(dk.getBilanganCetak());
                sej.setCatatan(dk.getCatatan());
                sej.setCawangan(dk.getCawangan());
                sej.setDibatalOleh(dk.getDibatalOleh());
                sej.setInfoAudit(dk.getInfoAudit());
                sej.setIsuKepada(dk.getIsuKepada());
                sej.setKodAgensiKutipanCaw(dk.getAgensiKutipan());
                sej.setKodBatal(dk.getKodBatal());
                sej.setKodDokumen(dk.getKodDokumen());
                sej.setNoRujukanManual(dk.getNoRujukanManual());
                sej.setStatus(dk.getStatus());

                sejarahDokumenKewanganDAO.saveOrUpdate(sej);

                manager.save(sej);
                manager.delete(dk);
            }
        }

    }

    public void delTrans(List<Transaksi> list, int tahun) {
        SejarahTransaksi sej = new SejarahTransaksi();

//        for (Transaksi tr : list) {
//            if ((tr.getKodTransaksi().getKod().equals("76152")) && (tr.getUntukTahun() == (tahun - 1))) {
////                manager.delete(tr);
//                continue;
//            } else {
//                sej = new SejarahTransaksi();
//
//                sej.setAkaunDebit(tr.getAkaunDebit());
//                sej.setAkaunKredit(tr.getAkaunKredit());
//                sej.setAmaun(tr.getAmaun());
//                sej.setCawangan(tr.getCawangan());
//                if (tr.getDokumenKewangan() != null) {
//                    sej.setDokumenKewangan(sejarahDokumenKewanganDAO.findById(tr.getDokumenKewangan().getIdDokumenKewangan()));
//                }
//                sej.setInfoAudit(tr.getInfoAudit());
//                sej.setKodBatal(tr.getKodBatal());
//                sej.setKodTransaksi(tr.getKodTransaksi());
//                sej.setKuantiti(tr.getKuantiti());
////                sej.setPermohonan(tr.getPermohonan().getIdPermohonan());
//                sej.setStatus(tr.getStatus());
//                sej.setTahunKewangan(tr.getTahunKewangan());
//                sej.setUntukTahun(tr.getUntukTahun());
//
////                manager.save(sej);
//                manager.sejarahTransaksiDAO.saveOrUpdate(sej);
//            }
//        }
        manager.delTrans(list);
    }

    public void pushSejarah(List<Transaksi> list, int tahun) {
        SejarahTransaksi sej = new SejarahTransaksi();

        for (Transaksi tr : list) {
            if ((tr.getKodTransaksi().getKod().equals("76152")) && (tr.getUntukTahun() == (tahun - 1))) {
//                manager.delete(tr);
                continue;
            } else {
                sej = new SejarahTransaksi();

                sej.setAkaunDebit(tr.getAkaunDebit());
                sej.setAkaunKredit(tr.getAkaunKredit());
                sej.setAmaun(tr.getAmaun());
                sej.setCawangan(tr.getCawangan());
                if (tr.getDokumenKewangan() != null) {
                    sej.setDokumenKewangan(sejarahDokumenKewanganDAO.findById(tr.getDokumenKewangan().getIdDokumenKewangan()));
                }
                sej.setInfoAudit(tr.getInfoAudit());
                sej.setKodBatal(tr.getKodBatal());
                sej.setKodTransaksi(tr.getKodTransaksi());
                sej.setKuantiti(tr.getKuantiti());
//                sej.setPermohonan(tr.getPermohonan().getIdPermohonan());
                sej.setStatus(tr.getStatus());
                sej.setTahunKewangan(tr.getTahunKewangan());
                sej.setUntukTahun(tr.getUntukTahun());

                manager.save(sej);
                manager.deleteTransaksi(tr);

            }
        }
        manager.delTrans(list);
    }

    public void updateAkaun(List<Transaksi> list, Akaun a, int tahun) {
        BigDecimal baki = new BigDecimal(0);
        for (Transaksi t : list) {
            if (t.getUntukTahun() == tahun) {
                if ((t.getAkaunDebit() != null) && (t.getAkaunDebit().getNoAkaun().equals(a.getNoAkaun()))) {
                    baki = baki.add(t.getAmaun());
                }
                if ((t.getAkaunKredit() != null) && (t.getAkaunKredit().getNoAkaun().equals(a.getNoAkaun()))) {
                    baki = baki.subtract(t.getAmaun());
                }
            }
        }
        akaun.setBaki(baki);
        manager.saveOrUpdate(akaun);
    }

    public String pushSejarahManual(int limit, String kodCaw) {
        String status = "";
        LOG.info("STARTING PUSH TO SEJARAH MANUAL FOR LIMIT : " + limit + " AT TIME : " + new Date().toString());

        List<TransaksiSejarah> listView = new ArrayList<TransaksiSejarah>();
        String noakaun = "";
        for (int i = 0; i < limit; i++) {
            TransaksiSejarah ts = sejarahHasilService.findTransaksiSejarah(kodCaw);
            if (ts != null) {
                noakaun = ts.getNoAkaun();
                LOG.info("akaun.getNoAkaun : " + noakaun);
                akaun = sejarahHasilService.findByList(noakaun, kodCaw);
                if (akaun != null) {
                    LOG.info("akaun.getNoAkaun : " + akaun.getNoAkaun());
                    senaraiTransaksi = akaun.getSemuaTransaksi();
                    Session s = sessionProvider.get();
                    Transaction tx = s.beginTransaction();
                    tx = s.beginTransaction();
                    Date now = new Date();
                    int tahun = now.getYear() + 1900;
                    LOG.info("tahun : " + tahun);

                    List<DokumenKewangan> senaraiResit = new ArrayList<DokumenKewangan>();
                    List<Transaksi> senaraiData = new ArrayList<Transaksi>();

                    for (Transaksi t : senaraiTransaksi) {
                        if (t.getUntukTahun() != tahun) {
                            if (t.getDokumenKewangan() != null) {
                                senaraiResit.add(t.getDokumenKewangan());
//                    for (DokumenKewangan resit : senaraiResit){
//                        manager.getTransDt(resit.getUntukTahun, resit.get)
//                    }
                            }
                            senaraiData.add(t);
                        }
                    }
                    try {
                        pushResit(senaraiResit);

                        updateAkaun(senaraiTransaksi, akaun, tahun);
                        pushSejarah(senaraiData, tahun);
                        tx.commit();
                        status += " " + i + " : " + noakaun;
                    } catch (Exception ex) {
                        LOG.error(ex.getMessage(), ex);
                        tx.rollback();
                    }
                }

            }
        }
        LOG.info("FINISHED PUSH TO SEJARAH MANUAL FOR LIMIT : " + limit + " AT TIME : " + new Date().toString());

        return status;
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        HistoryTransactionActionBean.kodNegeri = kodNegeri;
    }

    public Akaun getAkaun() {
        return akaun;
    }

    public void setAkaun(Akaun akaun) {
        this.akaun = akaun;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public List<Transaksi> getSenaraiTransaksi() {
        return senaraiTransaksi;
    }

    public void setSenaraiTransaksi(List<Transaksi> senaraiTransaksi) {
        this.senaraiTransaksi = senaraiTransaksi;
    }
}
