/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.sequence;

import etanah.model.Hakmilik;
import etanah.model.KodCawangan;
import java.text.NumberFormat;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import etanah.dao.KodBandarPekanMukimDAO;
import com.google.inject.Inject;
import etanah.model.KodBandarPekanMukim;
import java.text.DecimalFormat;
/**
 *
 * @author khairil
 */
public class GeneratorIdHakmilik extends SequenceGenerator {

    @Inject
    KodBandarPekanMukimDAO bpmDAO;
    
    @Override
    public String generate(String kodNegeri, KodCawangan caw, Object obj) {
        if (!(obj instanceof Hakmilik)) {
            throw new IllegalArgumentException("Parameter is not Hakmilik class");
        }
        Hakmilik kod = (Hakmilik) obj;
        // SEQ_HAKMILIK_{KOD_DAERAH}_{JENIS HAKMILIK}
        NumberFormat df = NumberFormat.getInstance();
        df.setMaximumIntegerDigits(8);
        df.setMinimumIntegerDigits(8);
        df.setGroupingUsed(false);
        /*TODO: NUMBER PADDING*/
//        String paddedSequence = StringUtils.leftPad(seqName,3, '0');

        KodBandarPekanMukim bpm = bpmDAO.findById(kod.getBandarPekanMukim().getKod());
        String kodBPM = bpm.getbandarPekanMukim();
        String seqName= kod.getKodHakmilik().getKod();
        String kodHakmilik = kod.getKodHakmilik().getKod();
        if(seqName.equals("PN") || seqName.equals("GRN")){
            // case 1
            // seq_hakmilik_pn
            // seq_hakmilik_grn
            // seqName

        } else if(seqName.equals("HSD")){
            //case2
            //seq_hakmilik_hsd + kod daerah
            seqName += "_" + kod.getDaerah().getKod();
        } else if(seqName.equals("HSM") || seqName.equals("GM") 
                || seqName.equals("PM") || seqName.equals("GMM") 
                || seqName.equals("HMM") || seqName.equals("EMR")
                || seqName.equals("CT")){
            
            if (seqName.equals("EMR")) {
                kodHakmilik = "GM";       
                seqName = "GM";
            } else if (seqName.equals("CT")) {
                kodHakmilik = "GRN";               
                seqName = "GRN";
            }  
            //case 3
            //seq_hakmilik_??m + kod daerah + kod bpm
            seqName += "_" + kod.getDaerah().getKod() + "_" + kodBPM;
        } 

        String id = kodNegeri + kod.getDaerah().getKod() + kodBPM + kodHakmilik + df.format(getSerialNo("SEQ_HAKMILIK_" + seqName));
        if (IS_DEBUG) LOG.debug(id);
        return id;
    }

    public String generateStrata(String kodNegeri, KodCawangan caw, Object obj) {
        if (!(obj instanceof Hakmilik)) {
            throw new IllegalArgumentException("Parameter is not Hakmilik class");
        }
        Hakmilik kod = (Hakmilik) obj;
        // SEQ_HAKMILIK_{KOD_DAERAH}_{JENIS HAKMILIK}
//       NumberFormat dfTingkat = NumberFormat.getInstance();
//        dfTingkat.setMaximumIntegerDigits(2);
//        dfTingkat.setMinimumIntegerDigits(2);
//        dfTingkat.setGroupingUsed(false);
//
//        NumberFormat dfPetak = NumberFormat.getInstance();
//        dfPetak.setMaximumIntegerDigits(3);
//        dfPetak.setMinimumIntegerDigits(3);
//        dfPetak.setGroupingUsed(false);


//        KodBandarPekanMukim bpm = bpmDAO.findById(kod.getBandarPekanMukim().getKod());
//        String kodBPM = bpm.getbandarPekanMukim();
//        String seqName= kod.getKodHakmilik().getKod();
//        String noBangunan = kod.getNoBangunan().substring(1);
        String noBangunanAwal = kod.getNoBangunan().substring(0,1);
        String noBangunan = kod.getNoBangunan();
//        logger.debug("noBangunan :"+noBangunan);
        String noTingkat = kod.getNoTingkat();
        String noPetak = kod.getNoPetak();
        String noHakmilikLama = kod.getIdHakmilik();

        NumberFormat formatterBangunan = new DecimalFormat("00");
        NumberFormat formatterTingkat = new DecimalFormat("000");
        NumberFormat formatterPetak = new DecimalFormat("00000");
        String formattedtingkat = formatterTingkat.format(Integer.parseInt(noTingkat));
        String formattedpetak = formatterPetak.format(Integer.parseInt(noPetak));
//        String formattedbangunan = formatterBangunan.format((noBangunan));
        
        String id = noHakmilikLama + format(noBangunan) + formattedtingkat + formattedpetak;
        if (IS_DEBUG) LOG.debug(id);
        return id;
    }


    private String format(String a) {
        String f = "";
        for (int i = a.length(); i < 3; i++) {
            System.out.println(i);
            f = f + "0";
            System.out.println(f);
        }
        String formatted = f + a;
        System.out.println("Formated : " + formatted);
        return formatted;
    }
}
