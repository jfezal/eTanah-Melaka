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
public class Sek8fifthStage {
    String idPermohonan;
    String tarikh;
    String noPU;
    List<LampiranMyEtaPP> attchment;

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public String getTarikh() {
        return tarikh;
    }

    public void setTarikh(String tarikh) {
        this.tarikh = tarikh;
    }

    public String getNoPU() {
        return noPU;
    }

    public void setNoPU(String noPU) {
        this.noPU = noPU;
    }

    public List<LampiranMyEtaPP> getAttchment() {
        return attchment;
    }

    public void setAttchment(List<LampiranMyEtaPP> attchment) {
        this.attchment = attchment;
    }
    
}
