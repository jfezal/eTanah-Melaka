package etanah.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table (name = "kod_dun")
@org.hibernate.annotations.Cache (usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE)
public class KodDUN implements Serializable{
    
    @Id
    @Column (name = "KOD")
    private String kod;

    @Column (name = "nama" )
    private String nama;

	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "kod_agensi" )
	private KodAgensi kodAgensi;
        
        @ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "kod_kws_parlimen" )
	private KodKawasanParlimen kodKawasanParlimen;

    @Column (name = "AKTIF", length = 1)
    private char aktif;
    
    @Embedded
    private InfoAudit infoAudit;



    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNama() {
        return nama;
    }

    public KodAgensi getKodAgensi() {
        return kodAgensi;
    }

    public void setKodAgensi(KodAgensi kodAgensi) {
        this.kodAgensi = kodAgensi;
    }

    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

 

    public void setAktif(char aktif) {
        this.aktif = aktif;
    }

    public char isAktif() {
        return aktif;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public KodKawasanParlimen getKodKawasanParlimen() {
        return kodKawasanParlimen;
    }

    public void setKodKawasanParlimen(KodKawasanParlimen kodKawasanParlimen) {
        this.kodKawasanParlimen = kodKawasanParlimen;
    }
    
    
}
