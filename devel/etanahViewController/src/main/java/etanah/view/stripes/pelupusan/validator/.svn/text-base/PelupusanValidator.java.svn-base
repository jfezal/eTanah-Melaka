
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.pelupusan.validator;

import com.google.inject.Inject;
import etanah.model.*;
//import etanah.dao.*;
import etanah.service.*;
import etanah.workflow.*;
import org.apache.log4j.Logger;

/*
 * @author  : Hayyan
 * @txn     : Disposal FAT
 * @created : 20131218
 * @update  : 20131223
 */

public class PelupusanValidator implements StageListener {

    @Inject
    etanah.Configuration cfg;
    @Inject
    BPelService bpel;
    @Inject
    PelupusanService ps;
    @Inject
    NotifikasiService notis;
    @Inject
    LaporanPelukisPelanService lpps;
    
    private static final Logger l = Logger.getLogger(PelupusanValidator.class);

    @Override
    public boolean beforeStart(StageContext context) {
//        throw new UnsupportedOperationException("beforeStart not yet supported.");
        return true;
    }

    @Override
    public boolean beforeGenerateReports(StageContext context) {
        return true;
    }

    @Override
    public void onGenerateReports(StageContext context) {
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {
        l.debug(">> beforeComplete() <<");

        String state = cfg.getProperty("kodNegeri");
        String stage = context.getStageName();
        Permohonan permohonan = context.getPermohonan();
        String urusan = permohonan.getKodUrusan().getKod();

        int negeri = state.equals("04") ? 1
                : state.equals("05") ? 2
                : 0;

        int kod = urusan.equals("PBMT") ? 1
                : urusan.equals("PLPS") ? 2
                : urusan.equals("PLPTD") ? 3
                : urusan.equals("RLPS") ? 4
                : urusan.equals("PPBB") ? 5
                : urusan.equals("PBPTG") ? 6
                : urusan.equals("PBMMK") ? 7
                : urusan.equals("PPRU") ? 8
                : urusan.equals("LPSP") ? 9
                : urusan.equals("PRIZ") ? 10
                : urusan.equals("PRMMK") ? 11
                : urusan.equals("PBRZ") ? 12
                : urusan.equals("PCRG") ? 13
                : urusan.equals("MPCRG") ? 14
                : urusan.equals("PJLB") ? 15
                : urusan.equals("MPJLB") ? 16
                : urusan.equals("RAYL") ? 17
                : urusan.equals("RLPTG") ? 18
                : urusan.equals("RAYK") ? 19
                : urusan.equals("PPTR") ? 20
                : urusan.equals("PWGSA") ? 21
                : urusan.equals("PBGSA") ? 22
                : urusan.equals("PTGSA") ? 23
                : urusan.equals("BMBT") ? 24
                : urusan.equals("PTBTC") ? 25
                : urusan.equals("PTBTS") ? 26
                : urusan.equals("WMRE") ? 27
                : urusan.equals("MMRE") ? 28
                : urusan.equals("JMRE") ? 29
                : urusan.equals("BMRE") ? 30
                : urusan.equals("PJTK") ? 31
                : urusan.equals("PTPBP") ? 32
                : urusan.equals("PTMTA") ? 33
                : 0;

        callDefault(negeri);

        switch (kod) {
            case 1:
                //PBMT
                switch (negeri) {
                    case 1:
                        //MELAKA
                        //TODO
                        break;
                    case 2:
                        //NEGERI SEMBILAN
                        //TODO
                        break;
                }
            case 2:
                //PLPS
                switch (negeri) {
                    case 1:
                        //MELAKA
                        //TODO
                        break;
                    case 2:
                        //NEGERI SEMBILAN
                        //TODO
                        break;
                }
            case 3:
                //PLPTD
                switch (negeri) {
                    case 1:
                        //MELAKA
                        //TODO
                        break;
                    case 2:
                        //NEGERI SEMBILAN
                        //TODO
                        break;
                }
            case 4:
                //RLPS
                switch (negeri) {
                    case 1:
                        //MELAKA
                        //TODO
                        break;
                    case 2:
                        //NEGERI SEMBILAN
                        //TODO
                        break;
                }
            case 5:
                //PPBB
                switch (negeri) {
                    case 1:
                        //MELAKA
                        //TODO
                        break;
                    case 2:
                        //NEGERI SEMBILAN
                        //TODO
                        break;
                }
            case 6:
                //PBPTG
                switch (negeri) {
                    case 1:
                        //MELAKA
                        //TODO
                        break;
                    case 2:
                        //NEGERI SEMBILAN
                        //TODO
                        break;
                }
            case 7:
                //PBMMK
                switch (negeri) {
                    case 1:
                        //MELAKA
                        //TODO
                        break;
                    case 2:
                        //NEGERI SEMBILAN
                        //TODO
                        break;
                }
            case 8:
                //PPRU
                l.debug(">> case 8: PPRU <<");

                switch (negeri) {
                    case 1:
                        //MELAKA
                        //TODO
                        break;
                    case 2:
                        //NEGERI SEMBILAN
                        l.debug(">> case 2: N9 <<");
                        
                        HakmilikPermohonan hp = ps.findHakmilikPermohonan(permohonan.getIdPermohonan());
                        Pemohon pemohon = ps.findPemohon(permohonan.getIdPermohonan());
                        PermohonanLaporanPelan plp = lpps.findMohonLaporPelanByidMohon(permohonan.getIdPermohonan());
                        PermohonanPermitItem ppi = ps.findByIdMohonPermitItem(permohonan.getIdPermohonan());
                        
                        if (stage.equals("01Kemasukan")) {
                            l.debug(">> Stage : " +stage+ " <<");                            
                            // Validate Tujuan
                            if (permohonan != null) {
                                l.debug(">> permohonan != null <<");
                                if (permohonan.getSebab() == null ) {
                                    context.addMessage(permohonan.getIdPermohonan() + " - Sila lengkapkan Maklumat Permohonan di tab Tanah terlebih dahulu.");
                                    return null;
                                } 
                            }                             
                            // Validate Tanah
                            if (hp != null) {
                                l.debug(">> hp != null <<");
                                if (hp.getBandarPekanMukimBaru() == null ) {
                                    context.addMessage(permohonan.getIdPermohonan() + " - Sila lengkapkan Maklumat Tanah Yang Terlibat di tab Tanah terlebih dahulu.");
                                    return null;
                                } 
                            } else { 
                                context.addMessage(permohonan.getIdPermohonan() + " - Sila masukkan Maklumat Tanah Yang Terlibat di tab Tanah terlebih dahulu.");
                                return null;
                            }
                            // Validate Pemohon
                            if (pemohon != null) {
                                l.debug(">> pemohon != null <<");
                                
                            } else { 
                                context.addMessage(permohonan.getIdPermohonan() + " - Sila masukkan Maklumat Pemohon di tab Pemohon terlebih dahulu.");
                                return null;
                            }
                            // Validate Tujuan LPS
                            if (ppi != null) {
                                l.debug(">> ppi != null <<");
                                
                            } else { 
                                context.addMessage(permohonan.getIdPermohonan() + " - Sila masukkan Tujuan LPS di tab Permit terlebih dahulu.");
                                return null;
                            }
                        } else if (stage.equals("g_charting_permohonan")) {
                            l.debug(">> Stage : " +stage+ " <<");
                            //Validate Laporan Pelan
                            if (plp != null) {
                                l.debug(">> hp != null <<");
                               
                                if (hp.getLuasTerlibat() == null || hp.getKodUnitLuas() == null || hp.getKodHakmilik() == null ) {
                                    context.addMessage(permohonan.getIdPermohonan() + " - Sila lengkapkan Status Tanah di tab Laporan Pelan terlebih dahulu.");
                                    return null;
                                } 
                                
                                if (plp.getNoLitho() == null || plp.getKodTanah() == null) {
                                    context.addMessage(permohonan.getIdPermohonan() + " - Sila lengkapkan Status Tanah di tab Laporan Pelan terlebih dahulu.");
                                    return null;
                                } 
                            } else { 
                                context.addMessage(permohonan.getIdPermohonan() + " - Sila lengkapkan Laporan Pelan terlebih dahulu.");
                                return null;
                            }
                        }
                        break;
                } //switch negeri
                break;
            case 9:
                //LPSP
                switch (negeri) {
                    case 1:
                        //MELAKA
                        //TODO
                        break;
                    case 2:
                        //NEGERI SEMBILAN
                        //TODO
                        break;
                }
            case 10:
                //PRIZ
                switch (negeri) {
                    case 1:
                        //MELAKA
                        //TODO
                        break;
                    case 2:
                        //NEGERI SEMBILAN
                        //TODO
                        break;
                }
            case 11:
                //PRMMK
                switch (negeri) {
                    case 1:
                        //MELAKA
                        //TODO
                        break;
                    case 2:
                        //NEGERI SEMBILAN
                        //TODO
                        break;
                }
            case 12:
                //PBRZ
                switch (negeri) {
                    case 1:
                        //MELAKA
                        //TODO
                        break;
                    case 2:
                        //NEGERI SEMBILAN
                        //TODO
                        break;
                }
            case 13:
                //PCRG
                switch (negeri) {
                    case 1:
                        //MELAKA
                        //TODO
                        break;
                    case 2:
                        //NEGERI SEMBILAN
                        //TODO
                        break;
                }
            case 14:
                //MPCRG
                switch (negeri) {
                    case 1:
                        //MELAKA
                        //TODO
                        break;
                    case 2:
                        //NEGERI SEMBILAN
                        //TODO
                        break;
                }
            case 15:
                //PJLB
                switch (negeri) {
                    case 1:
                        //MELAKA
                        //TODO
                        break;
                    case 2:
                        //NEGERI SEMBILAN
                        //TODO
                        break;
                }
            case 16:
                //MPJLB
                switch (negeri) {
                    case 1:
                        //MELAKA
                        //TODO
                        break;
                    case 2:
                        //NEGERI SEMBILAN
                        //TODO
                        break;
                }
            case 17:
                //RAYL
                switch (negeri) {
                    case 1:
                        //MELAKA
                        //TODO
                        break;
                    case 2:
                        //NEGERI SEMBILAN
                        //TODO
                        break;
                }
            case 18:
                //RLPTG
                switch (negeri) {
                    case 1:
                        //MELAKA
                        //TODO
                        break;
                    case 2:
                        //NEGERI SEMBILAN
                        //TODO
                        break;
                }
            case 19:
                //RAYK
                switch (negeri) {
                    case 1:
                        //MELAKA
                        //TODO
                        break;
                    case 2:
                        //NEGERI SEMBILAN
                        //TODO
                        break;
                }
            case 20:
                //PPTR
                switch (negeri) {
                    case 1:
                        //MELAKA
                        //TODO
                        break;
                    case 2:
                        //NEGERI SEMBILAN
                        //TODO
                        break;
                }
            case 21:
                //PWGSA
                switch (negeri) {
                    case 1:
                        //MELAKA
                        //TODO
                        break;
                    case 2:
                        //NEGERI SEMBILAN
                        //TODO
                        break;
                }
            case 22:
                //PBGSA
                switch (negeri) {
                    case 1:
                        //MELAKA
                        //TODO
                        break;
                    case 2:
                        //NEGERI SEMBILAN
                        //TODO
                        break;
                }
            case 23:
                //PTGSA
                switch (negeri) {
                    case 1:
                        //MELAKA
                        //TODO
                        break;
                    case 2:
                        //NEGERI SEMBILAN
                        l.debug(">> case 2: N9 <<");
                        
                        HakmilikPermohonan hp = ps.findHakmilikPermohonan(permohonan.getIdPermohonan());
                        Pemohon pemohon = ps.findPemohon(permohonan.getIdPermohonan());
                        
                        if (stage.equals("01Kemasukan")) {
                            l.debug(">> Stage : " +stage+ " <<");                            
                            // Validate Tujuan
                            if (permohonan != null) {
                                l.debug(">> permohonan != null <<");
                                if (permohonan.getSebab() == null ) {
                                    context.addMessage(permohonan.getIdPermohonan() + " - Sila lengkapkan Maklumat Permohonan di tab Tanah terlebih dahulu.");
                                    return null;
                                } 
                            }                             
                            // Validate Tanah
                            if (hp != null) {
                                l.debug(">> hp != null <<");
                                if (hp.getBandarPekanMukimBaru() == null || hp.getHakmilik() == null || hp.getLuasTerlibat() == null || hp.getKodUnitLuas() == null || hp.getNoLot() == null || hp.getLot() == null || hp.getLokasi() == null) {
                                    context.addMessage(permohonan.getIdPermohonan() + " - Sila lengkapkan Maklumat Tanah Yang Terlibat di tab Tanah terlebih dahulu.");
                                    return null;
                                } 
                            } else { 
                                context.addMessage(permohonan.getIdPermohonan() + " - Sila masukkan Maklumat Tanah Yang Terlibat di tab Tanah terlebih dahulu.");
                                return null;
                            }
                            // Validate Pemohon
                            if (pemohon != null) {
                                l.debug(">> pemohon != null <<");
                                
                            } else { 
                                context.addMessage(permohonan.getIdPermohonan() + " - Sila masukkan Maklumat Pemohon di tab Pemohon terlebih dahulu.");
                                return null;
                            }                            
                        }
                        break;
                }
            case 24:
                //BMBT
                switch (negeri) {
                    case 1:
                        //MELAKA
                        //TODO
                        break;
                    case 2:
                        //NEGERI SEMBILAN
                        //TODO
                        break;
                }
            case 25:
                //PTBTC
                switch (negeri) {
                    case 1:
                        //MELAKA
                        //TODO
                        break;
                    case 2:
                        //NEGERI SEMBILAN
                        //TODO
                        break;
                }
            case 26:
                //PTBTS
                switch (negeri) {
                    case 1:
                        //MELAKA
                        //TODO
                        break;
                    case 2:
                        //NEGERI SEMBILAN
                        //TODO
                        break;
                }
            case 27:
                //WMRE
                switch (negeri) {
                    case 1:
                        //MELAKA
                        //TODO
                        break;
                    case 2:
                        //NEGERI SEMBILAN
                        //TODO
                        break;
                }
            case 28:
                //MMRE
                switch (negeri) {
                    case 1:
                        //MELAKA
                        //TODO
                        break;
                    case 2:
                        //NEGERI SEMBILAN
                        //TODO
                        break;
                }
            case 29:
                //JMRE
                switch (negeri) {
                    case 1:
                        //MELAKA
                        //TODO
                        break;
                    case 2:
                        //NEGERI SEMBILAN
                        //TODO
                        break;
                }
            case 30:
                //BMRE
                switch (negeri) {
                    case 1:
                        //MELAKA
                        //TODO
                        break;
                    case 2:
                        //NEGERI SEMBILAN
                        //TODO
                        break;
                }
            case 31:
                //PJTK
                switch (negeri) {
                    case 1:
                        //MELAKA
                        //TODO
                        break;
                    case 2:
                        //NEGERI SEMBILAN
                        //TODO
                        break;
                }
            case 32:
                //PTPBP
                switch (negeri) {
                    case 1:
                        //MELAKA
                        //TODO
                        break;
                    case 2:
                        //NEGERI SEMBILAN
                        //TODO
                        break;
                }
            case 33:
                //PTMTA
                switch (negeri) {
                    case 1:
                        //MELAKA
                        //TODO
                        break;
                    case 2:
                        //NEGERI SEMBILAN
                        l.debug(">> case 2: N9 <<");
                        
                        HakmilikPermohonan hp = ps.findHakmilikPermohonan(permohonan.getIdPermohonan());
                        Pemohon pemohon = ps.findPemohon(permohonan.getIdPermohonan());
                        
                        if (stage.equals("01Kemasukan")) {
                            l.debug(">> Stage : " +stage+ " <<");                            
                            // Validate Tujuan
                            if (permohonan != null) {
                                l.debug(">> permohonan != null <<");
                                if (permohonan.getSebab() == null ) {
                                    context.addMessage(permohonan.getIdPermohonan() + " - Sila lengkapkan Maklumat Permohonan di tab Tanah terlebih dahulu.");
                                    return null;
                                } 
                            }                             
                            // Validate Tanah
                            if (hp != null) {
                                l.debug(">> hp != null <<");
                                if (hp.getBandarPekanMukimBaru() == null || hp.getHakmilik() == null || hp.getLuasTerlibat() == null || hp.getKodUnitLuas() == null || hp.getNoLot() == null || hp.getLot() == null || hp.getLokasi() == null) {
                                    context.addMessage(permohonan.getIdPermohonan() + " - Sila lengkapkan Maklumat Tanah Yang Terlibat di tab Tanah terlebih dahulu.");
                                    return null;
                                } 
                            } else { 
                                context.addMessage(permohonan.getIdPermohonan() + " - Sila masukkan Maklumat Tanah Yang Terlibat di tab Tanah terlebih dahulu.");
                                return null;
                            }
                            // Validate Pemohon
                            if (pemohon != null) {
                                l.debug(">> pemohon != null <<");
                                
                            } else { 
                                context.addMessage(permohonan.getIdPermohonan() + " - Sila masukkan Maklumat Pemohon di tab Pemohon terlebih dahulu.");
                                return null;
                            }                            
                        }
                        break;
                } //switch (negeri)
        } //switch (kod)

        return proposedOutcome;

    } //beforeComplete

    public void callDefault(int negeri) {
        //TODO
    }
    
    @Override
    public void afterComplete(StageContext context) {
//        throw new UnsupportedOperationException("afterComplete not yet supported.");
//        return true;
    }

    @Override
    public String beforePushBack(StageContext context, String proposedOutcome) {
        return proposedOutcome;
    }

    @Override
    public void afterPushBack(StageContext context) {
//        throw new UnsupportedOperationException("afterPushBack not yet supported.");
//        return true;
    }
}