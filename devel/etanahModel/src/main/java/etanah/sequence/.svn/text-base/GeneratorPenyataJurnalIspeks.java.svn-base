/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.sequence;

import etanah.model.KodCawangan;
import static etanah.sequence.SequenceGenerator.IS_DEBUG;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author mohd.faidzal
 */
public class GeneratorPenyataJurnalIspeks extends SequenceGenerator {

    SimpleDateFormat year = new SimpleDateFormat("yy");
    SimpleDateFormat month = new SimpleDateFormat("MM");

    @Override
    public String generate(String kodNegeri, KodCawangan caw, Object obj) {
        NumberFormat df = NumberFormat.getInstance();
        df.setMaximumIntegerDigits(4);
        df.setMinimumIntegerDigits(4);
        df.setGroupingUsed(false);
        String id = "";
        long a = 0;
        if (caw.getKod().equals("00")) {
            a= getSerialNo("seq_no_jurnal_" + caw.getKod());
            id = "PTGJ" + year.format(new Date()) + df.format(a);
        } else if (caw.getKod().equals("01")) {
            a= getSerialNo("seq_no_jurnal_" + caw.getKod());
            id = "MTJ01" + year.format(new Date()) + df.format(a);
        } else if (caw.getKod().equals("02")) {
            a= getSerialNo("seq_no_jurnal_" + caw.getKod());
            //            P0402202101010001
            id = "J" + "02" + year.format(new Date()) + month.format(new Date()) + df.format(a);
        } else if (caw.getKod().equals("03")) {
            a= getSerialNo("seq_no_jurnal_" + caw.getKod());
//AGP0403202100001
            id = "AGJ03" + year.format(new Date()) + df.format(a);
        } else if (caw.getKod().equals("04")) {
            a= getSerialNo("seq_no_jurnal_" + caw.getKod());
            id = "UTCJ01" + year.format(new Date()) + df.format(a);
        }
        if (IS_DEBUG) {
            LOG.debug(id);
        }
        return id;
    }

}
