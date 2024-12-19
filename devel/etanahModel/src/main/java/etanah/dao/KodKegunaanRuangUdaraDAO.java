package etanah.dao;

import etanah.model.KodKegunaanRuangUdara;

public class KodKegunaanRuangUdaraDAO extends HibernateDAO<KodKegunaanRuangUdara, String> {

    public String getDefaultOrderProperty() {
        return "nama";
    }
}
