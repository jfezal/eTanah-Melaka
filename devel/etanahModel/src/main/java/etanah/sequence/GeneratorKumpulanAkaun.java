package etanah.sequence;

import etanah.model.KodCawangan;
import etanah.model.Pengguna;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import org.apache.log4j.Logger;

/**
 *
 * @author abdul.hakim
 */
public class GeneratorKumpulanAkaun extends SequenceGenerator {

    @Override
    public String generate(String kodNegeri, KodCawangan caw, Object obj) {
        if (!(obj instanceof Pengguna)) {
            throw new IllegalArgumentException("Parameter is not Pengguna class");
        }
        String tarikh = (new SimpleDateFormat("yyyy")).format(new java.util.Date());

        NumberFormat df = NumberFormat.getInstance();
        df.setMaximumIntegerDigits(6);
        df.setMinimumIntegerDigits(6);
        df.setGroupingUsed(false);

        Pengguna pguna = (Pengguna) obj;
        String seqNo = df.format(getSerialNo("seq_kump_akaun"));

        String id = caw.getKod() + tarikh + seqNo;
        if (IS_DEBUG) LOG.debug(id);

        return id;
    }
}
