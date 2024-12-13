package etanah.dao;

import etanah.model.PermohonanBangunan;

public class PermohonanBangunanDAO extends HibernateDAO<PermohonanBangunan, Long> {

     @Override
    public String getDefaultOrderProperty() {
        return "nama desc";
    }
}
