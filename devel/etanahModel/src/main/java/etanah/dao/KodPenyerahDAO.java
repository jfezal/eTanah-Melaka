package etanah.dao;

import etanah.model.KodPenyerah;

public class KodPenyerahDAO extends HibernateDAO<KodPenyerah, String>{

	@Override
	public String getDefaultOrderProperty() {
		return "nama";
	}
	
}
