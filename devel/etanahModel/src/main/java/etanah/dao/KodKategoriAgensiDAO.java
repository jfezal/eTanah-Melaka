package etanah.dao;

import etanah.model.*;

public class KodKategoriAgensiDAO extends HibernateDAO<KodKategoriAgensi, String> {

    public String getDefaultOrderProperty() {
        return "nama";
    }
}
