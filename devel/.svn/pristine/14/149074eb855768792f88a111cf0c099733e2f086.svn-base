/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.sequence;

import etanah.model.KodCawangan;
import static etanah.sequence.SequenceGenerator.IS_DEBUG;
import java.text.NumberFormat;
import java.util.Date;

/**
 *
 * @author mohd.faidzal
 */
public class GeneratorNoPenyataIspeks extends SequenceGenerator{
	public final static String SEQ_NAME_PRE = "seq_ispeks_";
        
    @Override
    public String generate(String kodNegeri, KodCawangan caw, Object obj) {
        NumberFormat df = NumberFormat.getInstance();
        df.setMaximumIntegerDigits(6);
        df.setMinimumIntegerDigits(6);
        df.setGroupingUsed(false);

        String id = caw.getKod() + ((new Date()).getYear() + 1900) + df.format(getSerialNo(SEQ_NAME_PRE + caw.getKod()));
        if (IS_DEBUG) {
            LOG.debug(id);
        }

        return id;    }
    
}
