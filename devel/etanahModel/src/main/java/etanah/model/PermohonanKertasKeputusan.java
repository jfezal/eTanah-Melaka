package etanah.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "mohon_kertas_kpsn")
@SequenceGenerator(name = "seq_mohon_kertas_kpsn", sequenceName = "seq_mohon_kertas_kpsn")
public class PermohonanKertasKeputusan implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_mohon_kertas_kpsn")

    @Column(name = "id_kertas_kpsn")
    private long idKertasKpsn;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_kand", updatable = false)
    private PermohonanKertasKandungan kertasKandungan;
    @Column(name = "trh_rayuan")
    private Date trhRayuan;
    @Column(name = "jenis_rayuan")
    private String jenisRayuan;
    @Column(name = "kpsn")
    private String keputusan;

    @Embedded
    private InfoAudit infoAudit;

    public long getIdKertasKpsn() {
        return idKertasKpsn;
    }

    public void setIdKertasKpsn(long idKertasKpsn) {
        this.idKertasKpsn = idKertasKpsn;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    public String getJenisRayuan() {
        return jenisRayuan;
    }

    public void setJenisRayuan(String jenisRayuan) {
        this.jenisRayuan = jenisRayuan;
    }

    public String getKeputusan() {
        return keputusan;
    }

    public void setKeputusan(String keputusan) {
        this.keputusan = keputusan;
    }

    public PermohonanKertasKandungan getKertasKandungan() {
        return kertasKandungan;
    }

    public void setKertasKandungan(PermohonanKertasKandungan kertasKandungan) {
        this.kertasKandungan = kertasKandungan;
    }

    public Date getTrhRayuan() {
        return trhRayuan;
    }

    public void setTrhRayuan(Date trhRayuan) {
        this.trhRayuan = trhRayuan;
    }


}
