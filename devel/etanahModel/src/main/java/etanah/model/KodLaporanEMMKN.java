/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author faidzal
 */
@Entity
@Table(name = "kod_laporan_emmkn")
public class KodLaporanEMMKN implements Serializable {

    @Id
    @Column(name = "kod_laporan_emmkn")
    private Integer kodEmmkn;
    
    @Column(name = "nama_laporan")
    private String namaLaporan;
    
    @Column(name = "jns_laporan")
    private char jenisLaporan;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kod_urusan")
    private KodUrusan kodUrusan;
    
     @Embedded
    private InfoAudit infoAudit;

    public Integer getKodEmmkn() {
        return kodEmmkn;
    }

    public void setKodEmmkn(Integer kodEmmkn) {
        this.kodEmmkn = kodEmmkn;
    }



    public String getNamaLaporan() {
        return namaLaporan;
    }

    public void setNamaLaporan(String namaLaporan) {
        this.namaLaporan = namaLaporan;
    }

    public char getJenisLaporan() {
        return jenisLaporan;
    }

    public void setJenisLaporan(char jenisLaporan) {
        this.jenisLaporan = jenisLaporan;
    }

    public KodUrusan getKodUrusan() {
        return kodUrusan;
    }

    public void setKodUrusan(KodUrusan kodUrusan) {
        this.kodUrusan = kodUrusan;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }
     
     
}
