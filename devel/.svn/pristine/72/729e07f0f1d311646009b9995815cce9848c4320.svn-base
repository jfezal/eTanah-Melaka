package etanah.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table (name = "dasar_cukai")
public class DasarTuntutanCukai implements Serializable {
	
	@Id
	@Column (name = "id_dasar", length = 10)
	private String idDasar;
	
	@ManyToOne
	@JoinColumn (name = "kod_caw")
	private KodCawangan cawangan;
	
	@Column (name = "trh_dasar", nullable = false)
	private Date tarikhDasar;
	
	@Column (name = "perihal")
	private String perihal;
	
	@Column (name = "tunggak_min", columnDefinition = "number(12,2)")
	private BigDecimal tunggakanMinima;
	
	@Column (name = "tunggak_dr")
	private Integer tunggakanDariTahun;
	
	@Column (name = "tunggak_ke")
	private Integer tunggakanKeTahun;
	
	@ManyToOne
	@JoinColumn (name = "kod_bpm")
	private KodBandarPekanMukim bandarPekanMukim;
	
	@OneToMany (mappedBy = "dasarTuntutanCukai")
	private List<DasarTuntutanCukaiHakmilik> senaraiHakmilik;
	
	@Embedded
	private InfoAudit infoAudit;

	public String getIdDasar() {
		return idDasar;
	}

	public void setIdDasar(String idDasar) {
		this.idDasar = idDasar;
	}

	public KodCawangan getCawangan() {
		return cawangan;
	}

	public void setCawangan(KodCawangan cawangan) {
		this.cawangan = cawangan;
	}

	public Date getTarikhDasar() {
		return tarikhDasar;
	}

	public void setTarikhDasar(Date tarikhDasar) {
		this.tarikhDasar = tarikhDasar;
	}

	public String getPerihal() {
		return perihal;
	}

	public void setPerihal(String perihal) {
		this.perihal = perihal;
	}

	public BigDecimal getTunggakanMinima() {
		return tunggakanMinima;
	}

	public void setTunggakanMinima(BigDecimal tunggakanMinima) {
		this.tunggakanMinima = tunggakanMinima;
	}

	public Integer getTunggakanDariTahun() {
		return tunggakanDariTahun;
	}

	public void setTunggakanDariTahun(Integer tunggakanDariTahun) {
		this.tunggakanDariTahun = tunggakanDariTahun;
	}

	public Integer getTunggakanKeTahun() {
		return tunggakanKeTahun;
	}

	public void setTunggakanKeTahun(Integer tunggakanKeTahun) {
		this.tunggakanKeTahun = tunggakanKeTahun;
	}

	public KodBandarPekanMukim getBandarPekanMukim() {
		return bandarPekanMukim;
	}

	public void setBandarPekanMukim(KodBandarPekanMukim bandarPekanMukim) {
		this.bandarPekanMukim = bandarPekanMukim;
	}

	public void setSenaraiHakmilik(List<DasarTuntutanCukaiHakmilik> senaraiHakmilik) {
		this.senaraiHakmilik = senaraiHakmilik;
	}

	public List<DasarTuntutanCukaiHakmilik> getSenaraiHakmilik() {
		return senaraiHakmilik;
	}

	public InfoAudit getInfoAudit() {
		return infoAudit;
	}

	public void setInfoAudit(InfoAudit infoAudit) {
		this.infoAudit = infoAudit;
	}

}
