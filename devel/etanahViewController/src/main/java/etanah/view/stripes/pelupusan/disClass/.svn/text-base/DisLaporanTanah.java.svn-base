/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pelupusan.disClass;

import etanah.model.LaporanTanah;
import etanah.model.LaporanTanahSempadan;
import etanah.model.Permohonan;
import java.util.List;

/**
 *
 * @author afham
 */
public class DisLaporanTanah {
    
    private LaporanTanah laporanTanah ;
    
    public LaporanTanah copyPropertiesLaporanTanah(Permohonan mohon, LaporanTanah laporanTanahNew, LaporanTanah laporanTanahOld, DisLaporanTanahService disLaporanTanahService){
        
        if(laporanTanahOld != null){
            laporanTanahNew.setKeadaanTanah(laporanTanahOld.getKeadaanTanah() != null ? laporanTanahOld.getKeadaanTanah() : new String());
            laporanTanahNew.setKecerunanTanah(laporanTanahOld.getKecerunanTanah() != null ? laporanTanahOld.getKecerunanTanah() : null);
        }
        return laporanTanahNew ;
        
    }
    
    
    public List<LaporanTanahSempadan> copyPropertiesLaporanTanahSempadan(Permohonan mohon, List<LaporanTanahSempadan> laporanTanahSempadanList, LaporanTanah laporanTanahOld, DisLaporanTanahService disLaporanTanahService){
        
        if(laporanTanahOld != null){
            
        }
        return laporanTanahSempadanList ;
        
    }
}
