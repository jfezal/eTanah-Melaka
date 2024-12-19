/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.model;

import etanah.model.ambil.BorangPerPB;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;


/**
 *
 * @author Suhairi
 */
@Entity
@Table(name = "MOHON_MAHKAMAH")
@SequenceGenerator(name = "seq_mohon_mahkamah", sequenceName = "seq_mohon_mahkamah")
@NamedQueries({
    @NamedQuery(name = "MohonMahkamah.findAll", query = "SELECT m FROM PermohonanMahkamah m")})
public class PermohonanMahkamah implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_mohon_mahkamah")
    @Column(name = "ID_MM")
    private Long idMm;


    @ManyToOne
    @JoinColumn (name = "id_mp")
    private PermohonanPihak permohonanPihak;
    @ManyToOne
    @JoinColumn (name = "id_mohon")
    private Permohonan permohonan;
    @ManyToOne
    @JoinColumn (name = "id_borang_pb")
    private BorangPerPB borangPerPB;
    @ManyToOne
    @JoinColumn (name = "id_mh")
    private HakmilikPermohonan hamilikPermohonan;

    @Column(name = "SEBAB_UKUR_TANAH")
    private Character sebabUkurTanah;
    @Column(name = "SEBAB_AMN_PAMPASAN")
    private Character sebabAmnPampasan;
    @Column(name = "SEBAB_PIHAK_PAMPASAN")
    private Character sebabPihakPampasan;
    @Column(name = "SEBAB_UMPUKAN_PAMPASAN")
    private Character sebabUmpukanPampasan;
    @Column(name = "KEPENTINGAN_TANAH")
    private String kepentinganTanah;
    @Column(name = "ALASAN_BANTAH")
    private String alasanBantah;
    @Column(name = "RINGKASAN_BANTAH")
    private String ringkasanBantah;
    @Column(name = "BUTIRAN_TANAH")
    private String butiranTanah;
    @Column(name = "NOTIS")
    private String notis;
    @Column(name = "PERNYATAAN")
    private String pernyataan;
    @Column(name = "AMN_TAWAR_ROSAK")
    private BigDecimal amnTawarRosak;
    @Column(name = "AMN_TAWAR_PAMPASAN")
    private BigDecimal amnTawarPampasan;
    @Column(name = "ALASAN_AMN_PAMPASAN")
    private String alasanAmnPampasan;
    @Column(name = "LAMPIRAN")
    private String lampiran;
    
    @Column(name = "TRH_BICARA")
    private Date tarikh_bicara;
    
    @Column(name = "ULASAN_MAHKAMAH")
    private String ulasan_mahkamah;


    @ManyToOne
    @JoinColumn (name = "KEPUTUSAN_MAHKAMAH")
    private KodKeputusanMahkamah kodKeputusanMahkamah;


    @Column(name = "TAMBAHAN_PAMPASAN")
    private BigDecimal tambahanPampasan;

    @Column(name = "nama_pkp")
    private String namaPenolongKananPendaftar;

    @Column(name = "saman_pemula_bil")
    private String samanPemulaBil;

    @Column(name = "trh_saman")
    private Date tarikhSaman;

    @Column(name = "lokasi_saman")
    private String lokasiSaman;

    @Column(name = "trh_ikrar")
    private Date tarikhIkrar;

    @Column(name = "trh_terima_perintah")
    private Date tarikhTerimaPerintah;

    @Embedded
    private InfoAudit infoAudit;
    

    public PermohonanMahkamah() {
    }

    public PermohonanMahkamah(Long idMm) {
        this.idMm = idMm;
    }

    public Long getIdMm() {
        return idMm;
    }

    public void setIdMm(Long idMm) {
        this.idMm = idMm;
    }

    public PermohonanPihak getPermohonanPihak() {
        return permohonanPihak;
    }

    public void setPermohonanPihak(PermohonanPihak permohonanPihak) {
        this.permohonanPihak = permohonanPihak;
    }

    public Character getSebabUkurTanah() {
        return sebabUkurTanah;
    }

    public void setSebabUkurTanah(Character sebabUkurTanah) {
        this.sebabUkurTanah = sebabUkurTanah;
    }

    public Character getSebabAmnPampasan() {
        return sebabAmnPampasan;
    }

    public void setSebabAmnPampasan(Character sebabAmnPampasan) {
        this.sebabAmnPampasan = sebabAmnPampasan;
    }

    public Character getSebabPihakPampasan() {
        return sebabPihakPampasan;
    }

    public void setSebabPihakPampasan(Character sebabPihakPampasan) {
        this.sebabPihakPampasan = sebabPihakPampasan;
    }

    public Character getSebabUmpukanPampasan() {
        return sebabUmpukanPampasan;
    }

    public void setSebabUmpukanPampasan(Character sebabUmpukanPampasan) {
        this.sebabUmpukanPampasan = sebabUmpukanPampasan;
    }

    public String getKepentinganTanah() {
        return kepentinganTanah;
    }

    public void setKepentinganTanah(String kepentinganTanah) {
        this.kepentinganTanah = kepentinganTanah;
    }

    public String getAlasanBantah() {
        return alasanBantah;
    }

    public void setAlasanBantah(String alasanBantah) {
        this.alasanBantah = alasanBantah;
    }

    public String getRingkasanBantah() {
        return ringkasanBantah;
    }

    public void setRingkasanBantah(String ringkasanBantah) {
        this.ringkasanBantah = ringkasanBantah;
    }

    public String getButiranTanah() {
        return butiranTanah;
    }

    public void setButiranTanah(String butiranTanah) {
        this.butiranTanah = butiranTanah;
    }

    public String getNotis() {
        return notis;
    }

    public void setNotis(String notis) {
        this.notis = notis;
    }

    public String getPernyataan() {
        return pernyataan;
    }

    public void setPernyataan(String pernyataan) {
        this.pernyataan = pernyataan;
    }

    public BigDecimal getAmnTawarRosak() {
        return amnTawarRosak;
    }

    public void setAmnTawarRosak(BigDecimal amnTawarRosak) {
        this.amnTawarRosak = amnTawarRosak;
    }

    public BigDecimal getAmnTawarPampasan() {
        return amnTawarPampasan;
    }

    public void setAmnTawarPampasan(BigDecimal amnTawarPampasan) {
        this.amnTawarPampasan = amnTawarPampasan;
    }

    public String getAlasanAmnPampasan() {
        return alasanAmnPampasan;
    }

    public void setAlasanAmnPampasan(String alasanAmnPampasan) {
        this.alasanAmnPampasan = alasanAmnPampasan;
    }

    public String getLampiran() {
        return lampiran;
    }

    public void setLampiran(String lampiran) {
        this.lampiran = lampiran;
    }

    public KodKeputusanMahkamah getKodKeputusanMahkamah() {
        return kodKeputusanMahkamah;
    }

    public void setKodKeputusanMahkamah(KodKeputusanMahkamah kodKeputusanMahkamah) {
        this.kodKeputusanMahkamah = kodKeputusanMahkamah;
    }




    public BigDecimal getTambahanPampasan() {
        return tambahanPampasan;
    }

    public void setTambahanPampasan(BigDecimal tambahanPampasan) {
        this.tambahanPampasan = tambahanPampasan;
    }

    public String getLokasiSaman() {
        return lokasiSaman;
    }

    public void setLokasiSaman(String lokasiSaman) {
        this.lokasiSaman = lokasiSaman;
    }

    public String getNamaPenolongKananPendaftar() {
        return namaPenolongKananPendaftar;
    }

    public void setNamaPenolongKananPendaftar(String namaPenolongKananPendaftar) {
        this.namaPenolongKananPendaftar = namaPenolongKananPendaftar;
    }

    public String getSamanPemulaBil() {
        return samanPemulaBil;
    }

    public void setSamanPemulaBil(String samanPemulaBil) {
        this.samanPemulaBil = samanPemulaBil;
    }

    public Date getTarikhIkrar() {
        return tarikhIkrar;
    }

    public void setTarikhIkrar(Date tarikhIkrar) {
        this.tarikhIkrar = tarikhIkrar;
    }

    public Date getTarikhSaman() {
        return tarikhSaman;
    }

    public void setTarikhSaman(Date tarikhSaman) {
        this.tarikhSaman = tarikhSaman;
    }

    public Date getTarikhTerimaPerintah() {
        return tarikhTerimaPerintah;
    }

    public void setTarikhTerimaPerintah(Date tarikhTerimaPerintah) {
        this.tarikhTerimaPerintah = tarikhTerimaPerintah;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public BorangPerPB getBorangPerPB() {
        return borangPerPB;
    }

    public void setBorangPerPB(BorangPerPB borangPerPB) {
        this.borangPerPB = borangPerPB;
    }

    public HakmilikPermohonan getHamilikPermohonan() {
        return hamilikPermohonan;
    }

    public void setHamilikPermohonan(HakmilikPermohonan hamilikPermohonan) {
        this.hamilikPermohonan = hamilikPermohonan;
    }

    public Date getTarikh_bicara() {
        return tarikh_bicara;
    }

    public void setTarikh_bicara(Date tarikh_bicara) {
        this.tarikh_bicara = tarikh_bicara;
    }

    public String getUlasan_mahkamah() {
        return ulasan_mahkamah;
    }

    public void setUlasan_mahkamah(String ulasan_mahkamah) {
        this.ulasan_mahkamah = ulasan_mahkamah;
    }
    
    
    



    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMm != null ? idMm.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PermohonanMahkamah)) {
            return false;
        }
        PermohonanMahkamah other = (PermohonanMahkamah) object;
        if ((this.idMm == null && other.idMm != null) || (this.idMm != null && !this.idMm.equals(other.idMm))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "etanah.model.MohonMahkamah[idMm=" + idMm + "]";
    }

	public void setInfoAudit(InfoAudit infoAudit) {
		this.infoAudit = infoAudit;
	}

	public InfoAudit getInfoAudit() {
		return infoAudit;
	}

}
