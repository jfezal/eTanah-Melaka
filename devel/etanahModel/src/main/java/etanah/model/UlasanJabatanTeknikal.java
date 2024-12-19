/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author User
 */
@Entity
@Table(name = "JTEK_ULAS")
@SequenceGenerator(name = "seq_jtek_ulas", sequenceName = "seq_jtek_ulas")

public class UlasanJabatanTeknikal implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_jtek_ulas")
    @Column(name = "ID_JTEK_ULAS")
    private Long idJtekUlas;
    @Column(name = "NAMA_PENYELIA")
    private String namaPenyelia;
    @Column(name = "NO_RUJ")
    private String noRuj;
    @Column(name = "ULASAN")
    private String ulasan;
    @Column(name = "TRH_SEDIA")
    
    private Date trhSedia;
    @Column(name = "TRH_SELESAI")
    
    private Date trhSelesai;
    
    @OneToMany (mappedBy = "ulasanJabatanTeknikal", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<JteknikalDokumenTerima> senaraiDokumenTerima;

    @ManyToOne
    @JoinColumn(name = "kod_caw")
    private KodCawangan kodCawangan;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "id_mohon")
    private Permohonan permohonan;

    @ManyToOne
    @JoinColumn(name = "kod_sts")
    private KodStatusUlasanJabatanTeknikal kodStatusUlasanJabatanTeknikal;

    @ManyToOne
    @JoinColumn(name = "kod_agensi")
    private KodAgensi kodAgensi;

    @ManyToOne
    @JoinColumn(name = "kod_syor")
    private KodSyorUlasanJabatanTeknikal kodSyorUlasanJabatanTeknikal;


	@Embedded
	private InfoAudit infoAudit;

    public UlasanJabatanTeknikal() {
    }

    public UlasanJabatanTeknikal(Long idJtekUlas) {
        this.idJtekUlas = idJtekUlas;
    }



    public Long getIdJtekUlas() {
        return idJtekUlas;
    }

    public void setIdJtekUlas(Long idJtekUlas) {
        this.idJtekUlas = idJtekUlas;
    }

    public String getNamaPenyelia() {
        return namaPenyelia;
    }

    public void setNamaPenyelia(String namaPenyelia) {
        this.namaPenyelia = namaPenyelia;
    }

    public String getNoRuj() {
        return noRuj;
    }

    public void setNoRuj(String noRuj) {
        this.noRuj = noRuj;
    }

    public String getUlasan() {
        return ulasan;
    }

    public void setUlasan(String ulasan) {
        this.ulasan = ulasan;
    }

    public Date getTrhSedia() {
        return trhSedia;
    }

    public void setTrhSedia(Date trhSedia) {
        this.trhSedia = trhSedia;
    }

    public Date getTrhSelesai() {
        return trhSelesai;
    }

    public void setTrhSelesai(Date trhSelesai) {
        this.trhSelesai = trhSelesai;
    }

    public KodAgensi getKodAgensi() {
        return kodAgensi;
    }

    public void setKodAgensi(KodAgensi kodAgensi) {
        this.kodAgensi = kodAgensi;
    }

    public KodCawangan getKodCawangan() {
        return kodCawangan;
    }

    public void setKodCawangan(KodCawangan kodCawangan) {
        this.kodCawangan = kodCawangan;
    }

    public KodStatusUlasanJabatanTeknikal getKodStatusUlasanJabatanTeknikal() {
        return kodStatusUlasanJabatanTeknikal;
    }

    public void setKodStatusUlasanJabatanTeknikal(KodStatusUlasanJabatanTeknikal kodStatusUlasanJabatanTeknikal) {
        this.kodStatusUlasanJabatanTeknikal = kodStatusUlasanJabatanTeknikal;
    }

    public KodSyorUlasanJabatanTeknikal getKodSyorUlasanJabatanTeknikal() {
        return kodSyorUlasanJabatanTeknikal;
    }

    public void setKodSyorUlasanJabatanTeknikal(KodSyorUlasanJabatanTeknikal kodSyorUlasanJabatanTeknikal) {
        this.kodSyorUlasanJabatanTeknikal = kodSyorUlasanJabatanTeknikal;
    }


    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    public List<JteknikalDokumenTerima> getSenaraiDokumenTerima() {
        return senaraiDokumenTerima;
    }

    public void setSenaraiDokumenTerima(List<JteknikalDokumenTerima> senaraiDokumenTerima) {
        this.senaraiDokumenTerima = senaraiDokumenTerima;
    }
    
    
}
