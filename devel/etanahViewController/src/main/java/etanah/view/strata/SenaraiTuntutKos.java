package etanah.view.strata;

import etanah.model.KodTuntut;
import etanah.model.PermohonanTuntutanKos;
import java.math.BigDecimal;

public class SenaraiTuntutKos{

    public PermohonanTuntutanKos mohonTuntutKos = new PermohonanTuntutanKos();
    public KodTuntut kodTuntut = new KodTuntut();
    public int jumlahPetak = 0;

    public KodTuntut getKodTuntut() {
        return kodTuntut;
    }

    public void setKodTuntut(KodTuntut kodTuntut) {
        this.kodTuntut = kodTuntut;
    }

    public PermohonanTuntutanKos getMohonTuntutKos() {
        return mohonTuntutKos;
    }

    public void setMohonTuntutKos(PermohonanTuntutanKos mohonTuntutKos) {
        this.mohonTuntutKos = mohonTuntutKos;
    }

    public int getJumlahPetak() {
        return jumlahPetak;
    }

    public void setJumlahPetak(int jumlahPetak) {
        this.jumlahPetak = jumlahPetak;
    }
    
}
