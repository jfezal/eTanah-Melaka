/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.lelong.validator;

import com.google.inject.Inject;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodKeputusanDAO;
import etanah.dao.KodStatusLelonganDAO;
import etanah.model.FasaPermohonan;
import etanah.model.KodKeputusan;
import etanah.model.KodStatusLelongan;
import etanah.model.Lelongan;
import etanah.model.Permohonan;
import etanah.model.PermohonanTuntutanBayar;
import etanah.model.PermohonanTuntutanKos;
import etanah.service.common.LelongService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author mazurahayati.yusop
 */
public class SemakPembidaAdatValidatorN9 implements StageListener {

    private static final Logger LOG = Logger.getLogger(SemakPembidaAdatValidatorN9.class);
    @Inject
    LelongService lelongService;
    @Inject
    KodKeputusanDAO kodKeputusanDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    KodStatusLelonganDAO kodStatusLelonganDAO;

    @Override
    public boolean beforeStart(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean beforeGenerateReports(StageContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void onGenerateReports(StageContext context) {
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {
        LOG.info("------beforeComplete-------");
        Permohonan permohonan = context.getPermohonan();
        FasaPermohonan fasa = lelongService.findFasaPermohonanSemakPembida(permohonan.getIdPermohonan());

        if (fasa.getKeputusan().getKod().equals("LS") || fasa.getKeputusan().getKod().equals("AA")) {
            FasaPermohonan fasa2 = lelongService.findFasaPermohonanRekodBidaan(permohonan.getIdPermohonan());
            if (fasa2 != null) {
                lelongService.delete(fasa2);
            }

            List<Lelongan> ll = lelongService.listLelonganAK(permohonan.getIdPermohonan());
            if (ll.isEmpty()) {
                context.addMessage(permohonan.getIdPermohonan() + " - Sila Masukkan Tarikh Lelongan Di Tab Maklumat Keputusan");
                return null;
            } else {
                for (Lelongan lel : ll) {
                    if (lel.getTarikhLelong() == null) {
                        context.addMessage(permohonan.getIdPermohonan() + " - Sila Masukkan Tarikh Lelongan Di Tab Maklumat Keputusan");
                        return null;
                    }
                }
            }
        }

        if (fasa.getKeputusan().getKod().equals("AA")) {
            List<Lelongan> ll = lelongService.listLelonganAK(permohonan.getIdPermohonan());
            if (!ll.isEmpty()) {
                for (Lelongan lel : ll) {
                    if (lel.getSytJuruLelong()== null) {
                        context.addMessage(permohonan.getIdPermohonan() + " - Sila Pilih Jurulelong Berlesen Di Tab Maklumat Keputusan");
                        return null;
                    }
                }
            }
        }

        List<PermohonanTuntutanKos> list = new ArrayList<PermohonanTuntutanKos>();
        if (fasa.getKeputusan().getKod().equals("RM")) {
            //lelongan kali kedua,dah bayar bayaran di spoc,terus pg rujuk mahkamah
            List<PermohonanTuntutanKos> listPT = lelongService.listPT(permohonan.getIdPermohonan());
            if (!listPT.isEmpty()) {
                for (PermohonanTuntutanKos pp : listPT) {
                    List<PermohonanTuntutanBayar> listBayar = lelongService.listTB(pp.getIdKos());
                    if (!listBayar.isEmpty()) {
                        list.add(pp);
                    }
                }
            }
            List<Lelongan> listLel = lelongService.getLeloganALLDESC(permohonan.getIdPermohonan());
            for (Lelongan lelongan : listLel) {
                if (lelongan.getBil() == 4) {
                    lelongService.delete(lelongan);
                }
            }

            listLel = lelongService.getLeloganALLDESC(permohonan.getIdPermohonan());
            int bil2 = listLel.get(0).getBil();
            LOG.info("Bil2" + bil2);
            for (Lelongan lel : listLel) {
                KodStatusLelongan kod = null;
                if (lel.getBil() == bil2) {
                    LOG.info("----RM----");
                    kod = kodStatusLelonganDAO.findById("RM");
                    lel.setKodStatusLelongan(kod);
                    lelongService.saveOrUpdate(lel);
                }
            }
        }
        LOG.info("list : " + list.size());
        if (list.isEmpty()) {
            proposedOutcome = fasa.getKeputusan().getKod();
        } else {
            KodKeputusan kod = kodKeputusanDAO.findById("AD");
            proposedOutcome = kod.getKod();
        }
        return proposedOutcome;
    }

    @Override
    public void afterComplete(StageContext context) {
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
