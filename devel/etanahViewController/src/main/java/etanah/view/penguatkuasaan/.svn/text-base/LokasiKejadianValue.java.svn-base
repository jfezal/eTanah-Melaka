/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.penguatkuasaan;

import com.google.inject.Inject;
import etanah.dao.AduanLokasiDAO;
import etanah.model.KodBandarPekanMukim;
import etanah.model.KodPemilikan;
import java.io.Serializable;

/**
 *
 * @author latifah.iskak
 */
public class LokasiKejadianValue implements Serializable {

    @Inject
    AduanLokasiDAO aduanLokasiDAO;
    long idAduanLokasi;
    KodBandarPekanMukim bandarPekanMukim;
    KodPemilikan jenisTanah;
    String noLot;
    String lokasi;

    public KodBandarPekanMukim getBandarPekanMukim() {
        return bandarPekanMukim;
    }

    public void setBandarPekanMukim(KodBandarPekanMukim bandarPekanMukim) {
        this.bandarPekanMukim = bandarPekanMukim;
    }

    public long getIdAduanLokasi() {
        return idAduanLokasi;
    }

    public void setIdAduanLokasi(long idAduanLokasi) {
        this.idAduanLokasi = idAduanLokasi;
    }

    public KodPemilikan getJenisTanah() {
        return jenisTanah;
    }

    public void setJenisTanah(KodPemilikan jenisTanah) {
        this.jenisTanah = jenisTanah;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getNoLot() {
        return noLot;
    }

    public void setNoLot(String noLot) {
        this.noLot = noLot;
    }
}
