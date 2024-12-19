/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pengambilan.sek8;

import etanah.model.ambil.PermohonanHakmilikBaru;
import etanah.model.ambil.PermohonanPengambilanHakmilik;
import java.util.List;

/**
 *
 * @author mohd.faidzal
 */
public class KemasukanPelanPAForm {
    PermohonanPengambilanHakmilik permohonanPengambilanHakmilik;
    List<PermohonanHakmilikBaru> listHakmilikBaru;

    public PermohonanPengambilanHakmilik getPermohonanPengambilanHakmilik() {
        return permohonanPengambilanHakmilik;
    }

    public void setPermohonanPengambilanHakmilik(PermohonanPengambilanHakmilik permohonanPengambilanHakmilik) {
        this.permohonanPengambilanHakmilik = permohonanPengambilanHakmilik;
    }

    public List<PermohonanHakmilikBaru> getListHakmilikBaru() {
        return listHakmilikBaru;
    }

    public void setListHakmilikBaru(List<PermohonanHakmilikBaru> listHakmilikBaru) {
        this.listHakmilikBaru = listHakmilikBaru;
    }
    
}
