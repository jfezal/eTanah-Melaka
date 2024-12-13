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
 * @author mdizzat.mashrom
 */
public class SemakPembidaValidatorN9 implements StageListener {

    private static final Logger LOG = Logger.getLogger(SemakPembidaValidatorN9.class);
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
        String idPermohonan = permohonan.getIdPermohonan();
        
        List<Permohonan> listPermohonan = lelongService.getListPPTL(idPermohonan);
        if (!listPermohonan.isEmpty()) {
            LOG.info("----PPTL----");
            context.addMessage(idPermohonan + " - Terdapat Permohonan Tangguh 16O Pada Permohonan Ini. Urusan Tidak Dapat Diteruskan.");
            return null;
        }
        
        List<Permohonan> listPermohonan2 = lelongService.getListPPBL(idPermohonan);
        if (!listPermohonan2.isEmpty()) {       
            LOG.info("----PPBL----");
            context.addMessage(idPermohonan + " - Terdapat Permohonan Pembatalan 16O Pada Permohonan Ini. Urusan Tidak Dapat Diteruskan.");
            return null;
        }        
//        List<Permohonan> listPermohonan = lelongService.getListPPBLPPTL(permohonan.getIdPermohonan());
//        if (!listPermohonan.isEmpty()) {
//            context.addMessage(permohonan.getIdPermohonan() + " - Terdapat Permohonan Tangguh Pada Permohonan Ini.Urusan Tidak Dapat Diteruskan.");
//            return null;
//        }
        
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


        List<Lelongan> ll = lelongService.listLelonganAK(permohonan.getIdPermohonan());
        int bil = 0;
        for (Lelongan lelongan : ll) {
            if (lelongan.getBil() == 4) {
                bil = lelongan.getBil();
                lelongService.delete(lelongan);
            }
        }
        LOG.info("bil : " + bil);
        if (bil == 4 && !fasa.getKeputusan().getKod().equals("RM")) {
            context.addMessage(permohonan.getIdPermohonan() + " - Lelongan Telah Dilakukan Sebannyak 3 Kali,Sila Buat Keputusan Rujuk Mahkamah Di Tab Keputusan");
            return null;
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
        proposedOutcome = fasa.getKeputusan().getKod();
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
