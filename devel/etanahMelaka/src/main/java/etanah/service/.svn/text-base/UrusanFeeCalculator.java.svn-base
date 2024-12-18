package etanah.service;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;

/**
 * Urusan Fees are based on State Land Rule.
 * @author hishammk
 *
 */
public class UrusanFeeCalculator {
	
	private final static Logger LOG = Logger.getLogger(UrusanFeeCalculator.class);
	private final static boolean debug = LOG.isDebugEnabled();
	
	public static final BigDecimal TEN_THOUSAND = new BigDecimal(10000);
	
	/**
	 * Calculate the fee of urusan PMT based on the parameters.
	 * @param warganegara
	 * @param nilaiTransaksi
	 * @return
	 */
	public BigDecimal calculatePMTFee(boolean warganegara, BigDecimal nilaiTransaksi){
		if (nilaiTransaksi == null)
			throw new java.lang.IllegalArgumentException("Parameter nilaiTransaksi must be passed " +
					"to calculate fee");
		BigDecimal fee = null;
		
		if (nilaiTransaksi.compareTo(new BigDecimal(50000)) <= 0){
			fee = new BigDecimal(50.0);
		} else 
			fee = nilaiTransaksi.multiply(new BigDecimal(0.001)); // 0.1%			
		
		return fee.setScale(2, BigDecimal.ROUND_HALF_UP).setScale(0, BigDecimal.ROUND_UP);
	}
	
	/**
	 * Calculate the fine for PMT if late to register. According to Seksyen 239.
	 * @param tarikhSaksi
	 * @return
	 */
	public BigDecimal calculatePMTFine(Date tarikhSaksi, BigDecimal fee){
		Calendar today = Calendar.getInstance();
		if (debug) LOG.debug(today.get(Calendar.DAY_OF_MONTH) + "/" +
				today.get(Calendar.MONTH) + "/" +
				today.get(Calendar.YEAR));
		
		// check if within the first 3 months
		Calendar deadline = Calendar.getInstance(); // GET THE DEFAULT TIMEZONE
		deadline.setTime(tarikhSaksi);
		deadline.set(Calendar.HOUR_OF_DAY, 0);
		deadline.set(Calendar.MINUTE, 0);
		deadline.set(Calendar.SECOND, 0);
		deadline.add(Calendar.MONTH, 3);
		if (debug) LOG.debug(deadline.get(Calendar.DAY_OF_MONTH) + "/" +
				deadline.get(Calendar.MONTH) + "/" +
				deadline.get(Calendar.YEAR));
		
		if (today.before(deadline)){
			if (debug) LOG.debug("No fine since before 3 months");
			return BigDecimal.ZERO;
		}
		
		// calculate for every 3 months
		BigDecimal fine = null; 
		for (int i = 1; i < 5; i++){
			deadline.add(Calendar.MONTH, 3);
			if (debug) LOG.debug(deadline.get(Calendar.DAY_OF_MONTH) + "/" +
					deadline.get(Calendar.MONTH) + "/" +
					deadline.get(Calendar.YEAR));
			if (today.before(deadline)){
				return fine = fee.multiply(new BigDecimal(i));
			}
		}

		// maximum fine
		return fine = fee.multiply(new BigDecimal(5));
	}
	
	public BigDecimal calculateLelongFee(boolean warganegara, BigDecimal nilaiTransaksi){
		if (nilaiTransaksi == null)
			throw new java.lang.IllegalArgumentException("Parameter nilaiTransaksi must be passed " +
					"to calculate fee");
		BigDecimal fee = null;
		
		if (nilaiTransaksi.compareTo(new BigDecimal(50000)) <= 0){
			fee = new BigDecimal(50.0);
		} else{
			fee = nilaiTransaksi.multiply(new BigDecimal(0.001)); // 0.1%
			// max is 10K
			if (fee.compareTo(TEN_THOUSAND) > 0){
				fee = TEN_THOUSAND;
			}
		}
		
		return fee.setScale(2, BigDecimal.ROUND_HALF_UP).setScale(0, BigDecimal.ROUND_UP);
	}


}
