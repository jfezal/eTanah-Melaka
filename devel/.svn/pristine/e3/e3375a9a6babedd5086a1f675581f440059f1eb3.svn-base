/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.penguatkuasaan;

import com.google.inject.Inject;
import etanah.model.Enkuiri;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.EnkuiriPenguatkuasaan;
import etanah.model.KodCawangan;
import etanah.service.common.EnforcementService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;

/**
 *
 * @author ctzainal
 */
public class CalendarEnkuiriManager {

    private static final Logger LOG = Logger.getLogger(CalendarEnkuiriManager.class);
    @Inject
    EnforcementService enforcementService;
    private List<CalendarEnkuiri> listCalendar;

    public String splitTempat(String tempat) {

        String[] strArryAlamat;
        String strTempAlamat = tempat;
        strArryAlamat = strTempAlamat.split(",");
        strTempAlamat = "";
        if (strArryAlamat.length > 0) {
            for (int i = 0; i < strArryAlamat.length - 1; i++) {
                strTempAlamat = strTempAlamat + strArryAlamat[i] + " ";
            }
            strTempAlamat = strTempAlamat + strArryAlamat[strArryAlamat.length - 1];
            LOG.info("strTempAlamat : " + strTempAlamat);
        }
        return strTempAlamat;
    }

    public List<CalendarEnkuiri> getALLEnkuiri() {
        LOG.info("getALLEnkuiri : ");
        //calendar
        //enkuiri
        List<EnkuiriPenguatkuasaan> senaraiEnkuiri2 = enforcementService.getALLEnkuiri();
        LOG.info("senaraiEnkuiri2 : " + senaraiEnkuiri2.size());
        CalendarEnkuiri cE = null;
        listCalendar = new ArrayList<CalendarEnkuiri>();
        for (EnkuiriPenguatkuasaan ep : senaraiEnkuiri2) {

            String tempat = splitTempat(ep.getTempat()).toString();
            System.out.println("tempat sblm replace : "+tempat);
            tempat = tempat.replace("\n", " ");
            System.out.println("tempat slps replace : "+tempat);
            cE = new CalendarEnkuiri();
            cE.setTempat(tempat);
            cE.setCawangan(ep.getPermohonan().getCawangan());
            cE.setIdMohon(ep.getPermohonan().getIdPermohonan());
            cE.setTarikhMula(ep.getTarikhMula());
            listCalendar.add(cE);

        }

        LOG.info("listCalender : " + listCalendar.size());
        return listCalendar;
        //end calendar
    }
}
