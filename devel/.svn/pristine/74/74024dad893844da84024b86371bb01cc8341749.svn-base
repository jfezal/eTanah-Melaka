package etanah.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Senarai rujukan
 */
@Entity
@Table (name = "senarai_ruj")
//@SequenceGenerator (name = "seq_senarai_ruj", sequenceName = "seq_senarai_ruj")
public class SenaraiRujukan implements Serializable{

    @Id //@GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "seq_senarai_ruj")
    @Column (name = "kod")
    private String kod;

    @ManyToOne
    @JoinColumn (name = "kod_caw")
    private KodCawangan cawangan;

    @ManyToOne
    @JoinColumn (name = "kod_senarai")
    private KodSenarai senarai;

    @Column (name = "nama")
    private String nama;

    @Column (name = "perihal")
    private String perihal;

    @Embedded
    private Alamat alamat;

    @Column (name = "NO_TEL1")
    private String noTel1;

    @Column (name = "NO_TEL2")
    private String noTel2;

    @Column (name = "NO_FAX")
    private String nofax;

    @Column (name = "EMAIL")
    private String email;

    @Embedded
    private InfoAudit infoAudit;

    public Alamat getAlamat() {
        return alamat;
    }

    public void setAlamat(Alamat alamat) {
        this.alamat = alamat;
    }

    public KodCawangan getCawangan() {
        return cawangan;
    }

    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

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

    public String getPerihal() {
        return perihal;
    }

    public void setPerihal(String perihal) {
        this.perihal = perihal;
    }

    public KodSenarai getSenarai() {
        return senarai;
    }

    public void setSenarai(KodSenarai senarai) {
        this.senarai = senarai;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNoTel1() {
        return noTel1;
    }

    public void setNoTel1(String noTel1) {
        this.noTel1 = noTel1;
    }

    public String getNoTel2() {
        return noTel2;
    }

    public void setNoTel2(String noTel2) {
        this.noTel2 = noTel2;
    }

    public String getNofax() {
        return nofax;
    }

    public void setNofax(String nofax) {
        this.nofax = nofax;
    }
}
