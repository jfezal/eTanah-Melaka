/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.service.common;

import com.google.inject.Inject;
import com.wideplay.warp.persist.Transactional;
import etanah.dao.PermohonanUrusanDAO;
import etanah.model.PermohonanUrusan;
import java.util.List;

/**
 *
 * @author md.nurfikri
 */
public class UrusniagaService {

    @Inject
    PermohonanUrusanDAO permohonanUrusanDAO;


    @Transactional
    public PermohonanUrusan saveOrUpdate(PermohonanUrusan pu){
        return permohonanUrusanDAO.saveOrUpdate(pu);
    }

    public List<PermohonanUrusan> findAll(){
        return permohonanUrusanDAO.findAll();
    }

    public PermohonanUrusan findById(Long Id){
        return permohonanUrusanDAO.findById(Id);
    }

    public PermohonanUrusan saveOrUpdateByConn(PermohonanUrusan pu){
        return permohonanUrusanDAO.saveOrUpdate(pu);
    }
}
