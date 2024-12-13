package etanah.dao;

import etanah.model.*;

public class KodNegeriDAO extends HibernateDAO<KodNegeri, String> {

    public String getDefaultOrderProperty() {
        return "nama";
    }
}
