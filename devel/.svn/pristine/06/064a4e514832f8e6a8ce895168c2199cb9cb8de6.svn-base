package etanah.model.etapp;

import etanah.model.Hakmilik;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "ETAPP_PERINTAH")
@SequenceGenerator(name = "seq_ETAPP_PERINTAH", sequenceName = "seq_ETAPP_PERINTAH")
public class EtappPerintah implements Serializable {

    @Id
   // @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_ETAPP_PERINTAH")
    @Column(name = "no_fail")
    private String noFail;
    
    @Column(name = "nama_simati")
    private String namasimati;
    
    @Column(name = "no_kp_simati")
    private String noKpSimati;
    
    @Column(name = "tarikh_mati")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date tarikhMati;
    
    @Column(name = "no_sijil_mati")
    private String noSijilMati;
    
    @Column(name = "nama_pemohon")
    private String namaPemohon;
    
    @Column(name = "no_kp_pemohon")
    private String noKpPemohon;
    
    @Column(name = "alamat_pemohon1")
    private String alamatPemohon1;
    
    
    @Column(name = "alamat_pemohon2")
    private String alamatPemohon2;
    
    @Column(name = "alamat_pemohon3")
    private String alamatPemohon3;
    
    
    @Column(name = "poskod_pemohon")
    private String poskodPemohon;
    
    
    @Column(name = "tmp_bicara")
    private String tmpBicara;
    
    
    @Column(name = "alamat_bicara1")
    private String alamatBicara1;
    
    @Column(name = "alamat_bicara2")
    private String alamatBicara2;
    
    @Column(name = "alamat_bicara3")
    private String alamatBicara3;
    
    
    @Column(name = "poskod_bicara")
    private String poskodBicara;
    
    
    @Column(name = "negeri_bicara")
    private String negeriBicara;
    
    
    @Column(name = "pegawai_bicara")
    private String pegawaiBicara;
    
    
    @Column(name = "tarikh_perintah")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date tarikhPerintah;
    
    @OneToMany (mappedBy = "etappPerintah", fetch = FetchType.LAZY)
    private List<EtappHakmilik> listEtappHakmilik;

    public List<EtappHakmilik> getListEtappHakmilik() {
        return listEtappHakmilik;
    }

    public void setListEtappHakmilik(List<EtappHakmilik> listEtappHakmilik) {
        this.listEtappHakmilik = listEtappHakmilik;
    }
    
    
        
    
    @Embedded
    private InfoAudit infoAudit;

    public String getNoFail() {
        return noFail;
    }

    public void setNoFail(String noFail) {
        this.noFail = noFail;
    }



    public String getNamasimati() {
        return namasimati;
    }

    public void setNamasimati(String namasimati) {
        this.namasimati = namasimati;
    }

    public String getNoKpSimati() {
        return noKpSimati;
    }

    public void setNoKpSimati(String noKpSimati) {
        this.noKpSimati = noKpSimati;
    }

    public Date getTarikhMati() {
        return tarikhMati;
    }

    public void setTarikhMati(Date tarikhMati) {
        this.tarikhMati = tarikhMati;
    }

 

    public String getNoSijilMati() {
        return noSijilMati;
    }

    public void setNoSijilMati(String noSijilMati) {
        this.noSijilMati = noSijilMati;
    }

    public String getNamaPemohon() {
        return namaPemohon;
    }

    public void setNamaPemohon(String namaPemohon) {
        this.namaPemohon = namaPemohon;
    }

    public String getNoKpPemohon() {
        return noKpPemohon;
    }

    public void setNoKpPemohon(String noKpPemohon) {
        this.noKpPemohon = noKpPemohon;
    }

    public String getAlamatPemohon1() {
        return alamatPemohon1;
    }

    public void setAlamatPemohon1(String alamatPemohon1) {
        this.alamatPemohon1 = alamatPemohon1;
    }

    public String getAlamatPemohon2() {
        return alamatPemohon2;
    }

    public void setAlamatPemohon2(String alamatPemohon2) {
        this.alamatPemohon2 = alamatPemohon2;
    }

    public String getAlamatPemohon3() {
        return alamatPemohon3;
    }

    public void setAlamatPemohon3(String alamatPemohon3) {
        this.alamatPemohon3 = alamatPemohon3;
    }

    public String getPoskodPemohon() {
        return poskodPemohon;
    }

    public void setPoskodPemohon(String poskodPemohon) {
        this.poskodPemohon = poskodPemohon;
    }

    public String getTmpBicara() {
        return tmpBicara;
    }

    public void setTmpBicara(String tmpBicara) {
        this.tmpBicara = tmpBicara;
    }

    public String getAlamatBicara1() {
        return alamatBicara1;
    }

    public void setAlamatBicara1(String alamatBicara1) {
        this.alamatBicara1 = alamatBicara1;
    }

    public String getAlamatBicara2() {
        return alamatBicara2;
    }

    public void setAlamatBicara2(String alamatBicara2) {
        this.alamatBicara2 = alamatBicara2;
    }

    public String getAlamatBicara3() {
        return alamatBicara3;
    }

    public void setAlamatBicara3(String alamatBicara3) {
        this.alamatBicara3 = alamatBicara3;
    }

    public String getPoskodBicara() {
        return poskodBicara;
    }

    public void setPoskodBicara(String poskodBicara) {
        this.poskodBicara = poskodBicara;
    }

    public String getNegeriBicara() {
        return negeriBicara;
    }

    public void setNegeriBicara(String negeriBicara) {
        this.negeriBicara = negeriBicara;
    }

    public String getPegawaiBicara() {
        return pegawaiBicara;
    }

    public void setPegawaiBicara(String pegawaiBicara) {
        this.pegawaiBicara = pegawaiBicara;
    }

    public Date getTarikhPerintah() {
        return tarikhPerintah;
    }

    public void setTarikhPerintah(Date tarikhPerintah) {
        this.tarikhPerintah = tarikhPerintah;
    }



    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }


   


}
