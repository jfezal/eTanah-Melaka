package etanah.model.strata;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import etanah.model.Permohonan;
import etanah.model.Pihak;

@Entity
@Table(name = "strata_bdn_urus")
@SequenceGenerator(name = "SEQ_STRATA_BDN_URUS", sequenceName = "SEQ_STRATA_BDN_URUS")
public class BadanPengurusan implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_STRATA_BDN_URUS")
    @Column(name = "id_bdn")
    private long idBadan;

    @ManyToOne
    @JoinColumn(name = "kod_caw")
    private KodCawangan cawangan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_mohon")
    private Permohonan permohonan;

    @ManyToOne
    @JoinColumn(name = "id_pihak")
    private Pihak pihak;

    @Column(name = "no_ruj")
    private String pengurusanNoRujukan;

    @Column(name = "trh_ruj")
    private Date pengurusanTarikhRujukan;

    @Column(name = "no_sijil")
    private String pengurusanNoSijil;

    @Column(name = "trh_sijil")
    private Date pengurusanTarikhSijil;

    @Column(name = "nama")
    private String nama;

    @Column(name = "NO_PENGENALAN")
    private String noPengenalan;

    @Column(name = "ALAMAT4")
    private String alamat4;
//	@OneToMany (mappedBy = "badanPengurusan", fetch = FetchType.LAZY)
//	private List<Hakmilik> senaraiHakmilik;

    @Embedded
    private InfoAudit infoAudit;

    public long getIdBadan() {
        return idBadan;
    }

    public void setIdBadan(long idBadan) {
        this.idBadan = idBadan;
    }

    public KodCawangan getCawangan() {
        return cawangan;
    }

    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
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

    public String getPengurusanNoRujukan() {
        return pengurusanNoRujukan;
    }

    public void setPengurusanNoRujukan(String pengurusanNoRujukan) {
        this.pengurusanNoRujukan = pengurusanNoRujukan;
    }

    public Date getPengurusanTarikhRujukan() {
        return pengurusanTarikhRujukan;
    }

    public void setPengurusanTarikhRujukan(Date pengurusanTarikhRujukan) {
        this.pengurusanTarikhRujukan = pengurusanTarikhRujukan;
    }

    public String getPengurusanNoSijil() {
        return pengurusanNoSijil;
    }

    public void setPengurusanNoSijil(String pengurusanNoSijil) {
        this.pengurusanNoSijil = pengurusanNoSijil;
    }

    public Date getPengurusanTarikhSijil() {
        return pengurusanTarikhSijil;
    }

    public void setPengurusanTarikhSijil(Date pengurusanTarikhSijil) {
        this.pengurusanTarikhSijil = pengurusanTarikhSijil;
    }

//	public void setSenaraiHakmilik(List<Hakmilik> senaraiHakmilik) {
//		this.senaraiHakmilik = senaraiHakmilik;
//	}
//
//	public List<Hakmilik> getSenaraiHakmilik() {
//		return senaraiHakmilik;
//	}
    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNoPengenalan() {
        return noPengenalan;
    }

    public void setNoPengenalan(String noPengenalan) {
        this.noPengenalan = noPengenalan;
    }

    public String getAlamat4() {
        return alamat4;
    }

    public void setAlamat4(String alamat4) {
        this.alamat4 = alamat4;
    }

}
