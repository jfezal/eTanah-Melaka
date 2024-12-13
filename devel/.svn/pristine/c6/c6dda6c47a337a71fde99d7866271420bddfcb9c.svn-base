/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.pembangunan.validator;

import com.google.inject.Inject;
import etanah.dao.KodPerananDAO;
import etanah.model.InfoAudit;
import etanah.model.Notifikasi;
import etanah.model.Pengguna;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import org.hibernate.Session;
import etanah.model.KodPeranan;
import etanah.service.NotifikasiService;
import java.util.ArrayList;
import java.util.Date;
import etanah.model.FasaPermohonan;
import etanah.model.Permohonan;
import etanah.service.PembangunanService;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author nursyazwani
 */
public class UlasanPindaanNotification implements StageListener {

    private static final Logger LOG = Logger.getLogger(UlasanPindaanNotification.class);
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    KodPerananDAO kodPerananDAO;
    @Inject
    NotifikasiService notifikasiService;
    @Inject
    PembangunanService devService;

    @Override
    public boolean beforeStart(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void onGenerateReports(StageContext context) {
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {

        List<FasaPermohonan> listfasa = devService.findFasaPermohonanByIdAliranList(context.getPermohonan().getIdPermohonan(), "perakuanjkbbptg");

        if (listfasa.size() > 0) {
            FasaPermohonan fp = listfasa.get(0);

//            FasaPermohonan fp = devService.findFasaPermohonanByIdAliran(context.getPermohonan().getIdPermohonan(),"perakuanjkbbptg");

            if (fp.getKeputusan().getKod().equals("TL")) {
                InfoAudit ia = new InfoAudit();
                Permohonan mohon = context.getPermohonan();
                ia = mohon.getInfoAudit();
                ia.setTarikhKemaskini(new Date());
                ia.setDiKemaskiniOleh(context.getPengguna());
                mohon.setInfoAudit(ia);
                mohon.setStatus("SS");
                devService.simpanPermohonan(mohon);

                Notifikasi n = new Notifikasi();
                n.setTajuk("Makluman Pindaan Kertas JKBB");
                n.setMesej("Kertas JKBB dengan id permohonan :" + context.getPermohonan().getIdPermohonan() + " telah dihantar ke PTD semula untuk pindaan. Ulasan pindaan: " + fp.getUlasan());
                Pengguna p = context.getPengguna();
                n.setCawangan(context.getPermohonan().getCawangan());
                ArrayList<KodPeranan> list = new ArrayList<KodPeranan>();
                //        list.add(kodPerananDAO.findById("53"));
                //        list.add(kodPerananDAO.findById("9"));
                list.add(kodPerananDAO.findById("77")); //melaka - ptd dev
                ia = new InfoAudit();
                ia.setDimasukOleh(p);
                ia.setTarikhMasuk(new Date());
                n.setInfoAudit(ia);
                notifikasiService.addRolesToNotifikasi(n, context.getPermohonan().getCawangan(), list);
                context.addMessage("Makluman kepada Pentadbir Tanah Daerah " + context.getPermohonan().getCawangan().getDaerah().getNama() + " telah dihantar.");
                proposedOutcome = "TL";
            } else if (fp.getKeputusan().getKod().equals("LK") || fp.getKeputusan().getKod().equals("XA") || fp.getKeputusan().getKod().equals("KS")) {
                context.addMessage("Permohonan Lengkap. Tiada Pindaan.");
                proposedOutcome = "LK";
            }
            LOG.info("---------proposedOutcome------------:" + proposedOutcome);
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

    @Override
    public void afterPushBack(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String beforePushBack(StageContext context, String proposedOutcome) {
        return proposedOutcome;
    }
}
