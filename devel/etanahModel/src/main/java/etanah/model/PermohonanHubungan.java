package etanah.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table (name = "mohon_hbgn")
@SequenceGenerator(name = "seq_mohon_hbgn", sequenceName = "seq_mohon_hbgn")
public class PermohonanHubungan implements Serializable {

	@Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_mohon_hbgn")
	@Column (name = "id_hbgn")
	private long idPermohonanHubungan;

	@ManyToOne
	@JoinColumn (name = "kod_caw")
	private KodCawangan cawangan;

	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "id_sumber")
	private Permohonan permohonanSumber;

        @ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "id_sasar")
	private Permohonan permohonanSasaran;
        
        @ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "id_hakmilik")
	private Hakmilik hakmilik;

        @ManyToOne
	@JoinColumn (name = "kod_mohon_hbgn")
	private KodPerhubunganPermohonan hubunganPermohonan;	

	@Column (name = "catatan", length=200)
	private String catatan;

	@Embedded
	private InfoAudit infoAudit;

        public String getCatatan() {
            return catatan;
        }

        public void setCatatan(String catatan) {
            this.catatan = catatan;
        }

        public KodCawangan getCawangan() {
            return cawangan;
        }

        public void setCawangan(KodCawangan cawangan) {
            this.cawangan = cawangan;
        }

        public KodPerhubunganPermohonan getHubunganPermohonan() {
            return hubunganPermohonan;
        }

        public void setHubunganPermohonan(KodPerhubunganPermohonan hubunganPermohonan) {
            this.hubunganPermohonan = hubunganPermohonan;
        }

        public long getIdPermohonanHubungan() {
            return idPermohonanHubungan;
        }

        public void setIdPermohonanHubungan(long idPermohonanHubungan) {
            this.idPermohonanHubungan = idPermohonanHubungan;
        }

        public Permohonan getPermohonanSasaran() {
            return permohonanSasaran;
        }

        public void setPermohonanSasar(Permohonan permohonanSasaran) {
            this.permohonanSasaran = permohonanSasaran;
        }

        public Permohonan getPermohonanSumber() {
            return permohonanSumber;
        }

        public void setPermohonanSumber(Permohonan permohonanSumber) {
            this.permohonanSumber = permohonanSumber;
        }

	public InfoAudit getInfoAudit() {
		return infoAudit;
	}

	public void setInfoAudit(InfoAudit infoAudit) {
		this.infoAudit = infoAudit;
	}

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

}
