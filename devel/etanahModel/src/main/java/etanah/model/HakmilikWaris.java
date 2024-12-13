package etanah.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
@Table (name = "hakmilik_pihak_waris")
@SequenceGenerator(name = "seq_hakmilik_pihak_waris", sequenceName = "seq_hakmilik_pihak_waris")
@org.hibernate.annotations.Entity (dynamicInsert = true, dynamicUpdate = true)
public class HakmilikWaris implements Serializable {

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_hakmilik_pihak_waris")
	@Column (name = "id_hpw")
	private long idWaris;
	
	@ManyToOne
	@JoinColumn (name = "kod_caw", nullable = false)
	private KodCawangan cawangan;
	
	@ManyToOne
	@JoinColumn (name = "id_hp")
	private HakmilikPihakBerkepentingan pemegangAmanah;
		
	@Column (name = "nama", length = 250)
	private String nama;
	
	@ManyToOne (fetch = FetchType.EAGER)
	@JoinColumn (name = "kod_pengenalan")
	private KodJenisPengenalan jenisPengenalan;
	
	@Column (name = "no_pengenalan")
	private String noPengenalan;

    @ManyToOne
    @JoinColumn(name = "kod_warganegara")
    private KodWarganegara wargaNegara;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "kod_negeri")
    private KodNegeri negeri;
	
	@Column (name = "syer_pembilang")
	private long syerPembilang;
	
	@Column (name = "syer_penyebut")
	private long syerPenyebut;
		
	/**
	 * Hanya untuk kegunaan Pembetulan 380
	 */
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "id_mohon_betul")
	private Permohonan permohonanPembetulan;
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "id_hpw_betul")
	private HakmilikWaris betulWaris;
	
	@OneToMany (mappedBy = "betulWaris")
	private List<HakmilikWaris> senaraiPembetulan;

         @Column(name = "sts", columnDefinition = "CHAR(2)")
        private String status;

	@Embedded
	private InfoAudit infoAudit;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }        

	public long getIdWaris() {
		return idWaris;
	}

	public void setIdWaris(long idWaris) {
		this.idWaris = idWaris;
	}

	public void setCawangan(KodCawangan cawangan) {
		this.cawangan = cawangan;
	}

	public KodCawangan getCawangan() {
		return cawangan;
	}

	public HakmilikPihakBerkepentingan getPemegangAmanah() {
		return pemegangAmanah;
	}

	public void setPemegangAmanah(HakmilikPihakBerkepentingan pemegangAmanah) {
		this.pemegangAmanah = pemegangAmanah;
	}

	public void setBetulWaris(HakmilikWaris betulWaris) {
		this.betulWaris = betulWaris;
	}

	public HakmilikWaris getBetulWaris() {
		return betulWaris;
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

	public long getSyerPembilang() {
		return syerPembilang;
	}

	public void setSyerPembilang(long syerPembilang) {
		this.syerPembilang = syerPembilang;
	}

	public long getSyerPenyebut() {
		return syerPenyebut;
	}

	public void setSyerPenyebut(long syerPenyebut) {
		this.syerPenyebut = syerPenyebut;
	}

	public void setPermohonanPembetulan(Permohonan permohonan) {
		this.permohonanPembetulan = permohonan;
	}

	public Permohonan getPermohonanPembetulan() {
		return permohonanPembetulan;
	}
	
	public void setSenaraiPembetulan(List<HakmilikWaris> senaraiPembetulan) {
		this.senaraiPembetulan = senaraiPembetulan;
	}

	public List<HakmilikWaris> getSenaraiPembetulan() {
		return senaraiPembetulan;
	}

	public InfoAudit getInfoAudit() {
		return infoAudit;
	}

	public void setInfoAudit(InfoAudit infoAudit) {
		this.infoAudit = infoAudit;
	}

        public KodNegeri getNegeri() {
            return negeri;
        }

        public void setNegeri(KodNegeri negeri) {
            this.negeri = negeri;
        }

        public KodWarganegara getWargaNegara() {
            return wargaNegara;
        }

        public void setWargaNegara(KodWarganegara wargaNegara) {
            this.wargaNegara = wargaNegara;
        }
	
}
