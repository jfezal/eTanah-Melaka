/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pembangunan.disClass;

import etanah.model.Permohonan;
import etanah.model.PermohonanManual;

/**
 *
 * @author Admin
 */
public class DisPermohonanTanahTerdahulu {
    
    Permohonan permohonanTerdahulu;
    String noFail ;
    PermohonanManual permohonanManualSemasa;
    String statusKeputusan ;
    String tarikhKeputusan ;
    String keputusanOleh ;
    String namaPemohon ;

    public PermohonanManual getPermohonanManualSemasa() {
        return permohonanManualSemasa;
    }

    public void setPermohonanManualSemasa(PermohonanManual permohonanManualSemasa) {
        this.permohonanManualSemasa = permohonanManualSemasa;
    }
    
    public Permohonan getPermohonanTerdahulu() {
        return permohonanTerdahulu;
    }

    public void setPermohonanTerdahulu(Permohonan permohonanTerdahulu) {
        this.permohonanTerdahulu = permohonanTerdahulu;
    }    

    public String getStatusKeputusan() {
        return statusKeputusan;
    }

        public void setStatusKeputusan(String statusKeputusan) {
        this.statusKeputusan = statusKeputusan;
    }

    public String getNoFail() {
        return noFail;
    }

    public void setNoFail(String noFail) {
        this.noFail = noFail;
    }

    public String getTarikhKeputusan() {
        return tarikhKeputusan;
    }

    public void setTarikhKeputusan(String tarikhKeputusan) {
        this.tarikhKeputusan = tarikhKeputusan;
    }

    public String getKeputusanOleh() {
        return keputusanOleh;
    }

    public void setKeputusanOleh(String keputusanOleh) {
        this.keputusanOleh = keputusanOleh;
    }

    public String getNamaPemohon() {
        return namaPemohon;
    }

    public void setNamaPemohon(String namaPemohon) {
        this.namaPemohon = namaPemohon;
    }

    

}
