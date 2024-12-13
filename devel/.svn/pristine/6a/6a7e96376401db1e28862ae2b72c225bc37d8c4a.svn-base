/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import java.util.Date;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;

/**
 *
 * @author faidzal
 */
@UrlBinding("/strata/percakapandalampemeriksaan")
public class PercakapanDalamPemeriksaanActionBean extends AbleActionBean {
private String idPermohonan;
private String pejabat;
private String nama;
private String namaPanggilan;
private String noKadPengenalan;
private String warnaKadPengenalan;
private String keturunan;
private Date tarikhLahir;
private String tempatLahir;
private int umur;
private String jantina;
private String pekerjaan;
private String alamatTempatKerja1;
private String alamatLain;
private int noTelefon;
private String namaBapa;
private String alamatBapa;
private String namaPegawai;
private String alamatPegawai;
private Date tarikhRakam;
private String jamRakam;
private String jurubahasa;
    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
    }

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("strata/kuatkuasa/percakapandalampemeriksaan.jsp").addParameter("tab", "true");
    }

    public String getAlamatBapa() {
        return alamatBapa;
    }

    public void setAlamatBapa(String alamatBapa) {
        this.alamatBapa = alamatBapa;
    }

    public String getAlamatLain() {
        return alamatLain;
    }

    public void setAlamatLain(String alamatLain) {
        this.alamatLain = alamatLain;
    }

    public String getAlamatPegawai() {
        return alamatPegawai;
    }

    public void setAlamatPegawai(String alamatPegawai) {
        this.alamatPegawai = alamatPegawai;
    }

    public String getAlamatTempatKerja1() {
        return alamatTempatKerja1;
    }

    public void setAlamatTempatKerja1(String alamatTempatKerja1) {
        this.alamatTempatKerja1 = alamatTempatKerja1;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public String getJamRakam() {
        return jamRakam;
    }

    public void setJamRakam(String jamRakam) {
        this.jamRakam = jamRakam;
    }

    public String getJantina() {
        return jantina;
    }

    public void setJantina(String jantina) {
        this.jantina = jantina;
    }

    public String getJurubahasa() {
        return jurubahasa;
    }

    public void setJurubahasa(String jurubahasa) {
        this.jurubahasa = jurubahasa;
    }

    public String getKeturunan() {
        return keturunan;
    }

    public void setKeturunan(String keturunan) {
        this.keturunan = keturunan;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNamaBapa() {
        return namaBapa;
    }

    public void setNamaBapa(String namaBapa) {
        this.namaBapa = namaBapa;
    }

    public String getNamaPanggilan() {
        return namaPanggilan;
    }

    public void setNamaPanggilan(String namaPanggilan) {
        this.namaPanggilan = namaPanggilan;
    }

    public String getNamaPegawai() {
        return namaPegawai;
    }

    public void setNamaPegawai(String namaPegawai) {
        this.namaPegawai = namaPegawai;
    }

    public String getNoKadPengenalan() {
        return noKadPengenalan;
    }

    public void setNoKadPengenalan(String noKadPengenalan) {
        this.noKadPengenalan = noKadPengenalan;
    }

    public int getNoTelefon() {
        return noTelefon;
    }

    public void setNoTelefon(int noTelefon) {
        this.noTelefon = noTelefon;
    }

    public String getPejabat() {
        return pejabat;
    }

    public void setPejabat(String pejabat) {
        this.pejabat = pejabat;
    }

    public String getPekerjaan() {
        return pekerjaan;
    }

    public void setPekerjaan(String pekerjaan) {
        this.pekerjaan = pekerjaan;
    }

    public Date getTarikhLahir() {
        return tarikhLahir;
    }

    public void setTarikhLahir(Date tarikhLahir) {
        this.tarikhLahir = tarikhLahir;
    }

    public Date getTarikhRakam() {
        return tarikhRakam;
    }

    public void setTarikhRakam(Date tarikhRakam) {
        this.tarikhRakam = tarikhRakam;
    }

    public String getTempatLahir() {
        return tempatLahir;
    }

    public void setTempatLahir(String tempatLahir) {
        this.tempatLahir = tempatLahir;
    }

    public int getUmur() {
        return umur;
    }

    public void setUmur(int umur) {
        this.umur = umur;
    }

    public String getWarnaKadPengenalan() {
        return warnaKadPengenalan;
    }

    public void setWarnaKadPengenalan(String warnaKadPengenalan) {
        this.warnaKadPengenalan = warnaKadPengenalan;
    }  
}
