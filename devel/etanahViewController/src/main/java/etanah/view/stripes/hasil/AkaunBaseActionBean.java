/**
 * Base action bean for Akaun.
 * 
 * @author Mohd Hairudin Mansur
 * @version 1.0 16 Dec 2009
 */

package etanah.view.stripes.hasil;

import com.google.inject.Inject;

import etanah.dao.AkaunDAO;
import etanah.model.Akaun;
import etanah.service.AkaunService;
import able.stripes.AbleActionBean;

public class AkaunBaseActionBean extends AbleActionBean {
	
	private Akaun akaun;
	private String akaunID;
	
	@Inject
	AkaunDAO akaunDAO;
	
	@Inject
	AkaunService akaunService;
	
	
	
	/**
	 * @param akaun the akaun to set
	 */
	public void setAkaun(Akaun akaun) {
		this.akaun = akaun;
	}
	
	/**
	 * @return the akaun
	 */
	public Akaun getAkaun() {
		if(akaunID != null) {
			return akaunDAO.findById(akaunID);
		}
		
		return akaun;
	}
	
	/**
	 * @param akaunID the akaunID to set
	 */
	public void setAkaunID(String akaunID) {
		this.akaunID = akaunID;
	}

	/**
	 * @return the akaunID
	 */
	public String getAkaunID() {
		return akaunID;
	}
	
}
