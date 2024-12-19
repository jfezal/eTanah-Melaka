/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata.validator;

import com.google.inject.Inject;
import etanah.model.PelaksanaWaran;
import etanah.model.Permohonan;
import etanah.model.PermohonanSyarikatLelong;
import etanah.model.PermohonanWaranItem;
import etanah.model.StorRampasan;
import etanah.service.StrataPtService;
import etanah.view.strata.CommonService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;

/**
 *
 * @author faidzal
 */
public class KemasukanWaranValidator implements StageListener {

    @Inject
    CommonService comm;
    @Inject
    StrataPtService strService;
    StorRampasan stor = new StorRampasan();
    PelaksanaWaran pelaksanaWaran = new PelaksanaWaran();
    PermohonanWaranItem mohonWaranItem = new PermohonanWaranItem();
    PermohonanSyarikatLelong mohonSytLelong = new PermohonanSyarikatLelong();

    @Override
    public boolean beforeStart(StageContext sc) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean beforeGenerateReports(StageContext sc) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void onGenerateReports(StageContext sc) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String beforeComplete(StageContext sc, String string) {
        String pass = null;
        boolean check = false;
        Permohonan permohonan = sc.getPermohonan();
        stor = strService.findStor(permohonan.getIdPermohonan());
        if (stor == null) {
            pass = null;
            sc.addMessage("Sila isikan Maklumat Stor Rampasan");
        } else {
            check = true;
        }
        if (check) {
            if (!comm.getListPerlaksana(permohonan.getIdPermohonan()).isEmpty()) {
                pass = string;
                check = true;
            } else {
                check = false;
                pass = null;
                sc.addMessage("Sila Masukkan Maklumat Pelaksana Waran ");
            }
        }
        if (check) {

            if (!comm.findSenaraiItemWaran(permohonan.getIdPermohonan(), permohonan.getSenaraiHakmilik().get(0).getId()).isEmpty()) {
                pass = string;
                check = true;
            } else {
                check = false;
                pass = null;
                sc.addMessage("Sila isikan Maklumat Senarai Hutang");
            }
        }
        if (check) {

            if (strService.findSytLelong(permohonan.getIdPermohonan()).isEmpty()) {
                pass = null;
                sc.addMessage("Sila isikan Maklumat JuruLelong");
            }
        }
        return pass;
    }

    @Override
    public void afterComplete(StageContext sc) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String beforePushBack(StageContext sc, String string) {
        return string;
    }

    @Override
    public void afterPushBack(StageContext sc) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
