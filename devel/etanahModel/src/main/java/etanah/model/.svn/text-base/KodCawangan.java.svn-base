package etanah.model;

import java.io.Serializable;

import javax.persistence.*;
import java.sql.Blob;


@Entity
@Table (name = "kod_caw")
@org.hibernate.annotations.Cache (usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE)
public class KodCawangan implements Serializable{
    
    @Id
    @Column (name = "kod")
    private String kod;
    
    @ManyToOne
    @JoinColumn (name = "kod_daerah")
    private KodDaerah daerah;
    
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "kod_caw_utama")
    private KodCawangan cawanganUtama;
    
    @Column (name = "nama", unique = true)
    private String name;

    @Column (name = "aktif", length = 1, columnDefinition = "CHAR(1)")
    private char aktif;
    
    @Embedded
    private Alamat alamat;

    @Column (name = "kod_ptj")
    private String kodPTJ;

    @Column (name = "kod_jab_spek")
    private String kodJabatanSpek;
    
    @Column (name = "no_tel1")
    private String noTelefon;
    
    @Column (name = "no_tel2")
    private String noTelefon2;
    
    @Column (name = "NO_FAX")
    private String noFax;
    
    @Column (name = "akronim", length = 10)
    private String akronim;

    @Embedded
    private InfoAudit infoAudit;
    
    @Column(name = "cop")
    private Blob cop;

    
    public void setKod(String kod) {
        this.kod = kod;
    }

    public String getKod() {
        return kod;
    }

    public void setDaerah(KodDaerah daerah) {
        this.daerah = daerah;
    }

    public KodDaerah getDaerah() {
        return daerah;
    }

    public void setCawanganUtama(KodCawangan cawanganUtama) {
        this.cawanganUtama = cawanganUtama;
    }

    public KodCawangan getCawanganUtama() {
        return cawanganUtama;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAktif(char aktif) {
        this.aktif = aktif;
    }

    public char isAktif() {
        return aktif;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setAlamat(Alamat alamat) {
        this.alamat = alamat;
    }

    public Alamat getAlamat() {
        return alamat;
    }

    public String getKodPTJ() {
        return kodPTJ;
    }

    public void setKodPTJ(String kodPTJ) {
        this.kodPTJ = kodPTJ;
    }

    public String getKodJabatanSpek() {
        return kodJabatanSpek;
    }

    public void setKodJabatanSpek(String kodJabatanSpek) {
        this.kodJabatanSpek = kodJabatanSpek;
    }

    public String getNoTelefon() {
        return noTelefon;
    }

    public void setNoTelefon(String noTelefon) {
        this.noTelefon = noTelefon;
    }

    public String getAkronim() {
        return akronim;
    }

    public void setAkronim(String akronim) {
        this.akronim = akronim;
    }

    public String getNoTelefon2() {
        return noTelefon2;
    }

    public void setNoTelefon2(String noTelefon2) {
        this.noTelefon2 = noTelefon2;
    }

    public String getNoFax() {
        return noFax;
    }

    public void setNoFax(String noFax) {
        this.noFax = noFax;
    }

    public Blob getCop() {
        return cop;
    }

    public void setCop(Blob cop) {
        this.cop = cop;
    }
    
}
