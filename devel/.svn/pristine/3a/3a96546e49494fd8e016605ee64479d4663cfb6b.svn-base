/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.portal.ws.service;

import com.google.inject.Inject;
import com.wideplay.warp.persist.Transactional;
import etanah.dao.LogWsDAO;
import etanah.dao.PenggunaDAO;
import etanah.dao.PortalLogDAO;
import etanah.model.InfoAudit;
import etanah.model.LogWS;
import etanah.model.PortalLog;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author mohd.faidzal
 */
public class LogService {

    @Inject
    PortalLogDAO portalLogDAO;
    @Inject
    LogWsDAO logWsDAO;
    @Inject
    PenggunaDAO penggunaDAO;

    public String saveLog(String json, String type) {
        PortalLog portalLog = new PortalLog();
        portalLog = save(portalLog);
        return portalLog.getId() + "";
    }

    public String update(String id, String type) {
        PortalLog portalLog = portalLogDAO.findById(Long.valueOf(id));
        portalLog.setStatus(type);
        portalLog = save(portalLog);
        return portalLog.getId() + "";
    }

    @Transactional
    public PortalLog save(PortalLog portalLog) {
        return portalLogDAO.saveOrUpdate(portalLog);
    }

    @Transactional
    public LogWS saveLogWs(String jsonReq, String tarikhMasuk, String jenis, String modul) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss aa");
        LogWS logws = new LogWS();
        logws.setJenisWs(jenis);
        logws.setJsonReq(jsonReq);
        logws.setModul(modul);
        InfoAudit info = new InfoAudit();
        Date date = sdf.parse(tarikhMasuk);
        info.setTarikhMasuk(date);
        info.setDimasukOleh(penggunaDAO.findById("portal"));
        logws.setInfoAudit(info);
        return logWsDAO.saveOrUpdate(logws);
    }

    @Transactional
    public void updateLogWs(Long id, String info) {
        LogWS logws = logWsDAO.findById(id);
        logws.setLogInfo(info);
        logWsDAO.save(logws);
    }

}
