package etanah.dao;

import etanah.model.KodKadarBayaran;

public class KodKadarBayaranDAO extends HibernateDAO<KodKadarBayaran, String> {

    public String getDefaultOrderProperty() {
        return "nama";
    }
}
