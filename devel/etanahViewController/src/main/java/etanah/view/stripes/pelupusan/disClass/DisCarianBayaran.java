/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pelupusan.disClass;

import etanah.model.FolderDokumen;
import etanah.model.KodBandarPekanMukim;
import etanah.model.KodUrusan;
import etanah.model.Permohonan;
import java.util.Date;

/**
 *
 * @author wazer
 */
public class DisCarianBayaran {
    private Permohonan mohon;
    private KodUrusan kodUrusan;
    private KodBandarPekanMukim kodBPM;
    private Date tarikhTerimaNotis;
    private Date tarikhBayaranAkhir;

    public KodBandarPekanMukim getKodBPM() {
        return kodBPM;
    }

    public void setKodBPM(KodBandarPekanMukim kodBPM) {
        this.kodBPM = kodBPM;
    }

    public KodUrusan getKodUrusan() {
        return kodUrusan;
    }

    public void setKodUrusan(KodUrusan kodUrusan) {
        this.kodUrusan = kodUrusan;
    }

    public Permohonan getMohon() {
        return mohon;
    }

    public void setMohon(Permohonan mohon) {
        this.mohon = mohon;
    }

    public Date getTarikhBayaranAkhir() {
        return tarikhBayaranAkhir;
    }

    public void setTarikhBayaranAkhir(Date tarikhBayaranAkhir) {
        this.tarikhBayaranAkhir = tarikhBayaranAkhir;
    }

    public Date getTarikhTerimaNotis() {
        return tarikhTerimaNotis;
    }

    public void setTarikhTerimaNotis(Date tarikhTerimaNotis) {
        this.tarikhTerimaNotis = tarikhTerimaNotis;
    }

    
    
    
}
