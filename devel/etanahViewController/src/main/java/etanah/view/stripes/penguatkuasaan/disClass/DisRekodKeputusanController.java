/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.penguatkuasaan.disClass;

import etanah.view.stripes.pelupusan.disClass.*;

/**
 *
 * @author Admin
 */
public class DisRekodKeputusanController {
    
    private boolean mMesyuarat = Boolean.TRUE;
    private boolean sKelulusan = Boolean.TRUE;
    private boolean sHakmilik = Boolean.TRUE ;
    private boolean sblmMesyuarat = Boolean.TRUE ;
    
    public DisRekodKeputusanController viewOnlyRekodKeputusanPT(){
        DisRekodKeputusanController disRKControl = new DisRekodKeputusanController();
        disRKControl.setmMesyuarat(Boolean.TRUE);
        disRKControl.setsKelulusan(Boolean.TRUE);
        disRKControl.setsHakmilik(Boolean.TRUE);
        disRKControl.setSblmMesyuarat(Boolean.FALSE);
        return disRKControl;
    }
    public DisRekodKeputusanController viewOnlyRekodKeputusan(){
        DisRekodKeputusanController disRKControl = new DisRekodKeputusanController();
        disRKControl.setmMesyuarat(Boolean.FALSE);
        disRKControl.setsKelulusan(Boolean.FALSE);
        disRKControl.setsHakmilik(Boolean.FALSE);
        disRKControl.setSblmMesyuarat(Boolean.FALSE);
        return disRKControl;
    }
    public DisRekodKeputusanController showFormBeforeMesyuarat(){
        DisRekodKeputusanController disRKControl = new DisRekodKeputusanController();
        disRKControl.setmMesyuarat(Boolean.TRUE);
        disRKControl.setsKelulusan(Boolean.FALSE);
        disRKControl.setsHakmilik(Boolean.FALSE);
        disRKControl.setSblmMesyuarat(Boolean.TRUE);
        return disRKControl;
    }

    public boolean ismMesyuarat() {
        return mMesyuarat;
    }

    public void setmMesyuarat(boolean mMesyuarat) {
        this.mMesyuarat = mMesyuarat;
    }

    public boolean issKelulusan() {
        return sKelulusan;
    }

    public void setsKelulusan(boolean sKelulusan) {
        this.sKelulusan = sKelulusan;
    }

    public boolean issHakmilik() {
        return sHakmilik;
    }

    public void setsHakmilik(boolean sHakmilik) {
        this.sHakmilik = sHakmilik;
    }

    public boolean isSblmMesyuarat() {
        return sblmMesyuarat;
    }

    public void setSblmMesyuarat(boolean sblmMesyuarat) {
        this.sblmMesyuarat = sblmMesyuarat;
    }
    
    
    
    
}
