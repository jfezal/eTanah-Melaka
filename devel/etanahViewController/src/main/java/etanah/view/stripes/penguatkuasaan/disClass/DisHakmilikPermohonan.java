package etanah.view.stripes.penguatkuasaan.disClass;

import etanah.view.stripes.pelupusan.disClass.*;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.ImejLaporan;
import etanah.model.InfoAudit;
import etanah.model.KodBandarPekanMukim;
import etanah.model.KodHakmilik;
import etanah.model.KodKategoriTanah;
import etanah.model.KodKegunaanTanah;
import etanah.model.KodLot;
import etanah.model.KodSekatanKepentingan;
import etanah.model.KodSeksyen;
import etanah.model.KodSyaratNyata;
import etanah.model.KodUOM;
import etanah.model.LaporanTanahSempadan;
import etanah.model.Permohonan;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Shazwan 18112011 0207 AM
 *
 *
 */
public class DisHakmilikPermohonan {

    private HakmilikPermohonan hakmilikPermohonan;
    private String cukaiBaru;
    private String tandaBlok;
    private String jarakDariNama;
    private String keluasanUOM;
    private String kodBpm = new String();
    private String katTanahPilihan;
    private List<KodSeksyen> kodSeksyenList; 
    private String noPT = new String();
    /*
     * String[] data = to cater Permohonan with null hakmilik
     * data[0]->KodBandarPekanMukim
     * data[1]->KodSeksyen
     * data[2]->KodHakmilik
     * data[3]->KodLot
     * data[4]->NoLot
     * data[5]->Lokasi
     * data[6]->KodKategoriTanah
     * data[7]->KodSyaratNyata
     * data[8]->KodKegunaanTanah
     * data[9]->KodSekatanKepentingan
     * data[10]->pegangan
     * data[11]->tempohPajakan/tempohpegangan
     * data[12]->luas terlibat
     * data[13]->luas UOM
     * data[14]->Cukai Sebenar
     */
    public HakmilikPermohonan copyPropertiesHakmilikToMH(Permohonan mohon, HakmilikPermohonan mohonHakmilikNew, Hakmilik hakmilik, String[] data, DisLaporanTanahService disLaporanTanahService) {

        if (data.length > 0) {
            //IF DATA IS NOT EMPTY, MEANING THAT HAKMILIK IS NULL (TO CATER URUSAN THAT GOT NULL HAKMILIK)
            //ELSE GET HAKMILIK
            mohonHakmilikNew.setBandarPekanMukimBaru(!StringUtils.isEmpty(data[0]) ? (KodBandarPekanMukim) disLaporanTanahService.findObject(new KodBandarPekanMukim(), new String[]{data[0]}, 0) : null);
            mohonHakmilikNew.setKodSeksyen(!StringUtils.isEmpty(data[1]) ? (KodSeksyen) disLaporanTanahService.findObject(new KodSeksyen(), new String[]{data[1]}, 0) : null);
            mohonHakmilikNew.setKodHakmilik(!StringUtils.isEmpty(data[2]) ? (KodHakmilik) disLaporanTanahService.findObject(new KodHakmilik(), new String[]{data[2]}, 0) : null);
            mohonHakmilikNew.setLot(!StringUtils.isEmpty(data[3]) ? (KodLot) disLaporanTanahService.findObject(new KodLot(), new String[]{data[3]}, 0) : null);
            mohonHakmilikNew.setNoLot(!StringUtils.isEmpty(data[4]) ? data[4] : new String());
            mohonHakmilikNew.setLokasi(!StringUtils.isEmpty(data[5]) ? data[5] : new String());
            mohonHakmilikNew.setKategoriTanahBaru(!StringUtils.isEmpty(data[6]) ? (KodKategoriTanah) disLaporanTanahService.findObject(new KodKategoriTanah(), new String[]{data[6]}, 0) : null);
            mohonHakmilikNew.setSyaratNyata(!StringUtils.isEmpty(data[7]) ? (KodSyaratNyata) disLaporanTanahService.findObject(new KodSyaratNyata(), new String[]{data[7]}, 0) : null);
            mohonHakmilikNew.setKodKegunaanTanah(!StringUtils.isEmpty(data[8]) ? (KodKegunaanTanah) disLaporanTanahService.findObject(new KodKegunaanTanah(), new String[]{data[8]}, 0) : null);
            mohonHakmilikNew.setSekatanKepentingan(!StringUtils.isEmpty(data[9]) ? (KodSekatanKepentingan) disLaporanTanahService.findObject(new KodSekatanKepentingan(), new String[]{data[9]}, 0) : null);
            mohonHakmilikNew.setPegangan(!StringUtils.isEmpty(data[10]) ? data[10] : new String());
            mohonHakmilikNew.setTempohPajakan(!StringUtils.isEmpty(data[11]) ? Integer.parseInt(data[11]) : 0);
            mohonHakmilikNew.setLuasTerlibat(!StringUtils.isEmpty(data[12]) ? new BigDecimal(Float.valueOf(data[12])) : new BigDecimal(0));
            mohonHakmilikNew.setKodUnitLuas(!StringUtils.isEmpty(data[13]) ? (KodUOM) disLaporanTanahService.findObject(new KodUOM(), new String[]{data[13]}, 0) : null);
            mohonHakmilikNew.setCukaiBaru(!StringUtils.isEmpty(data[14]) ? new BigDecimal(Float.valueOf(data[14])) : new BigDecimal(0));

        } else {
            mohonHakmilikNew.setBandarPekanMukimBaru(hakmilik.getBandarPekanMukim());
            mohonHakmilikNew.setKodSeksyen(hakmilik.getSeksyen());
            if (mohon.getKodUrusan().getKod().equals("PBMT")) {
                mohonHakmilikNew.setKodHakmilik(mohonHakmilikNew.getKodHakmilikSementara() != null ? mohonHakmilikNew.getKodHakmilikSementara() : mohonHakmilikNew.getKodHakmilikTetap());
            } else {
                mohonHakmilikNew.setKodHakmilik(hakmilik.getKodHakmilik());
            }
            if (mohon.getKodUrusan().getKod().equals("PBMT")) {
                mohonHakmilikNew.setLot(mohonHakmilikNew.getLot());
                mohonHakmilikNew.setNoLot(mohonHakmilikNew.getNoLot());
            } else {
                mohonHakmilikNew.setLot(hakmilik.getLot());
                mohonHakmilikNew.setNoLot(hakmilik.getNoLot());
            }
            mohonHakmilikNew.setLokasi(hakmilik.getLokasi());
            mohonHakmilikNew.setKategoriTanahBaru(hakmilik.getKategoriTanah());
            mohonHakmilikNew.setSyaratNyata(hakmilik.getSyaratNyata());
            mohonHakmilikNew.setKodKegunaanTanah(hakmilik.getKegunaanTanah());
            mohonHakmilikNew.setSekatanKepentingan(hakmilik.getSekatanKepentingan());
            mohonHakmilikNew.setLuasTerlibat(hakmilik.getLuas());
            mohonHakmilikNew.setKodUnitLuas(hakmilik.getKodUnitLuas());

            if (mohon.getKodUrusan().getKod().equals("MMMCL")) {
                Double convert = 0.00;
                convert = Double.parseDouble(hakmilik.getCukaiSebenar().toString()) / 2;
                mohonHakmilikNew.setPegangan("S");
                mohonHakmilikNew.setTempohPajakan(null);
                mohonHakmilikNew.setCukaiBaru(BigDecimal.valueOf(convert));
            } else {
                mohonHakmilikNew.setCukaiBaru(hakmilik.getCukaiSebenar());
                mohonHakmilikNew.setPegangan(hakmilik.getPegangan() != null ? String.valueOf(hakmilik.getPegangan()) : new String());
                mohonHakmilikNew.setTempohPajakan(hakmilik.getTempohPegangan());
            }

        }
        return mohonHakmilikNew;
    }

    public Hakmilik copyPropertiesMohonHMToHakmilik(Permohonan mohon, HakmilikPermohonan mohonHakmilik, Hakmilik hakmilikNew, String[] data, DisLaporanTanahService disLaporanTanahService) {

        if (data.length > 0) {
            //IF DATA IS NOT EMPTY, MEANING THAT MOHONHAKMILIK IS NULL
            //ELSE GET HAKMILIKPERMOHONAN
            hakmilikNew.setBandarPekanMukim(!StringUtils.isEmpty(data[0]) ? (KodBandarPekanMukim) disLaporanTanahService.findObject(new KodBandarPekanMukim(), new String[]{data[0]}, 0) : null);
            hakmilikNew.setSeksyen(!StringUtils.isEmpty(data[1]) ? (KodSeksyen) disLaporanTanahService.findObject(new KodSeksyen(), new String[]{data[1]}, 0) : null);
            hakmilikNew.setKodHakmilik(!StringUtils.isEmpty(data[2]) ? (KodHakmilik) disLaporanTanahService.findObject(new KodHakmilik(), new String[]{data[2]}, 0) : null);
            hakmilikNew.setLot(!StringUtils.isEmpty(data[3]) ? (KodLot) disLaporanTanahService.findObject(new KodLot(), new String[]{data[3]}, 0) : null);
            hakmilikNew.setNoLot(!StringUtils.isEmpty(data[4]) ? data[4] : new String());
            hakmilikNew.setLokasi(!StringUtils.isEmpty(data[5]) ? data[5] : new String());
            hakmilikNew.setKategoriTanah(!StringUtils.isEmpty(data[6]) ? (KodKategoriTanah) disLaporanTanahService.findObject(new KodKategoriTanah(), new String[]{data[6]}, 0) : null);
            hakmilikNew.setSyaratNyata(!StringUtils.isEmpty(data[7]) ? (KodSyaratNyata) disLaporanTanahService.findObject(new KodSyaratNyata(), new String[]{data[7]}, 0) : null);
            hakmilikNew.setKegunaanTanah(!StringUtils.isEmpty(data[8]) ? (KodKegunaanTanah) disLaporanTanahService.findObject(new KodKegunaanTanah(), new String[]{data[8]}, 0) : null);
            hakmilikNew.setSekatanKepentingan(!StringUtils.isEmpty(data[9]) ? (KodSekatanKepentingan) disLaporanTanahService.findObject(new KodSekatanKepentingan(), new String[]{data[9]}, 0) : null);
            hakmilikNew.setPegangan(!StringUtils.isEmpty(data[10]) ? data[10].charAt(0) : null);
            hakmilikNew.setTempohPegangan(!StringUtils.isEmpty(data[11]) ? Integer.parseInt(data[11]) : 0);
            hakmilikNew.setLuas(!StringUtils.isEmpty(data[12]) ? new BigDecimal(Float.valueOf(data[12])) : new BigDecimal(0));
            hakmilikNew.setKodUnitLuas(!StringUtils.isEmpty(data[13]) ? (KodUOM) disLaporanTanahService.findObject(new KodUOM(), new String[]{data[13]}, 0) : null);
            hakmilikNew.setCukai(!StringUtils.isEmpty(data[14]) ? new BigDecimal(Float.valueOf(data[14])) : new BigDecimal(0));

        } else {
            hakmilikNew.setBandarPekanMukim(mohonHakmilik.getBandarPekanMukimBaru());
            hakmilikNew.setSeksyen(mohonHakmilik.getKodSeksyen());
            hakmilikNew.setKodHakmilik(mohonHakmilik.getKodHakmilik());
            hakmilikNew.setLot(mohonHakmilik.getLot());
            hakmilikNew.setNoLot(mohonHakmilik.getNoLot());
            hakmilikNew.setLokasi(mohonHakmilik.getLokasi());
            hakmilikNew.setKategoriTanah(mohonHakmilik.getKategoriTanahBaru());
            hakmilikNew.setSyaratNyata(mohonHakmilik.getSyaratNyata());
            hakmilikNew.setKegunaanTanah(mohonHakmilik.getKodKegunaanTanah());
            hakmilikNew.setSekatanKepentingan(mohonHakmilik.getSekatanKepentingan());
            hakmilikNew.setPegangan(!StringUtils.isEmpty(mohonHakmilik.getPegangan()) ? mohonHakmilik.getPegangan().charAt(0) : null);
            hakmilikNew.setTempohPegangan(mohonHakmilik.getTempohPajakan());
            hakmilikNew.setLuas(mohonHakmilik.getLuasTerlibat());
            hakmilikNew.setKodUnitLuas(mohonHakmilik.getKodUnitLuas());
            hakmilikNew.setCukai(mohonHakmilik.getCukaiBaru());

        }
        return hakmilikNew;
    }

    public Hakmilik copyPropertiesHakmilikToHakmilik(Permohonan mohon, Hakmilik hakmilikOld, Hakmilik hakmilikNew, String[] data, DisLaporanTanahService disLaporanTanahService) {

        if (data.length > 0) {
            //IF DATA IS NOT EMPTY, MEANING THAT MOHONHAKMILIK IS NULL
            //ELSE GET HAKMILIKPERMOHONAN
            hakmilikNew.setBandarPekanMukim(!StringUtils.isEmpty(data[0]) ? (KodBandarPekanMukim) disLaporanTanahService.findObject(new KodBandarPekanMukim(), new String[]{data[0]}, 0) : null);
            hakmilikNew.setSeksyen(!StringUtils.isEmpty(data[1]) ? (KodSeksyen) disLaporanTanahService.findObject(new KodSeksyen(), new String[]{data[1]}, 0) : null);
            hakmilikNew.setKodHakmilik(!StringUtils.isEmpty(data[2]) ? (KodHakmilik) disLaporanTanahService.findObject(new KodHakmilik(), new String[]{data[2]}, 0) : null);
            hakmilikNew.setLot(!StringUtils.isEmpty(data[3]) ? (KodLot) disLaporanTanahService.findObject(new KodLot(), new String[]{data[3]}, 0) : null);
            hakmilikNew.setNoLot(!StringUtils.isEmpty(data[4]) ? data[4] : new String());
            hakmilikNew.setLokasi(!StringUtils.isEmpty(data[5]) ? data[5] : new String());
            hakmilikNew.setKategoriTanah(!StringUtils.isEmpty(data[6]) ? (KodKategoriTanah) disLaporanTanahService.findObject(new KodKategoriTanah(), new String[]{data[6]}, 0) : null);
            hakmilikNew.setSyaratNyata(!StringUtils.isEmpty(data[7]) ? (KodSyaratNyata) disLaporanTanahService.findObject(new KodSyaratNyata(), new String[]{data[7]}, 0) : null);
            hakmilikNew.setKegunaanTanah(!StringUtils.isEmpty(data[8]) ? (KodKegunaanTanah) disLaporanTanahService.findObject(new KodKegunaanTanah(), new String[]{data[8]}, 0) : null);
            hakmilikNew.setSekatanKepentingan(!StringUtils.isEmpty(data[9]) ? (KodSekatanKepentingan) disLaporanTanahService.findObject(new KodSekatanKepentingan(), new String[]{data[9]}, 0) : null);
            hakmilikNew.setPegangan(!StringUtils.isEmpty(data[10]) ? data[10].charAt(0) : null);
            hakmilikNew.setTempohPegangan(!StringUtils.isEmpty(data[11]) ? Integer.parseInt(data[11]) : 0);
            hakmilikNew.setLuas(!StringUtils.isEmpty(data[12]) ? new BigDecimal(Float.valueOf(data[12])) : new BigDecimal(0));
            hakmilikNew.setKodUnitLuas(!StringUtils.isEmpty(data[13]) ? (KodUOM) disLaporanTanahService.findObject(new KodUOM(), new String[]{data[13]}, 0) : null);
            hakmilikNew.setCukai(!StringUtils.isEmpty(data[14]) ? new BigDecimal(Float.valueOf(data[14])) : new BigDecimal(0));

        } else {
            hakmilikNew.setBandarPekanMukim(hakmilikOld.getBandarPekanMukim());
            hakmilikNew.setSeksyen(hakmilikOld.getSeksyen());
            hakmilikNew.setKodHakmilik(hakmilikOld.getKodHakmilik());
            hakmilikNew.setLot(hakmilikOld.getLot());
            hakmilikNew.setNoLot(hakmilikOld.getNoLot());
            hakmilikNew.setLokasi(hakmilikOld.getLokasi());
            hakmilikNew.setKategoriTanah(hakmilikOld.getKategoriTanah());
            hakmilikNew.setSyaratNyata(hakmilikOld.getSyaratNyata());
            hakmilikNew.setKegunaanTanah(hakmilikOld.getKegunaanTanah());
            hakmilikNew.setSekatanKepentingan(hakmilikOld.getSekatanKepentingan());
            hakmilikNew.setPegangan(hakmilikOld.getPegangan());
            hakmilikNew.setTempohPegangan(hakmilikOld.getTempohPegangan());
            hakmilikNew.setLuas(hakmilikOld.getLuas());
            hakmilikNew.setKodUnitLuas(hakmilikOld.getKodUnitLuas());
            hakmilikNew.setCukai(hakmilikOld.getCukai());

        }
        return hakmilikNew;
    }

    public String getCukaiBaru() {
        return cukaiBaru;
    }

    public void setCukaiBaru(String cukaiBaru) {
        this.cukaiBaru = cukaiBaru;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public List<KodSeksyen> getKodSeksyenList() {
        return kodSeksyenList;
    }

    public void setKodSeksyenList(List<KodSeksyen> kodSeksyenList) {
        this.kodSeksyenList = kodSeksyenList;
    }

    public String getTandaBlok() {
        return tandaBlok;
    }

    public void setTandaBlok(String tandaBlok) {
        this.tandaBlok = tandaBlok;
    }

    public String getJarakDariNama() {
        return jarakDariNama;
    }

    public void setJarakDariNama(String jarakDariNama) {
        this.jarakDariNama = jarakDariNama;
    }

    public String getKeluasanUOM() {
        return keluasanUOM;
    }

    public void setKeluasanUOM(String keluasanUOM) {
        this.keluasanUOM = keluasanUOM;
    }

    public String getKodBpm() {
        return kodBpm;
    }

    public void setKodBpm(String kodBpm) {
        this.kodBpm = kodBpm;
    }

    public String getKatTanahPilihan() {
        return katTanahPilihan;
    }

    public void setKatTanahPilihan(String katTanahPilihan) {
        this.katTanahPilihan = katTanahPilihan;
    }

    public String getNoPT() {
        return noPT;
    }

    public void setNoPT(String noPT) {
        this.noPT = noPT;
    }
}
