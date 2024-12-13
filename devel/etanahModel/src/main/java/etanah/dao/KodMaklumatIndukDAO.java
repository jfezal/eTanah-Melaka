package etanah.dao;

import etanah.model.*;

public class KodMaklumatIndukDAO extends HibernateDAO<KodMaklumatInduk, String> {

    public String getDefaultOrderProperty() {
        return "nama";
    }
}
