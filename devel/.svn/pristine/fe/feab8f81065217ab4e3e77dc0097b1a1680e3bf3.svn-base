/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.etapp.ws;

import com.google.inject.Inject;
import com.wideplay.warp.persist.Transactional;
import etanah.dao.EtappLogDAO;
import etanah.model.etapp.EtappLog;

/**
 *
 * @author faidzal
 */
public class EtappLogService {

    @Inject
    EtappLogDAO etappLogDAO;
    @Inject
    PengambilanService service;

    @Transactional
    public void insertLog(String modul, String status, String urusan, String json, String flag, String code) {
        EtappLog etapp = new EtappLog();
        etapp.setModul(modul);
        etapp.setStatus(status);
        etapp.setUrusan(urusan);
        etapp.setInfoAudit(service.setInfoAudit());
        etapp.setFlagHantar(flag);
        etapp.setJson(json);

        etappLogDAO.saveOrUpdate(etapp);
    }
    @Transactional
    public void insertLog(String modul, String status, String urusan, String json) {
        EtappLog etapp = new EtappLog();
        etapp.setModul(modul);
        etapp.setStatus(status);
        etapp.setUrusan(urusan);
        etapp.setInfoAudit(service.setInfoAudit());
        etapp.setJson(json);

        etappLogDAO.saveOrUpdate(etapp);
    }
    
     @Transactional
    public void insertLog(String modul, String status, String urusan) {
        EtappLog etapp = new EtappLog();
        etapp.setModul(modul);
        etapp.setStatus(status);
        etapp.setUrusan(urusan);
        etapp.setInfoAudit(service.setInfoAudit());
        
        etappLogDAO.saveOrUpdate(etapp);
    }
    
    
}
