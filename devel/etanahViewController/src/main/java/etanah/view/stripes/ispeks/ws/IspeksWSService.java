/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.ispeks.ws;

import com.google.inject.Injector;
import etanah.service.ispeks.IspeksReadFileService;
import etanah.view.etanahContextListener;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mohd.faidzal
 */
public class IspeksWSService implements IIspeksWSService {

    private static Injector injector = etanahContextListener.getInjector();
    IspeksReadFileService ispeksReadFileService = injector.getInstance(IspeksReadFileService.class);

    @Override
    public Boolean extractToDatabase(String kat, String path) {
        try {
            return ispeksReadFileService.prosesIntegrationAuto(kat, path);
        } catch (Exception ex) {
            Logger.getLogger(IspeksWSService.class.getName()).log(Level.SEVERE, null, ex);
       return false;
        }
 }

}
