/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.portal.ws;

import com.google.inject.Injector;
import etanah.model.LogWS;
import etanah.view.etanahContextListener;
import etanah.view.portal.ws.service.LogService;
import java.text.ParseException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mohd.faidzal
 */
public class WsLogService implements IWsLogService {

    private static Injector injector = etanahContextListener.getInjector();
    LogService logService = injector.getInstance(LogService.class);

    @Override
    public Long insertLog(String jsonReq, String tarikhMasuk, String jenis, String modul) {
        
        try {
            return logService.saveLogWs( jsonReq,  tarikhMasuk,  jenis,  modul).getId();
        } catch (ParseException ex) {
            Logger.getLogger(WsLogService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public void updateLog(Long id, String info) {
        logService.updateLogWs(id, info);
    }

}
