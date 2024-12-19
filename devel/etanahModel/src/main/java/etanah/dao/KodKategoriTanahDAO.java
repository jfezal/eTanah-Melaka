package etanah.dao;

import etanah.model.*;

public class KodKategoriTanahDAO extends HibernateDAO<KodKategoriTanah, String> {

    public String getDefaultOrderProperty() {
        return "nama";
    }
}
