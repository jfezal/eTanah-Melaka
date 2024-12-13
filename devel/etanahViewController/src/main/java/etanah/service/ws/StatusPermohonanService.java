/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.service.ws;

import com.google.inject.Inject;
import etanah.model.Pemohon;
import etanah.model.Permohonan;
import etanah.service.RegService;
import etanah.view.portal.service.ws.StatusPermohonan;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author mohd.faidzal
 */
public class StatusPermohonanService {

    private static final Logger logger = Logger.getLogger(RegService.class);
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;

    public StatusPermohonan findStatusPermohonan(String idPermohonan) {
        StatusPermohonan form = new StatusPermohonan();

        Session s = sessionProvider.get();
        Query q = s.createQuery("from etanah.model.Permohonan u where u.idPermohonan = :idPermohonan");
        q.setString("idPermohonan", idPermohonan);
        q.uniqueResult();
        Permohonan mohon = (Permohonan) q.uniqueResult();
        form.setIdPermohonan(idPermohonan);
        form.setStatus(mohon.getKeputusan()!=null?mohon.getKeputusan().getNama():"Dalam Proses");
        form.setKodUrusan(mohon.getKodUrusan().getKod());
        form.setNamaUrusan(mohon.getKodUrusan().getNama());
        if (mohon.getTarikhKeputusan() != null) {
            form.setTarikhKeputusan(String.valueOf(mohon.getTarikhKeputusan()));
        }
        if (mohon.getKeputusanOleh() != null) {
            form.setKeputusanOleh(mohon.getKeputusanOleh().getNama());
        }
//        form.setIdPermohonan(mohon.getIdPermohonan());
        return form;
    }

    public List<StatusPermohonan> findStatusPermohonanByNoKP(String noKp) {
        StatusPermohonan form = new StatusPermohonan();
        List<StatusPermohonan> list = new ArrayList<StatusPermohonan>();
        Session s = sessionProvider.get();
         String query = "SELECT u from etanah.model.Permohonan u,etanah.model.Pemohon p  where u.idPermohonan = p.permohonan.idPermohonan and"
                + " p.pihak.noPengenalan = :noKp";
        Query q = s.createQuery(query);
        q.setString("noKp", noKp);
        List<Permohonan> listPermohonan = q.list();
        for (Permohonan permohonan : listPermohonan) {
            form.setStatus(permohonan.getStatus());
            form.setKodUrusan(permohonan.getKodUrusan().getKod());
            form.setNamaUrusan(permohonan.getKodUrusan().getNama());
            if (permohonan.getTarikhKeputusan() != null) {
                form.setTarikhKeputusan(String.valueOf(permohonan.getTarikhKeputusan()));
            }
            if (permohonan.getKeputusanOleh() != null) {
                form.setKeputusanOleh(permohonan.getKeputusanOleh().getNama());
            }
            list.add(form);
        }
        return list;
    }
}
