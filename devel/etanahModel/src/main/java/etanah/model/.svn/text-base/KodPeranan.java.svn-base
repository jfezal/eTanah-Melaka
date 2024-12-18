package etanah.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
@Table (name = "kod_peranan")
@org.hibernate.annotations.Cache (usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE)
public class KodPeranan implements Serializable {
    @Id
    @Column (name = "kod", length = 4)
    private String kod;
    
    @Column (name = "nama", length = 32)
    private String nama;
    
    @Column (name = "aktif")
    private char aktif;
    
    @Column (name = "skrin")
    private String defaultSreen;

    @Column (name = "kump_bpel")
    private String kumpBPEL;
    
    @OneToMany (mappedBy  = "peranan")
    private List<PenggunaPeranan> senaraiPengguna;
    
    @ManyToOne
    @JoinColumn(name = "kod_jab")
    private KodJabatan kodJabatan;

    @ManyToOne
    @JoinColumn(name = "kod_klas")
    private KodKlasifikasi kodKlasifikasi;

    @Embedded
    InfoAudit infoAudit;

    public void setKod(String kod) {
        this.kod = kod;
    }

    public String getKod() {
        return kod;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNama() {
        return nama;
    }

    public void setAktif(char aktif) {
        this.aktif = aktif;
    }

    public char getAktif() {
        return aktif;
    }
    
    public String getDefaultScreen(){
    	return defaultSreen;
    }
    
    public void setDefaultScreen(String URI){
    	this.defaultSreen = URI;
    }

    public void setSenaraiPengguna(List<PenggunaPeranan> senaraiPengguna) {
        this.senaraiPengguna = senaraiPengguna;
    }

    public List<PenggunaPeranan> getSenaraiPengguna() {
        return senaraiPengguna;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public String getKumpBPEL() {
        return kumpBPEL;
    }

    public void setKumpBPEL(String kumpBPEL) {
        this.kumpBPEL = kumpBPEL;
    }

    public KodJabatan getKodJabatan() {
        return kodJabatan;
    }

    public void setKodJabatan(KodJabatan kodJabatan) {
        this.kodJabatan = kodJabatan;
    }

    public KodKlasifikasi getKodKlasifikasi() {
        return kodKlasifikasi;
    }

    public void setKodKlasifikasi(KodKlasifikasi kodKlasifikasi) {
        this.kodKlasifikasi = kodKlasifikasi;
    }

    


}
