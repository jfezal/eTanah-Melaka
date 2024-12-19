package etanah.sequence;

import java.text.NumberFormat;
import java.util.Date;

import etanah.model.KodCawangan;
import etanah.model.KodUrusan;

/**
 * Format ID Permohonan: <2-KodNegeri><2-KodDaerah><3-KodJabatan><4-Tahun><6-NoSiri> total 16 digits
 * TODO create scheduler to reset for the year
 * Every kod Jabatan would need a sequence with format seq_mohon_kodJabatan
 * @author wan.fairul
 *
 */

public class GeneratorIdPerserahanPaksa extends SequenceGenerator{	

	@Override
	public String generate(String kodNegeri, KodCawangan caw, Object obj) {

		KodUrusan kod = (KodUrusan) obj;
		// System.out.println(namaJabatan);
		String kodDaerah = caw.getKod();
		// System.out.println(kodDaerah);
		
		NumberFormat df = NumberFormat.getInstance();
		df.setMaximumIntegerDigits(5);
		df.setMinimumIntegerDigits(5);
		df.setGroupingUsed(false);
                
                String kodPerserahan = kod.getKodPerserahan().getKod();
		
		String id = kodNegeri + 
					kodDaerah + 
					kodPerserahan + ((new Date()).getYear() + 1900) + "F" +
					df.format(getSerialNo("seq_mohon_f_" + kodDaerah));//ticket #99
		if (IS_DEBUG) LOG.debug(id);
		
		return id;
	}

}
