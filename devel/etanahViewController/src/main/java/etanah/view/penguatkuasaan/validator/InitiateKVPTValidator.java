
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.penguatkuasaan.validator;

import com.google.inject.Inject;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodJabatanDAO;
import etanah.dao.KodKeputusanDAO;
import etanah.dao.KodPerananDAO;
import etanah.dao.KodRujukanDAO;
import etanah.dao.KodUrusanDAO;
import etanah.dao.PermohonanNotaDAO;
import etanah.model.FasaPermohonan;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodPeranan;
import etanah.model.KodUrusan;
import etanah.model.Notifikasi;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanNota;
import etanah.model.PermohonanPihak;
import etanah.model.PermohonanRujukanLuar;
import etanah.service.EnforceService;
import etanah.service.NotifikasiService;
import etanah.service.StrataPtService;
import etanah.service.common.EnforcementService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author w.fairul
 */
public class InitiateKVPTValidator implements StageListener {

    @Inject
    StrataPtService strService;
    @Inject
    GenerateIdPerserahanWorkflow1 generateIdPerserahanWorkflow1;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    KodUrusanDAO kodUrusanDAO;
    @Inject
    KodJabatanDAO kodJabatanDAO;
    @Inject
    KodRujukanDAO kodRujukanDAO;
    @Inject
    private etanah.Configuration conf;
    @Inject
    PermohonanNotaDAO permohonanNotaDAO;
    @Inject
    EnforceService enforceService;
    @Inject
    EnforcementService enforcementService;
    @Inject
    private KodKeputusanDAO kodKeputusanDAO;
    @Inject
    NotifikasiService notifikasiService;
    @Inject
    KodPerananDAO kodPerananDAO;
    private static final Logger LOG = Logger.getLogger(InitiateKVPTValidator.class);
    private Hakmilik hakmilik;
    private String idHakmilik;
    private String stageId;
    private String keputusan;
    private PermohonanNota permohonanNota;
    private List<Pemohon> listPemohon = new ArrayList<Pemohon>();
    private List<PermohonanPihak> listPermohonanPihak = new ArrayList<PermohonanPihak>();

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

    @Override
    public boolean beforeStart(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void onGenerateReports(StageContext context) {
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {

        Permohonan permohonan = context.getPermohonan();
        Pengguna peng = (Pengguna) context.getPengguna();
        //added bt latifah 27/3/12 : to check nota tindakan & to update table mohon(column kod_kpsn) when result is TK = Tiada Kes : at stage keputusan_kes

        Pengguna pengguna = context.getPengguna();

        stageId = context.getStageName();
        LOG.info("--------------stage id------------- : " + stageId);

        permohonanNota = enforceService.findNotaByIdMohon(permohonan.getIdPermohonan(), stageId);
        LOG.info("--------------Permohonan Nota Wujud or Not------------- : " + permohonanNota);
        try {
            if (permohonanNota == null) {
                LOG.info("--------------Permohonan Nota Null-------------");
                context.addMessage("Sila Masukkan Nota/Kertas Minit Untuk Permohonan : " + permohonan.getIdPermohonan());
                return null;
            }


            List<FasaPermohonan> senaraiFasa = permohonan.getSenaraiFasa();

            for (FasaPermohonan fp : senaraiFasa) {
                if (fp.getIdAliran().equalsIgnoreCase(stageId)) {
                    if (fp.getKeputusan() != null) {
                        keputusan = fp.getKeputusan().getKod();
                        LOG.info("--------------keputusan untuk stage " + stageId + "--------------- : " + keputusan);
                    }
                }
            }

            if ("04".equals(conf.getProperty("kodNegeri"))) {
                if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("422") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("423")) {
                    if (stageId.equalsIgnoreCase("keputusan_kes")) {
                        if (keputusan.equalsIgnoreCase("OP")) {  // OP=Siasatan Polis
                            LOG.info("-------111-------");
                            updateKeputusan(permohonan, pengguna);
                            Notifikasi n = new Notifikasi();
                            n.setTajuk(" Sedia Laporan Polis");
                            n.setMesej(permohonan.getIdPermohonan() + " bagi " + permohonan.getKodUrusan().getNama() + " perlu dibuat laporan polis. (Untuk Tindakan Pembantu Tadbir Kanan)");
                            System.out.println("notify1");
                            Pengguna p = context.getPengguna();
                            n.setCawangan(p.getKodCawangan());
                            ArrayList<KodPeranan> list = new ArrayList<KodPeranan>();
                            System.out.println("notify2");
                            list.add(kodPerananDAO.findById("PPTD"));
                            list.add(kodPerananDAO.findById("PTK"));
                            list.add(kodPerananDAO.findById("TPHG"));
                            System.out.println("notify3");
                            InfoAudit ia = new InfoAudit();
                            ia.setDimasukOleh(p);
                            ia.setTarikhMasuk(new Date());
                            n.setInfoAudit(ia);
                            notifikasiService.addRolesToNotifikasi(n, p.getKodCawangan(), list);

                            context.addMessage(" Maklumat telah berjaya dihantar. ");
                        }
                        if (keputusan.equalsIgnoreCase("TK")) {  //TK= Tiada Kes
                            LOG.info("-------1111-------");
                            updateKeputusan(permohonan, pengguna);

                        }
                    }
                }
                if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("429") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("424") && stageId.equalsIgnoreCase("keputusan_kes") && keputusan.equalsIgnoreCase("OP")) {
                    LOG.info("-------start initiate KVPT for sek429-------");
                    initiateKVPT(permohonan, peng);
                    Notifikasi n = new Notifikasi();
                    n.setTajuk(" Sedia Laporan Polis");
                    n.setMesej(permohonan.getIdPermohonan() + " bagi " + permohonan.getKodUrusan().getNama() + " perlu dibuat laporan polis. (Untuk Tindakan Pembantu Tadbir Kanan)");
                    System.out.println("notify1");
                    Pengguna p = context.getPengguna();
                    n.setCawangan(p.getKodCawangan());
                    ArrayList<KodPeranan> list = new ArrayList<KodPeranan>();
                    System.out.println("notify2");
                    list.add(kodPerananDAO.findById("PPTD"));
                    list.add(kodPerananDAO.findById("PTK"));
                    list.add(kodPerananDAO.findById("TPHG"));
                    System.out.println("notify3");
                    InfoAudit ia = new InfoAudit();
                    ia.setDimasukOleh(p);
                    ia.setTarikhMasuk(new Date());
                    n.setInfoAudit(ia);
                    notifikasiService.addRolesToNotifikasi(n, p.getKodCawangan(), list);

                    context.addMessage(" Maklumat telah berjaya dihantar. ");
                } else {
                    LOG.info("-------start initiate KVPT for all section except sek429-------");
                    initiateKVPT(permohonan, peng);
                }

            } else {
                //for NS
                initiateKVPT(permohonan, peng);
            }

        } catch (Exception e) {
            LOG.error(e.getMessage());
            return null;
        }

        return proposedOutcome;
//        return null;
    }

    public void updateKeputusan(Permohonan permohonan, Pengguna pengguna) {
        LOG.info("-------updateKeputusan-------");
        if (permohonan != null) {
            InfoAudit ia = permohonan.getInfoAudit();
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(new java.util.Date());

            permohonan.setInfoAudit(ia);
            permohonan.setKeputusan(kodKeputusanDAO.findById("SV")); // SV = kes selesai
            enforceService.simpanPermohonan(permohonan);

        }
    }

    public void initiateKVPT(Permohonan permohonan, Pengguna pengguna) {
        LOG.info("-------initiateKVPT-------");
        Date now = new Date();
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(pengguna);
        infoAudit.setTarikhMasuk(new java.util.Date());
        List<Hakmilik> senaraiHakmilik = new ArrayList<Hakmilik>();
        for (HakmilikPermohonan hakmilikPermohonan : permohonan.getSenaraiHakmilik()) {
            senaraiHakmilik.add(hakmilikPermohonan.getHakmilik());
        }

        PermohonanRujukanLuar mohonRujukanLuar = new PermohonanRujukanLuar();
        mohonRujukanLuar.setNoRujukan(idHakmilik);
        mohonRujukanLuar.setTarikhRujukan(now);
        KodUrusan kod = kodUrusanDAO.findById("KVPT");
        LOG.info(kod.getNama());
        LOG.info(permohonan.getFolderDokumen());

        if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("422") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("423")) {
            listPermohonanPihak = enforcementService.findByIdPermohonan(permohonan.getIdPermohonan(), kod.getKod());
            listPemohon = enforcementService.findListPemohon(permohonan.getIdPermohonan());
            generateIdPerserahanWorkflow1.generateIdPerserahan(kod, pengguna, senaraiHakmilik, permohonan, listPemohon, listPermohonanPihak);
        } else {

            generateIdPerserahanWorkflow1.generateIdPerserahanWorkflow1(kod, pengguna, senaraiHakmilik, permohonan, null);
        }
    }

    @Override
    public void afterComplete(StageContext context) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean beforeGenerateReports(StageContext ctx) {

        return true;
    }

    @Override
    public void afterPushBack(StageContext context) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String beforePushBack(StageContext context, String proposedOutcome) {
        return proposedOutcome;
    }
}
