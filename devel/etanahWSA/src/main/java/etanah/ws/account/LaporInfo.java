/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.ws.account;

/**
 *
 * @author Izam
 */
public class LaporInfo {
    String idKewDok;
    String idTrans;
    String idKdb;
    long amaun;

    public long getAmaun() {
        return amaun;
    }

    public void setAmaun(long amaun) {
        this.amaun = amaun;
    }

    public String getIdKdb() {
        return idKdb;
    }

    public void setIdKdb(String idKdb) {
        this.idKdb = idKdb;
    }

    public String getIdKewDok() {
        return idKewDok;
    }

    public void setIdKewDok(String idKewDok) {
        this.idKewDok = idKewDok;
    }

    public String getIdTrans() {
        return idTrans;
    }

    public void setIdTrans(String idTrans) {
        this.idTrans = idTrans;
    }
}
