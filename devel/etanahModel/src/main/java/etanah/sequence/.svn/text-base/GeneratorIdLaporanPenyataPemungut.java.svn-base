package etanah.sequence;

import etanah.model.KodCawangan;
import etanah.model.Pengguna;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import org.apache.log4j.Logger;

public class GeneratorIdLaporanPenyataPemungut extends SequenceGenerator{

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
        Long seqNo = getSerialNo("seq_lapor_pnyata_pmungut");

        String id = seqNo.toString();
        if (IS_DEBUG) LOG.debug(id);

        return id;
    }
}
