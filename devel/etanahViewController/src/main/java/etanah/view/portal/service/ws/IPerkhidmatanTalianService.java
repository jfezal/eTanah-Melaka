/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.portal.service.ws;

import etanah.view.dokumen.ws.DokumenInfo;
import java.util.List;

/**
 *
 * @author mohd.faidzal
 */
public interface IPerkhidmatanTalianService {

    public StatusPermohonan findStatusPermohonan(String idPermohonan);

    public StatusTukarGanti findStatusTukarGanti(String idHakmilik);

    public StatusTukarGanti statusTukarGantiByPartial(String kodDaerah, String noLot, String kodBpm, String jenisHakmilik);

    public List<StatusPermohonan> findStatusPermohonanByNoPengenalan(String noKp);

    public List<SenaraiUrusan> findByKataKunci(String key);

    public List<SenaraiDokumen> findbyKodUrusan(String kodUrusan);
    
    public DokumenInfo muatTurunDokumen(String kodDokumen);

    public List<SenaraiKodDaerah> listKodDaerah();

    public List<SenaraiBandarPekanMukim> listBandarPekanMukim(String kodDaerah);
    
    public List<SenaraiKodSeksyen> listKodSeksyen(String kodBpm);

    public List<SenaraiKodHakmilik> listKod();
    
    public List<PengumumanForm> listPengumuman();
    
     public StatusSemakanAkaun findStatusSemakanAkaun(String noAkaun);
     
     public List<StatusSemakanAkaun> findStatusSemakanAkaunByNoPengenalan(String noKp);
     
     public DokumenInfo muatTurunBilCukai(String idHakmilik);
     
     public StatusSemakanAkaun findStatusSemakanAkaunByHakmilik(String idHakmilik);
    
    
}
