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
@Table(name = "transaksi_bayaran")
public class TransaksiBayaran {
	@Id
	@Column(name = "no_resit")
	String noresit;
//	@JoinColumn(name = "no_resit", referencedColumnName = "no_resit")
//    @ManyToOne
//	ResitBayaran resit;
	@Column(name = "no_akaun")
	String noAkaun;
	@Column(name = "id_hakmilik")
	String idHakmilik;
	@Column(name = "tarikh_bayaran")
	Date tarikhBayaran;
	@Column(name = "amaun")
	BigDecimal amaun;
	@Column(name = "status")
	String status;
	
}
