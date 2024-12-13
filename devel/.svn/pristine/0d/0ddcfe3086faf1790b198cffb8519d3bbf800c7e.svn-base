/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.lelong;

import com.google.inject.Inject;
import etanah.model.Enkuiri;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.Lelongan;
import etanah.model.PermohonanPihak;
import etanah.service.common.LelongService;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;

/**
 *
 * @author mdizzat.mashrom
 */
public class CalenderManager {

    private static final Logger LOG = Logger.getLogger(CalenderManager.class);
    @Inject
    LelongService lelongService;
    private List<CalenderLelong> listCalender;
    private List<CalenderLelong> listCalender2;

    public String splitTempat(String tempat) {
        String[] strArryAlamat;
        String strTempAlamat = tempat;
//            strArryAlamat = strTempAlamat.split(",");
        strArryAlamat = strTempAlamat.split("\n");
        strTempAlamat = "";
        if (strArryAlamat.length > 0) {
            for (int i = 0; i < strArryAlamat.length - 1; i++) {
                String replace = strArryAlamat[i].replace(",", "");
                String trim = replace.trim();
//                    strTempAlamat = strTempAlamat + strArryAlamat[i] + " ";
                if (strArryAlamat.length > 1) {
                    strTempAlamat = strTempAlamat + trim + ", ";
                } else {
                    strTempAlamat = strTempAlamat + trim + " ";
                }
            }
            strTempAlamat = strTempAlamat + strArryAlamat[strArryAlamat.length - 1];
        }
        return strTempAlamat;
    }

    public List<CalenderLelong> getALLEnkuri(String kod) {
        //calender
        //enkuiri
        List<Enkuiri> senaraiEnkuiri2 = lelongService.getALLEnkuri(kod);
        LOG.info("senaraiEnkuiri2 : " + senaraiEnkuiri2.size());
        CalenderLelong cL = null;
        List<PermohonanPihak> pihakle = null;
        listCalender = new ArrayList<CalenderLelong>();
        int i = 0;
        for (Enkuiri ee : senaraiEnkuiri2) {
            String hak = "";
            String tempat = splitTempat(ee.getTempat()).toString();
            cL = new CalenderLelong();
            cL.setTempat(tempat);
            cL.setIdMohon(ee.getPermohonan().getIdPermohonan());
            if (ee.getTarikhEnkuiri() != null) {
                LOG.info("tarikh Enkuiri : " + ee.getTarikhEnkuiri());

                cL.setTarikh(ee.getTarikhEnkuiri());
            } else {
                cL.setTarikh(new Date());
            }
            cL.setBil(ee.getBilanganKes());
            List<PermohonanPihak> listHAkP = lelongService.getSenaraiPmohonPihak(ee.getPermohonan().getIdPermohonan());
            Map<Long, PermohonanPihak> pihakMap2 = new HashMap<Long, PermohonanPihak>();
            for (PermohonanPihak hp : listHAkP) {
                Long id = hp.getPihak().getIdPihak();
                if (pihakMap2.containsKey(id)) {
                    continue;
                } else {
                    pihakMap2.put(id, hp);
                }
            }
            pihakle = new ArrayList(pihakMap2.values());

            StringBuilder sb = new StringBuilder();
            for (HakmilikPermohonan hp : ee.getPermohonan().getSenaraiHakmilik()) {
                Hakmilik h = hp.getHakmilik();
                if (sb.length() > 0) {
                    sb.append("<br>");
                }
                sb.append(h.getIdHakmilik());
            }
            hak = sb.toString();
            cL.setHakmilik(hak);
            cL.setNama(pihakle);
            listCalender.add(cL);
            i++;

        }
        LOG.info("listCalender : " + listCalender.size());
        return listCalender;
        //end calender
    }

    public List<CalenderLelong> getALLLelongan(String kod) {
        //lelong
        List<Lelongan> listL = lelongService.getALLLelongan(kod);
        LOG.info("listL : " + listL.size());
        Map<String, Lelongan> pihakMap = new HashMap<String, Lelongan>();
        for (Lelongan ll : listL) {
            String id = ll.getPermohonan().getIdPermohonan();
            if (pihakMap.containsKey(id)) {
                continue;
            } else {
                pihakMap.put(id, ll);
            }
        }
        List<Lelongan> senaraiLelongan1 = new ArrayList(pihakMap.values());
        CalenderLelong cL = null;
        List<PermohonanPihak> pihakle = null;
        listCalender2 = new ArrayList<CalenderLelong>();
        LOG.info("senaraiLelongan1 : " + senaraiLelongan1.size());
        if (!senaraiLelongan1.isEmpty()) {
            for (Lelongan ee : senaraiLelongan1) {
                String hak = "";
                String tempat = "";
                if (ee.getTempat() != null) {
                    tempat = splitTempat(ee.getTempat()).toString();
                }

                cL = new CalenderLelong();
//                cL.setTempat(tempat);
                cL.setIdMohon(ee.getPermohonan().getIdPermohonan());
                cL.setTarikh(ee.getTarikhLelong());
                cL.setBil(ee.getBil());
                List<PermohonanPihak> listHAkP = lelongService.getSenaraiPmohonPihak(ee.getPermohonan().getIdPermohonan());
                Map<Long, PermohonanPihak> pihakMap2 = new HashMap<Long, PermohonanPihak>();
                for (PermohonanPihak hp : listHAkP) {
                    Long id = hp.getPihak().getIdPihak();
                    if (pihakMap2.containsKey(id)) {
                        continue;
                    } else {
                        pihakMap2.put(id, hp);
                    }
                }
                pihakle = new ArrayList(pihakMap2.values());
                StringBuilder sb = new StringBuilder();
                for (HakmilikPermohonan hp : ee.getPermohonan().getSenaraiHakmilik()) {
                    Hakmilik h = hp.getHakmilik();
                    if (sb.length() > 0) {
                        sb.append("<br>");
                    }
                    sb.append(h.getIdHakmilik());
                }
                hak = sb.toString();
                cL.setHakmilik(hak);
                cL.setNama(pihakle);
                listCalender2.add(cL);
            }
        }
        LOG.info("listCalender2 : " + listCalender2.size());
        return listCalender2;
    }

    public List<CalenderLelong> getALLLelonganBaru(String kod) {
        //lelong
        List<Lelongan> listL = lelongService.getALLLelonganNotInSL(kod);
        LOG.info("listL : " + listL.size());
        Map<String, Lelongan> pihakMap = new HashMap<String, Lelongan>();
        for (Lelongan ll : listL) {
            String id = ll.getPermohonan().getIdPermohonan();
            if (pihakMap.containsKey(id)) {
                continue;
            } else {
                pihakMap.put(id, ll);
            }
        }
        List<Lelongan> senaraiLelongan1 = new ArrayList(pihakMap.values());
        CalenderLelong cL = null;
        List<PermohonanPihak> pihakle = null;
        listCalender2 = new ArrayList<CalenderLelong>();
        LOG.info("senaraiLelongan1 : " + senaraiLelongan1.size());
        int count = 0;
        if (!senaraiLelongan1.isEmpty()) {
            for (Lelongan ee : senaraiLelongan1) {
                String hak = "";
                String tempat = "";
                
                if (ee.getTempat() != null) {
                    tempat = splitTempat(ee.getTempat()).toString();
                }

                cL = new CalenderLelong();
//                cL.setTempat(tempat);
                cL.setIdMohon(ee.getPermohonan().getIdPermohonan());
                cL.setTarikh(ee.getTarikhLelong());
                cL.setBil(ee.getBil());
                List<PermohonanPihak> listHAkP = lelongService.getSenaraiPmohonPihak(ee.getPermohonan().getIdPermohonan());
                Map<Long, PermohonanPihak> pihakMap2 = new HashMap<Long, PermohonanPihak>();
                for (PermohonanPihak hp : listHAkP) {
                    Long id = hp.getPihak().getIdPihak();
                    if (pihakMap2.containsKey(id)) {
                        continue;
                    } else {
                        pihakMap2.put(id, hp);
                    }
                }
                pihakle = new ArrayList(pihakMap2.values());
                StringBuilder sb = new StringBuilder();
                for (HakmilikPermohonan hp : ee.getPermohonan().getSenaraiHakmilik()) {
                    Hakmilik h = hp.getHakmilik();
                    if (sb.length() > 0) {
                        sb.append("<br>");
                    }
                    sb.append(h.getIdHakmilik());
                }
                hak = sb.toString();
                cL.setHakmilik(hak);
                if (!pihakle.equals(null)) {
                    cL.setNama(pihakle);
                } else {
                    LOG.info("Count = " + count++);
                }
                if (!cL.getNama().equals(null) || !cL.getTempat().equals(null) || !cL.getHakmilik().equals(null) || !cL.getIdMohon().equals(null)) {
                    listCalender2.add(cL);
                }
            }
        }
        LOG.info("listCalender2 : " + listCalender2.size());
        return listCalender2;
    }
}
