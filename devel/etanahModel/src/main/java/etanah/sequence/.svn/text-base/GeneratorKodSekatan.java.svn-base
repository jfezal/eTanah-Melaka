package etanah.sequence;

import etanah.model.KodCawangan;
import etanah.model.Pengguna;
import java.text.NumberFormat;

/**
 *
 * @author haqqiem
 * Tue May 22 2012 12:58:05 PM
 *
 */
public class GeneratorKodSekatan extends SequenceGenerator {

    @Override
    public String generate(String kodNegeri, KodCawangan caw, Object obj) {
        if (!(obj instanceof Pengguna)) {
            throw new IllegalArgumentException("Parameter is not Pengguna class");
        }

        String cawangan = caw.getKod();

        NumberFormat df = NumberFormat.getInstance();
            df.setMaximumIntegerDigits(8);
            df.setMinimumIntegerDigits(8);
            df.setGroupingUsed(false);

        String id = cawangan + df.format(getSerialNo("SEQ_KOD_SEKATAN_" + cawangan));
        LOG.info("id : "+id);
        if (IS_DEBUG) LOG.debug(id);
        return id;
    }
}
