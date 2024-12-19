/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.kaunter;

import java.util.ArrayList;
import java.util.List;

import com.google.inject.Inject;
import etanah.dao.HakmilikDAO;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.service.ConsentPtdService;
import org.apache.log4j.Logger;

/**
 *
 * @author muhammad.amin
 */
public class UrusanConsentValidator {

    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    ConsentPtdService consentService;
    private static final Logger LOG = Logger.getLogger(UrusanConsentValidator.class);

    public ArrayList<String> validate(UrusanPermohonan urusan) {
        ArrayList<String> errors = new ArrayList<String>();
        StringBuilder sb = new StringBuilder();

        for (String idHakmilik : urusan.getHakmilikPermohonan()) {
            LOG.debug("Checking Pemegang Amanah : "+idHakmilik);
            List<HakmilikPihakBerkepentingan> hakmilikPihakList = new ArrayList<HakmilikPihakBerkepentingan>();
            hakmilikPihakList = consentService.findSenaraiHakPihakByHakmilikAndJenis(idHakmilik, "PA");

            if (!hakmilikPihakList.isEmpty()) {
                if (sb.length() > 0) {
                    sb.append(",");
                }
                sb.append(idHakmilik);
            }
        }

        if (sb.length() > 0) {
            String idHakmilikPA = sb.toString();
            errors.add("Hakmilik " + idHakmilikPA + " mempunyai Pemegang Amanah.");
        }

        return errors;
    }
}


