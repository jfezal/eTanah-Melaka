/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.model.ambil;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author mohd.faidzal
 */
@Entity
@Table(name = "mohon_ambil_etapp_hakmilik")
@SequenceGenerator(name = "seq_ambil_bicara_hadir", sequenceName = "seq_ambil_bicara_hadir")
public class PermohonanEtappHakmilik implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_ambil_bicara_hadir")
    @Column(name = "id")
    private Long id;
    @Column(name = "id_mohon")
    private String idPermohonan;
    @Column(name = "id_hakmilik")
    private String idHakmilik;
     @Column(name = "id_perserahan")
    private String idPerserahan;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public String getIdPerserahan() {
        return idPerserahan;
    }

    public void setIdPerserahan(String idPerserahan) {
        this.idPerserahan = idPerserahan;
    }
    
    

}
