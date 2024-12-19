/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.strata;
import java.io.Serializable;
import com.google.inject.Inject;
import etanah.dao.AduanOrangKenaSyakDAO;
import etanah.model.Alamat;
/**
 *
 * @author aminah.abdmutalib
 */
public class OksValue implements Serializable {
        @Inject
	AduanOrangKenaSyakDAO aduanOrangKenaSyakDAO;

        long idOrangKenaSyak;
        String nama;
        String noPengenalan;
        String noTelefon1;
        Alamat alamat;
        
        public Alamat getAlamat() {
            return alamat;
        }

        public void setAlamat(Alamat alamat) {
            this.alamat = alamat;
        }

        public long getIdOrangKenaSyak() {
		return idOrangKenaSyak;
	}

	public void setIdOrangKenaSyak(long idOrangKenaSyak) {
		this.idOrangKenaSyak = idOrangKenaSyak;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public String getNoPengenalan() {
		return noPengenalan;
	}

	public void setNoPengenalan(String noPengenalan) {
		this.noPengenalan = noPengenalan;
	}

	public String getNoTelefon1() {
		return noTelefon1;
	}

	public void setNoTelefon1(String noTelefon1) {
		this.noTelefon1 = noTelefon1;
	}

}
