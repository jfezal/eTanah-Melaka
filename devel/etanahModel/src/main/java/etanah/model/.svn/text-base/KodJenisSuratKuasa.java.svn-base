package etanah.model;


import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

@Entity
@Table (name = "kod_jenis_sb")
@org.hibernate.annotations.Cache (usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE)
public class KodJenisSuratKuasa implements Serializable{
    
    @Id
    @Column (name = "kod", length = 8)
    private BigDecimal kod;
    
    @Column (name = "jenis")
    private String nama;
   

    public void setKod(BigDecimal kod) {
        this.kod = kod;
    }

    public BigDecimal getKod() {
        return kod;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNama() {
        return nama;
    }
    
}
