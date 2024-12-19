package etanah.sequence;

import java.text.NumberFormat;
import java.util.Date;

import etanah.model.KodCawangan;
import etanah.model.KodUrusan;
import etanah.model.Permohonan;

/**
 * Format ID Permohonan: <2-KodNegeri><2-KodDaerah><3-KodModul><4-Tahun><6-NoSiri> total 16 digits
 * TODO create scheduler to reset for the year
 * Every kod Jabatan would need a sequence with format seq_mohon_kodJabatan
 * @author hishammk
 *
 */

public class GeneratorIdPermohonan extends SequenceGenerator{	

	@Override
	public String generate(String kodNegeri, KodCawangan caw, Object obj) {
		if (!(obj instanceof KodUrusan)){
			throw new IllegalArgumentException("Parameter is not KodUrusan class");
		}
		KodUrusan kod = (KodUrusan) obj;
		// System.out.println(namaJabatan);
		String kodDaerah = caw.getKod();
		// System.out.println(kodDaerah);
		
		NumberFormat df = NumberFormat.getInstance();
		df.setMaximumIntegerDigits(6);
		df.setMinimumIntegerDigits(6);
		df.setGroupingUsed(false);
		
		String id = kodNegeri + 
					kodDaerah + 
					kod.getJabatan().getAkronim() + ((new Date()).getYear() + 1900) + 
					df.format(getSerialNo("seq_mohon_" + kod.getJabatan().getAkronim() + "_" + kodDaerah));//ticket #99
		if (IS_DEBUG) LOG.debug(id);
		
		return id;
	}

}
