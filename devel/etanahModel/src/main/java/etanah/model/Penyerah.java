package etanah.model;

public interface Penyerah {
	
	public String getKodPenyerah();
		
	public long getIdPenyerah();

	public KodJenisPengenalan getJenisPengenalan();
	
	public String getNoPengenalan();
	
	public String getNama();

	public void setNama(String nama);

	public String getAlamat1();

	public void setAlamat1(String alamat1) ;

	public String getAlamat2();

	public void setAlamat2(String alamat2);

	public String getAlamat3();

	public void setAlamat3(String alamat3);

	public String getAlamat4();

	public void setAlamat4(String alamat4);

	public String getPoskod();

	public void setPoskod(String poskod);

	public KodNegeri getNegeri();

	public void setNegeri(KodNegeri negeri);

	public String getNoTelefon1();

	public void setNoTelefon1(String noTelefon1);

	public String getNoTelefon2();

	public void setNoTelefon2(String noTelefon2);
	
	public char getAktif();
	
	public void setAktif(char aktif);

	public InfoAudit getInfoAudit();

	public void setInfoAudit(InfoAudit infoAudit);
        
        public void setEmel(String emel);
        
        public String getEmel();

}
