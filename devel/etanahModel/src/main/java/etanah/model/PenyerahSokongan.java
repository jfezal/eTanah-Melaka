/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author john
 */
@Entity
@Table(name = "penyerah_sokongan")
@SequenceGenerator(name = "seq_penyerah_sokongan", sequenceName = "seq_penyerah_sokongan")
public class PenyerahSokongan implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_penyerah_sokongan")
    @Column(name = "id_penyerah_skg")
    private long idPenyerahSokongan;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_mohon")
    private Permohonan mohon;
    @Column(name = "nama_penyerah")
    private String namaPenyerah;
    @Column(name = "no_tel")
    private String noTel;
    @Column(name = "id_aliran")
    private String idAliran;
    @Column(name = "trh_terima")
    private Date tarikhTerima;
    
    @Column(name = "no_kp")
    private String noKp;

    @Embedded
    private InfoAudit infoAudit;

    public long getIdPenyerahSokongan() {
        return idPenyerahSokongan;
    }

    public void setIdPenyerahSokongan(long idPenyerahSokongan) {
        this.idPenyerahSokongan = idPenyerahSokongan;
    }

    public Permohonan getMohon() {
        return mohon;
    }

    public void setMohon(Permohonan mohon) {
        this.mohon = mohon;
    }

    public String getNamaPenyerah() {
        return namaPenyerah;
    }

    public void setNamaPenyerah(String namaPenyerah) {
        this.namaPenyerah = namaPenyerah;
    }

    public String getNoTel() {
        return noTel;
    }

    public void setNoTel(String noTel) {
        this.noTel = noTel;
    }

    public String getIdAliran() {
        return idAliran;
    }

    public void setIdAliran(String idAliran) {
        this.idAliran = idAliran;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    public Date getTarikhTerima() {
        return tarikhTerima;
    }

    public void setTarikhTerima(Date tarikhTerima) {
        this.tarikhTerima = tarikhTerima;
    }
    
    
}
