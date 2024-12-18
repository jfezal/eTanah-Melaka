package etanah.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table (name = "syarat_tambahan_lelong")
public class SyaratTambahanLelong implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "seq_syarat_tambahan_lelong")
    @Column (name = "id_syarat")
    private int idSyarat;

    @ManyToOne
    @JoinColumn (name = "id_lelong", nullable = false)
    private Lelongan lelong;

    @Column (name = "perihal", nullable = false)
    private String perihal;

    @Column (name = "kod_sumber")
    private String kodSumber;

    @Embedded
    InfoAudit infoAudit;

    public void setIdSyarat(int idSyarat) {
        this.idSyarat = idSyarat;
    }

    public int getIdSyarat() {
        return idSyarat;
    }

    public void setLelong(Lelongan lelong) {
		this.lelong = lelong;
	}

	public Lelongan getLelong() {
		return lelong;
	}

	public void setPerihal(String perihal) {
        this.perihal = perihal;
    }

    public String getPerihal() {
        return perihal;
    }

    public void setKodSumber(String kodSumber) {
        this.kodSumber = kodSumber;
    }

    public String getKodSumber() {
        return kodSumber;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }
}
