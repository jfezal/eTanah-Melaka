/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.kaunter.validator;

import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.model.Permit;
import etanah.model.PermitSahLaku;
import etanah.model.Permohonan;
import etanah.view.kaunter.UrusanPermohonan;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;

/**
 *
 * @author mohd.faidzal
 */
public class UrusanPengambilanValidator {
           @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
@Inject
PermohonanDAO permohonanDAO;
    public List<String> validate(UrusanPermohonan urusan) {
        ArrayList<String> errors = new ArrayList<String>();
        Session s = sessionProvider.get();

               
       if("813C".equals(urusan.getKodUrusan())){
           if(StringUtils.isBlank(urusan.getIdPermohonanSebelum())){
               throw new RuntimeException("Sila masukkan Id Permohonan Sebelum") ;
           }
           else {
               Permohonan permohonan = new Permohonan();
               permohonan = permohonanDAO.findById(urusan.getIdPermohonanSebelum()) ;
               if(permohonan != null){
                   if(permohonan.getKodUrusan().getKod().equals("SEK4")){
                       ArrayList<String> hakmilik = new ArrayList<String>();
                       if(false){
                       errors.add("Warta untuk permohonan seksyen 4 telah tamat");
                       }

                   }
                   else {
                       urusan.setIdPermohonanSebelum("");
                       errors.add("Id permohonan ini bukan urusan Permohonan Pemberimilikan Tanah (PBMT)");
                   }
               }
               else {
                   errors.add("Id Permohonan Ini Tidak Wujud");
               }
           }
           
       }

        return errors;
    }
}
