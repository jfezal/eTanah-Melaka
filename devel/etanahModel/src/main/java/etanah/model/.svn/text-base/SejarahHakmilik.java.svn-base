package etanah.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import org.hibernate.annotations.Immutable;


@Entity
@Table (name = "hakmilik")
public class SejarahHakmilik implements Serializable{

    public static final String KOD_AKAUN_CUKAI = "AC";
    @Id
    @Column(name = "id_hakmilik", length = 20)
    private String idHakmilik;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "kod_caw")
    private KodCawangan cawangan;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "kod_daerah")
    private KodDaerah daerah;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "kod_bpm")
    private KodBandarPekanMukim bandarPekanMukim;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "kod_seksyen")
    private KodSeksyen seksyen;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "kod_lot")
    private KodLot lot;
    @Column(name = "no_lot", length = 15)
    private String noLot;
    @Column(name = "no_hakmilik", nullable = false)
    private String noHakmilik;
    @Column(name = "lokasi", length = 50)
    private String lokasi;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_kump")
    private KumpulanHakmilik kumpulan;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "kod_hakmilik")
    private KodHakmilik kodHakmilik;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "kod_katg_tanah")
    private KodKategoriTanah kategoriTanah;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "kod_tanah")
    private KodTanah kodTanah;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "kod_syarat_nyata")
    private KodSyaratNyata syaratNyata;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn (name = "kod_guna_tanah")
    private KodKegunaanTanah kegunaanTanah;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "kod_sekatan")
    private KodSekatanKepentingan sekatanKepentingan;
    @Column(name = "trh_daftar")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date tarikhDaftar;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "kod_sts_hakmilik")
    private KodStatusHakmilik kodStatusHakmilik;
    @Column(name = "trh_luput")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date tarikhLuput;
    @Column(name = "pegangan")
    private Character pegangan;
    @Column(name = "tempoh_pegang", nullable = true)
    private Integer tempohPegangan;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kod_rizab")
    private KodRizab rizab;
    @Column(name = "lot_bumi")
    private Character lotBumiputera;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kod_pbt")
    private KodPBT pbt;
    @Column(name = "milik_fed")
    private Character milikPersekutuan;
    @OneToMany(mappedBy = "hakmilik", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<HakmilikPihakBerkepentingan> senaraiPihakBerkepentingan;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kod_uom")
    private KodUOM kodUnitLuas;
    @Column(name = "luas", columnDefinition = "number(11,4)", nullable = false)
    private BigDecimal luas;
    @Column(name = "no_litho", length = 50)
    private String noLitho;
    @Column(name = "no_pelan", length = 50)
    private String noPelan;
    @OneToMany(mappedBy = "hakmilik", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Akaun> senaraiAkaun;
    @Column(name = "no_pu")
    private String noPu;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kod_cukai")
    private KodKadarCukai kodKadarCukai;
    @Column(name = "cukai")
    private BigDecimal cukai;
    @Column (name = "cukai_sbnr")
    private BigDecimal cukaiSebenar;
//    @Column (name = "nama_inst", length = 200)
//    private String namaInstitusi;
    @Column(name = "atas_tanah")
    private String maklumatAtasTanah;
    @OneToMany(mappedBy = "hakmilik")
    private List<HakmilikAsal> senaraiHakmilikAsal;
    @OneToMany(mappedBy = "hakmilik")
    private List<HakmilikSebelum> senaraiHakmilikSebelum;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_dhde")
    private Dokumen dhde;

    @Column (name = "no_versi_dhde")
    private int noVersiDhde;

    @Column (name = "no_versi_dhke")
    private int noVersiDhke;

    @Column (name = "trh_batal")
    private Date tarikhBatal;

    @Column (name = "no_fail")
    private String noFail;

    @Embedded
    private InfoAudit infoAudit;

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }


    public KodCawangan getCawangan() {
        return cawangan;
    }


    public KodDaerah getDaerah() {
        return daerah;
    }

    public KodBandarPekanMukim getBandarPekanMukim() {
        return bandarPekanMukim;
    }

    public KodSeksyen getSeksyen() {
        return seksyen;
    }

    public KodLot getLot() {
        return lot;
    }

    public String getNoLot() {
        return noLot;
    }

    public String getNoHakmilik() {
        return noHakmilik;
    }

    public String getLokasi() {
        return lokasi;
    }

    public KumpulanHakmilik getKumpulan() {
        return kumpulan;
    }

    public KodHakmilik getKodHakmilik() {
        return kodHakmilik;
    }

    public KodKategoriTanah getKategoriTanah() {
        return kategoriTanah;
    }

    public KodTanah getKodTanah() {
        return kodTanah;
    }

    public KodSyaratNyata getSyaratNyata() {
        return syaratNyata;
    }

	public KodKegunaanTanah getKegunaanTanah() {
		return kegunaanTanah;
	}

    public KodSekatanKepentingan getSekatanKepentingan() {
        return sekatanKepentingan;
    }

    public void setTarikhDaftar(Date tarikhDaftar) {
        this.tarikhDaftar = tarikhDaftar;
    }

    public Date getTarikhDaftar() {
        return tarikhDaftar;
    }

    public KodStatusHakmilik getKodStatusHakmilik() {
        return kodStatusHakmilik;
    }


    public Date getTarikhLuput() {
        return tarikhLuput;
    }

    public Character getPegangan() {
        return pegangan;
    }

    public List<HakmilikPihakBerkepentingan> getSenaraiPihakBerkepentingan() {
        return senaraiPihakBerkepentingan;
    }

    public Integer getTempohPegangan() {
        return tempohPegangan;
    }

    public KodRizab getRizab() {
        return rizab;
    }

    public Character getLotBumiputera() {
        return lotBumiputera;
    }

    public KodPBT getPbt() {
        return pbt;
    }


    public Character getMilikPersekutuan() {
        return milikPersekutuan;
    }


//    public void setNamaInstitusi(String namaInstitusi) {
//		this.namaInstitusi = namaInstitusi;
//	}
//
//	public String getNamaInstitusi() {
//		return namaInstitusi;
//	}
    public KodUOM getKodUnitLuas() {
        return kodUnitLuas;
    }

    public BigDecimal getLuas() {
        return luas;
    }

    public String getNoLitho() {
        return noLitho;
    }

    public String getNoPelan() {
        return noPelan;
    }


    public String getNoPu() {
        return noPu;
    }


    public KodKadarCukai getKodKadarCukai() {
        return kodKadarCukai;
    }

    public BigDecimal getCukai() {
        return cukai;
    }


    public BigDecimal getCukaiSebenar(){
    	return cukaiSebenar;
    }


    public List<Akaun> getSenaraiAkaun() {
        return senaraiAkaun;
    }


    /**
     * Helper transient property
     * @return
     */
    public Akaun getAkaunCukai() {
        for (Akaun a : senaraiAkaun) {
            if (KOD_AKAUN_CUKAI.equals(a.getKodAkaun().getKod())) {
                return a;
            }
        }
        return null; // not found
    }

    public String getMaklumatAtasTanah() {
        return maklumatAtasTanah;
    }

    public List<HakmilikAsal> getSenaraiHakmilikAsal() {
        return senaraiHakmilikAsal;
    }

    public List<HakmilikSebelum> getSenaraiHakmilikSebelum() {
        return senaraiHakmilikSebelum;
    }

    public Dokumen getDhde() {
        return dhde;
    }

	public int getNoVersiDhde() {
		return noVersiDhde;
	}

	public int getNoVersiDhke() {
		return noVersiDhke;
	}

	public Date getTarikhBatal(){
		return tarikhBatal;
	}


	public String getNoFail() {
		return noFail;
	}

	public InfoAudit getInfoAudit() {
        return infoAudit;
    }

}