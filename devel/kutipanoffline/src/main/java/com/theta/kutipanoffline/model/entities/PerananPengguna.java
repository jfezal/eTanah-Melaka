package com.theta.kutipanoffline.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
@Data
@Entity
@Table(name = "peranan_pengguna")
public class PerananPengguna {

	@Id
	@Column(name = "id_pp")
	private Long idPP;
	
	@JoinColumn(name = "id_pengguna", referencedColumnName = "id_pengguna")
    @ManyToOne
	private Pengguna pengguna;
	@JoinColumn(name = "kod_peranan", referencedColumnName = "kod")
    @ManyToOne
	private KodPeranan kodPeranan;
	public Long getIdPP() {
		return idPP;
	}
	public void setIdPP(Long idPP) {
		this.idPP = idPP;
	}
	public Pengguna getPengguna() {
		return pengguna;
	}
	public void setPengguna(Pengguna pengguna) {
		this.pengguna = pengguna;
	}
	public KodPeranan getKodPeranan() {
		return kodPeranan;
	}
	public void setKodPeranan(KodPeranan kodPeranan) {
		this.kodPeranan = kodPeranan;
	}
	
	

}
