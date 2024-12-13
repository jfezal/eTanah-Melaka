package etanah.dao;

import etanah.model.KodKategoriBangunan;

public class KodKategoriBangunanDAO extends HibernateDAO<KodKategoriBangunan, String> {

    public String getDefaultOrderProperty() {
        return "nama";
    }
}
