package etanah.dao;

import etanah.model.*;

public class KodNegaraDAO extends HibernateDAO<KodNegara, String> {

    public String getDefaultOrderProperty() {
        return "nama";
    }
}
