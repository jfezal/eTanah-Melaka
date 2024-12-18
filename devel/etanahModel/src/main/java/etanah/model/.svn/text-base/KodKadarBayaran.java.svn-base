package etanah.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

@Entity
@Table (name = "kod_kadar_bayar")
@org.hibernate.annotations.Cache (usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE)
public class KodKadarBayaran implements Serializable {

	@Id
	@Column (name = "kod")
	private int kod;
	
	@ManyToOne
	@JoinColumn (name ="kod_urusan", nullable = false)
	private KodUrusan kodUrusan;
	
	@Column (name = "id_aliran")
	private String idAliran;
	
	@Column (name = "bil_hakmilik")
	private Integer bilHakmilik;
	
	@Column (name = "milik_daerah")
	private char milikDaerah;
	
	@Column (name = "katg")
	private String kategori;
	
	@Column (name = "nilai_min")
	private BigDecimal nilaiMinimum;
	
	@Column (name = "nilai_maks")
	private BigDecimal nilaiMaksimum;
	
	@Column (name = "kadar")
	private BigDecimal kadar;

	@Column (name = "peratus")
	private char peratus;
        
        @Column (name = "aktif")
        private char aktif;

    @Column (name = "kadar_min")
    private BigDecimal kadarMinimum;
    
    @Column (name = "kadar_maks")
    private BigDecimal kadarMaksimum;

	@Embedded
	private InfoAudit infoAudit;

	public int getKod() {
		return kod;
	}

	public void setKod(int kod) {
		this.kod = kod;
	}

	public KodUrusan getKodUrusan() {
		return kodUrusan;
	}

	public void setKodUrusan(KodUrusan kodUrusan) {
		this.kodUrusan = kodUrusan;
	}

	public void setIdAliran(String idAliran) {
		this.idAliran = idAliran;
	}

	public String getIdAliran() {
		return idAliran;
	}

	public void setBilHakmilik(Integer bilHakmilik) {
		this.bilHakmilik = bilHakmilik;
	}

	public Integer getBilHakmilik() {
		return bilHakmilik;
	}

	public char getMilikDaerah() {
		return milikDaerah;
	}

	public void setMilikDaerah(char milikDaerah) {
		this.milikDaerah = milikDaerah;
	}

	public String getKategori() {
		return kategori;
	}

	public void setKategori(String kategori) {
		this.kategori = kategori;
	}

	public BigDecimal getNilaiMinimum() {
		return nilaiMinimum;
	}

	public void setNilaiMinimum(BigDecimal nilaiMinimum) {
		this.nilaiMinimum = nilaiMinimum;
	}

	public BigDecimal getNilaiMaksimum() {
		return nilaiMaksimum;
	}

	public void setNilaiMaksimum(BigDecimal nilaiMaksimum) {
		this.nilaiMaksimum = nilaiMaksimum;
	}

	public BigDecimal getKadar() {
		return kadar;
	}

	public void setKadar(BigDecimal kadar) {
		this.kadar = kadar;
	}

	public BigDecimal getKadarMinimum() {
        return kadarMinimum;
    }

    public void setKadarMinimum(BigDecimal kadarMinimum) {
        this.kadarMinimum = kadarMinimum;
    }

    public BigDecimal getKadarMaksimum() {
        return kadarMaksimum;
    }

    public void setKadarMaksimum(BigDecimal kadarMaksimum) {
        this.kadarMaksimum = kadarMaksimum;
    }

    public void setPeratus(char peratus) {
		this.peratus = peratus;
	}

	public char getPeratus() {
		return peratus;
	}

	public InfoAudit getInfoAudit() {
		return infoAudit;
	}

	public void setInfoAudit(InfoAudit infoAudit) {
		this.infoAudit = infoAudit;
	}

    public char getAktif() {
        return aktif;
    }

    public void setAktif(char aktif) {
        this.aktif = aktif;
    }
        
        
	
}
