/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.nota;

import etanah.model.Dokumen;
import etanah.model.Hakmilik;
import etanah.model.HakmilikAsalPermohonan;
import etanah.model.HakmilikSebelumPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodBandarPekanMukim;
import etanah.model.KodCawangan;
import etanah.model.KodDUN;
import etanah.model.KodHakmilik;
import etanah.model.KodKadarCukai;
import etanah.model.KodKategoriTanah;
import etanah.model.KodKawasanParlimen;
import etanah.model.KodKegunaanTanah;
import etanah.model.KodKeputusan;
import etanah.model.KodLot;
import etanah.model.KodPBT;
import etanah.model.KodPemilikan;
import etanah.model.KodPerhubunganHakmilik;
import etanah.model.KodSekatanKepentingan;
import etanah.model.KodSeksyen;
import etanah.model.KodSyaratNyata;
import etanah.model.KodUOM;
import etanah.model.LaporanTanah;
import etanah.model.Permohonan;
import etanah.model.PermohonanPembetulanHakmilik;
import etanah.model.PermohonanPihakWakil;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 *
 * @author zipzap
 */
public class HakmilikPermohonanData {

    private Permohonan permohonan;
   
    private Hakmilik hakmilik;
   
    private BigDecimal luasTerlibat;


   
    private BigDecimal luasDiluluskan;

 
    private BigDecimal luasUkurHalus;

    private BigDecimal luasPelanB1;

    private KodUOM kodUnitLuas;


    private KodUOM luasUkurHalusUom;

    private KodUOM luasLulusUom;

    private KodUOM luasPelanB1Uom;

   
    /*
     * fikri : for convert txn only
     */
    private KodSeksyen kodSeksyen;

    private KodBandarPekanMukim bandarPekanMukimBaru;
    
    private String permit;
    
    private KodHakmilik kodHakmilik;
    private KodLot lot;
    private String noLot;
    private String noPajakan;
    private KodKategoriTanah kategoriTanahBaru;
    private KodSyaratNyata syaratNyataBaru;
    private KodSekatanKepentingan sekatanKepentinganBaru;
    private KodKadarCukai kadarCukaiBaru;
    private BigDecimal cukaiBaru;
    
    private String lokasi;
    
    private String jarak;
    
    
    private KodUOM unitJarak;
    
    private String jarakDari;

    private String jarakDariNama;

    private String nomborRujukan;
    
    /*
     * hairudin, 04/08/2010
     */
    
    private KodPemilikan kodMilik;
    
    private Date tarikhAwalDaftarGeran;
    private Integer tempohPajakan;
    
    /* 
     * syarat-syarat hakmilik
     */
    private KodHakmilik kodHakmilikSementara;
    
    private KodHakmilik kodHakmilikTetap;
    private String tempohHakmilik;
    private BigDecimal cukaiPerMeterPersegi;
    private BigDecimal cukaiPerLot;
    private BigDecimal kadarPremium;
    private BigDecimal dendaPremium;
    private KodKategoriTanah jenisPenggunaanTanah;
    
    private KodSyaratNyata syaratNyata;
    private KodSekatanKepentingan sekatanKepentingan;
    private KodPBT kodPbt;
	
    private BigDecimal nilaianJpph;
    
    private String catatan;
    
    
    private KodPerhubunganHakmilik hubunganHakmilik;
    
    /** Dokumen yg dijana utk permohonan/perserahan **/
    private Dokumen dokumen1;
    private Dokumen dokumen2;

    private Dokumen dokumen3;
    private Dokumen dokumen4;
    private Dokumen dokumen5;
    private Dokumen dokumen6;
    
//    @OneToOne (mappedBy = "hakmilik")
//    private PermohonanPembetulanHakmilik pembetulan;


    // Laporan tanah

    private BigDecimal kosInfra;

    private String tanahDiambil;

    private String keteranganInfra;


    private String keteranganCukaiBaru;

    private String keteranganKadarPremium;

    private KodKegunaanTanah kodKegunaanTanah;

    private String keteranganKadarUkur;

    private String keteranganKadarDaftar;



    private BigDecimal jarakDariKediaman;


    
    private KodUOM jarakDariKediamanUom;

    private String agensiUpahUkur;

    private String statusLuasDiluluskan;



    private String penjenisan;

    private String noUnitPetak;

    private String pegangan;

    private KodDUN kodDUN;


    private KodKawasanParlimen kodKawasanParlimen;

    private Integer tempohPengosongan;
    
    
    
            
    private String jenisHakisan;

    private KodKeputusan keputusan;
    
    private String lokaliti;
    
    private String penarikBalikan;
      
    private KodCawangan cawangan;
      
    private BigDecimal kedalamanTanah;
    
    private KodUOM kedalamanTanahUOM;  
    
    private BigDecimal kedalamanTanahDiluluskan;
    
    private KodUOM kedalamanTanahUOMDiLuluskan;  
      
    
    private BigDecimal fiPengeluaran;
    
    private BigDecimal fiPegangan;
    
    private BigDecimal jumlahPegangan;
    
    private String bahanKeluar;
    
    private KodUOM kodUnitLuasAlternatif;
    
    private BigDecimal luasAlternatif;
    
    private KodUOM kodUnitLuasBaru;  
    
    private BigDecimal luasBaru;   
    
    private Character aktif;
     
    private InfoAudit infoAudit;



    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public BigDecimal getLuasTerlibat() {
        return luasTerlibat;
    }

    public void setLuasTerlibat(BigDecimal luasTerlibat) {
        this.luasTerlibat = luasTerlibat;
    }

    public KodUOM getKodUnitLuas() {
        return kodUnitLuas;
    }

    public void setKodUnitLuas(KodUOM kodUnitLuas) {
        this.kodUnitLuas = kodUnitLuas;
    }

   

    public void setBandarPekanMukimBaru(KodBandarPekanMukim bandarPekanMukimBaru) {
		this.bandarPekanMukimBaru = bandarPekanMukimBaru;
	}

	public KodBandarPekanMukim getBandarPekanMukimBaru() {
		return bandarPekanMukimBaru;
	}

	public KodHakmilik getKodHakmilik() {
        return kodHakmilik;
    }

    public void setKodHakmilik(KodHakmilik kodHakmilik) {
        this.kodHakmilik = kodHakmilik;
    }

    public KodLot getLot() {
        return lot;
    }

    public void setLot(KodLot lot) {
        this.lot = lot;
    }

    public String getNoLot() {
        return noLot;
    }

    public void setNoLot(String noLot) {
        this.noLot = noLot;
    }

    public void setKategoriTanahBaru(KodKategoriTanah kategoriTanahBaru) {
        this.kategoriTanahBaru = kategoriTanahBaru;
    }

    public KodKategoriTanah getKategoriTanahBaru() {
        return kategoriTanahBaru;
    }

    public void setSyaratNyataBaru(KodSyaratNyata syaratNyataBaru) {
        this.syaratNyataBaru = syaratNyataBaru;
    }

    public KodSyaratNyata getSyaratNyataBaru() {
        return syaratNyataBaru;
    }

    public void setSekatanKepentinganBaru(KodSekatanKepentingan sekatanKepentinganBaru) {
        this.sekatanKepentinganBaru = sekatanKepentinganBaru;
    }

    public KodSekatanKepentingan getSekatanKepentinganBaru() {
        return sekatanKepentinganBaru;
    }
	
	public BigDecimal getNilaianJpph() {
        return nilaianJpph;
    }

    public void setNilaianJpph(BigDecimal nilaianJpph) {
        this.nilaianJpph = nilaianJpph;
    }

    public void setKadarCukaiBaru(KodKadarCukai kadarCukaiBaru) {
        this.kadarCukaiBaru = kadarCukaiBaru;
    }

    public KodKadarCukai getKadarCukaiBaru() {
        return kadarCukaiBaru;
    }

    public void setCukaiBaru(BigDecimal cukaiBaru) {
        this.cukaiBaru = cukaiBaru;
    }

    public BigDecimal getCukaiBaru() {
        return cukaiBaru;
    }

    public void setLokasi(String lokasi) {
		this.lokasi = lokasi;
	}

	public String getLokasi() {
		return lokasi;
	}

	public String getJarak() {
		return jarak;
	}

	public void setJarak(String jarak) {
		this.jarak = jarak;
	}

	public KodUOM getUnitJarak() {
		return unitJarak;
	}

	public void setUnitJarak(KodUOM unitJarak) {
		this.unitJarak = unitJarak;
	}

	public String getJarakDari() {
		return jarakDari;
	}

	public void setJarakDari(String jarakDari) {
		this.jarakDari = jarakDari;
	}

    public String getJarakDariNama() {
        return jarakDariNama;
    }

    public void setJarakDariNama(String jarakDariNama) {
        this.jarakDariNama = jarakDariNama;
    }

        



    public String getNomborRujukan() {
        return nomborRujukan;
    }

    public void setNomborRujukan(String nomborRujukan) {
        this.nomborRujukan = nomborRujukan;
    }

	public void setCatatan(String catatan) {
		this.catatan = catatan;
	}

	public String getCatatan() {
		return catatan;
	}

	public void setHubunganHakmilik(KodPerhubunganHakmilik hubunganHakmilik) {
		this.hubunganHakmilik = hubunganHakmilik;
	}

	public KodPerhubunganHakmilik getHubunganHakmilik() {
		return hubunganHakmilik;
	}

	public void setDokumen1(Dokumen dokumen) {
        this.dokumen1 = dokumen;
    }

    public Dokumen getDokumen1() {
        return dokumen1;
    }

    public void setDokumen2(Dokumen dokumen) {
        this.dokumen2 = dokumen;
    }

    public Dokumen getDokumen2() {
        return dokumen2;
    }

    public void setDokumen3(Dokumen dokumen) {
        this.dokumen3 = dokumen;
    }

    public Dokumen getDokumen3() {
        return dokumen3;
    }

    public Dokumen getDokumen4() {
        return dokumen4;
    }

    public void setDokumen4(Dokumen dokumen4) {
        this.dokumen4 = dokumen4;
    }

    public Dokumen getDokumen5() {
        return dokumen5;
    }

    public void setDokumen5(Dokumen dokumen5) {
        this.dokumen5 = dokumen5;
    }

    public Dokumen getDokumen6() {
        return dokumen6;
    }

    public void setDokumen6(Dokumen dokumen6) {
        this.dokumen6 = dokumen6;
    }

   
    public BigDecimal getKosInfra() {
        return kosInfra;
    }

    public void setKosInfra(BigDecimal kosInfra) {
        this.kosInfra = kosInfra;
    }

    public String getTanahDiambil() {
        return tanahDiambil;
    }

    public void setTanahDiambil(String tanahDiambil) {
        this.tanahDiambil = tanahDiambil;
    }

    public String getKeteranganInfra() {
        return keteranganInfra;
    }

    public void setKeteranganInfra(String keteranganInfra) {
        this.keteranganInfra = keteranganInfra;
    }

    public String getKeteranganCukaiBaru() {
        return keteranganCukaiBaru;
    }

    public void setKeteranganCukaiBaru(String keteranganCukaiBaru) {
        this.keteranganCukaiBaru = keteranganCukaiBaru;
    }

    public String getKeteranganKadarPremium() {
        return keteranganKadarPremium;
    }

    public void setKeteranganKadarPremium(String keteranganKadarPremium) {
        this.keteranganKadarPremium = keteranganKadarPremium;
    }

    public KodKegunaanTanah getKodKegunaanTanah() {
        return kodKegunaanTanah;
    }

    public void setKodKegunaanTanah(KodKegunaanTanah kodKegunaanTanah) {
        this.kodKegunaanTanah = kodKegunaanTanah;
    }

    public String getKeteranganKadarDaftar() {
        return keteranganKadarDaftar;
    }

    public void setKeteranganKadarDaftar(String keteranganKadarDaftar) {
        this.keteranganKadarDaftar = keteranganKadarDaftar;
    }

    public BigDecimal getJarakDariKediaman() {
        return jarakDariKediaman;
    }

    public void setJarakDariKediaman(BigDecimal jarakDariKediaman) {
        this.jarakDariKediaman = jarakDariKediaman;
    }

    public KodUOM getJarakDariKediamanUom() {
        return jarakDariKediamanUom;
    }

    public void setJarakDariKediamanUom(KodUOM jarakDariKediamanUom) {
        this.jarakDariKediamanUom = jarakDariKediamanUom;
    }






    public String getKeteranganKadarUkur() {
        return keteranganKadarUkur;
    }

    public void setKeteranganKadarUkur(String keteranganKadarUkur) {
        this.keteranganKadarUkur = keteranganKadarUkur;
    }


//    public void setPembetulan(PermohonanPembetulanHakmilik pembetulan) {
//		this.pembetulan = pembetulan;
//	}
//
//	public PermohonanPembetulanHakmilik getPembetulan() {
//		return pembetulan;
//	}

    

	public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    

	public void setKodMilik(KodPemilikan kodMilik) {
		this.kodMilik = kodMilik;
	}

	public KodPemilikan getKodMilik() {
		return kodMilik;
	}

	public void setTarikhAwalDaftarGeran(Date tarikhAwalDaftarGeran) {
		this.tarikhAwalDaftarGeran = tarikhAwalDaftarGeran;
	}

	public Date getTarikhAwalDaftarGeran() {
		return tarikhAwalDaftarGeran;
	}

	public void setKodHakmilikSementara(KodHakmilik kodHakmilikSementara) {
		this.kodHakmilikSementara = kodHakmilikSementara;
	}

	public KodHakmilik getKodHakmilikSementara() {
		return kodHakmilikSementara;
	}

	public void setKodHakmilikTetap(KodHakmilik kodHakmilikTetap) {
		this.kodHakmilikTetap = kodHakmilikTetap;
	}

	public KodHakmilik getKodHakmilikTetap() {
		return kodHakmilikTetap;
	}

	public void setCukaiPerMeterPersegi(BigDecimal cukaiPerMeterPersegi) {
		this.cukaiPerMeterPersegi = cukaiPerMeterPersegi;
	}

	public BigDecimal getCukaiPerMeterPersegi() {
		return cukaiPerMeterPersegi;
	}

	public void setCukaiPerLot(BigDecimal cukaiPerLot) {
		this.cukaiPerLot = cukaiPerLot;
	}

	public BigDecimal getCukaiPerLot() {
		return cukaiPerLot;
	}

	public void setKadarPremium(BigDecimal kadarPremium) {
		this.kadarPremium = kadarPremium;
	}

	public BigDecimal getKadarPremium() {
		return kadarPremium;
	}

	public void setDendaPremium(BigDecimal dendaPremium) {
		this.dendaPremium = dendaPremium;
	}

	public BigDecimal getDendaPremium() {
		return dendaPremium;
	}

	public void setSyaratNyata(KodSyaratNyata syaratNyata) {
		this.syaratNyata = syaratNyata;
	}

	public KodSyaratNyata getSyaratNyata() {
		return syaratNyata;
	}

	public void setSekatanKepentingan(KodSekatanKepentingan sekatanKepentingan) {
		this.sekatanKepentingan = sekatanKepentingan;
	}

	public KodSekatanKepentingan getSekatanKepentingan() {
		return sekatanKepentingan;
	}

	public void setTempohPajakan(Integer tempohPajakan) {
		this.tempohPajakan = tempohPajakan;
	}

	public Integer getTempohPajakan() {
		return tempohPajakan;
	}

	public void setTempohHakmilik(String tempohHakmilik) {
		this.tempohHakmilik = tempohHakmilik;
	}

	public String getTempohHakmilik() {
		return tempohHakmilik;
	}

	public void setNoPajakan(String noPajakan) {
		this.noPajakan = noPajakan;
	}

	public String getNoPajakan() {
		return noPajakan;
	}

	public void setJenisPenggunaanTanah(KodKategoriTanah jenisPenggunaanTanah) {
		this.jenisPenggunaanTanah = jenisPenggunaanTanah;
	}

	public KodKategoriTanah getJenisPenggunaanTanah() {
		return jenisPenggunaanTanah;
	}

    public KodSeksyen getKodSeksyen() {
        return kodSeksyen;
    }

    public void setKodSeksyen(KodSeksyen kodSeksyen) {
        this.kodSeksyen = kodSeksyen;
    }

    public BigDecimal getLuasPelanB1() {
        return luasPelanB1;
    }

    public void setLuasPelanB1(BigDecimal luasPelanB1) {
        this.luasPelanB1 = luasPelanB1;
    }

    public KodUOM getLuasPelanB1Uom() {
        return luasPelanB1Uom;
    }

    public void setLuasPelanB1Uom(KodUOM luasPelanB1Uom) {
        this.luasPelanB1Uom = luasPelanB1Uom;
    }

    public BigDecimal getLuasDiluluskan() {
        return luasDiluluskan;
    }

    public void setLuasDiluluskan(BigDecimal luasDiluluskan) {
        this.luasDiluluskan = luasDiluluskan;
    }

    public KodUOM getLuasLulusUom() {
        return luasLulusUom;
    }

    public void setLuasLulusUom(KodUOM luasLulusUom) {
        this.luasLulusUom = luasLulusUom;
    }

    public BigDecimal getLuasUkurHalus() {
        return luasUkurHalus;
    }

    public void setLuasUkurHalus(BigDecimal luasUkurHalus) {
        this.luasUkurHalus = luasUkurHalus;
    }

    public KodUOM getLuasUkurHalusUom() {
        return luasUkurHalusUom;
    }

    public void setLuasUkurHalusUom(KodUOM luasUkurHalusUom) {
        this.luasUkurHalusUom = luasUkurHalusUom;
    }

    public String getAgensiUpahUkur() {
        return agensiUpahUkur;
    }

    public void setAgensiUpahUkur(String agensiUpahUkur) {
        this.agensiUpahUkur = agensiUpahUkur;
    }

    public KodDUN getKodDUN() {
        return kodDUN;
    }

    public void setKodDUN(KodDUN kodDUN) {
        this.kodDUN = kodDUN;
    }

    public KodKawasanParlimen getKodKawasanParlimen() {
        return kodKawasanParlimen;
    }

    public void setKodKawasanParlimen(KodKawasanParlimen kodKawasanParlimen) {
        this.kodKawasanParlimen = kodKawasanParlimen;
    }

    public Integer getTempohPengosongan() {
        return tempohPengosongan;
    }

    public void setTempohPengosongan(Integer tempohPengosongan) {
        this.tempohPengosongan = tempohPengosongan;
    }

    public String getPermit() {
        return permit;
    }

    public void setPermit(String permit) {
        this.permit = permit;
    }

    public String getStatusLuasDiluluskan() {
        return statusLuasDiluluskan;
    }

    public void setStatusLuasDiluluskan(String statusLuasDiluluskan) {
        this.statusLuasDiluluskan = statusLuasDiluluskan;
    }

    public String getPenjenisan() {
        return penjenisan;
    }

    public void setPenjenisan(String penjenisan) {
        this.penjenisan = penjenisan;
    }

    public String getNoUnitPetak() {
        return noUnitPetak;
    }

    public void setNoUnitPetak(String noUnitPetak) {
        this.noUnitPetak = noUnitPetak;
    }

    public String getPegangan() {
        return pegangan;
    }

    public void setPegangan(String pegangan) {
        this.pegangan = pegangan;
    }

   

    public String getJenisHakisan() {
        return jenisHakisan;
    }

    public void setJenisHakisan(String jenisHakisan) {
        this.jenisHakisan = jenisHakisan;
    }

    public KodPBT getKodPbt() {
        return kodPbt;
    }

    public void setKodPbt(KodPBT kodPbt) {
        this.kodPbt = kodPbt;
    }

    public KodKeputusan getKeputusan() {
        return keputusan;
    }

    public void setKeputusan(KodKeputusan keputusan) {
        this.keputusan = keputusan;
    }

    public String getLokaliti() {
        return lokaliti;
    }

    public void setLokaliti(String lokaliti) {
        this.lokaliti = lokaliti;
    }

    public String getPenarikBalikan() {
        return penarikBalikan;
    }

    public void setPenarikBalikan(String penarikBalikan) {
        this.penarikBalikan = penarikBalikan;
    }

    public KodCawangan getCawangan() {
        return cawangan;
    }

    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
    }

    public BigDecimal getKedalamanTanah() {
        return kedalamanTanah;
    }

    public void setKedalamanTanah(BigDecimal kedalamanTanah) {
        this.kedalamanTanah = kedalamanTanah;
    }

    public KodUOM getKedalamanTanahUOM() {
        return kedalamanTanahUOM;
    }

    public void setKedalamanTanahUOM(KodUOM kedalamanTanahUOM) {
        this.kedalamanTanahUOM = kedalamanTanahUOM;
    }

    public BigDecimal getKedalamanTanahDiluluskan() {
        return kedalamanTanahDiluluskan;
    }

    public void setKedalamanTanahDiluluskan(BigDecimal kedalamanTanahDiluluskan) {
        this.kedalamanTanahDiluluskan = kedalamanTanahDiluluskan;
    }

    public KodUOM getKedalamanTanahUOMDiLuluskan() {
        return kedalamanTanahUOMDiLuluskan;
    }

    public void setKedalamanTanahUOMDiLuluskan(KodUOM kedalamanTanahUOMDiLuluskan) {
        this.kedalamanTanahUOMDiLuluskan = kedalamanTanahUOMDiLuluskan;
    }

    public BigDecimal getFiPengeluaran() {
        return fiPengeluaran;
    }

    public void setFiPengeluaran(BigDecimal fiPengeluaran) {
        this.fiPengeluaran = fiPengeluaran;
    }

    public BigDecimal getFiPegangan() {
        return fiPegangan;
    }

    public void setFiPegangan(BigDecimal fiPegangan) {
        this.fiPegangan = fiPegangan;
    }

    public BigDecimal getJumlahPegangan() {
        return jumlahPegangan;
    }

    public void setJumlahPegangan(BigDecimal jumlahPegangan) {
        this.jumlahPegangan = jumlahPegangan;
    }

    public String getBahanKeluar() {
        return bahanKeluar;
    }

    public void setBahanKeluar(String bahanKeluar) {
        this.bahanKeluar = bahanKeluar;
    }

    public KodUOM getKodUnitLuasAlternatif() {
        return kodUnitLuasAlternatif;
    }

    public void setKodUnitLuasAlternatif(KodUOM kodUnitLuasAlternatif) {
        this.kodUnitLuasAlternatif = kodUnitLuasAlternatif;
    }

    public BigDecimal getLuasAlternatif() {
        return luasAlternatif;
    }

    public void setLuasAlternatif(BigDecimal luasAlternatif) {
        this.luasAlternatif = luasAlternatif;
    }

    public KodUOM getKodUnitLuasBaru() {
        return kodUnitLuasBaru;
    }

    public void setKodUnitLuasBaru(KodUOM kodUnitLuasBaru) {
        this.kodUnitLuasBaru = kodUnitLuasBaru;
    }

    public BigDecimal getLuasBaru() {
        return luasBaru;
    }

    public void setLuasBaru(BigDecimal luasBaru) {
        this.luasBaru = luasBaru;
    }
    
    public Character getAktif() {
        return aktif;
    }

    public void setAktif(Character aktif) {
        this.aktif = aktif;
    }
}

