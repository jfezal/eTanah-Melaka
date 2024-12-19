package etanah.dao;

import etanah.model.BangunanPetakAksesori;

public class BangunanPetaAksesoriDAO extends HibernateDAO<BangunanPetakAksesori, Long> {
     @Override
    public String getDefaultOrderProperty() {
        return "nama desc";
    }

    
}
