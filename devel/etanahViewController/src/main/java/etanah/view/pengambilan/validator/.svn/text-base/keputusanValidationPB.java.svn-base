/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.pengambilan.validator;

import com.google.inject.Inject;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodPerananDAO;
import etanah.dao.KodUrusanDAO;
import etanah.model.FasaPermohonan;
import etanah.model.FolderDokumen;
import etanah.model.Hakmilik;
import etanah.model.InfoAudit;
import etanah.model.Notifikasi;
import etanah.model.Pengguna;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import org.hibernate.Session;
import etanah.model.KodPeranan;
import etanah.model.KodUrusan;
import etanah.model.Permohonan;
import etanah.report.ReportUtil;
import etanah.service.NotifikasiService;
import etanah.service.StrataPtService;
import etanah.view.strata.CommonService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import etanah.view.strata.GenerateIdPerserahanWorkflow;


/**
 *
 * @author nordiyana
 */
public class keputusanValidationPB implements StageListener {
    
     @Inject
    ReportUtil reportUtil;
    @Inject
    CommonService comm;
    @Inject
    etanah.Configuration conf;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    KodPerananDAO kodPerananDAO;
    @Inject
    NotifikasiService notifikasiService;
    @Inject
    FolderDokumenDAO folderDokumenDAO;
    @Inject
    StrataPtService strService;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    KodUrusanDAO kodUrusanDAO;
    @Inject
    GenerateIdPerserahanWorkflow generateIdPerserahanWorkflow;
    Pengguna pengguna;
    FolderDokumen fd;
    private static final Logger LOG = Logger.getLogger(etanah.view.strata.validator.KeputusanValidation.class);
    private String idHakmilik;
    private Hakmilik hakmilik;

    @Override
    public boolean beforeStart(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void onGenerateReports(StageContext context) {
        Pengguna peng = context.getPengguna();
        comm.setPengguna(peng);
        String kodNegeri = conf.getProperty("kodNegeri");
        Permohonan mohon = context.getPermohonan();
        FasaPermohonan mohonFasa = new FasaPermohonan();
        List<String> t = new ArrayList<String>();
        List<String> t2 = new ArrayList<String>();


        if (mohon.getKodUrusan().getKod().equals("PB")) {
            mohonFasa = strService.findMohonFasa(mohon.getIdPermohonan(), "01DrafPenarikanBalik");
            if (mohonFasa.getKeputusan() != null) {
                if (mohonFasa.getKeputusan().getKod().equals("8B")) {
                    // if keputusan Ada Borang K
                    if (kodNegeri.equals("04")) {
                       

                    } else {
                        t.add("ACQSrtPenarikan_NS.rdf");
                        t2.add("8TOL");
                        comm.reportGen(mohon, t, t2);
                    }
                } else {
                    // if keputusan Tiada Borang K
                    if (kodNegeri.equals("04")) {
                        t.add("STRSTolakPecahBahagi_MLK.rdf");
                        t2.add("STPBP");
                        comm.reportGen(mohon, t, t2);

                        t.add("STRKertasPTG_MLK.rdf");
                        t2.add("KPTG");
                        comm.reportGen(mohon, t, t2);
                    } else {
                        t.add("ACQSrtPenarikan_NS.rdf");
                        t2.add("8TOL");
                        comm.reportGen(mohon, t, t2);
                    }

                }
            }
        }

        //added for PBBD reports

        if (mohon.getKodUrusan().getKod().equals("PBBD")) {
            mohonFasa = strService.findMohonFasa(mohon.getIdPermohonan(), "keputusan1");
            if (mohonFasa.getKeputusan() != null) {
                if (mohonFasa.getKeputusan().getKod().equals("L")) {
                    // if keputusan Lulus
                    if (kodNegeri.equals("04")) {
                        t.add("STRSLulusPecahBahagi_MLK.rdf");
                        t2.add("SKPBP");

                        t.add("STRKertasPTG_MLK.rdf");
                        t2.add("KPTG");
                        comm.reportGen(mohon, t, t2);

                        t.add("STRSBayaranKeJUPEM_MLK.rdf");
                        t2.add("SMPBJ");
                        comm.reportGen(mohon, t, t2);
                    } else {
                        t.add("STRSTuntutBayarPecahBahagi_NS.rdf");
                        t2.add("SKPBP");
                        comm.reportGen(mohon, t, t2);
                    }
                } else {
                    // if keputusan Tolak
                    if (kodNegeri.equals("04")) {
                        t.add("STRSTolakPecahBahagi_MLK.rdf");
                        t2.add("STPBP");
                        comm.reportGen(mohon, t, t2);

                        t.add("STRKertasPTG_MLK.rdf");
                        t2.add("KPTG");
                        comm.reportGen(mohon, t, t2);
                    } else {
                        t.add("STRSTolakRayuan_NS.rdf");
                        t2.add("STRY");
                        comm.reportGen(mohon, t, t2);
                    }

                }
            }
        }

    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {

        Notifikasi n = new Notifikasi();
        n.setTajuk("Makluman Keputusan PTG");
        n.setMesej("Permohonan " + context.getPermohonan().getKodUrusan().getNama() + " telah dihantar kepada Timbalan Pengarah Hakmilik Geran Tanah untuk makluman");
        Pengguna p = context.getPengguna();
        n.setCawangan(p.getKodCawangan());
        ArrayList<KodPeranan> list = new ArrayList<KodPeranan>();
        list.add(kodPerananDAO.findById("23"));
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(p);
        ia.setTarikhMasuk(new Date());
        n.setInfoAudit(ia);
        notifikasiService.addRolesToNotifikasi(n, p.getKodCawangan(), list);

        //added at PAT Melaka on 11/07/2012 to initiate Task to Registration Module
        Permohonan mohon = context.getPermohonan();
        FasaPermohonan mohonFasa = new FasaPermohonan();

        Pengguna peng = (Pengguna) context.getPengguna();
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(peng);
        infoAudit.setTarikhMasuk(new java.util.Date());
        idHakmilik = mohon.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik();
        String[] name = {"idHakmilik"};
        Object[] value = {idHakmilik};
        List<Hakmilik> senaraiHakmilik = hakmilikDAO.findByEqualCriterias(name, value, null);

        String kodNegeri = conf.getProperty("kodNegeri");
        if (mohon.getKodUrusan().getKod().equals("PB")) {
            mohonFasa = strService.findMohonFasa(mohon.getIdPermohonan(), "01DrafPenarikanBalik");
            if (mohonFasa.getKeputusan() != null) {
                if (mohonFasa.getKeputusan().getKod().equals("8B")) {
                    // if keputusan Lulus
                    if (kodNegeri.equals("04")) {
                        KodUrusan kod = kodUrusanDAO.findById("PB");
                        LOG.info(kod.getNama());
                        LOG.info(mohon.getFolderDokumen());
                        generateIdPerserahanWorkflow.generateIdPerserahanWorkflow(kod, peng, senaraiHakmilik, mohon);
                    } else {
                    }
                } else {
                    // if keputusan Tolak
                    if (kodNegeri.equals("04")) {
                        KodUrusan kod = kodUrusanDAO.findById("PBBT");
                        LOG.info(kod.getNama());
                        LOG.info(mohon.getFolderDokumen());
                        generateIdPerserahanWorkflow.generateIdPerserahanWorkflow(kod, peng, senaraiHakmilik, mohon);
                    } else {
                    }
                }
            }
        }

        //For Urusan PBBD
        if (mohon.getKodUrusan().getKod().equals("PBBD")) {
            mohonFasa = strService.findMohonFasa(mohon.getIdPermohonan(), "keputusan1");
            if (mohonFasa.getKeputusan() != null) {
                if (mohonFasa.getKeputusan().getKod().equals("L")) {
                    // if keputusan Lulus
                    if (kodNegeri.equals("04")) {
                        KodUrusan kod = kodUrusanDAO.findById("PBBL");
                        LOG.info(kod.getNama());
                        LOG.info(mohon.getFolderDokumen());
                        generateIdPerserahanWorkflow.generateIdPerserahanWorkflow(kod, peng, senaraiHakmilik, mohon);
                    } else {
                    }
                } else {
                    // if keputusan Tolak
                    if (kodNegeri.equals("04")) {
                        KodUrusan kod = kodUrusanDAO.findById("PBBT");
                        LOG.info(kod.getNama());
                        LOG.info(mohon.getFolderDokumen());
                        generateIdPerserahanWorkflow.generateIdPerserahanWorkflow(kod, peng, senaraiHakmilik, mohon);
                    } else {
                    }
                }
            }
        }

        context.addMessage(" - Makluman kepada Timbalan Pengarah Hakmilik Geran Tanah telah dihantar.");
        return proposedOutcome;
    }

    @Override
    public void afterComplete(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean beforeGenerateReports(StageContext ctx) {

        return true;
    }

    @Override
    public void afterPushBack(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String beforePushBack(StageContext context, String proposedOutcome) {
        return proposedOutcome;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }
    
}

