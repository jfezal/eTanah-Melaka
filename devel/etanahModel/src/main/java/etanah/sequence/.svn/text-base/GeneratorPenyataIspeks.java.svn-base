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
public class GeneratorPenyataIspeks extends SequenceGenerator {

    SimpleDateFormat year = new SimpleDateFormat("yy");
    SimpleDateFormat month = new SimpleDateFormat("MM");

    @Override
    public String generate(String kodNegeri, KodCawangan caw, Object obj) {
        NumberFormat df = NumberFormat.getInstance();
        df.setMaximumIntegerDigits(4);
        df.setMinimumIntegerDigits(4);
        df.setGroupingUsed(false);
        String id = "";
        if (caw.getKod().equals("00")) {
            id = "PTGP" + year.format(new Date()) + df.format(getSerialNo("seq_no_penyata_" + caw.getKod()));
        } else if (caw.getKod().equals("01")) {
            id = "MTP01" + year.format(new Date()) + df.format(getSerialNo("seq_no_penyata_" + caw.getKod()));
        } else if (caw.getKod().equals("02")) {
            //            P0402202101010001
            id = "P" + "02" + year.format(new Date()) + month.format(new Date()) + df.format(getSerialNo("seq_no_penyata_" + caw.getKod()));
        } else if (caw.getKod().equals("03")) {
//AGP0403202100001
            id = "AGP03" + year.format(new Date()) + df.format(getSerialNo("seq_no_penyata_" + caw.getKod()));
        } else if (caw.getKod().equals("04")) {
            id = "UTCP01" + year.format(new Date()) + df.format(getSerialNo("seq_no_penyata_" + caw.getKod()));
        }
        if (IS_DEBUG) {
            LOG.debug(id);
        }
        return id;
    }

}
