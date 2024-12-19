package etanah.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table (name = "kew_akaun")
public class Akaun implements Serializable {

	@Id
	@Column (name = "no_akaun", length = 12)
	private String noAkaun;
	
	@ManyToOne
	@JoinColumn (name = "sts")
	private KodStatusAkaun status;
	
	@ManyToOne (fetch = FetchType.EAGER)
	@JoinColumn (name = "kod_akaun")
	private KodAkaun kodAkaun;
	
	@ManyToOne (fetch = FetchType.EAGER)
	@JoinColumn (name = "kod_caw")
	private KodCawangan cawangan;
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "id_hakmilik", nullable = true)
	private Hakmilik hakmilik;
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "id_mohon", nullable = true)
	private Permohonan permohonan;
	
	/**
	 * Pemegang kepada akaun. Jika untuk Hakmilik: pembayar.
	 */
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "dipegang")
	private Pihak pemegang;
	
	@Column (name = "baki", precision = 12, scale = 2, columnDefinition = "NUMBER(12,2)")
	private BigDecimal baki;
	
	@OneToMany (mappedBy = "akaunDebit")
	@OrderBy ("infoAudit.tarikhMasuk")
	private List<Transaksi> senaraiTransaksiDebit; 

	@OneToMany (mappedBy = "akaunKredit")
	@OrderBy ("infoAudit.tarikhMasuk")
	private List<Transaksi> senaraiTransaksiKredit;

        @OneToMany (mappedBy = "akaunDebit")
	@OrderBy ("infoAudit.tarikhMasuk")
	private List<SejarahTransaksi> senaraiSejarahTransaksiDebit;

	@OneToMany (mappedBy = "akaunKredit")
	@OrderBy ("infoAudit.tarikhMasuk")
	private List<SejarahTransaksi> senaraiSejarahTransaksiKredit;

	@Column (name = "kod_speks_jab", length = 50)
	private String kodSpeksJabatan;

	@Column (name = "kod_speks_ptj", length = 50)
	private String kodSpeksPTJ;
	
	@Column (name = "amaun_matang")
	private BigDecimal amaunMatang;

        @Column (name = "ansuran")
	private Character ansuran;
	
	/**
	 * Tarikh matang
	 */
	@Column (name = "trh_matang")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
	private Date tarikhMatang;
	
	/**
	 * Count of statement of this account being printed
	 */
	@Column (name = "bil_cetak")
	private int bilCetakPenyata;
	
	@Column (name = "hantar_bil")
	private char hantarBil;
	
	@OneToMany (mappedBy = "akaunDebit", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<TransaksiHadapan> senaraiTransaksiDebitHadapan;

	@OneToMany (mappedBy = "akaunKredit", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<TransaksiHadapan> senaraiTransaksiKreditHadapan;
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "id_kump")
	private KumpulanAkaun kumpulan;
	
	@Column (name = "no_akaun_lama", nullable = true)
	private String noAkaunLama;
	
	@Column (name = "catatan")
	private String catatan;
	
	@OneToMany (mappedBy = "akaun",fetch = FetchType.LAZY)
	private List<LogAkaunKewangan> senaraiLog;

	@Embedded
	private InfoAudit infoAudit;
	
        private InfoAuditBaru InfoAuditBaru;
        
        @Column (name = "is_jana")
	private char isJana;

	public String getNoAkaun() {
		return noAkaun;
	}

	public void setNoAkaun(String noAkaun) {
		this.noAkaun = noAkaun;
	}

	public void setStatus(KodStatusAkaun status) {
		this.status = status;
	}

	public KodStatusAkaun getStatus() {
		return status;
	}

	public KodCawangan getCawangan() {
		return cawangan;
	}

	public void setCawangan(KodCawangan cawangan) {
		this.cawangan = cawangan;
	}

	public Hakmilik getHakmilik() {
		return hakmilik;
	}

	public void setHakmilik(Hakmilik hakmilik) {
		this.hakmilik = hakmilik;
	}

	public void setPermohonan(Permohonan permohonan) {
		this.permohonan = permohonan;
	}

	public Permohonan getPermohonan() {
		return permohonan;
	}

	public void setPemegang(Pihak pemegang) {
		this.pemegang = pemegang;
	}

	public Pihak getPemegang() {
		return pemegang;
	}

	public BigDecimal getBaki() {
		return baki;
	}

	public void setBaki(BigDecimal baki) {
		this.baki = baki;
	}

	public List<Transaksi> getSenaraiTransaksiDebit() {
		return senaraiTransaksiDebit;
	}

	public void setSenaraiTransaksiDebit(List<Transaksi> senaraiTransaksi) {
		this.senaraiTransaksiDebit = senaraiTransaksi;
	}

	public List<Transaksi> getSenaraiTransaksiKredit() {
		return senaraiTransaksiKredit;
	}

	public void setSenaraiTransaksiKredit(List<Transaksi> senaraiTransaksi) {
		this.senaraiTransaksiKredit = senaraiTransaksi;
	}
        	public List<SejarahTransaksi> getSenaraiSejarahTransaksiDebit() {
		return senaraiSejarahTransaksiDebit;
	}

	public void setSenaraiSejarahTransaksiDebit(List<SejarahTransaksi> senaraiSejarahTransaksi) {
		this.senaraiSejarahTransaksiDebit = senaraiSejarahTransaksi;
	}

	public List<SejarahTransaksi> getSenaraiSejarahTransaksiKredit() {
		return senaraiSejarahTransaksiKredit;
	}

	public void setSenaraiSejarahTransaksiKredit(List<SejarahTransaksi> senaraiSejarahTransaksi) {
		this.senaraiSejarahTransaksiKredit = senaraiSejarahTransaksi;
	}

	/**
	 * Get the combined Transaksi of debits and credits sorted by date inserted
	 * @return
	 */
	public List<Transaksi> getSemuaTransaksi(){
		ArrayList<Transaksi> all = new ArrayList<Transaksi>();
		all.addAll(senaraiTransaksiDebit);
		all.addAll(senaraiTransaksiKredit);
		Collections.sort(all, new Comparator<Transaksi>(){
					public int compare(Transaksi a, Transaksi b) {
						return a.getInfoAudit().getTarikhMasuk()
							.compareTo(b.getInfoAudit().getTarikhMasuk());
					}			
		});
		return all;
	}
        	public List<SejarahTransaksi> getSemuaSejarahTransaksi(){
		ArrayList<SejarahTransaksi> all = new ArrayList<SejarahTransaksi>();
		all.addAll(senaraiSejarahTransaksiDebit);
		all.addAll(senaraiSejarahTransaksiKredit);
		Collections.sort(all, new Comparator<SejarahTransaksi>(){
					public int compare(SejarahTransaksi a, SejarahTransaksi b) {
						return a.getInfoAudit().getTarikhMasuk()
							.compareTo(b.getInfoAudit().getTarikhMasuk());
					}
		});
		return all;
	}
	
	public KodAkaun getKodAkaun() {
		return kodAkaun;
	}

	public void setKodAkaun(KodAkaun kodAkaun) {
		this.kodAkaun = kodAkaun;
	}

	public void setKodSpeksJabatan(String kodSpeksJabatan) {
		this.kodSpeksJabatan = kodSpeksJabatan;
	}

	public String getKodSpeksJabatan() {
		return kodSpeksJabatan;
	}

	public void setKodSpeksPTJ(String kodSpeksPTJ) {
		this.kodSpeksPTJ = kodSpeksPTJ;
	}

	public String getKodSpeksPTJ() {
		return kodSpeksPTJ;
	}

	public void setAmaunMatang(BigDecimal amaunMatang) {
		this.amaunMatang = amaunMatang;
	}

	public BigDecimal getAmaunMatang() {
		return amaunMatang;
	}

	public void setTarikhMatang(Date tarikhMatang) {
		this.tarikhMatang = tarikhMatang;
	}

	public Date getTarikhMatang() {
		return tarikhMatang;
	}

	public void setBilCetakPenyata(int bilCetakPenyata) {
		this.bilCetakPenyata = bilCetakPenyata;
	}

	public int getBilCetakPenyata() {
		return bilCetakPenyata;
	}

	public void setHantarBil(char hantarBil) {
		this.hantarBil = hantarBil;
	}

	public char getHantarBil() {
		return hantarBil;
	}

	public void setSenaraiTransaksiDebitHadapan(List<TransaksiHadapan> senaraiTransaksiHadapan) {
		this.senaraiTransaksiDebitHadapan = senaraiTransaksiHadapan;
	}

	public List<TransaksiHadapan> getSenaraiTransaksiDebitHadapan() {
		return senaraiTransaksiDebitHadapan;
	}

	public void setSenaraiTransaksiKreditHadapan(List<TransaksiHadapan> senaraiTransaksiHadapan) {
		this.senaraiTransaksiKreditHadapan = senaraiTransaksiHadapan;
	}

	public List<TransaksiHadapan> getSenaraiTransaksiKreditHadapan() {
		return senaraiTransaksiKreditHadapan;
	}

	public void setKumpulan(KumpulanAkaun kumpulan) {
		this.kumpulan = kumpulan;
	}

	public KumpulanAkaun getKumpulan() {
		return kumpulan;
	}

    public Character getAnsuran() {
        return ansuran;
    }

    public void setAnsuran(Character ansuran) {
        this.ansuran = ansuran;
    }
	
	public String getNoAkaunLama() {
		return noAkaunLama;
	}

	public void setNoAkaunLama(String noAkaunLama) {
		this.noAkaunLama = noAkaunLama;
	}

	public void setCatatan(String catatan) {
		this.catatan = catatan;
	}

	public String getCatatan() {
		return catatan;
	}

	public void setSenaraiLog(List<LogAkaunKewangan> senaraiLog) {
		this.senaraiLog = senaraiLog;
	}

	public List<LogAkaunKewangan> getSenaraiLog() {
		return senaraiLog;
	}

	public InfoAudit getInfoAudit() {
		return infoAudit;
	}

	public void setInfoAudit(InfoAudit infoAudit) {
		this.infoAudit = infoAudit;
	}

    public InfoAuditBaru getInfoAuditBaru() {
        return InfoAuditBaru;
    }

    public void setInfoAuditBaru(InfoAuditBaru InfoAuditBaru) {
        this.InfoAuditBaru = InfoAuditBaru;
    }

    public char getIsJana() {
        return isJana;
    }

    public void setIsJana(char isJana) {
        this.isJana = isJana;
    }
        
    
	
}
