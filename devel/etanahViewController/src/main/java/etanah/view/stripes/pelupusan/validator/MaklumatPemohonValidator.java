
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pelupusan.validator;

import com.google.inject.Inject;
import etanah.dao.KodPerananDAO;
import etanah.model.InfoAudit;
import etanah.model.KodPeranan;
import etanah.model.Notifikasi;
import etanah.model.Pemohon;
import etanah.model.PemohonHubungan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.Pihak;
import etanah.model.PihakPengarah;
import etanah.service.NotifikasiService;
import etanah.service.PelupusanService;
import etanah.service.common.PemohonService;
import etanah.service.common.PihakService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author Rohan
 */
public class MaklumatPemohonValidator implements StageListener {

    @Inject
    PihakService pihakService;
    @Inject
    PemohonService pemohonService;
    @Inject
    PelupusanService pelupusanService;
    @Inject
    KodPerananDAO kodPerananDAO;
    @Inject
    NotifikasiService notifikasiService;


    private Pihak pihak;
    private Pemohon pemohon;
    private List<PihakPengarah> pihakPengarahList;
    private List<PemohonHubungan> pemohonHubunganList;
    private List<PemohonHubungan> pemohonHubunganList1;
    private static final Logger LOG = Logger.getLogger(MaklumatPemohonValidator.class);

    @Override
    public boolean beforeStart(StageContext context) {

        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void onGenerateReports(StageContext context) {
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {

        try {
            Permohonan permohonan = context.getPermohonan();

            pemohon = pelupusanService.findPemohonByIdPemohon(permohonan.getIdPermohonan());
            if (pemohon == null) {
                context.addMessage("Sila Masukkan Maklumat Pemohon");
                return null;
            } else {
                Pihak pihak = pemohon.getPihak();
                if (pihak == null) {
                    context.addMessage("Sila Masukkan Maklumat Pemohon");
                    return null;
                } else {

                    if (pihak.getJenisPengenalan().getKod().equals("U") || pihak.getJenisPengenalan().getKod().equals("D")
                            || pihak.getJenisPengenalan().getKod().equals("S")) {
                        pihakPengarahList = pihakService.findPengarahByIDPihak(pihak.getIdPihak());
                        if (pihakPengarahList.isEmpty()) {
                            context.addMessage("Sila Masukkan Ahil Lembaga");
                            return null;
                        }
                    }

                    if (pihak.getJenisPengenalan().getKod().equals("B") || pihak.getJenisPengenalan().getKod().equals("L")
                            || pihak.getJenisPengenalan().getKod().equals("P") || pihak.getJenisPengenalan().getKod().equals("T")
                            || pihak.getJenisPengenalan().getKod().equals("I") || pihak.getJenisPengenalan().getKod().equals("O")
                            || pihak.getJenisPengenalan().getKod().equals("N") || pihak.getJenisPengenalan().getKod().equals("F")) {

                        pemohonHubunganList = pelupusanService.findHubunganByIDPemohon(pemohon.getIdPemohon());
                        if (pemohonHubunganList.isEmpty()) {
                            context.addMessage("Sila Masukkan Suami/Isteri");
                            return null;
                        }
                        pemohonHubunganList1 = pelupusanService.findHubunganByIDANAK(pemohon.getIdPemohon());
                        if (pemohonHubunganList1.isEmpty()) {
                            context.addMessage("Sila Masukkan Anak");
                            return null;
                        }
                    }

                }

            }
            NotifikasiPentadbirTanah(context);

        } catch (Exception e) {
            LOG.error(e.getMessage());
            return null;
        }

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

    public List<PihakPengarah> getPihakPengarahList() {
        return pihakPengarahList;
    }

    public void setPihakPengarahList(List<PihakPengarah> pihakPengarahList) {
        this.pihakPengarahList = pihakPengarahList;
    }

    public void NotifikasiPentadbirTanah(StageContext context) {
        Notifikasi n = new Notifikasi();
        n.setTajuk("Makluman Kemasukan Permohonan Lesen Pendudukan Sementara (Seksyen 65 KTN)");
        n.setMesej(context.getPermohonan().getKodUrusan().getNama() + " telah dihantar kepada Pentadbir Tanah untuk makluman");
        Pengguna p = context.getPengguna();
        n.setCawangan(p.getKodCawangan());
        ArrayList<KodPeranan> list = new ArrayList<KodPeranan>();
        list.add(kodPerananDAO.findById("10"));
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(p);
        ia.setTarikhMasuk(new Date());
        n.setInfoAudit(ia);
        notifikasiService.addRolesToNotifikasi(n, p.getKodCawangan(), list);
        context.addMessage("Makluman kepada Pentadbir Tanah telah dihantar.");
    }

     @Override
    public void afterPushBack(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String beforePushBack(StageContext context, String proposedOutcome) {
        return proposedOutcome;
    }
}

