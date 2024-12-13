/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.ws;

import etanah.etapp.ws.EtappClientServiceImpl;
import etanah.gpg.pgpfile.DecryptServiceImpl;
import etanah.mail.ws.MailClientService;
import etanah.mail.ws.MailClientServiceImpl;
import etanah.scheduler.CronTrigger;
import javax.xml.ws.Endpoint;
import org.apache.log4j.Logger;
import org.quartz.SchedulerException;

/**
 *
 * @author mohd.faidzal
 */
public class MainStart {
  private static final Logger logger = Logger.getLogger(MainStart.class);
    public static void main(String[] args) throws SchedulerException {
        URLForm url = new URLForm();
        System.out.println("Server is running. Please dont close terminal");
        Endpoint.publish(url.urlEtappClient, new EtappClientServiceImpl());
        Endpoint.publish(url.urlIspeksClient, new DecryptServiceImpl());
         Endpoint.publish(url.urlMailClient, new MailClientServiceImpl());
         CronTrigger trig = new CronTrigger();
         trig.trigger();
        System.out.println("Server is running. Please dont close terminal" + url.urlEtappClient + "*" );
        System.out.println("Server is running. Please dont close terminal"  + url.urlIspeksClient+ "*");
                System.out.println("Server is running. Please dont close terminal"  + url.urlMailClient+ "*");
                logger.error("ERROR");
        logger.warn("WARNING"); 
    logger.fatal("FATAL");
        logger.debug("DEBUG");
        logger.info("INFO");
        System.out.println("Final Output");


    }
}
