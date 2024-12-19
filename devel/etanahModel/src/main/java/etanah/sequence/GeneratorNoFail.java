package etanah.sequence;

import java.text.NumberFormat;
import java.util.Date;

import etanah.model.KodCawangan;
import etanah.model.KodUrusan;

/**
 * @author haqqiem
 *
 */
public class GeneratorNoFail extends SequenceGenerator {

    @Override
    public String generate(String kodNegeri, KodCawangan caw, Object obj) {
        if (!(obj instanceof KodUrusan)) {
            throw new IllegalArgumentException("Parameter is not KodUrusan class");
        }
        KodUrusan kod = (KodUrusan) obj;
        String kodDaerah = caw.getKod();

        NumberFormat df = NumberFormat.getInstance();
        df.setMaximumIntegerDigits(6);
        df.setMinimumIntegerDigits(6);
        df.setGroupingUsed(false);

        String id = kodNegeri + kodDaerah + kod.getKodPerserahan().getKod() + ((new Date()).getYear() + 1900) + df.format(getSerialNo("seq_serah_md_" + kodDaerah));
        if (IS_DEBUG) {
            LOG.debug(id);
        }

        return id;
    }

}
