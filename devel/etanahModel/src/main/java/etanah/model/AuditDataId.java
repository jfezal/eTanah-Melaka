/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Izam
 */
@Embeddable
public class AuditDataId implements Serializable {
    @Column(name = "audit_id")
    private Long idAudit;

    @Column(name = "nama_medan", length = 30)
    private String namaMedan;

    public AuditDataId() {
        
    }

    public AuditDataId(long idAudit, String namaMedan) {
        this.idAudit = idAudit;
        this.namaMedan = namaMedan;
    }

    @Override
    public boolean equals(Object arg0) {
        if(arg0 == null) return false;
        if(!(arg0 instanceof AuditDataId)) return false;

        AuditDataId arg1 = (AuditDataId) arg0;

        return (this.idAudit.longValue() == arg1.getIdAudit().longValue())
                && (this.namaMedan.equals(arg1.getNamaMedan()));
    }

    @Override
    public int hashCode() {
        int hsCode;
        hsCode = idAudit.hashCode();
        hsCode = 19 * hsCode + idAudit.hashCode();
        return hsCode;
    }

    public Long getIdAudit() {
        return idAudit;
    }

    public void setIdAudit(Long idAudit) {
        this.idAudit = idAudit;
    }

    public String getNamaMedan() {
        return namaMedan;
    }

    public void setNamaMedan(String namaMedan) {
        this.namaMedan = namaMedan;
    }
}
