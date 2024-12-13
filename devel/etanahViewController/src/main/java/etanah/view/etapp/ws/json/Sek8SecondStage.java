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
public class Sek8SecondStage {
    String idPermohonan;
    LampiranMyEtaPP mmkPaper;
    List<LampiranMyEtaPP> attchment;

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public LampiranMyEtaPP getMmkPaper() {
        return mmkPaper;
    }

    public void setMmkPaper(LampiranMyEtaPP mmkPaper) {
        this.mmkPaper = mmkPaper;
    }

    public List<LampiranMyEtaPP> getAttchment() {
        return attchment;
    }

    public void setAttchment(List<LampiranMyEtaPP> attchment) {
        this.attchment = attchment;
    }
    
    
}
