/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pembangunan.disClass;

/**
 *
 * @author afham
 */
public class DisKertasMMKController {
    
    private boolean kTajuk = Boolean.TRUE;
    private boolean kTujuan = Boolean.TRUE;
    private boolean kLatarBelakang = Boolean.TRUE ;
    private boolean kPerakuanPTD = Boolean.TRUE ;
    private boolean kPerakuanPTG = Boolean.TRUE ;
    private boolean vPTG = Boolean.TRUE ;
    
    public DisKertasMMKController editKertasMMKPT(){
        DisKertasMMKController disKC = new DisKertasMMKController();
        disKC.setkTajuk(Boolean.TRUE);
        disKC.setkTujuan(Boolean.TRUE);
        disKC.setkLatarBelakang(Boolean.TRUE);
        disKC.setkPerakuanPTD(Boolean.TRUE);
        disKC.setvPTG(Boolean.FALSE);
        disKC.setkPerakuanPTG(Boolean.FALSE);
        return disKC ;
    }
    
    public DisKertasMMKController editKertasMMKPTD(){
        DisKertasMMKController disKC = new DisKertasMMKController();
        disKC.setkTajuk(Boolean.FALSE);
        disKC.setkTujuan(Boolean.FALSE);
        disKC.setkLatarBelakang(Boolean.FALSE);
        disKC.setkPerakuanPTD(Boolean.TRUE);
        disKC.setvPTG(Boolean.FALSE);
        disKC.setkPerakuanPTG(Boolean.FALSE);
        return disKC ;
    }
    
    public DisKertasMMKController viewKertasMMKPTD(){
        DisKertasMMKController disKC = new DisKertasMMKController();
        disKC.setkTajuk(Boolean.FALSE);
        disKC.setkTujuan(Boolean.FALSE);
        disKC.setkLatarBelakang(Boolean.FALSE);
        disKC.setkPerakuanPTD(Boolean.FALSE);
        disKC.setvPTG(Boolean.FALSE);
        disKC.setkPerakuanPTG(Boolean.FALSE);
        return disKC ;
    }
    
    public DisKertasMMKController editKertasMMKPTG(){
        DisKertasMMKController disKC = new DisKertasMMKController();
        disKC.setkTajuk(Boolean.FALSE);
        disKC.setkTujuan(Boolean.FALSE);
        disKC.setkLatarBelakang(Boolean.FALSE);
        disKC.setkPerakuanPTD(Boolean.FALSE);
        disKC.setvPTG(Boolean.TRUE);
        disKC.setkPerakuanPTG(Boolean.TRUE);
        return disKC ;
    }
    
    public DisKertasMMKController viewKertasMMKPTG(){
        DisKertasMMKController disKC = new DisKertasMMKController();
        disKC.setkTajuk(Boolean.FALSE);
        disKC.setkTujuan(Boolean.FALSE);
        disKC.setkLatarBelakang(Boolean.FALSE);
        disKC.setkPerakuanPTD(Boolean.FALSE);
        disKC.setvPTG(Boolean.TRUE);
        disKC.setkPerakuanPTG(Boolean.FALSE);
        return disKC ;
    }

    public boolean iskTajuk() {
        return kTajuk;
    }

    public void setkTajuk(boolean kTajuk) {
        this.kTajuk = kTajuk;
    }

    public boolean iskTujuan() {
        return kTujuan;
    }

    public void setkTujuan(boolean kTujuan) {
        this.kTujuan = kTujuan;
    }

    public boolean iskLatarBelakang() {
        return kLatarBelakang;
    }

    public void setkLatarBelakang(boolean kLatarBelakang) {
        this.kLatarBelakang = kLatarBelakang;
    }

    public boolean iskPerakuanPTD() {
        return kPerakuanPTD;
    }

    public void setkPerakuanPTD(boolean kPerakuanPTD) {
        this.kPerakuanPTD = kPerakuanPTD;
    }

    public boolean iskPerakuanPTG() {
        return kPerakuanPTG;
    }

    public void setkPerakuanPTG(boolean kPerakuanPTG) {
        this.kPerakuanPTG = kPerakuanPTG;
    }

    public boolean isvPTG() {
        return vPTG;
    }

    public void setvPTG(boolean vPTG) {
        this.vPTG = vPTG;
    }
    
    
}