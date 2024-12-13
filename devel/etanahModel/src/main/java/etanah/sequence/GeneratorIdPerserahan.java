package etanah.sequence;

import java.text.NumberFormat;
import java.util.Date;

import etanah.model.KodCawangan;
import etanah.model.KodUrusan;
import etanah.model.Permohonan;

/**
 * Format ID Perserahan: <2-KodNegeri><2-KodDaerah><2-KodJenisUrusan><4-Tahun><6-NoSiri> total 17 digits
 * TODO create scheduler to reset for the year
 * Every kod Jabatan would need a sequence with format seq_mohon_kodJabatan
 * @author hishammk
 *
 */

public class GeneratorIdPerserahan extends SequenceGenerator{
	
	public final static String SEQ_NAME_PRE = "seq_serah_";

	@Override
	public String generate(String kodNegeri, KodCawangan caw, Object obj) {
		if (!(obj instanceof KodUrusan)){
			throw new IllegalArgumentException("Parameter is not KodUrusan class");
		}
		KodUrusan kod = (KodUrusan) obj;
		String kodCawangan = caw.getKod();
		
		NumberFormat df = NumberFormat.getInstance();
		df.setMaximumIntegerDigits(6);
		df.setMinimumIntegerDigits(6);
		df.setGroupingUsed(false);
		
		String kodPerserahan = kod.getKodPerserahan().getKod();
		String id = kodNegeri + 
					kodCawangan + 
					kodPerserahan + 
					((new Date()).getYear() + 1900) + 
					df.format(getSerialNo(SEQ_NAME_PRE + kodPerserahan +
							"_" + kodCawangan)); 
		if (IS_DEBUG) LOG.debug(id);
		
		return id;
	}

}
