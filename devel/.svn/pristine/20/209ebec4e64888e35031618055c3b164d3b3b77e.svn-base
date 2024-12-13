package etanah.util;

import java.math.BigDecimal;

import com.google.inject.Singleton;

@Singleton
public class MonetaryUtil {
	
	public BigDecimal roundToRinggit(BigDecimal d){
		return d.setScale(2, BigDecimal.ROUND_HALF_UP).setScale(0, BigDecimal.ROUND_UP);
	}

}
