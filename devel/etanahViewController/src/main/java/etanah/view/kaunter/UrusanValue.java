package etanah.view.kaunter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sourceforge.stripes.action.ActionBean;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import com.google.inject.Inject;

import etanah.dao.KodUrusanDAO;
import etanah.model.Dokumen;
import etanah.model.DokumenKewangan;
import etanah.model.InfoAudit;
import etanah.model.KodJenisPengenalan;
import etanah.model.KodNegeri;
import etanah.model.KodPenyerah;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.report.ReportUtil;
import etanah.view.etanahContextListener;

public class UrusanValue implements UrusanKaunter{
	
	@Inject
	KodUrusanDAO kodUrusanDAO;
	
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;      
		
	String kodJabatan;
	
	String kodUrusan;

	String kodUrusanPilih;
	
	String labelAmaun1;
	
    BigDecimal amaun1;
    
    String labelTarikh1;
    
    Date tarikh1;
    
    String labelNilai1;
    
    String nilai1;
    
    Dokumen akuanPenerimaan;
            
    String idPermohonan;
    
    BigDecimal cajPengecualian;
    
    BigDecimal cajPelepasan;
    
    BigDecimal cajNotis;
    
    BigDecimal cajFail;
    
    BigDecimal cajLain;
    
    String idPermohonanSebelum;
    
    // position in Session's WorkData's List<UrusanCache>
    int position;
    
    long idDokumen1;
    //for multiple sijil carian
    List<Long> senaraiDokumen;

    long idTransaksiCaj;
    long idTransaksiCajPelepasan;
    long idTransaksiCajPengecualian;
    long idTransaksiCajNotis;
    long idTransaksiCajFail;
    long idTransaksiCajLain;
    
    long noResitLama;
    
    
    Logger LOG = Logger.getLogger(UrusanValue.class);

	public String getKodJabatan() {
		return kodJabatan;
	}

	public void setKodJabatan(String kodJabatan) {
		this.kodJabatan = kodJabatan;
	}

	public String getKodUrusan() {
		return kodUrusan;
	}

	public void setKodUrusan(String kodUrusan) {
		this.kodUrusan = kodUrusan;
	}

	public String getNamaUrusan(){
		if (kodUrusan != null){
			LOG.debug(kodUrusan);
			return kodUrusanDAO.findById(kodUrusan).getNama();
		}
		else return null;
	}
	
	public String getKodUrusanPilih() {
		return kodUrusanPilih;
	}

	public void setKodUrusanPilih(String kodUrusanPilih) {
		this.kodUrusanPilih = kodUrusanPilih;
	}

	public String getLabelAmaun1(){
		return labelAmaun1;
	}
		
	public BigDecimal getAmaun1() {
		return amaun1;
	}

	public void setAmaun1(BigDecimal amaun1) {
		this.amaun1 = amaun1;
	}

	public String getLabelTarikh1(){
		return labelTarikh1;
	}

	public Date getTarikh1() {
		return tarikh1;
	}

	public void setTarikh1(Date tarikh1) {
		this.tarikh1 = tarikh1;
	}
	
	public String getLabelNilai1(){
		return labelNilai1;
	}

	public String getNilai1() {
		return nilai1;
	}

	public void setNilai1(String nilai1) {
		this.nilai1 = nilai1;
	}
	
	public void setAkuanPenerimaan(Dokumen akaunPenerimaan) {
		this.akuanPenerimaan = akaunPenerimaan;
	}

	public Dokumen getAkuanPenerimaan() {
		return akuanPenerimaan;
	}

	public String getIdPermohonan(){
		return idPermohonan;
	}
	
	public void setIdPermohonan(String id){
		this.idPermohonan = id;
	}

	public String getIdPermohonanSebelum() {
		return idPermohonanSebelum;
	}

	public void setIdPermohonanSebelum(String idPermohonanSebelum) {
		this.idPermohonanSebelum = idPermohonanSebelum;
	}

	public BigDecimal getCajPengecualian() {
		return cajPengecualian;
	}

	public void setCajPengecualian(BigDecimal cajPengecualian) {
		this.cajPengecualian = cajPengecualian;
	}

	public BigDecimal getCajPelepasan() {
		return cajPelepasan;
	}

	public void setCajPelepasan(BigDecimal cajPelepasan) {
		this.cajPelepasan = cajPelepasan;
	}

	public BigDecimal getCajNotis() {
		return cajNotis;
	}

	public void setCajNotis(BigDecimal cajNotis) {
		this.cajNotis = cajNotis;
	}

	public BigDecimal getCajFail() {
		return cajFail;
	}

	public void setCajFail(BigDecimal cajFail) {
		this.cajFail = cajFail;
	}

	public BigDecimal getCajLain() {
		return cajLain;
	}

	public void setCajLain(BigDecimal cajLain) {
		this.cajLain = cajLain;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public int getPosition() {
		return position;
	}

    public long getIdDokumen1() {
        return idDokumen1;
    }

    public void setIdDokumen1(long idDokumen1) {
        this.idDokumen1 = idDokumen1;
    }

    public List<Long> getSenaraiDokumen() {
        return senaraiDokumen;
    }

    public void setSenaraiDokumen(List<Long> senaraiDokumen) {
        this.senaraiDokumen = senaraiDokumen;
    }

    public long getIdTransaksiCaj() {
        return idTransaksiCaj;
    }

    public void setIdTransaksiCaj(long idTransaksiCaj) {
        this.idTransaksiCaj = idTransaksiCaj;
    }

    public long getIdTransaksiCajFail() {
        return idTransaksiCajFail;
    }

    public void setIdTransaksiCajFail(long idTransaksiCajFail) {
        this.idTransaksiCajFail = idTransaksiCajFail;
    }

    public long getIdTransaksiCajLain() {
        return idTransaksiCajLain;
    }

    public void setIdTransaksiCajLain(long idTransaksiCajLain) {
        this.idTransaksiCajLain = idTransaksiCajLain;
    }

    public long getIdTransaksiCajNotis() {
        return idTransaksiCajNotis;
    }

    public void setIdTransaksiCajNotis(long idTransaksiCajNotis) {
        this.idTransaksiCajNotis = idTransaksiCajNotis;
    }

    public long getIdTransaksiCajPelepasan() {
        return idTransaksiCajPelepasan;
    }

    public void setIdTransaksiCajPelepasan(long idTransaksiCajPelepasan) {
        this.idTransaksiCajPelepasan = idTransaksiCajPelepasan;
    }

    public long getIdTransaksiCajPengecualian() {
        return idTransaksiCajPengecualian;
    }

    public void setIdTransaksiCajPengecualian(long idTransaksiCajPengecualian) {
        this.idTransaksiCajPengecualian = idTransaksiCajPengecualian;
    }

    public long getNoResitLama() {
        return noResitLama;
    }

    public void setNoResitLama(long noResitLama) {
        this.noResitLama = noResitLama;
    }

    /**
     * Get the possible values for nilai1 selection which are retrived from KodKadarBayaran
     * for this KodUrusan
     * @return
     */
    public List<String> getNilai1Selection(){
    	if (kodUrusan == null || kodUrusan.length() == 0) return null;
    	
    	Session s = sessionProvider.get();
    	return s.createQuery("select distinct kategori from KodKadarBayaran kkb where kkb.kodUrusan.id = :kodUrusan")
    			.setString("kodUrusan", kodUrusan).list();
    }

    // TODO delete below (in fact delete this whole class!)
    
    @Override
    public void setNoRujukan(String noRujukan) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public String getNoRujukan() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Permohonan getPermohonan() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<DokumenValue> getSenaraiDokumenSerahan() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<DokumenValue> getSenaraiDokumenTamb() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<String> getSenaraiIdHakmilik() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<TransaksiValue> getSenaraiTransaksi() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<? extends UrusanKaunter> doSave(String kodNegeri,
            Pengguna pengguna, Dokumen resit, String idKumpulan, int seq,
            String idPenyerah, KodPenyerah penyerahKod,
            String penyerahNoPengenalan,
            KodJenisPengenalan penyerahJenisPengenalan, String penyerahNama,
            String penyerahAlamat1, String penyerahAlamat2,
            String penyerahAlamat3, String penyerahAlamat4,
            String penyerahPoskod, KodNegeri penyerahNegeri,
            String penyerahEmail, String penyerahNoTelefon,
            String namaPenyampai, String noPengenalanPenyampai,
            String noTelPenyampai,InfoAudit ia)
            {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void doAfterSave(ReportUtil reportUtil, DokumenKewangan dk, InfoAudit ia) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Class<? extends ActionBean> getEditActionBean() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Dokumen getCetakan() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean hasToInitiateWorkflow() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isEditable() {
        // TODO Auto-generated method stub
        return false;
    }
    
    @Override
    public String getKumpulanUrusan() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setKumpulanUrusan(String kumpulan) {
        // TODO Auto-generated method stub
        
    }

}
