/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.service.common;

import com.google.inject.Inject;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.Pemohon;
import etanah.model.Permohonan;
import etanah.model.PermohonanHubungan;
import etanah.model.PermohonanPihak;
import etanah.workflow.StageContext;
import java.util.List;
import org.apache.commons.lang.math.Fraction;

/**
 *
 * @author fikri
 */
public class ValidationService {

    @Inject PermohonanService permohonanService;
    
    @Inject PermohonanPihakService permohonanPihakService;

    @Inject PemohonService pemohonService;

    @Inject PermohonanAtasPihakBerkepentinganService mohonAtasPihakKepentinganService;   
   

    public boolean semakPihak(Permohonan permohonan, StageContext ctx) {

        List<HakmilikPermohonan> senaraiHakmilikTerlibat = permohonan.getSenaraiHakmilik();

        String idPermohonan = permohonan.getIdPermohonan();

        for (HakmilikPermohonan hp : senaraiHakmilikTerlibat) {
            if (hp == null || hp.getHakmilik() == null) {
                continue;
            }
            Hakmilik hm = hp.getHakmilik();
            String idHakmilik = hm.getIdHakmilik();
            List<PermohonanPihak> senaraiPP = permohonanPihakService
                    .getAllSenaraiPmohonPihakByHakmilikAndMohon(idPermohonan, idHakmilik);
            if (senaraiPP.isEmpty()) {
                ctx.addMessage("Sila masukan senarai bagi hakmilik " + idHakmilik);
                return false;
            }
        }

        return true;
    }

    public boolean semakHakmilik(Permohonan permohonan) {
        if(permohonan.getSenaraiHakmilik().isEmpty())
            return Boolean.FALSE;

        return Boolean.TRUE;
    }

    public boolean semakPemohon( Permohonan permohonan, StageContext ctx ) {

         List<HakmilikPermohonan> senaraiHakmilikTerlibat = permohonan.getSenaraiHakmilik();

        String idPermohonan = permohonan.getIdPermohonan();

        for (HakmilikPermohonan hp : senaraiHakmilikTerlibat) {
            if (hp == null || hp.getHakmilik() == null) {
                continue;
            }
            Hakmilik hm = hp.getHakmilik();
            String idHakmilik = hm.getIdHakmilik();
            List<Pemohon> senaraiPemohon = pemohonService.senaraiPemohonByIdPermohonanIdHakmilik(idPermohonan, idHakmilik);
            if (senaraiPemohon.isEmpty()) {
                ctx.addMessage("Sila masukan senarai pemohon bagi hakmilik " + idHakmilik);
                return false;
            }
        }

        return true;
    }

    public boolean semakMohonAtasPerserahan(Permohonan permohonan){
        if(permohonan.getSenaraiPermohonanAtasPerserahan().isEmpty()) return Boolean.FALSE;
        return Boolean.TRUE;
    }

    public boolean semakMohonAtasPihak (Permohonan permohonan) {
        if (permohonan.getSenaraiPermohonanAtasPihakBerkepentingan().isEmpty()) return false;
        return true;
    }

    public boolean semakMohonHubungan (Permohonan permohonan) {
        List<PermohonanHubungan> senarai = permohonanService.getSenaraiHubungan( permohonan.getIdPermohonan(), null );
        if (senarai.isEmpty()) return false;
        return true;
    }

}
