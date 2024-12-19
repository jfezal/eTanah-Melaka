/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pelupusan.validator;

import com.google.inject.Inject;
import etanah.dao.KodAgensiDAO;
import etanah.dao.KodRujukanDAO;
import etanah.model.FasaPermohonan;
import etanah.model.KodAgensi;
import etanah.model.KodDokumenAgensi;
import etanah.model.KodRujukan;
import etanah.model.KodUrusanAgensi;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanRujukanLuar;
import etanah.service.PelupusanService;
import etanah.view.stripes.pelupusan.disClass.DisLaporanTanahService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author zabedah.zainal
 */
public class JabatanPenilaianValidator implements StageListener {

    @Inject
    KodRujukanDAO kodRujukanDAO;
    @Inject
    KodAgensiDAO kodAgensiDAO;
    @Inject
    PelupusanService pelupusanService;
    @Inject
    DisLaporanTanahService disLaporanTanahService;
    private List<PermohonanRujukanLuar> senaraiRujukanLuar;
    private static final Logger LOG = Logger.getLogger(JabatanTeknikalValidator.class);
    private static int day;

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
        Pengguna pengguna = context.getPengguna();
        senaraiRujukanLuar = new ArrayList<PermohonanRujukanLuar>();
        String idAliran = new String();

        if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("MMMCL")) {
            idAliran = "tahun_hm_daftar";
        }
        FasaPermohonan mohonFasa = new FasaPermohonan();
        mohonFasa = pelupusanService.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), idAliran);

        if (mohonFasa != null && mohonFasa.getKeputusan().getKod().equals("96")) {
            PermohonanRujukanLuar mohonRujLuar = new PermohonanRujukanLuar();
            mohonRujLuar = (PermohonanRujukanLuar) disLaporanTanahService.findObject(mohonRujLuar, new String[]{permohonan.getIdPermohonan()}, 0);
            if (mohonRujLuar == null) {
                KodUrusanAgensi kua = new KodUrusanAgensi();
                kua = (KodUrusanAgensi) disLaporanTanahService.findObject(kua, new String[]{permohonan.getKodUrusan().getKod()}, 0);
                String stringAgensi = kua.getKodAgensi().getKod();
                if (kua != null) {
                    mohonRujLuar = new PermohonanRujukanLuar();
                    mohonRujLuar.setInfoAudit(disLaporanTanahService.findAudit(mohonRujLuar, "new", pengguna));
                    mohonRujLuar.setTempohHari(14);
                    KodRujukan kodRuj = new KodRujukan();
                    kodRuj = kodRujukanDAO.findById("FL");
                    mohonRujLuar.setKodRujukan(kodRuj);
                    mohonRujLuar.setTarikhRujukan(new java.util.Date());
                    mohonRujLuar.setTarikhDisampai(new java.util.Date());
                    mohonRujLuar.setTarikhJangkaTerima(getDateAfterDays(14));
                    mohonRujLuar.setUlasanMandatori("T");
                    mohonRujLuar.setPermohonan(permohonan);
                    mohonRujLuar.setCawangan(permohonan.getCawangan());
                    KodAgensi kodAgensi = new KodAgensi();
                    kodAgensi = kodAgensiDAO.findById(stringAgensi);
                    mohonRujLuar.setAgensi(kodAgensi);
                    mohonRujLuar.setNamaAgensi(kodAgensi.getNama());
                    pelupusanService.simpanPermohonanRujLuar(mohonRujLuar);
                }
            }
        }

        return proposedOutcome;
    }

    public static Date getDateAfterDays(int days) {
        Calendar cal = Calendar.getInstance();
        day = Calendar.DAY_OF_WEEK;
        if (day == Calendar.SUNDAY) {
            cal.add(Calendar.DATE, 18);
        } else if (day == Calendar.SATURDAY) {
            cal.add(Calendar.DATE, 19);
        } else {
            cal.add(Calendar.DATE, 20);// +days
        }
        return cal.getTime();
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
}

