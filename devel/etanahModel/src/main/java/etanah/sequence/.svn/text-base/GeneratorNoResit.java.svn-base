package etanah.sequence;

import etanah.model.KodCawangan;
import etanah.model.Pengguna;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;

/**
 * Format <yyMMdd><KodCawangan-2><?-2><user-2><serialNo-4>=16-digits
 * @author hishammk
 *
 */
public class GeneratorNoResit extends SequenceGenerator{

	@Override
	public String generate(String kodNegeri, KodCawangan caw, Object obj) {
            if (!(obj instanceof Pengguna)){
                throw new IllegalArgumentException("Parameter is not Pengguna class");
            }
            String tarikh = (new SimpleDateFormat("yyMMdd")).format(new java.util.Date());

            NumberFormat df = NumberFormat.getInstance();
            df.setMaximumIntegerDigits(4);
            df.setMinimumIntegerDigits(4);
            df.setGroupingUsed(false);
            
            Pengguna pguna = (Pengguna) obj;
            String kodDaerah = pguna.getKodCawangan().getKod();
            String kaunter = pguna.getIdKaunter();

            String seqNo = df.format(getSerialNo("seq_kew_dokumen"));

            String id = tarikh+kodDaerah+caw.getKod()+kaunter+seqNo ;
            if (IS_DEBUG) LOG.debug(id);

            return id;
	}

    public String generateNoResit(String kodNegeri, KodCawangan caw, Object obj) {
        if (!(obj instanceof Pengguna)) {
            throw new IllegalArgumentException("Parameter is not Hakmilik class");
        }
        Pengguna pguna = (Pengguna) obj;
        String tarikh = (new SimpleDateFormat("yyMMdd")).format(new java.util.Date());
        String kodDaerah = pguna.getKodCawangan().getKod();
        String kaunter = pguna.getIdKaunter();
        String seqName= pguna.getKodCawangan().getKod()+"_"+kaunter;
        System.out.println("sequence : SEQ_KAUNTER_"+seqName);

        NumberFormat df = NumberFormat.getInstance();
        df.setMaximumIntegerDigits(4);
        df.setMinimumIntegerDigits(4);
        df.setGroupingUsed(false);

        String id = tarikh+kodDaerah+caw.getKod()+kaunter+df.format(getSerialNo("SEQ_KAUNTER_" + seqName)) ;
        if (IS_DEBUG) LOG.debug(id);
        return id;
    }

    public String generateResitJurnal(String kodNegeri, KodCawangan caw, Object obj) {
            if (!(obj instanceof Pengguna)){
                throw new IllegalArgumentException("Parameter is not Pengguna class");
            }
            String tarikh = (new SimpleDateFormat("yyMMdd")).format(new java.util.Date());

            NumberFormat df = NumberFormat.getInstance();
            df.setMaximumIntegerDigits(7);
            df.setMinimumIntegerDigits(7);
            df.setGroupingUsed(false);

            String seqNo = df.format(getSerialNo("seq_kew_dokumen"));

            String id = "J"+seqNo ;
            if (IS_DEBUG) LOG.debug(id);

            return id;
	}
}
