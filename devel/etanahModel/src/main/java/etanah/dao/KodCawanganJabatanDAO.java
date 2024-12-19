package etanah.dao;

import etanah.model.KodCawanganJabatan;

public class KodCawanganJabatanDAO extends HibernateDAO<KodCawanganJabatan, String> {

	@Override
	public String getDefaultOrderProperty() {
		return "kod";
	}

}
