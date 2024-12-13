package etanah.model;

import java.util.List;
import java.io.Serializable;

import javax.persistence.*;

@Embeddable
public class Aduan implements Serializable {
	
    @OneToMany (mappedBy = "permohonan", fetch = FetchType.LAZY)
    private List<AduanLokasi> senaraiAduanLokasi;  
	
	@OneToMany (mappedBy = "permohonan")
	private List<AduanOrangKenaSyak> senaraiOrangKenaSyak;

    @OneToMany (mappedBy = "permohonan")
    private List<AduanSiasatan> senaraiAduanSiasatan;
    
    @OneToMany (mappedBy = "permohonan")
    private List<AduanPemantauan> senaraiPemantauan;
	
	public void setSenaraiAduanLokasi(List<AduanLokasi> senaraiAduanLokasi) {
		this.senaraiAduanLokasi = senaraiAduanLokasi;
	}

	public List<AduanLokasi> getSenaraiAduanLokasi() {
		return senaraiAduanLokasi;
	}

	public List<AduanOrangKenaSyak> getSenaraiOrangKenaSyak() {
		return senaraiOrangKenaSyak;
	}

	public void setSenaraiOrangKenaSyak(
			List<AduanOrangKenaSyak> senaraiOrangKenaSyak) {
		this.senaraiOrangKenaSyak = senaraiOrangKenaSyak;
	}

        public List<AduanSiasatan> getSenaraiAduanSiasatan() {
            return senaraiAduanSiasatan;
        }

        public void setSenaraiAduanSiasatan(List<AduanSiasatan> senaraiAduanSiasatan) {
            this.senaraiAduanSiasatan = senaraiAduanSiasatan;
        }

		public void setSenaraiPemantauan(List<AduanPemantauan> senaraiPemantauan) {
			this.senaraiPemantauan = senaraiPemantauan;
		}

		public List<AduanPemantauan> getSenaraiPemantauan() {
			return senaraiPemantauan;
		}

}
