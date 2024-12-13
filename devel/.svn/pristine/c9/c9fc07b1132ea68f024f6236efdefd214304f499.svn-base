/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pelupusan.validator;

import com.google.inject.Inject;
import etanah.dao.KodCawanganDAO;
import etanah.dao.KodPerananDAO;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import etanah.model.Notifikasi;
import etanah.model.Pengguna;
import org.hibernate.Session;
import etanah.model.KodPeranan;
import etanah.model.Pemohon;
import etanah.model.PemohonHubungan;
import etanah.model.PenggunaPeranan;
import etanah.model.Permohonan;
import etanah.model.Pihak;
import etanah.model.PihakPengarah;
import etanah.service.NotifikasiService;
import etanah.service.PelupusanService;
import etanah.service.common.PemohonService;
import etanah.service.common.PihakService;
import etanah.view.stripes.pelupusan.disClass.DisLaporanTanahService;
import etanah.view.stripes.pelupusan.disClass.DisNotifikasiCase;
import java.util.ArrayList;
import java.util.Date;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Query;

/**
 *
 * @author shazwan
 * @version 1.0
 */
public class NotifikasiPermohonanV2Validator implements StageListener {

    private static final Logger LOG = Logger.getLogger(NotifikasiPermohonanV2Validator.class);
    @Inject
    etanah.Configuration conf;
    @Inject
    KodPerananDAO kodPerananDAO;
    @Inject
    NotifikasiService notifikasiService;
    @Inject
    PihakService pihakService;
    @Inject
    PemohonService pemohonService;
    @Inject
    PelupusanService pelupusanService;
    @Inject
    KodCawanganDAO kodCawanganDAO;
    @Inject
    DisLaporanTanahService disLaporanTanahService;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;

    @Override
    public boolean beforeStart(StageContext context) {
//        throw new UnsupportedOperationException("Not supported yet.");
        return true;
    }

    @Override
    public boolean beforeGenerateReports(StageContext ctx) {
        return true;
    }

    @Override
    public void onGenerateReports(StageContext context) {
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {

        Permohonan permohonan = context.getPermohonan();
        String stageId = context.getStageName();
        DisNotifikasiCase disNotifikasi = new DisNotifikasiCase();
        // send notification

        String kodNegeri = conf.getProperty("kodNegeri");
        /*
         * LEGEND 
         * data[0] -> Tajuk
         * data[1] -> Mesej
         * NOTE : n.setCawangan need to be set manual based on situation, since it effect the display on messagebox in apps.
         */
        int urusan = permohonan.getKodUrusan().getKod().equals("PPRU") ? 1
                : permohonan.getKodUrusan().getKod().equals("PPTR") ? 2
                : permohonan.getKodUrusan().getKod().equals("LPSP") ? 3
                : permohonan.getKodUrusan().getKod().equals("RAYT") ? 4
                : permohonan.getKodUrusan().getKod().equals("PBRZ") ? 5
                : permohonan.getKodUrusan().getKod().equals("PRIZ") ? 6
                : permohonan.getKodUrusan().getKod().equals("PBMT") ? 7
                : permohonan.getKodUrusan().getKod().equals("PLPS") ? 8
                : permohonan.getKodUrusan().getKod().equals("PPBB") ? 9
                : permohonan.getKodUrusan().getKod().equals("PBPTD") ? 10
                : permohonan.getKodUrusan().getKod().equals("PBPTG") ? 11
                : permohonan.getKodUrusan().getKod().equals("PRMP") ? 12
                : permohonan.getKodUrusan().getKod().equals("LMCRG") ? 13
                : permohonan.getKodUrusan().getKod().equals("PTGSA") ? 14
                : permohonan.getKodUrusan().getKod().equals("PHLP") ? 15
                : permohonan.getKodUrusan().getKod().equals("PRMMK") ? 16
                : permohonan.getKodUrusan().getKod().equals("BMBT") ? 17
                : permohonan.getKodUrusan().getKod().equals("PTPBP") ? 18
                : permohonan.getKodUrusan().getKod().equals("RLPS") ? 19
                : permohonan.getKodUrusan().getKod().equals("PLPTD") ? 20
                : permohonan.getKodUrusan().getKod().equals("PBMMK") ? 21
                : permohonan.getKodUrusan().getKod().equals("PBMMK") ? 22
                : permohonan.getKodUrusan().getKod().equals("RLPTG") ? 23
                : permohonan.getKodUrusan().getKod().equals("RLPTG") ? 24
                : permohonan.getKodUrusan().getKod().equals("RAYL") ? 25
                : permohonan.getKodUrusan().getKod().equals("RYKN") ? 26
                : 0;

        int negeri = kodNegeri.equals("04") ? 1
                : kodNegeri.equals("05") ? 2
                : 0;

        switch (urusan) {
            case 1:
                disNotifikasi.casePPRU(stageId, negeri, context, permohonan);
                break;
            case 2:
                disNotifikasi.casePPTR(stageId, negeri, context, permohonan);
                break;
            case 3:
                disNotifikasi.caseLPSP(stageId, negeri, context, permohonan);
                break;
            case 4:
                disNotifikasi.caseRAYT(stageId, negeri, context, permohonan);
                break;
            case 5:
                disNotifikasi.casePBRZ(stageId, negeri, context, permohonan);
                break;
            case 6:
                disNotifikasi.casePRIZ(stageId, negeri, context, permohonan);
                break;
            case 7:
                disNotifikasi.casePBMT(stageId, negeri, context, permohonan);
                break;
            case 8:
                disNotifikasi.casePLPS(stageId, negeri, context, permohonan);
                break;
            case 9:
                disNotifikasi.casePPBB(stageId, negeri, context, permohonan);
                break;
            case 10:
                disNotifikasi.casePBPTD(stageId, negeri, context, permohonan);
                break;
            case 11:
                disNotifikasi.casePBPTG(stageId, negeri, context, permohonan);
                break;
            case 12:
                disNotifikasi.casePRMP(stageId, negeri, context, permohonan);
                break;
            case 13:
                disNotifikasi.caseLMCRG(stageId, negeri, context, permohonan);
                break;
            case 14:
                disNotifikasi.casePTGSA(stageId, negeri, context, permohonan);
                break;
            case 15:
                disNotifikasi.casePHLP(stageId, negeri, context, permohonan);
                break;
            case 16:
                disNotifikasi.casePRMMK(stageId, negeri, context, permohonan);
                break;
            case 17:
                disNotifikasi.caseBMBT(stageId, negeri, context, permohonan);
                break;
            case 18:
                disNotifikasi.casePTPBP(stageId, negeri, context, permohonan);
                break;
            case 19:
                disNotifikasi.caseRLPS(stageId, negeri, context, permohonan);
                break;
            case 20:
                disNotifikasi.casePLPTD(stageId, negeri, context, permohonan);
                break;
            case 21:
                disNotifikasi.casePBMMK(stageId, negeri, context, permohonan);
                break;
            case 22:
                disNotifikasi.casePBGSA(stageId, negeri, context, permohonan);
                break;
            case 23:
                disNotifikasi.caseRLPTG(stageId, negeri, context, permohonan);
                break;
            case 24:
                disNotifikasi.casePWGSA(stageId, negeri, context, permohonan);
            case 25:
                disNotifikasi.caseRAYL(stageId, negeri, context, permohonan);
                break;
            case 26:
                disNotifikasi.caseRYKN(stageId, negeri, context, permohonan);
                break;
        }
        if (!disNotifikasi.getKumpBPEL().isEmpty()) {
            Pengguna pguna = context.getPengguna();
            Notifikasi notifikasi = new Notifikasi();
            notifikasi.setTajuk(disNotifikasi.getData()[0]);
            notifikasi.setMesej(disNotifikasi.getData()[1]);
            notifikasi.setCawangan(disNotifikasi.getNotifikasi().getCawangan());
            notifikasi.setPengguna(pguna);
            notifikasi.setInfoAudit(disLaporanTanahService.findAudit(notifikasi, "new", pguna));

            List<PenggunaPeranan> penggunaPerananList = new ArrayList<PenggunaPeranan>();
            penggunaPerananList = getSenaraiPenggunaPeranan(permohonan.getCawangan(), disNotifikasi.getKumpBPEL());
            disLaporanTanahService.getPelupusanService().simpanNotifikasi(notifikasi, penggunaPerananList);
            context.addMessage(" : Makluman Permohonan Telah Dihantar.");
        }
        return proposedOutcome;
    }

    public List<PenggunaPeranan> getSenaraiPenggunaPeranan(KodCawangan kod, List<String> listKod) {
        try {
            String findkumpBPEL = new String();
            int count = 1;
            for (String pu : listKod) {
                if (count == 1) {
                    findkumpBPEL = "('";
                    findkumpBPEL = findkumpBPEL + pu;
                } else if (count != 1 && count != listKod.size()) {
                    findkumpBPEL = findkumpBPEL + "','" + pu;
                }

                if (count == listKod.size()) {
                    findkumpBPEL = findkumpBPEL + "')";
                }
                count++;
            }
            String query = "Select u from etanah.model.PenggunaPeranan u where u.peranan.kumpBPEL in " + findkumpBPEL + " and u.pengguna.kodCawangan.kod = :kod order by u.pengguna.nama";
            Session session = sessionProvider.get();
            Query q = session.createQuery(query);
            q.setString("kod", kod.getKod());
            return q.list();

        } finally {
        }
    }

    @Override
    public void afterComplete(StageContext sc) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void afterPushBack(StageContext context) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String beforePushBack(StageContext context, String proposedOutcome) {
        return proposedOutcome;
    }
}
