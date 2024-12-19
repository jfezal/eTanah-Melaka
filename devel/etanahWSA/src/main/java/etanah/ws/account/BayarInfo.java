/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.ws.account;

/**
 *
 * @author Izam
 */
public class BayarInfo {
    String kodCaraBayar;
    long amaun;
    String noRuj;
    String kodBank;
    String bankCaw;
    String tarikhCek;

    public long getAmaun() {
        return amaun;
    }

    public void setAmaun(long amaun) {
        this.amaun = amaun;
    }

    public String getBankCaw() {
        return bankCaw;
    }

    public void setBankCaw(String bankCaw) {
        this.bankCaw = bankCaw;
    }

    public String getKodBank() {
        return kodBank;
    }

    public void setKodBank(String kodBank) {
        this.kodBank = kodBank;
    }

    public String getKodCaraBayar() {
        return kodCaraBayar;
    }

    public void setKodCaraBayar(String kodCaraBayar) {
        this.kodCaraBayar = kodCaraBayar;
    }

    public String getNoRuj() {
        return noRuj;
    }

    public void setNoRuj(String noRuj) {
        this.noRuj = noRuj;
    }

    public String getTarikhCek() {
        return tarikhCek;
    }

    public void setTarikhCek(String tarikhCek) {
        this.tarikhCek = tarikhCek;
    }
}
