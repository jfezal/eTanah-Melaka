package etanah.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table (name = "dasar_cukai_notis")
public class DasarTuntutanNotis implements Serializable {
	
	@Id
	@Column (name = "id_notis")
	private long idNotis;
	
	@ManyToOne
	@JoinColumn (name = "kod_caw")
	private KodCawangan cawangan;
	
	@ManyToOne
	@JoinColumn (name = "id_dasar_hakmilik", nullable = false)
	private DasarTuntutanCukaiHakmilik hakmilik;

        @ManyToOne (fetch = FetchType.LAZY)
        @JoinColumn (name = "id_pihak")
        private Pihak pihak;
        
	@Column (name = "no_ruj", length = 20)
	private String noRujukan;
	
	/**
	 * Tempoh notis/peringatan
	 */
	@Column (name = "tempoh_bln")
	private int tempohBulan;
	@Column (name = "tempoh_hari")
	private int tempohHari;
	
	@Column (name = "trh_notis")
	private Date tarikhNotis;
	
	@ManyToOne
	@JoinColumn (name = "kod_notis")
	private KodNotis notis;
	
	@ManyToOne
	@JoinColumn (name = "kod_hantar")
	private KodCaraPenghantaran caraPenghantaran;
	
	@Column (name = "trh_hantar")
	private Date tarikhDihantar;
	
	@ManyToOne
	@JoinColumn (name = "dihantar")
	private Pengguna dihantarOleh;
	
	// TEMPAT2 PENGHANTARAN NOTIS
	
	@Column (name = "tempat_hantar1")
	private String tempatHantar1;
	
	@Column (name = "tempat_hantar2")
	private String tempatHantar2;
	
	@Column (name = "tempat_hantar3")
	private String tempatHantar3;
	
	@Column (name = "tempat_hantar4")
	private String tempatHantar4;
	
	@Column (name = "tempat_hantar5")
	private String tempatHantar5;
	
	@Column (name = "tempat_hantar6")
	private String tempatHantar6;
	
	@Column (name = "tempat_hantar7")
	private String tempatHantar7;
	
	@Column (name = "tempat_hantar8")
	private String tempatHantar8;
	
	@ManyToOne
	@JoinColumn (name = "kod_sts_terima")
	private KodStatusTerima statusTerima;
	
	@Column (name = "trh_terima")
	private Date tarikhTerima;

	@Column (name = "trh_perintah")
	private Date tarikhPerintah;
	
	@Column (name = "terima_catatan")
	private String catatanTerima;
	
	@ManyToOne
	@JoinColumn (name = "id_bukti")
	private Dokumen dokumenBukti;
        
        @Column (name = "kump_warta")
	private String kumpulanWarta;
	
	@Embedded
	private InfoAudit infoAudit;

	public long getIdNotis() {
		return idNotis;
	}

	public void setIdNotis(long idNotis) {
		this.idNotis = idNotis;
	}

	public KodCawangan getCawangan() {
		return cawangan;
	}

	public void setCawangan(KodCawangan cawangan) {
		this.cawangan = cawangan;
	}

	public DasarTuntutanCukaiHakmilik getHakmilik() {
		return hakmilik;
	}

	public void setHakmilik(DasarTuntutanCukaiHakmilik hakmilik) {
		this.hakmilik = hakmilik;
	}

        public Pihak getPihak() {
            return pihak;
        }

        public void setPihak(Pihak pihak) {
            this.pihak = pihak;
        }
	
	public String getNoRujukan() {
		return noRujukan;
	}

	public void setNoRujukan(String noRujukan) {
		this.noRujukan = noRujukan;
	}

	public void setTempohBulan(int tempohBulan) {
		this.tempohBulan = tempohBulan;
	}

	public int getTempohBulan() {
		return tempohBulan;
	}

	public void setTempohHari(int tempohHari) {
		this.tempohHari = tempohHari;
	}

	public int getTempohHari() {
		return tempohHari;
	}

	public Date getTarikhNotis() {
		return tarikhNotis;
	}

	public void setTarikhNotis(Date tarikhNotis) {
		this.tarikhNotis = tarikhNotis;
	}

	public KodNotis getNotis() {
		return notis;
	}

	public void setNotis(KodNotis notis) {
		this.notis = notis;
	}

	public KodCaraPenghantaran getCaraPenghantaran() {
		return caraPenghantaran;
	}

	public void setCaraPenghantaran(KodCaraPenghantaran caraPenghantaran) {
		this.caraPenghantaran = caraPenghantaran;
	}

	public Date getTarikhDihantar() {
		return tarikhDihantar;
	}

	public void setTarikhDihantar(Date tarikhDihantar) {
		this.tarikhDihantar = tarikhDihantar;
	}

	public void setDihantarOleh(Pengguna dihantarOleh) {
		this.dihantarOleh = dihantarOleh;
	}

	public Pengguna getDihantarOleh() {
		return dihantarOleh;
	}

	public String getTempatHantar1() {
		return tempatHantar1;
	}

	public void setTempatHantar1(String tempatHantar1) {
		this.tempatHantar1 = tempatHantar1;
	}

	public String getTempatHantar2() {
		return tempatHantar2;
	}

	public void setTempatHantar2(String tempatHantar2) {
		this.tempatHantar2 = tempatHantar2;
	}

	public String getTempatHantar3() {
		return tempatHantar3;
	}

	public void setTempatHantar3(String tempatHantar3) {
		this.tempatHantar3 = tempatHantar3;
	}

	public String getTempatHantar4() {
		return tempatHantar4;
	}

	public void setTempatHantar4(String tempatHantar4) {
		this.tempatHantar4 = tempatHantar4;
	}

	public String getTempatHantar5() {
		return tempatHantar5;
	}

	public void setTempatHantar5(String tempatHantar5) {
		this.tempatHantar5 = tempatHantar5;
	}

	public String getTempatHantar6() {
		return tempatHantar6;
	}

	public void setTempatHantar6(String tempatHantar6) {
		this.tempatHantar6 = tempatHantar6;
	}

	public String getTempatHantar7() {
		return tempatHantar7;
	}

	public void setTempatHantar7(String tempatHantar7) {
		this.tempatHantar7 = tempatHantar7;
	}

	public String getTempatHantar8() {
		return tempatHantar8;
	}

	public void setTempatHantar8(String tempatHantar8) {
		this.tempatHantar8 = tempatHantar8;
	}

	public KodStatusTerima getStatusTerima() {
		return statusTerima;
	}

	public void setStatusTerima(KodStatusTerima statusTerima) {
		this.statusTerima = statusTerima;
	}

	public Date getTarikhTerima() {
		return tarikhTerima;
	}

	public void setTarikhTerima(Date tarikhTerima) {
		this.tarikhTerima = tarikhTerima;
	}

	public Date getTarikhPerintah() {
		return tarikhPerintah;
	}

	public void setTarikhPerintah(Date tarikhPerintah) {
		this.tarikhPerintah = tarikhPerintah;
	}

	public void setCatatanTerima(String catatanTerima) {
		this.catatanTerima = catatanTerima;
	}

	public String getCatatanTerima() {
		return catatanTerima;
	}

	public Dokumen getDokumenBukti() {
		return dokumenBukti;
	}

	public void setDokumenBukti(Dokumen dokumenBukti) {
		this.dokumenBukti = dokumenBukti;
	}

        public String getKumpulanWarta() {
            return kumpulanWarta;
        }

        public void setKumpulanWarta(String kumpulanWarta) {
            this.kumpulanWarta = kumpulanWarta;
        }

	public InfoAudit getInfoAudit() {
		return infoAudit;
	}

	public void setInfoAudit(InfoAudit infoAudit) {
		this.infoAudit = infoAudit;
	}
	

}
