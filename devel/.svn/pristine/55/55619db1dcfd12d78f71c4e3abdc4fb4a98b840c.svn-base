package etanah.model;

import java.util.ArrayList;

import javax.persistence.*;


@Entity
@Table (name = "menu_item")
@org.hibernate.annotations.Cache (usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE)
//@NamedQuery (name = "selectMenuForUser",
//		     query = "select distinct m " +
//			            "from " +
//				        "Pengguna u , " +
//				        "PenggunaPeranan pp, " +
//				        "MenuCapaian c, " +
//				        "MenuItem m  " +
//				        "where " +
//				        "(m.umum = 'Y') or " +
//				        "(u.idPengguna = :idPengguna and " +
//				        "u.idPengguna = pp.pengguna.id and " +
//				        "pp.peranan.id = c.peranan.id and " +
//				        "c.menu.id = m.idMenu and " +
//				        "m.aktif = 'Y') " +
//				        "order by m.atas.id, m.turutan, m.tajuk ")
@NamedQuery (name = "selectMenuForUser",
		     query = "select distinct m " +
			            "from " +				        
				        "MenuItem m  " +
				        "where m.aktif ='Y' and (" +
                                        "m.umum = 'Y' or m.idMenu in " +
                                        "(Select distinct c.menu.id from " +
                                        "Pengguna u , PenggunaPeranan pp,MenuCapaian c where " +
				        "u.idPengguna = pp.pengguna.id and " +
				        "pp.peranan.id = c.peranan.id and " +
                                        "u.idPengguna = :idPengguna) )" +
				        "order by m.atas.id, m.turutan, m.tajuk ")
@SequenceGenerator(name = "seq_menu_item", sequenceName = "seq_menu_item", allocationSize = 1)
public class MenuItem {

	@Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_menu_item")
	@Column (name = "id_menu")
    public int idMenu;
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "id_atas")
    public MenuItem atas;
	
	@Column (name = "tajuk")
    public String tajuk;
	
	@Column (name = "uri")
    public String uri;
	
	@Column (name = "url_ikon")
    public String urlIkon;
	
	@Column (name = "umum")
	public char umum;
	
	@Column (name = "seq")
	public int turutan;
    
	@Column (name = "aktif")
	private char aktif;
	
	/**
	 * Working variable. Useless to be loaded from Hibernate since
	 * must be checked against security
	 */
	@Transient
    public ArrayList<MenuItem> senaraiSubmenu = new ArrayList<MenuItem>();

	@Embedded
	public InfoAudit infoAudit;

	public int getIdMenu() {
		return idMenu;
	}

	public void setIdMenu(int idMenu) {
		this.idMenu = idMenu;
	}

	public MenuItem getAtas() {
		return atas;
	}

	public void setAtas(MenuItem atas) {
		this.atas = atas;
	}

	public String getTajuk() {
		return tajuk;
	}

	public void setTajuk(String tajuk) {
		this.tajuk = tajuk;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getUrlIkon() {
		return urlIkon;
	}

	public void setUrlIkon(String urlIkon) {
		urlIkon = urlIkon;
	}

	public char getUmum() {
		return umum;
	}

	public void setUmum(char umum) {
		this.umum = umum;
	}

	public int getTurutan() {
		return turutan;
	}

	public void setTurutan(int turutan) {
		this.turutan = turutan;
	}

	public char getAktif() {
		return aktif;
	}

	public void setAktif(char aktif) {
		this.aktif = aktif;
	}

	public ArrayList<MenuItem> getSenaraiSubmenu() {
		return senaraiSubmenu;
	}

	public void setSenaraiSubmenu(ArrayList<MenuItem> senaraiSubmenu) {
		this.senaraiSubmenu = senaraiSubmenu;
	}

	public InfoAudit getInfoAudit() {
		return infoAudit;
	}

	public void setInfoAudit(InfoAudit infoAudit) {
		this.infoAudit = infoAudit;
	}

}

