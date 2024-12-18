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
 * @author md.nurfikri
 */
public enum RegistrationModule {

            PMT(1),  JDS(1),  TMAMG(1), PHMM(1), MGGS(1), PHMMS(1), PHMMT(1), PMPJK(1), MGG(1) //9
                    , TMAME(1), TMAML(1), TMAMF(1), TMAMT(1), TMAMW(1), PNPBK(1), PMP(1),PMG(1), TMAMD(1) //9
                    , LTPL(1), PH30A(1),PH30B(1), PHD(7), PLT(1), PNPPBK(1), FHMMS(1), PNPHB(7), JAGAB(1), PLK(1) //11
                    ,  PMTM(1)//5 
                    , PLS(12) //1
                    
            ,GD(2), GDCE(2), GDPJ(2), GDPJK(2), PJBT(2),PJKBT(2), PJT(2), PJKT(2), PJTM(2)//9
            ,PEMB(3), PJB(3),PJKB(3), KVSTB(3), KVSB(3), GDB(3), KVLB(3), KVLTB(3), KVAB(3), ISL(3) //11
                    , PPUHB(3), GDPJB(3),KVPB(3) //4
                    ,KVSMP(3), PLB(3), MGGB(3)//2
            ,TA(4), TN(4), PNKP(4)//3
            ,KVLK(5), KVPT(6), KVST(6), KVSS(5), KVLS(5), TEN(5), TENBT(5), TENPT(5)//9
            ,KVAT(6), PPUH(6), KVSPT(5), KVPS(6), KVSK(6), PMKMH(6), KVLT(6), KVAS(6), KVPK(6), KVAK(6), KVPPT(5), KVPP(5)//10
            //7 = set pihak ke tidak aktif, insert urusan , batalkan urusan terdahulu
            ,KVATB(7),GDL(7),IROAB(7),IRTBB(7), PJSB(7), PJKSB(7),GDPJL(7), GDCEL(7), TENL(7), TENB(7), ISB(7), KVSP(3)
                    , PMSE(7), PMHUN(7), PMHBE(7), PMBKU(7)//14
            ,PNPA(8) //pendaftaran pemegang amanah .. 1
            ,JPGD(9), JMGD(9), JML(9) , JPGPJ(9) , JMGPJ(9), JMLK(9),JDGD(9)//7
            ,JDGPJ(10),PHMMK(10)//2
            ,IS(11), ISBLS(11) //2
             //urusan 12 - hanya insert urusan sahaja
            ,LTK(12), KVSPC(6),GDT(12),PMHUK(12)  //2
            ,PMTB(13),TMAMB(22),CPB(12) , PNPAB(13)
            ,PHMB(14) //batal hakmilik
            ,TRPA(15)
            ,PBGAA(16)
            ,PMKSW(17)
                    //added by murali (STRATA)
            ,PBBM(17), PBBL(17), PBBT(17)
            //Added by Aizuddin
            ,PHADB(9), PHADS(9), KVLP(5), PJLT(12), JMB(20),JPB(19),GDWM(2),PB(9),GDPJT(3),ROSB(2),PMHHB(18),PRPMB(18)
            //tukar nama pemohon lama kepada baru, deaktifkan hakmilikPihak lama, insert hakmilikPihak baru
            ,PMBUD(21)
            ,WAKAF(23)
            
            
            ;
            
    private static final Map<Integer, RegistrationModule> lookup = new HashMap<Integer, RegistrationModule>();

    static {
        for (RegistrationModule s : EnumSet.allOf(RegistrationModule.class)) {
            lookup.put(s.getCode(), s);
        }
    }

    private final int code;

    RegistrationModule(int code){
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static RegistrationModule get(int code) {
        return lookup.get(code);
    }

    @Override
    public String toString() {
        return String.valueOf(code);
    }

}
