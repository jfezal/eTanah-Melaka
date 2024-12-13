/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.service.acq.integrationflow;

import com.google.inject.Inject;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.ref.pengambilan.sek8a.RefPeringkat;

/**
 *
 * @author mohd.faidzal
 */
public class TugasanUtilitiSek8aService {

    @Inject
    Sek8aIntegrationFlowService sek8aIntegrationFlowService;
    RefPeringkat ref = new RefPeringkat();

    public boolean sediaTerimaKeputusanKertasMMK(Permohonan p, Pengguna pengguna) {
//if kepu
        String kod = ref.SEDIA_TERIMA_KPSN_MMK;
        return sek8aIntegrationFlowService.completeTask(kod, p, pengguna);
    }

}
