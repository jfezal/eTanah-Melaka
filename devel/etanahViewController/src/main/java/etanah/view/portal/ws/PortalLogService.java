/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.portal.ws;

import com.google.inject.Injector;
import etanah.view.etanahContextListener;
import etanah.view.portal.ws.service.LogService;

/**
 *
 * @author mohd.faidzal
 */
public class PortalLogService implements IPortalLogService {

    private static Injector injector = etanahContextListener.getInjector();
    LogService logService = injector.getInstance(LogService.class);

    @Override
    public void insertLog(String json, String type) {
        logService.saveLog(json, type);
    }

}
