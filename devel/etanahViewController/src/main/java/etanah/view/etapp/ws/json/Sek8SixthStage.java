/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.etapp.ws.json;

import etanah.view.etapp.ws.form.LampiranMyEtaPP;
import java.util.List;

/**
 *
 * @author mohd.faidzal
 */
public class Sek8SixthStage {
    String idPermohonan;
    String tarikhHantar;
    String noRujukan;
    List<LampiranMyEtaPP> attchment;

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public String getTarikhHantar() {
        return tarikhHantar;
    }

    public void setTarikhHantar(String tarikhHantar) {
        this.tarikhHantar = tarikhHantar;
    }

    public String getNoRujukan() {
        return noRujukan;
    }

    public void setNoRujukan(String noRujukan) {
        this.noRujukan = noRujukan;
    }

    public List<LampiranMyEtaPP> getAttchment() {
        return attchment;
    }

    public void setAttchment(List<LampiranMyEtaPP> attchment) {
        this.attchment = attchment;
    }
    
}