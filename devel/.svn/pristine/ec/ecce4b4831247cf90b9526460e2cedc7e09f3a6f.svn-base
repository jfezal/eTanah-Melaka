package etanah.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

@Entity
@Table (name = "syarat_pendudukan")
@SequenceGenerator(name = "seq_syarat_pendudukan", sequenceName = "seq_syarat_pendudukan")
public class SyaratPendudukan implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "seq_syarat_pendudukan")
    @Column (name = "id_syarat_pendudukan")
    private int idSyaratPendudukan;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "id_mohon", nullable = false)
    private Permohonan permohonan;

    @Column (name = "pampasan", nullable = false)
    private BigDecimal jumlahPampasan;

    @Column (name = "cara_bayaran", nullable = false)
    private char caraBayaran;

    @Column (name = "kerosakan")
    private String perincianKerosakan;

    @Embedded
    InfoAudit infoAudit;

    public int getIdSyaratPendudukan() {
		return idSyaratPendudukan;
	}

	public void setIdSyaratPendudukan(int idSyaratPendudukan) {
		this.idSyaratPendudukan = idSyaratPendudukan;
	}

	public Permohonan getPermohonan() {
		return permohonan;
	}

	public void setPermohonan(Permohonan permohonan) {
		this.permohonan = permohonan;
	}

	public BigDecimal getJumlahPampasan() {
		return jumlahPampasan;
	}

	public void setJumlahPampasan(BigDecimal jumlahPampasan) {
		this.jumlahPampasan = jumlahPampasan;
	}

	public char getCaraBayaran() {
		return caraBayaran;
	}

	public void setCaraBayaran(char caraBayaran) {
		this.caraBayaran = caraBayaran;
	}

	public String getPerincianKerosakan() {
		return perincianKerosakan;
	}

	public void setPerincianKerosakan(String perincianKerosakan) {
		this.perincianKerosakan = perincianKerosakan;
	}

	public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

}
