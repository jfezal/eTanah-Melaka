/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pelupusan.disClass;

/**
 *
 * @author afham
 */
public class DisPemohonController {
    
    private boolean tPemohon = Boolean.TRUE ;
    private boolean tIbuBapa = Boolean.TRUE ;
    private boolean tPengarah = Boolean.TRUE ;
    private boolean tAnak = Boolean.TRUE ;

    
    public DisPemohonController editPemohon() {
         DisPemohonController disPControl = new DisPemohonController();
         disPControl.settPemohon(Boolean.TRUE);
         disPControl.settPengarah(Boolean.TRUE);
         disPControl.settIbuBapa(Boolean.TRUE);
         disPControl.settAnak(Boolean.TRUE);
  
         return disPControl ;      
    }
    
    public DisPemohonController viewPemohon() {
         DisPemohonController disPControl = new DisPemohonController();
         disPControl.settPemohon(Boolean.FALSE);
         disPControl.settPengarah(Boolean.FALSE);
         disPControl.settIbuBapa(Boolean.FALSE);
         disPControl.settAnak(Boolean.FALSE);
         
         return disPControl ;      
    }

    public boolean istPemohon() {
        return tPemohon;
    }

    public void settPemohon(boolean tPemohon) {
        this.tPemohon = tPemohon;
    }

    public boolean istIbuBapa() {
        return tIbuBapa;
    }

    public void settIbuBapa(boolean tIbuBapa) {
        this.tIbuBapa = tIbuBapa;
    }

    public boolean istPengarah() {
        return tPengarah;
    }

    public void settPengarah(boolean tPengarah) {
        this.tPengarah = tPengarah;
    }

    public boolean istAnak() {
        return tAnak;
    }

    public void settAnak(boolean tAnak) {
        this.tAnak = tAnak;
    }
    
    
}
