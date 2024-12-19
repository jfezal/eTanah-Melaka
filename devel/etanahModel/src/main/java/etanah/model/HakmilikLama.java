package etanah.model;

import java.io.Serializable;

import java.util.Date;
import javax.persistence.*;

@Entity
@Table (name = "hakmilik_lama")
public class HakmilikLama implements Serializable {
	
	@Id
	@Column (name = "id_hakmilik")
	private String idHakmilik;	
	
	@Column (name = "versi_dhdk")
	private String versiDhdk;
        
        @Column (name = "versi_dhkk")
	private String versiDhkk;   
        
        @Column (name = "trh_j14")
	private Date tarikhJilid14;
        
        @Column (name = "pegawai_j14")
	private String pegawaiJilid14;
        
         @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "pguna_j14")
        private Pengguna penggunaJilid14;
         
         @Column (name = "trh_j16")
	private Date tarikhJilid16;
        
        @Column (name = "pegawai_j16")
	private String pegawaiJilid16;
        
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "pguna_j16")
        private Pengguna penggunaJilid16;         
	
	@Embedded
	private InfoAudit infoAudit;

        public String getIdHakmilik() {
            return idHakmilik;
        }

        public void setIdHakmilik(String idHakmilik) {
            this.idHakmilik = idHakmilik;
        }

        public String getPegawaiJilid14() {
            return pegawaiJilid14;
        }

        public void setPegawaiJilid14(String pegawaiJilid14) {
            this.pegawaiJilid14 = pegawaiJilid14;
        }

        public String getPegawaiJilid16() {
            return pegawaiJilid16;
        }

        public void setPegawaiJilid16(String pegawaiJilid16) {
            this.pegawaiJilid16 = pegawaiJilid16;
        }

        public Pengguna getPenggunaJilid14() {
            return penggunaJilid14;
        }

        public void setPenggunaJilid14(Pengguna penggunaJilid14) {
            this.penggunaJilid14 = penggunaJilid14;
        }

        public Pengguna getPenggunaJilid16() {
            return penggunaJilid16;
        }

        public void setPenggunaJilid16(Pengguna penggunaJilid16) {
            this.penggunaJilid16 = penggunaJilid16;
        }

        public Date getTarikhJilid14() {
            return tarikhJilid14;
        }

        public void setTarikhJilid14(Date tarikhJilid14) {
            this.tarikhJilid14 = tarikhJilid14;
        }

        public Date getTarikhJilid16() {
            return tarikhJilid16;
        }

        public void setTarikhJilid16(Date tarikhJilid16) {
            this.tarikhJilid16 = tarikhJilid16;
        }

        public String getVersiDhdk() {
            return versiDhdk;
        }

        public void setVersiDhdk(String versiDhdk) {
            this.versiDhdk = versiDhdk;
        }

        public String getVersiDhkk() {
            return versiDhkk;
        }

        public void setVersiDhkk(String versiDhkk) {
            this.versiDhkk = versiDhkk;
        }	

	public InfoAudit getInfoAudit() {
		return infoAudit;
	}

	public void setInfoAudit(InfoAudit infoAudit) {
		this.infoAudit = infoAudit;
	}
	

}
