package etanah.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "kehadiran")
@SequenceGenerator (name = "seq_kehadiran", sequenceName = "seq_kehadiran")
public class Kehadiran implements Serializable {

    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "seq_kehadiran")
    @Column(name = "id_hadir")
    private long idKehadiran;

    @ManyToOne
    @JoinColumn(name = "id_enkuiri")
    private Enkuiri enkuiri;

    @ManyToOne
    @JoinColumn(name = "id_mp")
    private PermohonanPihak permohonanPihak;

    @ManyToOne
    @JoinColumn(name = "kod_penyerah")
    private KodPenyerah kodPenyerah;

    @Column(name = "hadir", length=1)
    private String hadir;

    @Column(name = "id_penyerah")
    private Integer idPenyerah;

    @Column(name = "catatan", length = 100)
    private String catatan;

    @Column(name = "jawatan", length = 50)
    private String jawatan;

    @Column(name = "no_tel", length = 20)
    private String noTelefon;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "wakil_id_lantikan")
    private Dokumen wakilSuratLantikan;

    @ManyToOne
    @JoinColumn (name = "wakil_kod_pengenalan")
    private KodJenisPengenalan wakilJenisPengenalan;

    @Column (name = "wakil_no_pengenalan")
    private String wakilNoPengenalan;

    @Column (name = "wakil_nama")
    private String wakilNama;

    @Column (name = "wakil_no_tel")
    private String wakilNoTelefon;

    @Column (name = "wakil_jawatan")
    private String wakilJawatan;

    @Column (name = "keterangan")
    private String keterangan;

    @ManyToOne
    @JoinColumn (name = "id_hp")
    private HakmilikPihakBerkepentingan hakmilikPihakBerkepentingan;


    @ManyToOne
    @JoinColumn(name = "kod_senarai")
    private KodSenarai kodSenarai;


    @ManyToOne
    @JoinColumn(name = "kod_senarai_ruj")
    private SenaraiRujukan senaraiRujukan;
    
    @Column (name = "trh_hadir")
    private Date tarikhKehadiran;
    

    @Embedded
    private InfoAudit infoAudit;

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public Enkuiri getEnkuiri() {
        return enkuiri;
    }

    public void setEnkuiri(Enkuiri enkuiri) {
        this.enkuiri = enkuiri;
    }

    public String getHadir() {
        return hadir;
    }

    public void setHadir(String hadir) {
        this.hadir = hadir;
    }

    public long getIdKehadiran() {
        return idKehadiran;
    }

    public void setIdKehadiran(long idKehadiran) {
        this.idKehadiran = idKehadiran;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    public PermohonanPihak getPermohonanPihak() {
        return permohonanPihak;
    }

    public void setPermohonanPihak(PermohonanPihak permohonanPihak) {
        this.permohonanPihak = permohonanPihak;
    }

    public Integer getIdPenyerah() {
        return idPenyerah;
    }

    public void setIdPenyerah(Integer idPenyerah) {
        this.idPenyerah = idPenyerah;
    }

    public KodPenyerah getKodPenyerah() {
        return kodPenyerah;
    }

    public void setKodPenyerah(KodPenyerah kodPenyerah) {
        this.kodPenyerah = kodPenyerah;
    }

    public String getJawatan() {
        return jawatan;
    }

    public void setJawatan(String jawatan) {
        this.jawatan = jawatan;
    }

    public String getNoTelefon() {
        return noTelefon;
    }

    public void setNoTelefon(String noTelefon) {
        this.noTelefon = noTelefon;
    }

	public void setWakilSuratLantikan(Dokumen suratLantikan) {
		this.wakilSuratLantikan = suratLantikan;
	}

	public Dokumen getWakilSuratLantikan() {
		return wakilSuratLantikan;
	}

	public KodJenisPengenalan getWakilJenisPengenalan() {
		return wakilJenisPengenalan;
	}

	public void setWakilJenisPengenalan(KodJenisPengenalan wakilJenisPengenalan) {
		this.wakilJenisPengenalan = wakilJenisPengenalan;
	}

	public String getWakilNoPengenalan() {
		return wakilNoPengenalan;
	}

	public void setWakilNoPengenalan(String wakilNoPengenalan) {
		this.wakilNoPengenalan = wakilNoPengenalan;
	}

	public String getWakilNama() {
		return wakilNama;
	}

	public void setWakilNama(String wakilNama) {
		this.wakilNama = wakilNama;
	}

    public String getWakilJawatan() {
        return wakilJawatan;
    }

    public void setWakilJawatan(String wakilJawatan) {
        this.wakilJawatan = wakilJawatan;
    }

    public String getWakilNoTelefon() {
        return wakilNoTelefon;
    }

    public void setWakilNoTelefon(String wakilNoTelefon) {
        this.wakilNoTelefon = wakilNoTelefon;
    }

	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
	}

	public String getKeterangan() {
		return keterangan;
	}

    public HakmilikPihakBerkepentingan getHakmilikPihakBerkepentingan() {
        return hakmilikPihakBerkepentingan;
    }

    public void setHakmilikPihakBerkepentingan(HakmilikPihakBerkepentingan hakmilikPihakBerkepentingan) {
        this.hakmilikPihakBerkepentingan = hakmilikPihakBerkepentingan;
    }

    public KodSenarai getKodSenarai() {
        return kodSenarai;
    }

    public void setKodSenarai(KodSenarai kodSenarai) {
        this.kodSenarai = kodSenarai;
    }

    public SenaraiRujukan getSenaraiRujukan() {
        return senaraiRujukan;
    }

    public void setSenaraiRujukan(SenaraiRujukan senaraiRujukan) {
        this.senaraiRujukan = senaraiRujukan;
    }

    public Date getTarikhKehadiran() {
        return tarikhKehadiran;
    }

    public void setTarikhKehadiran(Date tarikhKehadiran) {
        this.tarikhKehadiran = tarikhKehadiran;
    }

        
}
