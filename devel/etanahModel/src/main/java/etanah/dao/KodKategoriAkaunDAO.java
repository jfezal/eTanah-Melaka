package etanah.dao;

import etanah.model.*;

public class KodKategoriAkaunDAO extends HibernateDAO<KodKategoriAkaun, String> {

    public String getDefaultOrderProperty() {
        return "nama";
    }
}
