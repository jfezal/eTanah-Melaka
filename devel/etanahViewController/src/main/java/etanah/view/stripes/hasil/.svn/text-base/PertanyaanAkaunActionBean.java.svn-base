/**
 * Action bean for Pertanyaan Akaun Amanah and Akaun Deposit
 * 
 * @author Mohd Hairudin Mansur
 * @version 1.0 2 December 2009
 */

package etanah.view.stripes.hasil;

import com.google.inject.Inject;
import etanah.dao.KodBandarPekanMukimDAO;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import java.util.Iterator;

import etanah.model.Akaun;
import etanah.model.KodBandarPekanMukim;
import etanah.model.KodCawangan;
import etanah.model.Pengguna;
import etanah.model.Transaksi;
import etanah.report.ReportUtil;
import etanah.view.etanahActionBeanContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.DontValidate;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.validation.SimpleError;
import net.sourceforge.stripes.validation.ValidationErrors;
import net.sourceforge.stripes.validation.ValidationMethod;

import org.apache.log4j.Logger;

//@Wizard(startEvents = {"showForm","print"})
@UrlBinding("/hasil/pertanyaan_akaun")
public class PertanyaanAkaunActionBean extends AkaunBaseActionBean {
	
	private String kodAkaun;
	private String noAkaun;
	private String idHakmilik;
	private String idPermohonan;
	private String jenisPengenalan;
	private String noPengenalan;
        private String id;
        private String pemegang;
        private String kodBPM;
        private String noLot;
        private String noFail;
	private BigDecimal jumSudahDibayar;
	private BigDecimal bakiBelumDibayar;
	private BigDecimal total;
	private boolean showDisplayTable;
	private boolean showAkaunDetail;
	private List<Akaun> listAkaun;
	private List<Transaksi> listTransaksi;
        private List<KodBandarPekanMukim> senaraiBPM;
	private static final String DEFAULT_VIEW = "/WEB-INF/jsp/hasil/pertanyaan_akaun.jsp";
	private static final String DETAIL_VIEW = "/WEB-INF/jsp/hasil/maklumat_akaun.jsp";
	private static final Logger LOG = Logger.getLogger(PertanyaanAkaunActionBean.class);
	private static final boolean IS_LOG_DEBUG = LOG.isDebugEnabled();
	

        @Inject
        private ReportUtil reportUtil;
        @Inject
        KodBandarPekanMukimDAO  kodBPMDAO;

	@DefaultHandler
	public Resolution showForm() {
		showDisplayTable = false;
		jumSudahDibayar = new BigDecimal("0.00");
		bakiBelumDibayar = new BigDecimal("0.00");
		getSenaraiBPMfilterDaerah();
		return new ForwardResolution(DEFAULT_VIEW);
	}

        private void getSenaraiBPMfilterDaerah(){
            senaraiBPM = new ArrayList<KodBandarPekanMukim>();
            etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
            KodCawangan caw = ctx.getKodCawangan();
            String[] name = {"daerah"};
            Object[] value= {caw.getDaerah()};
            senaraiBPM = kodBPMDAO.findByEqualCriterias(name, value, "nama");
            LOG.debug("senaraiBPM.size :"+senaraiBPM.size());
        }
	
	/**
	 * Search akaun by criteria(s)
	 * 
	 * @return Resolution
	 * @author Mohd Hairudin Mansur
	 * @version 1.0 3 December 2009
	 */
	public Resolution searchAkaun() {

		LOG.info("Carian Akaun: Mula");
		
		listAkaun = new ArrayList<Akaun>();
		
		// get the daerah info - kod cawangan
    	etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
    	KodCawangan caw = ctx.getKodCawangan();
    	
    	listAkaun = akaunService.findAll(caw.getKod(), kodAkaun, noAkaun, idHakmilik, noLot, kodBPM, noFail, idPermohonan, jenisPengenalan, noPengenalan, pemegang);
		showDisplayTable = true;
		
		if(IS_LOG_DEBUG) {
			LOG.debug("Carian akaun: " + getListAkaun().size() + " rekod ditemui.");
		}
				
		LOG.info("Carian Akaun: Tamat");
		
		return new ForwardResolution(DEFAULT_VIEW);
	}

        public Resolution back() {
            getSenaraiBPMfilterDaerah();
            return new ForwardResolution(DEFAULT_VIEW);
        }

        public Resolution print() throws FileNotFoundException {
            etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
            Pengguna p = ctx.getUser();
            id = getContext().getRequest().getParameter("idHakmilik");
            Date now = new Date();
            File f = null;
            String tarikh = (new SimpleDateFormat("ddMMyyyyhhmm")).format(now);
            String documentPath = File.separator + "tmp" + File.separator;
            String path = tarikh + File.separator + String.valueOf(id);
            reportUtil.generateReport("HSLMaklumatPemegangAkaun.rdf",
                    new String[]{"p_id_hakmilik"},
                    new String[]{id},
                    documentPath + path, null);

            f = new File(documentPath + path);
            FileInputStream fis = new FileInputStream(f);
            return new StreamingResolution("application/pdf", fis);

        }
	
	/**
	 * View Akaun detail
	 * 
	 * @return Resolution
	 * @author Mohd Hairudin Mansur
	 * @version 1.0 17 Dec 2009
	 */
	public Resolution akaunDetail() {
		showAkaunDetail = true;
                total = new BigDecimal(0);
		setAkaun(akaunService.findById(getAkaunID()));
                listTransaksi = new ArrayList<Transaksi>();
		List<Transaksi>listT = getAkaun().getSemuaTransaksi();
                for (Transaksi tr : listT) {
                    if(tr.getAkaunDebit() != null && tr.getAkaunDebit().getNoAkaun().equals(getAkaun().getNoAkaun())){
                        tr.setAmaun(tr.getAmaun().multiply(new BigDecimal(-1)));
                    }
                    if(tr.getStatus().getKod()!='B'){
                        total = total.add(tr.getAmaun());
                    }
                    listTransaksi.add(tr);
                }
		calculateJumSudahDibayar();
		calculateBakiBelumDibayar();

                Akaun ak = akaunService.findById(getAkaunID());
                if(ak.getHakmilik() != null)
                    id = ak.getHakmilik().getIdHakmilik();
		
		return new ForwardResolution(DETAIL_VIEW);
	}
	
	/**
	 * Redirect to another page to add new Akaun record
	 * 
	 * @return Resolution
	 * @author Mohd Hairudin Mansur
	 * @version 1.0 8 Jan 2010
	 */
	@DontValidate
	public Resolution addNewAkaun() {
		return new RedirectResolution(KemasukanMaklumatAkaunActionBean.class);
	}
	
	/**
	 * Calculate total amount for transactions paid for the given Akaun Amanah
	 * 
	 * @author Mohd Hairudin Mansur
	 * @version 1.0 27 Dec 2009
	 */
	private void calculateJumSudahDibayar() {
		BigDecimal jum = new BigDecimal("0.00");
		
		for(Iterator<Transaksi> listTransIter = listTransaksi.iterator(); listTransIter.hasNext();) {
			Transaksi transObj = listTransIter.next();
			jum = jum.add(transObj.getAmaun());
		}
		
		setJumSudahDibayar(jum);
	}
	
	/**
	 * Calculate the balance needs to be paid for the Akaun Amanah
	 * 
	 * @author Mohd Hairudin Mansur
	 * @version 1.0 29 Dec 2009
	 */
	private void calculateBakiBelumDibayar() {
		// get the daerah info - kod cawangan
    	etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
    	KodCawangan caw = ctx.getKodCawangan();
            if(getAkaun().getHakmilik() != null){
		Akaun akaunCukai = akaunService.getAkaunCukaiForHakmilik(caw.getKod(), getAkaun().getHakmilik().getIdHakmilik());
		
		if(akaunCukai == null) {
			showAkaunDetail = false;
			addSimpleError("Akaun Cukai Tidak Ditemui Dalam Rekod");
		}
		else {
			BigDecimal baki = akaunCukai.getBaki().subtract(getJumSudahDibayar());
			setBakiBelumDibayar(baki);
		}
            }
	}
	
	/**
	 * Verify user input No. Pengenalan
	 * 
	 * @author Mohd Hairudin Mansur
	 * @return true/false
	 * @version 1.1 23 Dec 2009
	 */
//	@ValidationMethod(on = "searchAkaun")
//	public void verifyNoPengenalan(ValidationErrors errors) {
//		String pengenalanTidakBerkenaan = "0";
//
//		if(jenisPengenalan.equals(pengenalanTidakBerkenaan) && noPengenalan == null) {
//			errors.add("noPengenalan", new SimpleError("Sila Masukkan No. Pengenalan"));
//		}
//	}
	
	/**
	 * Verify user input Jenis Pengenalan
	 * 
	 * @return true/false
	 * @author Mohd Hairudin Mansur
	 * @version 1.1 23 Dec 2009
	 */
//	@ValidationMethod(on = "searchAkaun")
//	public void verifyJenisPengenalan(ValidationErrors errors) {
//		String pengenalanTidakBerkenaan = "0";
//
//		if(noPengenalan != null && jenisPengenalan.equals(pengenalanTidakBerkenaan)) {
//			errors.add("noPengenalan", new SimpleError("Sila Pilih Jenis Pengenalan Yang Betul"));
//		}
//	}

        /**
	 * Verify user input Jenis Pengenalan
	 *
	 * @return true/false
	 * @author Mohd Hairudin Mansur
	 * @version 1.1 23 Dec 2009
	 */
	@ValidationMethod(on = "searchAkaun")
	public void verifyKodAkaun(ValidationErrors errors) {
		String jenisAkaun = "0";

		if(kodAkaun.equals(jenisAkaun)) {
			errors.add("noPengenalan", new SimpleError("Sila Pilih Jenis Pengenalan Yang Betul"));
		}
	}
	
	/**
	 * @param listAkaun the listAkaun to set
	 */
	public void setListAkaun(List<Akaun> listAkaun) {
		this.listAkaun = listAkaun;
	}

	/**
	 * @return the listAkaun
	 */
	public List<Akaun> getListAkaun() {
		return listAkaun;
	}

	/**
	 * @param kodAkaun the kodAkaun to set
	 */
//	@Validate(required=true, expression="this != '0'", on="searchAkaun")
	public void setKodAkaun(String kodAkaun) {
		this.kodAkaun = kodAkaun;
	}

	/**
	 * @return the kodAkaun
	 */
	public String getKodAkaun() {
		return kodAkaun;
	}
	
	/**
	 * @param noAkaun the noAkaun to set
	 */
	public void setNoAkaun(String noAkaun) {
		this.noAkaun = noAkaun;
	}

	/**
	 * @return the noAkaun
	 */
	public String getNoAkaun() {
		return noAkaun;
	}

	/**
	 * @param idHakmilik the idHakmilik to set
	 */
	public void setIdHakmilik(String idHakmilik) {
		this.idHakmilik = idHakmilik;
	}

	/**
	 * @return the idHakmilik
	 */
	public String getIdHakmilik() {
		return idHakmilik;
	}

	/**
	 * @param idPermohonan the idPermohonan to set
	 */
	public void setIdPermohonan(String idPermohonan) {
		this.idPermohonan = idPermohonan;
	}

	/**
	 * @return the idPermohonan
	 */
	public String getIdPermohonan() {
		return idPermohonan;
	}

	/**
	 * @param jenisPengenalan the jenisPengenalan to set
	 */
//	@Validate(required=true, expression="this != '0'", on="searchAkaun")
	public void setJenisPengenalan(String jenisPengenalan) {
		this.jenisPengenalan = jenisPengenalan;
	}

	/**
	 * @return the jenisPengenalan
	 */
	public String getJenisPengenalan() {
		return jenisPengenalan;
	}

	/**
	 * @param noPengenalan the noPengenalan to set
	 */
//	@Validate(required=true, on="searchAkaun")
	public void setNoPengenalan(String noPengenalan) {
		this.noPengenalan = noPengenalan;
	}

	/**
	 * @return the noPengenalan
	 */
	public String getNoPengenalan() {
		return noPengenalan;
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
	public boolean isShowDisplayTable() {
		return showDisplayTable;
	}

	/**
	 * @param listTransaksi the listTransaksi to set
	 */
	public void setListTransaksi(List<Transaksi> listTransaksi) {
		this.listTransaksi = listTransaksi;
	}

	/**
	 * @return the listTransaksi
	 */
	public List<Transaksi> getListTransaksi() {
		return listTransaksi;
	}

	/**
	 * @param jumSudahDibayar the jumSudahDibayar to set
	 */
	public void setJumSudahDibayar(BigDecimal jumSudahDibayar) {
		this.jumSudahDibayar = jumSudahDibayar;
	}

	/**
	 * @return the jumSudahDibayar
	 */
	public BigDecimal getJumSudahDibayar() {
		return jumSudahDibayar;
	}

	/**
	 * @param bakiBelumDibayar the bakiBelumDibayar to set
	 */
	public void setBakiBelumDibayar(BigDecimal bakiBelumDibayar) {
		this.bakiBelumDibayar = bakiBelumDibayar;
	}

	/**
	 * @return the bakiBelumDibayar
	 */
	public BigDecimal getBakiBelumDibayar() {
		return bakiBelumDibayar;
	}

	/**
	 * @param showAkaunDetail the showAkaunDetail to set
	 */
	public void setShowAkaunDetail(boolean showAkaunDetail) {
		this.showAkaunDetail = showAkaunDetail;
	}

	/**
	 * @return the showAkaunDetail
	 */
	public boolean isShowAkaunDetail() {
		return showAkaunDetail;
	}

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getPemegang() {
        return pemegang;
    }

    public void setPemegang(String pemegang) {
        this.pemegang = pemegang;
    }

    public String getKodBPM() {
        return kodBPM;
    }

    public void setKodBPM(String kodBPM) {
        this.kodBPM = kodBPM;
    }

    public String getNoFail() {
        return noFail;
    }

    public void setNoFail(String noFail) {
        this.noFail = noFail;
    }

    public String getNoLot() {
        return noLot;
    }

    public void setNoLot(String noLot) {
        this.noLot = noLot;
    }

    public List<KodBandarPekanMukim> getSenaraiBPM() {
        return senaraiBPM;
    }

    public void setSenaraiBPM(List<KodBandarPekanMukim> senaraiBPM) {
        this.senaraiBPM = senaraiBPM;
    }
}
