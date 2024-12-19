package etanah.view.etapp.htp.ws;

public class Hakmilik {

	private String idHakmilik;
	private String idJenisHakmilik;
	private String noHakmilik;
	private String noWarta;
	private String tarikhWarta;
	private String idLuas;
	private String luas;
	private String idNegeri;
	private String idDaerah;
	private String idMukim;
	private String idLot;
	private String noLot;
	private String noSyit;
	private String noPelan;
	private String syaratNyata;
	private String sekatan;
	private String kegunaanTanah;
	private String idKategori;
	private String idSubkategori;
	private String lokasi;
	private String noBangunan;
	private String noTingkat;
	private String noPetak;
	private String cukai;
	private String tarikhDaftar;
	private String tarafHakmilik;
	private String tempoh;
	private String tarikhLuput;
	private String noPU;
	private String statusPajakan;
	private String tarikhTamatPajakan;
	private String statusRizab;
	private String idJenisRizab;
	private String kawasanRizab;
	private String noRizab;
	private String tarikhRizab;
	private String noFailPTD;
	private String noFailPTG;
	private String statusHakmilik; // D = DAFTAR; B = BATAL
	
	private HakmilikSebelum[] listHakmilikSebelum;
	private HakmilikSambungan[] listHakmilikSambungan;
	
	public String getIdHakmilik() {
		return idHakmilik;
	}
	public void setIdHakmilik(String idHakmilik) {
		this.idHakmilik = idHakmilik;
	}
	public String getIdJenisHakmilik() {
		return idJenisHakmilik;
	}
	public void setIdJenisHakmilik(String idJenisHakmilik) {
		this.idJenisHakmilik = idJenisHakmilik;
	}
	public String getNoHakmilik() {
		return noHakmilik;
	}
	public void setNoHakmilik(String noHakmilik) {
		this.noHakmilik = noHakmilik;
	}
	public String getNoWarta() {
		return noWarta;
	}
	public void setNoWarta(String noWarta) {
		this.noWarta = noWarta;
	}
	public String getTarikhWarta() {
		return tarikhWarta;
	}
	public void setTarikhWarta(String tarikhWarta) {
		this.tarikhWarta = tarikhWarta;
	}
	public String getIdLuas() {
		return idLuas;
	}
	public void setIdLuas(String idLuas) {
		this.idLuas = idLuas;
	}
	public String getLuas() {
		return luas;
	}
	public void setLuas(String luas) {
		this.luas = luas;
	}
	public String getIdNegeri() {
		return idNegeri;
	}
	public void setIdNegeri(String idNegeri) {
		this.idNegeri = idNegeri;
	}
	public String getIdDaerah() {
		return idDaerah;
	}
	public void setIdDaerah(String idDaerah) {
		this.idDaerah = idDaerah;
	}
	public String getIdMukim() {
		return idMukim;
	}
	public void setIdMukim(String idMukim) {
		this.idMukim = idMukim;
	}
	public String getIdLot() {
		return idLot;
	}
	public void setIdLot(String idLot) {
		this.idLot = idLot;
	}
	public String getNoLot() {
		return noLot;
	}
	public void setNoLot(String noLot) {
		this.noLot = noLot;
	}
	public String getNoSyit() {
		return noSyit;
	}
	public void setNoSyit(String noSyit) {
		this.noSyit = noSyit;
	}
	public String getNoPelan() {
		return noPelan;
	}
	public void setNoPelan(String noPelan) {
		this.noPelan = noPelan;
	}
	public String getSyaratNyata() {
		return syaratNyata;
	}
	public void setSyaratNyata(String syaratNyata) {
		this.syaratNyata = syaratNyata;
	}
	public String getSekatan() {
		return sekatan;
	}
	public void setSekatan(String sekatan) {
		this.sekatan = sekatan;
	}
	public String getKegunaanTanah() {
		return kegunaanTanah;
	}
	public void setKegunaanTanah(String kegunaanTanah) {
		this.kegunaanTanah = kegunaanTanah;
	}
	public String getIdKategori() {
		return idKategori;
	}
	public void setIdKategori(String idKategori) {
		this.idKategori = idKategori;
	}
	public String getIdSubkategori() {
		return idSubkategori;
	}
	public void setIdSubkategori(String idSubkategori) {
		this.idSubkategori = idSubkategori;
	}
	public String getLokasi() {
		return lokasi;
	}
	public void setLokasi(String lokasi) {
		this.lokasi = lokasi;
	}
	public String getNoBangunan() {
		return noBangunan;
	}
	public void setNoBangunan(String noBangunan) {
		this.noBangunan = noBangunan;
	}
	public String getNoTingkat() {
		return noTingkat;
	}
	public void setNoTingkat(String noTingkat) {
		this.noTingkat = noTingkat;
	}
	public String getNoPetak() {
		return noPetak;
	}
	public void setNoPetak(String noPetak) {
		this.noPetak = noPetak;
	}
	public String getCukai() {
		return cukai;
	}
	public void setCukai(String cukai) {
		this.cukai = cukai;
	}
	public String getTarikhDaftar() {
		return tarikhDaftar;
	}
	public void setTarikhDaftar(String tarikhDaftar) {
		this.tarikhDaftar = tarikhDaftar;
	}
	public String getTarafHakmilik() {
		return tarafHakmilik;
	}
	public void setTarafHakmilik(String tarafHakmilik) {
		this.tarafHakmilik = tarafHakmilik;
	}
	public String getTempoh() {
		return tempoh;
	}
	public void setTempoh(String tempoh) {
		this.tempoh = tempoh;
	}
	public String getTarikhLuput() {
		return tarikhLuput;
	}
	public void setTarikhLuput(String tarikhLuput) {
		this.tarikhLuput = tarikhLuput;
	}
	public String getNoPU() {
		return noPU;
	}
	public void setNoPU(String noPU) {
		this.noPU = noPU;
	}
	public String getStatusPajakan() {
		return statusPajakan;
	}
	public void setStatusPajakan(String statusPajakan) {
		this.statusPajakan = statusPajakan;
	}
	public String getTarikhTamatPajakan() {
		return tarikhTamatPajakan;
	}
	public void setTarikhTamatPajakan(String tarikhTamatPajakan) {
		this.tarikhTamatPajakan = tarikhTamatPajakan;
	}
	public String getStatusRizab() {
		return statusRizab;
	}
	public void setStatusRizab(String statusRizab) {
		this.statusRizab = statusRizab;
	}
	public String getIdJenisRizab() {
		return idJenisRizab;
	}
	public void setIdJenisRizab(String idJenisRizab) {
		this.idJenisRizab = idJenisRizab;
	}
	public String getKawasanRizab() {
		return kawasanRizab;
	}
	public void setKawasanRizab(String kawasanRizab) {
		this.kawasanRizab = kawasanRizab;
	}
	public String getNoRizab() {
		return noRizab;
	}
	public void setNoRizab(String noRizab) {
		this.noRizab = noRizab;
	}
	public String getTarikhRizab() {
		return tarikhRizab;
	}
	public void setTarikhRizab(String tarikhRizab) {
		this.tarikhRizab = tarikhRizab;
	}
	public String getNoFailPTD() {
		return noFailPTD;
	}
	public void setNoFailPTD(String noFailPTD) {
		this.noFailPTD = noFailPTD;
	}
	public String getNoFailPTG() {
		return noFailPTG;
	}
	public void setNoFailPTG(String noFailPTG) {
		this.noFailPTG = noFailPTG;
	}
	public String getStatusHakmilik() {
		return statusHakmilik;
	}
	public void setStatusHakmilik(String statusHakmilik) {
		this.statusHakmilik = statusHakmilik;
	}

    public HakmilikSebelum[] getListHakmilikSebelum() {
        return listHakmilikSebelum;
    }

    public void setListHakmilikSebelum(HakmilikSebelum[] listHakmilikSebelum) {
        this.listHakmilikSebelum = listHakmilikSebelum;
    }

    public HakmilikSambungan[] getListHakmilikSambungan() {
        return listHakmilikSambungan;
    }

    public void setListHakmilikSambungan(HakmilikSambungan[] listHakmilikSambungan) {
        this.listHakmilikSambungan = listHakmilikSambungan;
    }

}
