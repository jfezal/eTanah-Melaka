package etanah.view.stripes.pelupusan.disClass;

import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Shazwan 08102012 0316 PM
 * 
 * 
 */

public class DisPermohonanHakmilikPihak  {

    private HakmilikPermohonan mohonHakmilik;
    private HakmilikPihakBerkepentingan hakmilikPihak;

    public HakmilikPihakBerkepentingan getHakmilikPihak() {
        return hakmilikPihak;
    }

    public void setHakmilikPihak(HakmilikPihakBerkepentingan hakmilikPihak) {
        this.hakmilikPihak = hakmilikPihak;
    }

    public HakmilikPermohonan getMohonHakmilik() {
        return mohonHakmilik;
    }

    public void setMohonHakmilik(HakmilikPermohonan mohonHakmilik) {
        this.mohonHakmilik = mohonHakmilik;
    }
}
