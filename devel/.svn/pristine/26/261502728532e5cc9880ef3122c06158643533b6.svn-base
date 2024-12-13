package etanah.model;

import java.io.Serializable;

import javax.persistence.*;

/**
 * Pihak berkuasa tempatan.
 * @author hishammk
 *
 */

@Entity
@Table (name = "kod_pbt")
@org.hibernate.annotations.Cache (usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE)
public class KodPBT implements Serializable{
    @Id
    @Column (name = "kod", length = 1)
    private String kod;
    
    @Column (name = "nama", length = 50, nullable = false, unique = true)
    private String nama;    

    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn (name = "kod_daerah")
    private KodDaerah daerah;
    
    @Embedded
    private InfoAudit infoAudit;

    public void setKod(String kod) {
        this.kod = kod;
    }

    public String getKod() {
        return kod;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNama() {
        return nama;
    }


    public void setDaerah(KodDaerah daerah) {
		this.daerah = daerah;
	}


	public KodDaerah getDaerah() {
		return daerah;
	}

	public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

}
