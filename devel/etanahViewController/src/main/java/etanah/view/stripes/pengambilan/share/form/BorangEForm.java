/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pengambilan.share.form;

import etanah.model.Dokumen;
import etanah.model.HakmilikPermohonan;
import etanah.model.ambil.BorangPerHakmilik;
import etanah.model.ambil.BorangPerPB;
import etanah.model.ambil.HakmilikPerbicaraan;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mohd.faidzal
 */
public class BorangEForm {
    Long id;
    HakmilikPermohonan hm;
    Long jumlahPihak;
    String status;
    String tandatangan;
    Dokumen dok;
    BorangPerHakmilik bphG;
    HakmilikPerbicaraan hakmilikPerbicaraan;
    
    List<BorangFTuntutanForm> listBorangPerPB = new ArrayList<BorangFTuntutanForm>();

    public String getTandatangan() {
        return tandatangan;
    }

    public void setTandatangan(String tandatangan) {
        this.tandatangan = tandatangan;
    }

    public Dokumen getDok() {
        return dok;
    }

    public void setDok(Dokumen dok) {
        this.dok = dok;
    }

    public List<BorangFTuntutanForm> getListBorangPerPB() {
        return listBorangPerPB;
    }

    public void setListBorangPerPB(List<BorangFTuntutanForm> listBorangPerPB) {
        this.listBorangPerPB = listBorangPerPB;
    }

    public HakmilikPermohonan getHm() {
        return hm;
    }

    public void setHm(HakmilikPermohonan hm) {
        this.hm = hm;
    }

    public Long getJumlahPihak() {
        return jumlahPihak;
    }

    public void setJumlahPihak(Long jumlahPihak) {
        this.jumlahPihak = jumlahPihak;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BorangPerHakmilik getBphG() {
        return bphG;
    }

    public void setBphG(BorangPerHakmilik bphG) {
        this.bphG = bphG;
    }

    public HakmilikPerbicaraan getHakmilikPerbicaraan() {
        return hakmilikPerbicaraan;
    }

    public void setHakmilikPerbicaraan(HakmilikPerbicaraan hakmilikPerbicaraan) {
        this.hakmilikPerbicaraan = hakmilikPerbicaraan;
    }

    
 
    
}
