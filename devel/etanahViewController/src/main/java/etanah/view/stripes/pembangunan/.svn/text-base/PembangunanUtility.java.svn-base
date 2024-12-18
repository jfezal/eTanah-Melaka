package etanah.view.stripes.pembangunan;

/**
 *
 * @author NageswaraRao
 */
import etanah.model.PermohonanKertasKandungan;
import etanah.model.PermohonanRujukanLuar;

public class PembangunanUtility {

    private PermohonanKertasKandungan permohonanKertasKandungan;
    private String agensi;
    private PermohonanRujukanLuar permohonanRujukanLuar;

    public String getAgensi() {
        return agensi;
    }

    public void setAgensi(String agensi) {
        this.agensi = agensi;
    }

    public PermohonanKertasKandungan getPermohonanKertasKandungan() {
        return permohonanKertasKandungan;
    }

    public void setPermohonanKertasKandungan(PermohonanKertasKandungan permohonanKertasKandungan) {
        this.permohonanKertasKandungan = permohonanKertasKandungan;
    }

    public PermohonanRujukanLuar getPermohonanRujukanLuar() {
        return permohonanRujukanLuar;
    }

    public void setPermohonanRujukanLuar(PermohonanRujukanLuar permohonanRujukanLuar) {
        this.permohonanRujukanLuar = permohonanRujukanLuar;
    }

    public String convertStringtoInitCap(String val) {
        StringBuilder ff = new StringBuilder();
        String resultVal = null;
        //String inputString = "this is a sentence";

        if (val != null) {
            for (String f : val.split(" ")) {
                if (ff.length() > 0) {
                    ff.append(" ");
                }
                if (!("").equals(f)) {
                    ff.append(f.substring(0, 1).toUpperCase()).append(f.substring(1, f.length()).toLowerCase());
                }
            }
            resultVal = ff.toString();
        }
        
        return resultVal;
    }

    public String convertStringtoInitCapOnlyOnce(String val) {
        StringBuilder ff = new StringBuilder();
        //String inputString = "this is a sentence";
        loop:
        for (String f : val.split(" ")) {
            if (ff.length() > 0) {
                ff.append(" ");
            }
            if (!("").equals(f)) {
                ff.append(f.substring(0, 1).toUpperCase()).append(f.substring(1, f.length()).toLowerCase());
            }
            break loop;
        }
        String resultVal = ff.toString();
        return resultVal;
    }
}