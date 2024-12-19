/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.sequence;

import java.text.NumberFormat;
import java.util.Date;

import etanah.model.KodCawangan;
import etanah.model.KodUrusan;
import etanah.model.Permohonan;

/**
 * Format ID Kelompok: <2-KodNegeri><2-KodDaerah>GRP<4-Tahun><6-NoSiri> total 17 digits
 * @author Afham
 *
 */

public class GeneratorIdPermohonanKelompok extends SequenceGenerator{

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
					"GRP" + ((new Date()).getYear() + 1900) + 
					df.format(getSerialNo("seq_mohon_" + kod.getJabatan().getAkronim() + "_" + kodDaerah));//ticket #99
		if (IS_DEBUG) LOG.debug(id);
		
		return id;
	}

}

