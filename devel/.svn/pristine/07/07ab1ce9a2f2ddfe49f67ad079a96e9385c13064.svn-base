/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.sequence;

import etanah.model.KodCawangan;
import etanah.model.Pengguna;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import org.apache.log4j.Logger;

/**
 *
 * @author abu.mansur
 */
public class GeneratorTagAkaun extends SequenceGenerator {

    @Override
    public String generate(String kodNegeri, KodCawangan caw, Object obj) {
        if (!(obj instanceof Pengguna)) {
            throw new IllegalArgumentException("Parameter is not Pengguna class");
        }
        String tarikh = (new SimpleDateFormat("yyyy")).format(new java.util.Date());

        NumberFormat df = NumberFormat.getInstance();
        df.setMaximumIntegerDigits(8);
        df.setMinimumIntegerDigits(8);
        df.setGroupingUsed(false);

        Pengguna pguna = (Pengguna) obj;
        String seqNo = df.format(getSerialNo("seq_tag_akaun"));

        String id = tarikh + seqNo;
        if (IS_DEBUG) LOG.debug(id);

        return id;
    }

}
