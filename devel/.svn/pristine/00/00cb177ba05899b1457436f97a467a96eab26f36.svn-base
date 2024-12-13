/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.portal.service.ws;

import com.google.inject.Injector;
import etanah.view.etanahContextListener;
import etanah.view.stripes.hasil.HistoryTransactionActionBean;

/**
 *
 * @author mohd.faidzal
 */
public class UtilitiSetupService implements IUtilitiSetupService{
    private static Injector injector = etanahContextListener.getInjector();
    HistoryTransactionActionBean historyTransactionActionBean = injector.getInstance(HistoryTransactionActionBean.class);

    @Override
    public String pushSejarah(Integer limit, String kodCaw) {
        String status = "";
        status = historyTransactionActionBean.pushSejarahManual(limit,kodCaw);
        return status;
    }
    
    
}
