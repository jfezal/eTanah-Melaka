package etanah.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
@Table (name = "dasar_cukai_hakmilik")
public class DasarTuntutanCukaiHakmilik implements Serializable {
	  
	@Id
	@Column (name = "id_dch")
	private long idDasarHakmilik;
	
	@ManyToOne
	@JoinColumn (name = "kod_caw", nullable = false)
	private KodCawangan cawangan;
	
	@ManyToOne
	@JoinColumn (name = "id_dasar", nullable = false)
	private DasarTuntutanCukai dasarTuntutanCukai;
	
	@ManyToOne
	@JoinColumn (name = "id_hakmilik", nullable = false)
	private Hakmilik hakmilik;
	
	@ManyToOne
	@JoinColumn (name = "kod_sts")
	private KodStatusTuntutanCukai status;
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "id_serah_6a", nullable = true)
	private Permohonan perserahan6A;
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "id_serah_batal_6a", nullable = true)
	private Permohonan perserahanBatal6A;
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "id_serah_8a", nullable = true)
	private Permohonan perserahan8A;
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "id_serah_batal_8a", nullable = true)
	private Permohonan perserahanBatal8A;

	@OneToMany (mappedBy = "hakmilik")
	private List<DasarTuntutanNotis> senaraiNotis;

	@Column (name = "no_ruj", length = 33)
	private String noRujukan;
        
        @Column (name = "CATAT_HAPUS1")
        private String catatanHapus1;
        
        @Column (name = "CATAT_HAPUS2")
        private String catatanHapus2;
         
        @Column (name = "CATAT_HAPUS3")
        private String catatanHapus3;
          
        @Column (name = "CATAT_HAPUS4")
        private String catatanHapus4;
           
        @Column (name = "CATAT_HAPUS5")
        private String catatanHapus5;
            
        @Column (name = "CATAT_HAPUS6")
        private String catatanHapus6;
             
        @Column (name = "CATAT_HAPUS7")
        private String catatanHapus7;
        
        @Column (name = "PEGAWAI1")
        private String pegawai1;
        
        @Column (name = "PEGAWAI2")
        private String pegawai2;
        
        @Column (name = "PEGAWAI3")
        private String pegawai3;
        
        @Column (name = "PEGAWAI4")
        private String pegawai4;
        
        @Column (name = "PEGAWAI5")
        private String pegawai5;
        
        @Column (name = "PENYELIA1")
        private String penyelia1;
        
        @Column (name = "PENYELIA2")
        private String penyelia2;
        
        @Column (name = "PENYELIA3")
        private String penyelia3;

	@Embedded
	private InfoAudit infoAudit;

	public long getIdDasarHakmilik() {
		return idDasarHakmilik;
	}

	public void setIdDasarHakmilik(long idDasarHakmilik) {
		this.idDasarHakmilik = idDasarHakmilik;
	}

	public KodCawangan getCawangan() {
		return cawangan;
	}

	public void setCawangan(KodCawangan cawangan) {
		this.cawangan = cawangan;
	}

	public DasarTuntutanCukai getDasarTuntutanCukai() {
		return dasarTuntutanCukai;
	}

	public void setDasarTuntutanCukai(DasarTuntutanCukai dasarTuntutanCukai) {
		this.dasarTuntutanCukai = dasarTuntutanCukai;
	}

	public Hakmilik getHakmilik() {
		return hakmilik;
	}

	public void setHakmilik(Hakmilik hakmilik) {
		this.hakmilik = hakmilik;
	}

	public KodStatusTuntutanCukai getStatus() {
		return status;
	}

	public void setStatus(KodStatusTuntutanCukai status) {
		this.status = status;
	}

	public Permohonan getPerserahan6A() {
		return perserahan6A;
	}

	public void setPerserahan6A(Permohonan perserahan6a) {
		perserahan6A = perserahan6a;
	}

	public void setPerserahanBatal6A(Permohonan perserahanBatal6A) {
		this.perserahanBatal6A = perserahanBatal6A;
	}

	public Permohonan getPerserahanBatal6A() {
		return perserahanBatal6A;
	}

	public Permohonan getPerserahan8A() {
		return perserahan8A;
	}

	public void setPerserahanBatal8A(Permohonan perserahanBatal8A) {
		this.perserahanBatal8A = perserahanBatal8A;
	}

	public Permohonan getPerserahanBatal8A() {
		return perserahanBatal8A;
	}

	public void setPerserahan8A(Permohonan perserahan8a) {
		perserahan8A = perserahan8a;
	}

	public void setSenaraiNotis(List<DasarTuntutanNotis> senaraiNotis) {
		this.senaraiNotis = senaraiNotis;
	}

	public List<DasarTuntutanNotis> getSenaraiNotis() {
		return senaraiNotis;
	}

	public InfoAudit getInfoAudit() {
		return infoAudit;
	}

	public void setInfoAudit(InfoAudit infoAudit) {
		this.infoAudit = infoAudit;
	}

	public String getNoRujukan() {
		return noRujukan;
	}

	public void setNoRujukan(String noRujukan) {
		this.noRujukan = noRujukan;
	}

    public String getCatatanHapus1() {
        return catatanHapus1;
    }

    public void setCatatanHapus1(String catatanHapus1) {
        this.catatanHapus1 = catatanHapus1;
    }

    public String getCatatanHapus2() {
        return catatanHapus2;
    }

    public void setCatatanHapus2(String catatanHapus2) {
        this.catatanHapus2 = catatanHapus2;
    }

    public String getCatatanHapus3() {
        return catatanHapus3;
    }

    public void setCatatanHapus3(String catatanHapus3) {
        this.catatanHapus3 = catatanHapus3;
    }

    public String getCatatanHapus4() {
        return catatanHapus4;
    }

    public void setCatatanHapus4(String catatanHapus4) {
        this.catatanHapus4 = catatanHapus4;
    }

    public String getCatatanHapus5() {
        return catatanHapus5;
    }

    public void setCatatanHapus5(String catatanHapus5) {
        this.catatanHapus5 = catatanHapus5;
    }

    public String getCatatanHapus6() {
        return catatanHapus6;
    }

    public void setCatatanHapus6(String catatanHapus6) {
        this.catatanHapus6 = catatanHapus6;
    }

    public String getCatatanHapus7() {
        return catatanHapus7;
    }

    public void setCatatanHapus7(String catatanHapus7) {
        this.catatanHapus7 = catatanHapus7;
    }

    public String getPegawai1() {
        return pegawai1;
    }

    public void setPegawai1(String pegawai1) {
        this.pegawai1 = pegawai1;
    }

    public String getPegawai2() {
        return pegawai2;
    }

    public void setPegawai2(String pegawai2) {
        this.pegawai2 = pegawai2;
    }

    public String getPegawai3() {
        return pegawai3;
    }

    public void setPegawai3(String pegawai3) {
        this.pegawai3 = pegawai3;
    }

    public String getPegawai4() {
        return pegawai4;
    }

    public void setPegawai4(String pegawai4) {
        this.pegawai4 = pegawai4;
    }

    public String getPegawai5() {
        return pegawai5;
    }

    public void setPegawai5(String pegawai5) {
        this.pegawai5 = pegawai5;
    }

    public String getPenyelia1() {
        return penyelia1;
    }

    public void setPenyelia1(String penyelia1) {
        this.penyelia1 = penyelia1;
    }

    public String getPenyelia2() {
        return penyelia2;
    }

    public void setPenyelia2(String penyelia2) {
        this.penyelia2 = penyelia2;
    }

    public String getPenyelia3() {
        return penyelia3;
    }

    public void setPenyelia3(String penyelia3) {
        this.penyelia3 = penyelia3;
    }
	
}
