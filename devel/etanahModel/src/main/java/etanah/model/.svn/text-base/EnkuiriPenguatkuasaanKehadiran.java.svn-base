package etanah.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table (name = "kkuasa_enkuiri_hadir")
@SequenceGenerator (name = "seq_kkuasa_enkuiri_hadir", sequenceName = "seq_kkuasa_enkuiri_hadir")
public class EnkuiriPenguatkuasaanKehadiran implements Serializable {

	@Id
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "seq_kkuasa_enkuiri_hadir")
        @Column (name = "id_hadir")
	private long idKehadiran;
	
	@ManyToOne
	@JoinColumn (name = "kod_caw", nullable = false)
	private KodCawangan cawangan;

        @ManyToOne
	@JoinColumn (name = "id_enkuiri", nullable = false)
	private EnkuiriPenguatkuasaan enkuiri;
	
	@ManyToOne
	@JoinColumn (name = "id_oks")
	private AduanOrangKenaSyak orangKenaSyak;
	
	@Column (name = "nama")
	private String nama;
	
	@ManyToOne
	@JoinColumn (name = "kod_pengenalan")
	private KodJenisPengenalan jenisPengenalan;
	
	@Column (name = "no_pengenalan")
	private String noPengenalan;
	
	@Column (name = "jawatan")
	private String jawatan;
	
	@Column (name = "hadir")
	private Character hadir;
	
	@Column (name = "denda")
	private Character kenaDenda;
	
	@Column (name = "catatan")
	private String catatan;
        
        @Column (name = "no_ruj")
	private String noRujukan;
        
        @Column (name = "nama_pb")
	private String namaPihakBerkepentingan;
        
        @ManyToOne
	@JoinColumn (name = "kod_pengenalan_pb")
	private KodJenisPengenalan jenisPengenalanPihakBerkepentingan;
        
        @Column (name = "no_pengenalan_pb")
	private String noPengenalanPihakBerkepentingan;
        
        @Column (name = "hubungan_pb")
	private String hubunganPihakBerkepentingan;
	
	@Embedded
	private InfoAudit infoAudit;
        
        @Embedded
        private Alamat alamat;

	public long getIdKehadiran() {
		return idKehadiran;
	}

	public void setIdKehadiran(long idKehadiran) {
		this.idKehadiran = idKehadiran;
	}

	public KodCawangan getCawangan() {
		return cawangan;
	}

	public void setCawangan(KodCawangan cawangan) {
		this.cawangan = cawangan;
	}
	public EnkuiriPenguatkuasaan getEnkuiri() {
		return enkuiri;
	}

	public void setEnkuiri(EnkuiriPenguatkuasaan enkuiri) {
		this.enkuiri = enkuiri;
	}

	public AduanOrangKenaSyak getOrangKenaSyak() {
		return orangKenaSyak;
	}

	public void setOrangKenaSyak(AduanOrangKenaSyak orangKenaSyak) {
		this.orangKenaSyak = orangKenaSyak;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
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

	public String getJawatan() {
		return jawatan;
	}

	public void setJawatan(String jawatan) {
		this.jawatan = jawatan;
	}

	public Character getHadir() {
		return hadir;
	}

	public void setHadir(Character hadir) {
		this.hadir = hadir;
	}

	public void setKenaDenda(Character kenaDenda) {
		this.kenaDenda = kenaDenda;
	}

	public Character getKenaDenda() {
		return kenaDenda;
	}

	public String getCatatan() {
		return catatan;
	}

	public void setCatatan(String catatan) {
		this.catatan = catatan;
	}

	public InfoAudit getInfoAudit() {
		return infoAudit;
	}

	public void setInfoAudit(InfoAudit infoAudit) {
		this.infoAudit = infoAudit;
	}

    public String getNoRujukan() {
        return noRujukan;
    }

    public void setNoRujukan(String noRujukan) {
        this.noRujukan = noRujukan;
    }

    public Alamat getAlamat() {
        return alamat;
    }

    public void setAlamat(Alamat alamat) {
        this.alamat = alamat;
    }

    public String getNamaPihakBerkepentingan() {
        return namaPihakBerkepentingan;
    }

    public void setNamaPihakBerkepentingan(String namaPihakBerkepentingan) {
        this.namaPihakBerkepentingan = namaPihakBerkepentingan;
    }

    public KodJenisPengenalan getJenisPengenalanPihakBerkepentingan() {
        return jenisPengenalanPihakBerkepentingan;
    }

    public void setJenisPengenalanPihakBerkepentingan(KodJenisPengenalan jenisPengenalanPihakBerkepentingan) {
        this.jenisPengenalanPihakBerkepentingan = jenisPengenalanPihakBerkepentingan;
    }

    public String getNoPengenalanPihakBerkepentingan() {
        return noPengenalanPihakBerkepentingan;
    }

    public void setNoPengenalanPihakBerkepentingan(String noPengenalanPihakBerkepentingan) {
        this.noPengenalanPihakBerkepentingan = noPengenalanPihakBerkepentingan;
    }

    public String getHubunganPihakBerkepentingan() {
        return hubunganPihakBerkepentingan;
    }

    public void setHubunganPihakBerkepentingan(String hubunganPihakBerkepentingan) {
        this.hubunganPihakBerkepentingan = hubunganPihakBerkepentingan;
    }

}
