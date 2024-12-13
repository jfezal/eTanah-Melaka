/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.etapp.ws;

import etanah.model.etapp.EtappBorangE;
import etanah.model.etapp.EtappBorangF;
import etanah.model.etapp.EtappBorangH;
import etanah.model.etapp.EtappHakmilik;

/**
 *
 * @author faidzal
 */
public class HakmilikDataSet {

    private String idEtHakmilik;
    private String idHakmilik;
    private String jenisHakmilik;
    private String noHakmilik;
    private String kodLuas;
    private Integer luas;
    private String basimati;
    private String bbsimati;
    private String kodPembahagian;
    private BorangE[] listEtappBorangE;
    private BorangF[] listEtappBorangF;


    public String getIdEtHakmilik() {
        return idEtHakmilik;
    }

    public void setIdEtHakmilik(String idEtHakmilik) {
        this.idEtHakmilik = idEtHakmilik;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public String getJenisHakmilik() {
        return jenisHakmilik;
    }

    public void setJenisHakmilik(String jenisHakmilik) {
        this.jenisHakmilik = jenisHakmilik;
    }

    public String getNoHakmilik() {
        return noHakmilik;
    }

    public void setNoHakmilik(String noHakmilik) {
        this.noHakmilik = noHakmilik;
    }

    public String getKodLuas() {
        return kodLuas;
    }

    public void setKodLuas(String kodLuas) {
        this.kodLuas = kodLuas;
    }

    public Integer getLuas() {
        return luas;
    }

    public void setLuas(Integer luas) {
        this.luas = luas;
    }

    public String getBasimati() {
        return basimati;
    }

    public void setBasimati(String basimati) {
        this.basimati = basimati;
    }

    public String getBbsimati() {
        return bbsimati;
    }

    public void setBbsimati(String bbsimati) {
        this.bbsimati = bbsimati;
    }

    public String getKodPembahagian() {
        return kodPembahagian;
    }

    public void setKodPembahagian(String kodPembahagian) {
        this.kodPembahagian = kodPembahagian;
    }

    public BorangE[] getListEtappBorangE() {
        return listEtappBorangE;
    }

    public void setListEtappBorangE(BorangE[] listEtappBorangE) {
        this.listEtappBorangE = listEtappBorangE;
    }

    public BorangF[] getListEtappBorangF() {
        return listEtappBorangF;
    }

    public void setListEtappBorangF(BorangF[] listEtappBorangF) {
        this.listEtappBorangF = listEtappBorangF;
    }



    
}
