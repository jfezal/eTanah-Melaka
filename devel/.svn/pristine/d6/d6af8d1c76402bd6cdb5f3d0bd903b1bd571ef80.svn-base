package etanah.model.ambil;

import java.io.Serializable;

import javax.persistence.*;

import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import java.util.Date;

@Entity
@Table(name = "ambil_bicara_tangguh")
@SequenceGenerator(name = "seq_ambil_bicara_tangguh", sequenceName = "seq_ambil_bicara_tangguh")
public class HakmilikPerbicaraanTangguh implements Serializable {

    @Id
     @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_ambil_bicara_tangguh")
    @Column(name = "id_tangguh")
    private long idTangguh;
    @ManyToOne
    @JoinColumn(name = "kod_caw", nullable = false)
    private KodCawangan cawangan;
    @ManyToOne
    @JoinColumn(name = "id_bicara", nullable = false)
    private HakmilikPerbicaraan hakmilikPerbicaraan;
    @Column(name = "trh_bicara")
    private Date tarikhBicara;
    @Column(name = "lokasi_bicara")
    private String lokasiBicara;

    @Column(name = "catatan")
    private String catatan;
    @Embedded
    private InfoAudit infoAudit;

    public KodCawangan getCawangan() {
        return cawangan;
    }

    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
    }


    public String getLokasiBicara() {
        return lokasiBicara;
    }

    public void setLokasiBicara(String lokasiBicara) {
        this.lokasiBicara = lokasiBicara;
    }


    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public HakmilikPerbicaraan getHakmilikPerbicaraan() {
        return hakmilikPerbicaraan;
    }

    public void setHakmilikPerbicaraan(HakmilikPerbicaraan hakmilikPerbicaraan) {
        this.hakmilikPerbicaraan = hakmilikPerbicaraan;
    }

    public long getIdTangguh() {
        return idTangguh;
    }

    public void setIdTangguh(long idTangguh) {
        this.idTangguh = idTangguh;
    }

    public Date getTarikhBicara() {
        return tarikhBicara;
    }

    public void setTarikhBicara(Date tarikhBicara) {
        this.tarikhBicara = tarikhBicara;
    }




    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }
}
