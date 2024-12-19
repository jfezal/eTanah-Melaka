package etanah.dao;

import etanah.model.*;

public class KodJenisPihakBerkepentinganDAO extends HibernateDAO<KodJenisPihakBerkepentingan, String> {

	@Override
	public String getDefaultOrderProperty() {
		return "nama";
	}

}
