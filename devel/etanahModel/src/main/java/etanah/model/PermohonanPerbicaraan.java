package etanah.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import etanah.model.KodUOM;
import etanah.model.Pengguna;

@Entity
@Table(name = "mohon_bicara")
@SequenceGenerator(name = "seq_mohon_bicara", sequenceName = "seq_mohon_bicara")
public class PermohonanPerbicaraan implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_mohon_bicara")
    @Column(name = "id_bicara")
    private long idPerbicaraan;
    
    @ManyToOne
    @JoinColumn(name = "kod_caw", nullable = false)
    private KodCawangan cawangan;
    
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "id_mohon", nullable = false)
    private Permohonan permohonan;
    
    @Column(name = "no_ruj")
    public String noRujukan;
    
    @Column(name = "trh_bicara")
    private Date tarikhBicara;
    
    @Column(name = "tmpt_bicara")
    private String lokasiBicara;
    
    @ManyToOne
    @JoinColumn(name = "dibicara")
    private Pengguna dibicaraOleh;
    
    @Column(name = "catatan")
    private String catatan;
    
    @OneToMany (mappedBy = "perbicaraan")
    private List<PermohonanPerbicaraanKehadiran> senaraiKehadiran;
    
    @Embedded
    private InfoAudit infoAudit;

    public long getIdPerbicaraan() {
        return idPerbicaraan;
    }

    public void setIdPerbicaraan(long idPerbicaraan) {
        this.idPerbicaraan = idPerbicaraan;
    }

    public KodCawangan getCawangan() {
        return cawangan;
    }

    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
    }

    public void setPermohonan(Permohonan permohonan) {
		this.permohonan = permohonan;
	}

	public Permohonan getPermohonan() {
		return permohonan;
	}

    public String getNoRujukan() {
        return noRujukan;
    }

    public void setNoRujukan(String noRujukan) {
        this.noRujukan = noRujukan;
    }

    public Date getTarikhBicara() {
        return tarikhBicara;
    }

    public void setTarikhBicara(Date tarikhBicara) {
        this.tarikhBicara = tarikhBicara;
    }

    public String getLokasiBicara() {
        return lokasiBicara;
    }

    public void setLokasiBicara(String lokasiBicara) {
        this.lokasiBicara = lokasiBicara;
    }

    public Pengguna getDibicaraOleh() {
        return dibicaraOleh;
    }

    public void setDibicaraOleh(Pengguna dibicaraOleh) {
        this.dibicaraOleh = dibicaraOleh;
    }


    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public void setSenaraiKehadiran(List<PermohonanPerbicaraanKehadiran> senaraiKehadiran) {
		this.senaraiKehadiran = senaraiKehadiran;
	}

	public List<PermohonanPerbicaraanKehadiran> getSenaraiKehadiran() {
		return senaraiKehadiran;
	}

	public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }
}
