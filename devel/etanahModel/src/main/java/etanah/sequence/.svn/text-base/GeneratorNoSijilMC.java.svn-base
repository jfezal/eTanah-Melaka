package etanah.sequence;

import etanah.model.KodCawangan;
import java.text.NumberFormat;


/**
 *
 * @author faidzal
 */
public class GeneratorNoSijilMC extends SequenceGenerator {

    @Override
    public String generate(String kodNegeri, KodCawangan caw, Object obj) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
public String generateSijil() {
        NumberFormat df = NumberFormat.getInstance();
        df.setMaximumIntegerDigits(5);
        df.setMinimumIntegerDigits(5);
        df.setGroupingUsed(false);
        String seqNo = df.format(getSerialNo("seq_no_sijil_mc"));
        String id = seqNo;
        if (IS_DEBUG) LOG.debug(id);        
        return id;
    }
}
