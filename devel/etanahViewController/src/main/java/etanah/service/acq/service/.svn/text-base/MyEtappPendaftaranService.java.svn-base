/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.service.acq.service;

import com.google.inject.Inject;
import etanah.dao.KodUrusanDAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.service.ambil.MyEtappService;
import etanah.view.strata.GenerateIdPerserahanWorkflow;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mohd.faidzal
 */
public class MyEtappPendaftaranService {

    @Inject
    KodUrusanDAO kodUrusanDAO;
    @Inject
    MyEtappService myEtappService;

    @Inject
    GenerateIdPerserahanWorkflow generateIdPerserahanWorkflow;

    public void endors(Permohonan p, Integer k, Object v, Pengguna pengguna) {
        String kodUrusan = "";
        switch (k) {
            case 1:
                kodUrusan = "ABT-D";
                setValueEndos(kodUrusan, p, pengguna);
                break;
            case 2:
                kodUrusan = "ABTKB";
                setValueEndos(kodUrusan, p, pengguna);
                break;
        }
    }

    public void endos(String kodUrusan, Permohonan permohonan, Pengguna peng, List<Hakmilik> lisHakmilik) {
        generateIdPerserahanWorkflow.generateIdPerserahanWorkflow2(kodUrusanDAO.findById(kodUrusan), peng, lisHakmilik, permohonan, "");
    }

    private void setValueEndos(String kodUrusan, Permohonan p, Pengguna pengguna) {
        List<HakmilikPermohonan> listHp00 = myEtappService.findHakmilikByIdPermohonanKodCaw(p.getIdPermohonan(), "00");
        List<HakmilikPermohonan> listHp01 = myEtappService.findHakmilikByIdPermohonanKodCaw(p.getIdPermohonan(), "01");
        List<HakmilikPermohonan> listHp02 = myEtappService.findHakmilikByIdPermohonanKodCaw(p.getIdPermohonan(), "02");
        List<HakmilikPermohonan> listHp03 = myEtappService.findHakmilikByIdPermohonanKodCaw(p.getIdPermohonan(), "03");

        if (!listHp00.isEmpty()) {
            endos(kodUrusan, p, pengguna, setListHakmilik(listHp00));
        }
        if (!listHp01.isEmpty()) {
            endos(kodUrusan, p, pengguna, setListHakmilik(listHp01));
        }
        if (!listHp02.isEmpty()) {
            endos(kodUrusan, p, pengguna, setListHakmilik(listHp02));
        }
        if (!listHp03.isEmpty()) {
            endos(kodUrusan, p, pengguna, setListHakmilik(listHp03));
        }
    }

    private List<Hakmilik> setListHakmilik(List<HakmilikPermohonan> listHp00) {

        List<Hakmilik> lh = new ArrayList<Hakmilik>();
        for (HakmilikPermohonan hp : listHp00) {
            lh.add(hp.getHakmilik());
        }
        return lh;
    }

}
