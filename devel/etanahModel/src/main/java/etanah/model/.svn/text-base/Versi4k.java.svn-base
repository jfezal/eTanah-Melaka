package etanah.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "versi_4k")
//@SequenceGenerator(name = "seq_mohon_fasa", sequenceName = "seq_mohon_fasa")
@org.hibernate.annotations.Entity(selectBeforeUpdate = true,
        dynamicInsert = true, dynamicUpdate = true)
public class Versi4k implements Serializable {

//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_mohon_fasa")
    @Id
    @Column(name = "ID_HM", length = 23)
    private String idHakmilikStrata;
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "ID_HM")
//    private Hakmilik idHakmilikStrata;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_HAKMILIK_INDUK")
    private Hakmilik hakmilikInduk;
//    @Column(name = "ID_HAKMILIK_INDUK", length = 23)
//    private String hakmilikInduk;

//    @ManyToOne
//    @JoinColumn(name = "id_dokumen")
//    private Dokumen dokumen;
    @Column(name = "NO_VERSI")
    private Long versi4k;

//      @Column(name = "NO_VERSI", columnDefinition = "number(11,4)", nullable = false)
//    private BigDecimal versi4k;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_2K")
    private Dokumen id2k;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_3K")
    private Dokumen id3k;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_DHKK_4K")
    private Dokumen DHKK4K;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_DHDK_4K")
    private Dokumen DHDK4K;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "KOD_CAW")
    private KodCawangan cawangan;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "KOD_DAERAH")
    private KodDaerah daerah;

    @Embedded
    private InfoAudit infoAudit;

    public String getIdHakmilikStrata() {
        return idHakmilikStrata;
    }

    public void setIdHakmilikStrata(String idHakmilikStrata) {
        this.idHakmilikStrata = idHakmilikStrata;
    }

    public Hakmilik getHakmilikInduk() {
        return hakmilikInduk;
    }

    public void setHakmilikInduk(Hakmilik hakmilikInduk) {
        this.hakmilikInduk = hakmilikInduk;
    }

    public Long getVersi4k() {
        return versi4k;
    }

    public void setVersi4k(Long versi4k) {
        this.versi4k = versi4k;
    }

    public Dokumen getId2k() {
        return id2k;
    }

    public void setId2k(Dokumen id2k) {
        this.id2k = id2k;
    }

    public Dokumen getId3k() {
        return id3k;
    }

    public void setId3k(Dokumen id3k) {
        this.id3k = id3k;
    }

    public Dokumen getDHKK4K() {
        return DHKK4K;
    }

    public void setDHKK4K(Dokumen DHKK4K) {
        this.DHKK4K = DHKK4K;
    }

    public Dokumen getDHDK4K() {
        return DHDK4K;
    }

    public void setDHDK4K(Dokumen DHDK4K) {
        this.DHDK4K = DHDK4K;
    }

    public KodCawangan getCawangan() {
        return cawangan;
    }

    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
    }

    public KodDaerah getDaerah() {
        return daerah;
    }

    public void setDaerah(KodDaerah daerah) {
        this.daerah = daerah;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

}
