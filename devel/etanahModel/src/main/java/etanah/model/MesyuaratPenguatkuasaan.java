/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Entity
@Table (name = "kkuasa_mesyuarat")
@SequenceGenerator(name = "seq_kkuasa_mesyuarat", sequenceName = "seq_kkuasa_mesyuarat")
public class MesyuaratPenguatkuasaan implements Serializable {
	
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_kkuasa_mesyuarat")
    @Column (name = "id_km")
    private long idMesyuarat;
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "id_mohon", nullable = false)
    private Permohonan permohonan;
    @Column (name = "no_ruj")
    private String rujukan;
    @Column (name = "trh_ruj")
    private Date tarikhRujukan;
    @Column (name = "trh_mesyuarat")
    private Date tarikh;
    @Column (name = "tpt_mesyuarat")
    private String tempat;
    @Column (name = "catatan")
    private String catatan;
    @Column (name = "kpsn_mesyuarat")
    private String keputusan;        
    @Embedded
    private InfoAudit infoAudit;

    public long getIdMesyuarat() {
        return idMesyuarat;
    }

    public void setIdMesyuarat(long idMesyuarat) {
        this.idMesyuarat = idMesyuarat;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public String getRujukan() {
        return rujukan;
    }

    public void setRujukan(String rujukan) {
        this.rujukan = rujukan;
    }

    public Date getTarikhRujukan() {
        return tarikhRujukan;
    }

    public void setTarikhRujukan(Date tarikhRujukan) {
        this.tarikhRujukan = tarikhRujukan;
    }

    public Date getTarikh() {
        return tarikh;
    }

    public void setTarikh(Date tarikh) {
        this.tarikh = tarikh;
    }

    public String getTempat() {
        return tempat;
    }

    public void setTempat(String tempat) {
        this.tempat = tempat;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public String getKeputusan() {
        return keputusan;
    }

    public void setKeputusan(String keputusan) {
        this.keputusan = keputusan;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }
  
}
