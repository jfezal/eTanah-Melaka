package com.theta.kutipanoffline.model.entities;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "resit_bayaran")
public class ResitBayaran {
	@Id
	@Column(name = "no_resit")
	String noresit;
	
	@Column(name = "no_akaun")
	String noAkaun;
	
	@Column(name = "id_hakmilik")
	String idHakmilik;
	
	@Column(name = "kod_daerah")
	String kodDaerah;
	
	@Column(name = "kod_cawangan")
	String kodCawangan;
	
	@Column(name = "amaun")
	BigDecimal amaun;
	
	@Column(name = "tarikh_bayaran")
	Date tarikhBayaran;
	
	@JoinColumn(name = "di_terima_oleh", referencedColumnName = "id_pengguna")
    @ManyToOne
	Pengguna diTerimaOleh;
	
	@Column(name = "status")
	String status;
	
	@JoinColumn(name = "di_batal_oleh", referencedColumnName = "id_pengguna")
    @ManyToOne
	private Pengguna diBatalOleh;
	
	@Column(name = "tarikh_batal")
	Date tarikhBatal;

}
