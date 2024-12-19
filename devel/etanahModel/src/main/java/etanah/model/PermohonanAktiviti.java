package etanah.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table (name = "mohon_atvt")
public class PermohonanAktiviti implements Serializable {
	@Id
	@Column (name = "id_mohon", length = 22)
	private String idPermohonan;
	
	@Column (name = "luas")
	private BigDecimal luasTerlibat;
	
	@ManyToOne (fetch = FetchType.EAGER)
	@JoinColumn (name = "kod_uom")
	private KodUOM kodUnitLuas;
	
	@Column (name = "trh_mula")
	private Date tarikhMula;
	
	@Column (name = "thn_sblm", length = 100)
	private String tahunSebelum;
	
	@Column (name = "hakmilik_lain", length = 100)
	private String hakmilikLain;
	
	@Column (name = "lot_lain", length = 100)
	private String lotLain;
	
	@Embedded
	private InfoAudit infoAudit;

	public String getIdPermohonan() {
		return idPermohonan;
	}

	public void setIdPermohonan(String idPermohonan) {
		this.idPermohonan = idPermohonan;
	}

	public BigDecimal getLuasTerlibat() {
		return luasTerlibat;
	}

	public void setLuasTerlibat(BigDecimal luasTerlibat) {
		this.luasTerlibat = luasTerlibat;
	}

	public void setKodUnitLuas(KodUOM kodUnitLuas) {
		this.kodUnitLuas = kodUnitLuas;
	}

	public KodUOM getKodUnitLuas() {
		return kodUnitLuas;
	}

	public Date getTarikhMula() {
		return tarikhMula;
	}

	public void setTarikhMula(Date taikhMula) {
		this.tarikhMula = taikhMula;
	}

	public String getTahunSebelum() {
		return tahunSebelum;
	}

	public void setTahunSebelum(String tahunSebelum) {
		this.tahunSebelum = tahunSebelum;
	}

	public String getHakmilikLain() {
		return hakmilikLain;
	}

	public void setHakmilikLain(String hakmilikLain) {
		this.hakmilikLain = hakmilikLain;
	}

	public String getLotLain() {
		return lotLain;
	}

	public void setLotLain(String lotLain) {
		this.lotLain = lotLain;
	}

	public InfoAudit getInfoAudit() {
		return infoAudit;
	}

	public void setInfoAudit(InfoAudit infoAudit) {
		this.infoAudit = infoAudit;
	}

}
