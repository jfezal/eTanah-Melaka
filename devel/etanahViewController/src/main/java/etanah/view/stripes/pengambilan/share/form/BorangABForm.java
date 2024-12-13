/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pengambilan.share.form;

import etanah.model.Dokumen;

/**
 *
 * @author mohd.faidzal
 */
public class BorangABForm {

    String idPermohonan;
    String urusan;
    String tujuanPermohonan;
    Integer jumlahHakmilik = 0;
    Integer jumlahLuas;
    String luas;
    
    Long idBorangPerPermohonanA;
    Long idBorangPerPermohonanB;

    Dokumen borangA;
    String tarikhBorangA;
    String tandatanganA;
    String tarikhWarta;
    String tarikhKelulusanMMKN;

    Dokumen borangB;
    String tarikhBorangB;
    String tandatanganB;
    Integer jumTempatTampal;

    public Dokumen getBorangA() {
        return borangA;
    }

    public void setBorangA(Dokumen borangA) {
        this.borangA = borangA;
    }

    public String getTarikhBorangA() {
        return tarikhBorangA;
    }

    public void setTarikhBorangA(String tarikhBorangA) {
        this.tarikhBorangA = tarikhBorangA;
    }

    public Dokumen getBorangB() {
        return borangB;
    }

    public void setBorangB(Dokumen borangB) {
        this.borangB = borangB;
    }

    public String getTarikhBorangB() {
        return tarikhBorangB;
    }

    public void setTarikhBorangB(String tarikhBorangB) {
        this.tarikhBorangB = tarikhBorangB;
    }

    public Long getIdBorangPerPermohonanA() {
        return idBorangPerPermohonanA;
    }

    public void setIdBorangPerPermohonanA(Long idBorangPerPermohonanA) {
        this.idBorangPerPermohonanA = idBorangPerPermohonanA;
    }

    public Long getIdBorangPerPermohonanB() {
        return idBorangPerPermohonanB;
    }

    public void setIdBorangPerPermohonanB(Long idBorangPerPermohonanB) {
        this.idBorangPerPermohonanB = idBorangPerPermohonanB;
    }

    public String getTandatanganA() {
        return tandatanganA;
    }

    public void setTandatanganA(String tandatanganA) {
        this.tandatanganA = tandatanganA;
    }

    public String getTarikhWarta() {
        return tarikhWarta;
    }

    public void setTarikhWarta(String tarikhWarta) {
        this.tarikhWarta = tarikhWarta;
    }

    public String getTarikhKelulusanMMKN() {
        return tarikhKelulusanMMKN;
    }

    public void setTarikhKelulusanMMKN(String tarikhKelulusanMMKN) {
        this.tarikhKelulusanMMKN = tarikhKelulusanMMKN;
    }

    public String getTandatanganB() {
        return tandatanganB;
    }

    public void setTandatanganB(String tandatanganB) {
        this.tandatanganB = tandatanganB;
    }

    public Integer getJumTempatTampal() {
        return jumTempatTampal;
    }

    public void setJumTempatTampal(Integer jumTempatTampal) {
        this.jumTempatTampal = jumTempatTampal;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public String getUrusan() {
        return urusan;
    }

    public void setUrusan(String urusan) {
        this.urusan = urusan;
    }

    public String getTujuanPermohonan() {
        return tujuanPermohonan;
    }

    public void setTujuanPermohonan(String tujuanPermohonan) {
        this.tujuanPermohonan = tujuanPermohonan;
    }

    public Integer getJumlahHakmilik() {
        return jumlahHakmilik;
    }

    public void setJumlahHakmilik(Integer jumlahHakmilik) {
        this.jumlahHakmilik = jumlahHakmilik;
    }

    public Integer getJumlahLuas() {
        return jumlahLuas;
    }

    public void setJumlahLuas(Integer jumlahLuas) {
        this.jumlahLuas = jumlahLuas;
    }

    public String getLuas() {
        return luas;
    }

    public void setLuas(String luas) {
        this.luas = luas;
    }

}
