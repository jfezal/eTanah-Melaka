/**
 * Generator class to generate Id Notis.
 * This is a temporary solution until we get the required format from the relevant parties.
 * 
 * @author Mohd Hairudin Mansur
 * @version 1.0 29 Dec 2009
 */

package etanah.sequence;

import java.text.NumberFormat;

import org.apache.log4j.Logger;

import etanah.model.DasarTuntutanNotis;
import etanah.model.KodCawangan;

public class GeneratorIdNotis extends SequenceGenerator {


	@Override
	public String generate(String kodNegeri, KodCawangan caw, Object obj) {
		if (!(obj instanceof DasarTuntutanNotis)){
			throw new IllegalArgumentException("Parameter is not DasarTuntutanNotis class");
		}
		
		NumberFormat df = NumberFormat.getInstance();
		df.setMaximumIntegerDigits(6);
		df.setMinimumIntegerDigits(6);
		df.setGroupingUsed(false);
		
		String seqNo = df.format(getSerialNo("seq_dasar_cukai_notis"));
		String padZero = "1";
		int padSize = 12 - seqNo.length() - 1;
		
		for(int counter=0; counter < padSize; counter++) {
			padZero += "0";
		}
		
		String id = padZero + seqNo;
		
                if (IS_DEBUG) LOG.debug(id);
                
		return id;
	}

}
