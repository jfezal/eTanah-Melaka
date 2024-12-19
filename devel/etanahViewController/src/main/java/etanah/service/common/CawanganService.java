package etanah.service.common;

import com.google.inject.Inject;
import com.wideplay.warp.persist.Transactional;
import etanah.dao.PihakCawanganDAO;
import etanah.dao.PihakDAO;
import etanah.model.PihakCawangan;
import org.hibernate.Session;

public class CawanganService {

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    PihakCawanganDAO pihakCawanganDAO;
    @Inject
    PihakDAO pihakDAO;


    @Transactional
    public void delete(PihakCawangan pc) {
        pihakCawanganDAO.delete(pc);
    }

    public PihakCawangan findById(String idCawangan) {
        return pihakCawanganDAO.findById(Long.valueOf(idCawangan));
    }
}
