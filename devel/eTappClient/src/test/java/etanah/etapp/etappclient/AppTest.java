package etanah.etapp.etappclient;

import etanah.etapp.ws.StatusForm;
import intg.etapp.EtappClientService;
import intg.etapp.EtappClientServiceImplServiceClient;
import java.util.ArrayList;
import java.util.List;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest
        extends TestCase {

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        EtappClientServiceImplServiceClient service = new EtappClientServiceImplServiceClient();
        EtappClientService port = service.getEtappClientServiceImplPort();

        StatusForm f = port.hantarBorangA("", "");
        
        return new TestSuite(AppTest.class);
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp() {
        assertTrue(true);
    }
}
