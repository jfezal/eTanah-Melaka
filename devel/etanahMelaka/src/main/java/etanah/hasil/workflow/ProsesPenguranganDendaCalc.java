/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.hasil.workflow;

import java.math.BigDecimal;
import org.apache.log4j.Logger;

/**
 *
 * @author abu.mansur
 */
public class ProsesPenguranganDendaCalc {
    private static final Logger logger = Logger.getLogger(ProsesPenguranganDendaCalc.class);

    public BigDecimal calc(BigDecimal amaun, BigDecimal nilaiKeputusan){
        logger.info("ProsesPenguranganDendaCalc::amaun = "+amaun);
        logger.info("ProsesPenguranganDendaCalc::nilaiKeputusan = "+nilaiKeputusan);
        BigDecimal rtnDenda = BigDecimal.ZERO;
        rtnDenda = amaun.multiply(nilaiKeputusan);
        logger.debug("ProsesPenguranganDendaCalc::rtnDenda = "+rtnDenda);
        rtnDenda = rtnDenda.setScale(1, BigDecimal.ROUND_DOWN);
        logger.debug("ProsesPenguranganDendaCalc::rtnDenda.setScale = "+rtnDenda);
        
        return rtnDenda;
    }

    public BigDecimal calcGandaanDenda(BigDecimal amaun, BigDecimal nilaiKeputusan){
        logger.info("ProsesPenguranganDendaCalc::amaun = "+amaun);
        logger.info("ProsesPenguranganDendaCalc::nilaiKeputusan = "+nilaiKeputusan);
        BigDecimal rtnDenda = BigDecimal.ZERO;
        rtnDenda = amaun.multiply(nilaiKeputusan);
        logger.debug("ProsesPenguranganDendaCalc::rtnDenda = "+rtnDenda);

        return rtnDenda;
    }

}
