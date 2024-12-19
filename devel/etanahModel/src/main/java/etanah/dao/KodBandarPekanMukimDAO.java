package etanah.dao;

import etanah.model.*;

public class KodBandarPekanMukimDAO extends HibernateDAO<KodBandarPekanMukim, Integer> {

    public String getDefaultOrderProperty() {
        return "nama";
    }
}
