/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.service.daftar;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author mr5rule
 */
public enum NotaDaftarKodMap {
    //group 1 Cetak/Sign DKDE sahaja
    PRPMB(2),
    HMV(2),
    HMVB(2),
    HASB(2),
    HSAM(2),
    HSHRM(2),
    ABT(2),
    ABTA(2),
    ABTD(2),
    ABTB(2),
    CB(2),
    CL(2),
    CM(2),
    CMP(2),
    CMB(2),
    SBKSB(2),
    SBKSL(2),
    SBKSM(2),
    SBKBG(2),
    PSMK(2),
    PBK(2),
    SBSTK(2),
    SBTK(2),
    CK(2),
    TSSKK(2),
    SSKPK(2),
    SBSTB(2),
    SBSTM(2),
    SBSTL(2),
    SBTB(2),
    SBTM(2),
    SBTL(2),
    SSKPM(2),
    SSKPL(2),
    SSKPB(2),
    TMA(2),
    TMAK(2),
    TBTW(2),
    TBTWB(2),
    TBTWK(2),
    TSM(2),
    TSB(2),
    TSSKB(2),
    HTPV(2),
    ITB(2),
    ITD(2),
    ITP(2),
    ITBB(2),
    ITDB(2),
    ITPB(2),
    ITS(2),
    KCL(2),
    KOSR(2),
    KOSRB(2),
    LPB(2),
    
    LPM(2),
    LPMB(2),
    MAJB(2),
    MAJD(2),
    MCLM(2),
    N6A(2),
    N6AB(2),
    N7A(2),
    N7AB(2),
    N7B(2),
    N7BB(2),
    N8A(2),
    N8AB(2),
    PBB(2),
    PBBB(2),
    PBBL(2),
    PBBM(2),
    PBBT(2),
    PBCTB(2),
    PBCTL(2),
    PBCTM(2),
    PBCTT(2),
    PBL(2),
    PBM(2),
    PBSCB(2),
    PBSCM(2),
    PCT(2),
    ECPI(2),
    PREM(2),
    PREMB(2),
    PSB(2),
    PSKB(2),
    PSKM(2),
    PSM(2),
    PSMB(2),
    PSMM(2),
    PSTSB(2),
    PSTSM(2),
    PTB(2),
    PTP(2),
    PTBB(2),
    PTPB(2),
    RKSR(2),
    RKSRB(2),
    //    GROUP 2 cetak 2-2
    ABTK(2),
    ABTBH(2),
    ABTKB(2),
    ABTS(2),
    ADAT(2),
    ADMNB(2),
    ADMNS(2),
    ADMRL(2),
    EUB(2),
    EUBS(2),
    FGT1(2),
    FGT2(2),
    FGT3(2),
    FGT4(2),
    FGT5(2),
    FGT6(2),
    FGT7(2),
    FGT8(2),
    FGT9(2),
    STMA(2),
    TSSKM(2),
    TSSKL(2),
    HTB(2),
    HTBKR(2),
    IGSA(2),
    IGSA5(2),
    IGSA6(2),
    IGSAB(2),
    IPMB(2),
    IRMB(2),
    IRMBM(2),
    IROAB(2),
    LTS(2),
    MCLL(2),
    PHKK(2),
    PHSK(2),
    PPKR(2),
    PSKSB(2),
    PSKSM(2),
    PSL(2),
    PSTSL(2),
    REM(2),
    REMB(2),
    RPBNB(2),
    PK(2),
    
    PKB(2),
    SHKK(2),
    SHSK(2),
    HLLA(2),
    HLPK(2),
    HLTE(2),
    HLTEB(2),
    HLLB(2),
    HLLS(2),
    LPBB(2), //ubah FAT sessi 3
    
    //Orogenic Urusan
    ADBSB(2),
    ADBSS(2),
    ADBSL(2),
    AEROD(2),
    IKOA(2),
    IKOAB(2),
    IROA(2),
    IRM(2),
    KRM(2),
    KRMB(2),
    LTDBL(2),
    IPM(2),
    PSPBB(2),
    PSPBN(2),
    PBMM(2),
    PPM(2),
    LMTP(2),
    LMTPB(2),
    SHKB(2),
    SHSB(2),
    TSP(2),
    TSPB(2),
    
    KPESL(2),
    KPEBL(2),
    
    //infokall urusan
    KB(2),
    KBB(2),
    IRTB(2),
    IRTBB(2),   
    HTT(2),
    HTBT(2),
    PBPM(2),
    PINDE(2),
    TT(2),
    TTB(2),
    TTTK(2),
    TTW(2),
    TTWB(2),
    TTWKP(2),
    PB(2),
    TMAMB(2),
    PMDPT(2),
    JPB(2),
    IDMLB(2),
    TTWLB(2),    
    TTWLM(2),
    PMBUD(2),
    GDPJT(2),
    GDWM(2), 
    PMHHB(2),
    LTSK(2),
    ROSB(2),
    ACT(2),
    PSPM(2),
    PSPL(2),
    PSPB(2),
    
    //    160 STOP HERE WILL BE CONTINUE.....
    ;
    private static final Map<Integer, NotaDaftarKodMap> lookup = new HashMap<Integer, NotaDaftarKodMap>();

    static {
        for (NotaDaftarKodMap s : EnumSet.allOf(NotaDaftarKodMap.class)) {
            lookup.put(s.getCode(), s);
        }
    }
    private final int code;

    NotaDaftarKodMap(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static NotaDaftarKodMap get(int code) {
        return lookup.get(code);
    }

    @Override
    public String toString() {
        return String.valueOf(code);
    }
}
