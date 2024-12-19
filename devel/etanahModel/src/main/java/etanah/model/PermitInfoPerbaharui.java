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
@Table(name = "permit_info_perbaharui")
@SequenceGenerator(name = "seq_permit_info_perbaharui", sequenceName = "seq_permit_info_perbaharui")
public class PermitInfoPerbaharui implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_permit_info_perbaharui")
    @Column(name = "id_permit_info_perbaharui")
    private Long idPermitInfoPerbaharui;
    @ManyToOne
    @JoinColumn(name = "kod_caw")
    private KodCawangan kodCawangan;
    @ManyToOne
    @JoinColumn(name = "id_permit")
    private Permit permit;
    @Column(name = "hari_akhir_byr")
    private Integer hariAkhirBayaran;
    @Column(name = "bln_akhir_byr")
    private Integer bulanAkhirBayaran;

    public PermitInfoPerbaharui() {
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

    public Integer getBulanAkhirBayaran() {
        return bulanAkhirBayaran;
    }

    public void setBulanAkhirBayaran(Integer bulanAkhirBayaran) {
        this.bulanAkhirBayaran = bulanAkhirBayaran;
    }

    public Integer getHariAkhirBayaran() {
        return hariAkhirBayaran;
    }

    public void setHariAkhirBayaran(Integer hariAkhirBayaran) {
        this.hariAkhirBayaran = hariAkhirBayaran;
    }

    public Long getIdPermitInfoPerbaharui() {
        return idPermitInfoPerbaharui;
    }

    public void setIdPermitInfoPerbaharui(Long idPermitInfoPerbaharui) {
        this.idPermitInfoPerbaharui = idPermitInfoPerbaharui;
    }



    public Permit getPermit() {
        return permit;
    }

    public void setPermit(Permit permit) {
        this.permit = permit;
    }
}
