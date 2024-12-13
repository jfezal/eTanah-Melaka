/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.service.dashboard;

import com.google.inject.Inject;
import etanah.model.InfoMmkn;
import etanah.view.dashboard.tasklist.form.ListForm;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author nurulwahida
 */
public class MmknListService {

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;

    public List<InfoMmkn> listTugasanMmkn() {
        String query = "select p from etanah.model.InfoMmkn p, etanah.model.TugasanUtiliti t "
                + "where p.permohonan.idPermohonan = t.idPermohonan "
                + "and p.kodPeringkat.kod = t.kodPeringkat.kod ";
        Query q = sessionProvider.get().createQuery(query);
        return q.list();

    }

    public List<InfoMmkn> findbyIdMohon(String idMohon) {
        String query = "select p from etanah.model.InfoMmkn p "
                + "where p.permohonan.idPermohonan = :idMohon ";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idMohon", idMohon);
        return q.list();

    }

    public List<ListForm> findListTugasan() {
        List<InfoMmkn> list = listTugasanMmkn();
        List<ListForm> listForm = new ArrayList<ListForm>();
        for (InfoMmkn t : list) {
            ListForm f = new ListForm();

            f.setIdInfoMmk(t.getIdInfoMmkn()+"");

            f.setIdMohon(t.getPermohonan().getIdPermohonan());
            f.setKodPeringkat(t.getKodPeringkat());
            f.setNoFailMmkn(t.getNoFailMmkn());
            f.setKpsn(t.getKodKpsn());
            f.setTrhSidang(t.getTrhSidang()+"");
            f.setTempatSidang(t.getTempatSidang());
            f.setKeteranganMmkn(t.getKeteranganMmkn());

            listForm.add(f);
        }

        return listForm;

    }

    public Long countKeputusanLulus() {
        String query = "select count(p) from etanah.model.InfoMmkn p"
                + " where p.kodKpsn = 'Y'";
        Query q = sessionProvider.get().createQuery(query);
        return (Long) q.uniqueResult();
    }

}
