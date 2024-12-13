package etanah.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table (name = "menu_capai")
@SequenceGenerator(name = "seq_menu_capai", sequenceName = "seq_menu_capai", allocationSize = 1)
@org.hibernate.annotations.Cache (usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE)
public class MenuCapaian implements Serializable {
	@Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_menu_capai")
	@Column (name = "id_capai")
	private int idCapaian;
	
	@ManyToOne (fetch = FetchType.EAGER)
	@JoinColumn (name = "kod_peranan")
	private KodPeranan peranan;
	
	@ManyToOne
	@JoinColumn (name = "id_menu")
	private MenuItem menu;
	
	@Embedded
	private InfoAudit infoAudit;

	public int getIdCapaian() {
		return idCapaian;
	}

	public void setIdCapaian(int idCapaian) {
		this.idCapaian = idCapaian;
	}

	public KodPeranan getPeranan() {
		return peranan;
	}

	public void setPeranan(KodPeranan peranan) {
		this.peranan = peranan;
	}

	public MenuItem getMenu() {
		return menu;
	}

	public void setMenu(MenuItem menu) {
		this.menu = menu;
	}

	public InfoAudit getInfoAudit() {
		return infoAudit;
	}

	public void setInfoAudit(InfoAudit infoAudit) {
		this.infoAudit = infoAudit;
	}
	
}
