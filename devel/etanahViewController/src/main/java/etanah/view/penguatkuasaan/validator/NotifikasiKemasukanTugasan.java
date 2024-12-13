/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.penguatkuasaan.validator;
import com.google.inject.Inject;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodJabatanDAO;
import etanah.dao.KodPerananDAO;
import etanah.dao.KodRujukanDAO;
import etanah.dao.KodUrusanDAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.InfoAudit;
import etanah.model.Notifikasi;
import etanah.model.Pengguna;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import org.hibernate.Session;
import etanah.model.KodPeranan;
import etanah.service.NotifikasiService;
import etanah.service.StrataPtService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import etanah.view.pembangunan.validator.GenerateIdPermohonan;


/**
 *
 * @author sitifariza.hanim
 * modified by ctzainal
 */
public class NotifikasiKemasukanTugasan implements StageListener {

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    KodPerananDAO kodPerananDAO;
    @Inject
    NotifikasiService notifikasiService;
    @Inject
    GenerateIdPermohonan generateIdPerserahanWorkflow;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    KodUrusanDAO kodUrusanDAO;
    @Inject
    StrataPtService strService;
    @Inject
    KodJabatanDAO kodJabatanDAO;

    private Hakmilik hakmilik;
    private List<HakmilikPihakBerkepentingan> senaraipihak;
    @Inject
    KodRujukanDAO kodRujukanDAO;

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public List<HakmilikPihakBerkepentingan> getSenaraipihak() {
        return senaraipihak;
    }

    public void setSenaraipihak(List<HakmilikPihakBerkepentingan> senaraipihak) {
        this.senaraipihak = senaraipihak;
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

        Notifikasi n = new Notifikasi();
        n.setTajuk("Makluman Permohonan Modul Penguatkuasaan");
        n.setMesej("Makluman Aduan dari PPTD untuk id permohonan:" + context.getPermohonan().getKodUrusan().getNama() + " telah dihantar kepada Timbalan Pentadbir Tanah Daerah dan Pentadbir Tanah Daerah (Penguatkuasaan) untuk tindakan selanjutnya.");
        System.out.println("notify1");
        Pengguna p = context.getPengguna();
        n.setCawangan(p.getKodCawangan());
        ArrayList<KodPeranan> list = new ArrayList<KodPeranan>();
        System.out.println("notify2");
        list.add(kodPerananDAO.findById("PTD")); //n9-ptd
        System.out.println("notify3");
        list.add(kodPerananDAO.findById("TPTD"));//n9-tptd
        System.out.println("notify4");
//        list.add(kodPerananDAO.findById("26")); //melaka - pentadbir tanah
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(p);
        ia.setTarikhMasuk(new Date());
        n.setInfoAudit(ia);
        notifikasiService.addRolesToNotifikasi(n, p.getKodCawangan(), list);
//        initiate(context, proposedOutcome);
        context.addMessage(" Makluman kepada Timbalan Pentadbir Tanah Daerah dan Pentadbir Tanah Daerah telah dihantar.");


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


}
