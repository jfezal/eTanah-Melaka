package etanah.model;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table (name = "kkuasa_pasukan")
@SequenceGenerator(name = "seq_kkuasa_pasukan", sequenceName = "seq_kkuasa_pasukan")
public class OperasiPenguatkuasaanPasukan implements Serializable {

	@Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_kkuasa_pasukan")
	@Column (name = "id_kkuasa_pasukan")
	private long idOperasiPenguatkuasaanPasukan;
	
	@ManyToOne
	@JoinColumn (name = "id_op" )
	private OperasiPenguatkuasaan idOperasiPenguatkuasaan;


	@Column (name = "nama")
	private String nama;

	@Column (name = "no_kp")
	private String noKp;


	@ManyToOne
	@JoinColumn (name = "peranan" )
	private KodPerananOperasi kodPerananOperasi;

	@Column (name = "kad_kuasa")
	private String kadKuasa;

	@ManyToOne
	@JoinColumn (name = "kod_caw")
	private KodCawangan cawangan;



	@Column (name = "saksi")
	private String saksi;

	@Column (name = "tempat_kerja")
	private String tempatKerja;
	
	
	@Embedded
	private InfoAudit infoAudit;


    public KodCawangan getCawangan() {
        return cawangan;
    }

    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
    }

    public String getSaksi() {
        return saksi;
    }

    public void setSaksi(String saksi) {
        this.saksi = saksi;
    }

    public String getTempatKerja() {
        return tempatKerja;
    }

    public void setTempatKerja(String tempatKerja) {
        this.tempatKerja = tempatKerja;
    }

  



    public OperasiPenguatkuasaan getIdOperasiPenguatkuasaan() {
        return idOperasiPenguatkuasaan;
    }

    public void setIdOperasiPenguatkuasaan(OperasiPenguatkuasaan idOperasiPenguatkuasaan) {
        this.idOperasiPenguatkuasaan = idOperasiPenguatkuasaan;
    }

    public long getIdOperasiPenguatkuasaanPasukan() {
        return idOperasiPenguatkuasaanPasukan;
    }

    public void setIdOperasiPenguatkuasaanPasukan(long idOperasiPenguatkuasaanPasukan) {
        this.idOperasiPenguatkuasaanPasukan = idOperasiPenguatkuasaanPasukan;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNoKp() {
        return noKp;
    }

    public void setNoKp(String noKp) {
        this.noKp = noKp;
    }

    public KodPerananOperasi getKodPerananOperasi() {
        return kodPerananOperasi;
    }

    public void setKodPerananOperasi(KodPerananOperasi kodPerananOperasi) {
        this.kodPerananOperasi = kodPerananOperasi;
    }


    public String getKadKuasa() {
        return kadKuasa;
    }

    public void setKadKuasa(String kadKuasa) {
        this.kadKuasa = kadKuasa;
    }




	public InfoAudit getInfoAudit() {
		return infoAudit;
	}

	public void setInfoAudit(InfoAudit infoAudit) {
		this.infoAudit = infoAudit;
	}
	
	

	
}
