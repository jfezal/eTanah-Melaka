/**
 * Action bean for Kemasukan Maklumat Akaun Amanah dan Deposit
 * 
 * @author Mohd Hairudin Mansur
 * @version 1.0 16 Dec 2009
 */

package etanah.view.stripes.hasil;

import java.util.Date;
import java.util.List;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.validation.SimpleError;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;
import net.sourceforge.stripes.validation.ValidationErrors;
import net.sourceforge.stripes.validation.ValidationMethod;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import able.stripes.JSP;

import com.google.inject.Inject;

import etanah.dao.HakmilikDAO;
import etanah.dao.KodStatusTransaksiKewanganDAO;
import etanah.dao.KodTransaksiDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PihakDAO;
import etanah.model.Akaun;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import etanah.model.Pengguna;
import etanah.model.Pihak;
import etanah.model.PihakCawangan;
import etanah.model.Transaksi;
import etanah.sequence.GeneratorNoAkaun;
import etanah.service.common.PihakService;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import net.sourceforge.stripes.action.DontValidate;
import org.hibernate.Query;

@UrlBinding("/hasil/kemasukan_maklumat_akaun")
public class KemasukanMaklumatAkaunActionBean extends AkaunBaseActionBean {
	
	private String jenisPengenalan=null;
	private String noPengenalan=null;
        private String namaPemegang=null;
        private String flag = "";
	private Pihak pemegangAkaun;
	private PihakCawangan pihakCawangan;
	private List<PihakCawangan> cawanganList;
	private boolean showCarianPemegangAkaun;
	private boolean showSearchForm;
	private boolean showTambahCawangan;
	private static final String DEFAULT_VIEW = "/WEB-INF/jsp/hasil/kemasukan_maklumat_akaun.jsp";
	private static final String POPUP_VIEW = "hasil/kemasukan_pemegang_akaun.jsp";
	private static final Logger LOG = Logger.getLogger(KemasukanMaklumatAkaunActionBean.class);
	private static boolean IS_LOG_DEBUG = LOG.isDebugEnabled();
        SimpleDateFormat yy = new SimpleDateFormat("yyyy");
	
	@Inject
	HakmilikDAO hakmilikDAO;
	
	@Inject
	PermohonanDAO permohonanDAO;
	
	@Inject
	PihakDAO pihakDAO;
	
	@Inject
	PihakService pihakService;
	
	@Inject
	GeneratorNoAkaun noAkaunGenerator;

        @Inject
        KodTransaksiDAO kodTransaksiDAO;
	
	@Inject
        protected com.google.inject.Provider<Session> sessionProvider;

        @Inject
        private etanah.kodHasilConfig hasil;

        @Inject
        KutipanHasilManager manager;

        @Inject
        private KodStatusTransaksiKewanganDAO kodStatusTransaksiKewanganDAO;
	
	@DefaultHandler
	public Resolution showForm() {
		return new ForwardResolution(DEFAULT_VIEW);
	}
	
	/**
	 * Save Akaun information record
	 * 
	 * @return Resolution
	 * @author Mohd Hairudin Mansur
	 * @version 1.0 16 Dec 2009
	 */
       public Resolution simpanMaklumatAkaun() {
            LOG.info("Saving Akaun Amanah akaun: Mula");

            // get the daerah info - kod cawangan
            etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
            KodCawangan caw = ctx.getKodCawangan();

            Akaun akaunRecord = getAkaun();
            akaunRecord.setNoAkaun(noAkaunGenerator.generate(ctx.getKodNegeri(), caw, akaunRecord));
//            akaunRecord.setNoAkaun(getAkaun().getKodAkaun().getNama()+getAkaun().getNoAkaun());
            akaunRecord.setCawangan(caw);

            // check to make sure idPihak is set
            if (akaunRecord.getPemegang().getIdPihak() == 0) {
                akaunRecord.setPemegang(retrievePemegangAkaun());
            }

            if (IS_LOG_DEBUG) {
                LOG.debug("Generated No. Akaun: " + akaunRecord.getNoAkaun());
            }

            // info audit object
            InfoAudit infoAudit = new InfoAudit();
            infoAudit.setDimasukOleh(ctx.getUser());
            infoAudit.setTarikhMasuk(new Date());

            Pengguna p = ctx.getUser();

            BigDecimal baki = getAkaun().getBaki().multiply(new BigDecimal(-1));

            akaunRecord.setInfoAudit(infoAudit);
            akaunRecord.setAmaunMatang(BigDecimal.ZERO);
            akaunRecord.setBaki(baki);
            akaunRecord.setCatatan(getAkaun().getCatatan());

            akaunService.saveOrUpdate(akaunRecord);

            createTransaksi(akaunRecord, caw, infoAudit, p);
            LOG.info("akaunRecord.getNoAkaun() : "+akaunRecord.getNoAkaun());
//            addSimpleMessage("Maklumat Akaun Telah Berjaya Disimpan. Nombor Akaun "+getAkaun().getKodAkaun().getNama()+ " anda ialah : "+akaunRecord.getNoAkaun());
            addSimpleMessage("Maklumat Akaun Telah Berjaya Disimpan. Nombor Akaun anda ialah : "+akaunRecord.getNoAkaun());

            LOG.info("Rekod Akaun Amanah Berjaya Disimpan");

            return new RedirectResolution(KemasukanMaklumatAkaunActionBean.class, "showForm");
        }

       public void createTransaksi(Akaun ak, KodCawangan caw, InfoAudit ia, Pengguna p){
           Transaksi t = new Transaksi();
           Date now = new Date();
           int year = Integer.parseInt(yy.format(now));

           String sql = "SELECT a FROM etanah.model.Akaun a WHERE a.kodAkaun.kod ='AKH' AND a.cawangan.kod =:caw";
            Session s = sessionProvider.get();
            Query q = s.createQuery(sql);
            q.setString("caw", p.getKodCawangan().getKod());
            Akaun akh = (Akaun) q.uniqueResult();
            LOG.info("akh.getNoAkaun() : "+akh.getNoAkaun());

           t.setCawangan(caw);
           t.setKodTransaksi(kodTransaksiDAO.findById(hasil.getProperty("depositN9")));
           t.setUntukTahun(year);
           t.setAkaunKredit(ak);
           t.setAmaun(ak.getBaki());
           t.setStatus(kodStatusTransaksiKewanganDAO.findById('T'));
           t.setTahunKewangan(year);
           t.setInfoAudit(ia);
           t.setAkaunDebit(akh);

           manager.save(t);
       }

       public Resolution checkingNoFile(){
           LOG.info("..:: checkingNoFile ::..");
           LOG.info("no FAIL :" + getContext().getRequest().getParameter("noRuj"));
           String catatan = getContext().getRequest().getParameter("noRuj");
           String results = "0";

           String sql = "SELECT a from etanah.model.Akaun a where a.catatan is not null AND a.catatan = :catatan";
            Session s = sessionProvider.get();
            Query q = s.createQuery(sql);
            q.setString("catatan", catatan);

            Akaun akaun = new Akaun();
            akaun = (Akaun) q.uniqueResult();

            if(akaun != null){
                results = "1";
            }

           return new StreamingResolution("text/plain", results);
       }
	
	/**
	 * Display a new window to search Pemegang Akaun (Pihak) record
	 * 
	 * @return Resolution
	 * @author Mohd Hairudin Mansur
	 * @version 1.0 5 Jan 2010
	 */
	public Resolution showPemegangAkaunPopup() {
		showCarianPemegangAkaun = false;
		showTambahCawangan = false;
		showSearchForm = true;
		
		return new JSP(POPUP_VIEW).addParameter("popup", "true");
	}

        @DontValidate
	public Resolution kembali() {
		return new RedirectResolution(PertanyaanAkaunActionBean.class);
	}
	
	/**
	 * Method to search Pemegang Akaun (Pihak) record
	 * 
	 * @return Resolution
	 * @author Mohd Hairudin Mansur
	 * @version 1.0 5 Jan 2010
	 */
       public Resolution searchPemegangAkaun() {
        LOG.info("Mula Cari Pemegang Akaun");

        if (validateSearchPemegangAkaunFields()) {
            if ((!jenisPengenalan.equals("0")) && (noPengenalan == null)) {
                pemegangAkaun = pihakService.findPihak(jenisPengenalan, noPengenalan);

                if (pemegangAkaun == null) {
                    addSimpleError("Tiada Data. Sila Masukkan Maklumat Baru.");
                    showCarianPemegangAkaun = false;
                    showSearchForm = false;
                    showTambahCawangan = false;
                } else {
                    cawanganList = pemegangAkaun.getSenaraiCawangan();
                    showCarianPemegangAkaun = true;
                    showSearchForm = false;
                    showTambahCawangan = false;
                }
            } else {
                List<Pihak> pList = pihakService.findPihakByName(namaPemegang, null);
                if (!pList.isEmpty()) {
                    pemegangAkaun = pList.get(0);

                    if (pemegangAkaun == null) {
                        addSimpleError("Tiada Data. Sila Masukkan Maklumat Baru.");
                        showCarianPemegangAkaun = false;
                        showSearchForm = false;
                        showTambahCawangan = false;
                    } else {
                        cawanganList = pemegangAkaun.getSenaraiCawangan();
                        showCarianPemegangAkaun = true;
                        showSearchForm = false;
                        showTambahCawangan = false;
                    }
                }
            }
        }

        return new JSP(POPUP_VIEW).addParameter("popup", "true");
    }

        public Resolution searchPemegangAkaun1() {
            LOG.info("Mula Cari Pemegang Akaun");

            showCarianPemegangAkaun = false;
            showSearchForm = false;
            showTambahCawangan = false;

            return new JSP(POPUP_VIEW).addParameter("popup", "true");
        }
	
	/**
	 * Save or update Pemegang Akaun (Pihak) record from the Pemegang Akaun Popup window
	 * 
	 * @return Resolution
	 * @author Mohd Hairudin Mansur
	 * @version 1.0 5 Jan 2010
	 */
	public Resolution simpanPemegangAkaunPopup() {
		LOG.info("Simpan / Kemaskini Pemegang Akaun Popup");
		boolean status = true;
		Pihak saveUpdatePemegangAkaun = pihakDAO.findById(pemegangAkaun.getIdPihak());
        
        if (pemegangAkaun.getBangsa() != null) {
            if (pemegangAkaun.getBangsa().toString().length() > 3) {
            	pemegangAkaun.setBangsa(null);
            }
        }
        
        // add new Pemegang Akaun
        if (saveUpdatePemegangAkaun == null) {
        	status = saveNewPemegangAkaun();
        }
        else {		// edit Pemegang Akaun
        	status = updatePemegangAkaun(saveUpdatePemegangAkaun);
        }
        
        if(!status) {
        	return new JSP(POPUP_VIEW).addParameter("popup", "true");
        }
        
		return new StreamingResolution("text/plain", "1");
	}
	
	/**
	 * Save new Pemegang Akaun (Pihak) record
	 * 
	 * @return true/false
	 * @author Mohd Hairudin Mansur
	 * @version 1.0 13 Jan 2010
	 */
	private boolean saveNewPemegangAkaun() {
		etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
		Transaction tx = sessionProvider.get().beginTransaction();
        tx.begin();
        
        try {
        	InfoAudit info = new InfoAudit();
			info.setDimasukOleh(ctx.getUser());
	        info.setTarikhMasuk(new Date());
	        
	        Pihak savePemegangAkaun = new Pihak();
	        savePemegangAkaun.setNama(pemegangAkaun.getNama());
	        savePemegangAkaun.setJenisPengenalan(pemegangAkaun.getJenisPengenalan());
	        savePemegangAkaun.setNoPengenalan(pemegangAkaun.getNoPengenalan());
	        savePemegangAkaun.setKodJantina(pemegangAkaun.getKodJantina());
	        savePemegangAkaun.setBangsa(pemegangAkaun.getBangsa());
	        savePemegangAkaun.setNoTelefon1(pemegangAkaun.getNoTelefon1());
	        savePemegangAkaun.setNoTelefon2(pemegangAkaun.getNoTelefon2());
	        savePemegangAkaun.setNoTelefonBimbit(pemegangAkaun.getNoTelefonBimbit());
	        savePemegangAkaun.setAlamat1(pemegangAkaun.getAlamat1());
	        savePemegangAkaun.setAlamat2(pemegangAkaun.getAlamat2());
	        savePemegangAkaun.setAlamat3(pemegangAkaun.getAlamat3());
	        savePemegangAkaun.setAlamat4(pemegangAkaun.getAlamat4());
	        savePemegangAkaun.setPoskod(pemegangAkaun.getPoskod());
	        savePemegangAkaun.setNegeri(pemegangAkaun.getNegeri());
	        savePemegangAkaun.setSuratAlamat1(pemegangAkaun.getSuratAlamat1());
	        savePemegangAkaun.setSuratAlamat2(pemegangAkaun.getSuratAlamat2());
	        savePemegangAkaun.setSuratAlamat3(pemegangAkaun.getSuratAlamat3());
	        savePemegangAkaun.setSuratAlamat4(pemegangAkaun.getSuratAlamat4());
	        savePemegangAkaun.setSuratPoskod(pemegangAkaun.getSuratPoskod());
	        savePemegangAkaun.setSuratNegeri(pemegangAkaun.getSuratNegeri());
	        savePemegangAkaun.setInfoAudit(info);
        
	        pihakService.saveOrUpdate(savePemegangAkaun);
	        LOG.debug("idPemegang: " + savePemegangAkaun.getIdPihak());
        }
        catch(Exception e) {
        	tx.rollback();
            addSimpleError("Kemasukan Data Gagal. Sila Hubungi Pentadbir Sistem.");
            LOG.error("Kemasukan Data Gagal. ", e);
            return false;
        }
        tx.commit();
        
		return true;
	}
	
	/**
	 * Update Pemegang Akaun (Pihak) record
	 * 
	 * @param updatePemegangAkaun
	 * @return true/false
	 * @author Mohd Hairudin Mansur
	 * @version 1.0 13 Jan 2010
	 */
	private boolean updatePemegangAkaun(Pihak updatePemegangAkaun) {
		etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
		Transaction tx = sessionProvider.get().beginTransaction();
        tx.begin();
        
        try {
        	InfoAudit info = new InfoAudit();
        	info.setDiKemaskiniOleh(ctx.getUser());
        	info.setTarikhKemaskini(new Date());
        	
        	updatePemegangAkaun.setSuratAlamat1(pemegangAkaun.getSuratAlamat1());
            updatePemegangAkaun.setSuratAlamat2(pemegangAkaun.getSuratAlamat2());
            updatePemegangAkaun.setSuratAlamat3(pemegangAkaun.getSuratAlamat3());
            updatePemegangAkaun.setSuratAlamat4(pemegangAkaun.getSuratAlamat4());
            updatePemegangAkaun.setSuratPoskod(pemegangAkaun.getSuratPoskod());
            updatePemegangAkaun.setSuratNegeri(pemegangAkaun.getSuratNegeri());
            updatePemegangAkaun.setInfoAudit(info);
            
            pihakService.saveOrUpdate(updatePemegangAkaun);
        }
        catch(Exception e) {
        	tx.rollback();
            addSimpleError("Kemaskini Data Gagal. Sila Hubungi Pentadbir Sistem.");
            LOG.error("Kemaskini Data Gagal. ", e);
            return false;
        }
        tx.commit();
        
        LOG.debug("idPemegang: " + updatePemegangAkaun.getIdPihak());
		
        return true;
	}
	
	/**
	 * Show Tambah Cawangan
	 * 
	 * @return Resolution
	 * @author Mohd Hairudin Mansur
	 * @version 1.0 10 Jan 2010
	 */
	public Resolution tambahCawangan() {
		if(pemegangAkaun.getNama() == null || 
				pemegangAkaun.getJenisPengenalan().getKod() == null ||
				pemegangAkaun.getNoPengenalan() == null) {
			addSimpleError("Sila Masukkan Maklumat Pada Medan Bertanda *");
			showCarianPemegangAkaun = false;
			showSearchForm = false;
			showTambahCawangan = false;
		}
		else {
			if(!(pihakDAO.exists(pemegangAkaun.getIdPihak()))) {
				etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
				Transaction tx = sessionProvider.get().getTransaction();
                tx.begin();
                
                try {
                	if(pemegangAkaun.getBangsa() != null && 
                			pemegangAkaun.getBangsa().getKod().toString().length() > 4) {
                		pemegangAkaun.setBangsa(null);
                	}
                	
                	InfoAudit info = new InfoAudit();
                	info.setDimasukOleh(ctx.getUser());
                	info.setTarikhMasuk(new Date());
                	pemegangAkaun.setInfoAudit(info);
                	
                	pihakService.saveOrUpdate(pemegangAkaun);
                }
                catch(Exception e) {
                	tx.rollback();
                    addSimpleError("Kemasukan Data Gagal. Sila Hubungi Pentadbir Sistem.");
                    LOG.error("Kemasukan Data Gagal. ", e);
                }
                tx.commit();
			}
			
			showCarianPemegangAkaun = false;
			showSearchForm = false;
			showTambahCawangan = true;
		}
		
		return new JSP(POPUP_VIEW).addParameter("popup", "true");
	}
	
	/**
	 * Save Cawangan for Pemegang Akaun record
	 * 
	 * @return Resolution
	 * @author Mohd Hairudin Mansur
	 * @version 1.0 14 Jan 2010
	 */
	public Resolution simpanCawangan() {
		
		etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
		
		if(pihakCawangan == null) {
			addSimpleError("Sila Masukkan Maklumat Pada Medan Bertanda *");
		}
		else {
			InfoAudit info = new InfoAudit();
	        info.setDimasukOleh(ctx.getUser());
	        info.setTarikhMasuk(new Date());

	        pihakCawangan.setIbupejabat(pemegangAkaun);
	        pihakCawangan.setInfoAudit(info);
	        pihakService.saveOrUpdatePihakCawangan(pihakCawangan);

	        Pihak tempPihak = new Pihak();
	        tempPihak = pihakDAO.findById(pemegangAkaun.getIdPihak());

	        pemegangAkaun = tempPihak;

	        cawanganList = pemegangAkaun.getSenaraiCawangan();

	        showCarianPemegangAkaun = true;
		}
		
        return new JSP(POPUP_VIEW).addParameter("popup", "true");
	}
	
	/**
	 * Retrieve the Pemegang Akaun (Pihak) object given the No. Pengenalan
	 * 
	 * @return Pihak pemegang
	 * @author Mohd Hairudin Mansur
	 * @version 1.0 10 Jan 2010
	 */
	private Pihak retrievePemegangAkaun() {
		String[] name = {"noPengenalan"};
		Object[] value = {getAkaun().getPemegang().getNoPengenalan()};
		List<Pihak> pemegangList = pihakDAO.findByEqualCriterias(name, value, null);
		Pihak pemegang = pemegangList.get(0);
		
		return pemegang;
	}
	
	/**
	 * Overrides the method in super class. Defined here so that we can validate the input fields.
	 * 
	 * @author Mohd Hairudin Mansur
	 * @version 1.0 24 Dec 2009
	 */
	@ValidateNestedProperties({
		@Validate(field="kodAkaun.kod", required=true, expression="this != '0'" , on="simpanMaklumatAkaun"),
		@Validate(field="pemegang.jenisPengenalan.kod", required=true, expression="this != '0'", on="simpanMaklumatAkaun"),
		@Validate(field="pemegang.noPengenalan", required=true, on="simpanMaklumatAkaun"),
		@Validate(field="pemegang.nama", required=true, on="simpanMaklumatAkaun")
	})
	public void setAkaun(Akaun akaun) {
		super.setAkaun(akaun);
	}
	
	/**
	 * Validation method to check whether the Object ID given exists in the system or not.
	 * 
	 * @param errors
	 * @author Mohd Hairudin Mansur
	 * @version 1.0 24 Dec 2009
	 */
	@ValidationMethod(on = "simpanMaklumatAkaun")
	public void isFieldExists(ValidationErrors errors) {
		if(getAkaun().getHakmilik() != null && !hakmilikDAO.exists(getAkaun().getHakmilik().getIdHakmilik())) {
			errors.add("hakmilik.idHakmilik", new SimpleError("ID Hakmilik Tidak Wujud Di dalam Sistem"));
			LOG.error("ID Hakmilik " + getAkaun().getHakmilik().getIdHakmilik() + " Tidak Wujud Di dalam Sistem");
		}
		
		if(getAkaun().getPermohonan() != null && !permohonanDAO.exists(getAkaun().getPermohonan().getIdPermohonan())) {
			errors.add("permohonan.idPermohonan", new SimpleError("ID Permohonan Tidak Wujud Di dalam Sistem"));
			LOG.error("ID Permohonan " + getAkaun().getPermohonan().getIdPermohonan() + " Tidak Wujud Di dalam Sistem");
		}
	}
	
	/**
	 * Validate input for Search Pemegang Akaun form
	 * 
	 * @return true/false
	 * @author Mohd Hairudin Mansur
	 * @version 1.0 5 jan 2010
	 */
	private boolean validateSearchPemegangAkaunFields() {
		boolean noInputError = true;
		
//		if(jenisPengenalan.equals("")) {
//			addSimpleError("Jenis Pengenalan Mesti Dimasukkan.");
//			showCarianPemegangAkaun = false;
//			showSearchForm = true;
//
//			noInputError = false;
//		}
		
		if((!jenisPengenalan.equals("0"))&&(noPengenalan == null || noPengenalan.trim().length() == 0)) {
			addSimpleError("No. Pengenalan Mesti Dimasukkan.");
			showCarianPemegangAkaun = false;
			showSearchForm = true;
			
			noInputError = false;
		}
                if((jenisPengenalan.equals("0"))&&(namaPemegang == null)) {
			addSimpleError("Sila Masukkan Nama Pemagang Untuk Membuat Carian.");
			showCarianPemegangAkaun = false;
			showSearchForm = true;

			noInputError = false;
		}
		
		return noInputError;
	}

	/**
	 * @param pemegangAkaun the pemegangAkaun to set
	 */
	public void setPemegangAkaun(Pihak pemegangAkaun) {
		this.pemegangAkaun = pemegangAkaun;
	}

	/**
	 * @return the pemegangAkaun
	 */
	public Pihak getPemegangAkaun() {
		return pemegangAkaun;
	}

	/**
	 * @param showCarianPemegangAkaun the showCarianPemegangAkaun to set
	 */
	public void setShowCarianPemegangAkaun(boolean showCarianPemegangAkaun) {
		this.showCarianPemegangAkaun = showCarianPemegangAkaun;
	}

	/**
	 * @return the showCarianPemegangAkaun
	 */
	public boolean isShowCarianPemegangAkaun() {
		return showCarianPemegangAkaun;
	}

	/**
	 * @param jenisPengenalan the jenisPengenalan to set
	 */
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
	public void setNoPengenalan(String noPengenalan) {
		this.noPengenalan = noPengenalan;
	}

	/**
	 * @return the noPengenalan
	 */
	public String getNoPengenalan() {
		return noPengenalan;
	}

        public String getNamaPemegang() {
            return namaPemegang;
        }

        public void setNamaPemegang(String namaPemegang) {
            this.namaPemegang = namaPemegang;
        }

	/**
	 * @param showSearchForm the showSearchForm to set
	 */
	public void setShowSearchForm(boolean showSearchForm) {
		this.showSearchForm = showSearchForm;
	}

	/**
	 * @return the showSearchForm
	 */
	public boolean isShowSearchForm() {
		return showSearchForm;
	}

	/**
	 * @param showTambahCawangan the showTambahCawangan to set
	 */
	public void setShowTambahCawangan(boolean showTambahCawangan) {
		this.showTambahCawangan = showTambahCawangan;
	}

	/**
	 * @return the showTambahCawangan
	 */
	public boolean isShowTambahCawangan() {
		return showTambahCawangan;
	}

	/**
	 * @param pihakCawangan the pihakCawangan to set
	 */
	public void setPihakCawangan(PihakCawangan pihakCawangan) {
		this.pihakCawangan = pihakCawangan;
	}

	/**
	 * @return the pihakCawangan
	 */
	public PihakCawangan getPihakCawangan() {
		return pihakCawangan;
	}

	/**
	 * @param cawanganList the cawanganList to set
	 */
	public void setCawanganList(List<PihakCawangan> cawanganList) {
		this.cawanganList = cawanganList;
	}

	/**
	 * @return the cawanganList
	 */
	public List<PihakCawangan> getCawanganList() {
		return cawanganList;
	}

        public String getFlag() {
            return flag;
        }

        public void setFlag(String flag) {
            this.flag = flag;
        }
	
}
