package etanah.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "pihak_wakil")
@SequenceGenerator(name = "seq_pihak_wakil", sequenceName = "seq_pihak_wakil")
public class WakilPihak implements Serializable {
    
    
	@Id
        @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_pihak_wakil")
	@Column(name = "id_wakil")
	private String idWakil;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_mohon", nullable = true)
	private Permohonan permohonan;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_pihak", nullable = true)
	private Pihak pihak;

	@Column(name = "semua_hakmilik", length = 1)
	private char untukSemuaHakmilik;

//	@OneToMany(mappedBy = "wakilPihak", cascade = CascadeType.ALL)
//	private List<WakilHakmilik> senaraiWakilHakmilik;

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

	@ManyToOne
	@JoinColumn(name = "kod_pengenalan")
	private KodJenisPengenalan jenisPengenalan;

	@Column(name = "no_pengenalan")
	private String noPengenalan;

	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "id_peguam")
	private Peguam peguam;
	
	@Column(name = "nama")
	private String nama;
	
	@Column (name = "alamat1")
	private String alamat1;
	
	@Column (name = "alamat2")
	private String alamat2;
	
	@Column (name = "alamat3")
	private String alamat3;
		
	@Column (name = "alamat4")
	private String alamat4;
	
	@Column (name = "poskod")
	private String poskod;

	@ManyToOne
	@JoinColumn (name = "kod_negeri")
	private KodNegeri negeri;

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

	@Embedded
	private InfoAudit infoAudit;

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

	public Pihak getPihak() {
		return pihak;
	}

	public void setPihak(Pihak pihak) {
		this.pihak = pihak;
	}

	public char getUntukSemuaHakmilik() {
		return untukSemuaHakmilik;
	}

	public void setUntukSemuaHakmilik(char untukSemuaHakmilik) {
		this.untukSemuaHakmilik = untukSemuaHakmilik;
	}

//	public void setSenaraiWakilHakmilik(List<WakilHakmilik> senaraiWakilHakmilik) {
//		this.senaraiWakilHakmilik = senaraiWakilHakmilik;
//	}

//	public List<WakilHakmilik> getSenaraiWakilHakmilik() {
//		return senaraiWakilHakmilik;
//	}

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
	
	public void setPeguam(Peguam peguam){
		this.peguam = peguam;
	}
	
	public Peguam getPeguam(){
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

	public InfoAudit getInfoAudit() {
		return infoAudit;
	}

	public void setInfoAudit(InfoAudit infoAudit) {
		this.infoAudit = infoAudit;
	}

}
