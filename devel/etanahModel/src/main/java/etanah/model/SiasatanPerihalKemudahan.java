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
@Table(name = "aduan_siasat_kemudahan")
@SequenceGenerator(name = "seq_aduan_siasat_kemudahan", sequenceName = "seq_aduan_siasat_kemudahan")


public class SiasatanPerihalKemudahan implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_aduan_siasat_kemudahan")
    @Column(name = "id_aduan_siasat_kemudahan")
    private Long idAduanSiasatKemudahan;

    @ManyToOne
    @JoinColumn (name = "kod_caw")
    private KodCawangan kodCawangan;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "id_mohon")
    private Permohonan permohonan;

    @ManyToOne
    @JoinColumn (name = "kod_harta_bersama")
    private KodHartaBersama kodHartaBersama;

    @Column(name = "catatan")
    private String catatan;

    public SiasatanPerihalKemudahan() {
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

    public Long getIdAduanSiasatKemudahan() {
        return idAduanSiasatKemudahan;
    }

    public void setIdAduanSiasatKemudahan(Long idAduanSiasatKemudahan) {
        this.idAduanSiasatKemudahan = idAduanSiasatKemudahan;
    }

    public KodHartaBersama getKodHartaBersama() {
        return kodHartaBersama;
    }

    public void setKodHartaBersama(KodHartaBersama kodHartaBersama) {
        this.kodHartaBersama = kodHartaBersama;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

   


}
