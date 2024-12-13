/**
 * Generator class to generate Kew Dokumen Sequence.
 * 
 * 
 * @author Mohd Hairudin Mansur
 * @version 1.0 11 March 2010
 */

package etanah.sequence;

import java.text.NumberFormat;

import org.apache.log4j.Logger;

import etanah.model.DokumenKewangan;
import etanah.model.KodCawangan;

public class GeneratorKewDokumen extends SequenceGenerator {
		
	@Override
	public String generate(String kodNegeri, KodCawangan caw, Object obj) {
		if (!(obj instanceof DokumenKewangan)){
			throw new IllegalArgumentException("Parameter is not DokumenKewangan class");
		}
		
		NumberFormat df = NumberFormat.getInstance();
		df.setMaximumIntegerDigits(6);
		df.setMinimumIntegerDigits(6);
		df.setGroupingUsed(false);
		
		String seqNo = df.format(getSerialNo("seq_kew_dokumen"));
		String padZero = "0";
		int padSize = (4 - seqNo.length()) - 1;
		
		for(int counter=0; counter < padSize; counter++) {
			padZero += "0";
		}
		
		String id = padZero + seqNo; 
		
                if (IS_DEBUG) LOG.debug(id);
		return id;
	}

}
