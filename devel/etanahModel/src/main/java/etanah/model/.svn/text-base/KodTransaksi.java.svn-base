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

@Entity
@Table (name = "kod_kew_trans")
@org.hibernate.annotations.Cache (usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE)
public class KodTransaksi implements Serializable{
    @Id
    @Column (name = "kod", length = 8)
    private String kod;
    
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "kod_katg_trans")
    private KodKategoriTransaksi kategoriTransaksi;
    
    @Column (name = "nama", length = 256, nullable = false, unique = true)
    private String nama;
    
    @Column (name  = "aktif", length = 1)
    private char aktif;
    
    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn (name = "kod_akaun_dt")
    private KodAkaun akaunDebit;

    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn (name = "kod_akaun_kr")
    private KodAkaun akaunKredit;
    
    @Column (name = "utk_fed")
    private char untukPersekutuan;
    
    @Column (name = "amanah")
    private char transaksiAmanah;
    
    @Column (name = "utama")
    private int keutamaan;

    @Embedded
    private InfoAudit infoAudit;

    public void setKod(String kod) {
        this.kod = kod;
    }

    public String getKod() {
        return kod;
    }

    public void setKategoriTransaksi(KodKategoriTransaksi kategoriTransaksi) {
		this.kategoriTransaksi = kategoriTransaksi;
	}

	public KodKategoriTransaksi getKategoriTransaksi() {
		return kategoriTransaksi;
	}

	public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNama() {
        return nama;
    }

    public void setAktif(char aktif) {
        this.aktif = aktif;
    }

    public char getAktif() {
        return aktif;
    }

    public void setAkaunDebit(KodAkaun akaunDebit) {
        this.akaunDebit = akaunDebit;
    }

    public KodAkaun getAkaunDebit() {
        return akaunDebit;
    }

    public void setAkaunKredit(KodAkaun akaunKredit) {
        this.akaunKredit = akaunKredit;
    }

    public KodAkaun getAkaunKredit() {
        return akaunKredit;
    }

    public char getUntukPersekutuan() {
		return untukPersekutuan;
	}

	public void setUntukPersekutuan(char untukPersekutuan) {
		this.untukPersekutuan = untukPersekutuan;
	}

	public void setTransaksiAmanah(char transaksiAmanah) {
		this.transaksiAmanah = transaksiAmanah;
	}

	public char getTransaksiAmanah() {
		return transaksiAmanah;
	}

	public void setKeutamaan(int keutamaan) {
		this.keutamaan = keutamaan;
	}

	public int getKeutamaan() {
		return keutamaan;
	}

	public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }
}
