/**
 * Generator class to generate No Akaun.
 * This is a temporary solution until we get the required format from the relevant parties.
 * 
 * @author Mohd Hairudin Mansur
 * @version 1.0 24 Dec 2009
 */
package etanah.sequence;

import java.text.NumberFormat;
import org.apache.log4j.Logger;

import etanah.model.Akaun;
import etanah.model.KodCawangan;

public class GeneratorNoAkaun extends SequenceGenerator {

    @Override
    public String generate(String kodNegeri, KodCawangan caw, Object obj) {
        if (!(obj instanceof Akaun)) {
            throw new IllegalArgumentException("Parameter is not Akaun class");
        }

        NumberFormat df = NumberFormat.getInstance();
        df.setMaximumIntegerDigits(6);
        df.setMinimumIntegerDigits(6);
        df.setGroupingUsed(false);

        String seqNo = df.format(getSerialNo("seq_kew_akaun"));
        String padZero = "1";
        int padSize = 12 - seqNo.length() - 1;

        for (int counter = 0; counter < padSize; counter++) {
            padZero += "0";
        }

        String id = padZero + seqNo;
        if (IS_DEBUG) LOG.debug(id);        

        return id;
    }

    public String generateMelaka() {
        NumberFormat df = NumberFormat.getInstance();
        df.setMaximumIntegerDigits(12);
        df.setMinimumIntegerDigits(12);
        df.setGroupingUsed(false);
        String seqNo = df.format(getSerialNo("seq_akaun_ml"));
        String id = seqNo;
        if (IS_DEBUG) LOG.debug(id);        
        return id;
    }
}
