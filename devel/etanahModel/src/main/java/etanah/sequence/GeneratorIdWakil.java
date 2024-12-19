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
 *
 * @author mohd.fairul
 */
public class GeneratorIdWakil extends SequenceGenerator{
    public final static String SEQ_NAME_PRE = "seq_pihak_wakil";

	@Override
	public String generate(String kodNegeri, KodCawangan caw, Object obj) {
		
		String kodDaerah = caw.getKod();

		NumberFormat df = NumberFormat.getInstance();
		df.setMaximumIntegerDigits(6);
		df.setMinimumIntegerDigits(6);
		df.setGroupingUsed(false);

		String id = kodNegeri +
					kodDaerah +
					"SW" +
					((new Date()).getYear() + 1900) +
					df.format(getSerialNo(SEQ_NAME_PRE));
		if (IS_DEBUG) LOG.debug(id);

		return id;
	}


}
