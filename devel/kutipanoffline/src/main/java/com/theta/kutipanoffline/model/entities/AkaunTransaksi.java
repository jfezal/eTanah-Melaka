package com.theta.kutipanoffline.model.entities;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "akaun_transaksi")
public class AkaunTransaksi {
	@Id
	@Column(name="id_at")
	Long idAkaunTransaksi;
	
	@Column(name = "no_akaun")
	String noAkaun;
	
	@Column(name="kod_trans")
	String kodTransaksi;
	
	@Column(name = "perihal_trans")
	String perihalTransaksi;
	
	@Column(name = "amaun")
	BigDecimal amaun;
}
