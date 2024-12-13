package etanah.model.ambil;

import etanah.model.InfoAudit;
import etanah.model.KodBandarPekanMukim;
import etanah.model.KodCawangan;
import etanah.model.KodPBT;
import etanah.model.KodUOM;
import etanah.model.Permohonan;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;


@Entity
@Table (name = "mohon_ambil")
@org.hibernate.annotations.Entity(selectBeforeUpdate = true,
		dynamicInsert = true, dynamicUpdate = true)
@SequenceGenerator(name = "seq_mohon_ambil", sequenceName = "seq_mohon_ambil")
public class PermohonanPengambilan implements Serializable {
	
	@Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_mohon_ambil")
	@Column (name = "id_ambil")
	private long idPengambilan;
	
	@OneToOne (cascade = CascadeType.ALL)
	@JoinColumn (name = "id_mohon", nullable = false)
	private Permohonan permohonan;
	
	@ManyToOne
	@JoinColumn (name = "kod_caw")
	private KodCawangan cawangan;

	@ManyToOne
	@JoinColumn (name = "kod_bpm")
	private KodBandarPekanMukim bandarPekanMukim;

	@ManyToOne
	@JoinColumn (name = "kod_pbt")
	private KodPBT pbt;
	
	@Column (name = "bil_lot")
	private int bilanganLot;
	
	/**
	 * Luas keseluruhan
	 */
    @Column(name = "luas")
    private BigDecimal luasTerlibat;

    @ManyToOne
    @JoinColumn(name = "kod_uom")
    private KodUOM kodUnitLuas;
    
    @Column (name = "nilaian_jpph")
    private BigDecimal nilaiJPPH;
    
        @Column (name = "deposit_fee")
    private BigDecimal deposit;
        
            @Column (name = "permohonan_fee")
    private BigDecimal feePermohonan;
            
            @Column (name = "kategori_ambil")
    private String katPengambilan;
             

	
	@Column (name = "lps_ambil")
	private String selepasPengambilan;
        
	@Column (name = "tujuan_permohonan")
	private String tujuanPermohonan;
	
	@Column (name = "peruntukan")
	private char adaPeruntukan;

	/**
	 * Laporan pelukis pelan
	 */
	@Column (name = "projek_kjaan")
	private String projekKerajaan;
	
	@Column (name = "ranc_rumah")
	private String rancanganPerumahan;
	
	@Column (name = "rizab_melayu")
	private String rizabMelayu;
	
	@Column (name = "rizab_hutan")
	private String rizabHutan;

	@Column (name = "gsa")
	private String tanahGSA;

	@Column (name = "mohon_sblm")
	private String permohonanTerdahulu;
	
	/**
	 * Warta pengambilan
	 */
	@Column (name = "no_warta")
	private String noWarta;

	@Column (name = "trh_warta")
	private Date tarikhWarta;
        
        @Column (name = "kedesakan")//Y-ADA KEDESAKAN T- TIADA KEDESAKAN
	private String kedesakan;
        
        @Column (name = "deposit_tambahan")
	private BigDecimal depositTambahan;
	
	@Embedded
	private InfoAudit infoAudit;

	public long getIdPengambilan() {
		return idPengambilan;
	}

	public void setIdPengambilan(long idPengambilan) {
		this.idPengambilan = idPengambilan;
	}

	public Permohonan getPermohonan() {
		return permohonan;
	}

	public void setPermohonan(Permohonan permohonan) {
		this.permohonan = permohonan;
	}

	public KodCawangan getCawangan() {
		return cawangan;
	}

	public void setCawangan(KodCawangan cawangan) {
		this.cawangan = cawangan;
	}

	public KodBandarPekanMukim getBandarPekanMukim() {
		return bandarPekanMukim;
	}

	public void setBandarPekanMukim(KodBandarPekanMukim bandarPekanMukim) {
		this.bandarPekanMukim = bandarPekanMukim;
	}

	public KodPBT getPbt() {
		return pbt;
	}

	public void setPbt(KodPBT pbt) {
		this.pbt = pbt;
	}

	public void setBilanganLot(int bilanganLot) {
		this.bilanganLot = bilanganLot;
	}

	public int getBilanganLot() {
		return bilanganLot;
	}

	public BigDecimal getLuasTerlibat() {
		return luasTerlibat;
	}

	public void setLuasTerlibat(BigDecimal luasTerlibat) {
		this.luasTerlibat = luasTerlibat;
	}

	public KodUOM getKodUnitLuas() {
		return kodUnitLuas;
	}

	public void setKodUnitLuas(KodUOM kodUnitLuas) {
		this.kodUnitLuas = kodUnitLuas;
	}

	public String getSelepasPengambilan() {
		return selepasPengambilan;
	}

	public void setSelepasPengambilan(String selepasPengambilan) {
		this.selepasPengambilan = selepasPengambilan;
	}

	public char getAdaPeruntukan() {
		return adaPeruntukan;
	}

	public void setAdaPeruntukan(char adaPeruntukan) {
		this.adaPeruntukan = adaPeruntukan;
	}

	public String getProjekKerajaan() {
		return projekKerajaan;
	}

	public void setProjekKerajaan(String projekKerajaan) {
		this.projekKerajaan = projekKerajaan;
	}

	public String getRancanganPerumahan() {
		return rancanganPerumahan;
	}

	public void setRancanganPerumahan(String rancanganPerumahan) {
		this.rancanganPerumahan = rancanganPerumahan;
	}

	public void setRizabMelayu(String rizabMelayu) {
		this.rizabMelayu = rizabMelayu;
	}

	public String getRizabMelayu() {
		return rizabMelayu;
	}

	public void setRizabHutan(String rizabHutan) {
		this.rizabHutan = rizabHutan;
	}

	public String getRizabHutan() {
		return rizabHutan;
	}

	public void setTanahGSA(String tanahGSA) {
		this.tanahGSA = tanahGSA;
	}

	public String getTanahGSA() {
		return tanahGSA;
	}

	public void setPermohonanTerdahulu(String permohonanTerdahulu) {
		this.permohonanTerdahulu = permohonanTerdahulu;
	}

	public String getPermohonanTerdahulu() {
		return permohonanTerdahulu;
	}

	public void setNoWarta(String noWarta) {
		this.noWarta = noWarta;
	}

	public String getNoWarta() {
		return noWarta;
	}

	public void setTarikhWarta(Date tarikhWarta) {
		this.tarikhWarta = tarikhWarta;
	}

	public Date getTarikhWarta() {
		return tarikhWarta;
	}

	public InfoAudit getInfoAudit() {
		return infoAudit;
	}

	public void setInfoAudit(InfoAudit infoAudit) {
		this.infoAudit = infoAudit;
	}

    public BigDecimal getNilaiJPPH() {
        return nilaiJPPH;
    }

    public void setNilaiJPPH(BigDecimal nilaiJPPH) {
        this.nilaiJPPH = nilaiJPPH;
    }

    public BigDecimal getDeposit() {
        return deposit;
    }

    public void setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
    }

    public BigDecimal getFeePermohonan() {
        return feePermohonan;
    }

    public void setFeePermohonan(BigDecimal feePermohonan) {
        this.feePermohonan = feePermohonan;
    }

    public String getKatPengambilan() {
        return katPengambilan;
    }

    public void setKatPengambilan(String katPengambilan) {
        this.katPengambilan = katPengambilan;
    }

    public String getTujuanPermohonan() {
        return tujuanPermohonan;
    }

    public void setTujuanPermohonan(String tujuanPermohonan) {
        this.tujuanPermohonan = tujuanPermohonan;
    }

    public String getKedesakan() {
        return kedesakan;
    }

    public void setKedesakan(String kedesakan) {
        this.kedesakan = kedesakan;
    }

    public BigDecimal getDepositTambahan() {
        return depositTambahan;
    }

    public void setDepositTambahan(BigDecimal depositTambahan) {
        this.depositTambahan = depositTambahan;
    }
        
        

}
