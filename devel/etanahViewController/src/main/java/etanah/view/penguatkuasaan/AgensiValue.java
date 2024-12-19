/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.penguatkuasaan;


import etanah.model.KodJenisPengenalan;

import java.io.Serializable;
/**
 *
 * @author latifah.iskak
 * 
 */
public class AgensiValue implements Serializable {

    long idAgensi;
    String namaAgensi;
    String kodAgensi;
    String namaKetuaAgensi;
    String kodJenisPengenalan;
    String noPengenalan;
    String jawatan;
    String noTelefon;
    String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getIdAgensi() {
        return idAgensi;
    }

    public void setIdAgensi(long idAgensi) {
        this.idAgensi = idAgensi;
    }

    public String getJawatan() {
        return jawatan;
    }

    public void setJawatan(String jawatan) {
        this.jawatan = jawatan;
    }

    public String getKodJenisPengenalan() {
        return kodJenisPengenalan;
    }

    public void setKodJenisPengenalan(String kodJenisPengenalan) {
        this.kodJenisPengenalan = kodJenisPengenalan;
    }

    public String getNamaAgensi() {
        return namaAgensi;
    }

    public void setNamaAgensi(String namaAgensi) {
        this.namaAgensi = namaAgensi;
    }

    public String getNamaKetuaAgensi() {
        return namaKetuaAgensi;
    }

    public void setNamaKetuaAgensi(String namaKetuaAgensi) {
        this.namaKetuaAgensi = namaKetuaAgensi;
    }

    public String getNoPengenalan() {
        return noPengenalan;
    }

    public void setNoPengenalan(String noPengenalan) {
        this.noPengenalan = noPengenalan;
    }

    public String getNoTelefon() {
        return noTelefon;
    }

    public void setNoTelefon(String noTelefon) {
        this.noTelefon = noTelefon;
    }

    public String getKodAgensi() {
        return kodAgensi;
    }

    public void setKodAgensi(String kodAgensi) {
        this.kodAgensi = kodAgensi;
    }


   

}
