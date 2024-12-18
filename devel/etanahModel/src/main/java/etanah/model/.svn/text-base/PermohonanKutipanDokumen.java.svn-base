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
 * @author press
 */
@Entity
@Table (name = "mohon_kutip_dok")
@SequenceGenerator(name = "seq_mohon_kutip_dok", sequenceName = "seq_mohon_kutip_dok")
public class PermohonanKutipanDokumen implements Serializable, Auditable {
    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "seq_mohon_kutip_dok")
    @Column (name = "id_mk")
    private Long idKutipan;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "id_mohon", nullable = false)
    private Permohonan permohonan;
    
    @Column (name = "PENGUTIP_NAMA")
    private String nama;
    
    @Embedded
    @AttributeOverrides( {
        @AttributeOverride (name="alamat1", column=@Column(name="pengutip_alamat1")),
        @AttributeOverride (name="alamat2", column=@Column(name="pengutip_alamat2")),
        @AttributeOverride (name="alamat3", column=@Column(name="pengutip_alamat3")),
        @AttributeOverride (name="alamat4", column=@Column(name="pengutip_alamat4")),
        @AttributeOverride (name="poskod", column=@Column(name="pengutip_poskod"))
    })
    @AssociationOverride (name="negeri", joinColumns=@JoinColumn(name="pengutip_kod_negeri"))
    private Alamat alamat;
    
    @Column (name = "pengutip_telefon")
    private String telefon;
    
    @Column (name = "no_pengenalan")
    private String noPengenalan;
    
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "KOD_PENGENALAN")
    private KodJenisPengenalan jenisPengenalan;
    
    @Temporal (TemporalType.DATE)
    @Column (name = "trh_kutip")
    private Date tarikhKutipan;
    
    @Column (name = "NAMA")
    private String name;
    
    @Embedded
    InfoAudit infoAudit;

    public Long getIdKutipan() {
        return idKutipan;
    }

    public void setIdKutipan(Long idKutipan) {
        this.idKutipan = idKutipan;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public Alamat getAlamat() {
        return alamat;
    }

    public void setAlamat(Alamat alamat) {
        this.alamat = alamat;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public Date getTarikhKutipan() {
        return tarikhKutipan;
    }

    public void setTarikhKutipan(Date tarikhKutipan) {
        this.tarikhKutipan = tarikhKutipan;
    }

    public String getNoPengenalan() {
        return noPengenalan;
    }

    public void setNoPengenalan(String noPengenalan) {
        this.noPengenalan = noPengenalan;
    }

    public KodJenisPengenalan getJenisPengenalan() {
        return jenisPengenalan;
    }

    public void setJenisPengenalan(KodJenisPengenalan jenisPengenalan) {
        this.jenisPengenalan = jenisPengenalan;
    }

    @Override
    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
