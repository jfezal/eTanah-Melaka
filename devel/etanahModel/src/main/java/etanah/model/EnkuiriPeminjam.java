package etanah.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table (name = "enkuiri_peminjam")
@SequenceGenerator (name = "seq_enkuiri_peminjam", sequenceName = "seq_enkuiri_peminjam")
public class EnkuiriPeminjam implements Serializable {
	
	@Id
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "seq_enkuiri_peminjam")
        @Column (name = "id_enkuiri_peminjam")
	private long idEnkuiriPeminjam;
	
	@ManyToOne
	@JoinColumn (name = "kod_caw", nullable = false)
	private KodCawangan cawangan;
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "id_enkuiri", nullable = false)
	private Enkuiri enkuiri;
	
	@Column (name = "nama_peminjam")
	private String nama_peminjam;

	@Embedded
	private InfoAudit infoAudit;

    public KodCawangan getCawangan() {
        return cawangan;
    }

    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
    }

    public Enkuiri getEnkuiri() {
        return enkuiri;
    }

    public void setEnkuiri(Enkuiri enkuiri) {
        this.enkuiri = enkuiri;
    }

    public long getIdEnkuiriPeminjam() {
        return idEnkuiriPeminjam;
    }

    public void setIdEnkuiriPeminjam(long idEnkuiriPeminjam) {
        this.idEnkuiriPeminjam = idEnkuiriPeminjam;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    public String getNama_peminjam() {
        return nama_peminjam;
    }

    public void setNama_peminjam(String nama_peminjam) {
        this.nama_peminjam = nama_peminjam;
    }
 

}
