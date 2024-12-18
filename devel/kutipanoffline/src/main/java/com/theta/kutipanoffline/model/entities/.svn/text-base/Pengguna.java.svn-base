package com.theta.kutipanoffline.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
@Data
@Entity
@Table(name = "pengguna")
public class Pengguna {

	@Id
	@Column(name = "id_pengguna")
	private String idPengguna;
	
	@Column(name = "nama")
	private String nama;
	
	@Column(name = "katalaluan")
	private String katalaluan;
	
	@Column(name = "alamat")
	private String alamat;

	public String getIdPengguna() {
		return idPengguna;
	}

	public void setIdPengguna(String idPengguna) {
		this.idPengguna = idPengguna;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public String getKatalaluan() {
		return katalaluan;
	}

	public void setKatalaluan(String katalaluan) {
		this.katalaluan = katalaluan;
	}

	public String getAlamat() {
		return alamat;
	}

	public void setAlamat(String alamat) {
		this.alamat = alamat;
	}	
	
}
