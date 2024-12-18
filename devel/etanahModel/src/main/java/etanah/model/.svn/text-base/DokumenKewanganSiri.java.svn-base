package etanah.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table (name = "kew_dokumen_siri")
@SequenceGenerator (name = "seq_kew_dokumen_siri", sequenceName = "seq_kew_dokumen_siri")
public class DokumenKewanganSiri  implements Serializable{
    
   @Id @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "seq_kew_dokumen_siri")
    @Column (name = "id_siri")
    private Long idSiri;
    
    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn (name = "kod_caw")
    private KodCawangan cawangan;

    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn (name = "kod_dokumen")
    private KodDokumen kodDokumen;
    
    @Column (name = "id_kaunter")
    private String idKaunter;
    
            
    @Column (name = "no_siri")
    private Integer noSiri;
    
    @Column (name = "dikunci")
    private String  dikunci;
    
    @Column (name = "kunci_siri")
    private Integer kuncisiri;
    
    @Column (name = "trh_kunci")
    private Date trhKunci;

    public Long getIdSiri() {
        return idSiri;
    }

    public void setIdSiri(Long idSiri) {
        this.idSiri = idSiri;
    }



    public KodCawangan getCawangan() {
        return cawangan;
    }

    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
    }

    public KodDokumen getKodDokumen() {
        return kodDokumen;
    }

    public void setKodDokumen(KodDokumen kodDokumen) {
        this.kodDokumen = kodDokumen;
    }

    public String getIdKaunter() {
        return idKaunter;
    }

    public void setIdKaunter(String idKaunter) {
        this.idKaunter = idKaunter;
    }

    public Integer getNoSiri() {
        return noSiri;
    }

    public void setNoSiri(Integer noSiri) {
        this.noSiri = noSiri;
    }

    public String getDikunci() {
        return dikunci;
    }

    public void setDikunci(String dikunci) {
        this.dikunci = dikunci;
    }

    public Integer getKuncisiri() {
        return kuncisiri;
    }

    public void setKuncisiri(Integer kuncisiri) {
        this.kuncisiri = kuncisiri;
    }

    public Date getTrhKunci() {
        return trhKunci;
    }

    public void setTrhKunci(Date trhKunci) {
        this.trhKunci = trhKunci;
    }
    
    
}
