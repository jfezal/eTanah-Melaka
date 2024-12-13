package etanah.view.stripes.pelupusan.disClass;

import etanah.model.KodUOM;
import etanah.model.PermohonanBahanBatuan;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Shazwan 22022012 1112 AM
 * purpose : To Control laporan Tanah Kemaskini Icon
 * 
 */
public class DisLaporanTanahController {

    private boolean pTanah = Boolean.TRUE;
    private boolean bSempadan = Boolean.TRUE;
    private boolean kTanah = Boolean.TRUE;
    private boolean lotSempadan = Boolean.TRUE;
    private boolean dKawasan = Boolean.TRUE;
    private boolean syorPPT = Boolean.TRUE;
    private boolean syorPPTKanan = Boolean.FALSE;
    private boolean bBahanBatuan = Boolean.FALSE;
    
    public DisLaporanTanahController viewOnlyLaporanTanah(){
        DisLaporanTanahController disLTControl = new DisLaporanTanahController();
        disLTControl.setpTanah(Boolean.FALSE);
        disLTControl.setbSempadan(Boolean.FALSE);
        disLTControl.setkTanah(Boolean.FALSE);
        disLTControl.setLotSempadan(Boolean.FALSE);
        disLTControl.setdKawasan(Boolean.FALSE);
        disLTControl.setSyorPPT(Boolean.FALSE);
        disLTControl.setSyorPPTKanan(Boolean.FALSE);
        disLTControl.setSyorPPTKanan(Boolean.FALSE);
        disLTControl.setbBahanBatuan(Boolean.FALSE);
        return disLTControl;
    }
    
    public DisLaporanTanahController viewOnlyLaporanTanahPPT(){
        DisLaporanTanahController disLTControl = new DisLaporanTanahController();
        disLTControl.setpTanah(Boolean.TRUE);
        disLTControl.setbSempadan(Boolean.TRUE);
        disLTControl.setkTanah(Boolean.TRUE);
        disLTControl.setLotSempadan(Boolean.TRUE);
        disLTControl.setdKawasan(Boolean.TRUE);
        disLTControl.setSyorPPT(Boolean.TRUE);
        disLTControl.setSyorPPTKanan(Boolean.FALSE);
        disLTControl.setbBahanBatuan(Boolean.TRUE);
        return disLTControl;
    }
    
    public DisLaporanTanahController viewOnlyLaporanTanahPPTKanan(){
        DisLaporanTanahController disLTControl = new DisLaporanTanahController();
        disLTControl.setpTanah(Boolean.FALSE);
        disLTControl.setbSempadan(Boolean.FALSE);
        disLTControl.setkTanah(Boolean.FALSE);
        disLTControl.setLotSempadan(Boolean.FALSE);
        disLTControl.setdKawasan(Boolean.FALSE);
        disLTControl.setSyorPPT(Boolean.FALSE);
        disLTControl.setSyorPPTKanan(Boolean.TRUE);
        disLTControl.setbBahanBatuan(Boolean.FALSE);
        return disLTControl;
    }
    
    public boolean isbSempadan() {
        return bSempadan;
    }

    public void setbSempadan(boolean bSempadan) {
        this.bSempadan = bSempadan;
    }

    public boolean isdKawasan() {
        return dKawasan;
    }

    public void setdKawasan(boolean dKawasan) {
        this.dKawasan = dKawasan;
    }

    public boolean iskTanah() {
        return kTanah;
    }

    public void setkTanah(boolean kTanah) {
        this.kTanah = kTanah;
    }

    public boolean isLotSempadan() {
        return lotSempadan;
    }

    public void setLotSempadan(boolean lotSempadan) {
        this.lotSempadan = lotSempadan;
    }

    public boolean ispTanah() {
        return pTanah;
    }

    public void setpTanah(boolean pTanah) {
        this.pTanah = pTanah;
    }

    public boolean isSyorPPT() {
        return syorPPT;
    }

    public void setSyorPPT(boolean syorPPT) {
        this.syorPPT = syorPPT;
    }

    public boolean isSyorPPTKanan() {
        return syorPPTKanan;
    }

    public void setSyorPPTKanan(boolean syorPPTKanan) {
        this.syorPPTKanan = syorPPTKanan;
    }

    public boolean isbBahanBatuan() {
        return bBahanBatuan;
    }

    public void setbBahanBatuan(boolean bBahanBatuan) {
        this.bBahanBatuan = bBahanBatuan;
    }
}
