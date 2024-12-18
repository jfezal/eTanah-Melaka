package etanah.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Entity
@Table (name = "nts_ptl_pguna")
@SequenceGenerator(name = "seq_nts_ptl_pguna", sequenceName = "seq_nts_ptl_pguna")
public class NotisPortalPengguna implements Serializable {

	@Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_nts_ptl_pguna")
	@Column (name = "id_nts_ptl_pguna")
	private long idNtsPtlPguna;
	
        
	@Column (name = "thn")
	private Integer tahun;
        
	@Column (name = "penggal")
	private Integer penggal;
        
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "kod_pengenalan" )
	private KodJenisPengenalan kodJenisPengenalan;


	@Column (name = "no_pengenalan")
	private String nomborPengenalan;


	@Column (name = "emel")
	private String emel;

	@Column (name = "trh_hantar_emel")
	private Date tarikhHantarEmel;

	
	
	
	@Embedded
	private InfoAudit infoAudit;

    public String getEmel() {
        return emel;
    }

    public void setEmel(String emel) {
        this.emel = emel;
    }

    public long getIdNtsPtlPguna() {
        return idNtsPtlPguna;
    }

    public void setIdNtsPtlPguna(long idNtsPtlPguna) {
        this.idNtsPtlPguna = idNtsPtlPguna;
    }

    public KodJenisPengenalan getKodJenisPengenalan() {
        return kodJenisPengenalan;
    }

    public void setKodJenisPengenalan(KodJenisPengenalan kodJenisPengenalan) {
        this.kodJenisPengenalan = kodJenisPengenalan;
    }

    public String getNomborPengenalan() {
        return nomborPengenalan;
    }

    public void setNomborPengenalan(String nomborPengenalan) {
        this.nomborPengenalan = nomborPengenalan;
    }

    public Integer getPenggal() {
        return penggal;
    }

    public void setPenggal(Integer penggal) {
        this.penggal = penggal;
    }

    public Integer getTahun() {
        return tahun;
    }

    public void setTahun(Integer tahun) {
        this.tahun = tahun;
    }

    public Date getTarikhHantarEmel() {
        return tarikhHantarEmel;
    }

    public void setTarikhHantarEmel(Date tarikhHantarEmel) {
        this.tarikhHantarEmel = tarikhHantarEmel;
    }

    

    


	public InfoAudit getInfoAudit() {
		return infoAudit;
	}

	public void setInfoAudit(InfoAudit infoAudit) {
		this.infoAudit = infoAudit;
	}
	
	

	
}
