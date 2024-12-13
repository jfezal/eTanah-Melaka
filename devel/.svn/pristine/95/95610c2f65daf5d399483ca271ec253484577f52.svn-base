package etanah.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "mohon_fasa")
@SequenceGenerator(name = "seq_mohon_fasa", sequenceName = "seq_mohon_fasa")
public class FasaPermohonan implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_mohon_fasa")
    @Column(name = "id_fasa")
    private long idFasa;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_mohon")
    private Permohonan permohonan;
    
    @Column(name = "id_aliran", length = 10)
    private String idAliran;
    
    @Column(name = "id_pguna",  length = 30, nullable = true)
    private String idPengguna;
    
    @Column (name = "kpsn_utk")
    private String keputusanUntuk;
    
    @Column (name = "trh_kpsn")
    private Date tarikhKeputusan;
    
    @ManyToOne
    @JoinColumn(name = "kod_caw")
    private KodCawangan cawangan;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "kod_kpsn")
    private KodKeputusan keputusan;
    
    @Column(name = "ulasan")
    private String ulasan;
    
    @Column (name = "trh_hantar")
    private Date tarikhHantar;
    
    @Column (name = "mesej")
    private String mesej;
    
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "dimesej")
    private Pengguna dimesejOleh;
    
    @Column (name = "trh_mesej")
    private Date tarikhMesej;
    
    @OneToMany (mappedBy = "fasa")
    private List<FasaPermohonanLog> senaraiLog;
    
    @Embedded
    private InfoAudit infoAudit;

    public long getIdFasa() {
        return idFasa;
    }

    public void setIdFasa(long idFasa) {
        this.idFasa = idFasa;
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

    public String getIdAliran() {
        return idAliran;
    }

    public void setIdAliran(String idAliran) {
        this.idAliran = idAliran;
    }

    public String getIdPengguna() {
        return idPengguna;
    }

    public void setIdPengguna(String idPengguna) {
        this.idPengguna = idPengguna;
    }

	public String getKeputusanUntuk() {
		return keputusanUntuk;
	}

	public void setKeputusanUntuk(String keputusanUntuk) {
		this.keputusanUntuk = keputusanUntuk;
	}

	public Date getTarikhKeputusan() {
		return tarikhKeputusan;
	}

	public void setTarikhKeputusan(Date tarikhKeputusan) {
		this.tarikhKeputusan = tarikhKeputusan;
	}
/**
    * Keputusan in:
    * (L)ulus (T)olak (S) (D)aftar (G)antung (P)eraku (R)Tidak Peraku (O)Syor Tolak (A)Syor Lulus
    **/
    public KodKeputusan getKeputusan() {
        return keputusan;
    }

    /**
    * Keputusan in:
    * (L)ulus (T)olak (S) (D)aftar (G)antung (P)eraku (R)Tidak Peraku (O)Syor Tolak (A)Syor Lulus
    **/
    public void setKeputusan(KodKeputusan keputusan) {
        this.keputusan = keputusan;
    }

    public String getUlasan() {
        return ulasan;
    }

    public void setUlasan(String ulasan) {
        this.ulasan = ulasan;
    }

    public Date getTarikhHantar() {
		return tarikhHantar;
	}

	public void setTarikhHantar(Date tarikhHantar) {
		this.tarikhHantar = tarikhHantar;
	}

	public String getMesej() {
		return mesej;
	}

	public void setMesej(String mesej) {
		this.mesej = mesej;
	}

	public Pengguna getDimesejOleh() {
		return dimesejOleh;
	}

	public void setDimesejOleh(Pengguna dimesejOleh) {
		this.dimesejOleh = dimesejOleh;
	}

	public Date getTarikhMesej() {
		return tarikhMesej;
	}

	public void setTarikhMesej(Date tarikhMesej) {
		this.tarikhMesej = tarikhMesej;
	}

	public void setSenaraiLog(List<FasaPermohonanLog> senaraiLog) {
		this.senaraiLog = senaraiLog;
	}

	public List<FasaPermohonanLog> getSenaraiLog() {
		return senaraiLog;
	}

	public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

}
