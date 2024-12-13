/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author haqqiem
 */
@Entity
@Table(name = "PERMIT_LPS_REKOD")
@SequenceGenerator(name = "SEQ_PERMIT_LPS_REKOD", sequenceName = "SEQ_PERMIT_LPS_REKOD")

public class PermitLPSRekod implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PERMIT_LPS_REKOD")
    @Column(name = "IDPERMITLPSREKOD")
    private Long idPermitLPSRekod;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_MOHON", nullable = false)
    private Permohonan permohonan;

    @Column(name = "NO_PERMIT")
    private String noPermit;

    @Column(name = "BAYARAN")
    private String bayaran;

    @Column(name = "NORESIT")
    private String resit;

    @Column(name = "TAHUN")
    private String tahun;

    @Column(name = "FLAG")
    private String flag;

    @Embedded
    private InfoAudit infoAudit;

    public Long getIdPermitLPSRekod() {
        return idPermitLPSRekod;
    }

    public void setIdPermitLPSRekod(Long idPermitLPSRekod) {
        this.idPermitLPSRekod = idPermitLPSRekod;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public String getNoPermit() {
        return noPermit;
    }

    public void setNoPermit(String noPermit) {
        this.noPermit = noPermit;
    }

    public String getBayaran() {
        return bayaran;
    }

    public void setBayaran(String bayaran) {
        this.bayaran = bayaran;
    }

    public String getResit() {
        return resit;
    }

    public void setResit(String resit) {
        this.resit = resit;
    }

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }
}
