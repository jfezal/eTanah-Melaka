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
@Table(name = "mohon_lapor_kws")
@SequenceGenerator(name = "seq_mohon_lapor_kws", sequenceName = "seq_mohon_lapor_kws")


public class PermohonanLaporanKawasan implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_mohon_lapor_kws")
    @Column(name = "id_mohon_lapor_kws")
    private Long idMohonlaporKws;

    @ManyToOne
    @JoinColumn (name = "kod_caw")
    private KodCawangan kodCawangan;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "id_mohon")
    private Permohonan permohonan;

    @ManyToOne
    @JoinColumn (name = "kod_rizab")
    private KodRizab kodRizab;

    @Column(name = "catatan")
    private String catatan;

	@Column (name = "no_warta")
	private String noWarta;

	@Column (name = "no_pelan_warta")
	private String noPelanWarta;

	@Column (name = "trh_warta")
	private Date tarikhWarta;

	@Column (name = "trh_terima_warta")
	private Date tarikhTerimaWarta;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "id_mh")
    private HakmilikPermohonan hakmilikPermohonan;

	@Column (name = "no_warta_dahulu")
	private String noWartaDahulu;


	@Column (name = "trh_warta_dahulu")
	private Date tarikhWartaDahulu;

    @Column(name = "aktif")
    private String aktif;

    public PermohonanLaporanKawasan() {
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

    public Long getIdMohonlaporKws() {
        return idMohonlaporKws;
    }

    public void setIdMohonlaporKws(Long idMohonlaporKws) {
        this.idMohonlaporKws = idMohonlaporKws;
    }

    public KodRizab getKodRizab() {
        return kodRizab;
    }

    public void setKodRizab(KodRizab kodRizab) {
        this.kodRizab = kodRizab;
    }

    public String getAktif() {
        return aktif;
    }

    public void setAktif(String aktif) {
        this.aktif = aktif;
    }

    public String getNoPelanWarta() {
        return noPelanWarta;
    }

    public void setNoPelanWarta(String noPelanWarta) {
        this.noPelanWarta = noPelanWarta;
    }

    public String getNoWarta() {
        return noWarta;
    }

    public void setNoWarta(String noWarta) {
        this.noWarta = noWarta;
    }

    public Date getTarikhWarta() {
        return tarikhWarta;
    }

    public void setTarikhWarta(Date tarikhWarta) {
        this.tarikhWarta = tarikhWarta;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    
    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public String getNoWartaDahulu() {
        return noWartaDahulu;
    }

    public void setNoWartaDahulu(String noWartaDahulu) {
        this.noWartaDahulu = noWartaDahulu;
    }

    public Date getTarikhTerimaWarta() {
        return tarikhTerimaWarta;
    }

    public void setTarikhTerimaWarta(Date tarikhTerimaWarta) {
        this.tarikhTerimaWarta = tarikhTerimaWarta;
    }

    public Date getTarikhWartaDahulu() {
        return tarikhWartaDahulu;
    }

    public void setTarikhWartaDahulu(Date tarikhWartaDahulu) {
        this.tarikhWartaDahulu = tarikhWartaDahulu;
    }

   


}
