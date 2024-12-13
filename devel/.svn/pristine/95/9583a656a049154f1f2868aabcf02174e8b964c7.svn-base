package etanah.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "wakil_kuasa")
public class WakilKuasa implements Serializable {
	@Id
	@Column(name = "id_wakil")
	private String idWakil;

        //added by Fairul
        @ManyToOne
        @JoinColumn (name = "kod_caw", nullable = false)
	private KodCawangan cawangan;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_mohon", nullable = false)
	private Permohonan permohonan;

	@OneToMany(mappedBy = "wakilKuasa", fetch = FetchType.LAZY)
	private List<WakilKuasaPemberi> senaraiPemberi;

        @Column(name = "semua_hakmilik", length = 1)
	private char untukSemuaHakmilik;

	@OneToMany(mappedBy = "wakilKuasa", cascade = CascadeType.ALL)
	private List<WakilKuasaHakmilik> senaraiHakmilik;

	@Column(name = "trh_daftar")
	private Date tarikhDaftar;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_folder")
	private FolderDokumen folder;

	@Column(name = "aktif")
	private char aktif;

	@Column(name = "tempoh_thn")
	private int tempohTahun;

	@Column(name = "tempoh_bln")
	private int tempohBulan;

	@Column(name = "tempoh_hari")
	private int tempohHari;

	@Column(name = "trh_tamat")
	private Date tarikhTamat;

	@Column(name = "no_ruj")
	private String noRujukan;

	@OneToMany (mappedBy = "wakilKuasa", fetch = FetchType.LAZY)
	private List<WakilKuasaPenerima> senaraiPenerima;
	
	@Column(name = "amaun_maks")
	private BigDecimal amaunMaksima;

	@Column(name = "kuasa_am", length = 1)
	private char kuasaAm;

	@Column(name = "kuasa_pindahmilik", length = 1)
	private char kuasaPindahMilik;

	@Column(name = "kuasa_gadai", length = 1)
	private char kuasaGadai;

	@Column(name = "kuasa_lps_gadai", length = 1)
	private char kuasaLepasGadai;

	@Column(name = "kuasa_kaveat", length = 1)
	private char kuasaKaveat;

	@Column(name = "kuasa_tarik_kaveat", length = 1)
	private char kuasaTarikKaveat;

	@Column(name = "kuasa_lps_kaveat", length = 1)
	private char kuasaLepasKaveat;

	@Column(name = "kuasa_pajak", length = 1)
	private char kuasaPajak;

	@Column(name = "kuasa_pajak_kecil", length = 1)
	private char kuasaPajakKecil;

	@Column(name = "kuasa_sewa", length = 1)
	private char kuasaSewa;

	@Column(name = "kuasa_lain1", length = 50)
	private String kuasaLain1;

	@Column(name = "kuasa_lain2", length = 50)
	private String kuasaLain2;
	
	@Column (name = "syarat")
	private String syaratTambahan;
        
        @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "KOD_JENIS_SB")
	private KodJenisSuratKuasa jenisSuratKuasa;

	@Embedded
	private InfoAudit infoAudit;

    public KodCawangan getCawangan() {
		return cawangan;
	}

	public void setCawangan(KodCawangan cawangan) {
		this.cawangan = cawangan;
	}

	public String getIdWakil() {
		return idWakil;
	}

	public void setIdWakil(String idWakil) {
		this.idWakil = idWakil;
	}

	public Permohonan getPermohonan() {
		return permohonan;
	}

	public void setPermohonan(Permohonan permohonan) {
		this.permohonan = permohonan;
	}

	public void setSenaraiPemberi(List<WakilKuasaPemberi> senaraiPemberi) {
		this.senaraiPemberi = senaraiPemberi;
	}

	public List<WakilKuasaPemberi> getSenaraiPemberi() {
		return senaraiPemberi;
	}

        //new added
    public List<WakilKuasaPenerima> getSenaraiPenerima() {
        return senaraiPenerima;
    }

    public void setSenaraiPenerima(List<WakilKuasaPenerima> senaraiPenerima) {
        this.senaraiPenerima = senaraiPenerima;
    }

        

	public char getUntukSemuaHakmilik() {
		return untukSemuaHakmilik;
	}

	public void setUntukSemuaHakmilik(char untukSemuaHakmilik) {
		this.untukSemuaHakmilik = untukSemuaHakmilik;
	}

	public void setSenaraiHakmilik(List<WakilKuasaHakmilik> senaraiHakmilik) {
		this.senaraiHakmilik = senaraiHakmilik;
	}

	public List<WakilKuasaHakmilik> getSenaraiHakmilik() {
		return senaraiHakmilik;
	}

	public Date getTarikhDaftar() {
		return tarikhDaftar;
	}

	public void setTarikhDaftar(Date tarikhDaftar) {
		this.tarikhDaftar = tarikhDaftar;
	}

	public FolderDokumen getFolder() {
		return folder;
	}

	public void setFolder(FolderDokumen folder) {
		this.folder = folder;
	}

	public char getAktif() {
		return aktif;
	}

	public void setAktif(char aktif) {
		this.aktif = aktif;
	}

	public int getTempohTahun() {
		return tempohTahun;
	}

	public void setTempohTahun(int tempohTahun) {
		this.tempohTahun = tempohTahun;
	}

	public int getTempohBulan() {
		return tempohBulan;
	}

	public void setTempohBulan(int tempohBulan) {
		this.tempohBulan = tempohBulan;
	}

	public int getTempohHari() {
		return tempohHari;
	}

	public void setTempohHari(int tempohHari) {
		this.tempohHari = tempohHari;
	}

	public Date getTarikhTamat() {
		return tarikhTamat;
	}

	public void setTarikhTamat(Date tarikhTamat) {
		this.tarikhTamat = tarikhTamat;
	}

	public String getNoRujukan() {
		return noRujukan;
	}

	public void setNoRujukan(String noRujukan) {
		this.noRujukan = noRujukan;
	}

	public BigDecimal getAmaunMaksima() {
		return amaunMaksima;
	}

	public void setAmaunMaksima(BigDecimal amaunMaksima) {
		this.amaunMaksima = amaunMaksima;
	}

	public char getKuasaAm() {
		return kuasaAm;
	}

	public void setKuasaAm(char kuasaAm) {
		this.kuasaAm = kuasaAm;
	}

	public char getKuasaPindahMilik() {
		return kuasaPindahMilik;
	}

	public void setKuasaPindahMilik(char kuasaPindahMilik) {
		this.kuasaPindahMilik = kuasaPindahMilik;
	}

	public char getKuasaGadai() {
		return kuasaGadai;
	}

	public void setKuasaGadai(char kuasaGadai) {
		this.kuasaGadai = kuasaGadai;
	}

	public char getKuasaLepasGadai() {
		return kuasaLepasGadai;
	}

	public void setKuasaLepasGadai(char kuasaLepasGadai) {
		this.kuasaLepasGadai = kuasaLepasGadai;
	}

	public char getKuasaKaveat() {
		return kuasaKaveat;
	}

	public void setKuasaKaveat(char kuasaKaveat) {
		this.kuasaKaveat = kuasaKaveat;
	}

	public char getKuasaTarikKaveat() {
		return kuasaTarikKaveat;
	}

	public void setKuasaTarikKaveat(char kuasaTarikKaveat) {
		this.kuasaTarikKaveat = kuasaTarikKaveat;
	}

	public char getKuasaLepasKaveat() {
		return kuasaLepasKaveat;
	}

	public void setKuasaLepasKaveat(char kuasaLepasKaveat) {
		this.kuasaLepasKaveat = kuasaLepasKaveat;
	}

	public char getKuasaPajak() {
		return kuasaPajak;
	}

	public void setKuasaPajak(char kuasaPajak) {
		this.kuasaPajak = kuasaPajak;
	}

	public char getKuasaPajakKecil() {
		return kuasaPajakKecil;
	}

	public void setKuasaPajakKecil(char kuasaPajakKecil) {
		this.kuasaPajakKecil = kuasaPajakKecil;
	}

	public char getKuasaSewa() {
		return kuasaSewa;
	}

	public void setKuasaSewa(char kuasaSewa) {
		this.kuasaSewa = kuasaSewa;
	}

	public String getKuasaLain1() {
		return kuasaLain1;
	}

	public void setKuasaLain1(String kuasaLain1) {
		this.kuasaLain1 = kuasaLain1;
	}

	public String getKuasaLain2() {
		return kuasaLain2;
	}

	public void setKuasaLain2(String kuasaLain2) {
		this.kuasaLain2 = kuasaLain2;
	}

	public void setSyaratTambahan(String syaratTambahan) {
		this.syaratTambahan = syaratTambahan;
	}

	public String getSyaratTambahan() {
		return syaratTambahan;
	}

	public InfoAudit getInfoAudit() {
		return infoAudit;
	}

	public void setInfoAudit(InfoAudit infoAudit) {
		this.infoAudit = infoAudit;
	}

    public KodJenisSuratKuasa getJenisSuratKuasa() {
        return jenisSuratKuasa;
    }

    public void setJenisSuratKuasa(KodJenisSuratKuasa jenisSuratKuasa) {
        this.jenisSuratKuasa = jenisSuratKuasa;
    }

}
