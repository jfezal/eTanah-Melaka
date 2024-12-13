package etanah.model;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table (name = "lelong_suku")
@SequenceGenerator(name = "seq_lelong_suku", sequenceName = "seq_lelong_suku")
public class LelonganSuku implements Serializable {

	@Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_lelong_suku")
	@Column (name = "id_lelong_suku")
	private long idLelongSuku;
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "id_lelong" )
	private Lelongan lelongan;


	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "kod_suku" )
	private KodSuku kodSuku;

	@ManyToOne
	@JoinColumn (name = "kod_caw")
	private KodCawangan cawangan;


	
	
	
	@Embedded
	private InfoAudit infoAudit;


    public KodCawangan getCawangan() {
        return cawangan;
    }

    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
    }

    public Lelongan getLelongan() {
        return lelongan;
    }

    public void setLelongan(Lelongan lelongan) {
        this.lelongan = lelongan;
    }

    public long getIdLelongSuku() {
        return idLelongSuku;
    }

    public void setIdLelongSuku(long idLelongSuku) {
        this.idLelongSuku = idLelongSuku;
    }

    public KodSuku getKodSuku() {
        return kodSuku;
    }

    public void setKodSuku(KodSuku kodSuku) {
        this.kodSuku = kodSuku;
    }






	public InfoAudit getInfoAudit() {
		return infoAudit;
	}

	public void setInfoAudit(InfoAudit infoAudit) {
		this.infoAudit = infoAudit;
	}
	
	

	
}
