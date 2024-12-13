package etanah.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.FetchMode;

@Entity
@Table (name = "kkuasa_kompaun")
@SequenceGenerator(name = "seq_kkuasa_kompaun", sequenceName = "seq_kkuasa_kompaun")
public class Kompaun implements Serializable {

	@Id
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "seq_kkuasa_kompaun")
        @Column (name = "id_kompaun")
	private long idKompaun;
	
	@ManyToOne
	@JoinColumn (name = "kod_caw", nullable = false)
	private KodCawangan cawangan;
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "id_mohon", nullable = false)
	private Permohonan permohonan;
		
	@Column (name = "no_ruj", nullable = false)
	private String noRujukan;
	
	@Column (name = "amaun", nullable = false)
	private BigDecimal amaun;
	
	@Column (name = "tempoh_hari")
	private int tempohHari;
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "id_oks")
	private AduanOrangKenaSyak orangKenaSyak;
	
	@Column (name = "kpd", nullable = false)
	private String isuKepada;
	
	@ManyToOne
	@JoinColumn (name = "kod_pengenalan")
	private KodJenisPengenalan jenisPengenalan;
	
	@Column (name = "no_pengenalan")
	private String noPengenalan;
	
	@Column (name = "trh_hantar")
	private Date tarikhDihantar;
	
	@ManyToOne
	@JoinColumn (name = "kod_sts_terima")
	private KodStatusTerima statusTerima;
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "id_kew_dok")
	private DokumenKewangan resit;
        
        @Column (name = "drujuk1")
	private String rujukan1;
        
        @Embedded
        private Alamat alamat;
	
	@Embedded
	private InfoAudit infoAudit;

	@ManyToOne
	@JoinColumn (name = "id_kos")
	private PermohonanTuntutanKos permohonanTuntutanKos;
        
         @ManyToOne
	@JoinColumn (name = "id_saksi")
	private PermohonanSaksi saksi; 
         
         @Column( name="status_rayuan")
         private Character rayuan;

	public long getIdKompaun() {
		return idKompaun;
	}

	public void setIdKompaun(long idKompaun) {
		this.idKompaun = idKompaun;
	}

	public KodCawangan getCawangan() {
		return cawangan;
	}

	public void setCawangan(KodCawangan cawangan) {
		this.cawangan = cawangan;
	}

	public Permohonan getPermohonan() {
		return permohonan;
	}

	public void setPermohonan(Permohonan permohonan) {
		this.permohonan = permohonan;
	}

	public String getNoRujukan() {
		return noRujukan;
	}

	public void setNoRujukan(String noRujukan) {
		this.noRujukan = noRujukan;
	}

	public BigDecimal getAmaun() {
		return amaun;
	}

	public void setAmaun(BigDecimal amaun) {
		this.amaun = amaun;
	}

	public int getTempohHari() {
		return tempohHari;
	}

	public void setTempohHari(int tempohHari) {
		this.tempohHari = tempohHari;
	}

	public void setOrangKenaSyak(AduanOrangKenaSyak orangKenaSyak) {
		this.orangKenaSyak = orangKenaSyak;
	}

	public AduanOrangKenaSyak getOrangKenaSyak() {
		return orangKenaSyak;
	}

	public String getIsuKepada() {
		return isuKepada;
	}

	public void setIsuKepada(String isuKepada) {
		this.isuKepada = isuKepada;
	}

	public KodJenisPengenalan getJenisPengenalan() {
		return jenisPengenalan;
	}

	public void setJenisPengenalan(KodJenisPengenalan jenisPengenalan) {
		this.jenisPengenalan = jenisPengenalan;
	}

	public String getNoPengenalan() {
		return noPengenalan;
	}

	public void setNoPengenalan(String noPengenalan) {
		this.noPengenalan = noPengenalan;
	}

	public void setTarikhDihantar(Date tarikhDihantar) {
		this.tarikhDihantar = tarikhDihantar;
	}

	public Date getTarikhDihantar() {
		return tarikhDihantar;
	}

	public void setStatusTerima(KodStatusTerima statusTerima) {
		this.statusTerima = statusTerima;
	}

	public KodStatusTerima getStatusTerima() {
		return statusTerima;
	}
	
	public void setResit(DokumenKewangan resit) {
		this.resit = resit;
	}

	public DokumenKewangan getResit() {
		return resit;
	}

	public InfoAudit getInfoAudit() {
		return infoAudit;
	}

	public void setInfoAudit(InfoAudit infoAudit) {
		this.infoAudit = infoAudit;
	}

    public PermohonanTuntutanKos getPermohonanTuntutanKos() {
        return permohonanTuntutanKos;
    }

    public void setPermohonanTuntutanKos(PermohonanTuntutanKos permohonanTuntutanKos) {
        this.permohonanTuntutanKos = permohonanTuntutanKos;
    }

    public PermohonanSaksi getSaksi() {
        return saksi;
    }

    public void setSaksi(PermohonanSaksi saksi) {
        this.saksi = saksi;
    }

    public Character getRayuan() {
        return rayuan;
    }

    public void setRayuan(Character rayuan) {
        this.rayuan = rayuan;
    }

    public String getRujukan1() {
        return rujukan1;
    }

    public void setRujukan1(String rujukan1) {
        this.rujukan1 = rujukan1;
    }

    public Alamat getAlamat() {
        return alamat;
    }

    public void setAlamat(Alamat alamat) {
        this.alamat = alamat;
    }
	
}
