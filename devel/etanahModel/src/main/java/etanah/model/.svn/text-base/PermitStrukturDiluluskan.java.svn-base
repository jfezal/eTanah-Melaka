/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author Suhairi
 */
@Entity
@Table(name = "permit_struktur_lulus")
@SequenceGenerator(name = "seq_permit_struktur_lulus", sequenceName = "seq_permit_struktur_lulus")
public class PermitStrukturDiluluskan implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_permit_struktur_lulus")
    @Column(name = "id_permit_struktur_lulus")
    private Long idPermitStrukturLulus;
    @ManyToOne
    @JoinColumn(name = "kod_caw")
    private KodCawangan kodCawangan;
    @ManyToOne
    @JoinColumn(name = "id_permit")
    private Permit permit;
    @Column(name = "keterangan")
    private String keterangan;
    @Column(name = "jenis_struktur_dibina")
    private String jenisStruktur;
    @Column(name = "luas_struktur")
    private BigDecimal luasStruktur;
    @ManyToOne
    @JoinColumn(name = "luas_struktur_uom")
    private KodUOM kodUomLuas;
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "id_mohon")
    private Permohonan permohonan;
    @Column(name = "NO_LESEN")
    private String noLesen;

    public PermitStrukturDiluluskan() {
    }
    @Embedded
    private InfoAudit infoAudit;

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    public KodCawangan getKodCawangan() {
        return kodCawangan;
    }

    public void setKodCawangan(KodCawangan kodCawangan) {
        this.kodCawangan = kodCawangan;
    }

    public Long getIdPermitStrukturLulus() {
        return idPermitStrukturLulus;
    }

    public void setIdPermitStrukturLulus(Long idPermitStrukturLulus) {
        this.idPermitStrukturLulus = idPermitStrukturLulus;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public Permit getPermit() {
        return permit;
    }

    public void setPermit(Permit permit) {
        this.permit = permit;
    }

    public String getJenisStruktur() {
        return jenisStruktur;
    }

    public void setJenisStruktur(String jenisStruktur) {
        this.jenisStruktur = jenisStruktur;
    }

    public KodUOM getKodUomLuas() {
        return kodUomLuas;
    }

    public void setKodUomLuas(KodUOM kodUomLuas) {
        this.kodUomLuas = kodUomLuas;
    }

    public BigDecimal getLuasStruktur() {
        return luasStruktur;
    }

    public void setLuasStruktur(BigDecimal luasStruktur) {
        this.luasStruktur = luasStruktur;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public String getNoLesen() {
        return noLesen;
    }

    public void setNoLesen(String noLesen) {
        this.noLesen = noLesen;
    }
}
