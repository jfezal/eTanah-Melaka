
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata.validator;

import com.google.inject.Inject;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodKeputusanDAO;
import etanah.dao.KodPerananDAO;
import etanah.dao.KodRujukanDAO;
import etanah.dao.KodUrusanDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.BangunanPetak;
import etanah.model.BangunanPetakAksesori;
import etanah.model.BangunanTingkat;
import etanah.model.Dokumen;
import etanah.model.FasaPermohonan;
import etanah.model.FolderDokumen;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodKeputusan;
import etanah.model.Notifikasi;
import etanah.model.Pengguna;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import org.hibernate.Session;
import etanah.model.KodPeranan;
import etanah.model.KodRujukan;
import etanah.model.KodUrusan;
import etanah.model.Permohonan;
import etanah.model.PermohonanBangunan;
import etanah.model.PermohonanRujukanLuar;
import etanah.model.PermohonanSkim;
import etanah.model.PermohonanStrata;
import etanah.report.ReportUtil;
import etanah.service.NotifikasiService;
import etanah.service.StrataPtService;
import etanah.service.common.TaskDebugService;
import etanah.view.strata.CommonService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import etanah.view.strata.GenerateIdPerserahanWorkflow;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Map;
import org.apache.commons.lang.ArrayUtils;
import etanah.model.LanjutanTempoh;

public class HTBValidator implements StageListener {

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
    @Inject
    KodRujukanDAO kodRujukanDAO;
    @Inject
    private TaskDebugService tds;
    @Inject
    private PermohonanDAO permohonanDAO;
    @Inject
    private KodKeputusanDAO kodKeputusanDAO;
    Pengguna pengguna;
    FolderDokumen fd;
    private HakmilikPermohonan mohonHakmilik = new HakmilikPermohonan();
    private static final Logger LOG = Logger.getLogger(HTBValidator.class);
    private String idHakmilik;
    private String idHakmilik1;
    private Hakmilik hakmilik;
    private Date tarikhLulus;

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

        if (kodNegeri.equals("04")) {
            if (mohon.getKodUrusan().getKod().equals("PBBS") || mohon.getKodUrusan().getKod().equals("PBBD")
                    || mohon.getKodUrusan().getKod().equals("PHPP") || mohon.getKodUrusan().getKod().equals("PHPC")
                    || mohon.getKodUrusan().getKod().equals("PSBS") || mohon.getKodUrusan().getKod().equals("PBS")) {
                mohonFasa = strService.findMohonFasa(mohon.getIdPermohonan(), "keputusan");
                if (mohonFasa.getKeputusan() != null) {
                    if (mohonFasa.getKeputusan().getKod().equals("L")) {
                        t.add("STRSuratLulus_MLK.rdf");
                        t2.add("SKL");
                        comm.reportGen(mohon, t, t2);
                    }
                }
            }

        } else {

            Permohonan permohonan = context.getPermohonan();
            mohonFasa = strService.findMohonFasa(permohonan.getIdPermohonan(), "keputusan");

            LOG.debug("--------permohonan----:" + permohonan.getIdPermohonan());
            if (permohonan != null) {

                permohonan.setKeputusan(mohonFasa.getKeputusan());
                peng = (Pengguna) context.getPengguna();

                permohonan.setKeputusanOleh(peng);
                permohonan.setTarikhKeputusan(new Date());
                strService.updateMohon(permohonan);

            }

            if (mohon.getKodUrusan().getKod().equals("PBBS") || mohon.getKodUrusan().getKod().equals("PBBD")
                    || mohon.getKodUrusan().getKod().equals("PHPP") || mohon.getKodUrusan().getKod().equals("PHPC")
                    || mohon.getKodUrusan().getKod().equals("PBS") || mohon.getKodUrusan().getKod().equals("PBS")) {
                mohonFasa = strService.findMohonFasa(mohon.getIdPermohonan(), "keputusan");
                if (mohonFasa.getKeputusan() != null) {
                    if (mohonFasa.getKeputusan().getKod().equals("L")) {
                        t.add("STRSuratMaklum_NS.rdf");
                        t2.add("SKL");
                        comm.reportGen(mohon, t, t2);
                    }
                }
            }
        }
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {

        Permohonan permohonan = context.getPermohonan();
        FasaPermohonan mohonFasa = new FasaPermohonan();

//        if (permohonan.getKodUrusan().getKod().equals("PBBS") || permohonan.getKodUrusan().getKod().equals("PBBD")
//                || permohonan.getKodUrusan().getKod().equals("PHPP") || permohonan.getKodUrusan().getKod().equals("PHPC")
//                || permohonan.getKodUrusan().getKod().equals("PBS") || permohonan.getKodUrusan().getKod().equals("PBS")) {
//
//            try {
//                Pengguna peng = (Pengguna) context.getPengguna();
//                InfoAudit infoAudit = new InfoAudit();
//                infoAudit.setDimasukOleh(peng);
//                infoAudit.setTarikhMasuk(new java.util.Date());
//                idHakmilik = permohonan.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik();
//                String[] name = {"idHakmilik"};
//                Object[] value = {idHakmilik};
//                List<Hakmilik> senaraiHakmilik = hakmilikDAO.findByEqualCriterias(name, value, null);
//                KodUrusan kod = kodUrusanDAO.findById("HTB");
//                LOG.info(kod.getNama());
//                LOG.info(permohonan.getFolderDokumen());
//                generateIdPerserahanWorkflow.generateIdPerserahanWorkflow(kod, peng, senaraiHakmilik, permohonan);
//            } catch (Exception e) {
//                LOG.error(e.getMessage());
//                return null;
//            }
//        }

        String kodNegeri = conf.getProperty("kodNegeri");
        LOG.info("--kodNegeri--" + kodNegeri);
        Permohonan mohon = context.getPermohonan();
        Notifikasi n = new Notifikasi();
        n.setTajuk("Makluman Keputusan PTG");
        if (kodNegeri.equals("04")) {
            String[] kodursns = {"RMHS1", "RMH1A", "RMHS5", "RMHS6", "RMHS7", "RTPS", "RTHS", "RBHS", "PBBS", "PBBD", "PHPP", "PHPC", "PBS", "PSBS"};
            if (!ArrayUtils.contains(kodursns, mohon.getKodUrusan().getKod())) {
                mohonFasa = strService.findMohonFasa(mohon.getIdPermohonan(), "keputusan1");
            } else {
                mohonFasa = strService.findMohonFasa(mohon.getIdPermohonan(), "keputusan");
            }
            KodUrusan kod = new KodUrusan();
            if (mohonFasa.getKeputusan().getKod().equals("L")) {
                n.setMesej(context.getPermohonan().getIdPermohonan() + " - " + context.getPermohonan().getKodUrusan().getNama() + " telah diluluskan oleh Pengarah Tanah dan Galian.");
            } else {
                n.setMesej(context.getPermohonan().getIdPermohonan() + " - " + context.getPermohonan().getKodUrusan().getNama() + " telah ditolak oleh Pengarah Tanah dan Galian.");
            }
            LOG.info("++++++++++++++notifikasi before complete++++++++++");
        } else {
            //n.setMesej(context.getPermohonan().getKodUrusan().getNama() + " Makluman kepada Timbalan Pengarah Tanah dan Galian");

            mohonFasa = strService.findMohonFasa(mohon.getIdPermohonan(), "keputusan");
            //   KodUrusan kod = new KodUrusan();
            if (mohonFasa.getKeputusan().getKod().equals("L")) {
                n.setMesej(context.getPermohonan().getIdPermohonan() + " - " + context.getPermohonan().getKodUrusan().getNama() + " telah diluluskan.");
            } else {
                n.setMesej(context.getPermohonan().getIdPermohonan() + " - " + context.getPermohonan().getKodUrusan().getNama() + " telah ditolak.");
            }

        }
        Pengguna p = context.getPengguna();
        n.setCawangan(p.getKodCawangan());
        ArrayList<KodPeranan> list = new ArrayList<KodPeranan>();
        if (kodNegeri.equals("04")) {
            list.add(kodPerananDAO.findById("63"));
            String[] kodursns = {"RMHS1", "RMH1A", "RMHS5", "RMHS6", "RMHS7"};
            if (ArrayUtils.contains(kodursns, mohon.getKodUrusan().getKod())) {
                mohonFasa = strService.findMohonFasa(mohon.getIdPermohonan(), "keputusan");

                list.add(kodPerananDAO.findById("23"));
            }
        } else {
            // list.add(kodPerananDAO.findById("233"));
            list.add(kodPerananDAO.findById("54"));
        }
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(p);
        ia.setTarikhMasuk(new Date());
        n.setInfoAudit(ia);
        LOG.info("--creating notice--calling to addRolesToNotifikasi--");

        notifikasiService.addRolesToNotifikasi(n, p.getKodCawangan(), list);

//        Permohonan mohon = context.getPermohonan();
        Pengguna peng = (Pengguna) context.getPengguna();
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(peng);
        infoAudit.setTarikhMasuk(new java.util.Date());
        idHakmilik = mohon.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik();
        String[] name = {"idHakmilik"};
        Object[] value = {idHakmilik};
        List<Hakmilik> senaraiHakmilik = hakmilikDAO.findByEqualCriterias(name, value, null);

        if (kodNegeri.equals("04")) {
            String[] kodursns = {"PBBS", "PBBD", "PHPP", "PHPC", "PBS", "PSBS"};
            if (!ArrayUtils.contains(kodursns, mohon.getKodUrusan().getKod())) {
                mohonFasa = strService.findMohonFasa(mohon.getIdPermohonan(), "cetak_pelan");
            } else {
                mohonFasa = strService.findMohonFasa(mohon.getIdPermohonan(), "cetak_pelan");
            }
            KodUrusan kod = new KodUrusan();
            if (mohonFasa != null) {

                if (mohon.getKodUrusan().getKod().equals("PBBS") || mohon.getKodUrusan().getKod().equals("PBBD")
                        || mohon.getKodUrusan().getKod().equals("PBS") //|| mohon.getKodUrusan().getKod().equals("PSBS")
                        || mohon.getKodUrusan().getKod().equals("PBTS")
                        || permohonan.getKodUrusan().getKod().equals("PHPP") || permohonan.getKodUrusan().getKod().equals("PHPC")) {

                    kod = kodUrusanDAO.findById("HTB");

                    LOG.info("--kod.getNama()--:" + kod.getNama());
                    LOG.info("--mohon.getFolderDokumen()--:" + mohon.getFolderDokumen());

                    Permohonan mohonReg = generateIdPerserahanWorkflow.generateIdPerserahanWorkflow1(kod, peng, senaraiHakmilik, mohon);

                    LOG.info("--mohonReg--:" + mohonReg.getIdPermohonan());
                    LOG.info("--Sending Strata Idmohon to Reg/Saving in Mohon Rujuluar--:");
                    PermohonanRujukanLuar permohonanRujLuar = new PermohonanRujukanLuar();
                    permohonanRujLuar.setInfoAudit(mohon.getInfoAudit());
                    permohonanRujLuar.setCawangan(peng.getKodCawangan());
                    permohonanRujLuar.setPermohonan(mohonReg);
                    permohonanRujLuar.setNoFail(mohonReg.getPermohonanSebelum().getIdPermohonan());
                    permohonanRujLuar.setNoRujukan("1");
                    permohonanRujLuar.setHakmilik(senaraiHakmilik.get(0));
                    try {
                        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                        Date date = new Date();
                        String formattedDate = dateFormat.format(date);
                        LOG.info("--formattedDate--" + formattedDate);
                        permohonanRujLuar.setTarikhRujukan(new Date(formattedDate));
                    } catch (Exception e) {
                        LOG.error(e.getMessage());
                    }
                    KodRujukan kodRujukan;
                    kodRujukan = kodRujukanDAO.findById("FL");
                    permohonanRujLuar.setKodRujukan(kodRujukan);
                    strService.SimpanMohonRujukLuar(permohonanRujLuar);

                    context.addMessage(" - Integrasi Dengan Unit Pendaftaran. " + mohonReg.getIdPermohonan() + " - Telah Dijana Untuk Dimaklumkan Kepada Unit Pendaftaran ("+mohonReg.getKodUrusan().getNama()+").");
                    LOG.info("--Saved in Mohon Rujuluar--:");
                }
                
                if (mohon.getKodUrusan().getKod().equals("PBBS") || mohon.getKodUrusan().getKod().equals("PBBD") 
                        || mohon.getKodUrusan().getKod().equals("PBS")
                        || mohon.getKodUrusan().getKod().equals("PBTS")) {
                    
                    kod = kodUrusanDAO.findById("ECPI");
                    LOG.info("AUTO JANA ID MOHON ECPI");
                    LOG.info("--kod.getNama()--:" + kod.getNama());
                    LOG.info("--mohon.getFolderDokumen()--:" + mohon.getFolderDokumen());
                    
                    Permohonan mohonReg1 = generateIdPerserahanWorkflow.generateIdPerserahanWorkflow1(kod, peng, senaraiHakmilik, mohon);
                    LOG.info("--mohonReg1--:" + mohonReg1.getIdPermohonan());
                    LOG.info("--Sending Strata Idmohon to Reg/Saving in Mohon Rujuluar--:");
                    PermohonanRujukanLuar permohonanRujLuar1 = new PermohonanRujukanLuar();
                    permohonanRujLuar1.setInfoAudit(mohon.getInfoAudit());
                    permohonanRujLuar1.setCawangan(peng.getKodCawangan());
                    permohonanRujLuar1.setPermohonan(mohonReg1);
                    permohonanRujLuar1.setNoFail(mohonReg1.getPermohonanSebelum().getIdPermohonan());
                    permohonanRujLuar1.setNoRujukan("32");
                    permohonanRujLuar1.setHakmilik(senaraiHakmilik.get(0));
                    try {
                        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                        Date date = new Date();
                        String formattedDate = dateFormat.format("20/12/2018");
                        LOG.info("--formattedDate--" + formattedDate);
                        permohonanRujLuar1.setTarikhRujukan(new Date(formattedDate));
                    } catch (Exception e) {
                        LOG.error(e.getMessage());
                    }
                    KodRujukan kodRujukan;
                    kodRujukan = kodRujukanDAO.findById("FL");
                    permohonanRujLuar1.setKodRujukan(kodRujukan);
                    strService.SimpanMohonRujukLuar(permohonanRujLuar1);
                    
                    context.addMessage(" - Integrasi Dengan Unit Pendaftaran. " + mohonReg1.getIdPermohonan() + " - Telah Dijana Untuk Dimaklumkan Kepada Unit Pendaftaran ("+mohonReg1.getKodUrusan().getNama()+").");
                    LOG.info("--Saved in Mohon Rujuluar--:");
                }
            }
        }

        return proposedOutcome;
//        return null;
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
        //throw new UnsupportedOperationException("Not supported yet.");

        String kodNegeri = conf.getProperty("kodNegeri");
        LOG.info("--kodNegeri--" + kodNegeri);
        Notifikasi n = new Notifikasi();
        n.setTajuk("Makluman Semakan Semula");
        if (kodNegeri.equals("04")) {
            n.setMesej(context.getPermohonan().getIdPermohonan() + " - " + context.getPermohonan().getKodUrusan().getNama() + " telah dihantar semula kepada Penolong Pegawai Tadbir / Penolong Pengarah Tanah Galian untuk semakan");
            LOG.info("++++++++++++++afterPushBack++++++++++");
        } else {
            n.setMesej(context.getPermohonan().getKodUrusan().getNama() + " Makluman kepada Timbalan Pengarah Tanah dan Galian");
        }
        Pengguna p = context.getPengguna();
        n.setCawangan(p.getKodCawangan());
        ArrayList<KodPeranan> list = new ArrayList<KodPeranan>();
        if (kodNegeri.equals("04")) {
            list.add(kodPerananDAO.findById("63"));
        } else {
            list.add(kodPerananDAO.findById("233"));
        }
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(p);
        ia.setTarikhMasuk(new Date());
        n.setInfoAudit(ia);
        LOG.info("--creating notice--calling to addRolesToNotifikasi--");
        notifikasiService.addRolesToNotifikasi(n, p.getKodCawangan(), list);

    }

    @Override
    public String beforePushBack(StageContext context, String proposedOutcome) {
        //return proposedOutcome;
        return "back";
    }

    private void updateNewPetak(StageContext sc) {
        for (PermohonanBangunan pb : sc.getPermohonan().getSenaraiBangunan()) {
            pb.setPermohonan(sc.getPermohonan().getPermohonanSebelum());
            strService.saveMohonBangunan(pb);
        }

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

    public String getIdHakmilik1() {
        return idHakmilik1;
    }

    public void setIdHakmilik1(String idHakmilik1) {
        this.idHakmilik1 = idHakmilik1;
    }

    public Date getTarikhLulus() {
        return tarikhLulus;
    }

    public void setTarikhLulus(Date tarikhLulus) {
        this.tarikhLulus = tarikhLulus;
    }
}
