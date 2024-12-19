package etanah.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "mohon_hakmilik_sblm")
@SequenceGenerator(name = "seq_mohon_hakmilik_sblm", sequenceName = "seq_mohon_hakmilik_sblm")
public class HakmilikSebelumPermohonan implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_mohon_hakmilik_sblm")
    @Column(name = "id_mhs")
    private long idHakmilikSebelumPermohonan;
    @ManyToOne
    @JoinColumn(name = "id_mh")
    private HakmilikPermohonan hakmilikPermohonan;
    @ManyToOne
    @JoinColumn(name = "id_hakmilik")
    private Hakmilik hakmilik;
//    @ManyToOne
//    @JoinColumn(name = "id_sej_hakmilik")
//    private SejarahHakmilik hakmilikSejarah;
    
     @Column (name = "id_sej_hakmilik")
     private String hakmilikSejarah;
     
    @ManyToOne
    @JoinColumn(name = "id_mohon")
    private Permohonan permohonan;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "kod_caw")
    private KodCawangan cawangan;
    @Embedded
    private InfoAudit infoAudit;

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }
    
    public long getIdHakmilikSebelumPermohonan() {
        return idHakmilikSebelumPermohonan;
    }

	public void setCawangan(KodCawangan cawangan) {
		this.cawangan = cawangan;
	}

	public KodCawangan getCawangan() {
		return cawangan;
	}

	public void setPermohonan(Permohonan permohonan) {
		this.permohonan = permohonan;
	}

	public Permohonan getPermohonan() {
		return permohonan;
	}

	public HakmilikPermohonan getHakmilikPermohonan() {
		return hakmilikPermohonan;
	}

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }


    public String getHakmilikSejarah() {
        return hakmilikSejarah;
    }

    public void setHakmilikSejarah(String hakmilikSejarah) {
        this.hakmilikSejarah = hakmilikSejarah;
    }
    
    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }
}
