package etanah.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table (name = "carian_hakmilik")
@SequenceGenerator (name = "seq_carian_hakmilik", sequenceName = "seq_carian_hakmilik")
public class CarianHakmilik implements Serializable {
	  
	@Id @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "seq_carian_hakmilik")
	@Column (name = "id_ch")
	private long idCarianHakmilik;
	
	@ManyToOne
	@JoinColumn (name = "id_carian", nullable = false)
	private PermohonanCarian permohonanCarian;
	
	@ManyToOne (fetch = FetchType.EAGER)
	@JoinColumn (name = "kod_caw", nullable = false)
	private KodCawangan cawangan;
	
	@Column (name = "id_hakmilik")
	private String idHakmilik;
	
	@ManyToOne
	@JoinColumn (name = "kod_urusan")
	private KodUrusan kodUrusan;
	
	@Column (name = "id_mohon")
	private String idPerserahan;

        @Column(name = "no_bngn")
        private String noBangunan;

        @Column(name = "no_tgkt")
        private String noTingkat;
        
        @Column(name = "no_petak")
        private String noPetak;
	
	@Embedded
	private InfoAudit infoAudit;

	public long getIdCarianHakmilik() {
		return idCarianHakmilik;
	}

	public void setIdCarianHakmilik(long idCarianHakmilik) {
		this.idCarianHakmilik = idCarianHakmilik;
	}

	public PermohonanCarian getPermohonanCarian() {
		return permohonanCarian;
	}

	public void setPermohonanCarian(PermohonanCarian permohonanCarian) {
		this.permohonanCarian = permohonanCarian;
	}

	public KodCawangan getCawangan() {
		return cawangan;
	}

	public void setCawangan(KodCawangan cawangan) {
		this.cawangan = cawangan;
	}

	public String getIdHakmilik() {
		return idHakmilik;
	}

	public void setIdHakmilik(String idHakmilik) {
		this.idHakmilik = idHakmilik;
	}

	public KodUrusan getKodUrusan() {
		return kodUrusan;
	}

	public void setKodUrusan(KodUrusan kodUrusan) {
		this.kodUrusan = kodUrusan;
	}

	public String getIdPerserahan() {
		return idPerserahan;
	}

	public void setIdPerserahan(String idPerserahan) {
		this.idPerserahan = idPerserahan;
	}

        public String getNoBangunan() {
            return noBangunan;
        }

        public void setNoBangunan(String noBangunan) {
            this.noBangunan = noBangunan;
        }

        public String getNoPetak() {
            return noPetak;
        }

        public void setNoPetak(String noPetak) {
            this.noPetak = noPetak;
        }

        public String getNoTingkat() {
            return noTingkat;
        }

        public void setNoTingkat(String noTingkat) {
            this.noTingkat = noTingkat;
        }

	public InfoAudit getInfoAudit() {
		return infoAudit;
	}

	public void setInfoAudit(InfoAudit infoAudit) {
		this.infoAudit = infoAudit;
	}

}
