package etanah.view.kaunter.validator;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Session;

import com.google.inject.Inject;

import etanah.dao.HakmilikDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.Hakmilik;
import etanah.model.KodUrusan;
import etanah.model.Pengguna;
import etanah.model.Permit;
import etanah.model.PermitSahLaku;
import etanah.model.Permohonan;
import etanah.view.kaunter.UrusanPermohonan;

public class UrusanPelupusanValidator {
    
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    PermohonanDAO permohonanDAO ;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;

    private static final Logger LOG = Logger.getLogger(UrusanPelupusanValidator.class);
    private static final boolean debug = LOG.isDebugEnabled();
        
    public List<String> validate(UrusanPermohonan urusan) {
        ArrayList<String> errors = new ArrayList<String>();
        Session s = sessionProvider.get();

        // PELUPUSAN
        if ("RLPS".equals(urusan.getKodUrusan())) {

            if (StringUtils.isBlank(urusan.getTeks1())) {
                throw new RuntimeException("Sila masukkan no lesen");
            } else {
                List<Permit> permit = (List<Permit>) s.createQuery("from Permit p where "
                        + "p.noPermit = :noPermit ").setString("noPermit", urusan.getTeks1()).list();
                if (debug) {
                    LOG.debug("permit.size()=" + permit.size());
                }
                if (permit.isEmpty()) {
                    errors.add("No permit yang dimasukkan tidak wujud!");
                } else {
                    List<PermitSahLaku> permitSahLaku = (List<PermitSahLaku>) s.createQuery("from PermitSahLaku p where "
                            + "p.permit.idPermit = :idPermit ").setLong("idPermit", permit.get(0).getIdPermit()).list();
                    if (permitSahLaku.size() == 4) {
                        errors.add("Anda tidak boleh memperbaharui lesen kerana lesen ini telah diperbaharui sebanyak tiga kali. Sila mohon semula lesen");
                    }
                    if (permitSahLaku.size() > 4) {
                        errors.add("Anda tidak boleh memperbaharui lesen kerana lesen ini telah diperbaharui sebanyak tiga kali. Sila mohon semula lesen");
                    }
                }
            }

        }
        
       if("RYKN".equals(urusan.getKodUrusan())){
           if(StringUtils.isBlank(urusan.getTeks1())){
               throw new RuntimeException("Sila masukkan Id Permohonan Sebelum") ;
           }
           else {
               Permohonan permohonan = new Permohonan();
               permohonan = permohonanDAO.findById(urusan.getTeks1()) ;
               if(permohonan != null){
                   if(permohonan.getKodUrusan().getKod().equals("PBMT")){
               urusan.setIdPermohonanSebelum(urusan.getTeks1());
                   }
                   else {
                       errors.add("Id permohonan ini bukan urusan Permohonan Pemberimilikan Tanah (PBMT)");
                   }
               }
               else {
                   errors.add("Id Permohonan Ini Tidak Wujud");
               }
           }
           
       }
        /**if("MMMCL".equals(urusan.getKodUrusan())){ Close since not use anymore
           if(StringUtils.isBlank(urusan.getTeks1())){
               throw new RuntimeException("Sila masukkan Id Permohonan Sebelum") ;
           }
           else {
               Permohonan permohonan = new Permohonan();
               permohonan = permohonanDAO.findById(urusan.getTeks1().toUpperCase()) ;
               if(permohonan != null){
                   if(permohonan.getKodUrusan().getKod().equals("MCMCL")){
               urusan.setIdPermohonanSebelum(urusan.getTeks1());
                   }
                   else {
                       errors.add("Id permohonan ini bukan urusan Permohonan Mencatitkan Tanah Adat Melaka");
                   }
               }
               else {
                   errors.add("Id Permohonan Ini Tidak Wujud");
               }
           }
           
       } **/

        return errors;
    }


}
