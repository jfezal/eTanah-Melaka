package etanah.model.gis;

import javax.persistence.*;
import etanah.model.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table (name = "gis_cms_urusan")
public class UrusanCMS implements Serializable{

    @Id
    @Column (name = "gis_key")
    private String gisKey;

    @Column (name = "id_transaksi")
    private String idTransaksi;

    @Column (name = "no_transaksi")
    private long noTransaksi;

    @ManyToOne
    @JoinColumn (name = "kod_daerah")
    private KodDaerah daerah;

    @Column (name = "kod_mukim")
    private String kodMukim;

    @Column (name = "jenis_carian")
    private String jenisCarian;

    @Column (name = "lot_sblh")
    private String lotSebelah;

    @Column (name = "no_carian")
    private String noCarian;

    @Column (name = "jum_lot_sblh")
    private long jumlahLotSebelah;

    @Column (name = "no_resit")
    private String noResit;

    @Column (name = "saiz_kertas")
    private String saizKertas;

    @Column (name = "bilangan")
    private long bilangan;

    @Column (name = "baki")
    private long baki;

    @Column (name = "tkh_cetak")
    private Date tarikhCetak;

    @Column (name = "kod_urusan")
    private String kodUrusan;

    @Column (name = "kuantiti_a3")
    private long kuantitiA3;

    @Column (name = "kuantiti_a4")
    private long kuantitiA4;

    public long getBaki() {
        return baki;
    }

    public long getBilangan() {
        return bilangan;
    }

    public KodDaerah getDaerah() {
        return daerah;
    }

    public String getIdTransaksi() {
        return idTransaksi;
    }

    public String getJenisCarian() {
        return jenisCarian;
    }

    public long getJumlahLotSebelah() {
        return jumlahLotSebelah;
    }

    public String getKodMukim() {
        return kodMukim;
    }

    public String getKodUrusan() {
        return kodUrusan;
    }

    public String getLotSebelah() {
        return lotSebelah;
    }

    public long getNoTransaksi() {
        return noTransaksi;
    }

    public String getNoResit() {
        return noResit;
    }

    public String getSaizKertas() {
        return saizKertas;
    }

    public Date getTarikhCetak() {
        return tarikhCetak;
    }

    public void setBaki(long baki) {
        this.baki = baki;
    }

    public void setBilangan(long bilangan) {
        this.bilangan = bilangan;
    }

    public void setIdTransaksi(String idTransaksi) {
        this.idTransaksi = idTransaksi;
    }

    public void setNoResit(String noResit) {
        this.noResit = noResit;
    }

    public void setSaizKertas(String saizKertas) {
        this.saizKertas = saizKertas;
    }

    public void setTarikhCetak(Date tarikhCetak) {
        this.tarikhCetak = tarikhCetak;
    }

    public String getNoCarian() {
        return noCarian;
    }

    public void setNoCarian(String noCarian) {
        this.noCarian = noCarian;
    }

    public void setKodUrusan(String kodUrusan) {
        this.kodUrusan = kodUrusan;
    }

    public String getGisKey() {
        return gisKey;
    }

    public void setGisKey(String gisKey) {
        this.gisKey = gisKey;
    }

    public long getKuantitiA3() {
        return kuantitiA3;
    }

    public void setKuantitiA3(long kuantitiA3) {
        this.kuantitiA3 = kuantitiA3;
    }

    public long getKuantitiA4() {
        return kuantitiA4;
    }

    public void setKuantitiA4(long kuantitiA4) {
        this.kuantitiA4 = kuantitiA4;
    }
}
