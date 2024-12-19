/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.pengambilan.validator;

import etanah.view.strata.validator.*;
import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.InfoAudit;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanPihak;
import etanah.model.PermohonanStrata;
import etanah.model.PermohonanTuntutanKos;
import etanah.model.strata.BadanPengurusan;
import etanah.service.PengambilanService1;
import etanah.service.StrataPtService;
import etanah.service.common.PermohonanPihakService;
import etanah.view.etanahActionBeanContext;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author Sreenivasa Reddy Munagala.
 */
public class JanaReportPerTuanTanahValidator implements StageListener {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    StrataPtService strService;
    @Inject
    PermohonanPihakService permohonanPihakService;
    @Inject
    PengambilanService1 pengambilanService1;
    private PermohonanStrata pemilik;
    private Pemohon pemohon;
    private BadanPengurusan mc;
    private static final Logger LOG = Logger.getLogger(JanaReportPerTuanTanahValidator.class);
    private String catatan;    
    private Permohonan permohonan;
    private ArrayList []tuanTanahAmaunList=new ArrayList[20];
    private ArrayList []sebenarAmaunList=new ArrayList[20];
    private List<HakmilikPihakBerkepentingan> senaraiPihakBerkepentingan;
    private BigDecimal jumCaraBayar1 = new BigDecimal(0.00);
    private BigDecimal amaunSebenarTotal = new BigDecimal(0.00);
    List<HakmilikPermohonan> senaraiHakmilikPermohonan = new ArrayList<HakmilikPermohonan>();


    @Override
    public boolean beforeStart(StageContext context) {
         System.out.println("--------------------beforeStart--------------------");
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void onGenerateReports(StageContext context) {
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {

        System.out.println("----beforeComplete-----"+context.getPermohonan());
        Permohonan permohonan = context.getPermohonan();
        permohonan = permohonanDAO.findById(permohonan.getIdPermohonan());

        String idSblm = permohonan.getPermohonanSebelum().getIdPermohonan();
        Permohonan idSblmPermohonan=new Permohonan();
        

        senaraiHakmilikPermohonan = new ArrayList<HakmilikPermohonan>();
        PermohonanPihak permohonanPihak1 = new PermohonanPihak();
        List<PermohonanTuntutanKos> permohonanTuntutanKosList1 = new ArrayList<PermohonanTuntutanKos>();

        if(idSblm!=null){
         idSblmPermohonan = permohonanDAO.findById(idSblm);
        senaraiHakmilikPermohonan=idSblmPermohonan.getSenaraiHakmilik();

        if (senaraiHakmilikPermohonan != null && senaraiHakmilikPermohonan.size() > 0) {
              for (int p = 0; p < senaraiHakmilikPermohonan.size(); p++) {

                  tuanTanahAmaunList[p]=new ArrayList<BigDecimal>();
                  sebenarAmaunList[p]=new ArrayList<BigDecimal>();

              Hakmilik hakmilik = senaraiHakmilikPermohonan.get(p).getHakmilik();

        senaraiPihakBerkepentingan = hakmilik.getSenaraiPihakBerkepentingan();
        BigDecimal tuanTanahAmaunTotal = BigDecimal.ZERO;

//         tuanTanahAmaunTotal = BigDecimal.ZERO;
//         amaunSebenarTuanTotal = BigDecimal.ZERO;

        if (senaraiPihakBerkepentingan != null && senaraiPihakBerkepentingan.size() > 0) {
            for (int i = 0; i < senaraiPihakBerkepentingan.size(); i++) {
              HakmilikPihakBerkepentingan  hakmilikPihakBerkepentingan = (HakmilikPihakBerkepentingan) senaraiPihakBerkepentingan.get(i);
                permohonanPihak1 = permohonanPihakService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idSblm,hakmilik.getIdHakmilik(),hakmilikPihakBerkepentingan.getPihak().getIdPihak());
                if (permohonanPihak1 != null) {
                    permohonanTuntutanKosList1 = pengambilanService1.findByIdPermohonanPihak2(permohonanPihak1.getIdPermohonanPihak());

                    jumCaraBayar1 = BigDecimal.ZERO;
                    amaunSebenarTotal = BigDecimal.ZERO;

                    for (Iterator<PermohonanTuntutanKos> ptk = permohonanTuntutanKosList1.iterator(); ptk.hasNext();) {
                         PermohonanTuntutanKos permohonanTuntutanKos = ptk.next();
                        if (permohonanTuntutanKos.getAmaunTuntutan() != null) {
                            jumCaraBayar1 = permohonanTuntutanKos.getAmaunTuntutan().add(jumCaraBayar1);
                        }
                        if (permohonanTuntutanKos.getAmaunSebenar() != null) {
                            amaunSebenarTotal = permohonanTuntutanKos.getAmaunSebenar().add(amaunSebenarTotal);
                        }
                    }
//                    tuanTanahAmaun.add(jumCaraBayar1);
//                    System.out.println("---------tuanTanahAmaun------"+tuanTanahAmaun);
//                    amaunSebenarTotalList.add(amaunSebenarTotal);
//                    tuanTanahAmaun.add(jumCaraBayar1);
//                    System.out.println("---------tuanTanahAmaun------"+tuanTanahAmaun);
//                    amaunSebenarTotalList.add(amaunSebenarTotal);
                    tuanTanahAmaunList[p].add(jumCaraBayar1);
                    sebenarAmaunList[p].add(amaunSebenarTotal);

//                    tuanTanahAmaunTotal = tuanTanahAmaunTotal.add(jumCaraBayar1);
//                    amaunSebenarTuanTotal = amaunSebenarTuanTotal.add(amaunSebenarTotal);
                }//if
            }
        }
//        jumCaraBayar1 = tuanTanahAmaunTotal;

//     hakmilikAmaunList.add(tuanTanahAmaunTotal);
//     hakmilikSebenarList.add(amaunSebenarTuanTotal);

//     System.out.println("---------hakmilikAmaunList--------"+hakmilikAmaunList);
//     System.out.println("---------hakmilikSebenarList--------"+hakmilikSebenarList);

       }//for
     }
    }//if

        //reporting
//                String dokumenPath = conf.getProperty("document.path");
                String params = "p_id_mohon";
                String values = idSblm.trim();
                String gen ="STR_Borang4K.rdf";
                //gen2 = reportName.getDHKEReportName();                
//                kd2.setKod("DHKE");
//                e = saveOrUpdateDokumen(fd, kd2, hakmilikPermohonan.getHakmilik().getIdHakmilik(), context);
//                path2 = idSblmPermohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(e.getIdDokumen());
//                logger.info("::Path To save :: " + path2);
//                logger.info("::Report Name ::" + gen2);
//                syslog.info(peng.getIdPengguna() + " generate report " + kd2.getKod() + " for :" + hakmilikPermohonan.getHakmilik().getIdHakmilik() + " and saving it to:" + path2);
//                reportUtil.generateReport(gen, params, values, dokumenPath + path2, peng);






         return proposedOutcome;
   }


    @Override
    public void afterComplete(StageContext context) {
        System.out.println("--------------------afterComplete--------------------");
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean beforeGenerateReports(StageContext ctx) {
        System.out.println("--------------------beforeGenerateReports--------------------");
        return true;
    }

    public Pemohon getPemohon() {
        return pemohon;
    }

    public void setPemohon(Pemohon pemohon) {
        this.pemohon = pemohon;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public ArrayList[] getSebenarAmaunList() {
        return sebenarAmaunList;
    }

    public void setSebenarAmaunList(ArrayList[] sebenarAmaunList) {
        this.sebenarAmaunList = sebenarAmaunList;
    }

    public List<HakmilikPihakBerkepentingan> getSenaraiPihakBerkepentingan() {
        return senaraiPihakBerkepentingan;
    }

    public void setSenaraiPihakBerkepentingan(List<HakmilikPihakBerkepentingan> senaraiPihakBerkepentingan) {
        this.senaraiPihakBerkepentingan = senaraiPihakBerkepentingan;
    }

    public ArrayList[] getTuanTanahAmaunList() {
        return tuanTanahAmaunList;
    }

    public void setTuanTanahAmaunList(ArrayList[] tuanTanahAmaunList) {
        this.tuanTanahAmaunList = tuanTanahAmaunList;
    }

    public List<HakmilikPermohonan> getSenaraiHakmilikPermohonan() {
        return senaraiHakmilikPermohonan;
    }

    public void setSenaraiHakmilikPermohonan(List<HakmilikPermohonan> senaraiHakmilikPermohonan) {
        this.senaraiHakmilikPermohonan = senaraiHakmilikPermohonan;
    }

     @Override
    public void afterPushBack(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String beforePushBack(StageContext context, String proposedOutcome) {
        return proposedOutcome;
    }

 

}


