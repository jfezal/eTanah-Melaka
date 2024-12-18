package etanah.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "lupus_permit")
@SequenceGenerator(name = "seq_lupus_permit", sequenceName = "seq_lupus_permit")
public class LupusPermit implements Serializable {
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_lupus_permit")
    @Column(name = "id_permit")
    private long id;
	
	@Column(name = "no_siri")
	private String noSiri;
	
	@Column(name = "no_permit")
	private String noPermit;
	
	@Column(name = "jenis_permit")
	private char jenisPermit;
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn(name = "id_mohon")
	private Permohonan permohonan;
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn(name = "id_mh")
	private HakmilikPermohonan hakmilikPermohonan;
	
	@ManyToOne
	@JoinColumn(name = "kod_caw")
	private KodCawangan cawangan;
	
	@Column (name = "byrn", precision = 5, scale = 2, columnDefinition = "number(5,2)")
    private BigDecimal bayaran;
	@Column(name = "byrn_pkataan")
	private String bayaranPerkataan;
	@Column(name = "no_resit")
	private String noResit;
	@Column(name = "trh_akhir_byr")
	private Date tarikhAkhirBayar;
	@Column(name = "tempoh_berakhir")
	private String tempohBerakhir;
	
	/*  
	 * butir bayaran tambahan 
	 */
	@Column(name = "tmbhn_byrn1", precision = 5, scale = 2, columnDefinition = "number(5,2)")
	private BigDecimal tambahanBayaran1;
	@Column(name = "tmbhn_byrn_pkataan1")
	private String tambahanBayarnPerkataan1;
	@Column(name = "tmbhn_no_resit1")
	private String tambahanNoResit1;
	@Column(name = "tmbhn_tempoh_berakhir1")
	private String tambahanTempohBerakhir1;
	@Column(name = "tmbhn_byrn2", precision = 5, scale = 2, columnDefinition = "number(5,2)")
	private BigDecimal tambahanBayaran2;
	@Column(name = "tmbhn_byrn_pkataan2")
	private String tambahanBayarnPerkataan2;
	@Column(name = "tmbhn_no_resit2")
	private String tambahanNoResit2;
	@Column(name = "tmbhn_tempoh_berakhir2")
	private String tambahanTempohBerakhir2;
	@Column(name = "tmbhn_byrn3", precision = 5, scale = 2, columnDefinition = "number(5,2)")
	private BigDecimal tambahanBayaran3;
	@Column(name = "tmbhn_byrn_pkataan3")
	private String tambahanBayarnPerkataan3;
	@Column(name = "tmbhn_no_resit3")
	private String tambahanNoResit3;
	@Column(name = "tmbhn_tempoh_berakhir3")
	private String tambahanTempohBerakhir3;
	
	@ManyToOne
	@JoinColumn(name = "id_pihak")
	private Pihak pihak;
	@Column(name = "trh_keluar")
	private Date tarikhKeluar;
	@Column(name = "trh_mula")
	private Date tarikhMula;
	
	@Column(name = "trh_tamat")
	private Date tarikhTamat;
	@Column(name = "tempoh_permit")
	private String tempohPermit;
	@Column(name = "kaedah")
	private String kaedah;
	@Column(name = "peruntukan_tmbhn")
	private String peruntukanTambahan;
	
	/*
	 * butir bahan batuan
	 */
	@ManyToOne
	@JoinColumn(name = "id_bhn_batu")
	private KodBahanBatu jenisBahanBatu;
	@Column(name = "kuantiti_max")
	private BigDecimal kuantitiMaksimum;
	@ManyToOne
	@JoinColumn(name = "kod_uom_kntt")
	private KodUOM unitIsipaduKuantiti;
	@Column(name = "kuantiti_max_sethn")
	private BigDecimal kuantitiMaksimumSetahun;
	@ManyToOne
	@JoinColumn(name = "kod_uom_kntt_sethn")
	private KodUOM unitIsipaduKuantitiSetahun;
	@Column(name = "kadar_byrn", precision = 8, scale = 2, columnDefinition = "number(8,2)")
	private BigDecimal kadarBayaran;
	@ManyToOne
	@JoinColumn(name = "kod_uom")
	private KodUOM unitIsipadu;
	@Column(name = "struktur_boleh_bina")
	private String strukturBolehBina;
	@Column (name = "aktif")
    private char aktif;
	
	@Embedded
    private InfoAudit infoAudit;
	
	
	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setNoSiri(String noSiri) {
		this.noSiri = noSiri;
	}

	public String getNoSiri() {
		return noSiri;
	}
	
	public String getNoPermit() {
		return noPermit;
	}

	public void setNoPermit(String noPermit) {
		this.noPermit = noPermit;
	}

	public char getJenisPermit() {
		return jenisPermit;
	}

	public void setJenisPermit(char jenisPermit) {
		this.jenisPermit = jenisPermit;
	}

	public Permohonan getPermohonan() {
		return permohonan;
	}

	public void setPermohonan(Permohonan permohonan) {
		this.permohonan = permohonan;
	}

	public HakmilikPermohonan getHakmilikPermohonan() {
		return hakmilikPermohonan;
	}

	public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
		this.hakmilikPermohonan = hakmilikPermohonan;
	}

	public KodCawangan getCawangan() {
		return cawangan;
	}

	public void setCawangan(KodCawangan cawangan) {
		this.cawangan = cawangan;
	}

	public BigDecimal getBayaran() {
		return bayaran;
	}

	public void setBayaran(BigDecimal bayaran) {
		this.bayaran = bayaran;
	}

	public String getBayaranPerkataan() {
		return bayaranPerkataan;
	}

	public void setBayaranPerkataan(String bayaranPerkataan) {
		this.bayaranPerkataan = bayaranPerkataan;
	}

	public String getNoResit() {
		return noResit;
	}

	public void setNoResit(String noResit) {
		this.noResit = noResit;
	}

	public Date getTarikhAkhirBayar() {
		return tarikhAkhirBayar;
	}

	public void setTarikhAkhirBayar(Date tarikhAkhirBayar) {
		this.tarikhAkhirBayar = tarikhAkhirBayar;
	}

	public String getTempohBerakhir() {
		return tempohBerakhir;
	}

	public void setTempohBerakhir(String tempohBerakhir) {
		this.tempohBerakhir = tempohBerakhir;
	}

	public BigDecimal getTambahanBayaran1() {
		return tambahanBayaran1;
	}

	public void setTambahanBayaran1(BigDecimal tambahanBayaran1) {
		this.tambahanBayaran1 = tambahanBayaran1;
	}

	public String getTambahanBayarnPerkataan1() {
		return tambahanBayarnPerkataan1;
	}

	public void setTambahanBayarnPerkataan1(String tambahanBayarnPerkataan1) {
		this.tambahanBayarnPerkataan1 = tambahanBayarnPerkataan1;
	}

	public String getTambahanNoResit1() {
		return tambahanNoResit1;
	}

	public void setTambahanNoResit1(String tambahanNoResit1) {
		this.tambahanNoResit1 = tambahanNoResit1;
	}

	public String getTambahanTempohBerakhir1() {
		return tambahanTempohBerakhir1;
	}

	public void setTambahanTempohBerakhir1(String tambahanTempohBerakhir1) {
		this.tambahanTempohBerakhir1 = tambahanTempohBerakhir1;
	}

	public BigDecimal getTambahanBayaran2() {
		return tambahanBayaran2;
	}

	public void setTambahanBayaran2(BigDecimal tambahanBayaran2) {
		this.tambahanBayaran2 = tambahanBayaran2;
	}

	public String getTambahanBayarnPerkataan2() {
		return tambahanBayarnPerkataan2;
	}

	public void setTambahanBayarnPerkataan2(String tambahanBayarnPerkataan2) {
		this.tambahanBayarnPerkataan2 = tambahanBayarnPerkataan2;
	}

	public String getTambahanNoResit2() {
		return tambahanNoResit2;
	}

	public void setTambahanNoResit2(String tambahanNoResit2) {
		this.tambahanNoResit2 = tambahanNoResit2;
	}

	public String getTambahanTempohBerakhir2() {
		return tambahanTempohBerakhir2;
	}

	public void setTambahanTempohBerakhir2(String tambahanTempohBerakhir2) {
		this.tambahanTempohBerakhir2 = tambahanTempohBerakhir2;
	}

	public BigDecimal getTambahanBayaran3() {
		return tambahanBayaran3;
	}

	public void setTambahanBayaran3(BigDecimal tambahanBayaran3) {
		this.tambahanBayaran3 = tambahanBayaran3;
	}

	public String getTambahanBayarnPerkataan3() {
		return tambahanBayarnPerkataan3;
	}

	public void setTambahanBayarnPerkataan3(String tambahanBayarnPerkataan3) {
		this.tambahanBayarnPerkataan3 = tambahanBayarnPerkataan3;
	}

	public String getTambahanNoResit3() {
		return tambahanNoResit3;
	}

	public void setTambahanNoResit3(String tambahanNoResit3) {
		this.tambahanNoResit3 = tambahanNoResit3;
	}

	public String getTambahanTempohBerakhir3() {
		return tambahanTempohBerakhir3;
	}

	public void setTambahanTempohBerakhir3(String tambahanTempohBerakhir3) {
		this.tambahanTempohBerakhir3 = tambahanTempohBerakhir3;
	}

	public Pihak getPihak() {
		return pihak;
	}

	public void setPihak(Pihak pihak) {
		this.pihak = pihak;
	}

	public Date getTarikhKeluar() {
		return tarikhKeluar;
	}

	public void setTarikhKeluar(Date tarikhKeluar) {
		this.tarikhKeluar = tarikhKeluar;
	}

	public Date getTarikhMula() {
		return tarikhMula;
	}

	public void setTarikhMula(Date tarikhMula) {
		this.tarikhMula = tarikhMula;
	}

	public Date getTarikhTamat() {
		return tarikhTamat;
	}

	public void setTarikhTamat(Date tarikhTamat) {
		this.tarikhTamat = tarikhTamat;
	}

	public String getTempohPermit() {
		return tempohPermit;
	}

	public void setTempohPermit(String tempohPermit) {
		this.tempohPermit = tempohPermit;
	}

	public String getKaedah() {
		return kaedah;
	}

	public void setKaedah(String kaedah) {
		this.kaedah = kaedah;
	}

	public String getPeruntukanTambahan() {
		return peruntukanTambahan;
	}

	public void setPeruntukanTambahan(String peruntukanTambahan) {
		this.peruntukanTambahan = peruntukanTambahan;
	}

	public KodBahanBatu getJenisBahanBatu() {
		return jenisBahanBatu;
	}

	public void setJenisBahanBatu(KodBahanBatu jenisBahanBatu) {
		this.jenisBahanBatu = jenisBahanBatu;
	}

	public BigDecimal getKuantitiMaksimum() {
		return kuantitiMaksimum;
	}

	public void setKuantitiMaksimum(BigDecimal kuantitiMaksimum) {
		this.kuantitiMaksimum = kuantitiMaksimum;
	}

	public BigDecimal getKuantitiMaksimumSetahun() {
		return kuantitiMaksimumSetahun;
	}

	public void setKuantitiMaksimumSetahun(BigDecimal kuantitiMaksimumSetahun) {
		this.kuantitiMaksimumSetahun = kuantitiMaksimumSetahun;
	}

	public BigDecimal getKadarBayaran() {
		return kadarBayaran;
	}

	public void setKadarBayaran(BigDecimal kadarBayaran) {
		this.kadarBayaran = kadarBayaran;
	}

	public KodUOM getUnitIsipadu() {
		return unitIsipadu;
	}

	public void setUnitIsipadu(KodUOM unitIsipadu) {
		this.unitIsipadu = unitIsipadu;
	}

	public String getStrukturBolehBina() {
		return strukturBolehBina;
	}

	public void setStrukturBolehBina(String strukturBolehBina) {
		this.strukturBolehBina = strukturBolehBina;
	}

	public char getAktif() {
		return aktif;
	}

	public void setAktif(char aktif) {
		this.aktif = aktif;
	}

	public InfoAudit getInfoAudit() {
		return infoAudit;
	}

	public void setInfoAudit(InfoAudit infoAudit) {
		this.infoAudit = infoAudit;
	}

	public void setUnitIsipaduKuantiti(KodUOM unitIsipaduKuantiti) {
		this.unitIsipaduKuantiti = unitIsipaduKuantiti;
	}

	public KodUOM getUnitIsipaduKuantiti() {
		return unitIsipaduKuantiti;
	}

	public void setUnitIsipaduKuantitiSetahun(KodUOM unitIsipaduKuantitiSetahun) {
		this.unitIsipaduKuantitiSetahun = unitIsipaduKuantitiSetahun;
	}

	public KodUOM getUnitIsipaduKuantitiSetahun() {
		return unitIsipaduKuantitiSetahun;
	}
	
}
