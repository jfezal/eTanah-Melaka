/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.sequence;

import etanah.model.KodCawangan;
import etanah.model.PermitSahLaku;
import java.text.NumberFormat;

/**
 *
 * @author afham
 */
public class GeneratorNoSiriPermit extends SequenceGenerator {

    @Override
	public String generate(String kodNegeri, KodCawangan caw, Object obj) {
		if (!(obj instanceof PermitSahLaku)){
			throw new IllegalArgumentException("Parameter is not PermitSahLaku class");
		}
		
		String cawangan = caw.getKod();

        NumberFormat df = NumberFormat.getInstance();
        df.setMaximumIntegerDigits(12);
        df.setMinimumIntegerDigits(1);
        df.setGroupingUsed(false);

        String id = df.format(getSerialNo("SEQ_PERMIT_SAH_LAKU"));
        LOG.info("id : " + id);
        if (IS_DEBUG) {
            LOG.debug(id);
        }
        return id;
    }
    
}
