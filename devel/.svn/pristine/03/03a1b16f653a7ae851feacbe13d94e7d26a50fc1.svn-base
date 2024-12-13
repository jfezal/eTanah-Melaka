package etanah.view.support.util;

import able.stripes.AbleActionBean;
import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.emmkn.ws.EMMKNService;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.service.common.IntegrasiService;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
import java.util.Date;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.apache.log4j.Logger;

/**
 *
 * @author Izam
 */

@UrlBinding ("/util/test-emmkn")
public class TestEMMKNData extends AbleActionBean {

    @Inject
    private PermohonanDAO mohonDAO;

    @Inject
    private IntegrasiService intgServ;

    private static final Logger LOG = Logger.getLogger(TestEMMKNData.class);

    @DefaultHandler
    public Resolution perform() {
        String html = null;

        EMMKNService service = new EMMKNService();

        /* PLEASE FOLLOW THE SEQUENCE WHEN ADDING THE METHODS */

        //Set username and password for web service authentication
        service.setUserAndPassword("wsEtanahUserId", "wsEtanah2010TEST");

        //Set Ulasan for Jabatan Teknikal, PTD and PTD
        /*  'T' for ulasan Jabatan Teknikal 
            'D' for ulasan PTD 
            'G' for ulasan PTG */
        service.addUlasan("D", "2", "Tanah bagus.");
        service.addUlasan("G", "2", "Tanah yang bagus.");

        /* THE APPLICANT CAN BE EITHER INDIVIDUAL, COMPANY OR JABATAN */
        /*
         * Individual - must set addTanggungan (optional) and addIndividual
         * Company - must set addPengarah (optional) and addCompany
         * Jabatan - must set addJabatan
         */

        /* SAMPLE DATA FOR INDIVIDUAL
        //Set maklumat tanggungan for individual applicant
        service.addTanggungan("AYER KEROH", "ADIK", "MAHFUZ HAFIZ", "840326045123",
                "PEGAWAI", new BigDecimal(1200));

        //Set individual applicant
        service.addIndividual("AYER KEROH", "AYER KEROH", Boolean.TRUE, "sahid@gmail.com",
                "MEL", "1", "MAL", 5, "HUSSIN BIN NAPIAH", "460302075233", "0191231231",
                "PEGAWAI", new BigDecimal(2000), "AYER KEROH", "23");
         * 
         */

        /* SAMPLE DATA FOR COMPANY */
        //Set for company applicant (director)
        service.addPengarah("AYER KEROH", "MAL", "IMAN MANAN", "850922045522");

        //Set for company information (tarikhDitubuhkan is mandatory)
        service.addCompany("AYER KEROH", "", "", "", "sahid@bbmb.com.my", new BigDecimal(150000),
                new BigDecimal(150000), "BBMB KEWANGAN BERHAD", "A138417", "034231231",
                service.convertDate(new Date()));

        /* SAMPLE DATA FOR JABATAN
        //Set for jabatan applicant - kodJabatan can be fetch from IntegrasiPihakJabatan
        service.addJabatan("AYER KEROH", "sahid@bbmb.com.my", "01", "034231231");
         * 
         */

        //Set lot information
        service.addLotInformation("DSP", "41", "45198", "2055", "");

        //Set land application
        service.addLandApplication("GRANT", "1");

        //Set land general details
        service.addLandDetails("LADANG", "LADANG", "30", "01", "TANAH BERBUKIT DAN RATA",
                "KAMPUNG SUNGAI BERTAM", "975", "10494.83", "0.0975", "0.24", "MAKLUMAT TAMBAHAN",
                "LADANG", "LADANG", "", "LADANG", "LADANG", "LADANG", "LADANG");

        //Set kertas risalat
        service.createRisalat("Akta Pengambilan Tanah 35", "BANDAR JASIN", "02",
                "0403CON2010150013", "0403CON2010150013", "KAWASAN BANDAR I",
                "KERTAS PERMOHONAN PENGAMBILAN PENARIKAN BALIK");

        //Invoke the service and send the data
        service.sendData();
        
        // the data are used for testing purposes
        Permohonan mohon = mohonDAO.findById("0400CON2010150011");
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().
                                            getAttribute(etanahActionBeanContext.KEY_USER);

        // save the web service log
        if(mohon != null && pengguna != null)
            intgServ.saveLog(pengguna, mohon, service);

        if (html == null) html = "Please check the log for more information about the status.";
        
        return new StreamingResolution("text/plain", html);
    }

}
