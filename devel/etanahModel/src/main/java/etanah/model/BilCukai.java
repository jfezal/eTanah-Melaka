package etanah.model;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="bil_cukai")
public class BilCukai implements Serializable {

    @Id
    @Column(name = "id_bil")
    private Long id;

    @ManyToOne
    @JoinColumn (name = "kod_caw")
    private KodCawangan kodCawangan;

    @Column(name = "utk_thn", nullable = false, length = 4)
    private String untukTahun;

    @Column(name = "bilcukai", columnDefinition = "CLOB")
    private String bilCukai;

    @ManyToOne
    @JoinColumn (name = "disah")
    private Pengguna disah;

    @Column (name = "sts", length = 1)
    private char status;

    @Column (name = "jenis_cukai")
    private String jenisCukai;
    
    @Embedded
    private InfoAudit infoAudit;

    public String getBilCukai() {
        return bilCukai;
    }

    public Pengguna getDisah() {
        return disah;
    }

    /**
     * Pengguna yang mengesahkan bahawa bil cukai adalah betul dan diterima
     * @param disah
     */
    public void setDisah(Pengguna disah) {
        this.disah = disah;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    public KodCawangan getKodCawangan() {
        return kodCawangan;
    }

    /**
     * <p>Status bagi bill:<br/>
     * <ul><li>B - Baru</li><li>Y - Ya, diterima</li><li>T - Tidak diterima</li></ul>
     * </p>
     * @return Either B, Y, or T
     */
    public char getStatus() {
        return status;
    }

    /**
     * <p>Status bagi bill:<br/>
     * <ul><li>B - Baru</li><li>Y - Ya, diterima</li><li>T - Tidak diterima</li></ul>
     * </p>
     * @return
     */
    public void setStatus(char status) {
        this.status = status;
    }

    public String getUntukTahun() {
        return untukTahun;
    }

    public String getJenisCukai() {
        return jenisCukai;
    }

    public void setJenisCukai(String jenisCukai) {
        this.jenisCukai = jenisCukai;
    }
    
    

}
