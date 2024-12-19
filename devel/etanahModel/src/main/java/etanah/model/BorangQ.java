package etanah.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table (name = "borang_q")
@SequenceGenerator(name = "seq_borang_q", sequenceName = "seq_borang_q")
public class BorangQ implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "seq_borang_q")
    @Column (name = "id_borang_q")
    private int idBorangQ;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "id_mohon", nullable = false)
    private Permohonan permohonan;

    @Column (name = "tempoh")
    private int tempoh;

    @Column(name = "trh_mula")
    private Date tarikhMulaMendudukiTanah;
    
    @Column (name = "maksud")
    private String maksud;

    @Column (name = "pampasan")
    private BigDecimal tawaranPampasan;

    @Column(name = "trh_hadir")
    private Date tarikhHadir;

    @Column(name = "trh_tamat_pendudukan")
    private Date tarikhTamatPendudukan;

    @Column (name = "waktu_hadir")
    private char waktuHadir;

    @Embedded
    InfoAudit infoAudit;

    public int getIdBorangQ() {
		return idBorangQ;
	}

	public void setIdBorangQ(int idBorangQ) {
		this.idBorangQ = idBorangQ;
	}

	public Permohonan getPermohonan() {
		return permohonan;
	}

	public void setPermohonan(Permohonan permohonan) {
		this.permohonan = permohonan;
	}

	public int getTempoh() {
		return tempoh;
	}

	public void setTempoh(int tempoh) {
		this.tempoh = tempoh;
	}

	public Date getTarikhMulaMendudukiTanah() {
		return tarikhMulaMendudukiTanah;
	}

	public void setTarikhMulaMendudukiTanah(Date tarikhMulaMendudukiTanah) {
		this.tarikhMulaMendudukiTanah = tarikhMulaMendudukiTanah;
	}

	public String getMaksud() {
		return maksud;
	}

	public void setMaksud(String maksud) {
		this.maksud = maksud;
	}

	public BigDecimal getTawaranPampasan() {
		return tawaranPampasan;
	}

	public void setTawaranPampasan(BigDecimal tawaranPampasan) {
		this.tawaranPampasan = tawaranPampasan;
	}

	public Date getTarikhHadir() {
		return tarikhHadir;
	}

	public void setTarikhHadir(Date tarikhHadir) {
		this.tarikhHadir = tarikhHadir;
	}

    public Date getTarikhTamatPendudukan() {
        return tarikhTamatPendudukan;
    }

    public void setTarikhTamatPendudukan(Date tarikhTamatPendudukan) {
        this.tarikhTamatPendudukan = tarikhTamatPendudukan;
    }

	public char getWaktuHadir() {
		return waktuHadir;
	}

	public void setWaktuHadir(char waktuHadir) {
		this.waktuHadir = waktuHadir;
	}

	public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

}
