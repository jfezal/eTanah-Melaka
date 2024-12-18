package etanah.model;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table (name = "audit_rekod_hapus")
@SequenceGenerator(name = "seq_audit_rekod_hapus", sequenceName = "seq_audit_rekod_hapus")
public class AuditRekodHapus implements Serializable {

	@Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_audit_rekod_hapus")
	@Column (name = "id_audit_rekod_hapus")
	private long idAuditRekodHapus;


        @Column (name = "nama_jadual")
	private String namaJadual;



        @Column (name = "id_rekod")
	private String idRekod;

	
	
	
	@Embedded
	private InfoAudit infoAudit;

    public long getIdAuditRekodHapus() {
        return idAuditRekodHapus;
    }

    public void setIdAuditRekodHapus(long idAuditRekodHapus) {
        this.idAuditRekodHapus = idAuditRekodHapus;
    }

    public String getIdRekod() {
        return idRekod;
    }

    public void setIdRekod(String idRekod) {
        this.idRekod = idRekod;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    public String getNamaJadual() {
        return namaJadual;
    }

    public void setNamaJadual(String namaJadual) {
        this.namaJadual = namaJadual;
    }



	

	
}
