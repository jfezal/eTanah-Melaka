package etanah.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table (name = "wakil_kuasa_pberi")
@SequenceGenerator(name = "seq_wakil_kuasa_pemberi", sequenceName = "seq_wakil_kuasa_pemberi")
public class WakilKuasaPemberi implements Serializable {
	@Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_wakil_kuasa_pemberi")
	@Column (name = "id_pberi")
	private long idPemberi;
	
	@ManyToOne
	@JoinColumn (name = "kod_caw", nullable = false)
	private KodCawangan cawangan;
	
	@ManyToOne
	@JoinColumn (name = "id_wakil", nullable = false)
	private WakilKuasa wakilKuasa;

	@ManyToOne
	@JoinColumn (name = "id_pihak")
	private Pihak pihak;
         
         @Column (name = "nama")
	private String nama;
         
         @Column (name = "no_pengenalan")
	private String noPengenalan;
         
         @Column (name = "kod_pengenalan")
	private String kodPengenalan;
         
	
	@Embedded
	private InfoAudit infoAudit;

	public long getIdPemberi() {
		return idPemberi;
	}

	public void setIdPemberi(long idPemberi) {
		this.idPemberi = idPemberi;
	}

	public KodCawangan getCawangan() {
		return cawangan;
	}

	public void setCawangan(KodCawangan cawangan) {
		this.cawangan = cawangan;
	}

	public WakilKuasa getWakilKuasa() {
		return wakilKuasa;
	}

	public void setWakilKuasa(WakilKuasa wakilKuasa) {
		this.wakilKuasa = wakilKuasa;
	}

	public Pihak getPihak() {
		return pihak;
	}

	public void setPihak(Pihak pihak) {
		this.pihak = pihak;
	}

	public InfoAudit getInfoAudit() {
		return infoAudit;
	}

	public void setInfoAudit(InfoAudit infoAudit) {
		this.infoAudit = infoAudit;
	}

     public String getNama() {
          return nama;
     }

     public void setNama(String nama) {
          this.nama = nama;
     }

     public String getNoPengenalan() {
          return noPengenalan;
     }

     public void setNoPengenalan(String noPengenalan) {
          this.noPengenalan = noPengenalan;
     }

     public String getKodPengenalan() {
          return kodPengenalan;
     }

     public void setKodPengenalan(String kodPengenalan) {
          this.kodPengenalan = kodPengenalan;
     }

}
