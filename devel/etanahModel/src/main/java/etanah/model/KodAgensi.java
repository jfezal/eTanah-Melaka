package etanah.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
@Table (name = "kod_agensi")
@org.hibernate.annotations.Cache (usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE)
public class KodAgensi implements Serializable , Penyerah{

    @Id
    @Basic(optional = false)
    @Column(name = "KOD")
    private String kod;

    @Column(name = "KOD_KEMENTERIAN")
    private int kodKementerian;
    
    @Column(name = "NAMA", length = 100)
    private String nama;

    @Column(name = "ALAMAT1", length = 40)
    private String alamat1;

    @Column(name = "ALAMAT2", length = 40)
    private String alamat2;

    @Column(name = "ALAMAT3", length = 40)
    private String alamat3;

    @Column(name = "ALAMAT4", length = 40)
    private String alamat4;

    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn (name = "KOD_NEGERI")
    private KodNegeri kodNegeri;

    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn (name = "katg_agensi")
    private KodKategoriAgensi kategoriAgensi;


    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "kod_gelaran")
    private KodGelaran kodGelaran;
    
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "kod_daerah")
    private KodDaerah kodDaerah;

    @Column(name = "POSKOD", length = 5)
    private String poskod;

    @Column (name  = "aktif", length = 1)
    private char aktif;

    @Column (name  = "emel", length = 1)
    private String emel;
    
    @Column (name = "no_tel1", length = 20)
	private String noTelefon1;

	@Column (name = "no_tel2", length = 20)
	private String noTelefon2;

    @Column (name = "kod_emmkn")
    private String kodEmmkn;
    
    @ManyToOne
    @JoinColumn (name = "kod_pengenalan")
    private KodJenisPengenalan jenisPengenalan;
    
     @OneToMany(mappedBy = "kodAgensi")
    private List<PortalPengguna> senaraiPortalPengguna;


    @Embedded
    private InfoAudit infoAudit;    

    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    public int getKodKementerian() {
        return kodKementerian;
    }

    public void setKodKementerian(int kodKementerian) {
        this.kodKementerian = kodKementerian;
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

    public KodNegeri getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(KodNegeri kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public String getPoskod() {
        return poskod;
    }

    public void setPoskod(String poskod) {
        this.poskod = poskod;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    @Override
    public String getKodPenyerah() {
        return "05"; // kod agensi
    }

    @Override
    public long getIdPenyerah() {
        return Long.parseLong(kod);
    }

    @Override
    public KodJenisPengenalan getJenisPengenalan() {
        return jenisPengenalan;
    }
    
    public void setJenisPengenalan(KodJenisPengenalan jenisPengenalan) {
        this.jenisPengenalan = jenisPengenalan;
    }    

    @Override
    public String getNoPengenalan() {
        return "";
    }

    @Override
    public KodNegeri getNegeri() {
        return kodNegeri;
    }

    @Override
    public void setNegeri(KodNegeri negeri) {
        this.kodNegeri = kodNegeri;
    }

    public KodGelaran getKodGelaran() {
        return kodGelaran;
    }

    public void setKodGelaran(KodGelaran kodGelaran) {
        this.kodGelaran = kodGelaran;
    }



    public KodKategoriAgensi getKategoriAgensi() {
        return kategoriAgensi;
    }

    public void setKategoriAgensi(KodKategoriAgensi kategoriAgensi) {
        this.kategoriAgensi = kategoriAgensi;
    }

    public KodDaerah getKodDaerah() {
        return kodDaerah;
    }

    public void setKodDaerah(KodDaerah kodDaerah) {
        this.kodDaerah = kodDaerah;
    }

    
    public String getNoTelefon1() {
		return noTelefon1;
	}

	public void setNoTelefon1(String noTelefon1) {
		this.noTelefon1 = noTelefon1;
	}

	public String getNoTelefon2() {
		return noTelefon2;
	}

	public void setNoTelefon2(String noTelefon2) {
		this.noTelefon2 = noTelefon2;
	}

    @Override
    public char getAktif() {
        return aktif;
    }

    @Override
    public void setAktif(char aktif) {
        this.aktif = aktif;
    }

    public String getEmel() {
        return emel;
    }

    public void setEmel(String emel) {
        this.emel = emel;
    }

    public List<PortalPengguna> getSenaraiPortalPengguna() {
        return senaraiPortalPengguna;
    }

    public void setSenaraiPortalPengguna(List<PortalPengguna> senaraiPortalPengguna) {
        this.senaraiPortalPengguna = senaraiPortalPengguna;
    }

    /**
     * Kod yang digunakan oleh sistem e-MMKN, bagi tujuan integrasi shj.
     * @return
     */
    public String getKodEmmkn() {
        return kodEmmkn;
    }
}
