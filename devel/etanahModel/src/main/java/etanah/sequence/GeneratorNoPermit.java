/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.sequence;

import etanah.model.KodCawangan;
import etanah.model.Permit;
import java.text.NumberFormat;
import org.apache.log4j.Logger;
/**
 *
 * @author afham
 */
public class GeneratorNoPermit extends SequenceGenerator {

    @Override
	public String generate(String kodNegeri, KodCawangan caw, Object obj) {
		if (!(obj instanceof Permit)){
			throw new IllegalArgumentException("Parameter is not Permit class");
		}
		
		String cawangan = caw.getKod();

        NumberFormat df = NumberFormat.getInstance();
        df.setMaximumIntegerDigits(12);
        df.setMinimumIntegerDigits(1);
        df.setGroupingUsed(false);

        String id = df.format(getSerialNo("SEQ_PERMIT"));
        LOG.info("id : " + id);
        if (IS_DEBUG) {
            LOG.debug(id);
        }
        return id;
    }
    
}
