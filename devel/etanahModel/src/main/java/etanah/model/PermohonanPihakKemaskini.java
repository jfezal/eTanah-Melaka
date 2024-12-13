package etanah.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table (name = "mohon_pihak_kkini")
@SequenceGenerator(name = "seq_mohon_pihak_kkini", sequenceName = "seq_mohon_pihak_kkini")
public class PermohonanPihakKemaskini implements Serializable {

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_mohon_pihak_kkini")
	@Column (name = "id_kkini")
	private long idKemaskini;
	
	@ManyToOne
	@JoinColumn (name = "kod_caw")
	private KodCawangan cawangan;
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "id_mohon")
	private Permohonan permohonan;
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "id_atas_pihak")
	private PermohonanAtasPihakBerkepentingan atasPihakBerkepentingan;

        @ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "id_pemohon")
	private Pemohon pemohon;
        
        @ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "id_hakmilik")
	private Hakmilik hakmilik;
	
	@Column (name = "nama_medan", length = 50)
	private String namaMedan;
	
	@Column (name = "nilai_lama", length = 250)
	private String nilaiLama;
	
	@Column (name = "nilai_baru", length = 250)
	private String nilaiBaru;
        
        @ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "id_hp")
	private HakmilikPihakBerkepentingan pihakTerlibat;
        
        @ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "id_hpw")
	private HakmilikWaris warisTerlibat;
	
	@Embedded
	private InfoAudit infoAudit;
        
        @ManyToOne
        @JoinColumn(name = "KOD_PB")
        private KodJenisPihakBerkepentingan jenis;
        
        @ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_pihak")
	private Pihak pihak;
        
        @Column (name = "ID_MOHON_LAMA")
        private String idPermohonanLama;        
        
        @Column (name = "ID_MP")
        private String idMohonPihak;        
        @Column (name = "NILAI_BKAITAN1")
        private String nilaiBerkaitan1;        
        @Column (name = "NILAI_BKAITAN2")
        private String nilaiBerkaitan2;        
        @Column (name = "NILAI_BKAITAN3")
        private String nilaiBerkaitan3;        

	public long getIdKemaskini() {
		return idKemaskini;
	}

	public void setIdKemaskini(long idKemaskini) {
		this.idKemaskini = idKemaskini;
	}

	public void setCawangan(KodCawangan cawangan) {
		this.cawangan = cawangan;
	}

	public KodCawangan getCawangan() {
		return cawangan;
	}

	public Permohonan getPermohonan() {
		return permohonan;
	}

	public void setPermohonan(Permohonan permohonan) {
		this.permohonan = permohonan;
	}

	public void setAtasPihakBerkepentingan(PermohonanAtasPihakBerkepentingan atasPihakBerkepentingan) {
		this.atasPihakBerkepentingan = atasPihakBerkepentingan;
	}

	public PermohonanAtasPihakBerkepentingan getAtasPihakBerkepentingan() {
		return atasPihakBerkepentingan;
	}

	public String getNamaMedan() {
		return namaMedan;
	}

	public void setNamaMedan(String namaMedan) {
		this.namaMedan = namaMedan;
	}

	public String getNilaiLama() {
		return nilaiLama;
	}

	public void setNilaiLama(String nilaiLama) {
		this.nilaiLama = nilaiLama;
	}

	public String getNilaiBaru() {
		return nilaiBaru;
	}

	public void setNilaiBaru(String nilaiBaru) {
		this.nilaiBaru = nilaiBaru;
	}

	public InfoAudit getInfoAudit() {
		return infoAudit;
	}

	public void setInfoAudit(InfoAudit infoAudit) {
		this.infoAudit = infoAudit;
	}

        public Pemohon getPemohon() {
            return pemohon;
        }

        public void setPemohon(Pemohon pemohon) {
            this.pemohon = pemohon;
        }

    public HakmilikPihakBerkepentingan getPihakTerlibat() {
        return pihakTerlibat;
    }

    public void setPihakTerlibat(HakmilikPihakBerkepentingan pihakTerlibat) {
        this.pihakTerlibat = pihakTerlibat;
    }

    public HakmilikWaris getWarisTerlibat() {
        return warisTerlibat;
    }

    public void setWarisTerlibat(HakmilikWaris warisTerlibat) {
        this.warisTerlibat = warisTerlibat;
    }

    public KodJenisPihakBerkepentingan getJenis() {
        return jenis;
    }

    public void setJenis(KodJenisPihakBerkepentingan jenis) {
        this.jenis = jenis;
    }

    public String getIdPermohonanLama() {
        return idPermohonanLama;
    }

    public void setIdPermohonanLama(String idPermohonanLama) {
        this.idPermohonanLama = idPermohonanLama;
    }

    public Pihak getPihak() {
        return pihak;
    }

    public void setPihak(Pihak pihak) {
        this.pihak = pihak;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public String getIdMohonPihak() {
        return idMohonPihak;
    }

    public void setIdMohonPihak(String idMohonPihak) {
        this.idMohonPihak = idMohonPihak;
    }

    public String getNilaiBerkaitan1() {
        return nilaiBerkaitan1;
    }

    public void setNilaiBerkaitan1(String nilaiBerkaitan1) {
        this.nilaiBerkaitan1 = nilaiBerkaitan1;
    }

    public String getNilaiBerkaitan2() {
        return nilaiBerkaitan2;
    }

    public void setNilaiBerkaitan2(String nilaiBerkaitan2) {
        this.nilaiBerkaitan2 = nilaiBerkaitan2;
    }

    public String getNilaiBerkaitan3() {
        return nilaiBerkaitan3;
    }

    public void setNilaiBerkaitan3(String nilaiBerkaitan3) {
        this.nilaiBerkaitan3 = nilaiBerkaitan3;
    }
}
