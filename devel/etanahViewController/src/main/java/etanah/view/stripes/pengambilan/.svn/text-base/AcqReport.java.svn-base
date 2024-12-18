/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pengambilan;

/**
 *
 * @author nordiyana
 */
import com.google.inject.Inject;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.service.PelupusanService;
import etanah.util.StringUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;




public class AcqReport {
    @Inject
    PelupusanService pservice;
    private HashMap reportMap;

    public HashMap getReportMap(int numUrusan, String stageID, String negeri, Permohonan p) {
        reportMap = new HashMap();
        //Pengguna pguna = new Pengguna();
        int numnegeri = negeri.equalsIgnoreCase("04") ? 1
                : negeri.equalsIgnoreCase("05") ? 2
                : 0;
        switch (numUrusan) {
            case 1:
                //PB
                reportMap = getReportByCasePB(stageID, numnegeri, p);
                break;
            
        }
        return reportMap;
    }

 

  

    

    public HashMap getReportByCasePB(String stageID, int numnegeri, Permohonan p) {
        /*
         * NOTE: reportBil = numOfReportsCalled reportName{intCount} =
         * reportName reportKod{intCount} = reportKodDokumen
         */

        reportMap = new HashMap();

        switch (numnegeri) {
            case 1: //MELAKA
                
                break;
            case 2:
                //NEGERI SEMBILAN
                if (stageID.contentEquals("01DrafPenarikanBalik")) {
                    if (p.getKeputusan().getKod().equalsIgnoreCase("8B")) {
                        reportMap.put("reportName1", "ACQSrtPenarikan_NS.rdf");
                        reportMap.put("reportKod1", "8TOL");
                    }
                }
                break;

        }
        return reportMap;
    }

 
    
}
