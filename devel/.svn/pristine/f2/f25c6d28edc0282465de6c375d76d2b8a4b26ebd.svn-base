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
public class NotifikasiPermohonanValidator implements StageListener {

    private static final Logger LOG = Logger.getLogger(NotifikasiPermohonanValidator.class);
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
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

        // send notification
        Notifikasi n = new Notifikasi();
        Pengguna p = context.getPengguna();

        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(p);
        ia.setTarikhMasuk(new Date());

        n.setInfoAudit(ia);


        // n.setMesej(context.getPermohonan().getIdPermohonan() + " - " + context.getPermohonan().getKodUrusan().getNama() + " telah dihantar kepada Pentadbir Tanah untuk makluman");

        /*
         * LEGEND 
         * 1 - PPRU (PERMOHONAN PERMIT RUANG UDARA)
         * 2 - PPTR (PERMOHONAN MEMAJAK TANAH RIZAB)
         * NOTE : n.setCawangan need to be set manual based on situation, since it effect the display on messagebox in apps.
         */
        List<PenggunaPeranan> penggunaPerananList = new ArrayList<PenggunaPeranan>();
        List<String> kumpBPEL = new ArrayList<String>();
        int urusan = permohonan.getKodUrusan().getKod().equals("PPRU") ? 1
                : permohonan.getKodUrusan().getKod().equals("PPTR") ? 2
                : permohonan.getKodUrusan().getKod().equals("LPSP") ? 3 
                : permohonan.getKodUrusan().getKod().equals("PRIZ") ? 2 
                : permohonan.getKodUrusan().getKod().equals("RAYT") ? 4 
                : permohonan.getKodUrusan().getKod().equals("PBRZ") ? 5 
                : permohonan.getKodUrusan().getKod().equals("PJBTR") ? 6
                : permohonan.getKodUrusan().getKod().equals("PRMP") ? 7
                : 0;
                

        switch (urusan) {
            case 1:
                n.setTajuk("Makluman Keputusan MMKN");
                n.setCawangan(permohonan.getCawangan());
                n.setMesej(context.getPermohonan().getIdPermohonan() + " - " + permohonan.getKodUrusan().getNama() + " telah di" + context.getPermohonan().getKeputusan().getNama() + " oleh Pihak Berkuasa Negeri.");
                //send notifikasi
                if (stageId.equals("rekod_keputusan_mmkn")) {
                    kumpBPEL.add("kptlupus");
                    kumpBPEL.add("ptd");
                    kumpBPEL.add("tptd");
                    n.setCawangan(settingNotifikasiCawangan(permohonan.getCawangan(), "PTDPTD"));
                }
                break;
            case 2:
                n.setTajuk("Makluman Keputusan MMKN");
                n.setMesej(context.getPermohonan().getIdPermohonan() + " - " + permohonan.getKodUrusan().getNama() + " telah di" + context.getPermohonan().getKeputusan().getNama() + " oleh Pihak Berkuasa Negeri.");
                //send notifikasi
                if (stageId.equals("rekod_keputusan_mmkn")) {
                    kumpBPEL.add("kptlupus");
                    kumpBPEL.add("ptd");
                    kumpBPEL.add("tptd");
                    n.setCawangan(settingNotifikasiCawangan(permohonan.getCawangan(), "PTGPTD"));
                } else if (stageId.equals("RekodKeputusanMMK")) {
                    kumpBPEL.add("kptlupus");
                    kumpBPEL.add("ptd");
                    kumpBPEL.add("tptd");
                    n.setCawangan(settingNotifikasiCawangan(permohonan.getCawangan(), "PTGPTD"));
                }
                break;
            case 3:
                n.setTajuk("Makluman Keputusan MMKN");
                n.setMesej(context.getPermohonan().getIdPermohonan() + " - " + permohonan.getKodUrusan().getNama() + " telah di" + context.getPermohonan().getKeputusan().getNama() + " oleh Pihak Berkuasa Negeri.");
                //send notifikasi
                if (stageId.equals("rekod_keputusan_mmkn")) {
                    kumpBPEL.add("kptlupus");
                    kumpBPEL.add("ptd");
                    kumpBPEL.add("tptd");
                    n.setCawangan(settingNotifikasiCawangan(permohonan.getCawangan(), "PTGPTD"));
                } else if (stageId.equals("RekodKeputusanMMK")) {
                    kumpBPEL.add("kptlupus");
                    kumpBPEL.add("ptd");
                    kumpBPEL.add("tptd");
                    n.setCawangan(settingNotifikasiCawangan(permohonan.getCawangan(), "PTGPTD"));
                }
                break;
            case 4:
                n.setTajuk("Makluman Keputusan MMKN");
                n.setMesej(context.getPermohonan().getIdPermohonan() + " - " + permohonan.getKodUrusan().getNama() + " telah di" + context.getPermohonan().getKeputusan().getNama() + " oleh Pihak Berkuasa Negeri.");
                //send notifikasi
                if (stageId.equals("rekod_keputusan_mmkn")) {
                    kumpBPEL.add("ptd");
                    n.setCawangan(settingNotifikasiCawangan(permohonan.getCawangan(), "PTGPTD"));
                } else if (stageId.equals("RekodKeputusanMMK")) {
                    kumpBPEL.add("ptd");
                    n.setCawangan(settingNotifikasiCawangan(permohonan.getCawangan(), "PTGPTD"));
                } else if (stageId.equals("012_KeputusanMMKN")) {
                    kumpBPEL.add("ptd");
                    n.setCawangan(settingNotifikasiCawangan(permohonan.getCawangan(), "PTGPTD"));
                }
                break;
            case 5:
                n.setTajuk("Makluman Keputusan MMKN");
                n.setMesej(context.getPermohonan().getIdPermohonan() + " - " + permohonan.getKodUrusan().getNama() + " telah di" + context.getPermohonan().getKeputusan().getNama() + " oleh Pihak Berkuasa Negeri.");
                //send notifikasi
                if (stageId.equals("023_suratDanWarta")) {
                    kumpBPEL.add("pptd");
                    n.setCawangan(settingNotifikasiCawangan(permohonan.getCawangan(), "PTGPTD"));
                } else if (stageId.equals("032_SediaSuratdanWarta")) {
                    kumpBPEL.add("ptg");
                    n.setCawangan(settingNotifikasiCawangan(permohonan.getCawangan(), "PTGPTG"));
                } else if (stageId.equals("035_terimawarta")) {
                    kumpBPEL.add("ptd");
                    n.setCawangan(settingNotifikasiCawangan(permohonan.getCawangan(), "PTDPTD"));
                }
                break;
            case 6:
                n.setTajuk("Makluman Keputusan MMKN");
                n.setMesej(context.getPermohonan().getIdPermohonan() + " - " + permohonan.getKodUrusan().getNama() + " telah di" + context.getPermohonan().getKeputusan().getNama() + " oleh Pihak Berkuasa Negeri.");
                //send notifikasi
                if (stageId.equals("rekod_keputusan_MMKN_PTG")) {
                    kumpBPEL.add("pptg");
                    kumpBPEL.add("ptg");                    
                    n.setCawangan(settingNotifikasiCawangan(permohonan.getCawangan(), "PTGPTG"));
                } 
                break;
            case 7:
                n.setTajuk("Makluman Keputusan MMKN");
                n.setMesej(context.getPermohonan().getIdPermohonan() + " - " + permohonan.getKodUrusan().getNama() + " telah di" + context.getPermohonan().getKeputusan().getNama() + " oleh Pihak Berkuasa Negeri.");
                //send notifikasi
                if (stageId.equals("rekod_keputusan_mmkn")) {
                    kumpBPEL.add("kptlupus");
                    kumpBPEL.add("ptd");
                    kumpBPEL.add("tptd");
                    n.setCawangan(settingNotifikasiCawangan(permohonan.getCawangan(), "PTGPTD"));
                } else if (stageId.equals("RekodKeputusanMMK")) {
                    kumpBPEL.add("kptlupus");
                    kumpBPEL.add("ptd");
                    kumpBPEL.add("tptd");
                    n.setCawangan(settingNotifikasiCawangan(permohonan.getCawangan(), "PTGPTD"));
                }
                break;
        }
        if(!kumpBPEL.isEmpty()){           
            penggunaPerananList = getSenaraiPenggunaPeranan(permohonan.getCawangan(), kumpBPEL);
            disLaporanTanahService.getPelupusanService().simpanNotifikasi(n, penggunaPerananList);
            context.addMessage(" : Makluman Keputusan Permohonan Telah Dihantar.");        
        }
        return proposedOutcome;
    }

    private KodCawangan settingNotifikasiCawangan(KodCawangan cawPermohonan, String type) {
        KodCawangan kodCaw = new KodCawangan();
        int numType = type.equals("PTDPTD") ? 1
                : type.equals("PTDPTG") ? 2
                : type.equals("PTGPTD") ? 3
                : type.equals("PTGPTG") ? 4 : 0;
        if (numType == 2 || numType == 4) {
            kodCaw = kodCawanganDAO.findById("00");
            return kodCaw;
        } else {
            return cawPermohonan;
        }
    }

    public List<PenggunaPeranan> getSenaraiPenggunaPeranan(KodCawangan kod, List<String> listKod) {
        try {
            String kumpBPEL = new String();
            int count = 1;
            for (String pu : listKod) {
                if (count == 1) {
                    kumpBPEL = "('";
                    kumpBPEL = kumpBPEL + pu;
                } else {
                    kumpBPEL = kumpBPEL + "','" + pu;
                }

                if (count == listKod.size()) {
                    kumpBPEL = kumpBPEL + "')";
                }
                count++;
            }
            String query = "Select u from etanah.model.PenggunaPeranan u where u.peranan.kumpBPEL in" + kumpBPEL + " and u.pengguna.kodCawangan.kod = :kod order by u.pengguna.nama";
            LOG.info("QUERY->" + query);
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
        return "back";
    }
}
