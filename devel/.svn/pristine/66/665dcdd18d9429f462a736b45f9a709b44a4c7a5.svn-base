package etanah.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Entity
@Table (name = "waran")
@SequenceGenerator(name = "seq_waran", sequenceName = "seq_waran")
public class Waran implements Serializable {

	@Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_waran")
	@Column (name = "id_waran")
	private long idWaran;
	
	@ManyToOne
	@JoinColumn (name = "id_mohon" )
	private Permohonan idPermohonan;


	@ManyToOne
	@JoinColumn (name = "id_hakmilik" )
	private Hakmilik hakmilik;

	@ManyToOne
	@JoinColumn (name = "kod_caw")
	private KodCawangan cawangan;



    @Column(name = "trh_waran_keluar")
    private Date tarikhWaranKeluar;

    @Column(name = "trh_akhir_sah")
    private Date tarikhAkhirSah;
	
	@Embedded
	private InfoAudit infoAudit;


    public KodCawangan getCawangan() {
        return cawangan;
    }

    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public long getIdWaran() {
        return idWaran;
    }

    public void setIdWaran(long idWaran) {
        this.idWaran = idWaran;
    }

    public Date getTarikhAkhirSah() {
        return tarikhAkhirSah;
    }

    public void setTarikhAkhirSah(Date tarikhAkhirSah) {
        this.tarikhAkhirSah = tarikhAkhirSah;
    }

    public Date getTarikhWaranKeluar() {
        return tarikhWaranKeluar;
    }

    public void setTarikhWaranKeluar(Date tarikhWaranKeluar) {
        this.tarikhWaranKeluar = tarikhWaranKeluar;
    }

    

    public Permohonan getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(Permohonan idPermohonan) {
        this.idPermohonan = idPermohonan;
    }




	public InfoAudit getInfoAudit() {
		return infoAudit;
	}

	public void setInfoAudit(InfoAudit infoAudit) {
		this.infoAudit = infoAudit;
	}
	
	

	
}
