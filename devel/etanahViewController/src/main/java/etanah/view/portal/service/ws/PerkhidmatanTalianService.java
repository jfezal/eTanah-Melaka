/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.portal.service.ws;

import com.google.inject.Injector;
import etanah.service.ws.PengumumanService;
import etanah.service.ws.StatusPermohonanService;
import etanah.service.ws.SenaraiKodService;
import etanah.service.ws.StatusSemakanAkaunService;
import etanah.service.ws.StatusTukarGantiService;
import etanah.service.ws.StatusTukarGantiByPartialService;
import etanah.view.dokumen.ws.DokumenInfo;
import etanah.view.etanahContextListener;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mohd.faidzal
 */
public class PerkhidmatanTalianService implements IPerkhidmatanTalianService {

    private static Injector injector = etanahContextListener.getInjector();
    StatusTukarGantiService statusTukarGantiService = injector.getInstance(StatusTukarGantiService.class);
    StatusPermohonanService statusPermohonanService = injector.getInstance(StatusPermohonanService.class);
    SenaraiKodService senaraiKodService = injector.getInstance(SenaraiKodService.class);
    PengumumanService pengumumanService = injector.getInstance(PengumumanService.class);
    StatusTukarGantiByPartialService statusTukarGantiByPartialService = injector.getInstance(StatusTukarGantiByPartialService.class);
    StatusSemakanAkaunService semakanAkaunService = injector.getInstance(StatusSemakanAkaunService.class);
    private String noLot;

    @Override
    public StatusPermohonan findStatusPermohonan(String idPermohonan) {
        return statusPermohonanService.findStatusPermohonan(idPermohonan);
    }

    public StatusSemakanAkaun findStatusByNoAkaun(String noAkaun) {
        return semakanAkaunService.findStatusSemakanAkaun(noAkaun);
    }

    @Override
    public StatusTukarGanti findStatusTukarGanti(String idHakmilik) {
        return statusTukarGantiService.findStatusTukarGanti(idHakmilik);
    }

    @Override
    public StatusTukarGanti statusTukarGantiByPartial(String kodDaerah, String noLot, String kodBpm, String jenisHakmilik) {
        return statusTukarGantiByPartialService.findStatusTukarGantiByPartial(kodDaerah, noLot, kodBpm, jenisHakmilik);
    }

    @Override
    public List<StatusPermohonan> findStatusPermohonanByNoPengenalan(String noKp) {
        return statusPermohonanService.findStatusPermohonanByNoKP(noKp);
    }

    @Override
    public List<SenaraiUrusan> findByKataKunci(String key) {
        return senaraiKodService.findByKeyWord(key);
//        throw new UnsupportedOperationException("Not supported yet."); //kod urusan
    }

    @Override
    public List<SenaraiDokumen> findbyKodUrusan(String kodUrusan) {
        return senaraiKodService.findbyKodUrusan(kodUrusan);
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<SenaraiKodDaerah> listKodDaerah() {
        return senaraiKodService.findAllKodDaerah();

    }

    @Override
    public List<SenaraiBandarPekanMukim> listBandarPekanMukim(String kodDaerah) {
        return senaraiKodService.findAllBandarPekanMukim(kodDaerah);
    }

    @Override
    public List<SenaraiKodHakmilik> listKod() {
        return senaraiKodService.findAllKodHakmilik();
    }

    @Override
    public List<SenaraiKodSeksyen> listKodSeksyen(String kodBpm) {
        return senaraiKodService.findKodSekyenbyBpm(kodBpm);
    }

    @Override
    public List<PengumumanForm> listPengumuman() {
        return pengumumanService.senaraiPengumumanAktif();
    }

    @Override
    public DokumenInfo muatTurunDokumen(String kodDokumen) {
        try {
            return senaraiKodService.muatTurunDokumen(kodDokumen);
        } catch (SQLException ex) {
            Logger.getLogger(PerkhidmatanTalianService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public StatusSemakanAkaun findStatusSemakanAkaun(String noAkaun) {
        return semakanAkaunService.findStatusSemakanAkaun(noAkaun);
    }
    
     @Override
    public List<StatusSemakanAkaun> findStatusSemakanAkaunByNoPengenalan(String noKp) {
        return semakanAkaunService.findStatusSemakanAkaunByNoKP(noKp);
    }

    @Override
    public DokumenInfo muatTurunBilCukai(String idHakmilik) {
        return semakanAkaunService.muatTurunBilCukai(idHakmilik);
          
    }
    
    @Override
    public StatusSemakanAkaun findStatusSemakanAkaunByHakmilik(String idHakmilik) {
        return semakanAkaunService.findStatusSemakanAkaunByIdHakmilik(idHakmilik);
    }
}
