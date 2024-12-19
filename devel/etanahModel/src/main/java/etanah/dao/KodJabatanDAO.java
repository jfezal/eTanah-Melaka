package etanah.dao;

import etanah.model.KodJabatan;

public class KodJabatanDAO extends HibernateDAO<KodJabatan, String> {

	@Override
	public String getDefaultOrderProperty() {
		return "nama";
	}

}
