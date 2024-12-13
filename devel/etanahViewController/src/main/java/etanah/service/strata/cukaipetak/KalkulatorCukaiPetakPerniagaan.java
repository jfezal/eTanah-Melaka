/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.service.strata.cukaipetak;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 *
 * @author john
 */
public enum KalkulatorCukaiPetakPerniagaan {
    BANDAR1,BANDAR2, PEKANDESA1, DESA3;

    BigDecimal calculate(double luas, double luasKeseluruhan, double luasTanah) {
        BigDecimal a = new BigDecimal(BigInteger.ZERO);
        switch (this) {
            case BANDAR1:
                a = calc(luas, luasKeseluruhan,luasTanah, 2.16);
                return a;
            case BANDAR2:
                a = calc(luas, luasKeseluruhan,luasTanah, 1.75);
                return a;
            case PEKANDESA1:
                a = calc(luas, luasKeseluruhan,luasTanah, 1.35);
                return a;
            case DESA3:
                a = calc(luas, luasKeseluruhan,luasTanah, 0.72);
                return a;
            default:
                throw new AssertionError("Unknown operations " + this);
        }
    }

    private BigDecimal calc(double luas, double luasKeseluruhan,double luasTanah, double f) {
        BigDecimal a = new BigDecimal(luasTanah*(f));
        BigDecimal ab = new BigDecimal((luas / luasKeseluruhan));
        BigDecimal sum = new BigDecimal(Math.ceil(Double.parseDouble(a.multiply(ab).toString())));
        
        return sum;
    }
}
