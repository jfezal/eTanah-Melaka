/**
 * Action bean for Pertanyaan Deposit Tidak Dituntut
 * 
 * @author Mohd Hairudin Mansur
 * @version 1.0 21 Dec 2009
 */

package etanah.view.stripes.hasil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import etanah.model.Akaun;
import etanah.model.KodCawangan;
import etanah.model.Transaksi;
import etanah.view.etanahActionBeanContext;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

@UrlBinding("/hasil/pertanyaan_deposit_tidak_dituntut")
public class PertanyaanDepositTidakDituntutActionBean extends
		AkaunBaseActionBean {
	
	private String noAkaunDeposit;
	private String tarikhDari;
	private String tarikhHingga;
	private List<Akaun> listDepositTidakDituntut;
	private List<Transaksi> listTransaksiDeposit;
	private boolean showDisplayTable;
	private static final String DEFAULT_VIEW = "/WEB-INF/jsp/hasil/pertanyaan_deposit_tidak_dituntut.jsp";
	private static final Logger LOG = Logger.getLogger(PertanyaanDepositTidakDituntutActionBean.class);
	private static final boolean IS_LOG_DEBUG = LOG.isDebugEnabled();
	
	@DefaultHandler
	public Resolution showForm() {
		showDisplayTable = false;
		return new ForwardResolution(DEFAULT_VIEW);
	}
	
	/**
	 * Search all Deposit Tidak Dituntut
	 * 
	 * @return Resolution
	 * @author Mohd Hairudin Mansur
	 * @version 1.0 21 Dec 2009
	 */
	public Resolution searchDepositTidakDituntut() {
		listDepositTidakDituntut = new ArrayList<Akaun>();
		showDisplayTable = true;
		
		// get the daerah info - kod cawangan
    	etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
    	KodCawangan caw = ctx.getKodCawangan();
    	
    	listDepositTidakDituntut = akaunService.findAkaunDepositTidakDituntut(caw.getKod(), noAkaunDeposit, tarikhDari, tarikhHingga);
    	addTransaksiIntoList();
		
		return new ForwardResolution(DEFAULT_VIEW);
	}
	
	/**
	 * Find all Transaksi records from the the given Akaun Deposit Tidak Dituntut
	 * 
	 * @author Mohd Hairudin Mansur
	 * @version 1.0 29 Dec 2009
	 */
	private void addTransaksiIntoList() {
		listTransaksiDeposit = new ArrayList<Transaksi>();
		for(Iterator<Akaun> listDepositIter = listDepositTidakDituntut.iterator(); listDepositIter.hasNext();) {
			Akaun depositObj = (Akaun)listDepositIter.next();
			List<Transaksi> tmpList = depositObj.getSemuaTransaksi();
			
			if(tmpList.size() > 0) {
				listTransaksiDeposit.addAll(tmpList);
			}
		}
	}

	/**
	 * @param noAkaunDeposit the noAkaunDeposit to set
	 */
	public void setNoAkaunDeposit(String noAkaunDeposit) {
		this.noAkaunDeposit = noAkaunDeposit;
	}

	/**
	 * @return the noAkaunDeposit
	 */
	public String getNoAkaunDeposit() {
		return noAkaunDeposit;
	}

	/**
	 * @param tarikhDari the tarikhDari to set
	 */
	public void setTarikhDari(String tarikhDari) {
		this.tarikhDari = tarikhDari;
	}

	/**
	 * @return the tarikhDari
	 */
	public String getTarikhDari() {
		return tarikhDari;
	}

	/**
	 * @param tarikhHingga the tarikhHingga to set
	 */
	public void setTarikhHingga(String tarikhHingga) {
		this.tarikhHingga = tarikhHingga;
	}

	/**
	 * @return the tarikhHingga
	 */
	public String getTarikhHingga() {
		return tarikhHingga;
	}

	/**
	 * @param listDepositTidakDituntut the listDepositTidakDituntut to set
	 */
	public void setListDepositTidakDituntut(List<Akaun> listDepositTidakDituntut) {
		this.listDepositTidakDituntut = listDepositTidakDituntut;
	}

	/**
	 * @return the listDepositTidakDituntut
	 */
	public List<Akaun> getListDepositTidakDituntut() {
		return listDepositTidakDituntut;
	}

	/**
	 * @param showDisplayTable the showDisplayTable to set
	 */
	public void setShowDisplayTable(boolean showDisplayTable) {
		this.showDisplayTable = showDisplayTable;
	}

	/**
	 * @return the showDisplayTable
	 */
	public boolean getShowDisplayTable() {
		return showDisplayTable;
	}

	/**
	 * @param listTransaksiDeposit the listTransaksiDeposit to set
	 */
	public void setListTransaksiDeposit(List<Transaksi> listTransaksiDeposit) {
		this.listTransaksiDeposit = listTransaksiDeposit;
	}

	/**
	 * @return the listTransaksiDeposit
	 */
	public List<Transaksi> getListTransaksiDeposit() {
		return listTransaksiDeposit;
	}
	
}
