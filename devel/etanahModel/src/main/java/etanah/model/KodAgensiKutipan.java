package etanah.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
@Table (name = "kod_agensi_kutipan")
@org.hibernate.annotations.Cache (usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE)
public class KodAgensiKutipan implements Serializable{

    @Id
    @Basic(optional = false)
    @Column(name = "KOD")
    private String kod;
    
    @Column(name = "NAMA", length = 100)
    private String nama;
    
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "no_akaun")
    private Akaun akaun;

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

    @Column(name = "POSKOD", length = 5)
    private String poskod;

    @Column (name  = "aktif", length = 1)
    private char aktif;
    
    @OneToMany (mappedBy = "agensiKutipan", fetch = FetchType.LAZY)
    private List<KodAgensiKutipanCawangan> senaraiCawangan;
    
    @Embedded
    private InfoAudit infoAudit;    

    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setAkaun(Akaun akaun) {
		this.akaun = akaun;
	}

	public Akaun getAkaun() {
		return akaun;
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

    public char getAktif() {
        return aktif;
    }

    public void setAktif(char aktif) {
        this.aktif = aktif;
    }
	
    public void setSenaraiCawangan(List<KodAgensiKutipanCawangan> senaraiCawangan) {
		this.senaraiCawangan = senaraiCawangan;
	}

	public List<KodAgensiKutipanCawangan> getSenaraiCawangan() {
		return senaraiCawangan;
	}

	public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }


}
