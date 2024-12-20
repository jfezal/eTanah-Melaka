/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.hasil.validator;
import com.google.inject.Inject;
import etanah.model.*;
import etanah.dao.*;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author abdul.hakim
 */
public class AnsuranValidation implements StageListener{
    private static final Logger LOG = Logger.getLogger(AnsuranValidation.class);

    @Inject
    HakmilikDAO hakmilikDAO;

    @Inject
    HakmilikAlamatDAO hakmilikAlamatDAO;

    @Inject
    PermohonanPihakDAO permohonanPihakDAO;

    @Inject
    PermohonanRujukanLuarDAO permohonanRujukanLuarDAO;

    @Inject
    PermohonanAktivitiDAO permohonanAktivitiDAO;

    @Inject
    private etanah.Configuration conf;


    @Override
    public boolean beforeStart(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean beforeGenerateReports(StageContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void onGenerateReports(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {
        LOG.info("beforeComplete::Start");
        Permohonan permohonan = context.getPermohonan();
        Hakmilik hakmilik = hakmilikDAO.findById(permohonan.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik());
        List<PermohonanPihak> senaraiPemohonanPihak = new ArrayList();
        
        String[] name = {"permohonan"};
        Object[] value= {permohonan};
        senaraiPemohonanPihak = permohonanPihakDAO.findByEqualCriterias(name, value, null);
        int count = 0;
        LOG.debug("senaraiPemohon.size() :"+senaraiPemohonanPihak.size());
        if("04".equals(conf.getProperty("kodNegeri"))){
            if("BACT".equals(permohonan.getKodUrusan().getKod())){ //validation for kodUrusan = BACT (Bayaran Ancuran Cukai Tanah)

                for (int x=0; x<senaraiPemohonanPihak.size(); x++) {
                    PermohonanPihak mhnPihak = senaraiPemohonanPihak.get(x);
                    count = x;
                }
                PermohonanPihak pp = senaraiPemohonanPihak.get(count);
                if (StringUtils.isBlank(pp.getPekerjaan()) || pp.getPendapatan().doubleValue() == 0.0) {
                    context.addMessage("Sila masukkan maklumat pada Maklumat Pemohon : " + permohonan.getIdPermohonan());
                    return null;
                }else if(permohonan.getTempohBulan()== 0 || permohonan.getTarikhMula()==null){
                    context.addMessage("Sila masukkan maklumat pada Maklumat Bayaran Ansuran : "+permohonan.getIdPermohonan());
                    return null;
                }
            }
        }
        if("05".equals(conf.getProperty("kodNegeri"))){
            if("RBYA".equals(permohonan.getKodUrusan().getKod())){ //validation for kodUrusan = RBYA (Bayaran Ancuran Cukai Tanah)

                for (int x=0; x<senaraiPemohonanPihak.size(); x++) {
                    PermohonanPihak mhnPihak = senaraiPemohonanPihak.get(x);
                    count = x;
                }
                PermohonanPihak pp = senaraiPemohonanPihak.get(count);
                if (StringUtils.isBlank(pp.getPekerjaan()) || pp.getPendapatan().doubleValue() == 0.0) {
                    context.addMessage("Sila masukkan maklumat pada Maklumat Pemohon : " + permohonan.getIdPermohonan());
                    return null;
                }else if(permohonan.getTempohBulan()== 0 || permohonan.getTarikhMula()==null){
                    context.addMessage("Sila masukkan maklumat pada Maklumat Bayaran Ansuran : "+permohonan.getIdPermohonan());
                    return null;
                }
            }
        }
        LOG.info("beforeComplete::Finish");
        return proposedOutcome;
    }

    @Override
    public void afterComplete(StageContext context) {
    }

     @Override
    public void afterPushBack(StageContext context) {
        LOG.info("afterPushBack::START");
        LOG.info("afterPushBack::FINISH");
    }

    @Override
    public String beforePushBack(StageContext context, String proposedOutcome) {
        return "back";
    }

}
