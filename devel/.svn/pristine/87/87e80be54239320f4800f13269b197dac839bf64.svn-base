/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.kaunter;

import com.google.inject.Inject;
import etanah.dao.HakmilikDAO;
import etanah.model.Dokumen;
import etanah.model.DokumenKewangan;
import etanah.model.Hakmilik;
import etanah.service.AkaunService;
import java.util.ArrayList;

/**
 *
 * @author mohd.faidzal
 */
public class UrusanHasilValidator {

    @Inject
    AkaunService akaunService;
    @Inject
            HakmilikDAO hakmilikDAO;

    DokumenKewangan findResitHasil(ArrayList<String> hakmilikPermohonan, String teks1) {
        Hakmilik ha = hakmilikDAO.findById(hakmilikPermohonan.get(0));
        return akaunService.findResitByIdkewDokAndAkaun(teks1, ha.getAkaunCukai().getNoAkaun());
    }

}
