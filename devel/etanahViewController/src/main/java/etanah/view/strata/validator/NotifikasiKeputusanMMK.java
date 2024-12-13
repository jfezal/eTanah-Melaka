/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata.validator;

import com.google.inject.Inject;
import etanah.dao.KodCawanganDAO;
import etanah.dao.KodPerananDAO;
import etanah.model.FasaPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodPeranan;
import etanah.model.Notifikasi;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.service.NotifikasiService;
import etanah.service.StrataPtService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import java.util.ArrayList;
import org.apache.commons.lang.ArrayUtils;
import etanah.model.KodUrusan;
import java.util.Date;
import org.apache.log4j.Logger;
import org.hibernate.Session;

/**
 *
 * @author zadhirul.farihim
 */
public class NotifikasiKeputusanMMK implements StageListener {

    private static final Logger LOG = Logger.getLogger(NotifikasiKeputusanMMK.class);
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    KodPerananDAO kodPerananDAO;
    @Inject
    NotifikasiService notifikasiService;
    @Inject
    StrataPtService strService;
    @Inject
    KodCawanganDAO kodCawanganDAO;
    private FasaPermohonan fasaPermohonan;
    private String keputusan = "";
    @Inject
    etanah.Configuration conf;

    @Override
    public boolean beforeStart(StageContext sc) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean beforeGenerateReports(StageContext sc) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void onGenerateReports(StageContext sc) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {
        Permohonan permohonan = context.getPermohonan();
        Pengguna p = context.getPengguna();
        fasaPermohonan = strService.findFasaPermohonanByIdAliran(permohonan.getIdPermohonan(), "keputusanmmk");
        if (fasaPermohonan.getKeputusan() != null) {
            if (fasaPermohonan.getKeputusan().getKod().equals("L")) {
                keputusan = "diluluskan";
            } else {
                keputusan = "ditolak";
            }
        }
        Notifikasi n = new Notifikasi();
        InfoAudit ia = new InfoAudit();
        n.setTajuk("Makluman " + permohonan.getKodUrusan().getNama());
        if (fasaPermohonan.getKeputusan().getKod().equals("L")) {
            n.setMesej(permohonan.getIdPermohonan() + " - " + permohonan.getKodUrusan().getNama() + " telah diluluskan.");
        } else {
            n.setMesej(permohonan.getIdPermohonan() + " - " + permohonan.getKodUrusan().getNama() + " telah ditolak.");
        }
        n.setCawangan(kodCawanganDAO.findById("00"));
        ArrayList<KodPeranan> list = new ArrayList<KodPeranan>();
        list.add(kodPerananDAO.findById("22"));
        list.add(kodPerananDAO.findById("23"));
        //list.add(kodPerananDAO.findById("63"));
        //list.add(kodPerananDAO.findById("12"));
        ia.setDimasukOleh(p);
        ia.setTarikhMasuk(new Date());
        n.setInfoAudit(ia);
        LOG.info("--creating notice--calling to addRolesToNotifikasi--");
        notifikasiService.addRolesToNotifikasi(n, kodCawanganDAO.findById("00"), list);
        context.addMessage(" - Penghantaran Berjaya.");
        return proposedOutcome;
        //return null;
    }

    @Override
    public void afterComplete(StageContext sc) {
    }

    @Override
    public String beforePushBack(StageContext sc, String string) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void afterPushBack(StageContext sc) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
