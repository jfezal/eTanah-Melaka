package etanah;

import org.apache.log4j.Logger;

/**
 * Utility class to get the SYSLOG Logger.
 * @author hishammk
 *
 */
public class SYSLOG {
	
	private static final Logger LOG = Logger.getLogger("SYSLOG");
	
	public static Logger getLogger(){
		return LOG;
	}

}
