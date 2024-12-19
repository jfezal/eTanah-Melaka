/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata;

import java.math.BigDecimal;

/**
 *
 * @author faidzal
 */
public class SenaraiPetakBaruList {

    String idPetak;
    String idHakmilik;
    BigDecimal luasPetak;
    int unitSyer = 0;

    public String getIdPetak() {
        return idPetak;
    }

    public void setIdPetak(String idPetak) {
        this.idPetak = idPetak;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public BigDecimal getLuasPetak() {
        return luasPetak;
    }

    public void setLuasPetak(BigDecimal luasPetak) {
        this.luasPetak = luasPetak;
    }

    public int getUnitSyer() {
        return unitSyer;
    }

    public void setUnitSyer(int unitSyer) {
        this.unitSyer = unitSyer;
    }
}
