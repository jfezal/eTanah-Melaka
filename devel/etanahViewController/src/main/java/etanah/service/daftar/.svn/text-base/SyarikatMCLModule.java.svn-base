/*
 * Author : Aizuddin
 * Usage  : To cater 4 urusan SM
 */

package etanah.service.daftar;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum SyarikatMCLModule {
    //Case 1 (Just aktifkan pihak SM)
    SMB(1), SMK(1),
    
    //Case 2 (Update PIHAK)
    SMD(2), //Mungkin tak pakai since die x generate id perserahan dah
    
    //Case 3 (Batal Urusan)
    SMBT(3)
    
    ;
            
    private static final Map<Integer, SyarikatMCLModule> lookup = new HashMap<Integer, SyarikatMCLModule>();

    static {
        for (SyarikatMCLModule s : EnumSet.allOf(SyarikatMCLModule.class)) {
            lookup.put(s.getCode(), s);
        }
    }

    private final int code;

    SyarikatMCLModule(int code){
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static SyarikatMCLModule get(int code) {
        return lookup.get(code);
    }

    @Override
    public String toString() {
        return String.valueOf(code);
    }

}
