/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.theta.portal.stripes.utiliti;

/**
 *
 * @author mohd.faidzal
 */
public class PengawasanForm {
    String noAduan;
    String tarikhAduan;
    String jenisLaporan;
    String modul;
    String item;
    String namaPelapor;
    String jabatan;
    String unit;
    
    String urlReport;
    
    String namaPegawai;
    String status;
    String tarikhTerima;
    String bilHari;
    boolean view;

    public boolean isView() {
        return view;
    }

    public void setView(boolean view) {
        this.view = view;
    }
    
    

    public String getNoAduan() {
        return noAduan;
    }

    public void setNoAduan(String noAduan) {
        this.noAduan = noAduan;
    }

    public String getTarikhAduan() {
        return tarikhAduan;
    }

    public void setTarikhAduan(String tarikhAduan) {
        this.tarikhAduan = tarikhAduan;
    }

    public String getJenisLaporan() {
        return jenisLaporan;
    }

    public void setJenisLaporan(String jenisLaporan) {
        this.jenisLaporan = jenisLaporan;
    }

    public String getModul() {
        return modul;
    }

    public void setModul(String modul) {
        this.modul = modul;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getNamaPelapor() {
        return namaPelapor;
    }

    public void setNamaPelapor(String namaPelapor) {
        this.namaPelapor = namaPelapor;
    }

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    public String getNamaPegawai() {
        return namaPegawai;
    }

    public void setNamaPegawai(String namaPegawai) {
        this.namaPegawai = namaPegawai;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getBilHari() {
        return bilHari;
    }

    public void setBilHari(String bilHari) {
        this.bilHari = bilHari;
    }

    public String getTarikhTerima() {
        return tarikhTerima;
    }

    public void setTarikhTerima(String tarikhTerima) {
        this.tarikhTerima = tarikhTerima;
    }

    public String getUrlReport() {
        return urlReport;
    }

    public void setUrlReport(String urlReport) {
        this.urlReport = urlReport;
    }
    
    
}
