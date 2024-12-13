package etanah.view.stripes.penguatkuasaan.disClass;

import etanah.view.stripes.pelupusan.disClass.*;
import etanah.model.KodLot;
import etanah.model.LaporanTanahSempadan;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Shazwan 23022012 1255 PM
 * purpose : Seperate Perihal Lot-Lot Bersempadan
 * 
 */

public class DisLaporanTanahSempadan  {
    
    private List listLaporTanahSpdnU = new ArrayList();
    private List listLaporTanahSpdnS = new ArrayList();
    private List listLaporTanahSpdnB = new ArrayList();
    private List listLaporTanahSpdnT = new ArrayList();
    private int uSize;
    private int sSize;
    private int bSize;
    private int tSize;
    private LaporanTanahSempadan laporanTanahSempadan; 
    private String hakmilik_ref;
    private String jenisSmpdn;
    private String idHakmilikSmpdn;
    private String noLot;
    private String kodLot;
    private KodLot kodLotUOM;
    private String kegunaanSmpdn;
    private String keadaanTanah;
    private String catatan;
    private String milikKerajaanSmpdn;
    private String statusSempadan;
    private String jarakDariTanahUOM;
    private String jarakDariTanah;
        
    public int getbSize() {
        return bSize;
    }

    public void setbSize(int bSize) {
        this.bSize = bSize;
    }

    public int getsSize() {
        return sSize;
    }

    public void setsSize(int sSize) {
        this.sSize = sSize;
    }

    public int gettSize() {
        return tSize;
    }

    public void settSize(int tSize) {
        this.tSize = tSize;
    }

    public int getuSize() {
        return uSize;
    }

    public void setuSize(int uSize) {
        this.uSize = uSize;
    }  
    
    public List getListLaporTanahSpdnB() {
        return listLaporTanahSpdnB;
    }

    public void setListLaporTanahSpdnB(List listLaporTanahSpdnB) {
        this.listLaporTanahSpdnB = listLaporTanahSpdnB;
    }

    public List getListLaporTanahSpdnS() {
        return listLaporTanahSpdnS;
    }

    public void setListLaporTanahSpdnS(List listLaporTanahSpdnS) {
        this.listLaporTanahSpdnS = listLaporTanahSpdnS;
    }

    public List getListLaporTanahSpdnT() {
        return listLaporTanahSpdnT;
    }

    public void setListLaporTanahSpdnT(List listLaporTanahSpdnT) {
        this.listLaporTanahSpdnT = listLaporTanahSpdnT;
    }

    public List getListLaporTanahSpdnU() {
        return listLaporTanahSpdnU;
    }

    public void setListLaporTanahSpdnU(List listLaporTanahSpdnU) {
        this.listLaporTanahSpdnU = listLaporTanahSpdnU;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public String getHakmilik_ref() {
        return hakmilik_ref;
    }

    public void setHakmilik_ref(String hakmilik_ref) {
        this.hakmilik_ref = hakmilik_ref;
    }

    public String getIdHakmilikSmpdn() {
        return idHakmilikSmpdn;
    }

    public void setIdHakmilikSmpdn(String idHakmilikSmpdn) {
        this.idHakmilikSmpdn = idHakmilikSmpdn;
    }

    public String getJenisSmpdn() {
        return jenisSmpdn;
    }

    public void setJenisSmpdn(String jenisSmpdn) {
        this.jenisSmpdn = jenisSmpdn;
    }

    public String getKeadaanTanah() {
        return keadaanTanah;
    }

    public void setKeadaanTanah(String keadaanTanah) {
        this.keadaanTanah = keadaanTanah;
    }

    public String getKegunaanSmpdn() {
        return kegunaanSmpdn;
    }

    public void setKegunaanSmpdn(String kegunaanSmpdn) {
        this.kegunaanSmpdn = kegunaanSmpdn;
    }

    public LaporanTanahSempadan getLaporanTanahSempadan() {
        return laporanTanahSempadan;
    }

    public void setLaporanTanahSempadan(LaporanTanahSempadan laporanTanahSempadan) {
        this.laporanTanahSempadan = laporanTanahSempadan;
    }

    public String getMilikKerajaanSmpdn() {
        return milikKerajaanSmpdn;
    }

    public void setMilikKerajaanSmpdn(String milikKerajaanSmpdn) {
        this.milikKerajaanSmpdn = milikKerajaanSmpdn;
    }

    public String getStatusSempadan() {
        return statusSempadan;
    }

    public void setStatusSempadan(String statusSempadan) {
        this.statusSempadan = statusSempadan;
    }

    public String getJarakDariTanah() {
        return jarakDariTanah;
    }

    public void setJarakDariTanah(String jarakDariTanah) {
        this.jarakDariTanah = jarakDariTanah;
    }

    public String getJarakDariTanahUOM() {
        return jarakDariTanahUOM;
    }

    public void setJarakDariTanahUOM(String jarakDariTanahUOM) {
        this.jarakDariTanahUOM = jarakDariTanahUOM;
    }

    public String getKodLot() {
        return kodLot;
    }

    public void setKodLot(String kodLot) {
        this.kodLot = kodLot;
    }

    public String getNoLot() {
        return noLot;
    }

    public void setNoLot(String noLot) {
        this.noLot = noLot;
    }

    public KodLot getKodLotUOM() {
        return kodLotUOM;
    }

    public void setKodLotUOM(KodLot kodLotUOM) {
        this.kodLotUOM = kodLotUOM;
    }
    
    
}
