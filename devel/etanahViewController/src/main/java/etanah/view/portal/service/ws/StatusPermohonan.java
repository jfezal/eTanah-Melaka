/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.portal.service.ws;

import etanah.model.KodKeputusan;
import java.util.Date;

/**
 *
 * @author wazer
 */
public class StatusPermohonan {

    String idPermohonan;
    String status;
    String kodUrusan;
    String tarikhKeputusan;
    String keputusanOleh;
    String namaUrusan;

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public String getNamaUrusan() {
        return namaUrusan;
    }

    public void setNamaUrusan(String namaUrusan) {
        this.namaUrusan = namaUrusan;
    }

    public String getKodUrusan() {
        return kodUrusan;
    }

    public void setKodUrusan(String kodUrusan) {
        this.kodUrusan = kodUrusan;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
