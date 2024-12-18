package etanah.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 *
 * Utiliti bagi kertas keselamatan
 */
@Entity
@Table(name = "hakmilik_kertas")
@SequenceGenerator(name = "seq_hakmilik_kertas", sequenceName = "seq_hakmilik_kertas")
public class HakmilikKertas implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_hakmilik_kertas")
    @Column(name = "id_hk")
    private long idHakmilikKertas;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "kod_caw")
    private KodCawangan kodCawangan;
    @Column(name = "no_awal")
    private String noAwal;
    @Column(name = "no_akhir")
    private String noAkhir;
    @Column(name = "bilangan")
    private int bilangan;
    @Column(name = "jenis_kertas")
    private String jenisKertas;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_pguna", nullable = false)
    private Pengguna pengguna;
    @Column (name = "trh_ambil")
    private Date tarikhDiambil;
    @Embedded
    private InfoAudit infoAudit;

    public long getIdHakmilikKertas() {
        return idHakmilikKertas;
    }

    public void setIdHakmilikKertas(long idHakmilikKertas) {
        this.idHakmilikKertas = idHakmilikKertas;
    }

    public KodCawangan getKodCawangan() {
        return kodCawangan;
    }

    public void setKodCawangan(KodCawangan kodCawangan) {
        this.kodCawangan = kodCawangan;
    }

    public String getNoAwal() {
        return noAwal;
    }

    public void setNoAwal(String noAwal) {
        this.noAwal = noAwal;
    }

    public String getNoAkhir() {
        return noAkhir;
    }

    public void setNoAkhir(String noAkhir) {
        this.noAkhir = noAkhir;
    }

    public int getBilangan() {
        return bilangan;
    }

    public void setBilangan(int bilangan) {
        this.bilangan = bilangan;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    public String getJenisKertas() {
        return jenisKertas;
    }

    public void setJenisKertas(String jenisKertas) {
        this.jenisKertas = jenisKertas;
    }

    public Date getTarikhDiambil() {
        return tarikhDiambil;
    }

    public void setTarikhDiambil(Date tarikhDiambil) {
        this.tarikhDiambil = tarikhDiambil;
    }
        
}
