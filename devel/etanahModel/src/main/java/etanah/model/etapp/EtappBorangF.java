package etanah.model.etapp;

import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "ETAPP_BORANGF")
@SequenceGenerator(name = "seq_ETAPP_BORANGF", sequenceName = "seq_ETAPP_BORANGF")
public class EtappBorangF implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_ETAPP_BORANGF")
    @Column(name = "ID_ET_BF")
    private Long idEtBf;
    // tempat aduan dibuat
    @ManyToOne
    @JoinColumn(name = "ID_ET_HAKMILIK", nullable = false)
    private EtappHakmilik etappHakmilik;

    @Column(name = "NAMA_WARIS")
    private String namaWaris;
    
    @Column(name = "NO_KP_WARIS")
    private String noKpWaris;
    
    @Column(name = "BANDAR")
    private String bandar;
    
    @Column(name = "POSKOD")
    private String poskod;
    
    @Column(name = "NEGERI")
    private String negeri;
    
    @Column(name = "ALAMAT_3")
    private String alamat3;
    
    @Column(name = "ALAMAT_2")
    private String alamat2;
      @Column(name = "ALAMAT_1")
    private String alamat1;
        
    @Column(name = "HUBUNGAN")
    private String hubungan;
    @Column(name = "STATUS_PERKAHWINAN")
    private String statusPerkahwinan;
    
    @Column(name = "KEWARGANEGARAAN")
    private String kewarganaan;
    
    @Column(name = "JANTINA")
    private String jantina;
    
    @Column(name = "UMUR")
    private String umur;
    
    @Column(name = "TARIKH_LAHIR")
    private String trhLahir;
    
    @Column(name = "JENIS_PENGENALAN")
    private String jenisPengenalan;
    
    @Column(name = "JENIS_PIHAK_BEKEPENTINGAN")
    private String jenisPihakKepentingan;
    @Embedded
    private InfoAudit infoAudit;
 
    public Long getIdEtBf() {
        return idEtBf;
    }

    public void setIdEtBf(Long idEtBf) {
        this.idEtBf = idEtBf;
    }

    public String getAlamat1() {
        return alamat1;
    }

    public void setAlamat1(String alamat1) {
        this.alamat1 = alamat1;
    }

    public String getHubungan() {
        return hubungan;
    }

    public void setHubungan(String hubungan) {
        this.hubungan = hubungan;
    }

   

   



    public EtappHakmilik getEtappHakmilik() {
        return etappHakmilik;
    }

    public void setEtappHakmilik(EtappHakmilik etappHakmilik) {
        this.etappHakmilik = etappHakmilik;
    }



    public String getNamaWaris() {
        return namaWaris;
    }

    public void setNamaWaris(String namaWaris) {
        this.namaWaris = namaWaris;
    }

    public String getNoKpWaris() {
        return noKpWaris;
    }

    public void setNoKpWaris(String noKpWaris) {
        this.noKpWaris = noKpWaris;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    public String getBandar() {
        return bandar;
    }

    public void setBandar(String bandar) {
        this.bandar = bandar;
    }

    public String getPoskod() {
        return poskod;
    }

    public void setPoskod(String poskod) {
        this.poskod = poskod;
    }

    public String getNegeri() {
        return negeri;
    }

    public void setNegeri(String negeri) {
        this.negeri = negeri;
    }

    public String getAlamat3() {
        return alamat3;
    }

    public void setAlamat3(String alamat3) {
        this.alamat3 = alamat3;
    }

    public String getAlamat2() {
        return alamat2;
    }

    public void setAlamat2(String alamat2) {
        this.alamat2 = alamat2;
    }

    public String getStatusPerkahwinan() {
        return statusPerkahwinan;
    }

    public void setStatusPerkahwinan(String statusPerkahwinan) {
        this.statusPerkahwinan = statusPerkahwinan;
    }

    public String getKewarganaan() {
        return kewarganaan;
    }

    public void setKewarganaan(String kewarganaan) {
        this.kewarganaan = kewarganaan;
    }

    public String getJantina() {
        return jantina;
    }

    public void setJantina(String jantina) {
        this.jantina = jantina;
    }

    public String getUmur() {
        return umur;
    }

    public void setUmur(String umur) {
        this.umur = umur;
    }

    public String getTrhLahir() {
        return trhLahir;
    }

    public void setTrhLahir(String trhLahir) {
        this.trhLahir = trhLahir;
    }

    public String getJenisPengenalan() {
        return jenisPengenalan;
    }

    public void setJenisPengenalan(String jenisPengenalan) {
        this.jenisPengenalan = jenisPengenalan;
    }

    public String getJenisPihakKepentingan() {
        return jenisPihakKepentingan;
    }

    public void setJenisPihakKepentingan(String jenisPihakKepentingan) {
        this.jenisPihakKepentingan = jenisPihakKepentingan;
    }


}
