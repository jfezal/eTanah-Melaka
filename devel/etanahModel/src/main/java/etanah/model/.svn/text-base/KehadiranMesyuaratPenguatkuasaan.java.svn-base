/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.model;

import java.io.Serializable;
import javax.persistence.*;


@Entity
@Table (name = "kkuasa_mesyuarat_hadir")
@SequenceGenerator(name = "seq_kkuasa_mesyuarat_hadir", sequenceName = "seq_kkuasa_mesyuarat_hadir")
public class KehadiranMesyuaratPenguatkuasaan implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_kkuasa_mesyuarat_hadir")
    @Column (name = "id_kmh")
    private long idKehadiran;
    @ManyToOne
    @JoinColumn (name = "id_km", nullable = false)
    private MesyuaratPenguatkuasaan mesyuaratPenguatkuasaan;
    @ManyToOne
    @JoinColumn (name = "kod_agensi")
    private KodAgensi kodAgensi;
    @Column (name = "nama")
    private String nama;
    @ManyToOne
    @JoinColumn (name = "kod_pengenalan")
    private KodJenisPengenalan kodJenisPengenalan;
    @Column (name = "no_pengenalan")
    private String noPengenalan;
    @Column (name = "jawatan")
    private String jawatan;
    @Column (name = "email")
    private String email;
    @Column (name = "alamat_agensi")
    private String alamatAgensi;
    @Embedded
    private InfoAudit infoAudit;

    public long getIdKehadiran() {
        return idKehadiran;
    }

    public void setIdKehadiran(long idKehadiran) {
        this.idKehadiran = idKehadiran;
    }

    public MesyuaratPenguatkuasaan getMesyuaratPenguatkuasaan() {
        return mesyuaratPenguatkuasaan;
    }

    public void setMesyuaratPenguatkuasaan(MesyuaratPenguatkuasaan mesyuaratPenguatkuasaan) {
        this.mesyuaratPenguatkuasaan = mesyuaratPenguatkuasaan;
    }

    public KodAgensi getKodAgensi() {
        return kodAgensi;
    }

    public void setKodAgensi(KodAgensi kodAgensi) {
        this.kodAgensi = kodAgensi;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public KodJenisPengenalan getKodJenisPengenalan() {
        return kodJenisPengenalan;
    }

    public void setKodJenisPengenalan(KodJenisPengenalan kodJenisPengenalan) {
        this.kodJenisPengenalan = kodJenisPengenalan;
    }

    public String getNoPengenalan() {
        return noPengenalan;
    }

    public void setNoPengenalan(String noPengenalan) {
        this.noPengenalan = noPengenalan;
    }

    public String getJawatan() {
        return jawatan;
    }

    public void setJawatan(String jawatan) {
        this.jawatan = jawatan;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAlamatAgensi() {
        return alamatAgensi;
    }

    public void setAlamatAgensi(String alamatAgensi) {
        this.alamatAgensi = alamatAgensi;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

}
