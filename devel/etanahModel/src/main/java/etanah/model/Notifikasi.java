package etanah.model;

import java.util.List;

import javax.persistence.*;

@Entity
@Table (name = "mesej_pguna")
@SequenceGenerator(name = "seq_peranan_notif", sequenceName = "seq_peranan_notif")
@NamedQueries ({
@NamedQuery (name = "findActiveMesejForPengguna", 
				query = "select n from Notifikasi n " +
						"where " +
						"n.infoAudit.tarikhMasuk > :expiryDate and " +
						"n.pengguna.id = :idPengguna " +
						"and n.cawangan.id = :kodCawangan " + 
						"order by n.infoAudit.tarikhMasuk desc"),
@NamedQuery (name = "findTotalActiveMesejForPengguna", 
				query = "select count(n) from Notifikasi n " +
						"where " +
						"n.infoAudit.tarikhMasuk > :expiryDate and " +
						"n.pengguna.id = :idPengguna " +
						"and n.cawangan.id = :kodCawangan "),
@NamedQuery (name = "findTotalUnreadMesejForPengguna", 
				query = "select count(n) from Notifikasi n " +
						"where " +
//						"n.infoAudit.tarikhMasuk > :expiryDate and " +
						"n.pengguna.id = :idPengguna " +
						"and n.cawangan.id = :kodCawangan " +
						"and n.telahDibaca NOT IN ('Y') ")})
						
public class Notifikasi {

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_peranan_notif")
	@Column (name = "id_mesej")
	private long idNotifikasi;
	
	@ManyToOne (fetch = FetchType.EAGER)
	@JoinColumn (name = "kod_caw", nullable = false)
	private KodCawangan cawangan;
	
	@ManyToOne (fetch = FetchType.EAGER)
	@JoinColumn (name = "id_pguna")
	private Pengguna pengguna;
		
	@Column (name = "tajuk", length = 200, nullable = false)
	private String tajuk;
	
	@Column (name = "mesej", nullable = false, columnDefinition = "clob")
	private String mesej;
	
	@Column (name = "baca")
	private char telahDibaca;
	
	@Embedded
	private InfoAudit infoAudit;

	public long getIdNotifikasi() {
		return idNotifikasi;
	}

	public void setIdNotifikasi(long idNotifikasi) {
		this.idNotifikasi = idNotifikasi;
	}

	public KodCawangan getCawangan() {
		return cawangan;
	}

	public void setCawangan(KodCawangan cawangan) {
		this.cawangan = cawangan;
	}

	public Pengguna getPengguna() {
		return pengguna;
	}

	public void setPengguna(Pengguna pengguna) {
		this.pengguna = pengguna;
	}

	public void setTajuk(String tajuk) {
		this.tajuk = tajuk;
	}

	public String getTajuk() {
		return tajuk;
	}

	public String getMesej() {
		return mesej;
	}

	public void setMesej(String mesej) {
		this.mesej = mesej;
	}

	public void setTelahDibaca(char telahDibaca) {
		this.telahDibaca = telahDibaca;
	}

	public char getTelahDibaca() {
		return telahDibaca;
	}

	public InfoAudit getInfoAudit() {
		return infoAudit;
	}

	public void setInfoAudit(InfoAudit infoAudit) {
		this.infoAudit = infoAudit;
	}

}
