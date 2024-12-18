package etanah.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "wakil_kuasa_pnerima")
@SequenceGenerator(name = "seq_wakil_kuasa_penerima", sequenceName = "seq_wakil_kuasa_penerima")
public class WakilKuasaPenerima implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_wakil_kuasa_penerima")
    @Column(name = "id_pnerima")
    private long idPenerima;
    @ManyToOne
    @JoinColumn(name = "kod_caw", nullable = false)
    private KodCawangan cawangan;
    @ManyToOne
    @JoinColumn(name = "id_wakil", nullable = false)
    private WakilKuasa wakilKuasa;
        
    @ManyToOne
    @JoinColumn(name = "kod_pengenalan")
    private KodJenisPengenalan jenisPengenalan;
    @Column(name = "no_pengenalan")
    private String noPengenalan;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_peguam")
    private Peguam peguam;
    @Column(name = "nama")
    private String nama;
    @Column(name = "alamat1")
    private String alamat1;
    @Column(name = "alamat2")
    private String alamat2;
    @Column(name = "alamat3")
    private String alamat3;
    @Column(name = "alamat4")
    private String alamat4;
    @Column(name = "poskod")
    private String poskod;
    @ManyToOne
    @JoinColumn(name = "kod_negeri")
    private KodNegeri negeri;
    
    @Column (name = "amaun_maks")
    private BigDecimal amaunMaksima;
    
    @Column(name = "sts", columnDefinition = "CHAR(2)")
    private String status;
        
    /**
     * Guna untuk pembetulan 380 sahaja
     */
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "id_mohon_betul")
	private Permohonan permohonanPembetulan;
    
    @ManyToOne
    @JoinColumn (name = "id_pnerima_betul")
    private WakilKuasaPenerima betulPenerima;
	
    @OneToMany (mappedBy = "betulPenerima")
    private List<WakilKuasaPenerima> senaraiPembetulan;
    
    @Embedded
    private InfoAudit infoAudit;

    public long getIdPenerima() {
        return idPenerima;
    }

    public void setIdPenerima(long idPenerima) {
        this.idPenerima = idPenerima;
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

    public void setPeguam(Peguam peguam) {
        this.peguam = peguam;
    }

    public Peguam getPeguam() {
        return peguam;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat1() {
        return alamat1;
    }

    public void setAlamat1(String alamat1) {
        this.alamat1 = alamat1;
    }

    public String getAlamat2() {
        return alamat2;
    }

    public void setAlamat2(String alamat2) {
        this.alamat2 = alamat2;
    }

    public String getAlamat3() {
        return alamat3;
    }

    public void setAlamat3(String alamat3) {
        this.alamat3 = alamat3;
    }

    public String getAlamat4() {
        return alamat4;
    }

    public void setAlamat4(String alamat4) {
        this.alamat4 = alamat4;
    }

    public String getPoskod() {
        return poskod;
    }

    public void setPoskod(String poskod) {
        this.poskod = poskod;
    }

    public KodNegeri getNegeri() {
        return negeri;
    }

    public void setNegeri(KodNegeri negeri) {
        this.negeri = negeri;
    }

    public void setAmaunMaksima(BigDecimal amaunMaksima) {
		this.amaunMaksima = amaunMaksima;
	}

	public BigDecimal getAmaunMaksima() {
		return amaunMaksima;
	}

	public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setPermohonanPembetulan(Permohonan permohonanPembetulan) {
		this.permohonanPembetulan = permohonanPembetulan;
	}

	public Permohonan getPermohonanPembetulan() {
		return permohonanPembetulan;
	}
	
    public void setBetulPenerima(WakilKuasaPenerima betulPenerima) {
		this.betulPenerima = betulPenerima;
	}

	public WakilKuasaPenerima getBetulPenerima() {
		return betulPenerima;
	}

	public void setSenaraiPembetulan(List<WakilKuasaPenerima> senaraiPembetulan) {
		this.senaraiPembetulan = senaraiPembetulan;
	}

	public List<WakilKuasaPenerima> getSenaraiPembetulan() {
		return senaraiPembetulan;
	}

	public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }
}
