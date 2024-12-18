/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author Suhairi
 */
@Entity
@Table(name = "aduan_siasat_bngn")
@SequenceGenerator(name = "seq_aduan_siasat_bngn", sequenceName = "seq_aduan_siasat_bngn")


public class SiasatanPerihalBangunan implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_aduan_siasat_bngn")
    @Column(name = "id_aduan_siasat_bngn")
    private Long idAduanSiasatBangunan;

    @ManyToOne
    @JoinColumn (name = "kod_caw")
    private KodCawangan kodCawangan;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "id_mohon")
    private Permohonan permohonan;

    @ManyToOne
    @JoinColumn (name = "kod_guna_petak")
    private KodKegunaanPetak kodKegunaanPetak;

     @ManyToOne
    @JoinColumn (name = "kod_jenis_pembangunan")
    private KodJenisPembangunan kodJenisPembangunan;

    @Column(name = "bil_tingkat")
    private String bilanganTingkat;

    @Column(name = "bil_blok")
    private Integer bilanganBlok;

    @Column(name = "bil_unit")
    private Integer bilanganUnit;



    @Column(name = "kongsi_tangga")
    private String kongsiTangga;

    @Column(name = "catatan")
    private String catatan;
 
    public SiasatanPerihalBangunan() {
    }


	@Embedded
	private InfoAudit infoAudit;

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }


    public KodCawangan getKodCawangan() {
        return kodCawangan;
    }

    public void setKodCawangan(KodCawangan kodCawangan) {
        this.kodCawangan = kodCawangan;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public Integer getBilanganBlok() {
        return bilanganBlok;
    }

    public void setBilanganBlok(Integer bilanganBlok) {
        this.bilanganBlok = bilanganBlok;
    }

    public String getBilanganTingkat() {
        return bilanganTingkat;
    }

    public void setBilanganTingkat(String bilanganTingkat) {
        this.bilanganTingkat = bilanganTingkat;
    }

    public Integer getBilanganUnit() {
        return bilanganUnit;
    }

    public void setBilanganUnit(Integer bilanganUnit) {
        this.bilanganUnit = bilanganUnit;
    }

    public Long getIdAduanSiasatBangunan() {
        return idAduanSiasatBangunan;
    }

    public void setIdAduanSiasatBangunan(Long idAduanSiasatBangunan) {
        this.idAduanSiasatBangunan = idAduanSiasatBangunan;
    }

    public KodKegunaanPetak getKodKegunaanPetak() {
        return kodKegunaanPetak;
    }

    public void setKodKegunaanPetak(KodKegunaanPetak kodKegunaanPetak) {
        this.kodKegunaanPetak = kodKegunaanPetak;
    }

    public String getKongsiTangga() {
        return kongsiTangga;
    }

    public void setKongsiTangga(String kongsiTangga) {
        this.kongsiTangga = kongsiTangga;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public KodJenisPembangunan getKodJenisPembangunan() {
        return kodJenisPembangunan;
    }

    public void setKodJenisPembangunan(KodJenisPembangunan kodJenisPembangunan) {
        this.kodJenisPembangunan = kodJenisPembangunan;
    }

   

   


}
