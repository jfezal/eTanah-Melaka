/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author Suhairi
 */
@Entity
@Table(name = "PENASIHAT_UNDANG")
@SequenceGenerator(name = "seq_penasihat_undang", sequenceName = "seq_penasihat_undang")

public class PenasihatUndang implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_penasihat_undang")
    @Column(name = "ID_PENASIHAT")
    private long idPenasihat;

    @Column(name = "NAMA")
    private String nama;
    @Column(name = "TRH_AKTIF_DARI")
    @Temporal(TemporalType.DATE)
    private Date tarikhAktifDari;
    @Column(name = "TRH_AKTIF_HINGGA")
    @Temporal(TemporalType.DATE)
    private Date tarikhAktifHingga;
    @JoinColumn(name = "KOD_NEGERI", referencedColumnName = "KOD")
    @ManyToOne
    private KodNegeri kodNegeri;
    @Column(name = "alamat1")
    private String alamat1;
    @Column(name = "alamat2")
    private String alamat2;
    @Column(name = "alamat3")
    private String alamat3;
    @Column(name = "alamat4")
    private String alamat4;
    @Column(name = "poskod")
    private String poskod;
    public PenasihatUndang() {
    }

    public PenasihatUndang(Short idPenasihat) {
        this.idPenasihat = idPenasihat;
    }


    @Embedded
    private InfoAudit infoAudit;

    public long getIdPenasihat() {
        return idPenasihat;
    }

    public void setIdPenasihat(Short idPenasihat) {
        this.idPenasihat = idPenasihat;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public Date getTarikhAktifDari() {
        return tarikhAktifDari;
    }

    public void setTarikhAktifDari(Date tarikhAktifDari) {
        this.tarikhAktifDari = tarikhAktifDari;
    }

    public Date getTarikhAktifHingga() {
        return tarikhAktifHingga;
    }

    public void setTarikhAktifHingga(Date tarikhAktifHingga) {
        this.tarikhAktifHingga = tarikhAktifHingga;
    }



    public KodNegeri getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(KodNegeri kodNegeri) {
        this.kodNegeri = kodNegeri;
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

	public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

}
