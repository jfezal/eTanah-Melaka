/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pelupusan.validator;

import com.google.inject.Inject;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodBandarPekanMukimDAO;
import etanah.dao.KodLotDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.NoPt;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.service.PelupusanService;
import etanah.util.StringUtils;
import etanah.view.etanahActionBeanContext;
import etanah.view.stripes.pelupusan.disClass.DisLaporanTanahService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import java.util.List;

/**
 *
 * @author afham
 */
public class NoPTSementaraValidator implements StageListener {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    DisLaporanTanahService disLaporanTanahService;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    PelupusanService pelupusanService;
    @Inject
    KodBandarPekanMukimDAO kodBandarPekanMukimDAO;
    @Inject
    KodLotDAO kodLotDAO;

    private NoPt noPT;
    private List<HakmilikPermohonan> hakmilikPermohonanList;

    @Override
    public boolean beforeStart(StageContext sc) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean beforeGenerateReports(StageContext sc) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void onGenerateReports(StageContext sc) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {
        hakmilikPermohonanList = context.getPermohonan().getSenaraiHakmilik();
        Permohonan permohonan = context.getPermohonan();
        Pengguna peng = context.getPengguna();
        InfoAudit info = new InfoAudit();
        info.setDimasukOleh(peng);
        info.setTarikhMasuk(new java.util.Date());
        boolean check = false;
        if (hakmilikPermohonanList.size() > 0) {

            int index = 0;
            //Checking
            for (HakmilikPermohonan hp : hakmilikPermohonanList) {
                noPT = pelupusanService.findNoPtByIdHakmilikPermohonan(hp.getId());
                if (noPT != null) {

                } else {
                    if (permohonan.getKodUrusan().getKod().equals("PPTR")) {
                        //Jika lulus sebahagian buat no pt sementara
                        if (hp.getStatusLuasDiluluskan() != null && hp.getStatusLuasDiluluskan().equals("S")) {
                            noPT = new NoPt();
                            index++;
                            noPT.setInfoAudit(info);
                            if (hp.getHakmilik() != null) {
                                noPT.setKodBpm(kodBandarPekanMukimDAO.findById(hp.getHakmilik().getBandarPekanMukim().getKod()));
                            } else {
                                noPT.setKodBpm(kodBandarPekanMukimDAO.findById(hp.getBandarPekanMukimBaru().getKod()));
                            }
                            noPT.setHakmilikPermohonan(hp);
                            Long maxNoPt = new Long(0);
                            Long maxNoPtSementara = new Long(0);
                            if (hp.getHakmilik() != null) {
                                maxNoPt = pelupusanService.getMaxOfNoPTNew(hp.getHakmilik().getBandarPekanMukim().getKod());
                                maxNoPtSementara = pelupusanService.getMaxOfNoPTSementaraNew(hp.getHakmilik().getBandarPekanMukim().getKod());
                            } else {
                                maxNoPt = pelupusanService.getMaxOfNoPTNew(hp.getBandarPekanMukimBaru().getKod());
                                maxNoPtSementara = pelupusanService.getMaxOfNoPTSementaraNew(hp.getBandarPekanMukimBaru().getKod());
                            }
                            
                            if(maxNoPt == null){
                                maxNoPt = new Long(0);
                            }
                            if(maxNoPtSementara == null){
                                maxNoPtSementara = new Long(0);
                            }
                            int no1 = 0;
                            String noPtSementara = null;
                            if (!maxNoPtSementara.toString().equals("0")) {
                                if (!maxNoPt.toString().equals("0")) {
                                    if (maxNoPt > maxNoPtSementara) {
                                        no1 = Integer.parseInt(maxNoPt.toString()) + 1;
                                        noPtSementara = no1 + "";
                                        noPT.setNoPtSementara(Long.parseLong(noPtSementara));
                                    } else {
                                        no1 = Integer.parseInt(maxNoPtSementara.toString()) + 1;
                                        noPtSementara = no1 + "";
                                        noPT.setNoPtSementara(Long.parseLong(noPtSementara));
                                    }
                                } else {
                                    no1 = Integer.parseInt(maxNoPtSementara.toString()) + 1;
                                    noPtSementara = no1 + "";
                                    noPT.setNoPtSementara(Long.parseLong(noPtSementara));
                                }
                            } else {
                                if (!maxNoPt.toString().equals("0")) {
                                    no1 = Integer.parseInt(maxNoPt.toString()) + 1;
                                    noPtSementara = no1 + "";
                                    noPT.setNoPtSementara(Long.parseLong(noPtSementara));
                                } else { //For the first data
                                    no1 = 1;
                                    noPtSementara = no1 + "";
                                    noPT.setNoPtSementara(Long.parseLong(noPtSementara));
                                }
                            }
                            pelupusanService.simpanNoPt(noPT);
                            proposedOutcome = "L";
                        }else{
                            proposedOutcome = "T";
                        }
                        
                    } else {
                        noPT = new NoPt();
                        index++;
                        noPT.setInfoAudit(info);
                        if (hp.getHakmilik() != null) {
                            noPT.setKodBpm(kodBandarPekanMukimDAO.findById(hp.getHakmilik().getBandarPekanMukim().getKod()));
                        } else {
                            noPT.setKodBpm(kodBandarPekanMukimDAO.findById(hp.getBandarPekanMukimBaru().getKod()));
                        }
                        noPT.setHakmilikPermohonan(hp);
                        Long maxNoPt = new Long(0);
                        Long maxNoPtSementara = new Long(0);
                        maxNoPt = pelupusanService.getMaxOfNoPTNew(hp.getBandarPekanMukimBaru().getKod());
                        maxNoPtSementara = pelupusanService.getMaxOfNoPTSementaraNew(hp.getBandarPekanMukimBaru().getKod());
                        int no1 = 0;
                        String noPtSementara = null;
                        if (!maxNoPtSementara.toString().equals("0")) {
                            if (!maxNoPt.toString().equals("0")) {
                                if (maxNoPt > maxNoPtSementara) {
                                    no1 = Integer.parseInt(maxNoPt.toString()) + 1;
                                    noPtSementara = no1 + "";
                                    noPT.setNoPtSementara(Long.parseLong(noPtSementara));
                                } else {
                                    no1 = Integer.parseInt(maxNoPtSementara.toString()) + 1;
                                    noPtSementara = no1 + "";
                                    noPT.setNoPtSementara(Long.parseLong(noPtSementara));
                                }
                            } else {
                                no1 = Integer.parseInt(maxNoPtSementara.toString()) + 1;
                                noPtSementara = no1 + "";
                                noPT.setNoPtSementara(Long.parseLong(noPtSementara));
                            }
                        } else {
                            if (!maxNoPt.toString().equals("0")) {
                                no1 = Integer.parseInt(maxNoPt.toString()) + 1;
                                noPtSementara = no1 + "";
                                noPT.setNoPtSementara(Long.parseLong(noPtSementara));
                            } else { //For the first data
                                no1 = 1;
                                noPtSementara = no1 + "";
                                noPT.setNoPtSementara(Long.parseLong(noPtSementara));
                            }
                        }
                        pelupusanService.simpanNoPt(noPT);
                    }
                }
            }
        }
        return proposedOutcome;
    }

    @Override
    public void afterComplete(StageContext sc) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String beforePushBack(StageContext sc, String string) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void afterPushBack(StageContext sc) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
