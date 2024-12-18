/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package etanah.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;



/**
 *
 * @author nurulwahida
 */
@Entity
@Table(name = "Info_Mmkn")
@SequenceGenerator(name = "seq_infommkn", sequenceName = "seq_infommkn")

public class InfoMmkn  implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_infommkn")
    @Column(name = "id_infommkn", length = 23)
     private Long idInfoMmkn;
    
     @ManyToOne
    @JoinColumn(name = "id_mohon")
    private Permohonan permohonan;
     
     @ManyToOne
    @JoinColumn(name = "KOD_PERINGKAT")
    private KodPeringkat kodPeringkat;
   
    @Column(name = "NO_FAILMMKN")
    private String noFailMmkn;
    
    @Column(name = "KOD_KPSN")
    private String kodKpsn;
    
    @Column(name = "TRH_SIDANG")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date trhSidang;
    
    @Column(name = "TEMPAT_SIDANG")
    private String tempatSidang;
   
    @Column(name = "TRH_SAH")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date trhSah;
   
    @Column(name = "KETERANGAN_MMKN")
    private String keteranganMmkn;
      
    
   @Embedded
    private InfoAudit infoAudit;

    public Long getIdInfoMmkn() {
        return idInfoMmkn;
    }

    public void setIdInfoMmkn(Long idInfoMmkn) {
        this.idInfoMmkn = idInfoMmkn;
    }

    

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public KodPeringkat getKodPeringkat() {
        return kodPeringkat;
    }

    public void setKodPeringkat(KodPeringkat kodPeringkat) {
        this.kodPeringkat = kodPeringkat;
    }

    public String getNoFailMmkn() {
        return noFailMmkn;
    }

    public void setNoFailMmkn(String noFailMmkn) {
        this.noFailMmkn = noFailMmkn;
    }

    public String getKodKpsn() {
        return kodKpsn;
    }

    public void setKodKpsn(String kodKpsn) {
        this.kodKpsn = kodKpsn;
    }

    public String getTempatSidang() {
        return tempatSidang;
    }

    public void setTempatSidang(String tempatSidang) {
        this.tempatSidang = tempatSidang;
    }

    public String getKeteranganMmkn() {
        return keteranganMmkn;
    }

    public void setKeteranganMmkn(String keteranganMmkn) {
        this.keteranganMmkn = keteranganMmkn;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    public Date getTrhSidang() {
        return trhSidang;
    }

    public void setTrhSidang(Date trhSidang) {
        this.trhSidang = trhSidang;
    }

    public Date getTrhSah() {
        return trhSah;
    }

    public void setTrhSah(Date trhSah) {
        this.trhSah = trhSah;
    }

     
    
}
